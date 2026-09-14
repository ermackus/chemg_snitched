package com.kingagroot.component.ui.menu.menuitem;

import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;
import com.kingagroot.component.ui.ToolEnum;

public class SearchCleanItemView extends ToolBaseItemView
{
    private ToolEnum toolEnum;
    
    public SearchCleanItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_clear);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.toolEnum = ToolEnum.GCLEAR_TOOL;
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    private void onClear() {
        this.setCheck(false);
        if (this.kingDrawView != null) {
            this.kingDrawView.clear();
        }
        if (this.onToolHintListener != null) {
            this.onToolHintListener.onShowToolName(this.getResources().getString(this.toolEnum.hintNameId));
        }
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        this.onClear();
    }
}
