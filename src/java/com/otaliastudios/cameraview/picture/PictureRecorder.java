package com.otaliastudios.cameraview.picture;

import com.otaliastudios.cameraview.PictureResult;

public abstract class PictureRecorder
{
    protected Exception mError;
    PictureResultListener mListener;
    PictureResult.Stub mResult;
    
    public PictureRecorder(final PictureResult.Stub mResult, final PictureResultListener mListener) {
        this.mResult = mResult;
        this.mListener = mListener;
    }
    
    protected void dispatchOnShutter(final boolean b) {
        final PictureResultListener mListener = this.mListener;
        if (mListener != null) {
            mListener.onPictureShutter(b);
        }
    }
    
    protected void dispatchResult() {
        final PictureResultListener mListener = this.mListener;
        if (mListener != null) {
            mListener.onPictureResult(this.mResult, this.mError);
            this.mListener = null;
            this.mResult = null;
        }
    }
    
    public abstract void take();
    
    public interface PictureResultListener
    {
        void onPictureResult(final PictureResult.Stub p0, final Exception p1);
        
        void onPictureShutter(final boolean p0);
    }
}
