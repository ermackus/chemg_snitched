package com.kingagroot.kingdraw.core.view3d.tool.gesture;

import android.view.MotionEvent;
import android.view.GestureDetector$OnGestureListener;
import com.kingagroot.kingdraw.core.view3d.PaletteView;
import android.view.GestureDetector;

public class DoubleTapGestureAction extends BaseGestureAction
{
    private boolean isResponseEvent;
    private GestureDetector myDetector;
    private PaletteView view;
    
    public DoubleTapGestureAction(final PaletteView view) {
        this.view = view;
        this.myDetector = new GestureDetector(view.getContext(), (GestureDetector$OnGestureListener)new DoubleTapGestureAction.DoubleTapGestureAction$MyGestureListener(this, (DoubleTapGestureAction$1)null));
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
