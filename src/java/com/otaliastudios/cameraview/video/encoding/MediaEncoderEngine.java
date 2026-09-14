package com.otaliastudios.cameraview.video.encoding;

import java.util.Calendar;
import android.media.MediaFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import android.media.MediaMuxer;
import java.util.List;
import com.otaliastudios.cameraview.internal.WorkerHandler;
import com.otaliastudios.cameraview.CameraLogger;

public class MediaEncoderEngine
{
    private static final boolean DEBUG_PERFORMANCE = true;
    public static final int END_BY_MAX_DURATION = 1;
    public static final int END_BY_MAX_SIZE = 2;
    public static final int END_BY_USER = 0;
    private static final CameraLogger LOG;
    private static final String TAG;
    private final Controller mController;
    private final Object mControllerLock;
    private final WorkerHandler mControllerThread;
    private final List<MediaEncoder> mEncoders;
    private int mEndReason;
    private Listener mListener;
    private MediaMuxer mMediaMuxer;
    private boolean mMediaMuxerStarted;
    private int mPossibleEndReason;
    private int mStartedEncodersCount;
    private int mStoppedEncodersCount;
    
    static {
        LOG = CameraLogger.create(TAG = MediaEncoderEngine.class.getSimpleName());
    }
    
    public MediaEncoderEngine(final File file, final VideoMediaEncoder videoMediaEncoder, final AudioMediaEncoder audioMediaEncoder, int mPossibleEndReason, long min, final Listener mListener) {
        this.mEncoders = (List<MediaEncoder>)new ArrayList();
        this.mStartedEncodersCount = 0;
        this.mStoppedEncodersCount = 0;
        this.mMediaMuxerStarted = false;
        this.mController = new Controller();
        this.mControllerThread = WorkerHandler.get("EncoderEngine");
        this.mControllerLock = new Object();
        this.mEndReason = 0;
        this.mListener = mListener;
        this.mEncoders.add((Object)videoMediaEncoder);
        if (audioMediaEncoder != null) {
            this.mEncoders.add((Object)audioMediaEncoder);
        }
        try {
            this.mMediaMuxer = new MediaMuxer(file.toString(), 0);
            final Iterator iterator = this.mEncoders.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                n += ((MediaEncoder)iterator.next()).getEncodedBitRate();
            }
            final long n2 = min / (n / 8) * 1000L * 1000L;
            final long n3 = mPossibleEndReason * 1000L;
            final long n4 = lcmp(min, 0L);
            if (n4 > 0 && mPossibleEndReason > 0) {
                if (n2 < n3) {
                    mPossibleEndReason = 2;
                }
                else {
                    mPossibleEndReason = 1;
                }
                this.mPossibleEndReason = mPossibleEndReason;
                min = Math.min(n2, n3);
            }
            else if (n4 > 0) {
                this.mPossibleEndReason = 2;
                min = n2;
            }
            else if (mPossibleEndReason > 0) {
                this.mPossibleEndReason = 1;
                min = n3;
            }
            else {
                min = Long.MAX_VALUE;
            }
            MediaEncoderEngine.LOG.w(new Object[] { "Computed a max duration of", min / 1000000.0f });
            final Iterator iterator2 = this.mEncoders.iterator();
            while (iterator2.hasNext()) {
                ((MediaEncoder)iterator2.next()).prepare(this.mController, min);
            }
        }
        catch (final IOException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    private void end() {
        MediaEncoderEngine.LOG.i(new Object[] { "end:", "Releasing muxer after all encoders have been released." });
        final MediaMuxer mMediaMuxer = this.mMediaMuxer;
        Exception ex3;
        if (mMediaMuxer != null) {
            Exception ex = null;
            try {
                mMediaMuxer.stop();
                ex = null;
            }
            catch (final Exception ex2) {}
            try {
                this.mMediaMuxer.release();
                ex3 = ex;
            }
            catch (final Exception ex4) {
                ex3 = ex;
                if (ex == null) {
                    ex3 = ex4;
                }
            }
            this.mMediaMuxer = null;
        }
        else {
            ex3 = null;
        }
        MediaEncoderEngine.LOG.w(new Object[] { "end:", "Dispatching end to listener - reason:", this.mEndReason, "error:", ex3 });
        final Listener mListener = this.mListener;
        if (mListener != null) {
            mListener.onEncodingEnd(this.mEndReason, ex3);
            this.mListener = null;
        }
        this.mEndReason = 0;
        this.mStartedEncodersCount = 0;
        this.mStoppedEncodersCount = 0;
        this.mMediaMuxerStarted = false;
        this.mControllerThread.destroy();
        MediaEncoderEngine.LOG.i(new Object[] { "end:", "Completed." });
    }
    
    public AudioMediaEncoder getAudioEncoder() {
        if (this.mEncoders.size() > 1) {
            return (AudioMediaEncoder)this.mEncoders.get(1);
        }
        return null;
    }
    
    public VideoMediaEncoder getVideoEncoder() {
        return (VideoMediaEncoder)this.mEncoders.get(0);
    }
    
    public final void notify(final String s, final Object o) {
        MediaEncoderEngine.LOG.v(new Object[] { "Passing event to encoders:", s });
        final Iterator iterator = this.mEncoders.iterator();
        while (iterator.hasNext()) {
            ((MediaEncoder)iterator.next()).notify(s, o);
        }
    }
    
    public final void start() {
        MediaEncoderEngine.LOG.i(new Object[] { "Passing event to encoders:", "START" });
        final Iterator iterator = this.mEncoders.iterator();
        while (iterator.hasNext()) {
            ((MediaEncoder)iterator.next()).start();
        }
    }
    
    public final void stop() {
        MediaEncoderEngine.LOG.i(new Object[] { "Passing event to encoders:", "STOP" });
        final Iterator iterator = this.mEncoders.iterator();
        while (iterator.hasNext()) {
            ((MediaEncoder)iterator.next()).stop();
        }
        final Listener mListener = this.mListener;
        if (mListener != null) {
            mListener.onEncodingStop();
        }
    }
    
    public class Controller
    {
        private Map<Integer, Integer> mDebugCount;
        final MediaEncoderEngine this$0;
        
        public Controller(final MediaEncoderEngine this$0) {
            this.this$0 = this$0;
            this.mDebugCount = (Map<Integer, Integer>)new HashMap();
        }
        
        public boolean isStarted() {
            final Object access$000 = this.this$0.mControllerLock;
            synchronized (access$000) {
                return this.this$0.mMediaMuxerStarted;
            }
        }
        
        public int notifyStarted(final MediaFormat mediaFormat) {
            final Object access$000 = this.this$0.mControllerLock;
            synchronized (access$000) {
                if (!this.this$0.mMediaMuxerStarted) {
                    final int addTrack = this.this$0.mMediaMuxer.addTrack(mediaFormat);
                    MediaEncoderEngine.LOG.w(new Object[] { "notifyStarted:", "Assigned track", addTrack, "to format", mediaFormat.getString("mime") });
                    if (++this.this$0.mStartedEncodersCount == this.this$0.mEncoders.size()) {
                        MediaEncoderEngine.LOG.w(new Object[] { "notifyStarted:", "All encoders have started.", "Starting muxer and dispatching onEncodingStart()." });
                        this.this$0.mControllerThread.run((Runnable)new Runnable(this) {
                            final Controller this$1;
                            
                            public void run() {
                                this.this$1.this$0.mMediaMuxer.start();
                                this.this$1.this$0.mMediaMuxerStarted = true;
                                if (this.this$1.this$0.mListener != null) {
                                    this.this$1.this$0.mListener.onEncodingStart();
                                }
                            }
                        });
                    }
                    return addTrack;
                }
                throw new IllegalStateException("Trying to start but muxer started already");
            }
        }
        
        public void notifyStopped(final int n) {
            final Object access$000 = this.this$0.mControllerLock;
            synchronized (access$000) {
                MediaEncoderEngine.LOG.w(new Object[] { "notifyStopped:", "Called for track", n });
                if (++this.this$0.mStoppedEncodersCount == this.this$0.mEncoders.size()) {
                    MediaEncoderEngine.LOG.w(new Object[] { "requestStop:", "All encoders have been stopped.", "Stopping the muxer." });
                    this.this$0.mControllerThread.run((Runnable)new Runnable(this) {
                        final Controller this$1;
                        
                        public void run() {
                            this.this$1.this$0.end();
                        }
                    });
                }
            }
        }
        
        public void requestStop(final int n) {
            final Object access$000 = this.this$0.mControllerLock;
            synchronized (access$000) {
                MediaEncoderEngine.LOG.w(new Object[] { "requestStop:", "Called for track", n });
                if (--this.this$0.mStartedEncodersCount == 0) {
                    MediaEncoderEngine.LOG.w(new Object[] { "requestStop:", "All encoders have requested a stop.", "Stopping them." });
                    this.this$0.mEndReason = this.this$0.mPossibleEndReason;
                    this.this$0.mControllerThread.run((Runnable)new Runnable(this) {
                        final Controller this$1;
                        
                        public void run() {
                            this.this$1.this$0.stop();
                        }
                    });
                }
            }
        }
        
        public void write(final OutputBufferPool outputBufferPool, final OutputBuffer outputBuffer) {
            Integer value = (Integer)this.mDebugCount.get((Object)outputBuffer.trackIndex);
            final Map<Integer, Integer> mDebugCount = this.mDebugCount;
            final int trackIndex = outputBuffer.trackIndex;
            int intValue;
            if (value == null) {
                intValue = 1;
            }
            else {
                ++value;
                intValue = value;
            }
            mDebugCount.put((Object)trackIndex, (Object)intValue);
            final Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(outputBuffer.info.presentationTimeUs / 1000L);
            final CameraLogger access$300 = MediaEncoderEngine.LOG;
            final int trackIndex2 = outputBuffer.trackIndex;
            final long presentationTimeUs = outputBuffer.info.presentationTimeUs;
            final StringBuilder sb = new StringBuilder();
            sb.append(instance.get(13));
            sb.append(":");
            sb.append(instance.get(14));
            access$300.v(new Object[] { "write:", "Writing into muxer -", "track:", trackIndex2, "presentation:", presentationTimeUs, "readable:", sb.toString(), "count:", value });
            this.this$0.mMediaMuxer.writeSampleData(outputBuffer.trackIndex, outputBuffer.data, outputBuffer.info);
            outputBufferPool.recycle((Object)outputBuffer);
        }
    }
    
    public interface Listener
    {
        void onEncodingEnd(final int p0, final Exception p1);
        
        void onEncodingStart();
        
        void onEncodingStop();
    }
}
