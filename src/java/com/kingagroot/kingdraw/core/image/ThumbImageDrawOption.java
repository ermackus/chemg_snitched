package com.kingagroot.kingdraw.core.image;

import android.graphics.Canvas;
import android.graphics.Paint$FontMetrics;
import android.graphics.Paint$Style;
import android.graphics.RectF;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.graphics.Paint;
import android.graphics.Matrix;

public class ThumbImageDrawOption implements DrawOption
{
    private static final String MARKSTR = "KingDraw";
    private static final int MarkTextSize;
    private static final int markPaddingUnit = 5;
    private float DEFAULT_HEIGHT;
    private float DEFAULT_WIDTH;
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
    
    public ThumbImageDrawOption() {
        this.DEFAULT_WIDTH = (float)GDensityUtil.dp2px(400.0f);
        this.DEFAULT_HEIGHT = (float)GDensityUtil.dp2px(400.0f);
        this.isMark = true;
        this.scale = 1.0f;
        this.textY = 0.0f;
        this.textX = 0.0f;
    }
    
    public RectF drawBound(final RectF rectF) {
        final float min = Math.min(this.DEFAULT_WIDTH / rectF.width(), this.DEFAULT_HEIGHT / rectF.height());
        this.scale = min;
        if (min > 1.0f) {
            this.scale = 1.0f;
        }
        final float default_WIDTH = this.DEFAULT_WIDTH;
        final float default_HEIGHT = this.DEFAULT_HEIGHT;
        final float n = default_WIDTH + 40.0f;
        final float n2 = default_HEIGHT + 40.0f;
        final float n3 = n / 2.0f;
        final float n4 = n2 / 2.0f;
        float n5 = n2;
        if (this.isMark) {
            (this.paint = new Paint()).setTextSize((float)ThumbImageDrawOption.MarkTextSize);
            this.paint.setAntiAlias(true);
            this.paint.setStyle(Paint$Style.FILL);
            this.paint.setColor(685850175);
            final float measureText = this.paint.measureText("KingDraw");
            final Paint$FontMetrics fontMetrics = this.paint.getFontMetrics();
            n5 = n2 + (fontMetrics.bottom - fontMetrics.top + 10.0f);
            this.textY = n5 - 10.0f;
            this.textX = n - measureText - 5.0f;
        }
        this.transX = rectF.centerX() - n3;
        this.transY = rectF.centerY() - n4;
        if (this.scale < 1.0f) {
            this.scaleCenterX = rectF.centerX() - this.transX;
            this.scaleCenterY = rectF.centerY() - this.transY;
        }
        return new RectF(0.0f, 0.0f, n, n5);
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
    
    public void setHeight(final float default_HEIGHT) {
        this.DEFAULT_HEIGHT = default_HEIGHT;
    }
    
    public void setMark(final boolean isMark) {
        this.isMark = isMark;
    }
    
    public void setWidth(final float default_WIDTH) {
        this.DEFAULT_WIDTH = default_WIDTH;
    }
}
