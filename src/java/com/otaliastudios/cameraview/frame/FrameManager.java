package com.otaliastudios.cameraview.frame;

import android.graphics.ImageFormat;
import com.otaliastudios.cameraview.engine.offset.Axis;
import com.otaliastudios.cameraview.engine.offset.Reference;
import com.otaliastudios.cameraview.size.Size;
import java.util.concurrent.LinkedBlockingQueue;
import com.otaliastudios.cameraview.engine.offset.Angles;
import com.otaliastudios.cameraview.CameraLogger;

public abstract class FrameManager<T>
{
    protected static final CameraLogger LOG;
    private static final String TAG;
    private Angles mAngles;
    private int mFrameBytes;
    private final Class<T> mFrameDataClass;
    private int mFrameFormat;
    private LinkedBlockingQueue<Frame> mFrameQueue;
    private Size mFrameSize;
    private final int mPoolSize;
    
    static {
        LOG = CameraLogger.create(TAG = FrameManager.class.getSimpleName());
    }
    
    protected FrameManager(final int mPoolSize, final Class<T> mFrameDataClass) {
        this.mFrameBytes = -1;
        this.mFrameSize = null;
        this.mFrameFormat = -1;
        this.mPoolSize = mPoolSize;
        this.mFrameDataClass = mFrameDataClass;
        this.mFrameQueue = (LinkedBlockingQueue<Frame>)new LinkedBlockingQueue(this.mPoolSize);
    }
    
    final T cloneFrameData(final T t) {
        return this.onCloneFrameData(t);
    }
    
    public Frame getFrame(final T t, final long n) {
        if (!this.isSetUp()) {
            throw new IllegalStateException("Can't call getFrame() after releasing or before setUp.");
        }
        final Frame frame = (Frame)this.mFrameQueue.poll();
        if (frame != null) {
            FrameManager.LOG.v(new Object[] { "getFrame for time:", n, "RECYCLING." });
            frame.setContent(t, n, this.mAngles.offset(Reference.SENSOR, Reference.OUTPUT, Axis.RELATIVE_TO_SENSOR), this.mAngles.offset(Reference.SENSOR, Reference.VIEW, Axis.RELATIVE_TO_SENSOR), this.mFrameSize, this.mFrameFormat);
            return frame;
        }
        FrameManager.LOG.i(new Object[] { "getFrame for time:", n, "NOT AVAILABLE." });
        this.onFrameDataReleased(t, false);
        return null;
    }
    
    public final int getFrameBytes() {
        return this.mFrameBytes;
    }
    
    public final Class<T> getFrameDataClass() {
        return this.mFrameDataClass;
    }
    
    public final int getPoolSize() {
        return this.mPoolSize;
    }
    
    protected boolean isSetUp() {
        return this.mFrameSize != null;
    }
    
    protected abstract T onCloneFrameData(final T p0);
    
    protected abstract void onFrameDataReleased(final T p0, final boolean p1);
    
    void onFrameReleased(final Frame frame, final T t) {
        if (!this.isSetUp()) {
            return;
        }
        this.onFrameDataReleased(t, this.mFrameQueue.offer((Object)frame));
    }
    
    public void release() {
        if (!this.isSetUp()) {
            FrameManager.LOG.w(new Object[] { "release called twice. Ignoring." });
            return;
        }
        FrameManager.LOG.i(new Object[] { "release: Clearing the frame and buffer queue." });
        this.mFrameQueue.clear();
        this.mFrameBytes = -1;
        this.mFrameSize = null;
        this.mFrameFormat = -1;
        this.mAngles = null;
    }
    
    public void setUp(int i, final Size mFrameSize, final Angles mAngles) {
        this.isSetUp();
        this.mFrameSize = mFrameSize;
        this.mFrameFormat = i;
        i = ImageFormat.getBitsPerPixel(i);
        this.mFrameBytes = (int)Math.ceil(mFrameSize.getHeight() * mFrameSize.getWidth() * i / 8.0);
        for (i = 0; i < this.getPoolSize(); ++i) {
            this.mFrameQueue.offer((Object)new Frame(this));
        }
        this.mAngles = mAngles;
    }
}
