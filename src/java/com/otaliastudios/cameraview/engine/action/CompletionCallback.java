package com.otaliastudios.cameraview.engine.action;

public abstract class CompletionCallback implements ActionCallback
{
    protected abstract void onActionCompleted(final Action p0);
    
    public final void onActionStateChanged(final Action action, final int n) {
        if (n == Integer.MAX_VALUE) {
            this.onActionCompleted(action);
        }
    }
}
