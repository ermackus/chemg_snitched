package com.kingagroot.kingdraw.core.view3d.tool.gesture;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import android.view.GestureDetector$OnGestureListener;
import android.view.ViewConfiguration;
import com.kingagroot.kingdraw.core.view3d.PaletteView;
import android.graphics.PointF;
import android.view.ScaleGestureDetector;
import android.view.GestureDetector;

public class TransGestureAction extends BaseGestureAction
{
    private static final float DOUBLEPOINTDISMIN = 200.0f;
    private float TOUCHSLOP;
    private boolean isResponseEvent;
    private boolean isScale;
    private GestureDetector myDetector;
    private ScaleGestureDetector scaleGestureDetector;
    private boolean startCheckTrans;
    private PointF touchDownPoint;
    private PaletteView view;
    
    public TransGestureAction(final PaletteView view) {
        this.touchDownPoint = new PointF();
        this.isScale = false;
        this.view = view;
        this.TOUCHSLOP = (float)ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        this.myDetector = new GestureDetector(view.getContext(), (GestureDetector$OnGestureListener)new TransGestureAction.TransGestureAction$MyGestureListener(this, (TransGestureAction$1)null));
        this.scaleGestureDetector = new ScaleGestureDetector(view.getContext(), (ScaleGestureDetector$OnScaleGestureListener)new TransGestureAction.TransGestureAction$MyScaleGestureListener(this, (TransGestureAction$1)null));
    }
    
    public void onTouchEvent(final MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        motionEvent.getPointerCount();
        if (motionEvent.getAction() == 0) {
            this.isResponseEvent = true;
            this.setInterceptEvent(false);
            this.touchDownPoint.x = motionEvent.getX();
            this.touchDownPoint.y = motionEvent.getY();
            this.startCheckTrans = false;
            this.isScale = false;
        }
        if (this.MotionActon == 2 && this.isResponseEvent) {
            if (!this.startCheckTrans) {
                final float n = motionEvent.getX() - this.touchDownPoint.x;
                final float n2 = motionEvent.getY() - this.touchDownPoint.y;
                if (Math.sqrt((double)(n * n + n2 * n2)) > this.TOUCHSLOP * 4.0f) {
                    this.startCheckTrans = true;
                }
            }
            this.scaleGestureDetector.onTouchEvent(motionEvent);
            this.myDetector.onTouchEvent(motionEvent);
        }
    }
}
