package com.luck.picture.lib.widget;

import android.view.View;
import android.text.TextUtils;
import com.luck.picture.lib.utils.ValueOf;
import androidx.core.content.ContextCompat;
import com.luck.picture.lib.style.BottomNavBarStyle;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.style.PictureSelectorStyle;
import com.luck.picture.lib.utils.StyleUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.luck.picture.lib.config.SelectorProviders;
import android.view.animation.AnimationUtils;
import com.luck.picture.lib.R;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.view.animation.Animation;
import com.luck.picture.lib.config.SelectorConfig;
import android.widget.LinearLayout;

public class CompleteSelectView extends LinearLayout
{
    private SelectorConfig config;
    private Animation numberChangeAnimation;
    private TextView tvComplete;
    private TextView tvSelectNum;
    
    public CompleteSelectView(final Context context) {
        super(context);
        this.init();
    }
    
    public CompleteSelectView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public CompleteSelectView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.inflateLayout();
        this.setOrientation(0);
        this.tvSelectNum = (TextView)this.findViewById(R.id.ps_tv_select_num);
        this.tvComplete = (TextView)this.findViewById(R.id.ps_tv_complete);
        this.setGravity(16);
        this.numberChangeAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.ps_anim_modal_in);
        this.config = SelectorProviders.getInstance().getSelectorConfig();
    }
    
    protected void inflateLayout() {
        LayoutInflater.from(this.getContext()).inflate(R.layout.ps_complete_selected_layout, (ViewGroup)this);
    }
    
    public void setCompleteSelectViewStyle() {
        final PictureSelectorStyle selectorStyle = this.config.selectorStyle;
        final SelectMainStyle selectMainStyle = selectorStyle.getSelectMainStyle();
        if (StyleUtils.checkStyleValidity(selectMainStyle.getSelectNormalBackgroundResources())) {
            this.setBackgroundResource(selectMainStyle.getSelectNormalBackgroundResources());
        }
        String text;
        if (StyleUtils.checkStyleValidity(selectMainStyle.getSelectNormalTextResId())) {
            text = this.getContext().getString(selectMainStyle.getSelectNormalTextResId());
        }
        else {
            text = selectMainStyle.getSelectNormalText();
        }
        if (StyleUtils.checkTextValidity(text)) {
            if (StyleUtils.checkTextTwoFormatValidity(text)) {
                this.tvComplete.setText((CharSequence)String.format(text, new Object[] { this.config.getSelectCount(), this.config.maxSelectNum }));
            }
            else {
                this.tvComplete.setText((CharSequence)text);
            }
        }
        final int selectNormalTextSize = selectMainStyle.getSelectNormalTextSize();
        if (StyleUtils.checkSizeValidity(selectNormalTextSize)) {
            this.tvComplete.setTextSize((float)selectNormalTextSize);
        }
        final int selectNormalTextColor = selectMainStyle.getSelectNormalTextColor();
        if (StyleUtils.checkStyleValidity(selectNormalTextColor)) {
            this.tvComplete.setTextColor(selectNormalTextColor);
        }
        final BottomNavBarStyle bottomBarStyle = selectorStyle.getBottomBarStyle();
        if (bottomBarStyle.isCompleteCountTips()) {
            final int bottomSelectNumResources = bottomBarStyle.getBottomSelectNumResources();
            if (StyleUtils.checkStyleValidity(bottomSelectNumResources)) {
                this.tvSelectNum.setBackgroundResource(bottomSelectNumResources);
            }
            final int bottomSelectNumTextSize = bottomBarStyle.getBottomSelectNumTextSize();
            if (StyleUtils.checkSizeValidity(bottomSelectNumTextSize)) {
                this.tvSelectNum.setTextSize((float)bottomSelectNumTextSize);
            }
            final int bottomSelectNumTextColor = bottomBarStyle.getBottomSelectNumTextColor();
            if (StyleUtils.checkStyleValidity(bottomSelectNumTextColor)) {
                this.tvSelectNum.setTextColor(bottomSelectNumTextColor);
            }
        }
    }
    
    public void setSelectedChange(final boolean b) {
        final PictureSelectorStyle selectorStyle = this.config.selectorStyle;
        final SelectMainStyle selectMainStyle = selectorStyle.getSelectMainStyle();
        if (this.config.getSelectCount() > 0) {
            this.setEnabled(true);
            final int selectBackgroundResources = selectMainStyle.getSelectBackgroundResources();
            if (StyleUtils.checkStyleValidity(selectBackgroundResources)) {
                this.setBackgroundResource(selectBackgroundResources);
            }
            else {
                this.setBackgroundResource(R.drawable.ps_ic_trans_1px);
            }
            String text;
            if (StyleUtils.checkStyleValidity(selectMainStyle.getSelectTextResId())) {
                text = this.getContext().getString(selectMainStyle.getSelectTextResId());
            }
            else {
                text = selectMainStyle.getSelectText();
            }
            if (StyleUtils.checkTextValidity(text)) {
                if (StyleUtils.checkTextTwoFormatValidity(text)) {
                    this.tvComplete.setText((CharSequence)String.format(text, new Object[] { this.config.getSelectCount(), this.config.maxSelectNum }));
                }
                else {
                    this.tvComplete.setText((CharSequence)text);
                }
            }
            else {
                this.tvComplete.setText((CharSequence)this.getContext().getString(R.string.ps_completed));
            }
            final int selectTextSize = selectMainStyle.getSelectTextSize();
            if (StyleUtils.checkSizeValidity(selectTextSize)) {
                this.tvComplete.setTextSize((float)selectTextSize);
            }
            final int selectTextColor = selectMainStyle.getSelectTextColor();
            if (StyleUtils.checkStyleValidity(selectTextColor)) {
                this.tvComplete.setTextColor(selectTextColor);
            }
            else {
                this.tvComplete.setTextColor(ContextCompat.getColor(this.getContext(), R.color.ps_color_fa632d));
            }
            if (selectorStyle.getBottomBarStyle().isCompleteCountTips()) {
                if (this.tvSelectNum.getVisibility() == 8 || this.tvSelectNum.getVisibility() == 4) {
                    this.tvSelectNum.setVisibility(0);
                }
                if (!TextUtils.equals((CharSequence)ValueOf.toString(this.config.getSelectCount()), this.tvSelectNum.getText())) {
                    this.tvSelectNum.setText((CharSequence)ValueOf.toString(this.config.getSelectCount()));
                    if (this.config.onSelectAnimListener != null) {
                        this.config.onSelectAnimListener.onSelectAnim((View)this.tvSelectNum);
                    }
                    else {
                        this.tvSelectNum.startAnimation(this.numberChangeAnimation);
                    }
                }
            }
            else {
                this.tvSelectNum.setVisibility(8);
            }
        }
        else {
            if (b && selectMainStyle.isCompleteSelectRelativeTop()) {
                this.setEnabled(true);
                final int selectBackgroundResources2 = selectMainStyle.getSelectBackgroundResources();
                if (StyleUtils.checkStyleValidity(selectBackgroundResources2)) {
                    this.setBackgroundResource(selectBackgroundResources2);
                }
                else {
                    this.setBackgroundResource(R.drawable.ps_ic_trans_1px);
                }
                final int selectTextColor2 = selectMainStyle.getSelectTextColor();
                if (StyleUtils.checkStyleValidity(selectTextColor2)) {
                    this.tvComplete.setTextColor(selectTextColor2);
                }
                else {
                    this.tvComplete.setTextColor(ContextCompat.getColor(this.getContext(), R.color.ps_color_9b));
                }
            }
            else {
                this.setEnabled(this.config.isEmptyResultReturn);
                final int selectNormalBackgroundResources = selectMainStyle.getSelectNormalBackgroundResources();
                if (StyleUtils.checkStyleValidity(selectNormalBackgroundResources)) {
                    this.setBackgroundResource(selectNormalBackgroundResources);
                }
                else {
                    this.setBackgroundResource(R.drawable.ps_ic_trans_1px);
                }
                final int selectNormalTextColor = selectMainStyle.getSelectNormalTextColor();
                if (StyleUtils.checkStyleValidity(selectNormalTextColor)) {
                    this.tvComplete.setTextColor(selectNormalTextColor);
                }
                else {
                    this.tvComplete.setTextColor(ContextCompat.getColor(this.getContext(), R.color.ps_color_9b));
                }
            }
            this.tvSelectNum.setVisibility(8);
            String text2;
            if (StyleUtils.checkStyleValidity(selectMainStyle.getSelectNormalTextResId())) {
                text2 = this.getContext().getString(selectMainStyle.getSelectNormalTextResId());
            }
            else {
                text2 = selectMainStyle.getSelectNormalText();
            }
            if (StyleUtils.checkTextValidity(text2)) {
                if (StyleUtils.checkTextTwoFormatValidity(text2)) {
                    this.tvComplete.setText((CharSequence)String.format(text2, new Object[] { this.config.getSelectCount(), this.config.maxSelectNum }));
                }
                else {
                    this.tvComplete.setText((CharSequence)text2);
                }
            }
            else {
                this.tvComplete.setText((CharSequence)this.getContext().getString(R.string.ps_please_select));
            }
            final int selectNormalTextSize = selectMainStyle.getSelectNormalTextSize();
            if (StyleUtils.checkSizeValidity(selectNormalTextSize)) {
                this.tvComplete.setTextSize((float)selectNormalTextSize);
            }
        }
    }
}
