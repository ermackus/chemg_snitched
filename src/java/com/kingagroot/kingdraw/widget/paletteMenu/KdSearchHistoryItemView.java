package com.kingagroot.kingdraw.widget.paletteMenu;

import android.view.View;
import android.widget.PopupWindow$OnDismissListener;
import com.kingagroot.kingdraw.dialog.HistoryPalettePop$OnGetData;
import android.view.View$OnClickListener;
import android.content.Context;
import com.kingagroot.kingdraw.dialog.HistoryPalettePop;
import com.kingagroot.component.ui.menu.menuitem.ToolBaseItemView;

public class KdSearchHistoryItemView extends ToolBaseItemView
{
    boolean isCheck;
    private KdSearchHistoryItemView.KdSearchHistoryItemView$OnHistoryMenuClickListner onHistoryMenuClickListner;
    private HistoryPalettePop pop;
    
    public KdSearchHistoryItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(2131231488);
        this.setBackgroundResource(2131231567);
        this.setOnClickListener((View$OnClickListener)this);
        (this.pop = new HistoryPalettePop(this.context)).setData((HistoryPalettePop$OnGetData)new KdSearchHistoryItemView$1(this));
        this.pop.setOnDismissListener((PopupWindow$OnDismissListener)new KdSearchHistoryItemView$2(this));
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        this.setCheck(this.isCheck ^= true);
        if (this.isCheck) {
            this.pop.showAsDropDown((View)this);
        }
        else {
            final HistoryPalettePop pop = this.pop;
            if (pop != null && pop.isShowing()) {
                this.pop.dismiss();
            }
        }
    }
    
    @Override
    public void setCheck(final boolean isCheck) {
        this.isCheck = isCheck;
        if (isCheck) {
            this.setBackgroundResource(2131231668);
            this.setButtonDrawable(2131231489);
        }
        else {
            this.setBackgroundResource(2131231567);
            this.setButtonDrawable(2131231488);
        }
    }
    
    public void setOnHistoryMenuClickListner(final KdSearchHistoryItemView.KdSearchHistoryItemView$OnHistoryMenuClickListner onHistoryMenuClickListner) {
        this.onHistoryMenuClickListner = onHistoryMenuClickListner;
    }
}
