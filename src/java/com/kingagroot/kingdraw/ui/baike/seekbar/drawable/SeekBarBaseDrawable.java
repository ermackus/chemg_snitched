package com.kingagroot.kingdraw.ui.baike.seekbar.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public abstract class SeekBarBaseDrawable
{
    protected RectF bound;
    protected Paint paint;
    
    public SeekBarBaseDrawable() {
        (this.paint = new Paint()).setAntiAlias(true);
    }
    
    public RectF getBound() {
        return this.bound;
    }
    
    public abstract int getSize();
    
    public boolean isInArea(final float n, final float n2) {
        return false;
    }
    
    public abstract void onDraw(final Canvas p0);
    
    public void setBound(final RectF bound) {
        this.bound = bound;
    }
}
