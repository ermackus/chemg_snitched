package com.kingagroot.component.ui.view;

import android.graphics.Path;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;

public class RotateTextView extends AppCompatTextView
{
    private int rotateColor;
    
    public RotateTextView(final Context context) {
        super(context);
        this.rotateColor = -16777216;
    }
    
    public RotateTextView(final Context context, final AttributeSet set) {
        super(context, set);
        this.rotateColor = -16777216;
    }
    
    public int getRotateColor() {
        return this.rotateColor;
    }
    
    protected void onDraw(final Canvas canvas) {
        final int measuredWidth = this.getMeasuredWidth();
        final float n = (float)measuredWidth;
        canvas.rotate(-45.0f, n, n);
        final Paint paint = new Paint();
        paint.setColor(this.rotateColor);
        paint.setStyle(Paint$Style.FILL);
        final Path path = new Path();
        final float n2 = (float)(measuredWidth * 3 / 4);
        path.moveTo(n2, n2);
        path.lineTo((float)(int)(measuredWidth * 1.67), n2);
        path.lineTo(n, n);
        path.close();
        canvas.drawPath(path, paint);
    }
    
    public void setRotateColor(final int rotateColor) {
        this.rotateColor = rotateColor;
        this.invalidate();
    }
}
