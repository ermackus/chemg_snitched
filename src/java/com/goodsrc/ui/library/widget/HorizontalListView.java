package com.goodsrc.ui.library.widget;

import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView$OnItemLongClickListener;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.view.ViewCompat;
import android.widget.Adapter;
import android.content.res.TypedArray;
import com.goodsrc.ui.library.R;
import android.widget.ScrollView;
import android.widget.ListView;
import android.view.View$MeasureSpec;
import java.util.LinkedList;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.os.Build$VERSION;
import android.view.GestureDetector$OnGestureListener;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import java.util.Queue;
import java.util.List;
import android.graphics.Rect;
import android.view.View$OnClickListener;
import android.view.GestureDetector;
import android.widget.Scroller;
import androidx.core.widget.EdgeEffectCompat;
import android.graphics.drawable.Drawable;
import android.database.DataSetObserver;
import android.widget.ListAdapter;
import android.widget.AdapterView;

public class HorizontalListView extends AdapterView<ListAdapter>
{
    private static final String BUNDLE_ID_CURRENT_X = "BUNDLE_ID_CURRENT_X";
    private static final String BUNDLE_ID_PARENT_STATE = "BUNDLE_ID_PARENT_STATE";
    private static final float FLING_DEFAULT_ABSORB_VELOCITY = 30.0f;
    private static final float FLING_FRICTION = 0.009f;
    private static final int INSERT_AT_END_OF_LIST = -1;
    private static final int INSERT_AT_START_OF_LIST = 0;
    protected ListAdapter mAdapter;
    private final DataSetObserver mAdapterDataObserver;
    private boolean mBlockTouchAction;
    private ScrollState mCurrentScrollState;
    protected int mCurrentX;
    private int mCurrentlySelectedAdapterIndex;
    private boolean mDataChanged;
    private final Runnable mDelayedLayout;
    private int mDisplayOffset;
    private Drawable mDivider;
    private int mDividerWidth;
    private final EdgeEffectCompat mEdgeGlowLeft;
    private final EdgeEffectCompat mEdgeGlowRight;
    protected Scroller mFlingTracker;
    private final GestureDetector mGestureDetector;
    private final GestureListener mGestureListener;
    private boolean mHasNotifiedRunningLowOnData;
    private int mHeightMeasureSpec;
    private boolean mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent;
    private int mLeftViewAdapterIndex;
    private int mMaxX;
    protected int mNextX;
    private View$OnClickListener mOnClickListener;
    private OnScrollStateChangedListener mOnScrollStateChangedListener;
    private final Rect mRect;
    private final List<Queue<View>> mRemovedViewsCache;
    private Integer mRestoreX;
    private int mRightViewAdapterIndex;
    private RunningOutOfDataListener mRunningOutOfDataListener;
    private int mRunningOutOfDataThreshold;
    private View mViewBeingTouched;
    
