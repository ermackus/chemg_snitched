package com.luck.picture.lib.adapter.holder;

import com.luck.picture.lib.R$drawable;
import com.luck.picture.lib.utils.DateUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.style.SelectMainStyle;
import android.widget.RelativeLayout$LayoutParams;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.R$id;
import com.luck.picture.lib.config.SelectorConfig;
import android.view.View;
import android.widget.TextView;

public class AudioViewHolder extends BaseRecyclerMediaHolder
{
    private final TextView tvDuration;
    
    public AudioViewHolder(final View view, final SelectorConfig selectorConfig) {
        super(view, selectorConfig);
        this.tvDuration = (TextView)view.findViewById(R$id.tv_duration);
        final SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        final int adapterDurationDrawableLeft = selectMainStyle.getAdapterDurationDrawableLeft();
        final boolean checkStyleValidity = StyleUtils.checkStyleValidity(adapterDurationDrawableLeft);
        int i = 0;
        if (checkStyleValidity) {
            this.tvDuration.setCompoundDrawablesRelativeWithIntrinsicBounds(adapterDurationDrawableLeft, 0, 0, 0);
        }
        final int adapterDurationTextSize = selectMainStyle.getAdapterDurationTextSize();
        if (StyleUtils.checkSizeValidity(adapterDurationTextSize)) {
            this.tvDuration.setTextSize((float)adapterDurationTextSize);
        }
        final int adapterDurationTextColor = selectMainStyle.getAdapterDurationTextColor();
        if (StyleUtils.checkStyleValidity(adapterDurationTextColor)) {
            this.tvDuration.setTextColor(adapterDurationTextColor);
        }
        final int adapterDurationBackgroundResources = selectMainStyle.getAdapterDurationBackgroundResources();
        if (StyleUtils.checkStyleValidity(adapterDurationBackgroundResources)) {
            this.tvDuration.setBackgroundResource(adapterDurationBackgroundResources);
        }
        final int[] adapterDurationGravity = selectMainStyle.getAdapterDurationGravity();
        if (StyleUtils.checkArrayValidity(adapterDurationGravity) && this.tvDuration.getLayoutParams() instanceof RelativeLayout$LayoutParams) {
            ((RelativeLayout$LayoutParams)this.tvDuration.getLayoutParams()).removeRule(12);
            while (i < adapterDurationGravity.length) {
                ((RelativeLayout$LayoutParams)this.tvDuration.getLayoutParams()).addRule(adapterDurationGravity[i]);
                ++i;
            }
        }
    }
    
    public void bindData(final LocalMedia localMedia, final int n) {
        super.bindData(localMedia, n);
        this.tvDuration.setText((CharSequence)DateUtils.formatDurationTime(localMedia.getDuration()));
    }
    
    protected void loadCover(final String s) {
        this.ivPicture.setImageResource(R$drawable.ps_audio_placeholder);
    }
}
