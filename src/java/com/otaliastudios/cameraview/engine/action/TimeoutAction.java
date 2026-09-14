package com.otaliastudios.cameraview.engine.action;

import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;

class TimeoutAction extends ActionWrapper
{
    private BaseAction action;
    private long startMillis;
    private long timeoutMillis;
    
    TimeoutAction(final long timeoutMillis, final BaseAction action) {
        this.timeoutMillis = timeoutMillis;
        this.action = action;
    }
    
    @Override
    public BaseAction getAction() {
        return this.action;
    }
    
    @Override
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        if (!this.isCompleted() && System.currentTimeMillis() > this.startMillis + this.timeoutMillis) {
            this.getAction().abort(actionHolder);
        }
    }
    
    @Override
    protected void onStart(final ActionHolder actionHolder) {
        this.startMillis = System.currentTimeMillis();
        super.onStart(actionHolder);
    }
}
