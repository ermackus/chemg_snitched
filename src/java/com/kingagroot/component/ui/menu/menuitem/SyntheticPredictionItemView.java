package com.kingagroot.component.ui.menu.menuitem;

import com.kingagroot.kingdraw.core.OnCoreAvailableListener;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;

public class SyntheticPredictionItemView extends ToolBaseItemView
{
    private SyntheticPredictionItemView.SyntheticPredictionItemView$OnPredictionClickListener onPredictionClickListener;
    
    public SyntheticPredictionItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_forecast_disabled);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
        this.setClickEnable(false);
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        final SyntheticPredictionItemView.SyntheticPredictionItemView$OnPredictionClickListener onPredictionClickListener = this.onPredictionClickListener;
        if (onPredictionClickListener != null) {
            onPredictionClickListener.onPredictionClick();
        }
    }
    
    @Override
    public void setClickEnable(final boolean clickEnable) {
        super.setClickEnable(clickEnable);
        if (clickEnable) {
            this.setButtonDrawable(R$drawable.ic_forecast_default);
        }
        else {
            this.setButtonDrawable(R$drawable.ic_forecast_disabled);
        }
    }
    
    @Override
    public void setKingDrawView(final KingDrawView kingDrawView) {
        super.setKingDrawView(kingDrawView);
        kingDrawView.addCoreAvailableListener((OnCoreAvailableListener)new SyntheticPredictionItemView$1(this));
    }
    
    public void setOnPredictionClickListener(final SyntheticPredictionItemView.SyntheticPredictionItemView$OnPredictionClickListener onPredictionClickListener) {
        this.onPredictionClickListener = onPredictionClickListener;
    }
}
