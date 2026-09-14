package com.kingagroot.component.ui.widget.toolpop;

import com.kingagroot.component.ui.model.ToolModel;
import android.view.View;
import android.widget.PopupWindow;

public abstract class PaletteBasePop extends PopupWindow
{
    public OnToolsPopListener onToolsPopListener;
    
    public abstract View getIndicatorView();
    
    public abstract boolean isNeedMove();
    
    public void setOnToolsPopListener(final OnToolsPopListener onToolsPopListener) {
        this.onToolsPopListener = onToolsPopListener;
    }
    
    public abstract void show(final View p0);
    
    public interface OnToolsPopListener
    {
        void onChoice(final ToolModel p0);
    }
}
