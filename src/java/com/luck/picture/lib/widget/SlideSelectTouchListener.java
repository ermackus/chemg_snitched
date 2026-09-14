package com.luck.picture.lib.widget;

import androidx.core.view.ViewCompat;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.content.res.Resources;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView$OnItemTouchListener;

public class SlideSelectTouchListener implements RecyclerView$OnItemTouchListener
{
    private boolean isActive;
    private int mAutoScrollDistance;
    private int mBottomBoundFrom;
    private int mBottomBoundTo;
    private int mEnd;
    private int mHeaderViewCount;
    private boolean mInBottomSpot;
    private boolean mInTopSpot;
    private int mLastEnd;
    private int mLastStart;
    private float mLastX;
    private float mLastY;
    private int mMaxScrollDistance;
    private RecyclerView mRecyclerView;
    private boolean mScrollAboveTopRegion;
    private boolean mScrollBelowTopRegion;
    private int mScrollDistance;
    private final Runnable mScrollRunnable;
    private OverScroller mScroller;
    private SlideSelectTouchListener$OnSlideSelectListener mSelectListener;
    private int mStart;
    private int mTopBoundFrom;
    private int mTopBoundTo;
    private int mTouchRegionBottomOffset;
    private int mTouchRegionTopOffset;
    
    public SlideSelectTouchListener() {
        this.mScrollRunnable = (Runnable)new SlideSelectTouchListener$1(this);
        this.mMaxScrollDistance = 16;
        this.mAutoScrollDistance = (int)(Resources.getSystem().getDisplayMetrics().density * 56.0f);
        this.mTouchRegionTopOffset = 0;
        this.mTouchRegionBottomOffset = 0;
        this.mScrollAboveTopRegion = true;
        this.mScrollBelowTopRegion = true;
        this.reset();
    }
    
    private void changeSelectedRange(final RecyclerView recyclerView, final float n, final float n2) {
        final View childViewUnder = recyclerView.findChildViewUnder(n, n2);
        if (childViewUnder != null) {
            final int mEnd = recyclerView.getChildAdapterPosition(childViewUnder) - this.mHeaderViewCount;
            if (mEnd != -1 && this.mEnd != mEnd) {
                this.mEnd = mEnd;
                this.notifySelectRangeChange();
            }
        }
    }
    
    private void changeSelectedRange(final RecyclerView recyclerView, final MotionEvent motionEvent) {
        this.changeSelectedRange(recyclerView, motionEvent.getX(), motionEvent.getY());
    }
    
    private void initScroller(final Context context) {
        if (this.mScroller == null) {
            this.mScroller = new OverScroller(context, (Interpolator)new LinearInterpolator());
        }
    }
    
    private void notifySelectRangeChange() {
        if (this.mSelectListener == null) {
            return;
        }
        final int mStart = this.mStart;
        if (mStart != -1) {
            final int mEnd = this.mEnd;
            if (mEnd != -1) {
                final int min = Math.min(mStart, mEnd);
                final int max = Math.max(this.mStart, this.mEnd);
                if (min < 0) {
                    return;
                }
                final int mLastStart = this.mLastStart;
                if (mLastStart != -1 && this.mLastEnd != -1) {
                    if (min > mLastStart) {
                        this.mSelectListener.onSelectChange(mLastStart, min - 1, false);
                    }
                    else if (min < mLastStart) {
                        this.mSelectListener.onSelectChange(min, mLastStart - 1, true);
                    }
                    final int mLastEnd = this.mLastEnd;
                    if (max > mLastEnd) {
                        this.mSelectListener.onSelectChange(mLastEnd + 1, max, true);
                    }
                    else if (max < mLastEnd) {
                        this.mSelectListener.onSelectChange(max + 1, mLastEnd, false);
                    }
                }
                else if (max - min == 1) {
                    this.mSelectListener.onSelectChange(min, min, true);
                }
                else {
                    this.mSelectListener.onSelectChange(min, max, true);
                }
                this.mLastStart = min;
                this.mLastEnd = max;
            }
        }
    }
    
