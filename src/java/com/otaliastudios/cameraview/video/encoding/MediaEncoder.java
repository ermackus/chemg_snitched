package com.otaliastudios.cameraview.video.encoding;

import java.nio.ByteBuffer;
import java.util.HashMap;
import com.otaliastudios.cameraview.internal.WorkerHandler;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import android.media.MediaCodec;
import android.media.MediaCodec$BufferInfo;
import com.otaliastudios.cameraview.CameraLogger;

public abstract class MediaEncoder
{
    private static final int INPUT_TIMEOUT_US = 0;
    private static final CameraLogger LOG;
    private static final int OUTPUT_TIMEOUT_US = 0;
    private static final int STATE_LIMIT_REACHED = 5;
    private static final int STATE_NONE = 0;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private static final int STATE_STARTED = 4;
    private static final int STATE_STARTING = 3;
    private static final int STATE_STOPPED = 7;
    private static final int STATE_STOPPING = 6;
    private static final String TAG;
    private MediaCodec$BufferInfo mBufferInfo;
    private MediaCodecBuffers mBuffers;
    private MediaEncoderEngine.Controller mController;
    private long mDebugSetStateTimestamp;
    private long mFirstTimeUs;
    private long mLastTimeUs;
    private boolean mMaxLengthReached;
    private long mMaxLengthUs;
    protected MediaCodec mMediaCodec;
    private final String mName;
    private OutputBufferPool mOutputBufferPool;
    private final Map<String, AtomicInteger> mPendingEvents;
    private long mStartTimeMillis;
    private int mState;
    private int mTrackIndex;
    protected WorkerHandler mWorker;
    
    static {
        LOG = CameraLogger.create(TAG = MediaEncoder.class.getSimpleName());
    }
    
    protected MediaEncoder(final String mName) {
        this.mState = 0;
        this.mPendingEvents = (Map<String, AtomicInteger>)new HashMap();
        this.mStartTimeMillis = 0L;
        this.mFirstTimeUs = Long.MIN_VALUE;
        this.mLastTimeUs = 0L;
        this.mDebugSetStateTimestamp = Long.MIN_VALUE;
        this.mName = mName;
    }
    
    private void onMaxLengthReached() {
        if (this.mMaxLengthReached) {
            MediaEncoder.LOG.w(new Object[] { this.mName, "onMaxLengthReached: Called twice." });
        }
        else {
            this.mMaxLengthReached = true;
            final int mState = this.mState;
            if (mState >= 5) {
                MediaEncoder.LOG.w(new Object[] { this.mName, "onMaxLengthReached: Reached in wrong state. Aborting.", mState });
            }
            else {
                MediaEncoder.LOG.w(new Object[] { this.mName, "onMaxLengthReached: Requesting a stop." });
                this.setState(5);
                this.mController.requestStop(this.mTrackIndex);
            }
        }
    }
    
