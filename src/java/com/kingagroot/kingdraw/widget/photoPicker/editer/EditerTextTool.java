package com.kingagroot.kingdraw.widget.photoPicker.editer;

import android.view.MotionEvent;
import android.graphics.Rect;
import android.graphics.Paint$Style;
import android.graphics.Canvas;
import android.graphics.Paint;

public class EditerTextTool extends EditerBaseView
{
    Paint paint;
    
    public EditerTextTool(final ImageEditView imageEditView) {
        super(imageEditView);
        this.paint = new Paint();
    }
    
    public void cancle() {
    }
    
    protected void onDraw(final Canvas canvas) {
        this.paint.setColor(-65536);
        this.paint.setStyle(Paint$Style.FILL);
        canvas.drawRect(new Rect(0, 0, 500, 500), this.paint);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return false;
    }
    
    public void save() {
    }
}
