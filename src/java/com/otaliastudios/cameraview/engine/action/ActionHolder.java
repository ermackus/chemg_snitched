package com.otaliastudios.cameraview.engine.action;

import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CaptureRequest$Builder;

public interface ActionHolder
{
    void addAction(final Action p0);
    
    void applyBuilder(final Action p0);
    
    void applyBuilder(final Action p0, final CaptureRequest$Builder p1) throws CameraAccessException;
    
    CaptureRequest$Builder getBuilder(final Action p0);
    
    CameraCharacteristics getCharacteristics(final Action p0);
    
    TotalCaptureResult getLastResult(final Action p0);
    
    void removeAction(final Action p0);
}
