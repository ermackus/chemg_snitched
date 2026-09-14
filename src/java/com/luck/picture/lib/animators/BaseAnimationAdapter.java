package com.luck.picture.lib.animators;

import androidx.recyclerview.widget.RecyclerView$AdapterDataObserver;
import android.view.ViewGroup;
import android.animation.TimeInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import android.animation.Animator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public abstract class BaseAnimationAdapter extends RecyclerView$Adapter<RecyclerView$ViewHolder>
{
    private boolean isFirstOnly;
    private RecyclerView$Adapter<RecyclerView$ViewHolder> mAdapter;
    private int mDuration;
    private Interpolator mInterpolator;
    private int mLastPosition;
    
    public BaseAnimationAdapter(final RecyclerView$Adapter<RecyclerView$ViewHolder> mAdapter) {
        this.mDuration = 250;
        this.mInterpolator = (Interpolator)new LinearInterpolator();
        this.mLastPosition = -1;
        this.isFirstOnly = true;
        this.mAdapter = mAdapter;
    }
    
    protected abstract Animator[] getAnimators(final View p0);
    
    public int getItemCount() {
        return this.mAdapter.getItemCount();
    }
    
    public long getItemId(final int n) {
        return this.mAdapter.getItemId(n);
    }
    
    public int getItemViewType(final int n) {
        return this.mAdapter.getItemViewType(n);
    }
    
    public RecyclerView$Adapter<RecyclerView$ViewHolder> getWrappedAdapter() {
        return this.mAdapter;
    }
    
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mAdapter.onAttachedToRecyclerView(recyclerView);
    }
    
    public void onBindViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder, int i) {
        this.mAdapter.onBindViewHolder(recyclerView$ViewHolder, i);
        final int adapterPosition = recyclerView$ViewHolder.getAdapterPosition();
        if (this.isFirstOnly && adapterPosition <= this.mLastPosition) {
            ViewHelper.clear(recyclerView$ViewHolder.itemView);
        }
        else {
            final Animator[] animators = this.getAnimators(recyclerView$ViewHolder.itemView);
            int length;
            Animator animator;
            for (length = animators.length, i = 0; i < length; ++i) {
                animator = animators[i];
                animator.setDuration((long)this.mDuration).start();
                animator.setInterpolator((TimeInterpolator)this.mInterpolator);
            }
            this.mLastPosition = adapterPosition;
        }
    }
    
    public RecyclerView$ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return this.mAdapter.onCreateViewHolder(viewGroup, n);
    }
    
    public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mAdapter.onDetachedFromRecyclerView(recyclerView);
    }
    
    public void onViewAttachedToWindow(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        super.onViewAttachedToWindow(recyclerView$ViewHolder);
        this.mAdapter.onViewAttachedToWindow(recyclerView$ViewHolder);
    }
    
    public void onViewDetachedFromWindow(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        super.onViewDetachedFromWindow(recyclerView$ViewHolder);
        this.mAdapter.onViewDetachedFromWindow(recyclerView$ViewHolder);
    }
    
    public void onViewRecycled(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.mAdapter.onViewRecycled(recyclerView$ViewHolder);
        super.onViewRecycled(recyclerView$ViewHolder);
    }
    
    public void registerAdapterDataObserver(final RecyclerView$AdapterDataObserver recyclerView$AdapterDataObserver) {
        super.registerAdapterDataObserver(recyclerView$AdapterDataObserver);
        this.mAdapter.registerAdapterDataObserver(recyclerView$AdapterDataObserver);
    }
    
    public void setDuration(final int mDuration) {
        this.mDuration = mDuration;
    }
    
    public void setFirstOnly(final boolean isFirstOnly) {
        this.isFirstOnly = isFirstOnly;
    }
    
    public void setInterpolator(final Interpolator mInterpolator) {
        this.mInterpolator = mInterpolator;
    }
    
    public void setStartPosition(final int mLastPosition) {
        this.mLastPosition = mLastPosition;
    }
    
    public void unregisterAdapterDataObserver(final RecyclerView$AdapterDataObserver recyclerView$AdapterDataObserver) {
        super.unregisterAdapterDataObserver(recyclerView$AdapterDataObserver);
        this.mAdapter.unregisterAdapterDataObserver(recyclerView$AdapterDataObserver);
    }
}
