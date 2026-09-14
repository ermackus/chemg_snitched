package com.goodsrc.ui.library.widget.swipemenulistview;

import android.widget.TextView;
import android.widget.ImageView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import java.util.Iterator;
import android.view.View$OnClickListener;
import android.widget.LinearLayout;

public class SwipeMenuView extends LinearLayout implements View$OnClickListener
{
    private SwipeMenuLayout mLayout;
    private final SwipeMenuListView mListView;
    private final SwipeMenu mMenu;
    private OnSwipeItemClickListener onItemClickListener;
    private int position;
    
    public SwipeMenuView(final SwipeMenu mMenu, final SwipeMenuListView mListView) {
        super(mMenu.getContext());
        this.mListView = mListView;
        this.mMenu = mMenu;
        final Iterator iterator = mMenu.getMenuItems().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            this.addItem((SwipeMenuItem)iterator.next(), n);
            ++n;
        }
    }
    
    private void addItem(final SwipeMenuItem swipeMenuItem, final int id) {
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(swipeMenuItem.getWidth(), -1);
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setId(id);
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        linearLayout.setBackgroundDrawable(swipeMenuItem.getBackground());
        linearLayout.setOnClickListener((View$OnClickListener)this);
        this.addView((View)linearLayout);
        if (swipeMenuItem.getIcon() != null) {
            linearLayout.addView((View)this.createIcon(swipeMenuItem));
        }
        if (!TextUtils.isEmpty((CharSequence)swipeMenuItem.getTitle())) {
            linearLayout.addView((View)this.createTitle(swipeMenuItem));
        }
    }
    
    private ImageView createIcon(final SwipeMenuItem swipeMenuItem) {
        final ImageView imageView = new ImageView(this.getContext());
        imageView.setImageDrawable(swipeMenuItem.getIcon());
        return imageView;
    }
    
    private TextView createTitle(final SwipeMenuItem swipeMenuItem) {
        final TextView textView = new TextView(this.getContext());
        textView.setText((CharSequence)swipeMenuItem.getTitle());
        textView.setGravity(17);
        textView.setTextSize((float)swipeMenuItem.getTitleSize());
        textView.setTextColor(swipeMenuItem.getTitleColor());
        return textView;
    }
    
    public OnSwipeItemClickListener getOnSwipeItemClickListener() {
        return this.onItemClickListener;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public void onClick(final View view) {
        if (this.onItemClickListener != null && this.mLayout.isOpen()) {
            this.onItemClickListener.onItemClick(this, this.mMenu, view.getId());
        }
    }
    
    public void setLayout(final SwipeMenuLayout mLayout) {
        this.mLayout = mLayout;
    }
    
    public void setOnSwipeItemClickListener(final OnSwipeItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    
    public void setPosition(final int position) {
        this.position = position;
    }
    
    public interface OnSwipeItemClickListener
    {
        void onItemClick(final SwipeMenuView p0, final SwipeMenu p1, final int p2);
    }
}
