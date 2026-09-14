package com.kingagroot.kingdraw.utils;

import android.text.TextUtils$TruncateAt;
import androidx.appcompat.widget.AppCompatTextView;
import android.widget.LinearLayout$LayoutParams;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import com.goodsrc.library.utils.DisplayUtil;
import androidx.appcompat.widget.AppCompatImageButton;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View$OnClickListener;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.appcompat.widget.ActionMenuView;
import android.widget.LinearLayout;

public class GToolBar extends LinearLayout
{
    private ActionMenuView mMenuView;
    private ImageButton mNavBackView;
    private ImageButton mNavCloseView;
    private TextView mTitleView;
    private View$OnClickListener navBackOnClickListener;
    private View$OnClickListener navCloseOnClickListener;
    
    public GToolBar(final Context context) {
        this(context, null);
    }
    
    public GToolBar(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public GToolBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.addNavBackView(this.getResources().getDrawable(2131231356));
        this.addNavCloseView(this.getResources().getDrawable(2131231196));
        this.addTitleView(null);
        this.setBackgroundResource(2131099696);
    }
    
    private void addNavBackView(final Drawable imageDrawable) {
        if (this.mNavBackView == null) {
            this.mNavBackView = (ImageButton)new AppCompatImageButton(this.getContext(), (AttributeSet)null, 2130969710);
            final LinearLayout$LayoutParams generateDefaultLayoutParams = this.generateDefaultLayoutParams();
            generateDefaultLayoutParams.leftMargin = DisplayUtil.dip2px(this.getContext(), 5.0f);
            this.mNavBackView.setLayoutParams((ViewGroup$LayoutParams)generateDefaultLayoutParams);
        }
        if (imageDrawable != null) {
            this.mNavBackView.setImageDrawable(imageDrawable);
        }
        if (this.mNavBackView.getParent() != this) {
            this.addView((View)this.mNavBackView);
        }
    }
    
    private void addNavCloseView(final Drawable imageDrawable) {
        if (this.mNavCloseView == null) {
            (this.mNavCloseView = (ImageButton)new AppCompatImageButton(this.getContext(), (AttributeSet)null, 2130969710)).setLayoutParams((ViewGroup$LayoutParams)this.generateDefaultLayoutParams());
        }
        if (imageDrawable != null) {
            this.mNavCloseView.setImageDrawable(imageDrawable);
        }
        if (this.mNavCloseView.getParent() != this) {
            this.addView((View)this.mNavCloseView, 1);
        }
    }
    
    private void addTitleView(final String text) {
        if (this.mTitleView == null) {
            (this.mTitleView = (TextView)new AppCompatTextView(this.getContext())).setSingleLine();
            final LinearLayout$LayoutParams generateDefaultLayoutParams = this.generateDefaultLayoutParams();
            generateDefaultLayoutParams.width = -1;
            generateDefaultLayoutParams.rightMargin = DisplayUtil.dip2px(this.getContext(), 20.0f);
            this.mTitleView.setLayoutParams((ViewGroup$LayoutParams)generateDefaultLayoutParams);
            this.mTitleView.setGravity(16);
            this.mTitleView.setEllipsize(TextUtils$TruncateAt.END);
            this.mTitleView.setTextSize(2, 18.0f);
            this.setTitleColor(-16777216);
        }
        this.mTitleView.setText((CharSequence)text);
        this.addView((View)this.mTitleView, 2);
    }
    
    protected LinearLayout$LayoutParams generateDefaultLayoutParams() {
        return new LinearLayout$LayoutParams(DisplayUtil.dip2px(this.getContext(), 50.0f), -1);
    }
    
    public void setTitle(final String text) {
        this.mTitleView.setText((CharSequence)text);
    }
    
    public void setTitleColor(final int textColor) {
        this.mTitleView.setTextColor(textColor);
    }
    
    public void setsetNavigationOnBackClickListener(final View$OnClickListener onClickListener) {
        final ImageButton mNavBackView = this.mNavBackView;
        if (mNavBackView != null) {
            mNavBackView.setOnClickListener(onClickListener);
        }
    }
    
    public void setsetNavigationOnCloseClickListener(final View$OnClickListener onClickListener) {
        final ImageButton mNavCloseView = this.mNavCloseView;
        if (mNavCloseView != null) {
            mNavCloseView.setOnClickListener(onClickListener);
        }
    }
}
