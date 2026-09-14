package com.yalantis.ucrop.view;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import com.yalantis.ucrop.util.DensityUtil;
import android.view.MotionEvent;
import android.os.Build$VERSION;
import android.graphics.Region$Op;
import android.graphics.Canvas;
import android.graphics.Path$Direction;
import com.yalantis.ucrop.util.RectUtils;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.animation.OvershootInterpolator;
import android.graphics.Point;
import android.graphics.Paint$Style;
import com.yalantis.ucrop.R$color;
import com.yalantis.ucrop.R$styleable;
import android.content.res.TypedArray;
import com.yalantis.ucrop.R$dimen;
import android.util.AttributeSet;
import android.content.Context;
import android.animation.ValueAnimator;
import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.Path;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;
import android.view.View;

public class OverlayView extends View
{
    public static final boolean DEFAULT_CIRCLE_DIMMED_LAYER = false;
    public static final int DEFAULT_CROP_GRID_COLUMN_COUNT = 2;
    public static final int DEFAULT_CROP_GRID_ROW_COUNT = 2;
    public static final int DEFAULT_FREESTYLE_CROP_MODE = 0;
    public static final boolean DEFAULT_SHOW_CROP_FRAME = true;
    public static final boolean DEFAULT_SHOW_CROP_GRID = true;
    public static final int FREESTYLE_CROP_MODE_DISABLE = 0;
    public static final int FREESTYLE_CROP_MODE_ENABLE = 1;
    public static final int FREESTYLE_CROP_MODE_ENABLE_WITH_PASS_THROUGH = 2;
    private static final long SMOOTH_CENTER_DURATION = 1000L;
    private boolean isDragCenter;
    private OverlayViewChangeListener mCallback;
    private boolean mCircleDimmedLayer;
    private Path mCircularPath;
    private Paint mCropFrameCornersPaint;
    private Paint mCropFramePaint;
    protected float[] mCropGridCenter;
    private int mCropGridColumnCount;
    protected float[] mCropGridCorners;
    private Paint mCropGridPaint;
    private int mCropGridRowCount;
    private int mCropRectCornerTouchAreaLineLength;
    private int mCropRectMinSize;
    private final RectF mCropViewRect;
    private int mCurrentTouchCornerIndex;
    private int mDimmedColor;
    private Paint mDimmedStrokePaint;
    private int mFreestyleCropMode;
    private float[] mGridPoints;
    private float mPreviousTouchX;
    private float mPreviousTouchY;
    private boolean mShouldSetupCropBounds;
    private boolean mShowCropFrame;
    private boolean mShowCropGrid;
    private float mTargetAspectRatio;
    private final RectF mTempRect;
    protected int mThisHeight;
    protected int mThisWidth;
    private int mTouchPointThreshold;
    private ValueAnimator smoothAnimator;
    
    public OverlayView(final Context context) {
        this(context, null);
    }
    
