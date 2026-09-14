package com.kingagroot.component.ui.menu.menuitem;

import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;
import com.kingagroot.component.ui.model.ToolModel;

public class SecondaryMenuItem extends ToolBaseItemView
{
    private ToolModel toolModel;
    
    public SecondaryMenuItem(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    public ToolModel getToolModel() {
        return this.toolModel;
    }
    
    @Override
    public void setCheck(final boolean check) {
        super.setCheck(check);
        if (check) {
            this.setBackgroundResource(R$drawable.palete_menu_check_bg);
        }
        else {
            this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        }
    }
    
    public void setToolModel(final ToolModel toolModel) {
        this.toolModel = toolModel;
        this.setButtonDrawable(toolModel.getResId());
    }
}
