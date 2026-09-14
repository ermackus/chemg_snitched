package com.goodsrc.ui.library.widget.swipemenulistview;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class SwipeMenuItem
{
    private Drawable background;
    private Drawable icon;
    private int id;
    private final Context mContext;
    private String title;
    private int titleColor;
    private int titleSize;
    private int width;
    
    public SwipeMenuItem(final Context mContext) {
        this.mContext = mContext;
    }
    
    public Drawable getBackground() {
        return this.background;
    }
    
    public Drawable getIcon() {
        return this.icon;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public int getTitleColor() {
        return this.titleColor;
    }
    
    public int getTitleSize() {
        return this.titleSize;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setBackground(final int n) {
        this.background = this.mContext.getResources().getDrawable(n);
    }
    
    public void setBackground(final Drawable background) {
        this.background = background;
    }
    
    public void setIcon(final int n) {
        this.icon = this.mContext.getResources().getDrawable(n);
    }
    
    public void setIcon(final Drawable icon) {
        this.icon = icon;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setTitle(final int n) {
        this.setTitle(this.mContext.getString(n));
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setTitleColor(final int titleColor) {
        this.titleColor = titleColor;
    }
    
    public void setTitleSize(final int titleSize) {
        this.titleSize = titleSize;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
}
