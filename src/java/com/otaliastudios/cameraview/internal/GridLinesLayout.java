package com.otaliastudios.cameraview.internal;

import android.graphics.Canvas;
import android.util.TypedValue;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.otaliastudios.cameraview.controls.Grid;
import android.view.View;

public class GridLinesLayout extends View
{
    public static final int DEFAULT_COLOR;
    private static final float GOLDEN_RATIO_INV = 0.618034f;
    DrawCallback callback;
    private int gridColor;
    private Grid gridMode;
    private ColorDrawable horiz;
    private ColorDrawable vert;
    private final float width;
    
    static {
        DEFAULT_COLOR = Color.argb(160, 255, 255, 255);
    }
    
    public GridLinesLayout(final Context context) {
        this(context, null);
    }
    
    public GridLinesLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.gridColor = GridLinesLayout.DEFAULT_COLOR;
        this.horiz = new ColorDrawable(this.gridColor);
        this.vert = new ColorDrawable(this.gridColor);
        this.width = TypedValue.applyDimension(1, 0.9f, context.getResources().getDisplayMetrics());
    }
    
    private int getLineCount() {
        final int n = GridLinesLayout$1.$SwitchMap$com$otaliastudios$cameraview$controls$Grid[this.gridMode.ordinal()];
        if (n == 2 || n == 3) {
            return 2;
        }
        if (n != 4) {
            return 0;
        }
        return 3;
    }
    
    private float getLinePosition(final int n) {
        final int lineCount = this.getLineCount();
        if (this.gridMode == Grid.DRAW_PHI) {
            float n2 = 0.38196602f;
            if (n != 1) {
                n2 = 0.618034f;
            }
            return n2;
        }
        return 1.0f / (lineCount + 1) * (n + 1.0f);
    }
    
    public int getGridColor() {
        return this.gridColor;
    }
    
    public Grid getGridMode() {
        return this.gridMode;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final int lineCount = this.getLineCount();
        for (int i = 0; i < lineCount; ++i) {
            final float linePosition = this.getLinePosition(i);
            canvas.translate(0.0f, this.getHeight() * linePosition);
            this.horiz.draw(canvas);
            final float n = -linePosition;
            canvas.translate(0.0f, this.getHeight() * n);
            canvas.translate(linePosition * this.getWidth(), 0.0f);
            this.vert.draw(canvas);
            canvas.translate(n * this.getWidth(), 0.0f);
        }
        final DrawCallback callback = this.callback;
        if (callback != null) {
            callback.onDraw(lineCount);
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.horiz.setBounds(n, 0, n3, (int)this.width);
        this.vert.setBounds(0, n2, (int)this.width, n4);
    }
    
    public void setGridColor(final int color) {
        this.gridColor = color;
        this.horiz.setColor(color);
        this.vert.setColor(color);
        this.postInvalidate();
    }
    
    public void setGridMode(final Grid gridMode) {
        this.gridMode = gridMode;
        this.postInvalidate();
    }
    
    interface DrawCallback
    {
        void onDraw(final int p0);
    }
}
