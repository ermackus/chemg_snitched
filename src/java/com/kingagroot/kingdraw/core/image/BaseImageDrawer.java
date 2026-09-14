package com.kingagroot.kingdraw.core.image;

import android.graphics.Matrix;
import com.kingagroot.kingdraw.core.model.FormatValue;
import android.graphics.RectF;
import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import com.kingagroot.kingdraw.core.graphics.ImageCanvasHolder;
import com.kingagroot.kingdraw.core.graphics.CanvasController;
import android.graphics.Canvas;
import com.kingagroot.kingdraw.core.view.DrawBaseView;

public abstract class BaseImageDrawer implements DrawBaseView
{
    protected int backgroundColor;
    protected Canvas canvas;
    protected CanvasController canvasController;
    protected ImageCanvasHolder canvasHolder;
    protected Bitmap$Config config;
    protected Bitmap drawBitmap;
    protected DrawOption drawOption;
    
    public BaseImageDrawer() {
        this.backgroundColor = -1;
        this.config = Bitmap$Config.ARGB_8888;
    }
    
    public void endDraw(final Canvas canvas) {
        final DrawOption drawOption = this.drawOption;
        if (drawOption != null) {
            drawOption.drawEnd(canvas);
        }
    }
    
    public abstract RectF getBounds();
    
    public CanvasController getCanvasController() {
        return this.canvasController;
    }
    
    public Bitmap getDrawBitmap() {
        return this.drawBitmap;
    }
    
    public abstract String getPaletteId();
    
    public boolean isAvailable() {
        return true;
    }
    
    public void onDestroy() {
        final ImageCanvasHolder canvasHolder = this.canvasHolder;
        if (canvasHolder != null) {
            canvasHolder.onDestroy();
        }
    }
    
    public void setBackgroundColor(final int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public void setConfig(final Bitmap$Config config) {
        this.config = config;
    }
    
    public void setDrawOption(final DrawOption drawOption) {
        this.drawOption = drawOption;
    }
    
    public abstract void setFileContent(final String p0, final FormatValue p1);
    
    public Canvas startDraw() {
        final RectF bounds = this.getBounds();
        final DrawOption drawOption = this.drawOption;
        RectF drawBound = bounds;
        if (drawOption != null) {
            drawBound = drawOption.drawBound(bounds);
        }
        if (this.canvas == null) {
            this.drawBitmap = Bitmap.createBitmap((int)drawBound.width(), (int)drawBound.height(), this.config);
            this.canvas = new Canvas(this.drawBitmap);
            this.canvasHolder.setBounds(drawBound);
        }
        final Matrix matrix = new Matrix();
        final DrawOption drawOption2 = this.drawOption;
        Matrix drawMatrix = matrix;
        if (drawOption2 != null) {
            drawMatrix = drawOption2.drawMatrix(matrix);
        }
        this.canvas.concat(drawMatrix);
        this.canvas.drawColor(this.backgroundColor);
        final DrawOption drawOption3 = this.drawOption;
        if (drawOption3 != null) {
            drawOption3.drawStart(this.canvas);
        }
        return this.canvas;
    }
    
    public void updataNotification() {
    }
}
