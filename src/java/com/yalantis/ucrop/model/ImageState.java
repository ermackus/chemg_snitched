package com.yalantis.ucrop.model;

import android.graphics.RectF;

public class ImageState
{
    private RectF mCropRect;
    private float mCurrentAngle;
    private RectF mCurrentImageRect;
    private float mCurrentScale;
    
    public ImageState(final RectF mCropRect, final RectF mCurrentImageRect, final float mCurrentScale, final float mCurrentAngle) {
        this.mCropRect = mCropRect;
        this.mCurrentImageRect = mCurrentImageRect;
        this.mCurrentScale = mCurrentScale;
        this.mCurrentAngle = mCurrentAngle;
    }
    
    public RectF getCropRect() {
        return this.mCropRect;
    }
    
    public float getCurrentAngle() {
        return this.mCurrentAngle;
    }
    
    public RectF getCurrentImageRect() {
        return this.mCurrentImageRect;
    }
    
    public float getCurrentScale() {
        return this.mCurrentScale;
    }
}
