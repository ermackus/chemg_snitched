package com.otaliastudios.cameraview.frame;

import com.otaliastudios.cameraview.engine.offset.Angles;
import com.otaliastudios.cameraview.size.Size;
import java.util.concurrent.LinkedBlockingQueue;

public class ByteBufferFrameManager extends FrameManager<byte[]>
{
    private static final int BUFFER_MODE_DISPATCH = 0;
    private static final int BUFFER_MODE_ENQUEUE = 1;
    private ByteBufferFrameManager.ByteBufferFrameManager$BufferCallback mBufferCallback;
    private final int mBufferMode;
    private LinkedBlockingQueue<byte[]> mBufferQueue;
    
    public ByteBufferFrameManager(final int n, final ByteBufferFrameManager.ByteBufferFrameManager$BufferCallback mBufferCallback) {
        super(n, (Class)byte[].class);
        if (mBufferCallback != null) {
            this.mBufferCallback = mBufferCallback;
            this.mBufferMode = 0;
        }
        else {
            this.mBufferQueue = (LinkedBlockingQueue<byte[]>)new LinkedBlockingQueue(n);
            this.mBufferMode = 1;
        }
    }
    
    public byte[] getBuffer() {
        if (this.mBufferMode == 1) {
            return (byte[])this.mBufferQueue.poll();
        }
        throw new IllegalStateException("Can't call getBuffer() when not in BUFFER_MODE_ENQUEUE.");
    }
    
    public void onBufferUnused(final byte[] array) {
        if (this.mBufferMode == 1) {
            if (this.isSetUp()) {
                this.mBufferQueue.offer((Object)array);
            }
            else {
                ByteBufferFrameManager.LOG.w(new Object[] { "onBufferUnused: buffer was returned but we're not set up anymore." });
            }
            return;
        }
        throw new IllegalStateException("Can't call onBufferUnused() when not in BUFFER_MODE_ENQUEUE.");
    }
    
    protected byte[] onCloneFrameData(final byte[] array) {
        final byte[] array2 = new byte[array.length];
        System.arraycopy((Object)array, 0, (Object)array2, 0, array.length);
        return array2;
    }
    
    protected void onFrameDataReleased(final byte[] array, final boolean b) {
        if (b && array.length == this.getFrameBytes()) {
            if (this.mBufferMode == 0) {
                this.mBufferCallback.onBufferAvailable(array);
            }
            else {
                this.mBufferQueue.offer((Object)array);
            }
        }
    }
    
    public void release() {
        super.release();
        if (this.mBufferMode == 1) {
            this.mBufferQueue.clear();
        }
    }
    
    public void setUp(int i, final Size size, final Angles angles) {
        super.setUp(i, size, angles);
        final int frameBytes = this.getFrameBytes();
        for (i = 0; i < this.getPoolSize(); ++i) {
            if (this.mBufferMode == 0) {
                this.mBufferCallback.onBufferAvailable(new byte[frameBytes]);
            }
            else {
                this.mBufferQueue.offer((Object)new byte[frameBytes]);
            }
        }
    }
}
