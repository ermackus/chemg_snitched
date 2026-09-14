package com.otaliastudios.cameraview.engine.action;

import java.util.Iterator;
import android.hardware.camera2.CameraCharacteristics$Key;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAction implements Action
{
    private final List<ActionCallback> callbacks;
    private ActionHolder holder;
    private boolean needsOnStart;
    private int state;
    
    public BaseAction() {
        this.callbacks = (List<ActionCallback>)new ArrayList();
    }
    
    public final void abort(final ActionHolder actionHolder) {
        actionHolder.removeAction((Action)this);
        if (!this.isCompleted()) {
            this.onAbort(actionHolder);
            this.setState(Integer.MAX_VALUE);
        }
        this.needsOnStart = false;
    }
    
    public void addCallback(final ActionCallback actionCallback) {
        if (!this.callbacks.contains((Object)actionCallback)) {
            this.callbacks.add((Object)actionCallback);
            actionCallback.onActionStateChanged((Action)this, this.getState());
        }
    }
    
    protected ActionHolder getHolder() {
        return this.holder;
    }
    
    public final int getState() {
        return this.state;
    }
    
    public boolean isCompleted() {
        return this.state == Integer.MAX_VALUE;
    }
    
    protected void onAbort(final ActionHolder actionHolder) {
    }
    
    public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
    }
    
    public void onCaptureProgressed(final ActionHolder actionHolder, final CaptureRequest captureRequest, final CaptureResult captureResult) {
    }
    
    public void onCaptureStarted(final ActionHolder actionHolder, final CaptureRequest captureRequest) {
        if (this.needsOnStart) {
            this.onStart(actionHolder);
            this.needsOnStart = false;
        }
    }
    
    protected void onCompleted(final ActionHolder actionHolder) {
    }
    
    protected void onStart(final ActionHolder holder) {
        this.holder = holder;
    }
    
    protected <T> T readCharacteristic(final CameraCharacteristics$Key<T> cameraCharacteristics$Key, final T t) {
        Object value = this.holder.getCharacteristics((Action)this).get((CameraCharacteristics$Key)cameraCharacteristics$Key);
        if (value == null) {
            value = t;
        }
        return (T)value;
    }
    
    public void removeCallback(final ActionCallback actionCallback) {
        this.callbacks.remove((Object)actionCallback);
    }
    
    protected final void setState(final int state) {
        if (state != this.state) {
            this.state = state;
            final Iterator iterator = this.callbacks.iterator();
            while (iterator.hasNext()) {
                ((ActionCallback)iterator.next()).onActionStateChanged((Action)this, this.state);
            }
            if (this.state == Integer.MAX_VALUE) {
                this.holder.removeAction((Action)this);
                this.onCompleted(this.holder);
            }
        }
    }
    
    public final void start(final ActionHolder holder) {
        (this.holder = holder).addAction((Action)this);
        if (holder.getLastResult((Action)this) != null) {
            this.onStart(holder);
        }
        else {
            this.needsOnStart = true;
        }
    }
}
