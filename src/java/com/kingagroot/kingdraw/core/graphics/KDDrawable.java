package com.kingagroot.kingdraw.core.graphics;

import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.RectF;

abstract class KDDrawable
{
    protected RectF dst;
    
    KDDrawable() {
        this.dst = new RectF();
    }
    
    public abstract void draw(final Canvas p0, final Paint p1);
    
    public abstract long getByteCount();
    
    public abstract boolean isAvailable();
    
    public abstract void release();
    
    public void setBounds(final float n, final float n2, final float n3, final float n4) {
        this.dst.set(n, n2, n3, n4);
    }
}
