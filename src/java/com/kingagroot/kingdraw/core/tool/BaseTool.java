package com.kingagroot.kingdraw.core.tool;

import android.view.MotionEvent;

abstract class BaseTool
{
    public void onStart() {
    }
    
    public void onStop() {
    }
    
    public abstract boolean onTouchEvent(final MotionEvent p0);
}
