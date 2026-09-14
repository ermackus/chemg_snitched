package com.kingagroot.kingdraw.core.view3d.opengl;

import java.nio.Buffer;
import android.opengl.GLES20;
import java.nio.FloatBuffer;

public class GlBuffer
{
    int bufferSizeBytes;
    private int[] name;
    int stride;
    
    public GlBuffer() {
        this.name = new int[1];
    }
    
    public void initBuffer(final int stride, final int n, final FloatBuffer floatBuffer) {
        this.stride = stride;
        this.bufferSizeBytes = stride * n;
        GLES20.glGenBuffers(1, this.name, 0);
        GLES20.glBindBuffer(34962, this.name[0]);
        GLES20.glBufferData(34962, this.bufferSizeBytes, (Buffer)floatBuffer, 35044);
    }
    
    public void prepareToDraw(final int n, final int n2, final int n3) {
        GLES20.glBindBuffer(34962, this.name[0]);
        GLES20.glEnableVertexAttribArray(n);
        GLES20.glVertexAttribPointer(n, n2, 5126, false, 0, n3);
    }
}
