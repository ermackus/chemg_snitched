package com.otaliastudios.cameraview.video.encoding;

import android.os.Bundle;
import android.os.Build$VERSION;
import java.io.IOException;
import android.media.MediaCrypto;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.view.Surface;
import com.otaliastudios.cameraview.CameraLogger;

abstract class VideoMediaEncoder<C extends VideoConfig> extends MediaEncoder
{
    private static final CameraLogger LOG;
    private static final String TAG;
    protected C mConfig;
    protected int mFrameNumber;
    protected Surface mSurface;
    private boolean mSyncFrameFound;
    
    static {
        LOG = CameraLogger.create(TAG = VideoMediaEncoder.class.getSimpleName());
    }
    
    VideoMediaEncoder(final C mConfig) {
        super("VideoEncoder");
        this.mFrameNumber = -1;
        this.mSyncFrameFound = false;
        this.mConfig = mConfig;
    }
    
    protected int getEncodedBitRate() {
        return this.mConfig.bitRate;
    }
    
    protected void onPrepare(final MediaEncoderEngine$Controller mediaEncoderEngine$Controller, final long n) {
        final MediaFormat videoFormat = MediaFormat.createVideoFormat(this.mConfig.mimeType, this.mConfig.width, this.mConfig.height);
        videoFormat.setInteger("color-format", 2130708361);
        videoFormat.setInteger("bitrate", this.mConfig.bitRate);
        videoFormat.setInteger("frame-rate", this.mConfig.frameRate);
        videoFormat.setInteger("i-frame-interval", 1);
        videoFormat.setInteger("rotation-degrees", this.mConfig.rotation);
        try {
            if (this.mConfig.encoder != null) {
                this.mMediaCodec = MediaCodec.createByCodecName(this.mConfig.encoder);
            }
            else {
                this.mMediaCodec = MediaCodec.createEncoderByType(this.mConfig.mimeType);
            }
            this.mMediaCodec.configure(videoFormat, (Surface)null, (MediaCrypto)null, 1);
            this.mSurface = this.mMediaCodec.createInputSurface();
            this.mMediaCodec.start();
        }
        catch (final IOException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    protected void onStart() {
        this.mFrameNumber = 0;
    }
    
    protected void onStop() {
        VideoMediaEncoder.LOG.i(new Object[] { "onStop", "setting mFrameNumber to 1 and signaling the end of input stream." });
        this.mFrameNumber = -1;
        this.mMediaCodec.signalEndOfInputStream();
        this.drainOutput(true);
    }
    
    protected void onWriteOutput(final OutputBufferPool outputBufferPool, final OutputBuffer outputBuffer) {
        if (!this.mSyncFrameFound) {
            VideoMediaEncoder.LOG.w(new Object[] { "onWriteOutput:", "sync frame not found yet. Checking." });
            if ((outputBuffer.info.flags & 0x1) == 0x1) {
                VideoMediaEncoder.LOG.w(new Object[] { "onWriteOutput:", "SYNC FRAME FOUND!" });
                this.mSyncFrameFound = true;
                super.onWriteOutput(outputBufferPool, outputBuffer);
            }
            else {
                VideoMediaEncoder.LOG.w(new Object[] { "onWriteOutput:", "DROPPING FRAME and requesting a sync frame soon." });
                if (Build$VERSION.SDK_INT >= 19) {
                    final Bundle parameters = new Bundle();
                    parameters.putInt("request-sync", 0);
                    this.mMediaCodec.setParameters(parameters);
                }
                outputBufferPool.recycle((Object)outputBuffer);
            }
        }
        else {
            super.onWriteOutput(outputBufferPool, outputBuffer);
        }
    }
    
    protected boolean shouldRenderFrame(final long n) {
        if (n == 0L) {
            return false;
        }
        if (this.mFrameNumber < 0) {
            return false;
        }
        if (this.hasReachedMaxLength()) {
            return false;
        }
        ++this.mFrameNumber;
        return true;
    }
}
