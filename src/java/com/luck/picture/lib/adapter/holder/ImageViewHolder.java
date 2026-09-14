package com.luck.picture.lib.adapter.holder;

import com.luck.picture.lib.utils.MediaUtils;
import com.luck.picture.lib.R$string;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.style.SelectMainStyle;
import android.widget.RelativeLayout$LayoutParams;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.R$id;
import com.luck.picture.lib.config.SelectorConfig;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

public class ImageViewHolder extends BaseRecyclerMediaHolder
{
    private final ImageView ivEditor;
    private final TextView tvMediaTag;
    
    public ImageViewHolder(final View view, final SelectorConfig selectorConfig) {
        super(view, selectorConfig);
        this.tvMediaTag = (TextView)view.findViewById(R$id.tv_media_tag);
        this.ivEditor = (ImageView)view.findViewById(R$id.ivEditor);
        final SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        final int adapterImageEditorResources = selectMainStyle.getAdapterImageEditorResources();
        if (StyleUtils.checkStyleValidity(adapterImageEditorResources)) {
            this.ivEditor.setImageResource(adapterImageEditorResources);
        }
        final int[] adapterImageEditorGravity = selectMainStyle.getAdapterImageEditorGravity();
        final boolean checkArrayValidity = StyleUtils.checkArrayValidity(adapterImageEditorGravity);
        final int n = 0;
        if (checkArrayValidity && this.ivEditor.getLayoutParams() instanceof RelativeLayout$LayoutParams) {
            ((RelativeLayout$LayoutParams)this.ivEditor.getLayoutParams()).removeRule(12);
            for (int length = adapterImageEditorGravity.length, i = 0; i < length; ++i) {
                ((RelativeLayout$LayoutParams)this.ivEditor.getLayoutParams()).addRule(adapterImageEditorGravity[i]);
            }
        }
        final int[] adapterTagGravity = selectMainStyle.getAdapterTagGravity();
        if (StyleUtils.checkArrayValidity(adapterTagGravity) && this.tvMediaTag.getLayoutParams() instanceof RelativeLayout$LayoutParams) {
            ((RelativeLayout$LayoutParams)this.tvMediaTag.getLayoutParams()).removeRule(21);
            ((RelativeLayout$LayoutParams)this.tvMediaTag.getLayoutParams()).removeRule(12);
            for (int length2 = adapterTagGravity.length, j = n; j < length2; ++j) {
                ((RelativeLayout$LayoutParams)this.tvMediaTag.getLayoutParams()).addRule(adapterTagGravity[j]);
            }
        }
        final int adapterTagBackgroundResources = selectMainStyle.getAdapterTagBackgroundResources();
        if (StyleUtils.checkStyleValidity(adapterTagBackgroundResources)) {
            this.tvMediaTag.setBackgroundResource(adapterTagBackgroundResources);
        }
        final int adapterTagTextSize = selectMainStyle.getAdapterTagTextSize();
        if (StyleUtils.checkSizeValidity(adapterTagTextSize)) {
            this.tvMediaTag.setTextSize((float)adapterTagTextSize);
        }
        final int adapterTagTextColor = selectMainStyle.getAdapterTagTextColor();
        if (StyleUtils.checkStyleValidity(adapterTagTextColor)) {
            this.tvMediaTag.setTextColor(adapterTagTextColor);
        }
    }
    
    public void bindData(final LocalMedia localMedia, final int n) {
        super.bindData(localMedia, n);
        if (localMedia.isEditorImage() && localMedia.isCut()) {
            this.ivEditor.setVisibility(0);
        }
        else {
            this.ivEditor.setVisibility(8);
        }
        this.tvMediaTag.setVisibility(0);
        if (PictureMimeType.isHasGif(localMedia.getMimeType())) {
            this.tvMediaTag.setText((CharSequence)this.mContext.getString(R$string.ps_gif_tag));
        }
        else if (PictureMimeType.isHasWebp(localMedia.getMimeType())) {
            this.tvMediaTag.setText((CharSequence)this.mContext.getString(R$string.ps_webp_tag));
        }
        else if (MediaUtils.isLongImage(localMedia.getWidth(), localMedia.getHeight())) {
            this.tvMediaTag.setText((CharSequence)this.mContext.getString(R$string.ps_long_chart));
        }
        else {
            this.tvMediaTag.setVisibility(8);
        }
    }
}
