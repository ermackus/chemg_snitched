package com.king.zxing.config;

import androidx.camera.core.Preview;
import androidx.camera.core.Preview$Builder;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysis$Builder;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraSelector$Builder;
import android.util.DisplayMetrics;
import com.king.zxing.util.LogUtils;
import android.content.Context;
import android.util.Size;

public class ResolutionCameraConfig extends CameraConfig
{
    private Size mTargetSize;
    
    public ResolutionCameraConfig(final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final int widthPixels = displayMetrics.widthPixels;
        final int heightPixels = displayMetrics.heightPixels;
        LogUtils.d(String.format("displayMetrics:%d x %d", new Object[] { widthPixels, heightPixels }));
        if (widthPixels < heightPixels) {
            final int min = Math.min(widthPixels, 1080);
            if (widthPixels / (float)heightPixels > 0.7) {
                this.mTargetSize = new Size(min, (int)(min / 3.0f * 4.0f));
            }
            else {
                this.mTargetSize = new Size(min, (int)(min / 9.0f * 16.0f));
            }
        }
        else {
            final int min2 = Math.min(heightPixels, 1080);
            if (heightPixels / (float)widthPixels > 0.7) {
                this.mTargetSize = new Size((int)(min2 / 3.0f * 4.0f), min2);
            }
            else {
                this.mTargetSize = new Size((int)(min2 / 9.0f * 16.0), min2);
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("targetSize:");
        sb.append((Object)this.mTargetSize);
        LogUtils.d(sb.toString());
    }
    
    public CameraSelector options(final CameraSelector$Builder cameraSelector$Builder) {
        return super.options(cameraSelector$Builder);
    }
    
    public ImageAnalysis options(final ImageAnalysis$Builder imageAnalysis$Builder) {
        imageAnalysis$Builder.setTargetResolution(this.mTargetSize);
        return super.options(imageAnalysis$Builder);
    }
    
    public Preview options(final Preview$Builder preview$Builder) {
        return super.options(preview$Builder);
    }
}
