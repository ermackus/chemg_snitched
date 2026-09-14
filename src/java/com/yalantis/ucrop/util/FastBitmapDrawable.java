package com.yalantis.ucrop.util;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class FastBitmapDrawable extends Drawable
{
    private int mAlpha;
    private Bitmap mBitmap;
    private int mHeight;
    private final Paint mPaint;
    private int mWidth;
    
    public FastBitmapDrawable(final Bitmap bitmap) {
        this.mPaint = new Paint(2);
        this.mAlpha = 255;
        this.setBitmap(bitmap);
    }
    
    public void draw(final Canvas canvas) {
        final Bitmap mBitmap = this.mBitmap;
        if (mBitmap != null && !mBitmap.isRecycled()) {
            canvas.drawBitmap(this.mBitmap, (Rect)null, this.getBounds(), this.mPaint);
        }
    }
    
    public int getAlpha() {
        return this.mAlpha;
    }
    
    public Bitmap getBitmap() {
        return this.mBitmap;
    }
    
    public int getIntrinsicHeight() {
        return this.mHeight;
    }
    
    public int getIntrinsicWidth() {
        return this.mWidth;
    }
    
    public int getMinimumHeight() {
        return this.mHeight;
    }
    
    public int getMinimumWidth() {
        return this.mWidth;
    }
    
    public int getOpacity() {
        return -3;
    }
    
    public void setAlpha(final int n) {
        this.mAlpha = n;
        this.mPaint.setAlpha(n);
    }
    
    public void setBitmap(final Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        if (mBitmap != null) {
            this.mWidth = mBitmap.getWidth();
            this.mHeight = this.mBitmap.getHeight();
        }
        else {
            this.mHeight = 0;
            this.mWidth = 0;
        }
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }
    
    public void setFilterBitmap(final boolean filterBitmap) {
        this.mPaint.setFilterBitmap(filterBitmap);
    }
}
