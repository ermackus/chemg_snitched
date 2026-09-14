package com.luck.picture.lib.widget;

import com.luck.picture.lib.style.BottomNavBarStyle;
import com.luck.picture.lib.utils.DensityUtil;
import com.luck.picture.lib.utils.StyleUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;
import androidx.core.content.ContextCompat;
import com.luck.picture.lib.config.SelectorProviders;
import android.view.ViewGroup;
import com.luck.picture.lib.utils.PictureFileUtils;
import com.luck.picture.lib.R;
import com.luck.picture.lib.entity.LocalMedia;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.CheckBox;
import com.luck.picture.lib.config.SelectorConfig;
import android.view.View$OnClickListener;
import android.widget.RelativeLayout;

public class BottomNavBar extends RelativeLayout implements View$OnClickListener
{
    protected OnBottomNavBarListener bottomNavBarListener;
    protected SelectorConfig config;
    private CheckBox originalCheckbox;
    protected TextView tvImageEditor;
    protected TextView tvPreview;
    
    public BottomNavBar(final Context context) {
        super(context);
        this.init();
    }
    
    public BottomNavBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public BottomNavBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void calculateFileTotalSize() {
        if (this.config.isOriginalControl) {
            long n = 0L;
            for (int i = 0; i < this.config.getSelectCount(); ++i) {
                n += ((LocalMedia)this.config.getSelectedResult().get(i)).getSize();
            }
            if (n > 0L) {
                this.originalCheckbox.setText((CharSequence)this.getContext().getString(R.string.ps_original_image, new Object[] { PictureFileUtils.formatAccurateUnitFileSize(n) }));
            }
            else {
                this.originalCheckbox.setText((CharSequence)this.getContext().getString(R.string.ps_default_original_image));
            }
        }
        else {
            this.originalCheckbox.setText((CharSequence)this.getContext().getString(R.string.ps_default_original_image));
        }
    }
    
    protected void handleLayoutUI() {
    }
    
    protected void inflateLayout() {
        inflate(this.getContext(), R.layout.ps_bottom_nav_bar, (ViewGroup)this);
    }
    