    private void setState(final int mState) {
        if (this.mDebugSetStateTimestamp == Long.MIN_VALUE) {
            this.mDebugSetStateTimestamp = System.currentTimeMillis();
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final long mDebugSetStateTimestamp = this.mDebugSetStateTimestamp;
        this.mDebugSetStateTimestamp = System.currentTimeMillis();
        Object o = null;
        switch (mState) {
            case 7: {
                o = "STOPPED";
                break;
            }
            case 6: {
                o = "STOPPING";
                break;
            }
            case 5: {
                o = "LIMIT_REACHED";
                break;
            }
            case 4: {
                o = "STARTED";
                break;
            }
            case 3: {
                o = "STARTING";
                break;
            }
            case 2: {
                o = "PREPARED";
                break;
            }
            case 1: {
                o = "PREPARING";
                break;
            }
            case 0: {
                o = "NONE";
                break;
            }
        }
        MediaEncoder.LOG.w(new Object[] { this.mName, "setState:", o, "millisSinceLastState:", currentTimeMillis - mDebugSetStateTimestamp });
        this.mState = mState;
    }
    
    protected void acquireInputBuffer(final InputBuffer inputBuffer) {
        while (!this.tryAcquireInputBuffer(inputBuffer)) {}
    }
    
    protected final void drainOutput(final boolean b) {
        MediaEncoder.LOG.i(new Object[] { this.mName, "DRAINING - EOS:", b });
        final MediaCodec mMediaCodec = this.mMediaCodec;
        if (mMediaCodec == null) {
            MediaEncoder.LOG.e(new Object[] { "drain() was called before prepare() or after releasing." });
            return;
        }
        if (this.mBuffers == null) {
            this.mBuffers = new MediaCodecBuffers(mMediaCodec);
        }
        while (true) {
            final int dequeueOutputBuffer = this.mMediaCodec.dequeueOutputBuffer(this.mBufferInfo, 0L);
            MediaEncoder.LOG.i(new Object[] { this.mName, "DRAINING - Got status:", dequeueOutputBuffer });
            if (dequeueOutputBuffer == -1) {
                if (!b) {
                    break;
                }
                continue;
            }
            else if (dequeueOutputBuffer == -3) {
                this.mBuffers.onOutputBuffersChanged();
            }
            else if (dequeueOutputBuffer == -2) {
                if (this.mController.isStarted()) {
                    continue;
                }
                this.mTrackIndex = this.mController.notifyStarted(this.mMediaCodec.getOutputFormat());
                this.setState(4);
                this.mOutputBufferPool = new OutputBufferPool(this.mTrackIndex);
            }
            else if (dequeueOutputBuffer < 0) {
                final CameraLogger log = MediaEncoder.LOG;
                final StringBuilder sb = new StringBuilder();
                sb.append("Unexpected result from dequeueOutputBuffer: ");
                sb.append(dequeueOutputBuffer);
                log.e(new Object[] { sb.toString() });
            }
            else {
                final ByteBuffer outputBuffer = this.mBuffers.getOutputBuffer(dequeueOutputBuffer);
                if ((this.mBufferInfo.flags & 0x2) == 0x0 && this.mController.isStarted() && this.mBufferInfo.size != 0) {
                    outputBuffer.position(this.mBufferInfo.offset);
                    outputBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
                    if (this.mFirstTimeUs == Long.MIN_VALUE) {
                        final long presentationTimeUs = this.mBufferInfo.presentationTimeUs;
                        this.mFirstTimeUs = presentationTimeUs;
                        MediaEncoder.LOG.w(new Object[] { this.mName, "DRAINING - Got the first presentation time:", presentationTimeUs });
                    }
                    final long presentationTimeUs2 = this.mBufferInfo.presentationTimeUs;
                    this.mLastTimeUs = presentationTimeUs2;
                    this.mBufferInfo.presentationTimeUs = this.mStartTimeMillis * 1000L + presentationTimeUs2 - this.mFirstTimeUs;
                    MediaEncoder.LOG.v(new Object[] { this.mName, "DRAINING - About to write(). Adjusted presentation:", this.mBufferInfo.presentationTimeUs });
                    final OutputBuffer outputBuffer2 = (OutputBuffer)this.mOutputBufferPool.get();
                    outputBuffer2.info = this.mBufferInfo;
                    outputBuffer2.trackIndex = this.mTrackIndex;
                    outputBuffer2.data = outputBuffer;
                    this.onWriteOutput(this.mOutputBufferPool, outputBuffer2);
                }
                this.mMediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                if (!b && !this.mMaxLengthReached) {
                    final long mFirstTimeUs = this.mFirstTimeUs;
                    if (mFirstTimeUs != Long.MIN_VALUE) {
                        final long mLastTimeUs = this.mLastTimeUs;
                        if (mLastTimeUs - mFirstTimeUs > this.mMaxLengthUs) {
                            MediaEncoder.LOG.w(new Object[] { this.mName, "DRAINING - Reached maxLength! mLastTimeUs:", mLastTimeUs, "mStartTimeUs:", this.mFirstTimeUs, "mDeltaUs:", this.mLastTimeUs - this.mFirstTimeUs, "mMaxLengthUs:", this.mMaxLengthUs });
                            this.onMaxLengthReached();
                            break;
                        }
                    }
                }
                if ((this.mBufferInfo.flags & 0x4) != 0x0) {
                    MediaEncoder.LOG.w(new Object[] { this.mName, "DRAINING - Got EOS. Releasing the codec." });
                    this.onStopped();
                    break;
                }
                continue;
            }
        }
    }
    
    protected void encodeInputBuffer(final InputBuffer inputBuffer) {
        MediaEncoder.LOG.v(new Object[] { this.mName, "ENCODING - Buffer:", inputBuffer.index, "Bytes:", inputBuffer.length, "Presentation:", inputBuffer.timestamp });
        if (inputBuffer.isEndOfStream) {
            this.mMediaCodec.queueInputBuffer(inputBuffer.index, 0, 0, inputBuffer.timestamp, 4);
        }
        else {
            this.mMediaCodec.queueInputBuffer(inputBuffer.index, 0, inputBuffer.length, inputBuffer.timestamp, 0);
        }
    }
    
    protected abstract int getEncodedBitRate();
    
    protected long getMaxLengthUs() {
        return this.mMaxLengthUs;
    }
    
    protected final int getPendingEvents(final String s) {
        return ((AtomicInteger)this.mPendingEvents.get((Object)s)).intValue();
    }
    
    protected boolean hasReachedMaxLength() {
        return this.mMaxLengthReached;
    }
    
    final void notify(final String s, final Object o) {
        if (!this.mPendingEvents.containsKey((Object)s)) {
            this.mPendingEvents.put((Object)s, (Object)new AtomicInteger(0));
        }
        final AtomicInteger atomicInteger = (AtomicInteger)this.mPendingEvents.get((Object)s);
        atomicInteger.incrementAndGet();
        MediaEncoder.LOG.v(new Object[] { this.mName, "Notify was called. Posting. pendingEvents:", atomicInteger.intValue() });
        this.mWorker.post((Runnable)new Runnable(this, atomicInteger, s, o) {
            final MediaEncoder this$0;
            final Object val$data;
            final String val$event;
            final AtomicInteger val$pendingEvents;
            
            public void run() {
                MediaEncoder.LOG.v(new Object[] { this.this$0.mName, "Notify was called. Executing. pendingEvents:", this.val$pendingEvents.intValue() });
                this.this$0.onEvent(this.val$event, this.val$data);
                this.val$pendingEvents.decrementAndGet();
            }
        });
    }
    
    protected final void notifyFirstFrameMillis(final long mStartTimeMillis) {
        this.mStartTimeMillis = mStartTimeMillis;
    }
    
    protected void notifyMaxLengthReached() {
        this.onMaxLengthReached();
    }
    
    protected void onEvent(final String s, final Object o) {
    }
    
    protected abstract void onPrepare(final MediaEncoderEngine.Controller p0, final long p1);
    
    protected abstract void onStart();
    
    protected abstract void onStop();
    
    protected void onStopped() {
        MediaEncoder.LOG.w(new Object[] { this.mName, "is being released. Notifying controller and releasing codecs." });
        this.mController.notifyStopped(this.mTrackIndex);
        this.mMediaCodec.stop();
        this.mMediaCodec.release();
        this.mMediaCodec = null;
        this.mOutputBufferPool.clear();
        this.mOutputBufferPool = null;
        this.mBuffers = null;
        this.setState(7);
        this.mWorker.destroy();
    }
    
    protected void onWriteOutput(final OutputBufferPool outputBufferPool, final OutputBuffer outputBuffer) {
        this.mController.write(outputBufferPool, outputBuffer);
    }
    
    final void prepare(final MediaEncoderEngine.Controller mController, final long mMaxLengthUs) {
        final int mState = this.mState;
        if (mState >= 1) {
            MediaEncoder.LOG.e(new Object[] { this.mName, "Wrong state while preparing. Aborting.", mState });
            return;
        }
        this.mController = mController;
        this.mBufferInfo = new MediaCodec$BufferInfo();
        this.mMaxLengthUs = mMaxLengthUs;
        final WorkerHandler value = WorkerHandler.get(this.mName);
        this.mWorker = value;
        value.getThread().setPriority(10);
        MediaEncoder.LOG.i(new Object[] { this.mName, "Prepare was called. Posting." });
        this.mWorker.post((Runnable)new Runnable(this, mController, mMaxLengthUs) {
            final MediaEncoder this$0;
            final MediaEncoderEngine.Controller val$controller;
            final long val$maxLengthUs;
            
            public void run() {
                MediaEncoder.LOG.i(new Object[] { this.this$0.mName, "Prepare was called. Executing." });
                this.this$0.setState(1);
                this.this$0.onPrepare(this.val$controller, this.val$maxLengthUs);
                this.this$0.setState(2);
            }
        });
    }
    
    final void start() {
        MediaEncoder.LOG.w(new Object[] { this.mName, "Start was called. Posting." });
        this.mWorker.post((Runnable)new Runnable(this) {
            final MediaEncoder this$0;
            
            public void run() {
                if (this.this$0.mState >= 2 && this.this$0.mState < 3) {
                    this.this$0.setState(3);
                    MediaEncoder.LOG.w(new Object[] { this.this$0.mName, "Start was called. Executing." });
                    this.this$0.onStart();
                    return;
                }
                MediaEncoder.LOG.e(new Object[] { this.this$0.mName, "Wrong state while starting. Aborting.", this.this$0.mState });
            }
        });
    }
    
    final void stop() {
        final int mState = this.mState;
        if (mState >= 6) {
            MediaEncoder.LOG.e(new Object[] { this.mName, "Wrong state while stopping. Aborting.", mState });
            return;
        }
        this.setState(6);
        MediaEncoder.LOG.w(new Object[] { this.mName, "Stop was called. Posting." });
        this.mWorker.post((Runnable)new Runnable(this) {
            final MediaEncoder this$0;
            
            public void run() {
                MediaEncoder.LOG.w(new Object[] { this.this$0.mName, "Stop was called. Executing." });
                this.this$0.onStop();
            }
        });
    }
    
    protected boolean tryAcquireInputBuffer(final InputBuffer inputBuffer) {
        if (this.mBuffers == null) {
            this.mBuffers = new MediaCodecBuffers(this.mMediaCodec);
        }
        final int dequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(0L);
        if (dequeueInputBuffer < 0) {
            return false;
        }
        inputBuffer.index = dequeueInputBuffer;
        inputBuffer.data = this.mBuffers.getInputBuffer(dequeueInputBuffer);
        return true;
    }
}
