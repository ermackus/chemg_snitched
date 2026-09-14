package com.kingagroot.kingdraw.widget.photoPicker.photo;

import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector$OnScaleGestureListener;

public class ScaleGestureListener implements ScaleGestureDetector$OnScaleGestureListener
{
    private final CameraPreviewView camePreview;
    private float mScaleFactor;
    
    public ScaleGestureListener(final CameraPreviewView camePreview) {
        this.camePreview = camePreview;
    }
    
    public boolean onScale(final ScaleGestureDetector scaleGestureDetector) {
        if (scaleGestureDetector.getCurrentSpan() > this.mScaleFactor) {
            this.camePreview.zoomOut();
        }
        else {
            this.camePreview.zoomIn();
        }
        this.mScaleFactor = scaleGestureDetector.getCurrentSpan();
        return false;
    }
    
    public boolean onScaleBegin(final ScaleGestureDetector scaleGestureDetector) {
        this.mScaleFactor = scaleGestureDetector.getCurrentSpan();
        return true;
    }
    
    public void onScaleEnd(final ScaleGestureDetector scaleGestureDetector) {
        this.mScaleFactor = scaleGestureDetector.getCurrentSpan();
    }
}
