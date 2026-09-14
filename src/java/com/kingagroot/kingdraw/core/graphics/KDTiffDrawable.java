package com.kingagroot.kingdraw.core.graphics;

import android.graphics.RectF;
import android.graphics.Rect;
import android.graphics.Paint;
import android.graphics.Canvas;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import android.graphics.Bitmap;
import java.util.List;

public class KDTiffDrawable extends KDDrawable
{
    float bitmapHeight;
    List<Bitmap> bitmaps;
    
    public KDTiffDrawable(final List<Bitmap> list) {
        final ArrayList bitmaps = new ArrayList();
        this.bitmaps = (List<Bitmap>)bitmaps;
        this.bitmapHeight = 0.0f;
        if (list != null) {
            ((List)bitmaps).addAll((Collection)list);
            final Iterator iterator = list.iterator();
            float max = 0.0f;
            while (iterator.hasNext()) {
                final Bitmap bitmap = (Bitmap)iterator.next();
                max = Math.max(max, (float)bitmap.getWidth());
                this.bitmapHeight += bitmap.getHeight();
            }
            this.dst.set(0.0f, 0.0f, max, this.bitmapHeight);
        }
    }
    
    public void draw(final Canvas canvas, final Paint paint) {
        if (this.isAvailable()) {
            float top = this.dst.top;
            final int size = this.bitmaps.size();
            final float n = this.dst.height() / size;
            float n2;
            for (int i = 0; i < size; ++i, top = n2) {
                final Bitmap bitmap = (Bitmap)this.bitmaps.get(i);
                final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                final float left = this.dst.left;
                final float right = this.dst.right;
                n2 = top + n;
                canvas.drawBitmap(bitmap, rect, new RectF(left, top, right, n2), paint);
            }
        }
    }
    
    public long getByteCount() {
        final boolean available = this.isAvailable();
        int n = 0;
        int n2 = 0;
        if (available) {
            final Iterator iterator = this.bitmaps.iterator();
            while (true) {
                n = n2;
                if (!iterator.hasNext()) {
                    break;
                }
                n2 += ((Bitmap)iterator.next()).getByteCount();
            }
        }
        return n;
    }
    
    public boolean isAvailable() {
        if (this.bitmaps.size() == 0) {
            return false;
        }
        for (final Bitmap bitmap : this.bitmaps) {
            if (bitmap == null || bitmap.isRecycled()) {
                return false;
            }
        }
        return true;
    }
    
    public void release() {
        for (final Bitmap bitmap : this.bitmaps) {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
        this.bitmaps.clear();
    }
}
