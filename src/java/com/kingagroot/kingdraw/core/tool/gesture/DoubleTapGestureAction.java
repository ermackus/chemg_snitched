package com.kingagroot.kingdraw.core.tool.gesture;

import android.view.MotionEvent;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector;
import com.kingagroot.kingdraw.core.view.KingDrawView;

public class DoubleTapGestureAction extends BaseGestureAction
{
    private boolean isResponseEvent;
    private KingDrawView kingDrawView;
    private GestureDetector myDetector;
    
    public DoubleTapGestureAction(final KingDrawView kingDrawView) {
        this.kingDrawView = kingDrawView;
        this.myDetector = new GestureDetector(kingDrawView.getContext(), (GestureDetector$OnGestureListener)new DoubleTapGestureAction.DoubleTapGestureAction$MyGestureListener(this, (DoubleTapGestureAction$1)null));
    }
    
    public void onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.isResponseEvent = true;
            if (motionEvent.getPointerCount() != 1) {
                this.isResponseEvent = false;
            }
            this.setInterceptEvent(false);
        }
        if (this.isResponseEvent) {
            this.myDetector.onTouchEvent(motionEvent);
        }
    }
}
