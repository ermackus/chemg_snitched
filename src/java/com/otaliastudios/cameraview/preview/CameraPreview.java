package com.otaliastudios.cameraview.preview;

import android.view.ViewParent;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.tasks.TaskCompletionSource;
import android.os.Handler;
import android.os.Looper;
import com.otaliastudios.cameraview.size.Size;
import android.view.ViewGroup;
import android.content.Context;
import com.otaliastudios.cameraview.CameraLogger;
import android.view.View;

public abstract class CameraPreview<T extends View, Output>
{
    protected static final CameraLogger LOG;
    CropCallback mCropCallback;
    protected boolean mCropping;
    protected int mDrawRotation;
    protected int mInputStreamHeight;
    protected int mInputStreamWidth;
    protected int mOutputSurfaceHeight;
    protected int mOutputSurfaceWidth;
    private SurfaceCallback mSurfaceCallback;
    private T mView;
    
    static {
        LOG = CameraLogger.create(CameraPreview.class.getSimpleName());
    }
    
    public CameraPreview(final Context context, final ViewGroup viewGroup) {
        this.mView = this.onCreateView(context, viewGroup);
    }
    
    protected void crop(final CropCallback cropCallback) {
        if (cropCallback != null) {
            cropCallback.onCrop();
        }
    }
    
    protected final void dispatchOnSurfaceAvailable(final int mOutputSurfaceWidth, final int mOutputSurfaceHeight) {
        CameraPreview.LOG.i(new Object[] { "dispatchOnSurfaceAvailable:", "w=", mOutputSurfaceWidth, "h=", mOutputSurfaceHeight });
        this.mOutputSurfaceWidth = mOutputSurfaceWidth;
        this.mOutputSurfaceHeight = mOutputSurfaceHeight;
        if (mOutputSurfaceWidth > 0 && mOutputSurfaceHeight > 0) {
            this.crop(this.mCropCallback);
        }
        final SurfaceCallback mSurfaceCallback = this.mSurfaceCallback;
        if (mSurfaceCallback != null) {
            mSurfaceCallback.onSurfaceAvailable();
        }
    }
    
    protected final void dispatchOnSurfaceDestroyed() {
        this.mOutputSurfaceWidth = 0;
        this.mOutputSurfaceHeight = 0;
        final SurfaceCallback mSurfaceCallback = this.mSurfaceCallback;
        if (mSurfaceCallback != null) {
            mSurfaceCallback.onSurfaceDestroyed();
        }
    }
    
    protected final void dispatchOnSurfaceSizeChanged(final int mOutputSurfaceWidth, final int mOutputSurfaceHeight) {
        CameraPreview.LOG.i(new Object[] { "dispatchOnSurfaceSizeChanged:", "w=", mOutputSurfaceWidth, "h=", mOutputSurfaceHeight });
        if (mOutputSurfaceWidth != this.mOutputSurfaceWidth || mOutputSurfaceHeight != this.mOutputSurfaceHeight) {
            this.mOutputSurfaceWidth = mOutputSurfaceWidth;
            this.mOutputSurfaceHeight = mOutputSurfaceHeight;
            if (mOutputSurfaceWidth > 0 && mOutputSurfaceHeight > 0) {
                this.crop(this.mCropCallback);
            }
            final SurfaceCallback mSurfaceCallback = this.mSurfaceCallback;
            if (mSurfaceCallback != null) {
                mSurfaceCallback.onSurfaceChanged();
            }
        }
    }
    
    public abstract Output getOutput();
    
    public abstract Class<Output> getOutputClass();
    
    public abstract View getRootView();
    
    final Size getStreamSize() {
        return new Size(this.mInputStreamWidth, this.mInputStreamHeight);
    }
    
    public final Size getSurfaceSize() {
        return new Size(this.mOutputSurfaceWidth, this.mOutputSurfaceHeight);
    }
    
    public final T getView() {
        return this.mView;
    }
    
    public final boolean hasSurface() {
        return this.mOutputSurfaceWidth > 0 && this.mOutputSurfaceHeight > 0;
    }
    
    public boolean isCropping() {
        return this.mCropping;
    }
    
    protected abstract T onCreateView(final Context p0, final ViewGroup p1);
    
    public void onDestroy() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            this.onDestroyView();
            return;
        }
        final Handler handler = new Handler(Looper.getMainLooper());
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        handler.post((Runnable)new Runnable(this, taskCompletionSource) {
            final CameraPreview this$0;
            final TaskCompletionSource val$task;
            
            public void run() {
                this.this$0.onDestroyView();
                this.val$task.setResult((Object)null);
            }
        });
        try {
            Tasks.await(taskCompletionSource.getTask());
        }
        catch (final Exception ex) {}
    }
    
    protected void onDestroyView() {
        final View rootView = this.getRootView();
        final ViewParent parent = rootView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup)parent).removeView(rootView);
        }
    }
    
    public void onPause() {
    }
    
    public void onResume() {
    }
    
    public void setDrawRotation(final int mDrawRotation) {
        this.mDrawRotation = mDrawRotation;
    }
    
    public void setStreamSize(final int mInputStreamWidth, final int mInputStreamHeight) {
        CameraPreview.LOG.i(new Object[] { "setStreamSize:", "desiredW=", mInputStreamWidth, "desiredH=", mInputStreamHeight });
        this.mInputStreamWidth = mInputStreamWidth;
        this.mInputStreamHeight = mInputStreamHeight;
        if (mInputStreamWidth > 0 && mInputStreamHeight > 0) {
            this.crop(this.mCropCallback);
        }
    }
    
    public void setSurfaceCallback(SurfaceCallback mSurfaceCallback) {
        if (this.hasSurface()) {
            final SurfaceCallback mSurfaceCallback2 = this.mSurfaceCallback;
            if (mSurfaceCallback2 != null) {
                mSurfaceCallback2.onSurfaceDestroyed();
            }
        }
        this.mSurfaceCallback = mSurfaceCallback;
        if (this.hasSurface()) {
            mSurfaceCallback = this.mSurfaceCallback;
            if (mSurfaceCallback != null) {
                mSurfaceCallback.onSurfaceAvailable();
            }
        }
    }
    
    public boolean supportsCropping() {
        return false;
    }
    
    protected interface CropCallback
    {
        void onCrop();
    }
    
    public interface SurfaceCallback
    {
        void onSurfaceAvailable();
        
        void onSurfaceChanged();
        
        void onSurfaceDestroyed();
    }
}
