package com.goodsrc.ui.library.widget.swipemenulistview;

import android.util.Log;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout$LayoutParams;
import android.view.MotionEvent;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.util.TypedValue;
import android.util.AttributeSet;
import android.content.Context;
import android.view.GestureDetector$OnGestureListener;
import androidx.core.view.GestureDetectorCompat;
import android.view.View;
import androidx.core.widget.ScrollerCompat;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

public class SwipeMenuLayout extends FrameLayout
{
    private static final int CONTENT_VIEW_ID = 1;
    private static final int MENU_VIEW_ID = 2;
    private static final int STATE_CLOSE = 0;
    private static final int STATE_OPEN = 1;
    private final int MAX_VELOCITYX;
    private final int MIN_FLING;
    private boolean isFling;
    private int mBaseX;
    private Interpolator mCloseInterpolator;
    private ScrollerCompat mCloseScroller;
    private View mContentView;
    private int mDownX;
    private GestureDetectorCompat mGestureDetector;
    private GestureDetector$OnGestureListener mGestureListener;
    private SwipeMenuView mMenuView;
    private Interpolator mOpenInterpolator;
    private ScrollerCompat mOpenScroller;
    private int position;
    private int state;
    
    private SwipeMenuLayout(final Context context) {
        super(context);
        this.state = 0;
        this.MIN_FLING = this.dp2px(15);
        this.MAX_VELOCITYX = -this.dp2px(500);
    }
    
