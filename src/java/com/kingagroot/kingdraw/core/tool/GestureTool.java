package com.kingagroot.kingdraw.core.tool;

import java.util.Iterator;
import com.kingagroot.kingdraw.core.utils.L;
import android.view.MotionEvent;
import java.util.Collection;
import com.kingagroot.kingdraw.core.tool.gesture.RecognizeGestureAction;
import com.kingagroot.kingdraw.core.tool.gesture.TransGestureAction;
import com.kingagroot.kingdraw.core.tool.gesture.ScaleGestureAction;
import com.kingagroot.kingdraw.core.tool.gesture.DoubleTapGestureAction;
import java.util.ArrayList;
import android.graphics.PointF;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import java.util.List;
import com.kingagroot.kingdraw.core.tool.gesture.BaseGestureAction;

public class GestureTool extends BaseTool
{
    private BaseGestureAction gestureAction;
    List<BaseGestureAction> gestureActions;
    private KingDrawView kingDrawView;
    private PointF touchDownPoint;
    
    public GestureTool(final KingDrawView kingDrawView) {
        this.gestureActions = (List<BaseGestureAction>)new ArrayList();
        this.touchDownPoint = new PointF();
        this.kingDrawView = kingDrawView;
        this.init();
    }
    
    private void init() {
        this.addGestureAction(new DoubleTapGestureAction(this.kingDrawView));
        this.addGestureAction(new ScaleGestureAction(this.kingDrawView));
        this.addGestureAction(new TransGestureAction(this.kingDrawView));
        this.addGestureAction(new RecognizeGestureAction(this.kingDrawView));
    }
    
    private static native void nativeSetAtomName(final String p0, final String p1);
    
    private static native void nativeSetSupName(final String p0, final String p1);
    
    public void SetAtomName(final String s) {
        nativeSetAtomName(s, this.kingDrawView.getPaletteId());
    }
    
    public void SetSupName(final String s) {
        nativeSetSupName(s, this.kingDrawView.getPaletteId());
    }
    
    public void addGestureAction(final BaseGestureAction baseGestureAction) {
        this.gestureActions.add((Object)baseGestureAction);
    }
    
    public void addGestureActions(final List<BaseGestureAction> list) {
        if (list != null) {
            this.gestureActions.addAll((Collection)list);
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.gestureAction = null;
            this.touchDownPoint.x = motionEvent.getX();
            this.touchDownPoint.y = motionEvent.getY();
        }
        else if (motionEvent.getAction() == 1 && this.gestureAction == null) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.kingDrawView.onTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, this.touchDownPoint.x, this.touchDownPoint.y, 0));
            this.kingDrawView.onTouchEvent(motionEvent);
        }
        final BaseGestureAction gestureAction = this.gestureAction;
        if (gestureAction != null) {
            gestureAction.onTouchEvent(motionEvent);
        }
        else {
            for (final BaseGestureAction gestureAction2 : this.gestureActions) {
                gestureAction2.onTouchEvent(motionEvent);
                if (gestureAction2.isInterceptEvent()) {
                    this.gestureAction = gestureAction2;
                    L.i(gestureAction2.toString());
                    break;
                }
            }
        }
        return false;
    }
    
    public void setGestureActions(final List<BaseGestureAction> list) {
        this.gestureActions.clear();
        if (list != null) {
            this.gestureActions.addAll((Collection)list);
        }
    }
}