    protected void init() {
        this.inflateLayout();
        this.setClickable(true);
        this.setFocusable(true);
        this.config = SelectorProviders.getInstance().getSelectorConfig();
        this.tvPreview = (TextView)this.findViewById(R.id.ps_tv_preview);
        this.tvImageEditor = (TextView)this.findViewById(R.id.ps_tv_editor);
        this.originalCheckbox = (CheckBox)this.findViewById(R.id.cb_original);
        this.tvPreview.setOnClickListener((View$OnClickListener)this);
        this.tvImageEditor.setVisibility(8);
        this.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.ps_color_grey));
        this.originalCheckbox.setChecked(this.config.isCheckOriginalImage);
        this.originalCheckbox.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new CompoundButton$OnCheckedChangeListener(this) {
            final BottomNavBar this$0;
            
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean isCheckOriginalImage) {
                this.this$0.config.isCheckOriginalImage = isCheckOriginalImage;
                this.this$0.originalCheckbox.setChecked(this.this$0.config.isCheckOriginalImage);
                if (this.this$0.bottomNavBarListener != null) {
                    this.this$0.bottomNavBarListener.onCheckOriginalChange();
                    if (isCheckOriginalImage && this.this$0.config.getSelectCount() == 0) {
                        this.this$0.bottomNavBarListener.onFirstCheckOriginalSelectedChange();
                    }
                }
            }
        });
        this.handleLayoutUI();
    }
    
    public void onClick(final View view) {
        if (this.bottomNavBarListener == null) {
            return;
        }
        if (view.getId() == R.id.ps_tv_preview) {
            this.bottomNavBarListener.onPreview();
        }
    }
    
    public void setBottomNavBarStyle() {
        if (this.config.isDirectReturnSingle) {
            this.setVisibility(8);
            return;
        }
        final BottomNavBarStyle bottomBarStyle = this.config.selectorStyle.getBottomBarStyle();
        if (this.config.isOriginalControl) {
            this.originalCheckbox.setVisibility(0);
            final int bottomOriginalDrawableLeft = bottomBarStyle.getBottomOriginalDrawableLeft();
            if (StyleUtils.checkStyleValidity(bottomOriginalDrawableLeft)) {
                this.originalCheckbox.setButtonDrawable(bottomOriginalDrawableLeft);
            }
            String text;
            if (StyleUtils.checkStyleValidity(bottomBarStyle.getBottomOriginalTextResId())) {
                text = this.getContext().getString(bottomBarStyle.getBottomOriginalTextResId());
            }
            else {
                text = bottomBarStyle.getBottomOriginalText();
            }
            if (StyleUtils.checkTextValidity(text)) {
                this.originalCheckbox.setText((CharSequence)text);
            }
            final int bottomOriginalTextSize = bottomBarStyle.getBottomOriginalTextSize();
            if (StyleUtils.checkSizeValidity(bottomOriginalTextSize)) {
                this.originalCheckbox.setTextSize((float)bottomOriginalTextSize);
            }
            final int bottomOriginalTextColor = bottomBarStyle.getBottomOriginalTextColor();
            if (StyleUtils.checkStyleValidity(bottomOriginalTextColor)) {
                this.originalCheckbox.setTextColor(bottomOriginalTextColor);
            }
        }
        final int bottomNarBarHeight = bottomBarStyle.getBottomNarBarHeight();
        if (StyleUtils.checkSizeValidity(bottomNarBarHeight)) {
            this.getLayoutParams().height = bottomNarBarHeight;
        }
        else {
            this.getLayoutParams().height = DensityUtil.dip2px(this.getContext(), 46.0f);
        }
        final int bottomNarBarBackgroundColor = bottomBarStyle.getBottomNarBarBackgroundColor();
        if (StyleUtils.checkStyleValidity(bottomNarBarBackgroundColor)) {
            this.setBackgroundColor(bottomNarBarBackgroundColor);
        }
        final int bottomPreviewNormalTextColor = bottomBarStyle.getBottomPreviewNormalTextColor();
        if (StyleUtils.checkStyleValidity(bottomPreviewNormalTextColor)) {
            this.tvPreview.setTextColor(bottomPreviewNormalTextColor);
        }
        final int bottomPreviewNormalTextSize = bottomBarStyle.getBottomPreviewNormalTextSize();
        if (StyleUtils.checkSizeValidity(bottomPreviewNormalTextSize)) {
            this.tvPreview.setTextSize((float)bottomPreviewNormalTextSize);
        }
        String text2;
        if (StyleUtils.checkStyleValidity(bottomBarStyle.getBottomPreviewNormalTextResId())) {
            text2 = this.getContext().getString(bottomBarStyle.getBottomPreviewNormalTextResId());
        }
        else {
            text2 = bottomBarStyle.getBottomPreviewNormalText();
        }
        if (StyleUtils.checkTextValidity(text2)) {
            this.tvPreview.setText((CharSequence)text2);
        }
        String text3;
        if (StyleUtils.checkStyleValidity(bottomBarStyle.getBottomEditorTextResId())) {
            text3 = this.getContext().getString(bottomBarStyle.getBottomEditorTextResId());
        }
        else {
            text3 = bottomBarStyle.getBottomEditorText();
        }
        if (StyleUtils.checkTextValidity(text3)) {
            this.tvImageEditor.setText((CharSequence)text3);
        }
        final int bottomEditorTextSize = bottomBarStyle.getBottomEditorTextSize();
        if (StyleUtils.checkSizeValidity(bottomEditorTextSize)) {
            this.tvImageEditor.setTextSize((float)bottomEditorTextSize);
        }
        final int bottomEditorTextColor = bottomBarStyle.getBottomEditorTextColor();
        if (StyleUtils.checkStyleValidity(bottomEditorTextColor)) {
            this.tvImageEditor.setTextColor(bottomEditorTextColor);
        }
        final int bottomOriginalDrawableLeft2 = bottomBarStyle.getBottomOriginalDrawableLeft();
        if (StyleUtils.checkStyleValidity(bottomOriginalDrawableLeft2)) {
            this.originalCheckbox.setButtonDrawable(bottomOriginalDrawableLeft2);
        }
        String text4;
        if (StyleUtils.checkStyleValidity(bottomBarStyle.getBottomOriginalTextResId())) {
            text4 = this.getContext().getString(bottomBarStyle.getBottomOriginalTextResId());
        }
        else {
            text4 = bottomBarStyle.getBottomOriginalText();
        }
        if (StyleUtils.checkTextValidity(text4)) {
            this.originalCheckbox.setText((CharSequence)text4);
        }
        final int bottomOriginalTextSize2 = bottomBarStyle.getBottomOriginalTextSize();
        if (StyleUtils.checkSizeValidity(bottomOriginalTextSize2)) {
            this.originalCheckbox.setTextSize((float)bottomOriginalTextSize2);
        }
        final int bottomOriginalTextColor2 = bottomBarStyle.getBottomOriginalTextColor();
        if (StyleUtils.checkStyleValidity(bottomOriginalTextColor2)) {
            this.originalCheckbox.setTextColor(bottomOriginalTextColor2);
        }
    }
    
    public void setOnBottomNavBarListener(final OnBottomNavBarListener bottomNavBarListener) {
        this.bottomNavBarListener = bottomNavBarListener;
    }
    
    public void setOriginalCheck() {
        this.originalCheckbox.setChecked(this.config.isCheckOriginalImage);
    }
    
    public void setSelectedChange() {
        this.calculateFileTotalSize();
        final BottomNavBarStyle bottomBarStyle = this.config.selectorStyle.getBottomBarStyle();
        if (this.config.getSelectCount() > 0) {
            this.tvPreview.setEnabled(true);
            final int bottomPreviewSelectTextColor = bottomBarStyle.getBottomPreviewSelectTextColor();
            if (StyleUtils.checkStyleValidity(bottomPreviewSelectTextColor)) {
                this.tvPreview.setTextColor(bottomPreviewSelectTextColor);
            }
            else {
                this.tvPreview.setTextColor(ContextCompat.getColor(this.getContext(), R.color.ps_color_fa632d));
            }
            String text;
            if (StyleUtils.checkStyleValidity(bottomBarStyle.getBottomPreviewSelectTextResId())) {
                text = this.getContext().getString(bottomBarStyle.getBottomPreviewSelectTextResId());
            }
            else {
                text = bottomBarStyle.getBottomPreviewSelectText();
            }
            if (StyleUtils.checkTextValidity(text)) {
                if (StyleUtils.checkTextFormatValidity(text)) {
                    this.tvPreview.setText((CharSequence)String.format(text, new Object[] { this.config.getSelectCount() }));
                }
                else {
                    this.tvPreview.setText((CharSequence)text);
                }
            }
            else {
                this.tvPreview.setText((CharSequence)this.getContext().getString(R.string.ps_preview_num, new Object[] { this.config.getSelectCount() }));
            }
        }
        else {
            this.tvPreview.setEnabled(false);
            final int bottomPreviewNormalTextColor = bottomBarStyle.getBottomPreviewNormalTextColor();
            if (StyleUtils.checkStyleValidity(bottomPreviewNormalTextColor)) {
                this.tvPreview.setTextColor(bottomPreviewNormalTextColor);
            }
            else {
                this.tvPreview.setTextColor(ContextCompat.getColor(this.getContext(), R.color.ps_color_9b));
            }
            String text2;
            if (StyleUtils.checkStyleValidity(bottomBarStyle.getBottomPreviewNormalTextResId())) {
                text2 = this.getContext().getString(bottomBarStyle.getBottomPreviewNormalTextResId());
            }
            else {
                text2 = bottomBarStyle.getBottomPreviewNormalText();
            }
            if (StyleUtils.checkTextValidity(text2)) {
                this.tvPreview.setText((CharSequence)text2);
            }
            else {
                this.tvPreview.setText((CharSequence)this.getContext().getString(R.string.ps_preview));
            }
        }
    }
    
    public static class OnBottomNavBarListener
    {
        public void onCheckOriginalChange() {
        }
        
        public void onEditImage() {
        }
        
        public void onFirstCheckOriginalSelectedChange() {
        }
        
        public void onPreview() {
        }
    }
}
