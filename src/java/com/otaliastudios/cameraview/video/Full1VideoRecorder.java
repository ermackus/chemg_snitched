package com.otaliastudios.cameraview.video;

import android.hardware.Camera$PreviewCallback;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.internal.CamcorderProfiles;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import com.otaliastudios.cameraview.VideoResult$Stub;
import com.otaliastudios.cameraview.engine.Camera1Engine;
import android.hardware.Camera;

public class Full1VideoRecorder extends FullVideoRecorder
{
    private final Camera mCamera;
    private final int mCameraId;
    private final Camera1Engine mEngine;
    
    public Full1VideoRecorder(final Camera1Engine mEngine, final Camera mCamera, final int mCameraId) {
        super((VideoRecorder$VideoResultListener)mEngine);
        this.mCamera = mCamera;
        this.mEngine = mEngine;
        this.mCameraId = mCameraId;
    }
    
    protected void applyVideoSource(final VideoResult$Stub videoResult$Stub, final MediaRecorder mediaRecorder) {
        mediaRecorder.setCamera(this.mCamera);
        mediaRecorder.setVideoSource(1);
    }
    
    protected CamcorderProfile getCamcorderProfile(final VideoResult$Stub videoResult$Stub) {
        final int rotation = videoResult$Stub.rotation;
        Size size = videoResult$Stub.size;
        if (rotation % 180 != 0) {
            size = size.flip();
        }
        return CamcorderProfiles.get(this.mCameraId, size);
    }
    
    protected void onDispatchResult() {
        this.mCamera.setPreviewCallbackWithBuffer((Camera$PreviewCallback)this.mEngine);
        super.onDispatchResult();
    }
}
