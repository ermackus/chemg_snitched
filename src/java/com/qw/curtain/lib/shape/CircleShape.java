package com.qw.curtain.lib.shape;

import android.graphics.RectF;
import com.qw.curtain.lib.HollowInfo;
import android.graphics.Paint;
import android.graphics.Canvas;

public class CircleShape implements Shape
{
    public void drawShape(final Canvas canvas, final Paint paint, final HollowInfo hollowInfo) {
        canvas.drawOval(new RectF((float)hollowInfo.targetBound.left, (float)hollowInfo.targetBound.top, (float)hollowInfo.targetBound.right, (float)hollowInfo.targetBound.bottom), paint);
    }
}
