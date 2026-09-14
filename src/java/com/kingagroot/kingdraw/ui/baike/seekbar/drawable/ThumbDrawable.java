package com.kingagroot.kingdraw.ui.baike.seekbar.drawable;

import android.graphics.Canvas;
import android.graphics.RectF;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.content.Context;

public class ThumbDrawable extends SeekBarBaseDrawable
{
    public ThumbDrawable(final Context context) {
        this.paint.setColor(context.getResources().getColor(2131099773));
    }
    
    public int getSize() {
        return GDensityUtil.dp2px(26.0f);
    }
    
    public boolean isInArea(final float n, final float n2) {
        final RectF bound = this.getBound();
        return bound != null && new RectF(bound).contains(n, n2);
    }
    
    public void onDraw(final Canvas canvas) {
        final RectF bound = this.getBound();
        if (bound != null) {
            canvas.drawCircle(bound.centerX(), bound.centerY(), (float)(this.getSize() / 2), this.paint);
        }
    }
}
