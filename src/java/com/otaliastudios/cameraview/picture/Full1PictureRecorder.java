package com.otaliastudios.cameraview.picture;

import android.hardware.Camera$PictureCallback;
import android.hardware.Camera$ShutterCallback;
import android.hardware.Camera$PreviewCallback;
import android.hardware.Camera$Parameters;
import com.otaliastudios.cameraview.PictureResult$Stub;
import com.otaliastudios.cameraview.engine.Camera1Engine;
import android.hardware.Camera;

public class Full1PictureRecorder extends FullPictureRecorder
{
    private final Camera mCamera;
    private final Camera1Engine mEngine;
    
    public Full1PictureRecorder(final PictureResult$Stub pictureResult$Stub, final Camera1Engine mEngine, final Camera mCamera) {
        super(pictureResult$Stub, (PictureRecorder$PictureResultListener)mEngine);
        this.mEngine = mEngine;
        this.mCamera = mCamera;
        final Camera$Parameters parameters = mCamera.getParameters();
        parameters.setRotation(this.mResult.rotation);
        this.mCamera.setParameters(parameters);
    }
    
    protected void dispatchResult() {
        Full1PictureRecorder.LOG.i(new Object[] { "dispatching result. Thread:", Thread.currentThread() });
        super.dispatchResult();
    }
    
    public void take() {
        Full1PictureRecorder.LOG.i(new Object[] { "take() called." });
        this.mCamera.setPreviewCallbackWithBuffer((Camera$PreviewCallback)null);
        this.mEngine.getFrameManager().release();
        try {
            this.mCamera.takePicture((Camera$ShutterCallback)new Full1PictureRecorder$1(this), (Camera$PictureCallback)null, (Camera$PictureCallback)null, (Camera$PictureCallback)new Full1PictureRecorder$2(this));
            Full1PictureRecorder.LOG.i(new Object[] { "take() returned." });
        }
        catch (final Exception mError) {
            this.mError = mError;
            this.dispatchResult();
        }
    }
}
