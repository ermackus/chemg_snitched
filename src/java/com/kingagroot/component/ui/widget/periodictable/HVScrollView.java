package com.kingagroot.component.ui.widget.periodictable;

import java.util.List;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.View$MeasureSpec;
import android.view.KeyEvent;
import android.view.FocusFinder;
import android.view.ViewGroup$LayoutParams;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.view.ViewConfiguration;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import android.view.VelocityTracker;
import android.graphics.Rect;
import android.widget.Scroller;
import android.view.View;
import android.widget.FrameLayout;

public class HVScrollView extends FrameLayout
{
    static final int ANIMATED_SCROLL_GAP = 250;
    private static final int INVALID_POINTER = -1;
    static final float MAX_SCROLL_FACTOR = 0.5f;
    private int mActivePointerId;
    private View mChildToScrollTo;
    private boolean mFillViewport;
    private boolean mFlingEnabled;
    private boolean mIsBeingDragged;
    private boolean mIsLayoutDirty;
    private float mLastMotionX;
    private float mLastMotionY;
    private long mLastScroll;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mScrollViewMovedFocus;
    private Scroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    
    public HVScrollView(final Context context) {
        this(context, null);
    }
    
    public HVScrollView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mTempRect = new Rect();
        this.mIsLayoutDirty = true;
        this.mChildToScrollTo = null;
        this.mIsBeingDragged = false;
        this.mSmoothScrollingEnabled = true;
        this.mActivePointerId = -1;
        this.mFlingEnabled = true;
        this.initScrollView();
    }
    
    private boolean canScrollH() {
        final boolean b = false;
        final View child = this.getChildAt(0);
        boolean b2 = b;
        if (child != null) {
            final int width = child.getWidth();
            b2 = b;
            if (this.getWidth() < width + this.getPaddingLeft() + this.getPaddingRight()) {
                b2 = true;
            }
        }
        return b2;
    }
    
    private boolean canScrollV() {
        final boolean b = false;
        final View child = this.getChildAt(0);
        boolean b2 = b;
        if (child != null) {
            final int height = child.getHeight();
            b2 = b;
            if (this.getHeight() < height + this.getPaddingTop() + this.getPaddingBottom()) {
                b2 = true;
            }
        }
        return b2;
    }
    
    private int clamp(final int n, final int n2, final int n3) {
        if (n2 >= n3 || n < 0) {
            return 0;
        }
        if (n2 + n > n3) {
            return n3 - n2;
        }
        return n;
    }
    
    private void doScrollX(final int n) {
        if (n != 0) {
            if (this.mSmoothScrollingEnabled) {
                this.smoothScrollBy(n, 0);
            }
            else {
                this.scrollBy(n, 0);
            }
        }
    }
    
    private void doScrollY(final int n) {
        if (n != 0) {
            if (this.mSmoothScrollingEnabled) {
                this.smoothScrollBy(0, n);
            }
            else {
                this.scrollBy(0, n);
            }
        }
    }
    
    private View findFocusableViewInBoundsH(final boolean b, final int n, final int n2) {
        final ArrayList focusables = this.getFocusables(2);
        final int size = ((List)focusables).size();
        View view = null;
        int i = 0;
        int n3 = 0;
        while (i < size) {
            final View view2 = (View)((List)focusables).get(i);
            final int left = view2.getLeft();
            final int right = view2.getRight();
            View view3 = view;
            int n4 = n3;
            Label_0232: {
                if (n < right) {
                    view3 = view;
                    n4 = n3;
                    if (left < n2) {
                        final boolean b2 = n < left && right < n2;
                        if (view == null) {
                            view3 = view2;
                            n4 = (b2 ? 1 : 0);
                        }
                        else {
                            final boolean b3 = (b && left < view.getLeft()) || (!b && right > view.getRight());
                            if (n3 != 0) {
                                view3 = view;
                                n4 = n3;
                                if (!b2) {
                                    break Label_0232;
                                }
                                view3 = view;
                                n4 = n3;
                                if (!b3) {
                                    break Label_0232;
                                }
                            }
                            else {
                                if (b2) {
                                    view3 = view2;
                                    n4 = 1;
                                    break Label_0232;
                                }
                                view3 = view;
                                n4 = n3;
                                if (!b3) {
                                    break Label_0232;
                                }
                            }
                            view3 = view2;
                            n4 = n3;
                        }
                    }
                }
            }
            ++i;
            view = view3;
            n3 = n4;
        }
        return view;
    }
    
    private View findFocusableViewInBoundsV(final boolean b, final int n, final int n2) {
        final ArrayList focusables = this.getFocusables(2);
        final int size = ((List)focusables).size();
        View view = null;
        int i = 0;
        int n3 = 0;
        while (i < size) {
            final View view2 = (View)((List)focusables).get(i);
            final int top = view2.getTop();
            final int bottom = view2.getBottom();
            View view3 = view;
            int n4 = n3;
            Label_0232: {
                if (n < bottom) {
                    view3 = view;
                    n4 = n3;
                    if (top < n2) {
                        final boolean b2 = n < top && bottom < n2;
                        if (view == null) {
                            view3 = view2;
                            n4 = (b2 ? 1 : 0);
                        }
                        else {
                            final boolean b3 = (b && top < view.getTop()) || (!b && bottom > view.getBottom());
                            if (n3 != 0) {
                                view3 = view;
                                n4 = n3;
                                if (!b2) {
                                    break Label_0232;
                                }
                                view3 = view;
                                n4 = n3;
                                if (!b3) {
                                    break Label_0232;
                                }
                            }
                            else {
                                if (b2) {
                                    view3 = view2;
                                    n4 = 1;
                                    break Label_0232;
                                }
                                view3 = view;
                                n4 = n3;
                                if (!b3) {
                                    break Label_0232;
                                }
                            }
                            view3 = view2;
                            n4 = n3;
                        }
                    }
                }
            }
            ++i;
            view = view3;
            n3 = n4;
        }
        return view;
    }
    
    private boolean inChild(final int n, final int n2) {
        final int childCount = this.getChildCount();
        boolean b2;
        final boolean b = b2 = false;
        if (childCount > 0) {
            final int scrollX = this.getScrollX();
            final int scrollY = this.getScrollY();
            final View child = this.getChildAt(0);
            b2 = b;
            if (n2 >= child.getTop() - scrollY) {
                b2 = b;
                if (n2 < child.getBottom() - scrollY) {
                    b2 = b;
                    if (n >= child.getLeft() - scrollX) {
                        b2 = b;
                        if (n < child.getRight() - scrollX) {
                            b2 = true;
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    private void initScrollView() {
        this.mScroller = new Scroller(this.getContext());
        this.setFocusable(true);
        this.setDescendantFocusability(262144);
        this.setWillNotDraw(false);
        final ViewConfiguration value = ViewConfiguration.get(this.getContext());
        this.mTouchSlop = value.getScaledTouchSlop();
        this.mMinimumVelocity = value.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = value.getScaledMaximumFlingVelocity();
    }
    
    private boolean isOffScreenH(final View view) {
        return this.isWithinDeltaOfScreenH(view, 0) ^ true;
    }
    
    private boolean isOffScreenV(final View view) {
        return this.isWithinDeltaOfScreenV(view, 0, this.getHeight()) ^ true;
    }
    
    private boolean isViewDescendantOf(final View view, final View view2) {
        boolean b = true;
        if (view == view2) {
            return true;
        }
        final ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup) || !this.isViewDescendantOf((View)parent, view2)) {
            b = false;
        }
        return b;
    }
    
    private boolean isWithinDeltaOfScreenH(final View view, final int n) {
        view.getDrawingRect(this.mTempRect);
        this.offsetDescendantRectToMyCoords(view, this.mTempRect);
        return this.mTempRect.right + n >= this.getScrollX() && this.mTempRect.left - n <= this.getScrollX() + this.getWidth();
    }
    
    private boolean isWithinDeltaOfScreenV(final View view, final int n, final int n2) {
        view.getDrawingRect(this.mTempRect);
        this.offsetDescendantRectToMyCoords(view, this.mTempRect);
        return this.mTempRect.bottom + n >= this.getScrollY() && this.mTempRect.top - n <= this.getScrollY() + n2;
    }
    
    private void onSecondaryPointerUp(final MotionEvent motionEvent) {
        final int n = (motionEvent.getAction() & 0xFF00) >> 8;
        if (motionEvent.getPointerId(n) == this.mActivePointerId) {
            int n2;
            if (n == 0) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            this.mLastMotionX = motionEvent.getX(n2);
            this.mLastMotionY = motionEvent.getY(n2);
            this.mActivePointerId = motionEvent.getPointerId(n2);
            final VelocityTracker mVelocityTracker = this.mVelocityTracker;
            if (mVelocityTracker != null) {
                mVelocityTracker.clear();
            }
        }
    }
    
    private boolean scrollAndFocusH(final int n, int n2, final int n3) {
        final int width = this.getWidth();
        final int scrollX = this.getScrollX();
        final int n4 = width + scrollX;
        final boolean b = n == 17;
        Object focusableViewInBoundsH;
        if ((focusableViewInBoundsH = this.findFocusableViewInBoundsH(b, n2, n3)) == null) {
            focusableViewInBoundsH = this;
        }
        boolean b2;
        if (n2 >= scrollX && n3 <= n4) {
            b2 = false;
        }
        else {
            if (b) {
                n2 -= scrollX;
            }
            else {
                n2 = n3 - n4;
            }
            this.doScrollX(n2);
            b2 = true;
        }
        if (focusableViewInBoundsH != this.findFocus() && ((View)focusableViewInBoundsH).requestFocus(n)) {
            this.mScrollViewMovedFocus = true;
            this.mScrollViewMovedFocus = false;
        }
        return b2;
    }
    
    private boolean scrollAndFocusV(final int n, int n2, final int n3) {
        final int height = this.getHeight();
        final int scrollY = this.getScrollY();
        final int n4 = height + scrollY;
        final boolean b = n == 33;
        Object focusableViewInBoundsV;
        if ((focusableViewInBoundsV = this.findFocusableViewInBoundsV(b, n2, n3)) == null) {
            focusableViewInBoundsV = this;
        }
        boolean b2;
        if (n2 >= scrollY && n3 <= n4) {
            b2 = false;
        }
        else {
            if (b) {
                n2 -= scrollY;
            }
            else {
                n2 = n3 - n4;
            }
            this.doScrollY(n2);
            b2 = true;
        }
        if (focusableViewInBoundsV != this.findFocus() && ((View)focusableViewInBoundsV).requestFocus(n)) {
            this.mScrollViewMovedFocus = true;
            this.mScrollViewMovedFocus = false;
        }
        return b2;
    }
    
    private void scrollToChild(final View view) {
        view.getDrawingRect(this.mTempRect);
        this.offsetDescendantRectToMyCoords(view, this.mTempRect);
        final int computeScrollDeltaToGetChildRectOnScreenV = this.computeScrollDeltaToGetChildRectOnScreenV(this.mTempRect);
        final int computeScrollDeltaToGetChildRectOnScreenH = this.computeScrollDeltaToGetChildRectOnScreenH(this.mTempRect);
        if (computeScrollDeltaToGetChildRectOnScreenH != 0 || computeScrollDeltaToGetChildRectOnScreenV != 0) {
            this.scrollBy(computeScrollDeltaToGetChildRectOnScreenH, computeScrollDeltaToGetChildRectOnScreenV);
        }
    }
    
    private boolean scrollToChildRect(final Rect rect, final boolean b) {
        final int computeScrollDeltaToGetChildRectOnScreenV = this.computeScrollDeltaToGetChildRectOnScreenV(rect);
        final int computeScrollDeltaToGetChildRectOnScreenH = this.computeScrollDeltaToGetChildRectOnScreenH(rect);
        final boolean b2 = computeScrollDeltaToGetChildRectOnScreenH != 0 || computeScrollDeltaToGetChildRectOnScreenV != 0;
        if (b2) {
            if (b) {
                this.scrollBy(computeScrollDeltaToGetChildRectOnScreenH, computeScrollDeltaToGetChildRectOnScreenV);
            }
            else {
                this.smoothScrollBy(computeScrollDeltaToGetChildRectOnScreenH, computeScrollDeltaToGetChildRectOnScreenV);
            }
        }
        return b2;
    }
    
    public void addView(final View view) {
        if (this.getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
    
    public void addView(final View view, final int n) {
        if (this.getChildCount() <= 0) {
            super.addView(view, n);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (this.getChildCount() <= 0) {
            super.addView(view, n, viewGroup$LayoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
    
    public void addView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (this.getChildCount() <= 0) {
            super.addView(view, viewGroup$LayoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
    
    public boolean arrowScrollH(int descendantFocusability) {
        View focus;
        if ((focus = this.findFocus()) == this) {
            focus = null;
        }
        final View nextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup)this, focus, descendantFocusability);
        final int maxScrollAmountH = this.getMaxScrollAmountH();
        if (nextFocus != null && this.isWithinDeltaOfScreenH(nextFocus, maxScrollAmountH)) {
            nextFocus.getDrawingRect(this.mTempRect);
            this.offsetDescendantRectToMyCoords(nextFocus, this.mTempRect);
            this.doScrollX(this.computeScrollDeltaToGetChildRectOnScreenH(this.mTempRect));
            nextFocus.requestFocus(descendantFocusability);
        }
        else {
            int scrollX;
            if (descendantFocusability == 17 && this.getScrollX() < maxScrollAmountH) {
                scrollX = this.getScrollX();
            }
            else {
                scrollX = maxScrollAmountH;
                if (descendantFocusability == 66) {
                    scrollX = maxScrollAmountH;
                    if (this.getChildCount() > 0) {
                        final int n = this.getChildAt(0).getRight() - (this.getScrollX() + this.getWidth());
                        if (n < (scrollX = maxScrollAmountH)) {
                            scrollX = n;
                        }
                    }
                }
            }
            if (scrollX == 0) {
                return false;
            }
            if (descendantFocusability != 66) {
                scrollX = -scrollX;
            }
            this.doScrollX(scrollX);
        }
        if (focus != null && focus.isFocused() && this.isOffScreenH(focus)) {
            descendantFocusability = this.getDescendantFocusability();
            this.setDescendantFocusability(131072);
            this.requestFocus();
            this.setDescendantFocusability(descendantFocusability);
        }
        return true;
    }
    
    public boolean arrowScrollV(int descendantFocusability) {
        View focus;
        if ((focus = this.findFocus()) == this) {
            focus = null;
        }
        final View nextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup)this, focus, descendantFocusability);
        final int maxScrollAmountV = this.getMaxScrollAmountV();
        if (nextFocus != null && this.isWithinDeltaOfScreenV(nextFocus, maxScrollAmountV, this.getHeight())) {
            nextFocus.getDrawingRect(this.mTempRect);
            this.offsetDescendantRectToMyCoords(nextFocus, this.mTempRect);
            this.doScrollY(this.computeScrollDeltaToGetChildRectOnScreenV(this.mTempRect));
            nextFocus.requestFocus(descendantFocusability);
        }
        else {
            int scrollY;
            if (descendantFocusability == 33 && this.getScrollY() < maxScrollAmountV) {
                scrollY = this.getScrollY();
            }
            else {
                scrollY = maxScrollAmountV;
                if (descendantFocusability == 130) {
                    scrollY = maxScrollAmountV;
                    if (this.getChildCount() > 0) {
                        final int n = this.getChildAt(0).getBottom() - (this.getScrollY() + this.getHeight());
                        if (n < (scrollY = maxScrollAmountV)) {
                            scrollY = n;
                        }
                    }
                }
            }
            if (scrollY == 0) {
                return false;
            }
            if (descendantFocusability != 130) {
                scrollY = -scrollY;
            }
            this.doScrollY(scrollY);
        }
        if (focus != null && focus.isFocused() && this.isOffScreenV(focus)) {
            descendantFocusability = this.getDescendantFocusability();
            this.setDescendantFocusability(131072);
            this.requestFocus();
            this.setDescendantFocusability(descendantFocusability);
        }
        return true;
    }
    
    protected int computeHorizontalScrollOffset() {
        return Math.max(0, super.computeHorizontalScrollOffset());
    }
    
    protected int computeHorizontalScrollRange() {
        final int childCount = this.getChildCount();
        final int width = this.getWidth();
        final int paddingLeft = this.getPaddingLeft();
        final int paddingRight = this.getPaddingRight();
        if (childCount == 0) {
            return width - paddingLeft - paddingRight;
        }
        return this.getChildAt(0).getRight();
    }
    
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            final int currX = this.mScroller.getCurrX();
            final int currY = this.mScroller.getCurrY();
            if (this.getChildCount() > 0) {
                final View child = this.getChildAt(0);
                super.scrollTo(this.clamp(currX, this.getWidth() - this.getPaddingRight() - this.getPaddingLeft(), child.getWidth()), this.clamp(currY, this.getHeight() - this.getPaddingBottom() - this.getPaddingTop(), child.getHeight()));
            }
            this.awakenScrollBars();
            this.postInvalidate();
        }
    }
    
    protected int computeScrollDeltaToGetChildRectOnScreenH(final Rect rect) {
        final int childCount = this.getChildCount();
        final boolean b = false;
        if (childCount == 0) {
            return 0;
        }
        final int width = this.getWidth();
        final int scrollX = this.getScrollX();
        final int n = scrollX + width;
        final int horizontalFadingEdgeLength = this.getHorizontalFadingEdgeLength();
        int n2 = scrollX;
        if (rect.left > 0) {
            n2 = scrollX + horizontalFadingEdgeLength;
        }
        int n3 = n;
        if (rect.right < this.getChildAt(0).getWidth()) {
            n3 = n - horizontalFadingEdgeLength;
        }
        int n5;
        if (rect.right > n3 && rect.left > n2) {
            int n4;
            if (rect.width() > width) {
                n4 = rect.left - n2;
            }
            else {
                n4 = rect.right - n3;
            }
            n5 = Math.min(n4 + 0, this.getChildAt(0).getRight() - n3);
        }
        else {
            n5 = (b ? 1 : 0);
            if (rect.left < n2) {
                n5 = (b ? 1 : 0);
                if (rect.right < n3) {
                    int n6;
                    if (rect.width() > width) {
                        n6 = 0 - (n3 - rect.right);
                    }
                    else {
                        n6 = 0 - (n2 - rect.left);
                    }
                    n5 = Math.max(n6, -this.getScrollX());
                }
            }
        }
        return n5;
    }
    
    protected int computeScrollDeltaToGetChildRectOnScreenV(final Rect rect) {
        final int childCount = this.getChildCount();
        final boolean b = false;
        if (childCount == 0) {
            return 0;
        }
        final int height = this.getHeight();
        final int scrollY = this.getScrollY();
        final int n = scrollY + height;
        final int verticalFadingEdgeLength = this.getVerticalFadingEdgeLength();
        int n2 = scrollY;
        if (rect.top > 0) {
            n2 = scrollY + verticalFadingEdgeLength;
        }
        int n3 = n;
        if (rect.bottom < this.getChildAt(0).getHeight()) {
            n3 = n - verticalFadingEdgeLength;
        }
        int n5;
        if (rect.bottom > n3 && rect.top > n2) {
            int n4;
            if (rect.height() > height) {
                n4 = rect.top - n2;
            }
            else {
                n4 = rect.bottom - n3;
            }
            n5 = Math.min(n4 + 0, this.getChildAt(0).getBottom() - n3);
        }
        else {
            n5 = (b ? 1 : 0);
            if (rect.top < n2) {
                n5 = (b ? 1 : 0);
                if (rect.bottom < n3) {
                    int n6;
                    if (rect.height() > height) {
                        n6 = 0 - (n3 - rect.bottom);
                    }
                    else {
                        n6 = 0 - (n2 - rect.top);
                    }
                    n5 = Math.max(n6, -this.getScrollY());
                }
            }
        }
        return n5;
    }
    
    protected int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }
    
    protected int computeVerticalScrollRange() {
        final int childCount = this.getChildCount();
        final int height = this.getHeight();
        final int paddingBottom = this.getPaddingBottom();
        final int paddingTop = this.getPaddingTop();
        if (childCount == 0) {
            return height - paddingBottom - paddingTop;
        }
        return this.getChildAt(0).getBottom();
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || this.executeKeyEvent(keyEvent);
    }
    
    public boolean executeKeyEvent(final KeyEvent keyEvent) {
        this.mTempRect.setEmpty();
        if (keyEvent.getAction() == 0) {
            switch (keyEvent.getKeyCode()) {
                case 22: {
                    if (!this.canScrollH()) {
                        break;
                    }
                    if (!keyEvent.isAltPressed()) {
                        return this.arrowScrollH(66);
                    }
                    return this.fullScrollH(66);
                }
                case 21: {
                    if (!this.canScrollH()) {
                        break;
                    }
                    if (!keyEvent.isAltPressed()) {
                        return this.arrowScrollH(17);
                    }
                    return this.fullScrollH(17);
                }
                case 20: {
                    if (!this.canScrollV()) {
                        break;
                    }
                    if (!keyEvent.isAltPressed()) {
                        return this.arrowScrollV(130);
                    }
                    return this.fullScrollV(130);
                }
                case 19: {
                    if (!this.canScrollV()) {
                        break;
                    }
                    if (!keyEvent.isAltPressed()) {
                        return this.arrowScrollV(33);
                    }
                    return this.fullScrollV(33);
                }
            }
        }
        return false;
    }
    
    public void fling(final int n, final int n2) {
        if (this.getChildCount() > 0) {
            this.mScroller.fling(this.getScrollX(), this.getScrollY(), n, n2, 0, Math.max(0, this.getChildAt(0).getWidth() - (this.getWidth() - this.getPaddingRight() - this.getPaddingLeft())), 0, Math.max(0, this.getChildAt(0).getHeight() - (this.getHeight() - this.getPaddingBottom() - this.getPaddingTop())));
            this.invalidate();
        }
    }
    
    public boolean fullScrollH(final int n) {
        final boolean b = n == 66;
        final int width = this.getWidth();
        this.mTempRect.left = 0;
        this.mTempRect.right = width;
        if (b && this.getChildCount() > 0) {
            this.mTempRect.right = this.getChildAt(0).getRight();
            final Rect mTempRect = this.mTempRect;
            mTempRect.left = mTempRect.right - width;
        }
        return this.scrollAndFocusH(n, this.mTempRect.left, this.mTempRect.right);
    }
    
    public boolean fullScrollV(final int n) {
        final boolean b = n == 130;
        final int height = this.getHeight();
        this.mTempRect.top = 0;
        this.mTempRect.bottom = height;
        if (b) {
            final int childCount = this.getChildCount();
            if (childCount > 0) {
                this.mTempRect.bottom = this.getChildAt(childCount - 1).getBottom();
                final Rect mTempRect = this.mTempRect;
                mTempRect.top = mTempRect.bottom - height;
            }
        }
        return this.scrollAndFocusV(n, this.mTempRect.top, this.mTempRect.bottom);
    }
    
    protected float getBottomFadingEdgeStrength() {
        if (this.getChildCount() == 0) {
            return 0.0f;
        }
        final int verticalFadingEdgeLength = this.getVerticalFadingEdgeLength();
        final int n = this.getChildAt(0).getBottom() - this.getScrollY() - (this.getHeight() - this.getPaddingBottom());
        if (n < verticalFadingEdgeLength) {
            return n / (float)verticalFadingEdgeLength;
        }
        return 1.0f;
    }
    
    protected float getLeftFadingEdgeStrength() {
        if (this.getChildCount() == 0) {
            return 0.0f;
        }
        final int horizontalFadingEdgeLength = this.getHorizontalFadingEdgeLength();
        if (this.getScrollX() < horizontalFadingEdgeLength) {
            return this.getScrollX() / (float)horizontalFadingEdgeLength;
        }
        return 1.0f;
    }
    
    public int getMaxScrollAmountH() {
        return (int)((this.getRight() - this.getLeft()) * 0.5f);
    }
    
    public int getMaxScrollAmountV() {
        return (int)((this.getBottom() - this.getTop()) * 0.5f);
    }
    
    protected float getRightFadingEdgeStrength() {
        if (this.getChildCount() == 0) {
            return 0.0f;
        }
        final int horizontalFadingEdgeLength = this.getHorizontalFadingEdgeLength();
        final int n = this.getChildAt(0).getRight() - this.getScrollX() - (this.getWidth() - this.getPaddingRight());
        if (n < horizontalFadingEdgeLength) {
            return n / (float)horizontalFadingEdgeLength;
        }
        return 1.0f;
    }
    
    protected float getTopFadingEdgeStrength() {
        if (this.getChildCount() == 0) {
            return 0.0f;
        }
        final int verticalFadingEdgeLength = this.getVerticalFadingEdgeLength();
        if (this.getScrollY() < verticalFadingEdgeLength) {
            return this.getScrollY() / (float)verticalFadingEdgeLength;
        }
        return 1.0f;
    }
    
    public boolean isFillViewport() {
        return this.mFillViewport;
    }
    
    public boolean isFlingEnabled() {
        return this.mFlingEnabled;
    }
    
    public boolean isSmoothScrollingEnabled() {
        return this.mSmoothScrollingEnabled;
    }
    
    protected void measureChild(final View view, final int n, final int n2) {
        view.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
    }
    
    protected void measureChildWithMargins(final View view, final int n, final int n2, final int n3, final int n4) {
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        view.measure(View$MeasureSpec.makeMeasureSpec(viewGroup$MarginLayoutParams.leftMargin + viewGroup$MarginLayoutParams.rightMargin, 0), View$MeasureSpec.makeMeasureSpec(viewGroup$MarginLayoutParams.topMargin + viewGroup$MarginLayoutParams.bottomMargin, 0));
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (action == 2 && this.mIsBeingDragged) {
            return true;
        }
        final int n = action & 0xFF;
        if (n != 0) {
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n != 6) {
                            return this.mIsBeingDragged;
                        }
                        this.onSecondaryPointerUp(motionEvent);
                        return this.mIsBeingDragged;
                    }
                }
                else {
                    final int mActivePointerId = this.mActivePointerId;
                    if (mActivePointerId == -1) {
                        return this.mIsBeingDragged;
                    }
                    final int pointerIndex = motionEvent.findPointerIndex(mActivePointerId);
                    final float y = motionEvent.getY(pointerIndex);
                    if ((int)Math.abs(y - this.mLastMotionY) > this.mTouchSlop) {
                        this.mIsBeingDragged = true;
                        this.mLastMotionY = y;
                    }
                    final float x = motionEvent.getX(pointerIndex);
                    if ((int)Math.abs(x - this.mLastMotionX) > this.mTouchSlop) {
                        this.mIsBeingDragged = true;
                        this.mLastMotionX = x;
                        return this.mIsBeingDragged;
                    }
                    return this.mIsBeingDragged;
                }
            }
            this.mIsBeingDragged = false;
            this.mActivePointerId = -1;
        }
        else {
            final float x2 = motionEvent.getX();
            final float y2 = motionEvent.getY();
            if (!this.inChild((int)x2, (int)y2)) {
                this.mIsBeingDragged = false;
            }
            else {
                this.mLastMotionY = y2;
                this.mLastMotionX = x2;
                this.mActivePointerId = motionEvent.getPointerId(0);
                this.mIsBeingDragged = (this.mScroller.isFinished() ^ true);
            }
        }
        return this.mIsBeingDragged;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.mIsLayoutDirty = false;
        final View mChildToScrollTo = this.mChildToScrollTo;
        if (mChildToScrollTo != null && this.isViewDescendantOf(mChildToScrollTo, (View)this)) {
            this.scrollToChild(this.mChildToScrollTo);
        }
        this.mChildToScrollTo = null;
        this.scrollTo(this.getScrollX(), this.getScrollY());
    }
    
    protected void onMeasure(int n, int n2) {
        super.onMeasure(n, n2);
        if (!this.mFillViewport) {
            return;
        }
        n2 = View$MeasureSpec.getMode(n2);
        n = View$MeasureSpec.getMode(n);
        if (n2 == 0 && n == 0) {
            return;
        }
        if (this.getChildCount() > 0) {
            final View child = this.getChildAt(0);
            n = this.getMeasuredHeight();
            n2 = this.getMeasuredWidth();
            if (child.getMeasuredHeight() < n || child.getMeasuredWidth() < n2) {
                child.measure(View$MeasureSpec.makeMeasureSpec(n2 - this.getPaddingLeft() - this.getPaddingRight(), 1073741824), View$MeasureSpec.makeMeasureSpec(n - this.getPaddingTop() - this.getPaddingBottom(), 1073741824));
            }
        }
    }
    
    protected boolean onRequestFocusInDescendants(final int n, final Rect rect) {
        View view;
        if (rect == null) {
            view = FocusFinder.getInstance().findNextFocus((ViewGroup)this, (View)null, n);
        }
        else {
            view = FocusFinder.getInstance().findNextFocusFromRect((ViewGroup)this, rect, n);
        }
        return view != null && view.requestFocus(n, rect);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        final View focus = this.findFocus();
        if (focus != null) {
            if (this != focus) {
                if (this.isWithinDeltaOfScreenV(focus, 0, n4)) {
                    focus.getDrawingRect(this.mTempRect);
                    this.offsetDescendantRectToMyCoords(focus, this.mTempRect);
                    this.doScrollY(this.computeScrollDeltaToGetChildRectOnScreenV(this.mTempRect));
                }
                if (this.isWithinDeltaOfScreenH(focus, this.getRight() - this.getLeft())) {
                    focus.getDrawingRect(this.mTempRect);
                    this.offsetDescendantRectToMyCoords(focus, this.mTempRect);
                    this.doScrollX(this.computeScrollDeltaToGetChildRectOnScreenH(this.mTempRect));
                }
            }
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        final int n = motionEvent.getAction() & 0xFF;
        if (n != 0) {
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n == 6) {
                            this.onSecondaryPointerUp(motionEvent);
                        }
                    }
                    else if (this.mIsBeingDragged && this.getChildCount() > 0) {
                        this.mActivePointerId = -1;
                        this.mIsBeingDragged = false;
                        final VelocityTracker mVelocityTracker = this.mVelocityTracker;
                        if (mVelocityTracker != null) {
                            mVelocityTracker.recycle();
                            this.mVelocityTracker = null;
                        }
                    }
                }
                else if (this.mIsBeingDragged) {
                    final int pointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    final float y = motionEvent.getY(pointerIndex);
                    final int n2 = (int)(this.mLastMotionY - y);
                    this.mLastMotionY = y;
                    final float x = motionEvent.getX(pointerIndex);
                    final int n3 = (int)(this.mLastMotionX - x);
                    this.mLastMotionX = x;
                    this.scrollBy(n3, n2);
                }
            }
            else if (this.mIsBeingDragged) {
                if (this.mFlingEnabled) {
                    final VelocityTracker mVelocityTracker2 = this.mVelocityTracker;
                    mVelocityTracker2.computeCurrentVelocity(1000, (float)this.mMaximumVelocity);
                    final int n4 = (int)mVelocityTracker2.getXVelocity();
                    final int n5 = (int)mVelocityTracker2.getYVelocity();
                    if (this.getChildCount() > 0 && (Math.abs(n4) > n4 || Math.abs(n5) > this.mMinimumVelocity)) {
                        this.fling(-n4, -n5);
                    }
                }
                this.mActivePointerId = -1;
                this.mIsBeingDragged = false;
                final VelocityTracker mVelocityTracker3 = this.mVelocityTracker;
                if (mVelocityTracker3 != null) {
                    mVelocityTracker3.recycle();
                    this.mVelocityTracker = null;
                }
            }
        }
        else {
            final float x2 = motionEvent.getX();
            final float y2 = motionEvent.getY();
            if (!(this.mIsBeingDragged = this.inChild((int)x2, (int)y2))) {
                return false;
            }
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
            }
            this.mLastMotionY = y2;
            this.mLastMotionX = x2;
            this.mActivePointerId = motionEvent.getPointerId(0);
        }
        return true;
    }
    
    public void requestChildFocus(final View view, final View mChildToScrollTo) {
        if (!this.mScrollViewMovedFocus) {
            if (!this.mIsLayoutDirty) {
                this.scrollToChild(mChildToScrollTo);
            }
            else {
                this.mChildToScrollTo = mChildToScrollTo;
            }
        }
        super.requestChildFocus(view, mChildToScrollTo);
    }
    
    public boolean requestChildRectangleOnScreen(final View view, final Rect rect, final boolean b) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return this.scrollToChildRect(rect, b);
    }
    
    public void requestLayout() {
        this.mIsLayoutDirty = true;
        super.requestLayout();
    }
    
    public void scrollTo(int clamp, int clamp2) {
        if (this.getChildCount() > 0) {
            final View child = this.getChildAt(0);
            clamp = this.clamp(clamp, this.getWidth() - this.getPaddingRight() - this.getPaddingLeft(), child.getWidth());
            clamp2 = this.clamp(clamp2, this.getHeight() - this.getPaddingBottom() - this.getPaddingTop(), child.getHeight());
            if (clamp != this.getScrollX() || clamp2 != this.getScrollY()) {
                super.scrollTo(clamp, clamp2);
            }
        }
    }
    
    public void setFillViewport(final boolean mFillViewport) {
        if (mFillViewport != this.mFillViewport) {
            this.mFillViewport = mFillViewport;
            this.requestLayout();
        }
    }
    
    public void setFlingEnabled(final boolean mFlingEnabled) {
        this.mFlingEnabled = mFlingEnabled;
    }
    
    public void setSmoothScrollingEnabled(final boolean mSmoothScrollingEnabled) {
        this.mSmoothScrollingEnabled = mSmoothScrollingEnabled;
    }
    
    public void smoothScrollBy(int max, int max2) {
        if (this.getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250L) {
            final int max3 = Math.max(0, this.getChildAt(0).getHeight() - (this.getHeight() - this.getPaddingBottom() - this.getPaddingTop()));
            final int scrollY = this.getScrollY();
            max2 = Math.max(0, Math.min(max2 + scrollY, max3));
            final int max4 = Math.max(0, this.getChildAt(0).getWidth() - (this.getWidth() - this.getPaddingRight() - this.getPaddingLeft()));
            final int scrollX = this.getScrollX();
            max = Math.max(0, Math.min(max + scrollX, max4));
            this.mScroller.startScroll(scrollX, scrollY, max - scrollX, max2 - scrollY);
            this.invalidate();
        }
        else {
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
            }
            this.scrollBy(max, max2);
        }
        this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
    }
    
    public final void smoothScrollTo(final int n, final int n2) {
        this.smoothScrollBy(n - this.getScrollX(), n2 - this.getScrollY());
    }
}
