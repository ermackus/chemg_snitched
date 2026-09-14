package com.king.zxing.config;

import androidx.camera.core.Preview;
import androidx.camera.core.Preview$Builder;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysis$Builder;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraSelector$Builder;

public class CameraConfig
{
    public CameraSelector options(final CameraSelector$Builder cameraSelector$Builder) {
        return cameraSelector$Builder.build();
    }
    
    public ImageAnalysis options(final ImageAnalysis$Builder imageAnalysis$Builder) {
        return imageAnalysis$Builder.build();
    }
    
    public Preview options(final Preview$Builder preview$Builder) {
        return preview$Builder.build();
    }
}
