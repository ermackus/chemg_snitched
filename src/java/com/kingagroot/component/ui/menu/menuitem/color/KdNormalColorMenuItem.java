package com.kingagroot.component.ui.menu.menuitem.color;

import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import com.kingagroot.component.ui.menu.OnMenuClickListener;
import com.kingagroot.component.ui.R$id;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.component.ui.R$layout;
import android.content.Context;
import android.widget.TextView;
import com.kingagroot.component.ui.menu.MoreMenuPop;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.menu.menuitem.MenuBaseItem;
import android.widget.FrameLayout;

public class KdNormalColorMenuItem extends FrameLayout implements MenuBaseItem, View$OnClickListener
{
    private int color;
    private MoreMenuPop moreMenuPop;
    private OnColorMenuItemListener onColorMenuItemListener;
    private final TextView tvColor;
    
    public KdNormalColorMenuItem(final Context context) {
        super(context);
        this.tvColor = (TextView)View.inflate(context, R$layout.component_layout_menu_color, (ViewGroup)this).findViewById(R$id.tv_color);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    public void contactPopupWindow(final MoreMenuPop moreMenuPop) {
        this.moreMenuPop = moreMenuPop;
    }
    
    public boolean isCheckOnly() {
        return false;
    }
    
    public void onClick(final View view) {
        final MoreMenuPop moreMenuPop = this.moreMenuPop;
        if (moreMenuPop != null && moreMenuPop.isShowing()) {
            this.moreMenuPop.dismiss();
        }
        final OnColorMenuItemListener onColorMenuItemListener = this.onColorMenuItemListener;
        if (onColorMenuItemListener != null) {
            onColorMenuItemListener.onSelectColor(this.color);
        }
    }
    
    public void setCheck(final boolean b) {
    }
    
    public void setColor(final int n) {
        this.color = n;
        this.tvColor.setBackgroundColor(n);
    }
    
    public void setOnColorMenuItemListener(final OnColorMenuItemListener onColorMenuItemListener) {
        this.onColorMenuItemListener = onColorMenuItemListener;
    }
    
    public void setOnMenuClickListener(final OnMenuClickListener onMenuClickListener) {
    }
    
    public void setOnToolChangeListener(final OnToolChangeListener onToolChangeListener) {
    }
    
    public void setOnToolHintListener(final OnToolHintListener onToolHintListener) {
    }
}
