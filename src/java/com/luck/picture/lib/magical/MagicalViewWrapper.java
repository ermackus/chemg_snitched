package com.luck.picture.lib.magical;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.view.View;
import android.view.ViewGroup$MarginLayoutParams;

public class MagicalViewWrapper
{
    private final ViewGroup$MarginLayoutParams params;
    private final View viewWrapper;
    
    public MagicalViewWrapper(final View viewWrapper) {
        this.viewWrapper = viewWrapper;
        final ViewGroup$MarginLayoutParams params = (ViewGroup$MarginLayoutParams)viewWrapper.getLayoutParams();
        this.params = params;
        if (params instanceof LinearLayout$LayoutParams) {
            ((LinearLayout$LayoutParams)params).gravity = 8388611;
        }
    }
    
    public int getHeight() {
        return this.params.height;
    }
    
    public int getMarginBottom() {
        return this.params.bottomMargin;
    }
    
    public int getMarginLeft() {
        return this.params.leftMargin;
    }
    
    public int getMarginRight() {
        return this.params.rightMargin;
    }
    
    public int getMarginTop() {
        return this.params.topMargin;
    }
    
    public int getWidth() {
        return this.params.width;
    }
    
    public void setHeight(final float n) {
        this.params.height = Math.round(n);
        this.viewWrapper.setLayoutParams((ViewGroup$LayoutParams)this.params);
    }
    
    public void setMarginBottom(final int bottomMargin) {
        this.params.bottomMargin = bottomMargin;
        this.viewWrapper.setLayoutParams((ViewGroup$LayoutParams)this.params);
    }
    
    public void setMarginLeft(final int leftMargin) {
        this.params.leftMargin = leftMargin;
        this.viewWrapper.setLayoutParams((ViewGroup$LayoutParams)this.params);
    }
    
    public void setMarginRight(final int rightMargin) {
        this.params.rightMargin = rightMargin;
        this.viewWrapper.setLayoutParams((ViewGroup$LayoutParams)this.params);
    }
    
    public void setMarginTop(final int topMargin) {
        this.params.topMargin = topMargin;
        this.viewWrapper.setLayoutParams((ViewGroup$LayoutParams)this.params);
    }
    
    public void setWidth(final float n) {
        this.params.width = Math.round(n);
        this.viewWrapper.setLayoutParams((ViewGroup$LayoutParams)this.params);
    }
}
