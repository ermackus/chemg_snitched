package com.kingagroot.kingdraw.core.graphics;

import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.Matrix;
import com.kingagroot.kingdraw.core.view.DrawBaseView;

public class ImageCanvasHolder extends BaseCanvasHolder
{
    private DrawBaseView drawView;
    
    public void drawEnd() {
        synchronized (this) {
            if (this.canvas != null && this.drawView != null) {
                this.drawView.endDraw(this.canvas);
            }
        }
    }
    
    public void drawStart() {
        synchronized (this) {
            this.canvas = null;
            if (this.drawView != null && this.drawView.isAvailable()) {
                this.canvas = this.drawView.startDraw();
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
