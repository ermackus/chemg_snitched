package com.kingagroot.kingdraw.core.image;

import android.graphics.Canvas;
import android.graphics.Paint$FontMetrics;
import android.graphics.Paint$Style;
import android.graphics.RectF;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.graphics.Paint;
import android.graphics.Matrix;

public class ImageFileDrawOption implements DrawOption
{
    private static final int MAX_HEIGHT = 9000;
    private static final int MAX_WIDTH = 9000;
    private static final int MarkTextSize;
    private static final int markPadding = 5;
    private static final String markStr = "KingDraw";
    private boolean isMark;
    private Matrix matrix;
    private Paint paint;
    private float scale;
    private float scaleCenterX;
    private float scaleCenterY;
    private float textX;
    private float textY;
    private float transX;
    private float transY;
    
    static {
        MarkTextSize = GDensityUtil.sp2px(14.0f);
    }
    
    public ImageFileDrawOption() {
        this.isMark = true;
        this.scale = 1.0f;
        this.textY = 0.0f;
        this.textX = 0.0f;
    }
    
    public RectF drawBound(final RectF rectF) {
        float width = rectF.width();
        float height = rectF.height();
        final float min = Math.min(9000.0f / width, 9000.0f / height);
        this.scale = min;
        if (min < 1.0f) {
            width = (float)(int)(width * min);
            height = (float)(int)(min * height);
        }
        else {
            this.scale = 1.0f;
        }
        final float n = 60.0f / GDensityUtil.cm2px(0.508f);
        final float n2 = width * n + 20.0f;
        final float n3 = height * n + 20.0f;
        this.scale *= n;
        this.scaleCenterX = n2 / 2.0f;
        this.scaleCenterY = n3 / 2.0f;
        final float centerX = rectF.centerX();
        final float centerY = rectF.centerY();
        this.transX = centerX - this.scaleCenterX;
        this.transY = centerY - this.scaleCenterY;
        float n4 = n2;
        float n5 = n3;
        if (this.isMark) {
            (this.paint = new Paint()).setTextSize((float)ImageFileDrawOption.MarkTextSize);
            this.paint.setAntiAlias(true);
            this.paint.setStyle(Paint$Style.FILL);
            this.paint.setColor(685850175);
            final float measureText = this.paint.measureText("KingDraw");
            final Paint$FontMetrics fontMetrics = this.paint.getFontMetrics();
            final float bottom = fontMetrics.bottom;
            final float top = fontMetrics.top;
            final float n6 = measureText + 5.0f + 5.0f;
            n4 = n2;
            if (n2 < n6) {
                n4 = n6;
            }
            n5 = n3 + (bottom - top + 5.0f + 5.0f);
            this.textY = n5 - 10.0f;
            this.textX = n4 - measureText - 5.0f;
        }
        return new RectF(0.0f, 0.0f, n4, n5);
    }
    
    public void drawEnd(final Canvas canvas) {
        if (this.matrix != null) {
            final Matrix matrix = new Matrix();
            this.matrix.invert(matrix);
            canvas.concat(matrix);
        }
        if (this.isMark) {
            canvas.drawText("KingDraw", this.textX, this.textY, this.paint);
        }
    }
    
    public Matrix drawMatrix(final Matrix matrix) {
        matrix.preTranslate(-this.transX, -this.transY);
        final float scale = this.scale;
        matrix.postScale(scale, scale, this.scaleCenterX, this.scaleCenterY);
        return this.matrix = matrix;
    }
    
    public void drawStart(final Canvas canvas) {
    }
    
    public void setMark(final boolean isMark) {
        this.isMark = isMark;
    }
}
