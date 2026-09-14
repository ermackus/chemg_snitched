package com.kingagroot.kingdraw.widget.paletteMenu;

import android.view.View;
import android.view.View$OnClickListener;
import android.content.Context;
import com.kingagroot.component.ui.menu.menuitem.ShareItemView;

public class KdShareItemView extends ShareItemView
{
    private KdShareItemView.KdShareItemView$OnShareClickListner onShareClickListner;
    
    public KdShareItemView(final Context context) {
        super(context);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    public void onClick(final View view) {
        super.onClick(view);
        final KdShareItemView.KdShareItemView$OnShareClickListner onShareClickListner = this.onShareClickListner;
        if (onShareClickListner != null) {
            onShareClickListner.onShare();
        }
    }
    
    public void setOnShareClickListner(final KdShareItemView.KdShareItemView$OnShareClickListner onShareClickListner) {
        this.onShareClickListner = onShareClickListner;
    }
}
