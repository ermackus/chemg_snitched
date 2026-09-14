package com.luck.picture.lib.photoview;

import android.content.Context;
import android.widget.OverScroller;
import android.view.ViewParent;
import android.graphics.Matrix$ScaleToFit;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector$OnDoubleTapListener;
import android.view.GestureDetector$OnGestureListener;
import android.view.View;
import android.view.MotionEvent;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView$ScaleType;
import android.view.View$OnClickListener;
import android.view.View$OnLongClickListener;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.view.GestureDetector;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.view.View$OnLayoutChangeListener;
import android.view.View$OnTouchListener;

public class PhotoViewAttacher implements View$OnTouchListener, View$OnLayoutChangeListener
{
    private static final float DEFAULT_MAX_SCALE = 3.0f;
    private static final float DEFAULT_MID_SCALE = 1.75f;
    private static final float DEFAULT_MIN_SCALE = 1.0f;
    private static final int DEFAULT_ZOOM_DURATION = 200;
    private static final int HORIZONTAL_EDGE_BOTH = 2;
    private static final int HORIZONTAL_EDGE_LEFT = 0;
    private static final int HORIZONTAL_EDGE_NONE = -1;
    private static final int HORIZONTAL_EDGE_RIGHT = 1;
    private static final int SINGLE_TOUCH = 1;
    private static final int VERTICAL_EDGE_BOTH = 2;
    private static final int VERTICAL_EDGE_BOTTOM = 1;
    private static final int VERTICAL_EDGE_NONE = -1;
    private static final int VERTICAL_EDGE_TOP = 0;
    private boolean mAllowParentInterceptOnEdge;
    private final Matrix mBaseMatrix;
    private float mBaseRotation;
    private boolean mBlockParentIntercept;
    private FlingRunnable mCurrentFlingRunnable;
    private final RectF mDisplayRect;
    private final Matrix mDrawMatrix;
    private GestureDetector mGestureDetector;
    private int mHorizontalScrollEdge;
    private final ImageView mImageView;
    private Interpolator mInterpolator;
    private View$OnLongClickListener mLongClickListener;
    private OnMatrixChangedListener mMatrixChangeListener;
    private final float[] mMatrixValues;
    private float mMaxScale;
    private float mMidScale;
    private float mMinScale;
    private View$OnClickListener mOnClickListener;
    private OnViewDragListener mOnViewDragListener;
    private OnOutsidePhotoTapListener mOutsidePhotoTapListener;
    private OnPhotoTapListener mPhotoTapListener;
    private OnScaleChangedListener mScaleChangeListener;
    private CustomGestureDetector mScaleDragDetector;
    private ImageView$ScaleType mScaleType;
    private OnSingleFlingListener mSingleFlingListener;
    private final Matrix mSuppMatrix;
    private int mVerticalScrollEdge;
    private OnViewTapListener mViewTapListener;
    private int mZoomDuration;
    private boolean mZoomEnabled;
    private final OnGestureListener onGestureListener;
    
