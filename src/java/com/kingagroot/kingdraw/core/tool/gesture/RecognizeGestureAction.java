package com.kingagroot.kingdraw.core.tool.gesture;

import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.graphics.PointF;
import com.kingagroot.kingdraw.core.view.KingDrawView;

public class RecognizeGestureAction extends BaseGestureAction
{
    private float TOUCHSLOP;
    private boolean isResponseEvent;
    private KingDrawView kingDrawView;
    private PointF touchDownPoint;
    
    public RecognizeGestureAction(final KingDrawView kingDrawView) {
        this.touchDownPoint = new PointF();
        this.kingDrawView = kingDrawView;
        this.TOUCHSLOP = (float)ViewConfiguration.get(kingDrawView.getContext()).getScaledTouchSlop();
    }
    
    public void onTouchEvent(final MotionEvent motionEvent) {
        final int pointerCount = motionEvent.getPointerCount();
        final int n = motionEvent.getAction() & 0xFF;
        if (n == 0) {
            this.isResponseEvent = true;
            this.setInterceptEvent(false);
            this.touchDownPoint.x = motionEvent.getX();
            this.touchDownPoint.y = motionEvent.getY();
        }
        else if (n == 5) {
            this.isResponseEvent = false;
        }
        else if (n == 2) {
            if (pointerCount >= 2) {
                this.isResponseEvent = false;
            }
            if (this.isResponseEvent && !this.isInterceptEvent()) {
                final float n2 = motionEvent.getX() - this.touchDownPoint.x;
                final float n3 = motionEvent.getY() - this.touchDownPoint.y;
                if (Math.sqrt((double)(n2 * n2 + n3 * n3)) >= this.TOUCHSLOP * 3.0f) {
                    this.setInterceptEvent(true);
                    final long currentTimeMillis = System.currentTimeMillis();
                    this.kingDrawView.onTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, this.touchDownPoint.x, this.touchDownPoint.y, 0));
                }
            }
            if (this.isResponseEvent && this.isInterceptEvent()) {
                this.kingDrawView.onTouchEvent(motionEvent);
            }
        }
        else if (this.isInterceptEvent()) {
            this.kingDrawView.onTouchEvent(motionEvent);
        }
    }
}
