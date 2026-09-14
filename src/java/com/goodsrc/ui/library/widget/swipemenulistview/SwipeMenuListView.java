package com.goodsrc.ui.library.widget.swipemenulistview;

import android.widget.ListAdapter;
import android.widget.Adapter;
import android.view.View;
import androidx.core.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.util.TypedValue;
import android.util.AttributeSet;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.ListView;

public class SwipeMenuListView extends ListView
{
    private static final int TOUCH_STATE_NONE = 0;
    private static final int TOUCH_STATE_X = 1;
    private static final int TOUCH_STATE_Y = 2;
    private int MAX_X;
    private int MAX_Y;
    private Interpolator mCloseInterpolator;
    private float mDownX;
    private float mDownY;
    private SwipeMenuCreator mMenuCreator;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private OnSwipeListener mOnSwipeListener;
    private Interpolator mOpenInterpolator;
    private int mTouchPosition;
    private int mTouchState;
    private SwipeMenuLayout mTouchView;
    
    public SwipeMenuListView(final Context context) {
        super(context);
        this.MAX_Y = 5;
        this.MAX_X = 3;
        this.init();
    }
    
    public SwipeMenuListView(final Context context, final AttributeSet set) {
        super(context, set);
        this.MAX_Y = 5;
        this.MAX_X = 3;
        this.init();
    }
    
