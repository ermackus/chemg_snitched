package com.otaliastudios.cameraview;

import android.graphics.PointF;

public abstract class CameraListener
{
    public void onAutoFocusEnd(final boolean b, final PointF pointF) {
    }
    
    public void onAutoFocusStart(final PointF pointF) {
    }
    
    public void onCameraClosed() {
    }
    
    public void onCameraError(final CameraException ex) {
    }
    
    public void onCameraOpened(final CameraOptions cameraOptions) {
    }
    
    public void onExposureCorrectionChanged(final float n, final float[] array, final PointF[] array2) {
    }
    
    public void onOrientationChanged(final int n) {
    }
    
    public void onPictureShutter() {
    }
    
    public void onPictureTaken(final PictureResult pictureResult) {
    }
    
    public void onVideoRecordingEnd() {
    }
    
    public void onVideoRecordingStart() {
    }
    
    public void onVideoTaken(final VideoResult videoResult) {
    }
    
    public void onZoomChanged(final float n, final float[] array, final PointF[] array2) {
    }
}
