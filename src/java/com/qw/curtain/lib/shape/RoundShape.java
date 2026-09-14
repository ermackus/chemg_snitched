package com.qw.curtain.lib.shape;

import android.graphics.RectF;
import com.qw.curtain.lib.HollowInfo;
import android.graphics.Paint;
import android.graphics.Canvas;

public class RoundShape implements Shape
{
    private float radius;
    
    public RoundShape(final float radius) {
        this.radius = radius;
    }
    
    public void drawShape(final Canvas canvas, final Paint paint, final HollowInfo hollowInfo) {
        final RectF rectF = new RectF((float)hollowInfo.targetBound.left, (float)hollowInfo.targetBound.top, (float)hollowInfo.targetBound.right, (float)hollowInfo.targetBound.bottom);
        final float radius = this.radius;
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }
}
