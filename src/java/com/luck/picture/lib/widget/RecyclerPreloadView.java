package com.luck.picture.lib.widget;

import androidx.recyclerview.widget.RecyclerView$Adapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView$LayoutManager;
import android.util.AttributeSet;
import android.content.Context;
import com.luck.picture.lib.interfaces.OnRecyclerViewScrollStateListener;
import com.luck.picture.lib.interfaces.OnRecyclerViewScrollListener;
import com.luck.picture.lib.interfaces.OnRecyclerViewPreloadMoreListener;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerPreloadView extends RecyclerView
{
    private static final int BOTTOM_DEFAULT = 1;
    public static final int BOTTOM_PRELOAD = 2;
    private static final int LIMIT = 150;
    private static final String TAG;
    private boolean isEnabledLoadMore;
    private boolean isInTheBottom;
    private int mFirstVisiblePosition;
    private int mLastVisiblePosition;
    private OnRecyclerViewPreloadMoreListener onRecyclerViewPreloadListener;
    private OnRecyclerViewScrollListener onRecyclerViewScrollListener;
    private OnRecyclerViewScrollStateListener onRecyclerViewScrollStateListener;
    private int reachBottomRow;
    
    static {
        TAG = RecyclerPreloadView.class.getSimpleName();
    }
    
    public RecyclerPreloadView(final Context context) {
        super(context);
        this.isInTheBottom = false;
        this.isEnabledLoadMore = false;
        this.reachBottomRow = 1;
    }
    
    public RecyclerPreloadView(final Context context, final AttributeSet set) {
        super(context, set);
        this.isInTheBottom = false;
        this.isEnabledLoadMore = false;
        this.reachBottomRow = 1;
    }
    
    public RecyclerPreloadView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.isInTheBottom = false;
        this.isEnabledLoadMore = false;
        this.reachBottomRow = 1;
    }
    
    private void setLayoutManagerPosition(final RecyclerView$LayoutManager recyclerView$LayoutManager) {
        if (recyclerView$LayoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager)recyclerView$LayoutManager;
            this.mFirstVisiblePosition = gridLayoutManager.findFirstVisibleItemPosition();
            this.mLastVisiblePosition = gridLayoutManager.findLastVisibleItemPosition();
        }
        else if (recyclerView$LayoutManager instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView$LayoutManager;
            this.mFirstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
            this.mLastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition();
        }
    }
    
    public int getFirstVisiblePosition() {
        return this.mFirstVisiblePosition;
    }
    
    public int getLastVisiblePosition() {
        return this.mLastVisiblePosition;
    }
    
    public boolean isEnabledLoadMore() {
        return this.isEnabledLoadMore;
    }
    
    public void onScrollStateChanged(final int n) {
        super.onScrollStateChanged(n);
        if (n == 0 || n == 1) {
            this.setLayoutManagerPosition(this.getLayoutManager());
        }
        final OnRecyclerViewScrollListener onRecyclerViewScrollListener = this.onRecyclerViewScrollListener;
        if (onRecyclerViewScrollListener != null) {
            onRecyclerViewScrollListener.onScrollStateChanged(n);
        }
        if (n == 0) {
            final OnRecyclerViewScrollStateListener onRecyclerViewScrollStateListener = this.onRecyclerViewScrollStateListener;
            if (onRecyclerViewScrollStateListener != null) {
                onRecyclerViewScrollStateListener.onScrollSlow();
            }
        }
    }
    
    public void onScrolled(final int n, final int n2) {
        super.onScrolled(n, n2);
        final RecyclerView$LayoutManager layoutManager = this.getLayoutManager();
        if (layoutManager != null) {
            this.setLayoutManagerPosition(layoutManager);
            if (this.onRecyclerViewPreloadListener != null && this.isEnabledLoadMore) {
                final RecyclerView$Adapter adapter = this.getAdapter();
                if (adapter == null) {
                    throw new RuntimeException("Adapter is null,Please check it!");
                }
                boolean b = false;
                Label_0102: {
                    if (layoutManager instanceof GridLayoutManager) {
                        final GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
                        if (gridLayoutManager.findLastVisibleItemPosition() / gridLayoutManager.getSpanCount() >= adapter.getItemCount() / gridLayoutManager.getSpanCount() - this.reachBottomRow) {
                            b = true;
                            break Label_0102;
                        }
                    }
                    b = false;
                }
                if (!b) {
                    this.isInTheBottom = false;
                }
                else if (!this.isInTheBottom) {
                    this.onRecyclerViewPreloadListener.onRecyclerViewPreloadMore();
                    if (n2 > 0) {
                        this.isInTheBottom = true;
                    }
                }
                else if (n2 == 0) {
                    this.isInTheBottom = false;
                }
            }
            final OnRecyclerViewScrollListener onRecyclerViewScrollListener = this.onRecyclerViewScrollListener;
            if (onRecyclerViewScrollListener != null) {
                onRecyclerViewScrollListener.onScrolled(n, n2);
            }
            if (this.onRecyclerViewScrollStateListener != null) {
                if (Math.abs(n2) < 150) {
                    this.onRecyclerViewScrollStateListener.onScrollSlow();
                }
                else {
                    this.onRecyclerViewScrollStateListener.onScrollFast();
                }
            }
            return;
        }
        throw new RuntimeException("LayoutManager is null,Please check it!");
    }
    
    public void setEnabledLoadMore(final boolean isEnabledLoadMore) {
        this.isEnabledLoadMore = isEnabledLoadMore;
    }
    
    public void setLastVisiblePosition(final int mLastVisiblePosition) {
        this.mLastVisiblePosition = mLastVisiblePosition;
    }
    
    public void setOnRecyclerViewPreloadListener(final OnRecyclerViewPreloadMoreListener onRecyclerViewPreloadListener) {
        this.onRecyclerViewPreloadListener = onRecyclerViewPreloadListener;
    }
    
    public void setOnRecyclerViewScrollListener(final OnRecyclerViewScrollListener onRecyclerViewScrollListener) {
        this.onRecyclerViewScrollListener = onRecyclerViewScrollListener;
    }
    
    public void setOnRecyclerViewScrollStateListener(final OnRecyclerViewScrollStateListener onRecyclerViewScrollStateListener) {
        this.onRecyclerViewScrollStateListener = onRecyclerViewScrollStateListener;
    }
    
    public void setReachBottomRow(final int n) {
        int reachBottomRow = n;
        if (n < 1) {
            reachBottomRow = 1;
        }
        this.reachBottomRow = reachBottomRow;
    }
}
