package com.otaliastudios.cameraview.engine.action;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;

public interface Action
{
    public static final int STATE_COMPLETED = Integer.MAX_VALUE;
    
    void abort(final ActionHolder p0);
    
    void addCallback(final ActionCallback p0);
    
    int getState();
    
    void onCaptureCompleted(final ActionHolder p0, final CaptureRequest p1, final TotalCaptureResult p2);
    
    void onCaptureProgressed(final ActionHolder p0, final CaptureRequest p1, final CaptureResult p2);
    
    void onCaptureStarted(final ActionHolder p0, final CaptureRequest p1);
    
    void removeCallback(final ActionCallback p0);
    
    void start(final ActionHolder p0);
}