    public HorizontalListView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mFlingTracker = new Scroller(this.getContext());
        this.mGestureListener = new GestureListener();
        this.mRemovedViewsCache = (List<Queue<View>>)new ArrayList();
        this.mDataChanged = false;
        this.mRect = new Rect();
        this.mViewBeingTouched = null;
        this.mDividerWidth = 0;
        this.mDivider = null;
        this.mRestoreX = null;
        this.mMaxX = Integer.MAX_VALUE;
        this.mRunningOutOfDataListener = null;
        this.mRunningOutOfDataThreshold = 0;
        this.mHasNotifiedRunningLowOnData = false;
        this.mOnScrollStateChangedListener = null;
        this.mCurrentScrollState = ScrollState.SCROLL_STATE_IDLE;
        this.mBlockTouchAction = false;
        this.mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent = false;
        this.mAdapterDataObserver = new DataSetObserver() {
            final HorizontalListView this$0;
            
            public void onChanged() {
                this.this$0.mDataChanged = true;
                this.this$0.mHasNotifiedRunningLowOnData = false;
                this.this$0.unpressTouchedChild();
                this.this$0.invalidate();
                this.this$0.requestLayout();
            }
            
            public void onInvalidated() {
                this.this$0.mHasNotifiedRunningLowOnData = false;
                this.this$0.unpressTouchedChild();
                this.this$0.reset();
                this.this$0.invalidate();
                this.this$0.requestLayout();
            }
        };
        this.mDelayedLayout = (Runnable)new Runnable() {
            final HorizontalListView this$0;
            
            public void run() {
                this.this$0.requestLayout();
            }
        };
        this.mEdgeGlowLeft = new EdgeEffectCompat(context);
        this.mEdgeGlowRight = new EdgeEffectCompat(context);
        this.mGestureDetector = new GestureDetector(context, (GestureDetector$OnGestureListener)this.mGestureListener);
        this.bindGestureDetector();
        this.initView();
        this.retrieveXmlConfiguration(context, set);
        this.setWillNotDraw(false);
        if (Build$VERSION.SDK_INT >= 11) {
            HoneycombPlus.setFriction(this.mFlingTracker, 0.009f);
        }
    }
    
    private void addAndMeasureChild(final View view, final int n) {
        this.addViewInLayout(view, n, this.getLayoutParams(view), true);
        this.measureChild(view);
    }
    
    private void bindGestureDetector() {
        this.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener(this) {
            final HorizontalListView this$0;
            
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                return this.this$0.mGestureDetector.onTouchEvent(motionEvent);
            }
        });
    }
    
    private float determineFlingAbsorbVelocity() {
        if (Build$VERSION.SDK_INT >= 14) {
            return IceCreamSandwichPlus.getCurrVelocity(this.mFlingTracker);
        }
        return 30.0f;
    }
    
    private void determineIfLowOnData() {
        if (this.mRunningOutOfDataListener != null) {
            final ListAdapter mAdapter = this.mAdapter;
            if (mAdapter != null && mAdapter.getCount() - (this.mRightViewAdapterIndex + 1) < this.mRunningOutOfDataThreshold && !this.mHasNotifiedRunningLowOnData) {
                this.mHasNotifiedRunningLowOnData = true;
                this.mRunningOutOfDataListener.onRunningOutOfData();
            }
        }
    }
    
    private boolean determineMaxX() {
        final boolean lastItemInAdapter = this.isLastItemInAdapter(this.mRightViewAdapterIndex);
        boolean b2;
        final boolean b = b2 = false;
        if (lastItemInAdapter) {
            final View rightmostChild = this.getRightmostChild();
            b2 = b;
            if (rightmostChild != null) {
                final int mMaxX = this.mMaxX;
                if ((this.mMaxX = this.mCurrentX + (rightmostChild.getRight() - this.getPaddingLeft()) - this.getRenderWidth()) < 0) {
                    this.mMaxX = 0;
                }
                b2 = b;
                if (this.mMaxX != mMaxX) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    private void drawDivider(final Canvas canvas, final Rect bounds) {
        final Drawable mDivider = this.mDivider;
        if (mDivider != null) {
            mDivider.setBounds(bounds);
            this.mDivider.draw(canvas);
        }
    }
    
    private void drawDividers(final Canvas canvas) {
        final int childCount = this.getChildCount();
        final Rect mRect = this.mRect;
        mRect.top = this.getPaddingTop();
        final Rect mRect2 = this.mRect;
        mRect2.bottom = mRect2.top + this.getRenderHeight();
        for (int i = 0; i < childCount; ++i) {
            if (i != childCount - 1 || !this.isLastItemInAdapter(this.mRightViewAdapterIndex)) {
                final View child = this.getChildAt(i);
                mRect.left = child.getRight();
                mRect.right = child.getRight() + this.mDividerWidth;
                if (mRect.left < this.getPaddingLeft()) {
                    mRect.left = this.getPaddingLeft();
                }
                if (mRect.right > this.getWidth() - this.getPaddingRight()) {
                    mRect.right = this.getWidth() - this.getPaddingRight();
                }
                this.drawDivider(canvas, mRect);
                if (i == 0 && child.getLeft() > this.getPaddingLeft()) {
                    mRect.left = this.getPaddingLeft();
                    mRect.right = child.getLeft();
                    this.drawDivider(canvas, mRect);
                }
            }
        }
    }
    
    private void drawEdgeGlow(final Canvas canvas) {
        final EdgeEffectCompat mEdgeGlowLeft = this.mEdgeGlowLeft;
        if (mEdgeGlowLeft != null && !mEdgeGlowLeft.isFinished() && this.isEdgeGlowEnabled()) {
            final int save = canvas.save();
            final int height = this.getHeight();
            canvas.rotate(-90.0f, 0.0f, 0.0f);
            canvas.translate((float)(-height + this.getPaddingBottom()), 0.0f);
            this.mEdgeGlowLeft.setSize(this.getRenderHeight(), this.getRenderWidth());
            if (this.mEdgeGlowLeft.draw(canvas)) {
                this.invalidate();
            }
            canvas.restoreToCount(save);
        }
        else {
            final EdgeEffectCompat mEdgeGlowRight = this.mEdgeGlowRight;
            if (mEdgeGlowRight != null && !mEdgeGlowRight.isFinished() && this.isEdgeGlowEnabled()) {
                final int save2 = canvas.save();
                final int width = this.getWidth();
                canvas.rotate(90.0f, 0.0f, 0.0f);
                canvas.translate((float)this.getPaddingTop(), (float)(-width));
                this.mEdgeGlowRight.setSize(this.getRenderHeight(), this.getRenderWidth());
                if (this.mEdgeGlowRight.draw(canvas)) {
                    this.invalidate();
                }
                canvas.restoreToCount(save2);
            }
        }
    }
    
    private void fillList(final int n) {
        final View rightmostChild = this.getRightmostChild();
        final int n2 = 0;
        int right;
        if (rightmostChild != null) {
            right = rightmostChild.getRight();
        }
        else {
            right = 0;
        }
        this.fillListRight(right, n);
        final View leftmostChild = this.getLeftmostChild();
        int left = n2;
        if (leftmostChild != null) {
            left = leftmostChild.getLeft();
        }
        this.fillListLeft(left, n);
    }
    
    private void fillListLeft(int n, final int n2) {
        while (n + n2 - this.mDividerWidth > 0) {
            int mLeftViewAdapterIndex = this.mLeftViewAdapterIndex;
            if (mLeftViewAdapterIndex < 1) {
                break;
            }
            --mLeftViewAdapterIndex;
            this.mLeftViewAdapterIndex = mLeftViewAdapterIndex;
            final View view = this.mAdapter.getView(mLeftViewAdapterIndex, this.getRecycledView(mLeftViewAdapterIndex), (ViewGroup)this);
            this.addAndMeasureChild(view, 0);
            int measuredWidth;
            if (this.mLeftViewAdapterIndex == 0) {
                measuredWidth = view.getMeasuredWidth();
            }
            else {
                measuredWidth = this.mDividerWidth + view.getMeasuredWidth();
            }
            final int n3 = n - measuredWidth;
            final int mDisplayOffset = this.mDisplayOffset;
            if (n3 + n2 == 0) {
                n = view.getMeasuredWidth();
            }
            else {
                n = this.mDividerWidth;
                n += view.getMeasuredWidth();
            }
            this.mDisplayOffset = mDisplayOffset - n;
            n = n3;
        }
    }
    
    private void fillListRight(int n, final int n2) {
        while (n + n2 + this.mDividerWidth < this.getWidth() && this.mRightViewAdapterIndex + 1 < this.mAdapter.getCount()) {
            final int n3 = this.mRightViewAdapterIndex + 1;
            this.mRightViewAdapterIndex = n3;
            if (this.mLeftViewAdapterIndex < 0) {
                this.mLeftViewAdapterIndex = n3;
            }
            final ListAdapter mAdapter = this.mAdapter;
            final int mRightViewAdapterIndex = this.mRightViewAdapterIndex;
            final View view = mAdapter.getView(mRightViewAdapterIndex, this.getRecycledView(mRightViewAdapterIndex), (ViewGroup)this);
            this.addAndMeasureChild(view, -1);
            int mDividerWidth;
            if (this.mRightViewAdapterIndex == 0) {
                mDividerWidth = 0;
            }
            else {
                mDividerWidth = this.mDividerWidth;
            }
            n += mDividerWidth + view.getMeasuredWidth();
            this.determineIfLowOnData();
        }
    }
    
    private View getChild(final int n) {
        final int mLeftViewAdapterIndex = this.mLeftViewAdapterIndex;
        if (n >= mLeftViewAdapterIndex && n <= this.mRightViewAdapterIndex) {
            return this.getChildAt(n - mLeftViewAdapterIndex);
        }
        return null;
    }
    
    private int getChildIndex(final int n, final int n2) {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            this.getChildAt(i).getHitRect(this.mRect);
            if (this.mRect.contains(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    private ViewGroup$LayoutParams getLayoutParams(final View view) {
        ViewGroup$LayoutParams layoutParams;
        if ((layoutParams = view.getLayoutParams()) == null) {
            layoutParams = new ViewGroup$LayoutParams(-2, -1);
        }
        return layoutParams;
    }
    
    private View getLeftmostChild() {
        return this.getChildAt(0);
    }
    
    private View getRecycledView(int itemViewType) {
        itemViewType = this.mAdapter.getItemViewType(itemViewType);
        if (this.isItemViewTypeValid(itemViewType)) {
            return (View)((Queue)this.mRemovedViewsCache.get(itemViewType)).poll();
        }
        return null;
    }
    
    private int getRenderHeight() {
        return this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
    }
    
    private int getRenderWidth() {
        return this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
    }
    
    private View getRightmostChild() {
        return this.getChildAt(this.getChildCount() - 1);
    }
    
    private void initView() {
        this.mLeftViewAdapterIndex = -1;
        this.mRightViewAdapterIndex = -1;
        this.mDisplayOffset = 0;
        this.mCurrentX = 0;
        this.mNextX = 0;
        this.mMaxX = Integer.MAX_VALUE;
        this.setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
    }
    
    private void initializeRecycledViewCache(final int n) {
        this.mRemovedViewsCache.clear();
        for (int i = 0; i < n; ++i) {
            this.mRemovedViewsCache.add((Object)new LinkedList());
        }
    }
    
    private boolean isEdgeGlowEnabled() {
        final ListAdapter mAdapter = this.mAdapter;
        boolean b2;
        final boolean b = b2 = false;
        if (mAdapter != null) {
            if (mAdapter.isEmpty()) {
                b2 = b;
            }
            else {
                b2 = b;
                if (this.mMaxX > 0) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    private boolean isItemViewTypeValid(final int n) {
        return n < this.mRemovedViewsCache.size();
    }
    
    private boolean isLastItemInAdapter(final int n) {
        final int count = this.mAdapter.getCount();
        boolean b = true;
        if (n != count - 1) {
            b = false;
        }
        return b;
    }
    
    private void measureChild(final View view) {
        final ViewGroup$LayoutParams layoutParams = this.getLayoutParams(view);
        final int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.mHeightMeasureSpec, this.getPaddingTop() + this.getPaddingBottom(), layoutParams.height);
        int n;
        if (layoutParams.width > 0) {
            n = View$MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
        }
        else {
            n = View$MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(n, childMeasureSpec);
    }
    
    private void positionChildren(int i) {
        final int childCount = this.getChildCount();
        if (childCount > 0) {
            int mDisplayOffset = this.mDisplayOffset + i;
            this.mDisplayOffset = mDisplayOffset;
            View child;
            int n;
            int paddingTop;
            for (i = 0; i < childCount; ++i) {
                child = this.getChildAt(i);
                n = this.getPaddingLeft() + mDisplayOffset;
                paddingTop = this.getPaddingTop();
                child.layout(n, paddingTop, child.getMeasuredWidth() + n, child.getMeasuredHeight() + paddingTop);
                mDisplayOffset += child.getMeasuredWidth() + this.mDividerWidth;
            }
        }
    }
    
    private void recycleView(int itemViewType, final View view) {
        itemViewType = this.mAdapter.getItemViewType(itemViewType);
        if (this.isItemViewTypeValid(itemViewType)) {
            ((Queue)this.mRemovedViewsCache.get(itemViewType)).offer((Object)view);
        }
    }
    
    private void releaseEdgeGlow() {
        final EdgeEffectCompat mEdgeGlowLeft = this.mEdgeGlowLeft;
        if (mEdgeGlowLeft != null) {
            mEdgeGlowLeft.onRelease();
        }
        final EdgeEffectCompat mEdgeGlowRight = this.mEdgeGlowRight;
        if (mEdgeGlowRight != null) {
            mEdgeGlowRight.onRelease();
        }
    }
    
    private void removeNonVisibleChildren(final int n) {
        for (View view = this.getLeftmostChild(); view != null && view.getRight() + n <= 0; view = this.getLeftmostChild()) {
            final int mDisplayOffset = this.mDisplayOffset;
            int measuredWidth;
            if (this.isLastItemInAdapter(this.mLeftViewAdapterIndex)) {
                measuredWidth = view.getMeasuredWidth();
            }
            else {
                measuredWidth = this.mDividerWidth + view.getMeasuredWidth();
            }
            this.mDisplayOffset = mDisplayOffset + measuredWidth;
            this.recycleView(this.mLeftViewAdapterIndex, view);
            this.removeViewInLayout(view);
            ++this.mLeftViewAdapterIndex;
        }
        for (View view2 = this.getRightmostChild(); view2 != null && view2.getLeft() + n >= this.getWidth(); view2 = this.getRightmostChild()) {
            this.recycleView(this.mRightViewAdapterIndex, view2);
            this.removeViewInLayout(view2);
            --this.mRightViewAdapterIndex;
        }
    }
    
    private void requestParentListViewToNotInterceptTouchEvents(final Boolean b) {
        if (this.mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent != b) {
            for (Object o = this; ((View)o).getParent() instanceof View; o = ((View)o).getParent()) {
                if (((View)o).getParent() instanceof ListView || ((View)o).getParent() instanceof ScrollView) {
                    ((View)o).getParent().requestDisallowInterceptTouchEvent((boolean)b);
                    this.mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent = b;
                    break;
                }
            }
        }
    }
    
    private void reset() {
        this.initView();
        this.removeAllViewsInLayout();
        this.requestLayout();
    }
    
    private void retrieveXmlConfiguration(final Context context, final AttributeSet set) {
        if (set != null) {
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.HorizontalListView);
            final Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.HorizontalListView_android_divider);
            if (drawable != null) {
                this.setDivider(drawable);
            }
            final int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.HorizontalListView_dividerWidth, 0);
            if (dimensionPixelSize != 0) {
                this.setDividerWidth(dimensionPixelSize);
            }
            obtainStyledAttributes.recycle();
        }
    }
    
    private void setCurrentScrollState(final ScrollState mCurrentScrollState) {
        if (this.mCurrentScrollState != mCurrentScrollState) {
            final OnScrollStateChangedListener mOnScrollStateChangedListener = this.mOnScrollStateChangedListener;
            if (mOnScrollStateChangedListener != null) {
                mOnScrollStateChangedListener.onScrollStateChanged(mCurrentScrollState);
            }
        }
        this.mCurrentScrollState = mCurrentScrollState;
    }
    
    private void unpressTouchedChild() {
        final View mViewBeingTouched = this.mViewBeingTouched;
        if (mViewBeingTouched != null) {
            mViewBeingTouched.setPressed(false);
            this.refreshDrawableState();
            this.mViewBeingTouched = null;
        }
    }
    
    private void updateOverscrollAnimation(int n) {
        if (this.mEdgeGlowLeft != null) {
            if (this.mEdgeGlowRight != null) {
                final int n2 = this.mCurrentX + n;
                final Scroller mFlingTracker = this.mFlingTracker;
                if (mFlingTracker == null || mFlingTracker.isFinished()) {
                    if (n2 < 0) {
                        n = Math.abs(n);
                        this.mEdgeGlowLeft.onPull(n / (float)this.getRenderWidth());
                        if (!this.mEdgeGlowRight.isFinished()) {
                            this.mEdgeGlowRight.onRelease();
                        }
                    }
                    else if (n2 > this.mMaxX) {
                        n = Math.abs(n);
                        this.mEdgeGlowRight.onPull(n / (float)this.getRenderWidth());
                        if (!this.mEdgeGlowLeft.isFinished()) {
                            this.mEdgeGlowLeft.onRelease();
                        }
                    }
                }
            }
        }
    }
    
    protected void dispatchDraw(final Canvas canvas) {
        super.dispatchDraw(canvas);
        this.drawEdgeGlow(canvas);
    }
    
    protected void dispatchSetPressed(final boolean b) {
    }
    
    public ListAdapter getAdapter() {
        return this.mAdapter;
    }
    
    public int getFirstVisiblePosition() {
        return this.mLeftViewAdapterIndex;
    }
    
    public int getLastVisiblePosition() {
        return this.mRightViewAdapterIndex;
    }
    
    protected float getLeftFadingEdgeStrength() {
        final int horizontalFadingEdgeLength = this.getHorizontalFadingEdgeLength();
        final int mCurrentX = this.mCurrentX;
        if (mCurrentX == 0) {
            return 0.0f;
        }
        if (mCurrentX < horizontalFadingEdgeLength) {
            return mCurrentX / (float)horizontalFadingEdgeLength;
        }
        return 1.0f;
    }
    
    protected float getRightFadingEdgeStrength() {
        final int horizontalFadingEdgeLength = this.getHorizontalFadingEdgeLength();
        final int mCurrentX = this.mCurrentX;
        final int mMaxX = this.mMaxX;
        if (mCurrentX == mMaxX) {
            return 0.0f;
        }
        if (mMaxX - mCurrentX < horizontalFadingEdgeLength) {
            return (mMaxX - mCurrentX) / (float)horizontalFadingEdgeLength;
        }
        return 1.0f;
    }
    
    public View getSelectedView() {
        return this.getChild(this.mCurrentlySelectedAdapterIndex);
    }
    
    protected boolean onDown(final MotionEvent motionEvent) {
        this.mBlockTouchAction = (this.mFlingTracker.isFinished() ^ true);
        this.mFlingTracker.forceFinished(true);
        this.setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
        this.unpressTouchedChild();
        if (!this.mBlockTouchAction) {
            final int childIndex = this.getChildIndex((int)motionEvent.getX(), (int)motionEvent.getY());
            if (childIndex >= 0) {
                final View child = this.getChildAt(childIndex);
                if ((this.mViewBeingTouched = child) != null) {
                    child.setPressed(true);
                    this.refreshDrawableState();
                }
            }
        }
        return true;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        this.drawDividers(canvas);
    }
    
    protected boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
        this.mFlingTracker.fling(this.mNextX, 0, (int)(-n), 0, 0, this.mMaxX, 0, 0);
        this.setCurrentScrollState(ScrollState.SCROLL_STATE_FLING);
        this.requestLayout();
        return true;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (this.mAdapter == null) {
            return;
        }
        this.invalidate();
        if (this.mDataChanged) {
            final int mCurrentX = this.mCurrentX;
            this.initView();
            this.removeAllViewsInLayout();
            this.mNextX = mCurrentX;
            this.mDataChanged = false;
        }
        final Integer mRestoreX = this.mRestoreX;
        if (mRestoreX != null) {
            this.mNextX = mRestoreX;
            this.mRestoreX = null;
        }
        if (this.mFlingTracker.computeScrollOffset()) {
            this.mNextX = this.mFlingTracker.getCurrX();
        }
        final int mNextX = this.mNextX;
        if (mNextX < 0) {
            this.mNextX = 0;
            if (this.mEdgeGlowLeft.isFinished()) {
                this.mEdgeGlowLeft.onAbsorb((int)this.determineFlingAbsorbVelocity());
            }
            this.mFlingTracker.forceFinished(true);
            this.setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
        }
        else {
            final int mMaxX = this.mMaxX;
            if (mNextX > mMaxX) {
                this.mNextX = mMaxX;
                if (this.mEdgeGlowRight.isFinished()) {
                    this.mEdgeGlowRight.onAbsorb((int)this.determineFlingAbsorbVelocity());
                }
                this.mFlingTracker.forceFinished(true);
                this.setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
            }
        }
        final int n5 = this.mCurrentX - this.mNextX;
        this.removeNonVisibleChildren(n5);
        this.fillList(n5);
        this.positionChildren(n5);
        this.mCurrentX = this.mNextX;
        if (this.determineMaxX()) {
            this.onLayout(b, n, n2, n3, n4);
            return;
        }
        if (this.mFlingTracker.isFinished()) {
            if (this.mCurrentScrollState == ScrollState.SCROLL_STATE_FLING) {
                this.setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
            }
        }
        else {
            ViewCompat.postOnAnimation((View)this, this.mDelayedLayout);
        }
    }
    
    protected void onMeasure(final int n, final int mHeightMeasureSpec) {
        super.onMeasure(n, mHeightMeasureSpec);
        this.mHeightMeasureSpec = mHeightMeasureSpec;
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            final Bundle bundle = (Bundle)parcelable;
            this.mRestoreX = bundle.getInt("BUNDLE_ID_CURRENT_X");
            super.onRestoreInstanceState(bundle.getParcelable("BUNDLE_ID_PARENT_STATE"));
        }
    }
    
    public Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable("BUNDLE_ID_PARENT_STATE", super.onSaveInstanceState());
        bundle.putInt("BUNDLE_ID_CURRENT_X", this.mCurrentX);
        return (Parcelable)bundle;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        final Boolean value = false;
        if (action == 1) {
            final Scroller mFlingTracker = this.mFlingTracker;
            if (mFlingTracker == null || mFlingTracker.isFinished()) {
                this.setCurrentScrollState(ScrollState.SCROLL_STATE_IDLE);
            }
            this.requestParentListViewToNotInterceptTouchEvents(value);
            this.releaseEdgeGlow();
        }
        else if (motionEvent.getAction() == 3) {
            this.unpressTouchedChild();
            this.releaseEdgeGlow();
            this.requestParentListViewToNotInterceptTouchEvents(value);
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void scrollTo(final int n) {
        final Scroller mFlingTracker = this.mFlingTracker;
        final int mNextX = this.mNextX;
        mFlingTracker.startScroll(mNextX, 0, n - mNextX, 0);
        this.setCurrentScrollState(ScrollState.SCROLL_STATE_FLING);
        this.requestLayout();
    }
    
    public void setAdapter(final ListAdapter mAdapter) {
        final ListAdapter mAdapter2 = this.mAdapter;
        if (mAdapter2 != null) {
            mAdapter2.unregisterDataSetObserver(this.mAdapterDataObserver);
        }
        if (mAdapter != null) {
            this.mHasNotifiedRunningLowOnData = false;
            (this.mAdapter = mAdapter).registerDataSetObserver(this.mAdapterDataObserver);
        }
        this.initializeRecycledViewCache(this.mAdapter.getViewTypeCount());
        this.reset();
    }
    
    public void setDivider(final Drawable mDivider) {
        this.mDivider = mDivider;
        if (mDivider != null) {
            this.setDividerWidth(mDivider.getIntrinsicWidth());
        }
        else {
            this.setDividerWidth(0);
        }
    }
    
    public void setDividerWidth(final int mDividerWidth) {
        this.mDividerWidth = mDividerWidth;
        this.requestLayout();
        this.invalidate();
    }
    
    public void setOnClickListener(final View$OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }
    
    public void setOnScrollStateChangedListener(final OnScrollStateChangedListener mOnScrollStateChangedListener) {
        this.mOnScrollStateChangedListener = mOnScrollStateChangedListener;
    }
    
    public void setRunningOutOfDataListener(final RunningOutOfDataListener mRunningOutOfDataListener, final int mRunningOutOfDataThreshold) {
        this.mRunningOutOfDataListener = mRunningOutOfDataListener;
        this.mRunningOutOfDataThreshold = mRunningOutOfDataThreshold;
    }
    
    public void setSelection(final int mCurrentlySelectedAdapterIndex) {
        this.mCurrentlySelectedAdapterIndex = mCurrentlySelectedAdapterIndex;
    }
    
    private class GestureListener extends GestureDetector$SimpleOnGestureListener
    {
        final HorizontalListView this$0;
        
        private GestureListener(final HorizontalListView this$0) {
            this.this$0 = this$0;
        }
        
        public boolean onDown(final MotionEvent motionEvent) {
            return this.this$0.onDown(motionEvent);
        }
        
        public boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
            return this.this$0.onFling(motionEvent, motionEvent2, n, n2);
        }
        
        public void onLongPress(final MotionEvent motionEvent) {
            this.this$0.unpressTouchedChild();
            final int access$900 = this.this$0.getChildIndex((int)motionEvent.getX(), (int)motionEvent.getY());
            if (access$900 >= 0 && !this.this$0.mBlockTouchAction) {
                final View child = this.this$0.getChildAt(access$900);
                final AdapterView$OnItemLongClickListener onItemLongClickListener = this.this$0.getOnItemLongClickListener();
                if (onItemLongClickListener != null) {
                    final int n = this.this$0.mLeftViewAdapterIndex + access$900;
                    final HorizontalListView this$0 = this.this$0;
                    if (onItemLongClickListener.onItemLongClick((AdapterView)this$0, child, n, this$0.mAdapter.getItemId(n))) {
                        this.this$0.performHapticFeedback(0);
                    }
                }
            }
        }
        
        public boolean onScroll(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
            this.this$0.requestParentListViewToNotInterceptTouchEvents(true);
            this.this$0.setCurrentScrollState(ScrollState.SCROLL_STATE_TOUCH_SCROLL);
            this.this$0.unpressTouchedChild();
            final HorizontalListView this$0 = this.this$0;
            this$0.mNextX += (int)n;
            this.this$0.updateOverscrollAnimation(Math.round(n));
            this.this$0.requestLayout();
            return true;
        }
        
        public boolean onSingleTapConfirmed(final MotionEvent motionEvent) {
            this.this$0.unpressTouchedChild();
            final AdapterView$OnItemClickListener onItemClickListener = this.this$0.getOnItemClickListener();
            final int access$900 = this.this$0.getChildIndex((int)motionEvent.getX(), (int)motionEvent.getY());
            if (access$900 >= 0 && !this.this$0.mBlockTouchAction) {
                final View child = this.this$0.getChildAt(access$900);
                final int n = this.this$0.mLeftViewAdapterIndex + access$900;
                if (onItemClickListener != null) {
                    final HorizontalListView this$0 = this.this$0;
                    onItemClickListener.onItemClick((AdapterView)this$0, child, n, this$0.mAdapter.getItemId(n));
                    return true;
                }
            }
            if (this.this$0.mOnClickListener != null && !this.this$0.mBlockTouchAction) {
                this.this$0.mOnClickListener.onClick((View)this.this$0);
            }
            return false;
        }
    }
    
    private static final class HoneycombPlus
    {
        static {
            if (Build$VERSION.SDK_INT >= 11) {
                return;
            }
            throw new RuntimeException("Should not get to HoneycombPlus class unless sdk is >= 11!");
        }
        
        public static void setFriction(final Scroller scroller, final float friction) {
            if (scroller != null) {
                scroller.setFriction(friction);
            }
        }
    }
    
    private static final class IceCreamSandwichPlus
    {
        static {
            if (Build$VERSION.SDK_INT >= 14) {
                return;
            }
            throw new RuntimeException("Should not get to IceCreamSandwichPlus class unless sdk is >= 14!");
        }
        
        public static float getCurrVelocity(final Scroller scroller) {
            return scroller.getCurrVelocity();
        }
    }
    
    public interface OnScrollStateChangedListener
    {
        void onScrollStateChanged(final ScrollState p0);
        
        public enum ScrollState
        {
            private static final ScrollState[] $VALUES;
            
            SCROLL_STATE_FLING, 
            SCROLL_STATE_IDLE, 
            SCROLL_STATE_TOUCH_SCROLL;
        }
    }
    
    public interface RunningOutOfDataListener
    {
        void onRunningOutOfData();
    }
}
