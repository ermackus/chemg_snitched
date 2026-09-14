package com.luck.picture.lib.adapter.holder;

import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.R$string;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.R$id;
import android.widget.TextView;
import android.view.View;

public class CameraViewHolder extends BaseRecyclerMediaHolder
{
    public CameraViewHolder(final View view) {
        super(view);
        final TextView textView = (TextView)view.findViewById(R$id.tvCamera);
        this.selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
        final SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        final int adapterCameraBackgroundColor = selectMainStyle.getAdapterCameraBackgroundColor();
        if (StyleUtils.checkStyleValidity(adapterCameraBackgroundColor)) {
            textView.setBackgroundColor(adapterCameraBackgroundColor);
        }
        final int adapterCameraDrawableTop = selectMainStyle.getAdapterCameraDrawableTop();
        if (StyleUtils.checkStyleValidity(adapterCameraDrawableTop)) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, adapterCameraDrawableTop, 0, 0);
        }
        String text;
        if (StyleUtils.checkStyleValidity(selectMainStyle.getAdapterCameraTextResId())) {
            text = view.getContext().getString(selectMainStyle.getAdapterCameraTextResId());
        }
        else {
            text = selectMainStyle.getAdapterCameraText();
        }
        if (StyleUtils.checkTextValidity(text)) {
            textView.setText((CharSequence)text);
        }
        else if (this.selectorConfig.chooseMode == SelectMimeType.ofAudio()) {
            textView.setText((CharSequence)view.getContext().getString(R$string.ps_tape));
        }
        final int adapterCameraTextSize = selectMainStyle.getAdapterCameraTextSize();
        if (StyleUtils.checkSizeValidity(adapterCameraTextSize)) {
            textView.setTextSize((float)adapterCameraTextSize);
        }
        final int adapterCameraTextColor = selectMainStyle.getAdapterCameraTextColor();
        if (StyleUtils.checkStyleValidity(adapterCameraTextColor)) {
            textView.setTextColor(adapterCameraTextColor);
        }
    }
}
