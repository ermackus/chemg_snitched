package com.luck.picture.lib.widget;

import com.luck.picture.lib.style.TitleBarStyle;
import com.luck.picture.lib.R$drawable;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View$OnClickListener;
import com.luck.picture.lib.utils.StyleUtils;
import android.util.AttributeSet;
import android.content.Context;

public class PreviewTitleBar extends TitleBar
{
    public PreviewTitleBar(final Context context) {
        super(context);
    }
    
    public PreviewTitleBar(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public PreviewTitleBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public void setTitleBarStyle() {
        super.setTitleBarStyle();
        final TitleBarStyle titleBarStyle = this.config.selectorStyle.getTitleBarStyle();
        if (StyleUtils.checkStyleValidity(titleBarStyle.getPreviewTitleBackgroundColor())) {
            this.setBackgroundColor(titleBarStyle.getPreviewTitleBackgroundColor());
        }
        else if (StyleUtils.checkSizeValidity(titleBarStyle.getTitleBackgroundColor())) {
            this.setBackgroundColor(titleBarStyle.getTitleBackgroundColor());
        }
        if (StyleUtils.checkStyleValidity(titleBarStyle.getPreviewTitleLeftBackResource())) {
            this.ivLeftBack.setImageResource(titleBarStyle.getPreviewTitleLeftBackResource());
        }
        this.rlAlbumBg.setOnClickListener((View$OnClickListener)null);
        this.viewAlbumClickArea.setOnClickListener((View$OnClickListener)null);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.rlAlbumBg.getLayoutParams();
        relativeLayout$LayoutParams.removeRule(17);
        relativeLayout$LayoutParams.addRule(14);
        this.rlAlbumBg.setBackgroundResource(R$drawable.ps_ic_trans_1px);
        this.tvCancel.setVisibility(8);
        this.ivArrow.setVisibility(8);
        this.viewAlbumClickArea.setVisibility(8);
    }
}