    public PhotoViewAttacher(final ImageView mImageView) {
        this.mInterpolator = (Interpolator)new AccelerateDecelerateInterpolator();
        this.mZoomDuration = 200;
        this.mMinScale = 1.0f;
        this.mMidScale = 1.75f;
        this.mMaxScale = 3.0f;
        this.mAllowParentInterceptOnEdge = true;
        this.mBlockParentIntercept = false;
        this.mBaseMatrix = new Matrix();
        this.mDrawMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDisplayRect = new RectF();
        this.mMatrixValues = new float[9];
        this.mHorizontalScrollEdge = 2;
        this.mVerticalScrollEdge = 2;
        this.mZoomEnabled = true;
        this.mScaleType = ImageView$ScaleType.FIT_CENTER;
        this.onGestureListener = (OnGestureListener)new PhotoViewAttacher$1(this);
        (this.mImageView = mImageView).setOnTouchListener((View$OnTouchListener)this);
        mImageView.addOnLayoutChangeListener((View$OnLayoutChangeListener)this);
        if (mImageView.isInEditMode()) {
            return;
        }
        this.mBaseRotation = 0.0f;
        this.mScaleDragDetector = new CustomGestureDetector(mImageView.getContext(), this.onGestureListener);
        (this.mGestureDetector = new GestureDetector(mImageView.getContext(), (GestureDetector$OnGestureListener)new GestureDetector$SimpleOnGestureListener(this) {
            final PhotoViewAttacher this$0;
            
            public boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
                if (this.this$0.mSingleFlingListener != null) {
                    if (this.this$0.getScale() > 1.0f) {
                        return false;
                    }
                    if (motionEvent.getPointerCount() <= 1) {
                        if (motionEvent2.getPointerCount() <= 1) {
                            return this.this$0.mSingleFlingListener.onFling(motionEvent, motionEvent2, n, n2);
                        }
                    }
                }
                return false;
            }
            
            public void onLongPress(final MotionEvent motionEvent) {
                if (this.this$0.mLongClickListener != null) {
                    this.this$0.mLongClickListener.onLongClick((View)this.this$0.mImageView);
                }
            }
        })).setOnDoubleTapListener((GestureDetector$OnDoubleTapListener)new GestureDetector$OnDoubleTapListener(this) {
            final PhotoViewAttacher this$0;
            
            public boolean onDoubleTap(final MotionEvent motionEvent) {
                try {
                    final float scale = this.this$0.getScale();
                    final float x = motionEvent.getX();
                    final float y = motionEvent.getY();
                    if (scale < this.this$0.getMediumScale()) {
                        this.this$0.setScale(this.this$0.getMediumScale(), x, y, true);
                    }
                    else if (scale >= this.this$0.getMediumScale() && scale < this.this$0.getMaximumScale()) {
                        this.this$0.setScale(this.this$0.getMaximumScale(), x, y, true);
                    }
                    else {
                        this.this$0.setScale(this.this$0.getMinimumScale(), x, y, true);
                    }
                    return true;
                }
                catch (final ArrayIndexOutOfBoundsException ex) {
                    return true;
                }
            }
            
            public boolean onDoubleTapEvent(final MotionEvent motionEvent) {
                return false;
            }
            
            public boolean onSingleTapConfirmed(final MotionEvent motionEvent) {
                if (this.this$0.mOnClickListener != null) {
                    this.this$0.mOnClickListener.onClick((View)this.this$0.mImageView);
                }
                final RectF displayRect = this.this$0.getDisplayRect();
                final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                if (this.this$0.mViewTapListener != null) {
                    this.this$0.mViewTapListener.onViewTap((View)this.this$0.mImageView, x, y);
                }
                if (displayRect != null) {
                    if (displayRect.contains(x, y)) {
                        final float n = (x - displayRect.left) / displayRect.width();
                        final float n2 = (y - displayRect.top) / displayRect.height();
                        if (this.this$0.mPhotoTapListener != null) {
                            this.this$0.mPhotoTapListener.onPhotoTap(this.this$0.mImageView, n, n2);
                        }
                        return true;
                    }
                    if (this.this$0.mOutsidePhotoTapListener != null) {
                        this.this$0.mOutsidePhotoTapListener.onOutsidePhotoTap(this.this$0.mImageView);
                    }
                }
                return false;
            }
        });
    }
    
    private void cancelFling() {
        final FlingRunnable mCurrentFlingRunnable = this.mCurrentFlingRunnable;
        if (mCurrentFlingRunnable != null) {
            mCurrentFlingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }
    
    private void checkAndDisplayMatrix() {
        if (this.checkMatrixBounds()) {
            this.setImageViewMatrix(this.getDrawMatrix());
        }
    }
    
    private boolean checkMatrixBounds() {
        final RectF displayRect = this.getDisplayRect(this.getDrawMatrix());
        if (displayRect == null) {
            return false;
        }
        final float height = displayRect.height();
        final float width = displayRect.width();
        final float n = (float)this.getImageViewHeight(this.mImageView);
        final float n2 = 0.0f;
        float n6;
        if (height <= n) {
            final int n3 = PhotoViewAttacher$4.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (n3 != 2) {
                float n4;
                float n5;
                if (n3 != 3) {
                    n4 = (n - height) / 2.0f;
                    n5 = displayRect.top;
                }
                else {
                    n4 = n - height;
                    n5 = displayRect.top;
                }
                n6 = n4 - n5;
            }
            else {
                n6 = -displayRect.top;
            }
            this.mVerticalScrollEdge = 2;
        }
        else if (displayRect.top > 0.0f) {
            this.mVerticalScrollEdge = 0;
            n6 = -displayRect.top;
        }
        else if (displayRect.bottom < n) {
            this.mVerticalScrollEdge = 1;
            n6 = n - displayRect.bottom;
        }
        else {
            this.mVerticalScrollEdge = -1;
            n6 = 0.0f;
        }
        final float n7 = (float)this.getImageViewWidth(this.mImageView);
        float n11;
        if (width <= n7) {
            final int n8 = PhotoViewAttacher$4.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (n8 != 2) {
                float n9;
                float n10;
                if (n8 != 3) {
                    n9 = (n7 - width) / 2.0f;
                    n10 = displayRect.left;
                }
                else {
                    n9 = n7 - width;
                    n10 = displayRect.left;
                }
                n11 = n9 - n10;
            }
            else {
                n11 = -displayRect.left;
            }
            this.mHorizontalScrollEdge = 2;
        }
        else if (displayRect.left > 0.0f) {
            this.mHorizontalScrollEdge = 0;
            n11 = -displayRect.left;
        }
        else if (displayRect.right < n7) {
            n11 = n7 - displayRect.right;
            this.mHorizontalScrollEdge = 1;
        }
        else {
            this.mHorizontalScrollEdge = -1;
            n11 = n2;
        }
        this.mSuppMatrix.postTranslate(n11, n6);
        return true;
    }
    
    private RectF getDisplayRect(final Matrix matrix) {
        final Drawable drawable = this.mImageView.getDrawable();
        if (drawable != null) {
            this.mDisplayRect.set(0.0f, 0.0f, (float)drawable.getIntrinsicWidth(), (float)drawable.getIntrinsicHeight());
            matrix.mapRect(this.mDisplayRect);
            return this.mDisplayRect;
        }
        return null;
    }
    
    private Matrix getDrawMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }
    
    private int getImageViewHeight(final ImageView imageView) {
        return imageView.getHeight() - imageView.getPaddingTop() - imageView.getPaddingBottom();
    }
    
    private int getImageViewWidth(final ImageView imageView) {
        return imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
    }
    
    private float getValue(final Matrix matrix, final int n) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[n];
    }
    
    private void resetMatrix() {
        this.mSuppMatrix.reset();
        this.setRotationBy(this.mBaseRotation);
        this.setImageViewMatrix(this.getDrawMatrix());
        this.checkMatrixBounds();
    }
    
    private void setImageViewMatrix(final Matrix imageMatrix) {
        this.mImageView.setImageMatrix(imageMatrix);
        if (this.mMatrixChangeListener != null) {
            final RectF displayRect = this.getDisplayRect(imageMatrix);
            if (displayRect != null) {
                this.mMatrixChangeListener.onMatrixChanged(displayRect);
            }
        }
    }
    
    private void updateBaseMatrix(final Drawable drawable) {
        if (drawable == null) {
            return;
        }
        final float n = (float)this.getImageViewWidth(this.mImageView);
        final float n2 = (float)this.getImageViewHeight(this.mImageView);
        final int intrinsicWidth = drawable.getIntrinsicWidth();
        final int intrinsicHeight = drawable.getIntrinsicHeight();
        this.mBaseMatrix.reset();
        final float n3 = (float)intrinsicWidth;
        final float n4 = n / n3;
        final float n5 = (float)intrinsicHeight;
        final float n6 = n2 / n5;
        if (this.mScaleType == ImageView$ScaleType.CENTER) {
            this.mBaseMatrix.postTranslate((n - n3) / 2.0f, (n2 - n5) / 2.0f);
        }
        else if (this.mScaleType == ImageView$ScaleType.CENTER_CROP) {
            final float max = Math.max(n4, n6);
            this.mBaseMatrix.postScale(max, max);
            this.mBaseMatrix.postTranslate((n - n3 * max) / 2.0f, (n2 - n5 * max) / 2.0f);
        }
        else if (this.mScaleType == ImageView$ScaleType.CENTER_INSIDE) {
            final float min = Math.min(1.0f, Math.min(n4, n6));
            this.mBaseMatrix.postScale(min, min);
            this.mBaseMatrix.postTranslate((n - n3 * min) / 2.0f, (n2 - n5 * min) / 2.0f);
        }
        else {
            RectF rectF = new RectF(0.0f, 0.0f, n3, n5);
            final RectF rectF2 = new RectF(0.0f, 0.0f, n, n2);
            if ((int)this.mBaseRotation % 180 != 0) {
                rectF = new RectF(0.0f, 0.0f, n5, n3);
            }
            final int n7 = PhotoViewAttacher$4.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (n7 != 1) {
                if (n7 != 2) {
                    if (n7 != 3) {
                        if (n7 == 4) {
                            this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix$ScaleToFit.FILL);
                        }
                    }
                    else {
                        this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix$ScaleToFit.END);
                    }
                }
                else {
                    this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix$ScaleToFit.START);
                }
            }
            else {
                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix$ScaleToFit.CENTER);
            }
        }
        this.resetMatrix();
    }
    
    public void getDisplayMatrix(final Matrix matrix) {
        matrix.set(this.getDrawMatrix());
    }
    
    public RectF getDisplayRect() {
        this.checkMatrixBounds();
        return this.getDisplayRect(this.getDrawMatrix());
    }
    
    public Matrix getImageMatrix() {
        return this.mDrawMatrix;
    }
    
    public float getMaximumScale() {
        return this.mMaxScale;
    }
    
    public float getMediumScale() {
        return this.mMidScale;
    }
    
    public float getMinimumScale() {
        return this.mMinScale;
    }
    
    public float getScale() {
        return (float)Math.sqrt((double)((float)Math.pow((double)this.getValue(this.mSuppMatrix, 0), 2.0) + (float)Math.pow((double)this.getValue(this.mSuppMatrix, 3), 2.0)));
    }
    
    public ImageView$ScaleType getScaleType() {
        return this.mScaleType;
    }
    
    public void getSuppMatrix(final Matrix matrix) {
        matrix.set(this.mSuppMatrix);
    }
    
    @Deprecated
    public boolean isZoomEnabled() {
        return this.mZoomEnabled;
    }
    
    public boolean isZoomable() {
        return this.mZoomEnabled;
    }
    
    public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (n != n5 || n2 != n6 || n3 != n7 || n4 != n8) {
            this.updateBaseMatrix(this.mImageView.getDrawable());
        }
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final boolean mZoomEnabled = this.mZoomEnabled;
        final boolean b = false;
        final boolean b2 = false;
        boolean b3 = b;
        if (mZoomEnabled) {
            b3 = b;
            if (Util.hasDrawable((ImageView)view)) {
                final int action = motionEvent.getAction();
                boolean b4 = false;
                Label_0197: {
                    Label_0194: {
                        if (action != 0) {
                            if (action == 1 || action == 3) {
                                if (this.getScale() < this.mMinScale) {
                                    final RectF displayRect = this.getDisplayRect();
                                    if (displayRect == null) {
                                        break Label_0194;
                                    }
                                    view.post((Runnable)new AnimatedZoomRunnable(this.getScale(), this.mMinScale, displayRect.centerX(), displayRect.centerY()));
                                }
                                else {
                                    if (this.getScale() <= this.mMaxScale) {
                                        break Label_0194;
                                    }
                                    final RectF displayRect2 = this.getDisplayRect();
                                    if (displayRect2 == null) {
                                        break Label_0194;
                                    }
                                    view.post((Runnable)new AnimatedZoomRunnable(this.getScale(), this.mMaxScale, displayRect2.centerX(), displayRect2.centerY()));
                                }
                                b4 = true;
                                break Label_0197;
                            }
                        }
                        else {
                            final ViewParent parent = view.getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                            this.cancelFling();
                        }
                    }
                    b4 = false;
                }
                final CustomGestureDetector mScaleDragDetector = this.mScaleDragDetector;
                boolean b7;
                if (mScaleDragDetector != null) {
                    final boolean scaling = mScaleDragDetector.isScaling();
                    final boolean dragging = this.mScaleDragDetector.isDragging();
                    final boolean onTouchEvent = this.mScaleDragDetector.onTouchEvent(motionEvent);
                    final boolean b5 = !scaling && !this.mScaleDragDetector.isScaling();
                    final boolean b6 = !dragging && !this.mScaleDragDetector.isDragging();
                    boolean mBlockParentIntercept = b2;
                    if (b5) {
                        mBlockParentIntercept = b2;
                        if (b6) {
                            mBlockParentIntercept = true;
                        }
                    }
                    this.mBlockParentIntercept = mBlockParentIntercept;
                    b7 = onTouchEvent;
                }
                else {
                    b7 = b4;
                }
                final GestureDetector mGestureDetector = this.mGestureDetector;
                b3 = b7;
                if (mGestureDetector != null) {
                    b3 = b7;
                    if (mGestureDetector.onTouchEvent(motionEvent)) {
                        b3 = true;
                    }
                }
            }
        }
        return b3;
    }
    
    public void setAllowParentInterceptOnEdge(final boolean mAllowParentInterceptOnEdge) {
        this.mAllowParentInterceptOnEdge = mAllowParentInterceptOnEdge;
    }
    
    public void setBaseRotation(final float n) {
        this.mBaseRotation = n % 360.0f;
        this.update();
        this.setRotationBy(this.mBaseRotation);
        this.checkAndDisplayMatrix();
    }
    
    public boolean setDisplayMatrix(final Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        if (this.mImageView.getDrawable() == null) {
            return false;
        }
        this.mSuppMatrix.set(matrix);
        this.checkAndDisplayMatrix();
        return true;
    }
    
    public void setMaximumScale(final float mMaxScale) {
        Util.checkZoomLevels(this.mMinScale, this.mMidScale, mMaxScale);
        this.mMaxScale = mMaxScale;
    }
    
    public void setMediumScale(final float mMidScale) {
        Util.checkZoomLevels(this.mMinScale, mMidScale, this.mMaxScale);
        this.mMidScale = mMidScale;
    }
    
    public void setMinimumScale(final float mMinScale) {
        Util.checkZoomLevels(mMinScale, this.mMidScale, this.mMaxScale);
        this.mMinScale = mMinScale;
    }
    
    public void setOnClickListener(final View$OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }
    
    public void setOnDoubleTapListener(final GestureDetector$OnDoubleTapListener onDoubleTapListener) {
        this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener);
    }
    
    public void setOnLongClickListener(final View$OnLongClickListener mLongClickListener) {
        this.mLongClickListener = mLongClickListener;
    }
    
    public void setOnMatrixChangeListener(final OnMatrixChangedListener mMatrixChangeListener) {
        this.mMatrixChangeListener = mMatrixChangeListener;
    }
    
    public void setOnOutsidePhotoTapListener(final OnOutsidePhotoTapListener mOutsidePhotoTapListener) {
        this.mOutsidePhotoTapListener = mOutsidePhotoTapListener;
    }
    
    public void setOnPhotoTapListener(final OnPhotoTapListener mPhotoTapListener) {
        this.mPhotoTapListener = mPhotoTapListener;
    }
    
    public void setOnScaleChangeListener(final OnScaleChangedListener mScaleChangeListener) {
        this.mScaleChangeListener = mScaleChangeListener;
    }
    
    public void setOnSingleFlingListener(final OnSingleFlingListener mSingleFlingListener) {
        this.mSingleFlingListener = mSingleFlingListener;
    }
    
    public void setOnViewDragListener(final OnViewDragListener mOnViewDragListener) {
        this.mOnViewDragListener = mOnViewDragListener;
    }
    
    public void setOnViewTapListener(final OnViewTapListener mViewTapListener) {
        this.mViewTapListener = mViewTapListener;
    }
    
    public void setRotationBy(final float n) {
        this.mSuppMatrix.postRotate(n % 360.0f);
        this.checkAndDisplayMatrix();
    }
    
    public void setRotationTo(final float n) {
        this.mSuppMatrix.setRotate(n % 360.0f);
        this.checkAndDisplayMatrix();
    }
    
    public void setScale(final float n) {
        this.setScale(n, false);
    }
    
    public void setScale(final float n, final float n2, final float n3, final boolean b) {
        if (n >= this.mMinScale && n <= this.mMaxScale) {
            if (b) {
                this.mImageView.post((Runnable)new AnimatedZoomRunnable(this.getScale(), n, n2, n3));
            }
            else {
                this.mSuppMatrix.setScale(n, n, n2, n3);
                this.checkAndDisplayMatrix();
            }
            return;
        }
        throw new IllegalArgumentException("Scale must be within the range of minScale and maxScale");
    }
    
    public void setScale(final float n, final boolean b) {
        this.setScale(n, (float)(this.mImageView.getRight() / 2), (float)(this.mImageView.getBottom() / 2), b);
    }
    
    public void setScaleLevels(final float mMinScale, final float mMidScale, final float mMaxScale) {
        Util.checkZoomLevels(mMinScale, mMidScale, mMaxScale);
        this.mMinScale = mMinScale;
        this.mMidScale = mMidScale;
        this.mMaxScale = mMaxScale;
    }
    
    public void setScaleType(final ImageView$ScaleType mScaleType) {
        if (Util.isSupportedScaleType(mScaleType) && mScaleType != this.mScaleType) {
            this.mScaleType = mScaleType;
            this.update();
        }
    }
    
    public void setZoomInterpolator(final Interpolator mInterpolator) {
        this.mInterpolator = mInterpolator;
    }
    
    public void setZoomTransitionDuration(final int mZoomDuration) {
        this.mZoomDuration = mZoomDuration;
    }
    
    public void setZoomable(final boolean mZoomEnabled) {
        this.mZoomEnabled = mZoomEnabled;
        this.update();
    }
    
    public void update() {
        if (this.mZoomEnabled) {
            this.updateBaseMatrix(this.mImageView.getDrawable());
        }
        else {
            this.resetMatrix();
        }
    }
    
    private class AnimatedZoomRunnable implements Runnable
    {
        private final float mFocalX;
        private final float mFocalY;
        private final long mStartTime;
        private final float mZoomEnd;
        private final float mZoomStart;
        final PhotoViewAttacher this$0;
        
        public AnimatedZoomRunnable(final PhotoViewAttacher this$0, final float mZoomStart, final float mZoomEnd, final float mFocalX, final float mFocalY) {
            this.this$0 = this$0;
            this.mFocalX = mFocalX;
            this.mFocalY = mFocalY;
            this.mStartTime = System.currentTimeMillis();
            this.mZoomStart = mZoomStart;
            this.mZoomEnd = mZoomEnd;
        }
        
        private float interpolate() {
            return this.this$0.mInterpolator.getInterpolation(Math.min(1.0f, (System.currentTimeMillis() - this.mStartTime) * 1.0f / this.this$0.mZoomDuration));
        }
        
        public void run() {
            final float interpolate = this.interpolate();
            final float mZoomStart = this.mZoomStart;
            this.this$0.onGestureListener.onScale((mZoomStart + (this.mZoomEnd - mZoomStart) * interpolate) / this.this$0.getScale(), this.mFocalX, this.mFocalY);
            if (interpolate < 1.0f) {
                Compat.postOnAnimation((View)this.this$0.mImageView, (Runnable)this);
            }
        }
    }
    
    private class FlingRunnable implements Runnable
    {
        private int mCurrentX;
        private int mCurrentY;
        private final OverScroller mScroller;
        final PhotoViewAttacher this$0;
        
        public FlingRunnable(final PhotoViewAttacher this$0, final Context context) {
            this.this$0 = this$0;
            this.mScroller = new OverScroller(context);
        }
        
        public void cancelFling() {
            this.mScroller.forceFinished(true);
        }
        
        public void fling(int n, int round, final int n2, final int n3) {
            final RectF displayRect = this.this$0.getDisplayRect();
            if (displayRect == null) {
                return;
            }
            final int round2 = Math.round(-displayRect.left);
            final float n4 = (float)n;
            int round3;
            int n5;
            if (n4 < displayRect.width()) {
                round3 = Math.round(displayRect.width() - n4);
                n5 = 0;
            }
            else {
                n = (n5 = (round3 = round2));
            }
            final int round4 = Math.round(-displayRect.top);
            final float n6 = (float)round;
            if (n6 < displayRect.height()) {
                round = Math.round(displayRect.height() - n6);
                n = 0;
            }
            else {
                n = (round = round4);
            }
            this.mCurrentX = round2;
            this.mCurrentY = round4;
            if (round2 != round3 || round4 != round) {
                this.mScroller.fling(round2, round4, n2, n3, n5, round3, n, round, 0, 0);
            }
        }
        
        public void run() {
            if (this.mScroller.isFinished()) {
                return;
            }
            if (this.mScroller.computeScrollOffset()) {
                final int currX = this.mScroller.getCurrX();
                final int currY = this.mScroller.getCurrY();
                this.this$0.mSuppMatrix.postTranslate((float)(this.mCurrentX - currX), (float)(this.mCurrentY - currY));
                this.this$0.checkAndDisplayMatrix();
                this.mCurrentX = currX;
                this.mCurrentY = currY;
                Compat.postOnAnimation((View)this.this$0.mImageView, (Runnable)this);
            }
        }
    }
}
