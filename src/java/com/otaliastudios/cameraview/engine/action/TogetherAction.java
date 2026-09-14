package com.otaliastudios.cameraview.engine.action;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

class TogetherAction extends BaseAction
{
    private final List<BaseAction> actions;
    private final List<BaseAction> runningActions;
    
    TogetherAction(final List<BaseAction> list) {
        this.actions = (List<BaseAction>)new ArrayList((Collection)list);
        this.runningActions = (List<BaseAction>)new ArrayList((Collection)list);
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            ((BaseAction)iterator.next()).addCallback((ActionCallback)new TogetherAction$1(this));
        }
    }
    
    protected void onAbort(final ActionHolder actionHolder) {
        super.onAbort(actionHolder);
        for (final BaseAction baseAction : this.actions) {
            if (!baseAction.isCompleted()) {
                baseAction.onAbort(actionHolder);
            }
        }
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
        super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
        for (final BaseAction baseAction : this.actions) {
            if (!baseAction.isCompleted()) {
                baseAction.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
            }
        }
    }
    
    public void onCaptureProgressed(final ActionHolder actionHolder, final CaptureRequest captureRequest, final CaptureResult captureResult) {
        super.onCaptureProgressed(actionHolder, captureRequest, captureResult);
        for (final BaseAction baseAction : this.actions) {
            if (!baseAction.isCompleted()) {
                baseAction.onCaptureProgressed(actionHolder, captureRequest, captureResult);
            }
        }
    }
    
    public void onCaptureStarted(final ActionHolder actionHolder, final CaptureRequest captureRequest) {
        super.onCaptureStarted(actionHolder, captureRequest);
        for (final BaseAction baseAction : this.actions) {
            if (!baseAction.isCompleted()) {
                baseAction.onCaptureStarted(actionHolder, captureRequest);
            }
        }
    }
    
    protected void onStart(final ActionHolder actionHolder) {
        super.onStart(actionHolder);
        for (final BaseAction baseAction : this.actions) {
            if (!baseAction.isCompleted()) {
                baseAction.onStart(actionHolder);
            }
        }
    }
}
