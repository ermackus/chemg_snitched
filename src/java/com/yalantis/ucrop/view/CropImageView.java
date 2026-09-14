package com.yalantis.ucrop.view;

import com.yalantis.ucrop.R$styleable;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import com.yalantis.ucrop.task.BitmapCropTask;
import com.yalantis.ucrop.model.CropParameters;
import com.yalantis.ucrop.model.ImageState;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.drawable.Drawable;
import com.yalantis.ucrop.util.RectUtils;
import java.util.Arrays;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.yalantis.ucrop.callback.CropBoundsChangeListener;

public class CropImageView extends TransformImageView
{
    public static final float DEFAULT_ASPECT_RATIO = 0.0f;
    public static final int DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION = 500;
    public static final int DEFAULT_MAX_BITMAP_SIZE = 0;
    public static final float DEFAULT_MAX_SCALE_MULTIPLIER = 10.0f;
    public static final float SOURCE_IMAGE_ASPECT_RATIO = 0.0f;
    private CropBoundsChangeListener mCropBoundsChangeListener;
    private final RectF mCropRect;
    private long mImageToWrapCropBoundsAnimDuration;
    private int mMaxResultImageSizeX;
    private int mMaxResultImageSizeY;
    private float mMaxScale;
    private float mMaxScaleMultiplier;
    private float mMinScale;
    private float mTargetAspectRatio;
    private final Matrix mTempMatrix;
    private Runnable mWrapCropBoundsRunnable;
    private Runnable mZoomImageToPositionRunnable;
    
    public CropImageView(final Context context) {
        this(context, null);
    }
    
