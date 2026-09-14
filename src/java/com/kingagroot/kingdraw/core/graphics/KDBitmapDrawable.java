package com.kingagroot.kingdraw.core.graphics;

import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap;

public class KDBitmapDrawable extends KDDrawable
{
    private Bitmap bitmap;
    private Rect src;
    
    public KDBitmapDrawable(final Bitmap bitmap) {
        final Rect src = new Rect();
        this.src = src;
        if (bitmap != null) {
            this.bitmap = bitmap;
            src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            this.dst.set(0.0f, 0.0f, (float)bitmap.getWidth(), (float)bitmap.getHeight());
        }
    }
    
    public void draw(final Canvas canvas, final Paint paint) {
        if (this.isAvailable()) {
            canvas.drawBitmap(this.bitmap, this.src, this.dst, paint);
        }
    }
    
    public long getByteCount() {
        if (this.isAvailable()) {
            return this.bitmap.getByteCount();
        }
        return 0L;
    }
    
    public boolean isAvailable() {
        final Bitmap bitmap = this.bitmap;
        return bitmap != null && !bitmap.isRecycled();
    }
    
    public void release() {
        final Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.bitmap = null;
        }
    }
}
