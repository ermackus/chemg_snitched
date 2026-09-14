package com.otaliastudios.cameraview.video.encoding;

import android.media.MediaCodec$BufferInfo;
import com.otaliastudios.cameraview.internal.Pool$Factory;
import com.otaliastudios.cameraview.internal.Pool;

class OutputBufferPool extends Pool<OutputBuffer>
{
    OutputBufferPool(final int n) {
        super(Integer.MAX_VALUE, (Pool$Factory)new Pool$Factory<OutputBuffer>(n) {
            final int val$trackIndex;
            
            public OutputBuffer create() {
                final OutputBuffer outputBuffer = new OutputBuffer();
                outputBuffer.trackIndex = this.val$trackIndex;
                outputBuffer.info = new MediaCodec$BufferInfo();
                return outputBuffer;
            }
        });
    }
}
