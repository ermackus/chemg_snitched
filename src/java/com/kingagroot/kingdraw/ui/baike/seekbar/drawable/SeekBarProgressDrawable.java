package com.kingagroot.kingdraw.ui.baike.seekbar.drawable;

import android.graphics.RectF;
import android.graphics.Canvas;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.content.Context;

public class SeekBarProgressDrawable extends SeekBarBaseDrawable
{
    public SeekBarProgressDrawable(final Context context) {
        this.paint.setColor(context.getResources().getColor(2131099773));
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
