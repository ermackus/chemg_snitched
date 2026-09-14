package com.kingagroot.component.ui.menu.menuitem;

import com.kingagroot.component.ui.R$drawable;
import android.content.Context;

public class NavBackMenuItem extends ToolBaseItemView
{
    public NavBackMenuItem(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_menu_nav_back);
        this.setBackgroundColor(0);
    }
}
