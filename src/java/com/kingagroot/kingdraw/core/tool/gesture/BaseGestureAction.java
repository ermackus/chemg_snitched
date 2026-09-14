package com.kingagroot.kingdraw.core.tool.gesture;

import android.view.MotionEvent;

public abstract class BaseGestureAction
{
    protected int MotionActon;
    private boolean isInterceptEvent;
    
    public BaseGestureAction() {
        this.MotionActon = 0;
    }
    
    public boolean isInterceptEvent() {
        return this.isInterceptEvent;
    }
    
    public void onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.MotionActon = 0;
        }
        else if (motionEvent.getAction() == 2) {
            this.MotionActon = 2;
        }
        else if (motionEvent.getAction() == 1) {
            this.MotionActon = 1;
        }
    }
    
    protected void setInterceptEvent(final boolean isInterceptEvent) {
        this.isInterceptEvent = isInterceptEvent;
    }
}
