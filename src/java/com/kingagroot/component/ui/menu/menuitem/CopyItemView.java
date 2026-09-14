package com.kingagroot.component.ui.menu.menuitem;

import com.kingagroot.kingdraw.core.OnCoreAvailableListener;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import androidx.core.content.ContextCompat;
import com.kingagroot.component.ui.R$color;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import android.view.View;
import com.kingagroot.component.ui.ToolEnum;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;

public class CopyItemView extends ToolBaseItemView
{
    boolean isCheck;
    
    public CopyItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_copy_disable);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
        this.setCopyEnable(false);
    }
    
    public void SetCanPaste() {
        this.setEnabled(true);
        this.isCheck = true;
        this.setButtonDrawable(R$drawable.ic_copy_select);
        this.setBackgroundResource(R$drawable.tool_bg_sel);
        if (this.onMenuClickListener != null) {
            this.onMenuClickListener.onClearOtherMenuItem((MenuBaseItem)this);
        }
    }
    
    public void enablePaste() {
        this.setEnabled(true);
        this.isCheck = true;
        this.setButtonDrawable(R$drawable.ic_copy_select);
        this.setBackgroundResource(R$drawable.tool_bg_sel);
        if (this.onToolChangeListener != null) {
            this.onToolChangeListener.onChange(ToolEnum.GDUPLICATETOOL);
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
        this.setCheck(this.isCheck ^ true);
        if (!this.isCheck) {
            this.kingDrawView.setTool(ToolNameEnum.G_DRAG_TOOL, (String)null);
        }
    }
    
    @Override
    public void setCheck(final boolean isCheck) {
        this.isCheck = isCheck;
        if (isCheck) {
            this.enablePaste();
        }
        else {
            this.setCopyEnable(false);
        }
    }
    
    @Override
    public void setClickEnable(final boolean clickEnable) {
        super.setClickEnable(clickEnable);
        if (clickEnable) {
            this.setButtonDrawableColor(ContextCompat.getColor(this.context, R$color.elementColor));
        }
        else {
            this.setButtonDrawableColor(ContextCompat.getColor(this.context, R$color.line));
        }
    }
    
    public void setCopyEnable(final boolean clickEnable) {
        if (this.isCheck) {
            return;
        }
        this.setButtonDrawable(R$drawable.ic_copy_disable);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setClickEnable(clickEnable);
    }
    
    @Override
    public void setKingDrawView(final KingDrawView kingDrawView) {
        super.setKingDrawView(kingDrawView);
        kingDrawView.addCoreAvailableListener((OnCoreAvailableListener)new CopyItemView$1(this));
    }
}
