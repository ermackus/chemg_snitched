package com.kingagroot.component.ui.utils;

import android.graphics.Canvas;
import android.graphics.Paint$FontMetrics;
import android.graphics.Paint$Style;
import com.kingagroot.component.ui.UIComponentHelper;
import android.view.WindowManager;
import android.graphics.RectF;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.graphics.Paint;
import android.graphics.Matrix;
import com.kingagroot.kingdraw.core.image.DrawOption;

public class AlbumImageOption implements DrawOption
{
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
    
    public AlbumImageOption() {
        this.isMark = true;
        this.scale = 1.0f;
        this.textY = 0.0f;
        this.textX = 0.0f;
    }
    
    public RectF drawBound(final RectF rectF) {
        final float width = rectF.width();
        final float height = rectF.height();
        float n = width;
        if (width < 0.0f) {
            n = 1.0f;
        }
        float n2 = height;
        if (height < 0.0f) {
            n2 = 1.0f;
        }
        final WindowManager windowManager = (WindowManager)UIComponentHelper.getInstance().getSystemService("window");
        final float n3 = n2 / n;
        final float n4 = (float)(windowManager.getDefaultDisplay().getHeight() * 3);
        float n5 = n3 * n4;
        final float scale = n5 / n2;
        this.scale = scale;
        if (scale < 1.0f) {
            n = n4;
        }
        else {
            this.scale = 1.0f;
            n5 = n2;
        }
        final float n6 = n + 20.0f;
        final float n7 = n5 + 20.0f;
        this.scaleCenterX = n6 / 2.0f;
        this.scaleCenterY = n7 / 2.0f;
        final float centerX = rectF.centerX();
        final float centerY = rectF.centerY();
        this.transX = centerX - this.scaleCenterX;
        this.transY = centerY - this.scaleCenterY;
        float n8 = n6;
        float n9 = n7;
        if (this.isMark) {
            (this.paint = new Paint()).setTextSize((float)AlbumImageOption.MarkTextSize);
            this.paint.setAntiAlias(true);
            this.paint.setStyle(Paint$Style.FILL);
            this.paint.setColor(685850175);
            final float measureText = this.paint.measureText("KingDraw");
            final Paint$FontMetrics fontMetrics = this.paint.getFontMetrics();
            final float bottom = fontMetrics.bottom;
            final float top = fontMetrics.top;
            final float n10 = measureText + 5.0f + 5.0f;
            n8 = n6;
            if (n6 < n10) {
                n8 = n10;
            }
            n9 = n7 + (bottom - top + 5.0f + 5.0f);
            this.textY = n9 - 10.0f;
            this.textX = n8 - measureText - 5.0f;
        }
        return new RectF(0.0f, 0.0f, n8, n9);
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
