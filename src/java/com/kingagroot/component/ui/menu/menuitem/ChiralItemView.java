package com.kingagroot.component.ui.menu.menuitem;

import com.kingagroot.kingdraw.core.OnCoreAvailableListener;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import com.kingagroot.kingdraw.core.model.ChirlityAvliableEnum;
import android.content.Context;

public class ChiralItemView extends ToolBaseItemView
{
    boolean isCheck;
    private ChiralItemView.ChiralItemView$OnChiralClickListener onChiralClickListener;
    
    public ChiralItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_chiral_disable);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
        this.setClickEnable(false);
    }
    
    private void setChiralState(final ChirlityAvliableEnum chirlityAvliableEnum) {
        if (chirlityAvliableEnum == ChirlityAvliableEnum.Disable) {
            this.setClickEnable(false);
            this.setButtonDrawable(R$drawable.ic_chiral_disable);
            this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
            this.isCheck = false;
        }
        else if (chirlityAvliableEnum == ChirlityAvliableEnum.Close) {
            this.setButtonDrawable(R$drawable.ic_chiral_sel);
            this.setBackgroundResource(R$drawable.tool_bg_sel);
            this.isCheck = true;
        }
        else if (chirlityAvliableEnum == ChirlityAvliableEnum.Open) {
            this.setButtonDrawable(R$drawable.ic_chiral_nor);
            this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
            this.isCheck = false;
        }
    }
    
    public void enableChiral() {
        this.isCheck = true;
        this.setButtonDrawable(R$drawable.ic_chiral_sel);
        this.setBackgroundResource(R$drawable.tool_bg_sel);
    }
    
    @Override
    public boolean isCheckOnly() {
        return false;
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        final ChiralItemView.ChiralItemView$OnChiralClickListener onChiralClickListener = this.onChiralClickListener;
        if (onChiralClickListener != null) {
            onChiralClickListener.onChiralClick(this.isCheck);
        }
    }
    
    @Override
    public void setCheck(final boolean isCheck) {
        this.isCheck = isCheck;
        if (isCheck) {
            this.enableChiral();
        }
        else {
            this.setChiralEnable();
        }
    }
    
    public void setChiralEnable() {
        if (this.isCheck) {
            return;
        }
        this.setButtonDrawable(R$drawable.ic_chiral_nor);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
    }
    
    @Override
    public void setClickEnable(final boolean clickEnable) {
        super.setClickEnable(clickEnable);
        if (clickEnable) {
            this.setButtonDrawable(R$drawable.ic_chiral_nor);
        }
        else {
            this.setButtonDrawable(R$drawable.ic_chiral_disable);
        }
    }
    
    @Override
    public void setKingDrawView(final KingDrawView kingDrawView) {
        super.setKingDrawView(kingDrawView);
        kingDrawView.addCoreAvailableListener((OnCoreAvailableListener)new ChiralItemView$1(this, kingDrawView));
    }
    
    public void setOnChiralClickListener(final ChiralItemView.ChiralItemView$OnChiralClickListener onChiralClickListener) {
        this.onChiralClickListener = onChiralClickListener;
    }
}
