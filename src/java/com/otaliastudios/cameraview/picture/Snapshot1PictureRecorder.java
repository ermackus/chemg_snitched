package com.otaliastudios.cameraview.picture;

import android.hardware.Camera$PreviewCallback;
import com.otaliastudios.cameraview.PictureResult$Stub;
import com.otaliastudios.cameraview.size.AspectRatio;
import com.otaliastudios.cameraview.engine.Camera1Engine;
import android.hardware.Camera;

public class Snapshot1PictureRecorder extends SnapshotPictureRecorder
{
    private Camera mCamera;
    private Camera1Engine mEngine1;
    private int mFormat;
    private AspectRatio mOutputRatio;
    
    public Snapshot1PictureRecorder(final PictureResult$Stub pictureResult$Stub, final Camera1Engine mEngine1, final Camera mCamera, final AspectRatio mOutputRatio) {
        super(pictureResult$Stub, (PictureRecorder$PictureResultListener)mEngine1);
        this.mEngine1 = mEngine1;
        this.mCamera = mCamera;
        this.mOutputRatio = mOutputRatio;
        this.mFormat = mCamera.getParameters().getPreviewFormat();
    }
    
    protected void dispatchResult() {
        this.mEngine1 = null;
        this.mCamera = null;
        this.mOutputRatio = null;
        this.mFormat = 0;
        super.dispatchResult();
    }
    
    public void take() {
        this.mCamera.setOneShotPreviewCallback((Camera$PreviewCallback)new Snapshot1PictureRecorder$1(this));
    }
}
