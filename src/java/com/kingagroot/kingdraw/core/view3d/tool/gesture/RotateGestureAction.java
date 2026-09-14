package com.kingagroot.kingdraw.core.view3d.tool.gesture;

import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.kingagroot.kingdraw.core.view3d.PaletteView;
import android.graphics.PointF;

public class RotateGestureAction extends BaseGestureAction
{
    private float TOUCHSLOP;
    private boolean isResponseEvent;
    private PointF touchDownPoint;
    private PaletteView view;
    
    public RotateGestureAction(final PaletteView view) {
        this.touchDownPoint = new PointF();
        this.view = view;
        this.TOUCHSLOP = (float)ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
    }
    
    public void onTouchEvent(final MotionEvent motionEvent) {
        final int pointerCount = motionEvent.getPointerCount();
        if (motionEvent.getAction() == 0) {
            this.isResponseEvent = true;
            this.setInterceptEvent(false);
            this.touchDownPoint.x = motionEvent.getX();
            this.touchDownPoint.y = motionEvent.getY();
        }
        else if (motionEvent.getAction() == 2) {
            if (pointerCount == 2) {
                this.isResponseEvent = false;
            }
            if (this.isResponseEvent && !this.isInterceptEvent()) {
                final float n = motionEvent.getX() - this.touchDownPoint.x;
                final float n2 = motionEvent.getY() - this.touchDownPoint.y;
                if (Math.sqrt((double)(n * n + n2 * n2)) > this.TOUCHSLOP) {
                    this.setInterceptEvent(true);
                }
            }
            if (this.isResponseEvent && this.isInterceptEvent()) {
                this.view.rotate((float)((this.touchDownPoint.y - motionEvent.getY()) * 3.141592653589793) / this.view.getHeight(), (float)((this.touchDownPoint.x - motionEvent.getX()) * 3.141592653589793) / this.view.getWidth(), 0.0f);
                this.touchDownPoint.x = motionEvent.getX();
                this.touchDownPoint.y = motionEvent.getY();
            }
        }
    }
}
