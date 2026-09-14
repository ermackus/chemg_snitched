package com.kingagroot.component.ui.menu.menuitem;

import com.kingagroot.kingdraw.core.OnCoreAvailableListener;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.component.ui.R$string;
import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.util.AttributeSet;
import android.content.Context;

public class RedoItemView extends ToolBaseItemView
{
    public RedoItemView(final Context context) {
        super(context);
        this.init();
    }
    
    public RedoItemView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_canvas_redo);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        if (this.kingDrawView != null) {
            this.kingDrawView.forwardStep();
        }
        if (this.onToolHintListener != null) {
            this.onToolHintListener.onShowToolName(this.context.getString(R$string.redo));
        }
    }
    
    @Override
    public void setKingDrawView(final KingDrawView kingDrawView) {
        super.setKingDrawView(kingDrawView);
        kingDrawView.addCoreAvailableListener((OnCoreAvailableListener)new RedoItemView$1(this));
    }
}
