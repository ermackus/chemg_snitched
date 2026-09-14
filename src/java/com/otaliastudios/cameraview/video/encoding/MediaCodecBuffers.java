package com.otaliastudios.cameraview.video.encoding;

import android.os.Build$VERSION;
import android.media.MediaCodec;
import java.nio.ByteBuffer;

class MediaCodecBuffers
{
    private final ByteBuffer[] mInputBuffers;
    private final MediaCodec mMediaCodec;
    private ByteBuffer[] mOutputBuffers;
    
    MediaCodecBuffers(final MediaCodec mMediaCodec) {
        this.mMediaCodec = mMediaCodec;
        if (Build$VERSION.SDK_INT < 21) {
            this.mInputBuffers = mMediaCodec.getInputBuffers();
            this.mOutputBuffers = mMediaCodec.getOutputBuffers();
        }
        else {
            this.mOutputBuffers = null;
            this.mInputBuffers = null;
        }
    }
    
    ByteBuffer getInputBuffer(final int n) {
        if (Build$VERSION.SDK_INT >= 21) {
            return this.mMediaCodec.getInputBuffer(n);
        }
        final ByteBuffer byteBuffer = this.mInputBuffers[n];
        byteBuffer.clear();
        return byteBuffer;
    }
    
    ByteBuffer getOutputBuffer(final int n) {
        if (Build$VERSION.SDK_INT >= 21) {
            return this.mMediaCodec.getOutputBuffer(n);
        }
        return this.mOutputBuffers[n];
    }
    
    void onOutputBuffersChanged() {
        if (Build$VERSION.SDK_INT < 21) {
            this.mOutputBuffers = this.mMediaCodec.getOutputBuffers();
        }
    }
}
