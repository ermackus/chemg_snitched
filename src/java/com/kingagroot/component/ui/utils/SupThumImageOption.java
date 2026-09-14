package com.kingagroot.component.ui.utils;

import android.graphics.Canvas;
import android.graphics.Paint$FontMetrics;
import android.graphics.Paint$Style;
import android.graphics.RectF;
import android.content.Context;
import com.goodsrc.library.utils.SystemUtils;
import com.kingagroot.component.ui.UIComponentHelper;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.graphics.Paint;
import android.graphics.Matrix;
import com.kingagroot.kingdraw.core.image.DrawOption;

public class SupThumImageOption implements DrawOption
{
    private static final String MARKSTR = "KingDraw";
    private static final float MIN_HEIGHT;
    private static final float MIN_WIDTH;
    private static final int MarkTextSize;
    private static final int markPaddingUnit = 5;
    private final float MAX_HEIGHT;
    private final float MAX_WIDTH;
    private boolean isMark;
    private Matrix matrix;
    private Paint paint;
    private final float scale;
    private float scaleCenterX;
    private float scaleCenterY;
    private float textX;
    private float textY;
    private float transX;
    private float transY;
    
    static {
        MIN_WIDTH = (float)GDensityUtil.dp2px(90.0f);
        MIN_HEIGHT = (float)GDensityUtil.dp2px(100.0f);
        MarkTextSize = GDensityUtil.sp2px(14.0f);
    }
    
    public SupThumImageOption() {
        this.MAX_WIDTH = (float)(SystemUtils.getScreenWidth((Context)UIComponentHelper.getInstance()) * 3);
        this.MAX_HEIGHT = (float)(SystemUtils.getScreenHeight((Context)UIComponentHelper.getInstance()) * 3);
        this.isMark = false;
        this.scale = 1.0f;
        this.textY = 0.0f;
        this.textX = 0.0f;
    }
    
    public RectF drawBound(final RectF rectF) {
        final float width = rectF.width();
        final float height = rectF.height();
        final float min = Math.min(this.MAX_WIDTH / width, this.MAX_HEIGHT / height);
        final float min_WIDTH = SupThumImageOption.MIN_WIDTH;
        float n = width;
        if (width < min_WIDTH) {
            n = min_WIDTH;
        }
        final float min_HEIGHT = SupThumImageOption.MIN_HEIGHT;
        float n2 = height;
        if (height < min_HEIGHT) {
            n2 = min_HEIGHT;
        }
        final float n3 = fcmpg(min, 1.0f);
        float max_WIDTH = n;
        float max_HEIGHT = n2;
        if (n3 < 0) {
            max_WIDTH = this.MAX_WIDTH;
            max_HEIGHT = this.MAX_HEIGHT;
        }
        final float n4 = max_WIDTH / 2.0f;
        final float n5 = max_HEIGHT / 2.0f;
        float n6 = max_HEIGHT;
        if (this.isMark) {
            (this.paint = new Paint()).setTextSize((float)SupThumImageOption.MarkTextSize);
            this.paint.setAntiAlias(true);
            this.paint.setStyle(Paint$Style.FILL);
            this.paint.setColor(685850175);
            final float measureText = this.paint.measureText("KingDraw");
            final Paint$FontMetrics fontMetrics = this.paint.getFontMetrics();
            n6 = max_HEIGHT + (fontMetrics.bottom - fontMetrics.top + 10.0f);
            this.textY = n6 - 10.0f;
            this.textX = max_WIDTH - measureText - 5.0f;
        }
        this.transX = rectF.centerX() - n4;
        this.transY = rectF.centerY() - n5;
        if (n3 < 0) {
            this.scaleCenterX = rectF.centerX() - this.transX;
            this.scaleCenterY = rectF.centerY() - this.transY;
        }
        return new RectF(0.0f, 0.0f, max_WIDTH, n6);
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
        matrix.postScale(1.0f, 1.0f, this.scaleCenterX, this.scaleCenterY);
        return this.matrix = matrix;
    }
    
    public void drawStart(final Canvas canvas) {
    }
    
    public void setMark(final boolean isMark) {
        this.isMark = isMark;
    }
}
