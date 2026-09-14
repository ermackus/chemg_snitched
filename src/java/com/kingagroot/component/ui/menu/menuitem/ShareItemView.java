package com.kingagroot.component.ui.menu.menuitem;

import com.kingagroot.component.ui.R$mipmap;
import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;

public class ShareItemView extends ToolBaseItemView
{
    public ShareItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
        this.setClickEnable(false);
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
    }
    
    @Override
    public void setClickEnable(final boolean clickEnable) {
        super.setClickEnable(clickEnable);
        if (clickEnable) {
            this.setButtonDrawable(R$mipmap.ic_shape);
        }
        else {
            this.setButtonDrawable(R$mipmap.ic_shape_no);
        }
    }
}
