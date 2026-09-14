package com.kingagroot.kingdraw.widget.photoPicker.photo;

import android.hardware.Camera$Parameters;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Camera$CameraInfo;
import android.hardware.Camera;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import android.hardware.Camera$Size;
import java.util.List;
import android.view.Display;
import android.view.WindowManager;
import android.content.Context;

public class CameraParams
{
    private static volatile CameraParams cameraParams;
    public int oritation;
    private double screenRatio;
    
    private CameraParams() {
    }
    
    private void GetCameraPhotoRatio(final Context context) {
        final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        final int width = defaultDisplay.getWidth();
        final int height = defaultDisplay.getHeight();
        if (width < height) {
            this.screenRatio = height / (float)width;
        }
        else {
            this.screenRatio = width / (float)height;
        }
    }
    
    public static CameraParams getInstance() {
        if (CameraParams.cameraParams == null) {
            synchronized (CameraParams.class) {
                if (CameraParams.cameraParams == null) {
                    CameraParams.cameraParams = new CameraParams();
                }
            }
        }
        return CameraParams.cameraParams;
    }
    
    private Camera$Size getProperSize(final List<Camera$Size> list) {
        Collections.sort((List)list, (Comparator)new CameraSizeComparator());
        for (final Camera$Size camera$Size : list) {
            final float n = camera$Size.width / (float)camera$Size.height;
            if (camera$Size.width > 640 && Math.abs(n - this.screenRatio) <= 0.03) {
                return camera$Size;
            }
        }
        return null;
    }
    
    private void setOrientation(final Context context, final Camera camera, int oritation) {
        final Camera$CameraInfo camera$CameraInfo = new Camera$CameraInfo();
        Camera.getCameraInfo(oritation, camera$CameraInfo);
        final int rotation = ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getRotation();
        final int n = oritation = 0;
        if (rotation != 0) {
            if (rotation != 1) {
                if (rotation != 2) {
                    if (rotation != 3) {
                        oritation = n;
                    }
                    else {
                        oritation = 270;
                    }
                }
                else {
                    oritation = 180;
                }
            }
            else {
                oritation = 90;
            }
        }
        if (camera$CameraInfo.facing == 1) {
            oritation = (360 - (camera$CameraInfo.orientation + oritation) % 360) % 360;
        }
        else {
            oritation = (camera$CameraInfo.orientation - oritation + 360) % 360;
        }
        camera.setDisplayOrientation(this.oritation = oritation);
    }
    
    public void setCameraParams(final Context context, final Camera camera, final int n) {
        this.GetCameraPhotoRatio(context);
        final Camera$Parameters parameters = camera.getParameters();
        final Camera$Size properSize = this.getProperSize((List<Camera$Size>)parameters.getSupportedPictureSizes());
        if (properSize != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("picSize.width==================");
            sb.append(properSize.width);
            sb.append("  picSize.height=");
            sb.append(properSize.height);
            Log.e("params", sb.toString());
            parameters.setPictureSize(properSize.width, properSize.height);
        }
        final Camera$Size properSize2 = this.getProperSize((List<Camera$Size>)parameters.getSupportedPreviewSizes());
        if (properSize2 != null) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("preSize.width==================");
            sb2.append(properSize2.width);
            sb2.append("  preSize.height=");
            sb2.append(properSize2.height);
            Log.e("params", sb2.toString());
            parameters.setPreviewSize(properSize2.width, properSize2.height);
        }
        if (parameters.getSupportedFocusModes().contains((Object)"continuous-picture")) {
            parameters.setFocusMode("continuous-picture");
        }
        parameters.setPictureFormat(256);
        parameters.setJpegQuality(100);
        parameters.set("orientation", "portrait");
        this.setOrientation(context, camera, n);
        camera.setParameters(parameters);
    }
    
    private class CameraSizeComparator implements Comparator<Camera$Size>
    {
        final CameraParams this$0;
        
        private CameraSizeComparator(final CameraParams this$0) {
            this.this$0 = this$0;
        }
        
        public int compare(final Camera$Size camera$Size, final Camera$Size camera$Size2) {
            return Integer.valueOf(camera$Size2.width).compareTo(Integer.valueOf(camera$Size.width));
        }
    }
}
