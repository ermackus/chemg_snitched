package com.otaliastudios.cameraview.video.encoding;

import com.otaliastudios.cameraview.internal.Pool$Factory;
import java.nio.ByteBuffer;
import com.otaliastudios.cameraview.internal.Pool;

class ByteBufferPool extends Pool<ByteBuffer>
{
    ByteBufferPool(final int n, final int n2) {
        super(n2, (Pool$Factory)new Pool$Factory<ByteBuffer>(n) {
            final int val$bufferSize;
            
            public ByteBuffer create() {
                return ByteBuffer.allocateDirect(this.val$bufferSize);
            }
        });
    }
}
