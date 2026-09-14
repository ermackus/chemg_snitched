package com.kingagroot.kingdraw.core.tool.gesture;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import android.view.ScaleGestureDetector;
import com.kingagroot.kingdraw.core.view.KingDrawView;

public class ScaleGestureAction extends BaseGestureAction
{
    private boolean isResponseEvent;
    private KingDrawView kingDrawView;
    private ScaleGestureDetector myDetector;
    
    public ScaleGestureAction(final KingDrawView kingDrawView) {
        this.kingDrawView = kingDrawView;
        this.myDetector = new ScaleGestureDetector(this.kingDrawView.getContext(), (ScaleGestureDetector$OnScaleGestureListener)new ScaleGestureAction.ScaleGestureAction$MyScaleGestureListener(this, (ScaleGestureAction$1)null));
    }
    
    public void onTouchEvent(final MotionEvent motionEvent) {
        motionEvent.getPointerCount();
        final int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.isResponseEvent = true;
            this.setInterceptEvent(false);
        }
        else if (actionMasked == 6) {
            this.isResponseEvent = false;
        }
        if (motionEvent.getAction() != 0 && this.isResponseEvent) {
            this.myDetector.onTouchEvent(motionEvent);
        }
    }
}
