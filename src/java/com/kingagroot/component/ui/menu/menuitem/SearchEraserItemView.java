package com.kingagroot.component.ui.menu.menuitem;

import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;
import com.kingagroot.component.ui.ToolEnum;

public class SearchEraserItemView extends ToolBaseItemView
{
    private boolean isCheck;
    private ToolEnum toolEnum;
    
    public SearchEraserItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_erase);
        this.toolEnum = ToolEnum.GERASER_TOOL;
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    private void onEraser() {
        this.setCheck(true);
        if (this.onToolChangeListener != null) {
            this.onToolChangeListener.onChange(this.toolEnum);
        }
        if (this.onToolHintListener != null) {
            this.onToolHintListener.onShowToolName(this.getResources().getString(this.toolEnum.hintNameId));
        }
        if (this.onMenuClickListener != null) {
            this.onMenuClickListener.onClearOtherMenuItem((MenuBaseItem)this);
        }
    }
    
    @Override
    public boolean isCheckOnly() {
        return true;
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        this.isCheck ^= true;
        this.onEraser();
    }
    
    @Override
    public void setCheck(final boolean isCheck) {
        this.isCheck = isCheck;
        if (isCheck) {
            this.setBackgroundResource(R$drawable.palete_menu_check_bg);
        }
        else {
            this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        }
    }
}
