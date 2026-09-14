package com.otaliastudios.cameraview.video.encoding;

import java.io.IOException;
import android.media.MediaCrypto;
import android.view.Surface;
import android.media.MediaCodec;
import android.media.MediaFormat;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Map;
import com.otaliastudios.cameraview.CameraLogger;

public class AudioMediaEncoder extends MediaEncoder
{
    private static final CameraLogger LOG;
    private static final boolean PERFORMANCE_DEBUG = false;
    private static final boolean PERFORMANCE_FILL_GAPS = true;
    private static final int PERFORMANCE_MAX_GAPS = 8;
    private static final String TAG;
    private AudioNoise mAudioNoise;
    private ByteBufferPool mByteBufferPool;
    private AudioConfig mConfig;
    private long mDebugExecuteAvgDelay;
    private int mDebugExecuteCount;
    private long mDebugSendAvgDelay;
    private int mDebugSendCount;
    private Map<Long, Long> mDebugSendStartMap;
    private AudioMediaEncoder.AudioMediaEncoder$AudioEncodingThread mEncoder;
    private InputBufferPool mInputBufferPool;
    private final LinkedBlockingQueue<InputBuffer> mInputBufferQueue;
    private AudioMediaEncoder.AudioMediaEncoder$AudioRecordingThread mRecorder;
    private boolean mRequestStop;
    private final AudioTimestamp mTimestamp;
    
    static {
        LOG = CameraLogger.create(TAG = AudioMediaEncoder.class.getSimpleName());
    }
    
    public AudioMediaEncoder(AudioConfig copy) {
        super("AudioEncoder");
        this.mRequestStop = false;
        this.mInputBufferPool = new InputBufferPool();
        this.mInputBufferQueue = (LinkedBlockingQueue<InputBuffer>)new LinkedBlockingQueue();
        this.mDebugSendCount = 0;
        this.mDebugExecuteCount = 0;
        this.mDebugSendAvgDelay = 0L;
        this.mDebugExecuteAvgDelay = 0L;
        this.mDebugSendStartMap = (Map<Long, Long>)new HashMap();
        copy = copy.copy();
        this.mConfig = copy;
        this.mTimestamp = new AudioTimestamp(copy.byteRate());
        this.mEncoder = new AudioMediaEncoder.AudioMediaEncoder$AudioEncodingThread(this, (AudioMediaEncoder$1)null);
        this.mRecorder = new AudioMediaEncoder.AudioMediaEncoder$AudioRecordingThread(this, (AudioMediaEncoder$1)null);
    }
    
    private void skipFrames(final int n) {
        try {
            Thread.sleep(AudioTimestamp.bytesToMillis((long)(this.mConfig.frameSize() * n), this.mConfig.byteRate()));
        }
        catch (final InterruptedException ex) {}
    }
    
    protected int getEncodedBitRate() {
        return this.mConfig.bitRate;
    }
    
    protected void onPrepare(final MediaEncoderEngine$Controller mediaEncoderEngine$Controller, final long n) {
        final MediaFormat audioFormat = MediaFormat.createAudioFormat(this.mConfig.mimeType, this.mConfig.samplingFrequency, this.mConfig.channels);
        audioFormat.setInteger("aac-profile", 2);
        audioFormat.setInteger("channel-mask", this.mConfig.audioFormatChannels());
        audioFormat.setInteger("bitrate", this.mConfig.bitRate);
        try {
            if (this.mConfig.encoder != null) {
                this.mMediaCodec = MediaCodec.createByCodecName(this.mConfig.encoder);
            }
            else {
                this.mMediaCodec = MediaCodec.createEncoderByType(this.mConfig.mimeType);
            }
            this.mMediaCodec.configure(audioFormat, (Surface)null, (MediaCrypto)null, 1);
            this.mMediaCodec.start();
            this.mByteBufferPool = new ByteBufferPool(this.mConfig.frameSize(), this.mConfig.bufferPoolMaxSize());
            this.mAudioNoise = new AudioNoise(this.mConfig);
        }
        catch (final IOException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    protected void onStart() {
        this.mRequestStop = false;
        this.mRecorder.start();
        this.mEncoder.start();
    }
    
    protected void onStop() {
        this.mRequestStop = true;
    }
    
    protected void onStopped() {
        super.onStopped();
        this.mRequestStop = false;
        this.mEncoder = null;
        this.mRecorder = null;
        final ByteBufferPool mByteBufferPool = this.mByteBufferPool;
        if (mByteBufferPool != null) {
            mByteBufferPool.clear();
            this.mByteBufferPool = null;
        }
    }
}
