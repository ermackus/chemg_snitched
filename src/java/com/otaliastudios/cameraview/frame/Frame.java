package com.otaliastudios.cameraview.frame;

import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.CameraLogger;

public class Frame
{
    private static final CameraLogger LOG;
    private static final String TAG;
    private Object mData;
    private final Class<?> mDataClass;
    private int mFormat;
    private long mLastTime;
    private final FrameManager mManager;
    private Size mSize;
    private long mTime;
    private int mUserRotation;
    private int mViewRotation;
    
    static {
        LOG = CameraLogger.create(TAG = Frame.class.getSimpleName());
    }
    
    Frame(final FrameManager mManager) {
        this.mData = null;
        this.mTime = -1L;
        this.mLastTime = -1L;
        this.mUserRotation = 0;
        this.mViewRotation = 0;
        this.mSize = null;
        this.mFormat = -1;
        this.mManager = mManager;
        this.mDataClass = mManager.getFrameDataClass();
    }
    
    private void ensureHasContent() {
        if (this.hasContent()) {
            return;
        }
        Frame.LOG.e(new Object[] { "Frame is dead! time:", this.mTime, "lastTime:", this.mLastTime });
        throw new RuntimeException("You should not access a released frame. If this frame was passed to a FrameProcessor, you can only use its contents synchronously, for the duration of the process() method.");
    }
    
    private boolean hasContent() {
        return this.mData != null;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof Frame && ((Frame)o).mTime == this.mTime;
    }
    
    public Frame freeze() {
        this.ensureHasContent();
        final Frame frame = new Frame(this.mManager);
        frame.setContent(this.mManager.cloneFrameData(this.getData()), this.mTime, this.mUserRotation, this.mViewRotation, this.mSize, this.mFormat);
        return frame;
    }
    
    public <T> T getData() {
        this.ensureHasContent();
        return (T)this.mData;
    }
    
    public Class<?> getDataClass() {
        return this.mDataClass;
    }
    
    public int getFormat() {
        this.ensureHasContent();
        return this.mFormat;
    }
    
    @Deprecated
    public int getRotation() {
        return this.getRotationToUser();
    }
    
    public int getRotationToUser() {
        this.ensureHasContent();
        return this.mUserRotation;
    }
    
    public int getRotationToView() {
        this.ensureHasContent();
        return this.mViewRotation;
    }
    
    public Size getSize() {
        this.ensureHasContent();
        return this.mSize;
    }
    
    public long getTime() {
        this.ensureHasContent();
        return this.mTime;
    }
    
    public void release() {
        if (!this.hasContent()) {
            return;
        }
        Frame.LOG.v(new Object[] { "Frame with time", this.mTime, "is being released." });
        final Object mData = this.mData;
        this.mData = null;
        this.mUserRotation = 0;
        this.mViewRotation = 0;
        this.mTime = -1L;
        this.mSize = null;
        this.mFormat = -1;
        this.mManager.onFrameReleased(this, mData);
    }
    
    void setContent(final Object mData, final long n, final int mUserRotation, final int mViewRotation, final Size mSize, final int mFormat) {
        this.mData = mData;
        this.mTime = n;
        this.mLastTime = n;
        this.mUserRotation = mUserRotation;
        this.mViewRotation = mViewRotation;
        this.mSize = mSize;
        this.mFormat = mFormat;
    }
}
