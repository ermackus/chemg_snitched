package com.kingagroot.kingdraw.core.tool;

import android.view.MotionEvent;
import java.util.ArrayList;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.kingdraw.core.tool.gesture.BaseGestureAction;
import java.util.List;

public class ToolController
{
    List<BaseGestureAction> gestureActions;
    private KingDrawView kingDrawView;
    private String param;
    private BaseTool tool;
    private ToolNameEnum toolNameEnum;
    
    public ToolController(final KingDrawView kingDrawView) {
        this.gestureActions = (List<BaseGestureAction>)new ArrayList();
        this.kingDrawView = kingDrawView;
    }
    
    public void addGestureAction(final BaseGestureAction baseGestureAction) {
        final List<BaseGestureAction> gestureActions = this.gestureActions;
        if (gestureActions != null) {
            gestureActions.add((Object)baseGestureAction);
        }
    }
    
    public BaseTool getTool() {
        return this.tool;
    }
    
    public ToolNameEnum getToolNameEnum() {
        return this.toolNameEnum;
    }
    
    public String getToolParam() {
        return this.param;
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        return this.toolNameEnum == ToolNameEnum.G_DRAG_TOOL || this.toolNameEnum == ToolNameEnum.GDUPLICATETOOL || this.toolNameEnum == ToolNameEnum.GGESTURE_TOOL;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final BaseTool tool = this.tool;
        return tool != null && tool.onTouchEvent(motionEvent);
    }
    
    public void setTool(final ToolNameEnum toolNameEnum, final String param) {
        this.toolNameEnum = toolNameEnum;
        this.param = param;
        final BaseTool tool = this.tool;
        if (tool != null) {
            tool.onStop();
        }
        if (toolNameEnum == ToolNameEnum.G_DRAG_TOOL) {
            (this.tool = (BaseTool)new DragTool(this.kingDrawView)).onStart();
        }
        else if (toolNameEnum == ToolNameEnum.GGESTURE_TOOL) {
            final GestureTool tool2 = new GestureTool(this.kingDrawView);
            this.tool = (BaseTool)tool2;
            tool2.addGestureActions((List)this.gestureActions);
            this.tool.onStart();
        }
        else if (toolNameEnum == ToolNameEnum.GDUPLICATETOOL) {
            (this.tool = (BaseTool)new ClipboardTool(this.kingDrawView)).onStart();
        }
    }
}
