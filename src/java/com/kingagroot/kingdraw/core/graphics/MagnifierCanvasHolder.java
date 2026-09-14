package com.kingagroot.kingdraw.core.graphics;

import android.graphics.RectF;
import android.graphics.PorterDuff$Mode;
import android.graphics.Paint;
import android.graphics.Matrix;
import android.graphics.PointF;
import com.kingagroot.kingdraw.core.view.DrawBaseView;

public class MagnifierCanvasHolder extends BaseCanvasHolder
{
    private DrawBaseView drawView;
    private CanvasHolder paletteCanvas;
    private PointF touchPoint;
    
    public MagnifierCanvasHolder(final DrawBaseView drawView) {
        this.touchPoint = new PointF();
        this.drawView = drawView;
    }
    
    public void drawEnd() {
        synchronized (this) {
            if (this.canvas != null && this.drawView != null) {
                this.drawView.endDraw(this.canvas);
                this.canvas = null;
            }
        }
    }
    
    public void drawStart() {
        synchronized (this) {
            if (this.canvas != null && this.drawView != null) {
                this.drawView.endDraw(this.canvas);
                this.canvas = null;
            }
            if (this.drawView != null && this.drawView.isAvailable()) {
                this.canvas = this.drawView.startDraw();
                if (this.canvas != null && this.paletteCanvas != null) {
                    final float[] center = this.getCenter();
                    final Matrix matrix = this.paletteCanvas.getMatrix();
                    final Matrix matrix2 = new Matrix();
                    float n = -this.touchPoint.x;
                    float n2 = -this.touchPoint.y;
                    if (matrix != null) {
                        final Matrix matrix3 = new Matrix();
                        matrix.invert(matrix3);
                        final float[] array = { this.touchPoint.x, this.touchPoint.y };
                        matrix3.mapPoints(array);
                        n = -array[0];
                        n2 = -array[1];
                    }
                    matrix2.setTranslate(n + center[0], n2 + center[1]);
                    this.canvas.concat(matrix2);
                    this.canvas.drawColor(0, PorterDuff$Mode.CLEAR);
                }
            }
        }
    }
    
    public void matrixChanged(final KDMatrix kdMatrix) {
        synchronized (this) {
            super.matrixChanged(kdMatrix);
            if (this.drawView != null) {
                this.drawView.updataNotification();
            }
        }
    }
    
    public void onDestroy() {
        synchronized (this) {
            super.onDestroy();
            if (this.drawView != null) {
                this.drawView = null;
            }
            if (this.paletteCanvas != null) {
                this.paletteCanvas = null;
            }
        }
    }
    
    public void setPaletteCanvas(final CanvasHolder paletteCanvas) {
        this.paletteCanvas = paletteCanvas;
    }
    
    public void setTouchPoint(final float x, final float y) {
        this.touchPoint.x = x;
        this.touchPoint.y = y;
    }
}
