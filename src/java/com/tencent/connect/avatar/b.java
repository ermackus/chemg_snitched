package com.tencent.connect.avatar;

import android.graphics.Color;
import android.graphics.Paint$Style;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class b extends View
{
    private Rect a;
    private Paint b;
    
    public b(final Context context) {
        super(context);
        this.b();
    }
    
    private void b() {
        this.b = new Paint();
    }
    
    public Rect a() {
        if (this.a == null) {
            this.a = new Rect();
            final int measuredWidth = this.getMeasuredWidth();
            final int measuredHeight = this.getMeasuredHeight();
            final int min = Math.min(Math.min(measuredHeight - 60 - 80, measuredWidth), 640);
            final int n = (measuredWidth - min) / 2;
            final int n2 = (measuredHeight - min) / 2;
            this.a.set(n, n2, n + min, min + n2);
        }
        return this.a;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final Rect a = this.a();
        final int measuredWidth = this.getMeasuredWidth();
        final int measuredHeight = this.getMeasuredHeight();
        this.b.setStyle(Paint$Style.FILL);
        this.b.setColor(Color.argb(100, 0, 0, 0));
        final float n = (float)measuredWidth;
        canvas.drawRect(0.0f, 0.0f, n, (float)a.top, this.b);
        canvas.drawRect(0.0f, (float)a.bottom, n, (float)measuredHeight, this.b);
        canvas.drawRect(0.0f, (float)a.top, (float)a.left, (float)a.bottom, this.b);
        canvas.drawRect((float)a.right, (float)a.top, n, (float)a.bottom, this.b);
        canvas.drawColor(Color.argb(100, 0, 0, 0));
        this.b.setStyle(Paint$Style.STROKE);
        this.b.setColor(-1);
        canvas.drawRect((float)a.left, (float)a.top, (float)(a.right - 1), (float)a.bottom, this.b);
    }
}
