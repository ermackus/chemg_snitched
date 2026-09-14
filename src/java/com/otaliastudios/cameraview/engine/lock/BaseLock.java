package com.otaliastudios.cameraview.engine.lock;

import com.otaliastudios.cameraview.engine.action.ActionHolder;
import com.otaliastudios.cameraview.engine.action.BaseAction;

public abstract class BaseLock extends BaseAction
{
    protected abstract boolean checkIsSupported(final ActionHolder p0);
    
    protected abstract boolean checkShouldSkip(final ActionHolder p0);
    
    protected final void onStart(final ActionHolder actionHolder) {
        super.onStart(actionHolder);
        final boolean checkShouldSkip = this.checkShouldSkip(actionHolder);
        if (this.checkIsSupported(actionHolder) && !checkShouldSkip) {
            this.onStarted(actionHolder);
        }
        else {
            this.setState(Integer.MAX_VALUE);
        }
    }
    
    protected abstract void onStarted(final ActionHolder p0);
}