    public SwipeMenuListView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.MAX_Y = 5;
        this.MAX_X = 3;
        this.init();
    }
    
    private int dp2px(final int n) {
        return (int)TypedValue.applyDimension(1, (float)n, this.getContext().getResources().getDisplayMetrics());
    }
    
    private void init() {
        this.MAX_X = this.dp2px(this.MAX_X);
        this.MAX_Y = this.dp2px(this.MAX_Y);
        this.mTouchState = 0;
    }
    
    public Interpolator getCloseInterpolator() {
        return this.mCloseInterpolator;
    }
    
    public Interpolator getOpenInterpolator() {
        return this.mOpenInterpolator;
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 && this.mTouchView == null) {
            return super.onTouchEvent(motionEvent);
        }
        MotionEventCompat.getActionMasked(motionEvent);
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    final float abs = Math.abs(motionEvent.getY() - this.mDownY);
                    final float abs2 = Math.abs(motionEvent.getX() - this.mDownX);
                    this.getParent().requestDisallowInterceptTouchEvent(Math.round((float)(Math.asin(abs / Math.sqrt((double)(abs * abs + abs2 * abs2))) / 3.141592653589793 * 180.0)) <= 45);
                    final int mTouchState = this.mTouchState;
                    if (mTouchState == 1) {
                        final SwipeMenuLayout mTouchView = this.mTouchView;
                        if (mTouchView != null) {
                            mTouchView.onSwipe(motionEvent);
                        }
                        this.getSelector().setState(new int[] { 0 });
                        motionEvent.setAction(3);
                        return super.onTouchEvent(motionEvent);
                    }
                    if (mTouchState == 0) {
                        if (Math.abs(abs) > this.MAX_Y) {
                            this.mTouchState = 2;
                        }
                        else if (abs2 > this.MAX_X) {
                            this.mTouchState = 1;
                            final OnSwipeListener mOnSwipeListener = this.mOnSwipeListener;
                            if (mOnSwipeListener != null) {
                                mOnSwipeListener.onSwipeStart(this.mTouchPosition);
                            }
                        }
                    }
                }
            }
            else if (this.mTouchState == 1) {
                final SwipeMenuLayout mTouchView2 = this.mTouchView;
                if (mTouchView2 != null) {
                    mTouchView2.onSwipe(motionEvent);
                    if (!this.mTouchView.isOpen()) {
                        this.mTouchPosition = -1;
                        this.mTouchView = null;
                    }
                }
                final OnSwipeListener mOnSwipeListener2 = this.mOnSwipeListener;
                if (mOnSwipeListener2 != null) {
                    mOnSwipeListener2.onSwipeEnd(this.mTouchPosition);
                }
                motionEvent.setAction(3);
                super.onTouchEvent(motionEvent);
                return true;
            }
        }
        else {
            final int mTouchPosition = this.mTouchPosition;
            this.mDownX = motionEvent.getX();
            this.mDownY = motionEvent.getY();
            this.mTouchState = 0;
            if ((this.mTouchPosition = this.pointToPosition((int)motionEvent.getX(), (int)motionEvent.getY())) == mTouchPosition) {
                final SwipeMenuLayout mTouchView3 = this.mTouchView;
                if (mTouchView3 != null && mTouchView3.isOpen()) {
                    this.mTouchState = 1;
                    this.mTouchView.onSwipe(motionEvent);
                    return true;
                }
            }
            final View child = this.getChildAt(this.mTouchPosition - this.getFirstVisiblePosition());
            final SwipeMenuLayout mTouchView4 = this.mTouchView;
            if (mTouchView4 != null && mTouchView4.isOpen()) {
                this.mTouchView.smoothCloseMenu();
                this.mTouchView = null;
                return super.onTouchEvent(motionEvent);
            }
            if (child instanceof SwipeMenuLayout) {
                this.mTouchView = (SwipeMenuLayout)child;
            }
            final SwipeMenuLayout mTouchView5 = this.mTouchView;
            if (mTouchView5 != null) {
                mTouchView5.onSwipe(motionEvent);
            }
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void setAdapter(final ListAdapter listAdapter) {
        super.setAdapter((ListAdapter)new SwipeMenuAdapter(this, this.getContext(), listAdapter) {
            final SwipeMenuListView this$0;
            
            @Override
            public void createMenu(final SwipeMenu swipeMenu) {
                if (this.this$0.mMenuCreator != null) {
                    this.this$0.mMenuCreator.create(swipeMenu);
                }
            }
            
            @Override
            public void onItemClick(final SwipeMenuView swipeMenuView, final SwipeMenu swipeMenu, final int n) {
                if (this.this$0.mOnMenuItemClickListener != null) {
                    this.this$0.mOnMenuItemClickListener.onMenuItemClick(swipeMenuView.getPosition(), swipeMenu, n);
                }
                if (this.this$0.mTouchView != null) {
                    this.this$0.mTouchView.smoothCloseMenu();
                }
            }
        });
    }
    
    public void setCloseInterpolator(final Interpolator mCloseInterpolator) {
        this.mCloseInterpolator = mCloseInterpolator;
    }
    
    public void setMenuCreator(final SwipeMenuCreator mMenuCreator) {
        this.mMenuCreator = mMenuCreator;
    }
    
    public void setOnMenuItemClickListener(final OnMenuItemClickListener mOnMenuItemClickListener) {
        this.mOnMenuItemClickListener = mOnMenuItemClickListener;
    }
    
    public void setOnSwipeListener(final OnSwipeListener mOnSwipeListener) {
        this.mOnSwipeListener = mOnSwipeListener;
    }
    
    public void setOpenInterpolator(final Interpolator mOpenInterpolator) {
        this.mOpenInterpolator = mOpenInterpolator;
    }
    
    public void smoothOpenMenu(final int mTouchPosition) {
        if (mTouchPosition >= this.getFirstVisiblePosition() && mTouchPosition <= this.getLastVisiblePosition()) {
            final View child = this.getChildAt(mTouchPosition - this.getFirstVisiblePosition());
            if (child instanceof SwipeMenuLayout) {
                this.mTouchPosition = mTouchPosition;
                final SwipeMenuLayout mTouchView = this.mTouchView;
                if (mTouchView != null && mTouchView.isOpen()) {
                    this.mTouchView.smoothCloseMenu();
                }
                (this.mTouchView = (SwipeMenuLayout)child).smoothOpenMenu();
            }
        }
    }
    
    public interface OnMenuItemClickListener
    {
        void onMenuItemClick(final int p0, final SwipeMenu p1, final int p2);
    }
    
    public interface OnSwipeListener
    {
        void onSwipeEnd(final int p0);
        
        void onSwipeStart(final int p0);
    }
}
