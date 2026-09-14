package com.kingagroot.kingdraw.core.view3d.tool;

import java.util.Iterator;
import com.kingagroot.kingdraw.core.view3d.utils.L;
import android.view.MotionEvent;
import com.kingagroot.kingdraw.core.view3d.tool.gesture.RotateGestureAction;
import com.kingagroot.kingdraw.core.view3d.tool.gesture.TransGestureAction;
import com.kingagroot.kingdraw.core.view3d.tool.gesture.ScaleGestureAction;
import com.kingagroot.kingdraw.core.view3d.tool.gesture.DoubleTapGestureAction;
import java.util.ArrayList;
import com.kingagroot.kingdraw.core.view3d.PaletteView;
import java.util.List;
import com.kingagroot.kingdraw.core.view3d.tool.gesture.BaseGestureAction;

public class GestureTool extends BaseTool
{
    private BaseGestureAction gestureAction;
    List<BaseGestureAction> gestureActions;
    private PaletteView view;
    
    public GestureTool(final PaletteView view) {
        this.gestureActions = (List<BaseGestureAction>)new ArrayList();
        this.view = view;
        this.init();
    }
    
    private void init() {
        this.gestureActions.add((Object)new DoubleTapGestureAction(this.view));
        this.gestureActions.add((Object)new ScaleGestureAction(this.view));
        this.gestureActions.add((Object)new TransGestureAction(this.view));
        this.gestureActions.add((Object)new RotateGestureAction(this.view));
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.gestureAction = null;
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
}