    private void processAutoScroll(final MotionEvent motionEvent) {
        final int n = (int)motionEvent.getY();
        if (n >= this.mTopBoundFrom && n <= this.mTopBoundTo) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            final int mTopBoundTo = this.mTopBoundTo;
            final float n2 = (float)mTopBoundTo;
            final int mTopBoundFrom = this.mTopBoundFrom;
            this.mScrollDistance = (int)(this.mMaxScrollDistance * ((n2 - mTopBoundFrom - (n - (float)mTopBoundFrom)) / (mTopBoundTo - (float)mTopBoundFrom)) * -1.0f);
            if (!this.mInTopSpot) {
                this.mInTopSpot = true;
                this.startAutoScroll();
            }
        }
        else if (this.mScrollAboveTopRegion && n < this.mTopBoundFrom) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            this.mScrollDistance = this.mMaxScrollDistance * -1;
            if (!this.mInTopSpot) {
                this.mInTopSpot = true;
                this.startAutoScroll();
            }
        }
        else if (n >= this.mBottomBoundFrom && n <= this.mBottomBoundTo) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            final float n3 = (float)n;
            final int mBottomBoundFrom = this.mBottomBoundFrom;
            this.mScrollDistance = (int)(this.mMaxScrollDistance * ((n3 - mBottomBoundFrom) / (this.mBottomBoundTo - (float)mBottomBoundFrom)));
            if (!this.mInBottomSpot) {
                this.mInBottomSpot = true;
                this.startAutoScroll();
            }
        }
        else if (this.mScrollBelowTopRegion && n > this.mBottomBoundTo) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            this.mScrollDistance = this.mMaxScrollDistance;
            if (!this.mInTopSpot) {
                this.mInTopSpot = true;
                this.startAutoScroll();
            }
        }
        else {
            this.mInBottomSpot = false;
            this.mInTopSpot = false;
            this.mLastX = Float.MIN_VALUE;
            this.mLastY = Float.MIN_VALUE;
            this.stopAutoScroll();
        }
    }
    
    private void reset() {
        this.setActive(false);
        final SlideSelectTouchListener$OnSlideSelectListener mSelectListener = this.mSelectListener;
        if (mSelectListener != null && mSelectListener instanceof OnAdvancedSlideSelectListener) {
            ((OnAdvancedSlideSelectListener)mSelectListener).onSelectionFinished(this.mEnd);
        }
        this.mStart = -1;
        this.mEnd = -1;
        this.mLastStart = -1;
        this.mLastEnd = -1;
        this.mInTopSpot = false;
        this.mInBottomSpot = false;
        this.mLastX = Float.MIN_VALUE;
        this.mLastY = Float.MIN_VALUE;
        this.stopAutoScroll();
    }
    
    private void scrollBy(int n) {
        if (n > 0) {
            n = Math.min(n, this.mMaxScrollDistance);
        }
        else {
            n = Math.max(n, -this.mMaxScrollDistance);
        }
        this.mRecyclerView.scrollBy(0, n);
        final float mLastX = this.mLastX;
        if (mLastX != Float.MIN_VALUE) {
            final float mLastY = this.mLastY;
            if (mLastY != Float.MIN_VALUE) {
                this.changeSelectedRange(this.mRecyclerView, mLastX, mLastY);
            }
        }
    }
    
    public boolean onInterceptTouchEvent(final RecyclerView mRecyclerView, final MotionEvent motionEvent) {
        if (this.isActive && mRecyclerView.getAdapter() != null && mRecyclerView.getAdapter().getItemCount() != 0) {
            final int action = motionEvent.getAction();
            if (action == 0 || action == 5) {
                this.reset();
            }
            this.mRecyclerView = mRecyclerView;
            final int height = mRecyclerView.getHeight();
            final int mTouchRegionTopOffset = this.mTouchRegionTopOffset;
            this.mTopBoundFrom = mTouchRegionTopOffset;
            final int mAutoScrollDistance = this.mAutoScrollDistance;
            this.mTopBoundTo = mTouchRegionTopOffset + mAutoScrollDistance;
            final int mTouchRegionBottomOffset = this.mTouchRegionBottomOffset;
            this.mBottomBoundFrom = height + mTouchRegionBottomOffset - mAutoScrollDistance;
            this.mBottomBoundTo = height + mTouchRegionBottomOffset;
            return true;
        }
        return false;
    }
    
    public void onRequestDisallowInterceptTouchEvent(final boolean b) {
    }
    
    public void onTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
        if (!this.isActive) {
            this.reset();
            return;
        }
        final int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                if (!this.mInTopSpot && !this.mInBottomSpot) {
                    this.changeSelectedRange(recyclerView, motionEvent);
                }
                this.processAutoScroll(motionEvent);
                return;
            }
            if (action != 3 && action != 6) {
                return;
            }
        }
        this.reset();
    }
    
    public void setActive(final boolean isActive) {
        this.isActive = isActive;
    }
    
    public SlideSelectTouchListener setRecyclerViewHeaderCount(final int mHeaderViewCount) {
        this.mHeaderViewCount = mHeaderViewCount;
        return this;
    }
    
    public void startAutoScroll() {
        final RecyclerView mRecyclerView = this.mRecyclerView;
        if (mRecyclerView == null) {
            return;
        }
        this.initScroller(mRecyclerView.getContext());
        if (this.mScroller.isFinished()) {
            this.mRecyclerView.removeCallbacks(this.mScrollRunnable);
            final OverScroller mScroller = this.mScroller;
            mScroller.startScroll(0, mScroller.getCurrY(), 0, 5000, 100000);
            ViewCompat.postOnAnimation((View)this.mRecyclerView, this.mScrollRunnable);
        }
    }
    
    public void startSlideSelection(final int n) {
        this.setActive(true);
        this.mStart = n;
        this.mEnd = n;
        this.mLastStart = n;
        this.mLastEnd = n;
        final SlideSelectTouchListener$OnSlideSelectListener mSelectListener = this.mSelectListener;
        if (mSelectListener != null && mSelectListener instanceof OnAdvancedSlideSelectListener) {
            ((OnAdvancedSlideSelectListener)mSelectListener).onSelectionStarted(n);
        }
    }
    
    public void stopAutoScroll() {
        try {
            if (this.mScroller != null && !this.mScroller.isFinished()) {
                this.mRecyclerView.removeCallbacks(this.mScrollRunnable);
                this.mScroller.abortAnimation();
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public SlideSelectTouchListener withBottomOffset(final int mTouchRegionBottomOffset) {
        this.mTouchRegionBottomOffset = mTouchRegionBottomOffset;
        return this;
    }
    
    public SlideSelectTouchListener withMaxScrollDistance(final int mMaxScrollDistance) {
        this.mMaxScrollDistance = mMaxScrollDistance;
        return this;
    }
    
    public SlideSelectTouchListener withScrollAboveTopRegion(final boolean mScrollAboveTopRegion) {
        this.mScrollAboveTopRegion = mScrollAboveTopRegion;
        return this;
    }
    
    public SlideSelectTouchListener withScrollBelowTopRegion(final boolean mScrollBelowTopRegion) {
        this.mScrollBelowTopRegion = mScrollBelowTopRegion;
        return this;
    }
    
    public SlideSelectTouchListener withSelectListener(final SlideSelectTouchListener$OnSlideSelectListener mSelectListener) {
        this.mSelectListener = mSelectListener;
        return this;
    }
    
    public SlideSelectTouchListener withTopOffset(final int mTouchRegionTopOffset) {
        this.mTouchRegionTopOffset = mTouchRegionTopOffset;
        return this;
    }
    
    public SlideSelectTouchListener withTouchRegion(final int mAutoScrollDistance) {
        this.mAutoScrollDistance = mAutoScrollDistance;
        return this;
    }
    
    public interface OnAdvancedSlideSelectListener extends SlideSelectTouchListener$OnSlideSelectListener
    {
        void onSelectionFinished(final int p0);
        
        void onSelectionStarted(final int p0);
    }
}
