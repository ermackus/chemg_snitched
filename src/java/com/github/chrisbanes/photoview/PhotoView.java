package com.github.chrisbanes.photoview;

import android.view.View$OnLongClickListener;
import android.view.GestureDetector$OnDoubleTapListener;
import android.view.View$OnClickListener;
import android.net.Uri;
import android.graphics.drawable.Drawable;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.widget.ImageView;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView$ScaleType;
import androidx.appcompat.widget.AppCompatImageView;

public class PhotoView extends AppCompatImageView
{
    private PhotoViewAttacher attacher;
    private ImageView$ScaleType pendingScaleType;
    
    public PhotoView(final Context context) {
        this(context, null);
    }
    
    public PhotoView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public PhotoView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.attacher = new PhotoViewAttacher((ImageView)this);
        super.setScaleType(ImageView$ScaleType.MATRIX);
        final ImageView$ScaleType pendingScaleType = this.pendingScaleType;
        if (pendingScaleType != null) {
            this.setScaleType(pendingScaleType);
            this.pendingScaleType = null;
        }
    }
    
    public PhotoViewAttacher getAttacher() {
        return this.attacher;
    }
    
    public void getDisplayMatrix(final Matrix matrix) {
        this.attacher.getDisplayMatrix(matrix);
    }
    
    public RectF getDisplayRect() {
        return this.attacher.getDisplayRect();
    }
    
    public Matrix getImageMatrix() {
        return this.attacher.getImageMatrix();
    }
    
    public float getMaximumScale() {
        return this.attacher.getMaximumScale();
    }
    
    public float getMediumScale() {
        return this.attacher.getMediumScale();
    }
    
    public float getMinimumScale() {
        return this.attacher.getMinimumScale();
    }
    
    public float getScale() {
        return this.attacher.getScale();
    }
    
    public ImageView$ScaleType getScaleType() {
        return this.attacher.getScaleType();
    }
    
    public void getSuppMatrix(final Matrix matrix) {
        this.attacher.getSuppMatrix(matrix);
    }
    
    public boolean isZoomable() {
        return this.attacher.isZoomable();
    }
    
    public void setAllowParentInterceptOnEdge(final boolean allowParentInterceptOnEdge) {
        this.attacher.setAllowParentInterceptOnEdge(allowParentInterceptOnEdge);
    }
    
    public boolean setDisplayMatrix(final Matrix displayMatrix) {
        return this.attacher.setDisplayMatrix(displayMatrix);
    }
    
    protected boolean setFrame(final int n, final int n2, final int n3, final int n4) {
        final boolean setFrame = super.setFrame(n, n2, n3, n4);
        if (setFrame) {
            this.attacher.update();
        }
        return setFrame;
    }
    
    public void setImageDrawable(final Drawable imageDrawable) {
        super.setImageDrawable(imageDrawable);
        final PhotoViewAttacher attacher = this.attacher;
        if (attacher != null) {
            attacher.update();
        }
    }
    
    public void setImageResource(final int imageResource) {
        super.setImageResource(imageResource);
        final PhotoViewAttacher attacher = this.attacher;
        if (attacher != null) {
            attacher.update();
        }
    }
    
    public void setImageURI(final Uri imageURI) {
        super.setImageURI(imageURI);
        final PhotoViewAttacher attacher = this.attacher;
        if (attacher != null) {
            attacher.update();
        }
    }
    
    public void setMaximumScale(final float maximumScale) {
        this.attacher.setMaximumScale(maximumScale);
    }
    
    public void setMediumScale(final float mediumScale) {
        this.attacher.setMediumScale(mediumScale);
    }
    
    public void setMinimumScale(final float minimumScale) {
        this.attacher.setMinimumScale(minimumScale);
    }
    
    public void setOnClickListener(final View$OnClickListener onClickListener) {
        this.attacher.setOnClickListener(onClickListener);
    }
    
    public void setOnDoubleTapListener(final GestureDetector$OnDoubleTapListener onDoubleTapListener) {
        this.attacher.setOnDoubleTapListener(onDoubleTapListener);
    }
    
    public void setOnLongClickListener(final View$OnLongClickListener onLongClickListener) {
        this.attacher.setOnLongClickListener(onLongClickListener);
    }
    
    public void setOnMatrixChangeListener(final OnMatrixChangedListener onMatrixChangeListener) {
        this.attacher.setOnMatrixChangeListener(onMatrixChangeListener);
    }
    
    public void setOnOutsidePhotoTapListener(final OnOutsidePhotoTapListener onOutsidePhotoTapListener) {
        this.attacher.setOnOutsidePhotoTapListener(onOutsidePhotoTapListener);
    }
    
    public void setOnPhotoTapListener(final OnPhotoTapListener onPhotoTapListener) {
        this.attacher.setOnPhotoTapListener(onPhotoTapListener);
    }
    
    public void setOnScaleChangeListener(final OnScaleChangedListener onScaleChangeListener) {
        this.attacher.setOnScaleChangeListener(onScaleChangeListener);
    }
    
    public void setOnSingleFlingListener(final OnSingleFlingListener onSingleFlingListener) {
        this.attacher.setOnSingleFlingListener(onSingleFlingListener);
    }
    
    public void setOnViewDragListener(final OnViewDragListener onViewDragListener) {
        this.attacher.setOnViewDragListener(onViewDragListener);
    }
    
    public void setOnViewTapListener(final OnViewTapListener onViewTapListener) {
        this.attacher.setOnViewTapListener(onViewTapListener);
    }
    
    public void setRotationBy(final float rotationBy) {
        this.attacher.setRotationBy(rotationBy);
    }
    
    public void setRotationTo(final float rotationTo) {
        this.attacher.setRotationTo(rotationTo);
    }
    
    public void setScale(final float scale) {
        this.attacher.setScale(scale);
    }
    
    public void setScale(final float n, final float n2, final float n3, final boolean b) {
        this.attacher.setScale(n, n2, n3, b);
    }
    
    public void setScale(final float n, final boolean b) {
        this.attacher.setScale(n, b);
    }
    
    public void setScaleLevels(final float n, final float n2, final float n3) {
        this.attacher.setScaleLevels(n, n2, n3);
    }
    
    public void setScaleType(final ImageView$ScaleType imageView$ScaleType) {
        final PhotoViewAttacher attacher = this.attacher;
        if (attacher == null) {
            this.pendingScaleType = imageView$ScaleType;
        }
        else {
            attacher.setScaleType(imageView$ScaleType);
        }
    }
    
    public boolean setSuppMatrix(final Matrix displayMatrix) {
        return this.attacher.setDisplayMatrix(displayMatrix);
    }
    
    public void setZoomTransitionDuration(final int zoomTransitionDuration) {
        this.attacher.setZoomTransitionDuration(zoomTransitionDuration);
    }
    
    public void setZoomable(final boolean zoomable) {
        this.attacher.setZoomable(zoomable);
    }
}
