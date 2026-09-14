package com.kingagroot.kingdraw.ui.baike.seekbar.drawable;

import android.graphics.RectF;
import android.graphics.Canvas;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;

public class SeekBarBackgroundDrawable extends SeekBarBaseDrawable
{
    public SeekBarBackgroundDrawable() {
        this.paint.setColor(-2434342);
    }
    
    public int getSize() {
        return GDensityUtil.dp2px(6.0f);
    }
    
    public void onDraw(final Canvas canvas) {
        final RectF bound = this.getBound();
        if (bound != null) {
            canvas.drawRect(bound, this.paint);
        }
    }
}
