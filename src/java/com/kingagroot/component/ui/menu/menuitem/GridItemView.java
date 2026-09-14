package com.kingagroot.component.ui.menu.menuitem;

import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;

public class GridItemView extends ToolBaseItemView
{
    boolean isCheck;
    
    public GridItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_draw_grids_nor);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    @Override
    public boolean isCheckOnly() {
        return false;
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        this.setCheck(this.isCheck ^= true);
        this.kingDrawView.setShowGridLine(this.isCheck);
    }
    
    @Override
    public void setCheck(final boolean isCheck) {
        this.isCheck = isCheck;
        if (isCheck) {
            this.setBackgroundResource(R$drawable.tool_bg_sel);
            this.setButtonDrawable(R$drawable.ic_draw_grids_sel);
        }
        else {
            this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
            this.setButtonDrawable(R$drawable.ic_draw_grids_nor);
        }
    }
}
