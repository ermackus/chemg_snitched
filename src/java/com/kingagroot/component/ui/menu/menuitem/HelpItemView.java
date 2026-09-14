package com.kingagroot.component.ui.menu.menuitem;

import com.kingagroot.component.ui.R$drawable;
import com.kingagroot.component.ui.R$mipmap;
import android.content.Context;

public class HelpItemView extends ToolBaseItemView
{
    public HelpItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$mipmap.ic_doc);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
    }
}
