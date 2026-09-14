package com.kingagroot.kingdraw.core.view;

import android.graphics.Canvas;
import android.content.Context;
import com.kingagroot.kingdraw.core.graphics.CanvasHolder;
import android.view.TextureView;

class TextureDrawView extends TextureView implements DrawBaseView
{
    private CanvasHolder canvasHolder;
    
    public TextureDrawView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.canvasHolder = new CanvasHolder();
        this.setOpaque(false);
    }
    
    public void endDraw(final Canvas canvas) {
        this.unlockCanvasAndPost(canvas);
    }
    
    public CanvasHolder getCanvasHolder() {
        return this.canvasHolder;
    }
    
    public Canvas startDraw() {
        return this.lockCanvas();
    }
    
    public void updataNotification() {
    }
}
