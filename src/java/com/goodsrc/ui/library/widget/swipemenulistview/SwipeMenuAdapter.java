package com.goodsrc.ui.library.widget.swipemenulistview;

import android.database.DataSetObserver;
import android.view.ViewGroup;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.content.Context;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

public class SwipeMenuAdapter implements WrapperListAdapter, OnSwipeItemClickListener
{
    private final ListAdapter mAdapter;
    private final Context mContext;
    private SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener;
    
    public SwipeMenuAdapter(final Context mContext, final ListAdapter mAdapter) {
        this.mAdapter = mAdapter;
        this.mContext = mContext;
    }
    
    public boolean areAllItemsEnabled() {
        return this.mAdapter.areAllItemsEnabled();
    }
    
    public void createMenu(final SwipeMenu swipeMenu) {
        final SwipeMenuItem swipeMenuItem = new SwipeMenuItem(this.mContext);
        swipeMenuItem.setTitle("Item 1");
        swipeMenuItem.setBackground((Drawable)new ColorDrawable(-7829368));
        swipeMenuItem.setWidth(300);
        swipeMenu.addMenuItem(swipeMenuItem);
        final SwipeMenuItem swipeMenuItem2 = new SwipeMenuItem(this.mContext);
        swipeMenuItem2.setTitle("Item 2");
        swipeMenuItem2.setBackground((Drawable)new ColorDrawable(-65536));
        swipeMenuItem2.setWidth(300);
        swipeMenu.addMenuItem(swipeMenuItem2);
    }
    
    public int getCount() {
        return this.mAdapter.getCount();
    }
    
    public Object getItem(final int n) {
        return this.mAdapter.getItem(n);
    }
    
    public long getItemId(final int n) {
        return this.mAdapter.getItemId(n);
    }
    
    public int getItemViewType(final int n) {
        return this.mAdapter.getItemViewType(n);
    }
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        SwipeMenuLayout swipeMenuLayout;
        if (view == null) {
            view = this.mAdapter.getView(n, view, viewGroup);
            final SwipeMenu swipeMenu = new SwipeMenu(this.mContext);
            swipeMenu.setViewType(this.mAdapter.getItemViewType(n));
            this.createMenu(swipeMenu);
            final SwipeMenuListView swipeMenuListView = (SwipeMenuListView)viewGroup;
            final SwipeMenuView swipeMenuView = new SwipeMenuView(swipeMenu, swipeMenuListView);
            swipeMenuView.setOnSwipeItemClickListener((SwipeMenuView.OnSwipeItemClickListener)this);
            swipeMenuLayout = new SwipeMenuLayout(view, swipeMenuView, swipeMenuListView.getCloseInterpolator(), swipeMenuListView.getOpenInterpolator());
            swipeMenuLayout.setPosition(n);
        }
        else {
            swipeMenuLayout = (SwipeMenuLayout)view;
            swipeMenuLayout.closeMenu();
            swipeMenuLayout.setPosition(n);
            this.mAdapter.getView(n, swipeMenuLayout.getContentView(), viewGroup);
        }
        return (View)swipeMenuLayout;
    }
    
    public int getViewTypeCount() {
        return this.mAdapter.getViewTypeCount();
    }
    
    public ListAdapter getWrappedAdapter() {
        return this.mAdapter;
    }
    
    public boolean hasStableIds() {
        return this.mAdapter.hasStableIds();
    }
    
    public boolean isEmpty() {
        return this.mAdapter.isEmpty();
    }
    
    public boolean isEnabled(final int n) {
        return this.mAdapter.isEnabled(n);
    }
    
    public void onItemClick(final SwipeMenuView swipeMenuView, final SwipeMenu swipeMenu, final int n) {
        final SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener = this.onMenuItemClickListener;
        if (onMenuItemClickListener != null) {
            onMenuItemClickListener.onMenuItemClick(swipeMenuView.getPosition(), swipeMenu, n);
        }
    }
    
    public void registerDataSetObserver(final DataSetObserver dataSetObserver) {
        this.mAdapter.registerDataSetObserver(dataSetObserver);
    }
    
    public void setOnMenuItemClickListener(final SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }
    
    public void unregisterDataSetObserver(final DataSetObserver dataSetObserver) {
        this.mAdapter.unregisterDataSetObserver(dataSetObserver);
    }
}