    private SwipeMenuLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.state = 0;
        this.MIN_FLING = this.dp2px(15);
        this.MAX_VELOCITYX = -this.dp2px(500);
    }
    
    public SwipeMenuLayout(final View view, final SwipeMenuView swipeMenuView) {
        this(view, swipeMenuView, null, null);
    }
    
    public SwipeMenuLayout(final View mContentView, final SwipeMenuView mMenuView, final Interpolator mCloseInterpolator, final Interpolator mOpenInterpolator) {
        super(mContentView.getContext());
        this.state = 0;
        this.MIN_FLING = this.dp2px(15);
        this.MAX_VELOCITYX = -this.dp2px(500);
        this.mCloseInterpolator = mCloseInterpolator;
        this.mOpenInterpolator = mOpenInterpolator;
        this.mContentView = mContentView;
        (this.mMenuView = mMenuView).setLayout(this);
        this.init();
    }
    
    private int dp2px(final int n) {
        return (int)TypedValue.applyDimension(1, (float)n, this.getContext().getResources().getDisplayMetrics());
    }
    
    private void init() {
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        this.mGestureListener = (GestureDetector$OnGestureListener)new GestureDetector$SimpleOnGestureListener(this) {
            final SwipeMenuLayout this$0;
            
            public boolean onDown(final MotionEvent motionEvent) {
                this.this$0.isFling = false;
                return true;
            }
            
            public boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
                if (motionEvent.getX() - motionEvent2.getX() > this.this$0.MIN_FLING && n < this.this$0.MAX_VELOCITYX) {
                    this.this$0.isFling = true;
                }
                return super.onFling(motionEvent, motionEvent2, n, n2);
            }
        };
        this.mGestureDetector = new GestureDetectorCompat(this.getContext(), this.mGestureListener);
        if (this.mCloseInterpolator != null) {
            this.mCloseScroller = ScrollerCompat.create(this.getContext(), this.mCloseInterpolator);
        }
        else {
            this.mCloseScroller = ScrollerCompat.create(this.getContext());
        }
        if (this.mOpenInterpolator != null) {
            this.mOpenScroller = ScrollerCompat.create(this.getContext(), this.mOpenInterpolator);
        }
        else {
            this.mOpenScroller = ScrollerCompat.create(this.getContext());
        }
        this.mContentView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -2));
        if (this.mContentView.getId() < 1) {
            this.mContentView.setId(1);
        }
        this.mMenuView.setId(2);
        this.mMenuView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        this.addView(this.mContentView);
        this.addView((View)this.mMenuView);
    }
    
    private void swipe(int n) {
        int width = n;
        if (n > this.mMenuView.getWidth()) {
            width = this.mMenuView.getWidth();
        }
        if ((n = width) < 0) {
            n = 0;
        }
        final View mContentView = this.mContentView;
        mContentView.layout(-n, mContentView.getTop(), this.mContentView.getWidth() - n, this.getMeasuredHeight());
        this.mMenuView.layout(this.mContentView.getWidth() - n, this.mMenuView.getTop(), this.mContentView.getWidth() + this.mMenuView.getWidth() - n, this.mMenuView.getBottom());
    }
    
    public void closeMenu() {
        if (this.mCloseScroller.computeScrollOffset()) {
            this.mCloseScroller.abortAnimation();
        }
        if (this.state == 1) {
            this.swipe(this.state = 0);
        }
    }
    
    public void computeScroll() {
        if (this.state == 1) {
            if (this.mOpenScroller.computeScrollOffset()) {
                this.swipe(this.mOpenScroller.getCurrX());
                this.postInvalidate();
            }
        }
        else if (this.mCloseScroller.computeScrollOffset()) {
            this.swipe(this.mBaseX - this.mCloseScroller.getCurrX());
            this.postInvalidate();
        }
    }
    
    public View getContentView() {
        return this.mContentView;
    }
    
    public SwipeMenuView getMenuView() {
        return this.mMenuView;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public boolean isOpen() {
        final int state = this.state;
        boolean b = true;
        if (state != 1) {
            b = false;
        }
        return b;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.mContentView.layout(0, 0, this.getMeasuredWidth(), this.mContentView.getMeasuredHeight());
        this.mMenuView.layout(this.getMeasuredWidth(), 0, this.getMeasuredWidth() + this.mMenuView.getMeasuredWidth(), this.mContentView.getMeasuredHeight());
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        this.mMenuView.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
    }
    
    public boolean onSwipe(final MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    int n = (int)(this.mDownX - motionEvent.getX());
                    if (this.state == 1) {
                        n += this.mMenuView.getWidth();
                    }
                    this.swipe(n);
                }
            }
            else {
                if (!this.isFling && this.mDownX - motionEvent.getX() <= this.mMenuView.getWidth() / 2) {
                    this.smoothCloseMenu();
                    return false;
                }
                this.smoothOpenMenu();
            }
        }
        else {
            this.mDownX = (int)motionEvent.getX();
            this.isFling = false;
        }
        return true;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }
    
    public void openMenu() {
        if (this.state == 0) {
            this.state = 1;
            this.swipe(this.mMenuView.getWidth());
        }
    }
    
    public void setMenuHeight(final int height) {
        final StringBuilder sb = new StringBuilder();
        sb.append("pos = ");
        sb.append(this.position);
        sb.append(", height = ");
        sb.append(height);
        Log.i("byz", sb.toString());
        final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)this.mMenuView.getLayoutParams();
        if (frameLayout$LayoutParams.height != height) {
            frameLayout$LayoutParams.height = height;
            final SwipeMenuView mMenuView = this.mMenuView;
            mMenuView.setLayoutParams(mMenuView.getLayoutParams());
        }
    }
    
    public void setPosition(final int n) {
        this.position = n;
        this.mMenuView.setPosition(n);
    }
    
    public void smoothCloseMenu() {
        this.state = 0;
        final int mBaseX = -this.mContentView.getLeft();
        this.mBaseX = mBaseX;
        this.mCloseScroller.startScroll(0, 0, mBaseX, 0, 350);
        this.postInvalidate();
    }
    
    public void smoothOpenMenu() {
        this.state = 1;
        this.mOpenScroller.startScroll(-this.mContentView.getLeft(), 0, this.mMenuView.getWidth(), 0, 350);
        this.postInvalidate();
    }
}
