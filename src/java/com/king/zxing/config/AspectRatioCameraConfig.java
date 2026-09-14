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

public final class AspectRatioCameraConfig extends CameraConfig
{
    private int mAspectRatio;
    
    public AspectRatioCameraConfig(final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.mAspectRatio = this.aspectRatio((float)displayMetrics.widthPixels, (float)displayMetrics.heightPixels);
        final StringBuilder sb = new StringBuilder();
        sb.append("aspectRatio:");
        sb.append(this.mAspectRatio);
        LogUtils.d(sb.toString());
    }
    
    private int aspectRatio(float n, final float n2) {
        n = Math.max(n, n2) / Math.min(n, n2);
        if (Math.abs(n - 1.3333334f) < Math.abs(n - 1.7777778f)) {
            return 0;
        }
        return 1;
    }
    
    public CameraSelector options(final CameraSelector$Builder cameraSelector$Builder) {
        return super.options(cameraSelector$Builder);
    }
    
    public ImageAnalysis options(final ImageAnalysis$Builder imageAnalysis$Builder) {
        imageAnalysis$Builder.setTargetAspectRatio(this.mAspectRatio);
        return super.options(imageAnalysis$Builder);
    }
    
    public Preview options(final Preview$Builder preview$Builder) {
        return super.options(preview$Builder);
    }
}
