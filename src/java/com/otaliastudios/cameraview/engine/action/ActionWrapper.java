package com.otaliastudios.cameraview.engine.action;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;

public abstract class ActionWrapper extends BaseAction
{
    public abstract BaseAction getAction();
    
    protected void onAbort(final ActionHolder actionHolder) {
        super.onAbort(actionHolder);
        this.getAction().onAbort(actionHolder);
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        this.getAction().onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
    }
    
    public void onCaptureProgressed(final ActionHolder actionHolder, final CaptureRequest captureRequest, final CaptureResult captureResult) {
        super.onCaptureProgressed(actionHolder, captureRequest, captureResult);
        this.getAction().onCaptureProgressed(actionHolder, captureRequest, captureResult);
    }
    
    public void onCaptureStarted(final ActionHolder actionHolder, final CaptureRequest captureRequest) {
        super.onCaptureStarted(actionHolder, captureRequest);
        this.getAction().onCaptureStarted(actionHolder, captureRequest);
    }
    
    protected void onStart(final ActionHolder actionHolder) {
        super.onStart(actionHolder);
        this.getAction().addCallback((ActionCallback)new ActionWrapper$1(this));
        this.getAction().onStart(actionHolder);
    }
}
