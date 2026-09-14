package com.king.zxing;

import androidx.camera.core.Camera;

public interface ICamera
{
    Camera getCamera();
    
    void release();
    
    void startCamera();
    
    void stopCamera();
}
