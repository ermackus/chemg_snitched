package com.kingagroot.component.ui.menu;

import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import android.view.View;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.menu.menuitem.MenuBaseItem;
import com.kingagroot.component.ui.view.GImageButton;

public class SymbolItemView extends GImageButton implements MenuBaseItem, View$OnClickListener
{
    private MoreMenuPop menuPop;
    private OnMenuClickListener onMenuClickListener;
    String symbol;
    
    public SymbolItemView(final Context context, final String symbol, final int imageResource) {
        super(context);
        this.symbol = "";
        this.symbol = symbol;
        this.setImageResource(imageResource);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    public void contactPopupWindow(final MoreMenuPop menuPop) {
        this.menuPop = menuPop;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public boolean isCheckOnly() {
        return false;
    }
    
    public void onClick(final View view) {
        final OnMenuClickListener onMenuClickListener = this.onMenuClickListener;
        if (onMenuClickListener != null) {
            onMenuClickListener.onMenuItemClick((MenuBaseItem)this);
        }
        final MoreMenuPop menuPop = this.menuPop;
        if (menuPop != null) {
            menuPop.dismiss();
        }
    }
    
    public void setCheck(final boolean b) {
    }
    
    public void setOnMenuClickListener(final OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }
    
    public void setOnToolChangeListener(final OnToolChangeListener onToolChangeListener) {
    }
    
    public void setOnToolHintListener(final OnToolHintListener onToolHintListener) {
    }
}