    public OverlayView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public OverlayView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mCropViewRect = new RectF();
        this.mTempRect = new RectF();
        this.mGridPoints = null;
        this.mCircularPath = new Path();
        this.mDimmedStrokePaint = new Paint(1);
        this.mCropGridPaint = new Paint(1);
        this.mCropFramePaint = new Paint(1);
        this.mCropFrameCornersPaint = new Paint(1);
        this.mFreestyleCropMode = 0;
        this.mPreviousTouchX = -1.0f;
        this.mPreviousTouchY = -1.0f;
        this.mCurrentTouchCornerIndex = -1;
        this.mTouchPointThreshold = this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_rect_corner_touch_threshold);
        this.mCropRectMinSize = this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_rect_min_size);
        this.mCropRectCornerTouchAreaLineLength = this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_rect_corner_touch_area_line_length);
        this.init();
    }
    
    private int getCurrentTouchIndex(final float n, final float n2) {
        double n3 = this.mTouchPointThreshold;
        int n4 = -1;
        double n5;
        for (int i = 0; i < 8; i += 2, n3 = n5) {
            final double sqrt = Math.sqrt(Math.pow((double)(n - this.mCropGridCorners[i]), 2.0) + Math.pow((double)(n2 - this.mCropGridCorners[i + 1]), 2.0));
            n5 = n3;
            if (sqrt < n3) {
                n4 = i / 2;
                n5 = sqrt;
            }
        }
        if (this.mFreestyleCropMode == 1 && n4 < 0 && this.mCropViewRect.contains(n, n2)) {
            return 4;
        }
        return n4;
    }
    
    private void initCropFrameStyle(final TypedArray typedArray) {
        final int dimensionPixelSize = typedArray.getDimensionPixelSize(R$styleable.ucrop_UCropView_ucrop_frame_stroke_size, this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_frame_stoke_width));
        final int color = typedArray.getColor(R$styleable.ucrop_UCropView_ucrop_frame_color, this.getResources().getColor(R$color.ucrop_color_default_crop_frame));
        this.mCropFramePaint.setStrokeWidth((float)dimensionPixelSize);
        this.mCropFramePaint.setColor(color);
        this.mCropFramePaint.setStyle(Paint$Style.STROKE);
        this.mCropFrameCornersPaint.setStrokeWidth((float)(dimensionPixelSize * 3));
        this.mCropFrameCornersPaint.setColor(color);
        this.mCropFrameCornersPaint.setStyle(Paint$Style.STROKE);
    }
    
    private void initCropGridStyle(final TypedArray typedArray) {
        final int dimensionPixelSize = typedArray.getDimensionPixelSize(R$styleable.ucrop_UCropView_ucrop_grid_stroke_size, this.getResources().getDimensionPixelSize(R$dimen.ucrop_default_crop_grid_stoke_width));
        final int color = typedArray.getColor(R$styleable.ucrop_UCropView_ucrop_grid_color, this.getResources().getColor(R$color.ucrop_color_default_crop_grid));
        this.mCropGridPaint.setStrokeWidth((float)dimensionPixelSize);
        this.mCropGridPaint.setColor(color);
        this.mCropGridRowCount = typedArray.getInt(R$styleable.ucrop_UCropView_ucrop_grid_row_count, 2);
        this.mCropGridColumnCount = typedArray.getInt(R$styleable.ucrop_UCropView_ucrop_grid_column_count, 2);
    }
    
    private void smoothToCenter() {
        final Point point = new Point((this.getRight() + this.getLeft()) / 2, (this.getTop() + this.getBottom()) / 2);
        final int n = (int)(point.y - this.mCropViewRect.centerY());
        final int n2 = (int)(point.x - this.mCropViewRect.centerX());
        final RectF rectF = new RectF(this.mCropViewRect);
        new RectF(this.mCropViewRect).offset((float)n2, (float)n);
        final ValueAnimator smoothAnimator = this.smoothAnimator;
        if (smoothAnimator != null) {
            smoothAnimator.cancel();
        }
        (this.smoothAnimator = ValueAnimator.ofFloat(new float[] { 0.0f, 1.0f })).setDuration(1000L);
        this.smoothAnimator.setInterpolator((TimeInterpolator)new OvershootInterpolator(1.0f));
        this.smoothAnimator.addListener((Animator$AnimatorListener)new AnimatorListenerAdapter(this) {
            final OverlayView this$0;
            
            public void onAnimationEnd(final Animator animator) {
                if (this.this$0.mCallback != null) {
                    this.this$0.mCallback.onCropRectUpdated(this.this$0.mCropViewRect);
                }
            }
        });
        this.smoothAnimator.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener(this, n2, n, rectF) {
            float lastAnimationValue = 0.0f;
            final OverlayView this$0;
            final RectF val$before;
            final int val$offsetX;
            final int val$offsetY;
            
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                final float n = this.val$offsetX * (float)valueAnimator.getAnimatedValue();
                final float n2 = this.val$offsetY * (float)valueAnimator.getAnimatedValue();
                this.this$0.mCropViewRect.set(new RectF(this.val$before.left + n, this.val$before.top + n2, this.val$before.right + n, this.val$before.bottom + n2));
                this.this$0.updateGridPoints();
                this.this$0.postInvalidate();
                if (this.this$0.mCallback != null) {
                    this.this$0.mCallback.postTranslate(this.val$offsetX * ((float)valueAnimator.getAnimatedValue() - this.lastAnimationValue), this.val$offsetY * ((float)valueAnimator.getAnimatedValue() - this.lastAnimationValue));
                }
                this.lastAnimationValue = (float)valueAnimator.getAnimatedValue();
            }
        });
        this.smoothAnimator.start();
    }
    
    private void updateCropViewRect(float n, float right) {
        this.mTempRect.set(this.mCropViewRect);
        final int mCurrentTouchCornerIndex = this.mCurrentTouchCornerIndex;
        boolean b = true;
        if (mCurrentTouchCornerIndex != 0) {
            if (mCurrentTouchCornerIndex != 1) {
                if (mCurrentTouchCornerIndex != 2) {
                    if (mCurrentTouchCornerIndex != 3) {
                        if (mCurrentTouchCornerIndex == 4) {
                            this.mTempRect.offset(n - this.mPreviousTouchX, right - this.mPreviousTouchY);
                            if (this.mTempRect.left > this.getLeft() && this.mTempRect.top > this.getTop() && this.mTempRect.right < this.getRight() && this.mTempRect.bottom < this.getBottom()) {
                                this.mCropViewRect.set(this.mTempRect);
                                this.updateGridPoints();
                                this.postInvalidate();
                            }
                            return;
                        }
                    }
                    else {
                        this.mTempRect.set(n, this.mCropViewRect.top, this.mCropViewRect.right, right);
                    }
                }
                else {
                    this.mTempRect.set(this.mCropViewRect.left, this.mCropViewRect.top, n, right);
                }
            }
            else {
                this.mTempRect.set(this.mCropViewRect.left, right, n, this.mCropViewRect.bottom);
            }
        }
        else {
            this.mTempRect.set(n, right, this.mCropViewRect.right, this.mCropViewRect.bottom);
        }
        final boolean b2 = this.mTempRect.height() >= this.mCropRectMinSize;
        if (this.mTempRect.width() < this.mCropRectMinSize) {
            b = false;
        }
        final RectF mCropViewRect = this.mCropViewRect;
        if (b) {
            n = this.mTempRect.left;
        }
        else {
            n = mCropViewRect.left;
        }
        RectF rectF;
        if (b2) {
            rectF = this.mTempRect;
        }
        else {
            rectF = this.mCropViewRect;
        }
        final float top = rectF.top;
        RectF rectF2;
        if (b) {
            rectF2 = this.mTempRect;
        }
        else {
            rectF2 = this.mCropViewRect;
        }
        right = rectF2.right;
        RectF rectF3;
        if (b2) {
            rectF3 = this.mTempRect;
        }
        else {
            rectF3 = this.mCropViewRect;
        }
        mCropViewRect.set(n, top, right, rectF3.bottom);
        if (b2 || b) {
            this.updateGridPoints();
            this.postInvalidate();
        }
    }
    
    private void updateGridPoints() {
        this.mCropGridCorners = RectUtils.getCornersFromRect(this.mCropViewRect);
        this.mCropGridCenter = RectUtils.getCenterFromRect(this.mCropViewRect);
        this.mGridPoints = null;
        this.mCircularPath.reset();
        this.mCircularPath.addCircle(this.mCropViewRect.centerX(), this.mCropViewRect.centerY(), Math.min(this.mCropViewRect.width(), this.mCropViewRect.height()) / 2.0f, Path$Direction.CW);
    }
    
    protected void drawCropGrid(final Canvas canvas) {
        if (this.mShowCropGrid) {
            if (this.mGridPoints == null && !this.mCropViewRect.isEmpty()) {
                this.mGridPoints = new float[this.mCropGridRowCount * 4 + this.mCropGridColumnCount * 4];
                final int n = 0;
                int n2 = 0;
                int n3 = 0;
                int i;
                int n4;
                while (true) {
                    i = n;
                    n4 = n3;
                    if (n2 >= this.mCropGridRowCount) {
                        break;
                    }
                    final float[] mGridPoints = this.mGridPoints;
                    final int n5 = n3 + 1;
                    mGridPoints[n3] = this.mCropViewRect.left;
                    final float[] mGridPoints2 = this.mGridPoints;
                    final int n6 = n5 + 1;
                    final float height = this.mCropViewRect.height();
                    final float n7 = n2 + 1.0f;
                    mGridPoints2[n5] = height * (n7 / (this.mCropGridRowCount + 1)) + this.mCropViewRect.top;
                    final float[] mGridPoints3 = this.mGridPoints;
                    n3 = n6 + 1;
                    mGridPoints3[n6] = this.mCropViewRect.right;
                    this.mGridPoints[n3] = this.mCropViewRect.height() * (n7 / (this.mCropGridRowCount + 1)) + this.mCropViewRect.top;
                    ++n2;
                    ++n3;
                }
                while (i < this.mCropGridColumnCount) {
                    final float[] mGridPoints4 = this.mGridPoints;
                    final int n8 = n4 + 1;
                    final float width = this.mCropViewRect.width();
                    final float n9 = i + 1.0f;
                    mGridPoints4[n4] = width * (n9 / (this.mCropGridColumnCount + 1)) + this.mCropViewRect.left;
                    final float[] mGridPoints5 = this.mGridPoints;
                    final int n10 = n8 + 1;
                    mGridPoints5[n8] = this.mCropViewRect.top;
                    final float[] mGridPoints6 = this.mGridPoints;
                    final int n11 = n10 + 1;
                    mGridPoints6[n10] = this.mCropViewRect.width() * (n9 / (this.mCropGridColumnCount + 1)) + this.mCropViewRect.left;
                    final float[] mGridPoints7 = this.mGridPoints;
                    n4 = n11 + 1;
                    mGridPoints7[n11] = this.mCropViewRect.bottom;
                    ++i;
                }
            }
            final float[] mGridPoints8 = this.mGridPoints;
            if (mGridPoints8 != null) {
                canvas.drawLines(mGridPoints8, this.mCropGridPaint);
            }
        }
        if (this.mShowCropFrame) {
            canvas.drawRect(this.mCropViewRect, this.mCropFramePaint);
        }
        if (this.mFreestyleCropMode != 0) {
            canvas.save();
            this.mTempRect.set(this.mCropViewRect);
            final RectF mTempRect = this.mTempRect;
            final int mCropRectCornerTouchAreaLineLength = this.mCropRectCornerTouchAreaLineLength;
            mTempRect.inset((float)mCropRectCornerTouchAreaLineLength, (float)(-mCropRectCornerTouchAreaLineLength));
            canvas.clipRect(this.mTempRect, Region$Op.DIFFERENCE);
            this.mTempRect.set(this.mCropViewRect);
            final RectF mTempRect2 = this.mTempRect;
            final int mCropRectCornerTouchAreaLineLength2 = this.mCropRectCornerTouchAreaLineLength;
            mTempRect2.inset((float)(-mCropRectCornerTouchAreaLineLength2), (float)mCropRectCornerTouchAreaLineLength2);
            canvas.clipRect(this.mTempRect, Region$Op.DIFFERENCE);
            canvas.drawRect(this.mCropViewRect, this.mCropFrameCornersPaint);
            canvas.restore();
        }
    }
    
    protected void drawDimmedLayer(final Canvas canvas) {
        canvas.save();
        if (this.mCircleDimmedLayer) {
            canvas.clipPath(this.mCircularPath, Region$Op.DIFFERENCE);
        }
        else {
            canvas.clipRect(this.mCropViewRect, Region$Op.DIFFERENCE);
        }
        canvas.drawColor(this.mDimmedColor);
        canvas.restore();
        if (this.mCircleDimmedLayer) {
            canvas.drawCircle(this.mCropViewRect.centerX(), this.mCropViewRect.centerY(), Math.min(this.mCropViewRect.width(), this.mCropViewRect.height()) / 2.0f, this.mDimmedStrokePaint);
        }
    }
    
    public RectF getCropViewRect() {
        return this.mCropViewRect;
    }
    
    public int getFreestyleCropMode() {
        return this.mFreestyleCropMode;
    }
    
    public OverlayViewChangeListener getOverlayViewChangeListener() {
        return this.mCallback;
    }
    
    protected void init() {
        if (Build$VERSION.SDK_INT < 18) {
            this.setLayerType(1, (Paint)null);
        }
    }
    
    @Deprecated
    public boolean isFreestyleCropEnabled() {
        final int mFreestyleCropMode = this.mFreestyleCropMode;
        boolean b = true;
        if (mFreestyleCropMode != 1) {
            b = false;
        }
        return b;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        this.drawDimmedLayer(canvas);
        this.drawCropGrid(canvas);
    }
    
    protected void onLayout(final boolean b, int paddingRight, int paddingTop, int width, int height) {
        super.onLayout(b, paddingRight, paddingTop, width, height);
        if (b) {
            final int paddingLeft = this.getPaddingLeft();
            paddingTop = this.getPaddingTop();
            width = this.getWidth();
            paddingRight = this.getPaddingRight();
            height = this.getHeight();
            final int paddingBottom = this.getPaddingBottom();
            this.mThisWidth = width - paddingRight - paddingLeft;
            this.mThisHeight = height - paddingBottom - paddingTop;
            if (this.mShouldSetupCropBounds) {
                this.mShouldSetupCropBounds = false;
                this.setTargetAspectRatio(this.mTargetAspectRatio);
            }
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final boolean empty = this.mCropViewRect.isEmpty();
        boolean b = false;
        if (!empty) {
            if (this.mFreestyleCropMode != 0) {
                final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                if ((motionEvent.getAction() & 0xFF) == 0x0) {
                    if ((this.mCurrentTouchCornerIndex = this.getCurrentTouchIndex(x, y)) != -1) {
                        b = true;
                    }
                    if (!b) {
                        this.mPreviousTouchX = -1.0f;
                        this.mPreviousTouchY = -1.0f;
                    }
                    else if (this.mPreviousTouchX < 0.0f) {
                        this.mPreviousTouchX = x;
                        this.mPreviousTouchY = y;
                    }
                    return b;
                }
                if ((motionEvent.getAction() & 0xFF) == 0x2 && motionEvent.getPointerCount() == 1 && this.mCurrentTouchCornerIndex != -1) {
                    final float min = Math.min(Math.max(x, (float)this.getPaddingLeft()), (float)(this.getWidth() - this.getPaddingRight()));
                    final float min2 = Math.min(Math.max(y, (float)this.getPaddingTop()), (float)(this.getHeight() - this.getPaddingBottom()));
                    this.updateCropViewRect(min, min2);
                    this.mPreviousTouchX = min;
                    this.mPreviousTouchY = min2;
                    return true;
                }
                if ((motionEvent.getAction() & 0xFF) == 0x1) {
                    this.mPreviousTouchX = -1.0f;
                    this.mPreviousTouchY = -1.0f;
                    this.mCurrentTouchCornerIndex = -1;
                    final OverlayViewChangeListener mCallback = this.mCallback;
                    if (mCallback != null) {
                        mCallback.onCropRectUpdated(this.mCropViewRect);
                    }
                    if (this.isDragCenter) {
                        this.smoothToCenter();
                    }
                }
            }
        }
        return false;
    }
    
    protected void processStyledAttributes(final TypedArray typedArray) {
        this.mCircleDimmedLayer = typedArray.getBoolean(R$styleable.ucrop_UCropView_ucrop_circle_dimmed_layer, false);
        final int color = typedArray.getColor(R$styleable.ucrop_UCropView_ucrop_dimmed_color, this.getResources().getColor(R$color.ucrop_color_default_dimmed));
        this.mDimmedColor = color;
        this.mDimmedStrokePaint.setColor(color);
        this.mDimmedStrokePaint.setStyle(Paint$Style.STROKE);
        this.mDimmedStrokePaint.setStrokeWidth((float)DensityUtil.dip2px(this.getContext(), 1.0f));
        this.initCropFrameStyle(typedArray);
        this.mShowCropFrame = typedArray.getBoolean(R$styleable.ucrop_UCropView_ucrop_show_frame, true);
        this.initCropGridStyle(typedArray);
        this.mShowCropGrid = typedArray.getBoolean(R$styleable.ucrop_UCropView_ucrop_show_grid, true);
    }
    
    public void setCircleDimmedLayer(final boolean mCircleDimmedLayer) {
        this.mCircleDimmedLayer = mCircleDimmedLayer;
    }
    
    public void setCircleStrokeColor(final int color) {
        this.mDimmedStrokePaint.setColor(color);
    }
    
    public void setCropFrameColor(final int color) {
        this.mCropFramePaint.setColor(color);
    }
    
    public void setCropFrameStrokeWidth(final int n) {
        this.mCropFramePaint.setStrokeWidth((float)n);
    }
    
    public void setCropGridColor(final int color) {
        this.mCropGridPaint.setColor(color);
    }
    
    public void setCropGridColumnCount(final int mCropGridColumnCount) {
        this.mCropGridColumnCount = mCropGridColumnCount;
        this.mGridPoints = null;
    }
    
    public void setCropGridRowCount(final int mCropGridRowCount) {
        this.mCropGridRowCount = mCropGridRowCount;
        this.mGridPoints = null;
    }
    
    public void setCropGridStrokeWidth(final int n) {
        this.mCropGridPaint.setStrokeWidth((float)n);
    }
    
    public void setDimmedColor(final int mDimmedColor) {
        this.mDimmedColor = mDimmedColor;
    }
    
    public void setDimmedStrokeWidth(final int n) {
        this.mDimmedStrokePaint.setStrokeWidth((float)n);
    }
    
    public void setDragSmoothToCenter(final boolean isDragCenter) {
        this.isDragCenter = isDragCenter;
    }
    
    @Deprecated
    public void setFreestyleCropEnabled(final boolean mFreestyleCropMode) {
        this.mFreestyleCropMode = (mFreestyleCropMode ? 1 : 0);
    }
    
    public void setFreestyleCropMode(final int mFreestyleCropMode) {
        this.mFreestyleCropMode = mFreestyleCropMode;
        this.postInvalidate();
    }
    
    public void setOverlayViewChangeListener(final OverlayViewChangeListener mCallback) {
        this.mCallback = mCallback;
    }
    
    public void setShowCropFrame(final boolean mShowCropFrame) {
        this.mShowCropFrame = mShowCropFrame;
    }
    
    public void setShowCropGrid(final boolean mShowCropGrid) {
        this.mShowCropGrid = mShowCropGrid;
    }
    
    public void setTargetAspectRatio(final float mTargetAspectRatio) {
        this.mTargetAspectRatio = mTargetAspectRatio;
        if (this.mThisWidth > 0) {
            this.setupCropBounds();
            this.postInvalidate();
        }
        else {
            this.mShouldSetupCropBounds = true;
        }
    }
    
    public void setupCropBounds() {
        final int mThisWidth = this.mThisWidth;
        final float n = (float)mThisWidth;
        final float mTargetAspectRatio = this.mTargetAspectRatio;
        final int n2 = (int)(n / mTargetAspectRatio);
        final int mThisHeight = this.mThisHeight;
        if (n2 > mThisHeight) {
            final int n3 = (int)(mThisHeight * mTargetAspectRatio);
            final int n4 = (mThisWidth - n3) / 2;
            this.mCropViewRect.set((float)(this.getPaddingLeft() + n4), (float)this.getPaddingTop(), (float)(this.getPaddingLeft() + n3 + n4), (float)(this.getPaddingTop() + this.mThisHeight));
        }
        else {
            final int n5 = (mThisHeight - n2) / 2;
            this.mCropViewRect.set((float)this.getPaddingLeft(), (float)(this.getPaddingTop() + n5), (float)(this.getPaddingLeft() + this.mThisWidth), (float)(this.getPaddingTop() + n2 + n5));
        }
        final OverlayViewChangeListener mCallback = this.mCallback;
        if (mCallback != null) {
            mCallback.onCropRectUpdated(this.mCropViewRect);
        }
        this.updateGridPoints();
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface FreestyleMode {
    }
}
