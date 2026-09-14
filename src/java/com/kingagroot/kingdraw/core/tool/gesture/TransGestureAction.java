package com.kingagroot.kingdraw.core.tool.gesture;

import com.kingagroot.kingdraw.core.utils.L;
import android.view.MotionEvent;
import android.view.GestureDetector$OnGestureListener;
import android.view.ViewConfiguration;
import android.graphics.PointF;
import android.view.GestureDetector;
import com.kingagroot.kingdraw.core.view.KingDrawView;

public class TransGestureAction extends BaseGestureAction
{
    private static final float DOUBLEPOINTDISMIN = 200.0f;
    private float TOUCHSLOP;
    private boolean isResponseEvent;
    private boolean isScale;
    private KingDrawView kingDrawView;
    private float mInitialSpan;
    private int mSpanSlop;
    private GestureDetector myDetector;
    private boolean startCheckTrans;
    private PointF touchDownPoint;
    
    public TransGestureAction(final KingDrawView kingDrawView) {
        this.touchDownPoint = new PointF();
        this.isScale = false;
        this.kingDrawView = kingDrawView;
        final ViewConfiguration value = ViewConfiguration.get(kingDrawView.getContext());
        this.TOUCHSLOP = (float)value.getScaledTouchSlop();
        this.myDetector = new GestureDetector(kingDrawView.getContext(), (GestureDetector$OnGestureListener)new TransGestureAction.TransGestureAction$MyGestureListener(this, (TransGestureAction$1)null));
        this.mSpanSlop = value.getScaledTouchSlop() * 2;
    }
    
    private void checkIsScale(final MotionEvent motionEvent) {
        final int actionMasked = motionEvent.getActionMasked();
        final int pointerCount = motionEvent.getPointerCount();
        final boolean b = actionMasked == 6;
        int actionIndex;
        if (b) {
            actionIndex = motionEvent.getActionIndex();
        }
        else {
            actionIndex = -1;
        }
        int n;
        if (b) {
            n = pointerCount - 1;
        }
        else {
            n = pointerCount;
        }
        int i = 0;
        float n2 = 0.0f;
        float n3 = 0.0f;
        while (i < pointerCount) {
            if (actionIndex != i) {
                n2 += motionEvent.getX(i);
                n3 += motionEvent.getY(i);
            }
            ++i;
        }
        final float n4 = (float)n;
        final float n5 = n2 / n4;
        final float n6 = n3 / n4;
        int j = 0;
        float n7 = 0.0f;
        float n8 = 0.0f;
        while (j < pointerCount) {
            if (actionIndex != j) {
                n7 += Math.abs(motionEvent.getX(j) - n5);
                n8 += Math.abs(motionEvent.getY(j) - n6);
            }
            ++j;
        }
        final float mInitialSpan = (float)Math.hypot((double)(n7 / n4 * 2.0f), (double)(n8 / n4 * 2.0f));
        if (actionMasked == 5) {
            this.mInitialSpan = mInitialSpan;
        }
        final float mInitialSpan2 = this.mInitialSpan;
        if (mInitialSpan2 != 0.0f && mInitialSpan >= this.mSpanSlop && Math.abs(mInitialSpan - mInitialSpan2) > this.mSpanSlop) {
            L.i("start Scale");
            this.isScale = true;
            this.isResponseEvent = false;
        }
    }
    
    public void onTouchEvent(final MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        final int pointerCount = motionEvent.getPointerCount();
        if (motionEvent.getAction() == 0) {
            this.isResponseEvent = true;
            this.setInterceptEvent(false);
            this.touchDownPoint.x = motionEvent.getX();
            this.touchDownPoint.y = motionEvent.getY();
            this.startCheckTrans = false;
            this.isScale = false;
            this.mInitialSpan = 0.0f;
        }
        if (this.isResponseEvent && !this.isInterceptEvent()) {
            this.checkIsScale(motionEvent);
        }
        if (this.MotionActon == 2 && this.isResponseEvent) {
            if (!this.startCheckTrans) {
                final float n = motionEvent.getX() - this.touchDownPoint.x;
                final float n2 = motionEvent.getY() - this.touchDownPoint.y;
                if (Math.sqrt((double)(n * n + n2 * n2)) >= this.TOUCHSLOP * 3.0f && pointerCount == 2) {
                    this.startCheckTrans = true;
                }
            }
            this.myDetector.onTouchEvent(motionEvent);
        }
    }
}
