package com.kingagroot.kingdraw.core.graphics;

import android.graphics.RectF;
import android.graphics.PorterDuff$Mode;
import android.graphics.Paint;
import android.graphics.Matrix;
import com.kingagroot.kingdraw.core.view.DrawBaseView;

public class CanvasHolder extends BaseCanvasHolder
{
    private DrawBaseView drawView;
    
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
                if (this.canvas != null) {
                    if (this.matrix != null) {
                        this.canvas.concat(this.matrix);
                    }
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
        }
    }
    
    public void setDrawView(final DrawBaseView drawView) {
        this.drawView = drawView;
    }
}
