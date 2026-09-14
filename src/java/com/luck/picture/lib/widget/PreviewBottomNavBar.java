package com.luck.picture.lib.widget;

import com.luck.picture.lib.style.BottomNavBarStyle;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.R$id;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.TextView;
import android.util.AttributeSet;
import android.content.Context;

public class PreviewBottomNavBar extends BottomNavBar
{
    public PreviewBottomNavBar(final Context context) {
        super(context);
    }
    
    public PreviewBottomNavBar(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public PreviewBottomNavBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public TextView getEditor() {
        return this.tvImageEditor;
    }
    
    protected void handleLayoutUI() {
        final TextView tvPreview = this.tvPreview;
        int visibility = 8;
        tvPreview.setVisibility(8);
        this.tvImageEditor.setOnClickListener((View$OnClickListener)this);
        final TextView tvImageEditor = this.tvImageEditor;
        if (this.config.onEditMediaEventListener != null) {
            visibility = 0;
        }
        tvImageEditor.setVisibility(visibility);
    }
    
    public void isDisplayEditor(final boolean b) {
        final TextView tvImageEditor = this.tvImageEditor;
        int visibility;
        if (this.config.onEditMediaEventListener != null && !b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        tvImageEditor.setVisibility(visibility);
    }
    
    public void onClick(final View view) {
        super.onClick(view);
        if (view.getId() == R$id.ps_tv_editor && this.bottomNavBarListener != null) {
            this.bottomNavBarListener.onEditImage();
        }
    }
    
    public void setBottomNavBarStyle() {
        super.setBottomNavBarStyle();
        final BottomNavBarStyle bottomBarStyle = this.config.selectorStyle.getBottomBarStyle();
        if (StyleUtils.checkStyleValidity(bottomBarStyle.getBottomPreviewNarBarBackgroundColor())) {
            this.setBackgroundColor(bottomBarStyle.getBottomPreviewNarBarBackgroundColor());
        }
        else if (StyleUtils.checkSizeValidity(bottomBarStyle.getBottomNarBarBackgroundColor())) {
            this.setBackgroundColor(bottomBarStyle.getBottomNarBarBackgroundColor());
        }
    }
}
