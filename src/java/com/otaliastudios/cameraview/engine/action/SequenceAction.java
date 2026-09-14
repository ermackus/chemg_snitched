package com.otaliastudios.cameraview.engine.action;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;
import java.util.List;

class SequenceAction extends BaseAction
{
    private final List<BaseAction> actions;
    private int runningAction;
    
    SequenceAction(final List<BaseAction> actions) {
        this.runningAction = -1;
        this.actions = actions;
        this.increaseRunningAction();
    }
    
    private void increaseRunningAction() {
        final int runningAction = this.runningAction;
        boolean b = false;
        final boolean b2 = runningAction == -1;
        if (this.runningAction == this.actions.size() - 1) {
            b = true;
        }
        if (b) {
            this.setState(Integer.MAX_VALUE);
        }
        else {
            final int runningAction2 = this.runningAction + 1;
            this.runningAction = runningAction2;
            ((BaseAction)this.actions.get(runningAction2)).addCallback((ActionCallback)new SequenceAction$1(this));
            if (!b2) {
                ((BaseAction)this.actions.get(this.runningAction)).onStart(this.getHolder());
            }
        }
    }
    
    protected void onAbort(final ActionHolder actionHolder) {
        super.onAbort(actionHolder);
        final int runningAction = this.runningAction;
        if (runningAction >= 0) {
            ((BaseAction)this.actions.get(runningAction)).onAbort(actionHolder);
        }
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        final int runningAction = this.runningAction;
        if (runningAction >= 0) {
            ((BaseAction)this.actions.get(runningAction)).onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        }
    }
    
    public void onCaptureProgressed(final ActionHolder actionHolder, final CaptureRequest captureRequest, final CaptureResult captureResult) {
        super.onCaptureProgressed(actionHolder, captureRequest, captureResult);
        final int runningAction = this.runningAction;
        if (runningAction >= 0) {
            ((BaseAction)this.actions.get(runningAction)).onCaptureProgressed(actionHolder, captureRequest, captureResult);
        }
    }
    
    public void onCaptureStarted(final ActionHolder actionHolder, final CaptureRequest captureRequest) {
        super.onCaptureStarted(actionHolder, captureRequest);
        final int runningAction = this.runningAction;
        if (runningAction >= 0) {
            ((BaseAction)this.actions.get(runningAction)).onCaptureStarted(actionHolder, captureRequest);
        }
    }
    
    protected void onStart(final ActionHolder actionHolder) {
        super.onStart(actionHolder);
        final int runningAction = this.runningAction;
        if (runningAction >= 0) {
            ((BaseAction)this.actions.get(runningAction)).onStart(actionHolder);
        }
    }
}
