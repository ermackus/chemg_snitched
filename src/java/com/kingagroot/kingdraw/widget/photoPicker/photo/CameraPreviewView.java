package com.kingagroot.kingdraw.widget.photoPicker.photo;

import android.hardware.Camera$PictureCallback;
import android.hardware.Camera$ShutterCallback;
import android.hardware.Camera$Parameters;
import java.io.IOException;
import android.hardware.Camera$AutoFocusCallback;
import android.view.SurfaceHolder$Callback;
import android.util.AttributeSet;
import android.content.Context;
import android.view.SurfaceHolder;
import android.hardware.Camera;
import android.view.SurfaceView;

public class CameraPreviewView extends SurfaceView
{
    private Camera camera;
    private final SurfaceHolder holder;
    private ICameraCall listener;
    
    public CameraPreviewView(final Context context, final AttributeSet set) {
        super(context, set);
        (this.holder = this.getHolder()).addCallback((SurfaceHolder$Callback)new SurfaceCallback());
        this.holder.setKeepScreenOn(true);
        this.holder.setType(3);
    }
    
    public void autoFocus() {
        this.camera.autoFocus((Camera$AutoFocusCallback)null);
    }
    
    public void reStartCamera() {
        final Camera camera = this.camera;
        if (camera != null) {
            camera.stopPreview();
            this.camera.release();
            this.camera = null;
        }
        try {
            (this.camera = Camera.open(0)).setPreviewDisplay(this.holder);
            this.camera.startPreview();
            CameraParams.getInstance().setCameraParams(this.getContext(), this.camera, 0);
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setIsOpenFlashMode(final String flashMode) {
        final Camera$Parameters parameters = this.camera.getParameters();
        parameters.setFlashMode(flashMode);
        this.camera.setParameters(parameters);
    }
    
    public void setOnCameraListener(final ICameraCall listener) {
        this.listener = listener;
    }
    
    public void start() {
        final Camera camera = this.camera;
        if (camera != null) {
            camera.startPreview();
        }
    }
    
    public void takePicture() {
        final Camera camera = this.camera;
        if (camera != null) {
            try {
                camera.takePicture((Camera$ShutterCallback)null, (Camera$PictureCallback)null, (Camera$PictureCallback)new MyPictureCallback());
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void zoomIn() {
        final Camera$Parameters parameters = this.camera.getParameters();
        if (!parameters.isZoomSupported()) {
            return;
        }
        final int zoom = parameters.getZoom() - 5;
        if (zoom >= 0) {
            parameters.setZoom(zoom);
            this.camera.setParameters(parameters);
        }
    }
    
    public void zoomOut() {
        final Camera$Parameters parameters = this.camera.getParameters();
        if (!parameters.isZoomSupported()) {
            return;
        }
        final int zoom = parameters.getZoom() + 5;
        if (zoom < parameters.getMaxZoom()) {
            parameters.setZoom(zoom);
            this.camera.setParameters(parameters);
        }
    }
    
    private class MyPictureCallback implements Camera$PictureCallback
    {
        final CameraPreviewView this$0;
        
        private MyPictureCallback(final CameraPreviewView this$0) {
            this.this$0 = this$0;
        }
        
        public void onPictureTaken(final byte[] array, final Camera camera) {
            camera.stopPreview();
            if (this.this$0.listener != null) {
                this.this$0.listener.onCameraData(array);
            }
        }
    }
    
    private class SurfaceCallback implements SurfaceHolder$Callback
    {
        final CameraPreviewView this$0;
        
        private SurfaceCallback(final CameraPreviewView this$0) {
            this.this$0 = this$0;
        }
        
        public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
        }
        
        public void surfaceCreated(final SurfaceHolder surfaceHolder) {
            this.this$0.reStartCamera();
        }
        
        public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
            if (this.this$0.camera != null) {
                this.this$0.camera.release();
                this.this$0.camera = null;
            }
        }
    }
}
