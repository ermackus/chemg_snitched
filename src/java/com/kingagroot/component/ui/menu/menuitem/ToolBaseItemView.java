package com.kingagroot.component.ui.menu.menuitem;

import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import com.kingagroot.component.ui.menu.OnMenuClickListener;
import com.kingagroot.component.ui.menu.MoreMenuPop;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.view.GImageButton;

public class ToolBaseItemView extends GImageButton implements View$OnClickListener, MenuBaseItem
{
    protected boolean enable;
    protected KingDrawView kingDrawView;
    protected MoreMenuPop moreMenuPop;
    protected OnMenuClickListener onMenuClickListener;
    protected OnToolChangeListener onToolChangeListener;
    protected OnToolHintListener onToolHintListener;
    
    public ToolBaseItemView(final Context context) {
        super(context);
        this.enable = true;
    }
    
    public ToolBaseItemView(final Context context, final AttributeSet set) {
        super(context, set);
        this.enable = true;
    }
    
    public void contactPopupWindow(final MoreMenuPop moreMenuPop) {
        this.moreMenuPop = moreMenuPop;
    }
    
    public boolean isCheckOnly() {
        return true;
    }
    
    public void onClick(final View view) {
        final MoreMenuPop moreMenuPop = this.moreMenuPop;
        if (moreMenuPop != null && moreMenuPop.isShowing()) {
            this.moreMenuPop.dismiss();
        }
        final OnMenuClickListener onMenuClickListener = this.onMenuClickListener;
        if (onMenuClickListener != null) {
            onMenuClickListener.onMenuItemClick((MenuBaseItem)this);
        }
    }
    
    public void setCheck(final boolean b) {
    }
    
    public void setEnable(final boolean enable) {
        this.enable = enable;
    }
    
    public void setKingDrawView(final KingDrawView kingDrawView) {
        this.kingDrawView = kingDrawView;
    }
    
    public void setOnMenuClickListener(final OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }
    
    public void setOnToolChangeListener(final OnToolChangeListener onToolChangeListener) {
        this.onToolChangeListener = onToolChangeListener;
    }
    
    public void setOnToolHintListener(final OnToolHintListener onToolHintListener) {
        this.onToolHintListener = onToolHintListener;
    }
}
