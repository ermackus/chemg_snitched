package com.kingagroot.component.ui.menu;

import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.GestureDetector$OnGestureListener;
import android.content.Context;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.graphics.PointF;
import android.view.GestureDetector;
import com.kingagroot.kingdraw.core.tool.gesture.BaseGestureAction;

public class SildeFlingGestureAction extends BaseGestureAction
{
    private static final long FLING_INTERVAL_TIME = 1000L;
    private static final int VELOCITYY_MIN;
    private final float TOUCHSLOP;
    private long flingStartTime;
    private boolean isAre;
    private boolean isResponseEvent;
    private final GestureDetector myDetector;
    private SildeFlingGestureAction.SildeFlingGestureAction$OnSildeFlingGestureActionListener onSildeFlingGestureActionListener;
    private final float paletteWidth;
    private final PointF touchDownPoint;
    
    static {
        VELOCITYY_MIN = GDensityUtil.dp2px(180.0f);
    }
    
    public SildeFlingGestureAction(final Context context, final float paletteWidth) {
        this.flingStartTime = 0L;
        this.touchDownPoint = new PointF();
        this.myDetector = new GestureDetector(context, (GestureDetector$OnGestureListener)new SildeFlingGestureAction.SildeFlingGestureAction$MyGestureListener(this, (SildeFlingGestureAction$1)null));
        this.paletteWidth = paletteWidth;
        this.TOUCHSLOP = (float)ViewConfiguration.get(context).getScaledTouchSlop();
    }
    
    public void onTouchEvent(final MotionEvent motionEvent) {
        final int pointerCount = motionEvent.getPointerCount();
        if (motionEvent.getAction() == 0) {
            this.isAre = false;
            this.isResponseEvent = true;
            this.setInterceptEvent(false);
            this.touchDownPoint.x = motionEvent.getX();
            this.touchDownPoint.y = motionEvent.getY();
            if (pointerCount == 1 && this.touchDownPoint.x > this.paletteWidth - GDensityUtil.dp2px(100.0f)) {
                this.isAre = true;
            }
        }
        if (this.isAre && this.isResponseEvent) {
            if (pointerCount == 2) {
                this.isResponseEvent = false;
            }
            if (motionEvent.getAction() == 2) {
                final float abs = Math.abs(motionEvent.getX() - this.touchDownPoint.x);
                final float abs2 = Math.abs(motionEvent.getY() - this.touchDownPoint.y);
                final double sqrt = Math.sqrt((double)(abs * abs + abs2 * abs2));
                if (sqrt > this.TOUCHSLOP / 4.0f) {
                    if (Math.round((float)(Math.asin(abs2 / sqrt) / 3.141592653589793 * 180.0)) >= 45) {
                        this.setInterceptEvent(true);
                    }
                    else {
                        this.isResponseEvent = false;
                    }
                }
            }
            this.myDetector.onTouchEvent(motionEvent);
        }
    }
    
    public void setOnSildeFlingGestureActionListener(final SildeFlingGestureAction.SildeFlingGestureAction$OnSildeFlingGestureActionListener onSildeFlingGestureActionListener) {
        this.onSildeFlingGestureActionListener = onSildeFlingGestureActionListener;
    }
}
