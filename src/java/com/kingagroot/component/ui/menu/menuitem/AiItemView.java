package com.kingagroot.component.ui.menu.menuitem;

import com.kingagroot.component.ui.R$drawable;
import android.content.Context;

public class AiItemView extends ToolBaseItemView
{
    public AiItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_btn_photo_nor);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
    }
}
