package com.kingagroot.kingdraw.ui.baike.view;

import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.RectF;
import android.view.GestureDetector$OnGestureListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.widget.ImageView$ScaleType;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Matrix;
import android.view.ScaleGestureDetector;
import android.view.GestureDetector;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnTouchListener;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import android.widget.ImageView;

public class ZoomImageView extends ImageView implements ScaleGestureDetector$OnScaleGestureListener, View$OnTouchListener, ViewTreeObserver$OnGlobalLayoutListener
{
    public static final float SCALE_MAX = 4.0f;
    private static final float SCALE_MID = 2.0f;
    private static final String TAG;
    private float initScale;
    private boolean isAutoScale;
    private boolean isCanDrag;
    private boolean isCheckLeftAndRight;
    private boolean isCheckTopAndBottom;
    private int lastPointerCount;
    private final GestureDetector mGestureDetector;
    private float mLastX;
    private float mLastY;
    private ScaleGestureDetector mScaleGestureDetector;
    private final Matrix mScaleMatrix;
    private int mTouchSlop;
    private final float[] matrixValues;
    private boolean once;
    
    static {
        TAG = ZoomImageView.class.getSimpleName();
    }
    
    public ZoomImageView(final Context context) {
        this(context, null);
    }
    
    public ZoomImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.initScale = 1.0f;
        this.once = true;
        this.matrixValues = new float[9];
        this.mScaleGestureDetector = null;
        this.mScaleMatrix = new Matrix();
        this.isCheckTopAndBottom = true;
        this.isCheckLeftAndRight = true;
        super.setScaleType(ImageView$ScaleType.MATRIX);
        this.mGestureDetector = new GestureDetector(context, (GestureDetector$OnGestureListener)new GestureDetector$SimpleOnGestureListener(this) {
            final ZoomImageView this$0;
            
            public boolean onDoubleTap(final MotionEvent motionEvent) {
                if (this.this$0.isAutoScale) {
                    return true;
                }
                final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                final StringBuilder sb = new StringBuilder();
                sb.append(this.this$0.getScale());
                sb.append(" , ");
                sb.append(this.this$0.initScale);
                Log.e("DoubleTap", sb.toString());
                if (this.this$0.getScale() < 2.0f) {
                    final ZoomImageView this$0 = this.this$0;
                    this$0.postDelayed((Runnable)this$0.new AutoScaleRunnable(2.0f, x, y), 16L);
                    this.this$0.isAutoScale = true;
                }
                else if (this.this$0.getScale() >= 2.0f && this.this$0.getScale() < 4.0f) {
                    final ZoomImageView this$2 = this.this$0;
                    this$2.postDelayed((Runnable)this$2.new AutoScaleRunnable(4.0f, x, y), 16L);
                    this.this$0.isAutoScale = true;
                }
                else {
                    final ZoomImageView this$3 = this.this$0;
                    this$3.postDelayed((Runnable)this$3.new AutoScaleRunnable(this$3.initScale, x, y), 16L);
                    this.this$0.isAutoScale = true;
                }
                return true;
            }
        });
        this.mScaleGestureDetector = new ScaleGestureDetector(context, (ScaleGestureDetector$OnScaleGestureListener)this);
        this.setOnTouchListener((View$OnTouchListener)this);
    }
    
    private void checkBorderAndCenterWhenScale() {
        final RectF matrixRectF = this.getMatrixRectF();
        final int width = this.getWidth();
        final int height = this.getHeight();
        final float width2 = matrixRectF.width();
        final float n = (float)width;
        float n2 = 0.0f;
        final float n3 = 0.0f;
        float n4;
        if (width2 >= n) {
            if (matrixRectF.left > 0.0f) {
                n4 = -matrixRectF.left;
            }
            else {
                n4 = 0.0f;
            }
            if (matrixRectF.right < n) {
                n4 = n - matrixRectF.right;
            }
        }
        else {
            n4 = 0.0f;
        }
        final float height2 = matrixRectF.height();
        final float n5 = (float)height;
        if (height2 >= n5) {
            n2 = n3;
            if (matrixRectF.top > 0.0f) {
                n2 = -matrixRectF.top;
            }
            if (matrixRectF.bottom < n5) {
                n2 = n5 - matrixRectF.bottom;
            }
        }
        if (matrixRectF.width() < n) {
            n4 = matrixRectF.width() * 0.5f + (n * 0.5f - matrixRectF.right);
        }
        if (matrixRectF.height() < n5) {
            n2 = n5 * 0.5f - matrixRectF.bottom + matrixRectF.height() * 0.5f;
        }
        final String tag = ZoomImageView.TAG;
        final StringBuilder sb = new StringBuilder();
        sb.append("deltaX = ");
        sb.append(n4);
        sb.append(" , deltaY = ");
        sb.append(n2);
        Log.e(tag, sb.toString());
        this.mScaleMatrix.postTranslate(n4, n2);
    }
    
    private void checkMatrixBounds() {
        final RectF matrixRectF = this.getMatrixRectF();
        final float n = (float)this.getWidth();
        final float n2 = (float)this.getHeight();
        final float top = matrixRectF.top;
        final float n3 = 0.0f;
        float n4;
        if (top > 0.0f && this.isCheckTopAndBottom) {
            n4 = -matrixRectF.top;
        }
        else {
            n4 = 0.0f;
        }
        float n5 = n4;
        if (matrixRectF.bottom < n2) {
            n5 = n4;
            if (this.isCheckTopAndBottom) {
                n5 = n2 - matrixRectF.bottom;
            }
        }
        float n6 = n3;
        if (matrixRectF.left > 0.0f) {
            n6 = n3;
            if (this.isCheckLeftAndRight) {
                n6 = -matrixRectF.left;
            }
        }
        float n7 = n6;
        if (matrixRectF.right < n) {
            n7 = n6;
            if (this.isCheckLeftAndRight) {
                n7 = n - matrixRectF.right;
            }
        }
        this.mScaleMatrix.postTranslate(n7, n5);
    }
    
    private RectF getMatrixRectF() {
        final Matrix mScaleMatrix = this.mScaleMatrix;
        final RectF rectF = new RectF();
        final Drawable drawable = this.getDrawable();
        if (drawable != null) {
            rectF.set(0.0f, 0.0f, (float)drawable.getIntrinsicWidth(), (float)drawable.getIntrinsicHeight());
            mScaleMatrix.mapRect(rectF);
        }
        return rectF;
    }
    
    private boolean isCanDrag(final float n, final float n2) {
        return Math.sqrt((double)(n * n + n2 * n2)) >= this.mTouchSlop;
    }
    
    public final float getScale() {
        this.mScaleMatrix.getValues(this.matrixValues);
        return this.matrixValues[0];
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
    }
    
    public void onGlobalLayout() {
        if (this.once) {
            final Drawable drawable = this.getDrawable();
            if (drawable == null) {
                return;
            }
            final String tag = ZoomImageView.TAG;
            final StringBuilder sb = new StringBuilder();
            sb.append(drawable.getIntrinsicWidth());
            sb.append(" , ");
            sb.append(drawable.getIntrinsicHeight());
            Log.e(tag, sb.toString());
            final int width = this.getWidth();
            final int height = this.getHeight();
            final int intrinsicWidth = drawable.getIntrinsicWidth();
            final int intrinsicHeight = drawable.getIntrinsicHeight();
            float n;
            if (intrinsicWidth > width && intrinsicHeight <= height) {
                n = width * 1.0f / intrinsicWidth;
            }
            else {
                n = 1.0f;
            }
            float n2 = n;
            if (intrinsicHeight > height) {
                n2 = n;
                if (intrinsicWidth <= width) {
                    n2 = height * 1.0f / intrinsicHeight;
                }
            }
            float min = n2;
            if (intrinsicWidth > width) {
                min = n2;
                if (intrinsicHeight > height) {
                    min = Math.min(width * 1.0f / intrinsicWidth, height * 1.0f / intrinsicHeight);
                }
            }
            this.initScale = min;
            final String tag2 = ZoomImageView.TAG;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("initScale = ");
            sb2.append(this.initScale);
            Log.e(tag2, sb2.toString());
            this.mScaleMatrix.postTranslate((float)((width - intrinsicWidth) / 2), (float)((height - intrinsicHeight) / 2));
            this.mScaleMatrix.postScale(min, min, (float)(this.getWidth() / 2), (float)(this.getHeight() / 2));
            this.setImageMatrix(this.mScaleMatrix);
            this.once = false;
        }
    }
    
    public boolean onScale(final ScaleGestureDetector scaleGestureDetector) {
        final float scale = this.getScale();
        final float scaleFactor = scaleGestureDetector.getScaleFactor();
        if (this.getDrawable() == null) {
            return true;
        }
        if ((scale < 4.0f && scaleFactor > 1.0f) || (scale > this.initScale && scaleFactor < 1.0f)) {
            final float initScale = this.initScale;
            float n = scaleFactor;
            if (scaleFactor * scale < initScale) {
                n = initScale / scale;
            }
            float n2 = n;
            if (n * scale > 4.0f) {
                n2 = 4.0f / scale;
            }
            this.mScaleMatrix.postScale(n2, n2, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            this.checkBorderAndCenterWhenScale();
            this.setImageMatrix(this.mScaleMatrix);
        }
        return true;
    }
    
    public boolean onScaleBegin(final ScaleGestureDetector scaleGestureDetector) {
        return true;
    }
    
    public void onScaleEnd(final ScaleGestureDetector scaleGestureDetector) {
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (this.mGestureDetector.onTouchEvent(motionEvent)) {
            return true;
        }
        this.mScaleGestureDetector.onTouchEvent(motionEvent);
        final int pointerCount = motionEvent.getPointerCount();
        final float n = 0.0f;
        int i = 0;
        float n2 = 0.0f;
        float n3 = 0.0f;
        while (i < pointerCount) {
            n2 += motionEvent.getX(i);
            n3 += motionEvent.getY(i);
            ++i;
        }
        final float n4 = (float)pointerCount;
        final float n5 = n2 / n4;
        final float n6 = n3 / n4;
        if (pointerCount != this.lastPointerCount) {
            this.isCanDrag = false;
            this.mLastX = n5;
            this.mLastY = n6;
        }
        this.lastPointerCount = pointerCount;
        final RectF matrixRectF = this.getMatrixRectF();
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    if (matrixRectF.width() > this.getWidth() || matrixRectF.height() > this.getHeight()) {
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    Log.e(ZoomImageView.TAG, "ACTION_MOVE");
                    float n7 = n5 - this.mLastX;
                    float n8 = n6 - this.mLastY;
                    if (!this.isCanDrag) {
                        this.isCanDrag = this.isCanDrag(n7, n8);
                    }
                    if (this.isCanDrag && this.getDrawable() != null) {
                        this.isCheckTopAndBottom = true;
                        this.isCheckLeftAndRight = true;
                        if (matrixRectF.width() < this.getWidth()) {
                            this.isCheckLeftAndRight = false;
                            n7 = 0.0f;
                        }
                        if (matrixRectF.height() < this.getHeight()) {
                            this.isCheckTopAndBottom = false;
                            n8 = n;
                        }
                        this.mScaleMatrix.postTranslate(n7, n8);
                        this.checkMatrixBounds();
                        this.setImageMatrix(this.mScaleMatrix);
                    }
                    this.mLastX = n5;
                    this.mLastY = n6;
                    return true;
                }
                if (action != 3) {
                    return true;
                }
            }
            Log.e(ZoomImageView.TAG, "ACTION_UP");
            this.lastPointerCount = 0;
        }
        else if (matrixRectF.width() > this.getWidth() || matrixRectF.height() > this.getHeight()) {
            this.getParent().requestDisallowInterceptTouchEvent(true);
        }
        return true;
    }
    
    private class AutoScaleRunnable implements Runnable
    {
        static final float BIGGER = 1.0f;
        static final float SMALLER = 0.8f;
        private final float mTargetScale;
        final ZoomImageView this$0;
        private final float tmpScale;
        private final float x;
        private final float y;
        
        public AutoScaleRunnable(final ZoomImageView this$0, final float mTargetScale, final float x, final float y) {
            this.this$0 = this$0;
            this.mTargetScale = mTargetScale;
            this.x = x;
            this.y = y;
            if (this$0.getScale() < this.mTargetScale) {
                this.tmpScale = 1.0f;
            }
            else {
                this.tmpScale = 0.8f;
            }
        }
        
        public void run() {
            final Matrix access$200 = this.this$0.mScaleMatrix;
            final float tmpScale = this.tmpScale;
            access$200.postScale(tmpScale, tmpScale, this.x, this.y);
            this.this$0.checkBorderAndCenterWhenScale();
            final ZoomImageView this$0 = this.this$0;
            this$0.setImageMatrix(this$0.mScaleMatrix);
            final float scale = this.this$0.getScale();
            if ((this.tmpScale > 1.0f && scale < this.mTargetScale) || (this.tmpScale < 1.0f && this.mTargetScale < scale)) {
                this.this$0.postDelayed((Runnable)this, 16L);
            }
            else {
                final float n = this.mTargetScale / scale;
                this.this$0.mScaleMatrix.postScale(n, n, this.x, this.y);
                this.this$0.checkBorderAndCenterWhenScale();
                final ZoomImageView this$2 = this.this$0;
                this$2.setImageMatrix(this$2.mScaleMatrix);
                this.this$0.isAutoScale = false;
            }
        }
    }
}