    public CropImageView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public CropImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mCropRect = new RectF();
        this.mTempMatrix = new Matrix();
        this.mMaxScaleMultiplier = 10.0f;
        this.mZoomImageToPositionRunnable = null;
        this.mMaxResultImageSizeX = 0;
        this.mMaxResultImageSizeY = 0;
        this.mImageToWrapCropBoundsAnimDuration = 500L;
    }
    
    private float[] calculateImageIndents() {
        this.mTempMatrix.reset();
        this.mTempMatrix.setRotate(-this.getCurrentAngle());
        final float[] copy = Arrays.copyOf(this.mCurrentImageCorners, this.mCurrentImageCorners.length);
        final float[] cornersFromRect = RectUtils.getCornersFromRect(this.mCropRect);
        this.mTempMatrix.mapPoints(copy);
        this.mTempMatrix.mapPoints(cornersFromRect);
        final RectF trapToRect = RectUtils.trapToRect(copy);
        final RectF trapToRect2 = RectUtils.trapToRect(cornersFromRect);
        float n = trapToRect.left - trapToRect2.left;
        final float n2 = trapToRect.top - trapToRect2.top;
        final float n3 = trapToRect.right - trapToRect2.right;
        final float n4 = trapToRect.bottom - trapToRect2.bottom;
        final float[] array = new float[4];
        if (n <= 0.0f) {
            n = 0.0f;
        }
        array[0] = n;
        float n5;
        if (n2 > 0.0f) {
            n5 = n2;
        }
        else {
            n5 = 0.0f;
        }
        array[1] = n5;
        float n6;
        if (n3 < 0.0f) {
            n6 = n3;
        }
        else {
            n6 = 0.0f;
        }
        array[2] = n6;
        float n7;
        if (n4 < 0.0f) {
            n7 = n4;
        }
        else {
            n7 = 0.0f;
        }
        array[3] = n7;
        this.mTempMatrix.reset();
        this.mTempMatrix.setRotate(this.getCurrentAngle());
        this.mTempMatrix.mapPoints(array);
        return array;
    }
    
    private void calculateImageScaleBounds() {
        final Drawable drawable = this.getDrawable();
        if (drawable == null) {
            return;
        }
        this.calculateImageScaleBounds((float)drawable.getIntrinsicWidth(), (float)drawable.getIntrinsicHeight());
    }
    
    private void calculateImageScaleBounds(float min, final float n) {
        min = Math.min(Math.min(this.mCropRect.width() / min, this.mCropRect.width() / n), Math.min(this.mCropRect.height() / n, this.mCropRect.height() / min));
        this.mMinScale = min;
        this.mMaxScale = min * this.mMaxScaleMultiplier;
    }
    
    private void setupInitialImagePosition(float left, float n) {
        final float width = this.mCropRect.width();
        final float height = this.mCropRect.height();
        final float max = Math.max(this.mCropRect.width() / left, this.mCropRect.height() / n);
        final float n2 = (width - left * max) / 2.0f;
        left = this.mCropRect.left;
        n = (height - n * max) / 2.0f;
        final float top = this.mCropRect.top;
        this.mCurrentImageMatrix.reset();
        this.mCurrentImageMatrix.postScale(max, max);
        this.mCurrentImageMatrix.postTranslate(n2 + left, n + top);
        this.setImageMatrix(this.mCurrentImageMatrix);
    }
    
    public void cancelAllAnimations() {
        this.removeCallbacks(this.mWrapCropBoundsRunnable);
        this.removeCallbacks(this.mZoomImageToPositionRunnable);
    }
    
    public void cropAndSaveImage(final Bitmap$CompressFormat bitmap$CompressFormat, final int n, final BitmapCropCallback bitmapCropCallback) {
        this.cancelAllAnimations();
        this.setImageToWrapCropBounds(false);
        final ImageState imageState = new ImageState(this.mCropRect, RectUtils.trapToRect(this.mCurrentImageCorners), this.getCurrentScale(), this.getCurrentAngle());
        final CropParameters cropParameters = new CropParameters(this.mMaxResultImageSizeX, this.mMaxResultImageSizeY, bitmap$CompressFormat, n, this.getImageInputPath(), this.getImageOutputPath(), this.getExifInfo());
        cropParameters.setContentImageInputUri(this.getImageInputUri());
        cropParameters.setContentImageOutputUri(this.getImageOutputUri());
        new BitmapCropTask(this.getContext(), this.getViewBitmap(), imageState, cropParameters, bitmapCropCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    }
    
    public CropBoundsChangeListener getCropBoundsChangeListener() {
        return this.mCropBoundsChangeListener;
    }
    
    public float getMaxScale() {
        return this.mMaxScale;
    }
    
    public float getMinScale() {
        return this.mMinScale;
    }
    
    public float getTargetAspectRatio() {
        return this.mTargetAspectRatio;
    }
    
    protected boolean isImageWrapCropBounds() {
        return this.isImageWrapCropBounds(this.mCurrentImageCorners);
    }
    
    protected boolean isImageWrapCropBounds(float[] copy) {
        this.mTempMatrix.reset();
        this.mTempMatrix.setRotate(-this.getCurrentAngle());
        copy = Arrays.copyOf(copy, copy.length);
        this.mTempMatrix.mapPoints(copy);
        final float[] cornersFromRect = RectUtils.getCornersFromRect(this.mCropRect);
        this.mTempMatrix.mapPoints(cornersFromRect);
        return RectUtils.trapToRect(copy).contains(RectUtils.trapToRect(cornersFromRect));
    }
    
    protected void onImageLaidOut() {
        super.onImageLaidOut();
        final Drawable drawable = this.getDrawable();
        if (drawable == null) {
            return;
        }
        final float n = (float)drawable.getIntrinsicWidth();
        final float n2 = (float)drawable.getIntrinsicHeight();
        if (this.mTargetAspectRatio == 0.0f) {
            this.mTargetAspectRatio = n / n2;
        }
        final int n3 = (int)(this.mThisWidth / this.mTargetAspectRatio);
        if (n3 > this.mThisHeight) {
            final int n4 = (int)(this.mThisHeight * this.mTargetAspectRatio);
            final int n5 = (this.mThisWidth - n4) / 2;
            this.mCropRect.set((float)n5, 0.0f, (float)(n4 + n5), (float)this.mThisHeight);
        }
        else {
            final int n6 = (this.mThisHeight - n3) / 2;
            this.mCropRect.set(0.0f, (float)n6, (float)this.mThisWidth, (float)(n3 + n6));
        }
        this.calculateImageScaleBounds(n, n2);
        this.setupInitialImagePosition(n, n2);
        final CropBoundsChangeListener mCropBoundsChangeListener = this.mCropBoundsChangeListener;
        if (mCropBoundsChangeListener != null) {
            mCropBoundsChangeListener.onCropAspectRatioChanged(this.mTargetAspectRatio);
        }
        if (this.mTransformImageListener != null) {
            this.mTransformImageListener.onScale(this.getCurrentScale());
            this.mTransformImageListener.onRotate(this.getCurrentAngle());
        }
    }
    
    public void postRotate(final float n) {
        this.postRotate(n, this.mCropRect.centerX(), this.mCropRect.centerY());
    }
    
    public void postScale(final float n, final float n2, final float n3) {
        if (n > 1.0f && this.getCurrentScale() * n <= this.getMaxScale()) {
            super.postScale(n, n2, n3);
        }
        else if (n < 1.0f && this.getCurrentScale() * n >= this.getMinScale()) {
            super.postScale(n, n2, n3);
        }
    }
    
    protected void processStyledAttributes(final TypedArray typedArray) {
        final float abs = Math.abs(typedArray.getFloat(R$styleable.ucrop_UCropView_ucrop_aspect_ratio_x, 0.0f));
        final float abs2 = Math.abs(typedArray.getFloat(R$styleable.ucrop_UCropView_ucrop_aspect_ratio_y, 0.0f));
        if (abs != 0.0f && abs2 != 0.0f) {
            this.mTargetAspectRatio = abs / abs2;
        }
        else {
            this.mTargetAspectRatio = 0.0f;
        }
    }
    
    public void setCropBoundsChangeListener(final CropBoundsChangeListener mCropBoundsChangeListener) {
        this.mCropBoundsChangeListener = mCropBoundsChangeListener;
    }
    
    public void setCropRect(final RectF rectF) {
        this.mTargetAspectRatio = rectF.width() / rectF.height();
        this.mCropRect.set(rectF.left - this.getPaddingLeft(), rectF.top - this.getPaddingTop(), rectF.right - this.getPaddingRight(), rectF.bottom - this.getPaddingBottom());
        this.calculateImageScaleBounds();
        this.setImageToWrapCropBounds();
    }
    
    public void setImageToWrapCropBounds() {
        this.setImageToWrapCropBounds(true);
    }
    
    public void setImageToWrapCropBounds(final boolean b) {
        if (this.mBitmapLaidOut && !this.isImageWrapCropBounds()) {
            final float n = this.mCurrentImageCenter[0];
            final float n2 = this.mCurrentImageCenter[1];
            final float currentScale = this.getCurrentScale();
            float n3 = this.mCropRect.centerX() - n;
            float n4 = this.mCropRect.centerY() - n2;
            this.mTempMatrix.reset();
            this.mTempMatrix.setTranslate(n3, n4);
            final float[] copy = Arrays.copyOf(this.mCurrentImageCorners, this.mCurrentImageCorners.length);
            this.mTempMatrix.mapPoints(copy);
            final boolean imageWrapCropBounds = this.isImageWrapCropBounds(copy);
            float n5;
            if (imageWrapCropBounds) {
                final float[] calculateImageIndents = this.calculateImageIndents();
                n3 = -(calculateImageIndents[0] + calculateImageIndents[2]);
                n4 = -(calculateImageIndents[1] + calculateImageIndents[3]);
                n5 = 0.0f;
            }
            else {
                final RectF rectF = new RectF(this.mCropRect);
                this.mTempMatrix.reset();
                this.mTempMatrix.setRotate(this.getCurrentAngle());
                this.mTempMatrix.mapRect(rectF);
                final float[] rectSidesFromCorners = RectUtils.getRectSidesFromCorners(this.mCurrentImageCorners);
                n5 = Math.max(rectF.width() / rectSidesFromCorners[0], rectF.height() / rectSidesFromCorners[1]) * currentScale - currentScale;
            }
            if (b) {
                this.post(this.mWrapCropBoundsRunnable = (Runnable)new CropImageView.CropImageView$WrapCropBoundsRunnable(this, this.mImageToWrapCropBoundsAnimDuration, n, n2, n3, n4, currentScale, n5, imageWrapCropBounds));
            }
            else {
                this.postTranslate(n3, n4);
                if (!imageWrapCropBounds) {
                    this.zoomInImage(currentScale + n5, this.mCropRect.centerX(), this.mCropRect.centerY());
                }
            }
        }
    }
    
    public void setImageToWrapCropBoundsAnimDuration(final long mImageToWrapCropBoundsAnimDuration) {
        if (mImageToWrapCropBoundsAnimDuration > 0L) {
            this.mImageToWrapCropBoundsAnimDuration = mImageToWrapCropBoundsAnimDuration;
            return;
        }
        throw new IllegalArgumentException("Animation duration cannot be negative value.");
    }
    
    public void setMaxResultImageSizeX(final int mMaxResultImageSizeX) {
        this.mMaxResultImageSizeX = mMaxResultImageSizeX;
    }
    
    public void setMaxResultImageSizeY(final int mMaxResultImageSizeY) {
        this.mMaxResultImageSizeY = mMaxResultImageSizeY;
    }
    
    public void setMaxScaleMultiplier(final float mMaxScaleMultiplier) {
        this.mMaxScaleMultiplier = mMaxScaleMultiplier;
    }
    
    public void setTargetAspectRatio(final float n) {
        final Drawable drawable = this.getDrawable();
        if (drawable == null) {
            this.mTargetAspectRatio = n;
            return;
        }
        if (n == 0.0f) {
            this.mTargetAspectRatio = drawable.getIntrinsicWidth() / (float)drawable.getIntrinsicHeight();
        }
        else {
            this.mTargetAspectRatio = n;
        }
        final CropBoundsChangeListener mCropBoundsChangeListener = this.mCropBoundsChangeListener;
        if (mCropBoundsChangeListener != null) {
            mCropBoundsChangeListener.onCropAspectRatioChanged(this.mTargetAspectRatio);
        }
    }
    
    protected void zoomImageToPosition(float currentScale, final float n, final float n2, final long n3) {
        float maxScale = currentScale;
        if (currentScale > this.getMaxScale()) {
            maxScale = this.getMaxScale();
        }
        currentScale = this.getCurrentScale();
        this.post(this.mZoomImageToPositionRunnable = (Runnable)new CropImageView.CropImageView$ZoomImageToPosition(this, n3, currentScale, maxScale - currentScale, n, n2));
    }
    
    public void zoomInImage(final float n) {
        this.zoomInImage(n, this.mCropRect.centerX(), this.mCropRect.centerY());
    }
    
    public void zoomInImage(final float n, final float n2, final float n3) {
        if (n <= this.getMaxScale()) {
            this.postScale(n / this.getCurrentScale(), n2, n3);
        }
    }
    
    public void zoomOutImage(final float n) {
        this.zoomOutImage(n, this.mCropRect.centerX(), this.mCropRect.centerY());
    }
    
    public void zoomOutImage(final float n, final float n2, final float n3) {
        if (n >= this.getMinScale()) {
            this.postScale(n / this.getCurrentScale(), n2, n3);
        }
    }
}
