package com.goodsrc.ui.library.widget.swipemenulistview;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;

public class SwipeMenu
{
    private final Context mContext;
    private final List<SwipeMenuItem> mItems;
    private int mViewType;
    
    public SwipeMenu(final Context mContext) {
        this.mContext = mContext;
        this.mItems = (List<SwipeMenuItem>)new ArrayList();
    }
    
    public void addMenuItem(final SwipeMenuItem swipeMenuItem) {
        this.mItems.add((Object)swipeMenuItem);
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public SwipeMenuItem getMenuItem(final int n) {
        return (SwipeMenuItem)this.mItems.get(n);
    }
    
    public List<SwipeMenuItem> getMenuItems() {
        return this.mItems;
    }
    
    public int getViewType() {
        return this.mViewType;
    }
    
    public void removeMenuItem(final SwipeMenuItem swipeMenuItem) {
        this.mItems.remove((Object)swipeMenuItem);
    }
    
    public void setViewType(final int mViewType) {
        this.mViewType = mViewType;
    }
}
