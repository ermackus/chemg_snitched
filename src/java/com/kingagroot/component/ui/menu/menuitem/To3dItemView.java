package com.kingagroot.component.ui.menu.menuitem;

import com.kingagroot.kingdraw.core.OnCoreAvailableListener;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import com.kingagroot.component.ui.R$mipmap;
import android.content.Context;

public class To3dItemView extends ToolBaseItemView
{
    public To3dItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$mipmap.ic_more_3d);
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
            this.setButtonDrawable(R$mipmap.ic_more_3d);
        }
        else {
            this.setButtonDrawable(R$mipmap.ic_more_3d_no);
        }
    }
    
    @Override
    public void setKingDrawView(final KingDrawView kingDrawView) {
        super.setKingDrawView(kingDrawView);
        kingDrawView.addCoreAvailableListener((OnCoreAvailableListener)new To3dItemView$1(this));
    }
}
