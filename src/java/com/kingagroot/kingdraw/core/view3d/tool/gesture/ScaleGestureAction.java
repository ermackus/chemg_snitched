package com.kingagroot.kingdraw.core.view3d.tool.gesture;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import com.kingagroot.kingdraw.core.view3d.PaletteView;
import android.view.ScaleGestureDetector;

public class ScaleGestureAction extends BaseGestureAction
{
    private boolean isResponseEvent;
    private ScaleGestureDetector myDetector;
    private PaletteView view;
    
    public ScaleGestureAction(final PaletteView view) {
        this.view = view;
        this.myDetector = new ScaleGestureDetector(view.getContext(), (ScaleGestureDetector$OnScaleGestureListener)new ScaleGestureAction.ScaleGestureAction$MyScaleGestureListener(this, (ScaleGestureAction$1)null));
    }
    
    public void onTouchEvent(final MotionEvent motionEvent) {
        motionEvent.getPointerCount();
        if (motionEvent.getAction() == 0) {
            this.isResponseEvent = true;
            this.setInterceptEvent(false);
        }
        else {
            motionEvent.getAction();
        }
        if (motionEvent.getAction() != 0 && this.isResponseEvent) {
            this.myDetector.onTouchEvent(motionEvent);
        }
    }
}
