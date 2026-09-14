package com.otaliastudios.cameraview.video.encoding;

import com.otaliastudios.cameraview.internal.Pool$Factory;
import com.otaliastudios.cameraview.internal.Pool;

class InputBufferPool extends Pool<InputBuffer>
{
    InputBufferPool() {
        super(Integer.MAX_VALUE, (Pool$Factory)new Pool$Factory<InputBuffer>() {
            public InputBuffer create() {
                return new InputBuffer();
            }
        });
    }
}
