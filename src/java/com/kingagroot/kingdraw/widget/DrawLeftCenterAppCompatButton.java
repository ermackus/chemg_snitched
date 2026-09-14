package com.kingagroot.kingdraw.widget;

import android.graphics.drawable.Drawable;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import androidx.appcompat.widget.AppCompatButton;

public class DrawLeftCenterAppCompatButton extends AppCompatButton
{
    public DrawLeftCenterAppCompatButton(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    private Canvas getTopCanvas(final Canvas canvas) {
        final Drawable[] compoundDrawables = this.getCompoundDrawables();
        if (compoundDrawables == null) {
            return canvas;
        }
        Drawable drawable;
        if ((drawable = compoundDrawables[0]) == null) {
            drawable = compoundDrawables[2];
        }
        final float n = this.getPaint().measureText(this.getText().toString()) + drawable.getIntrinsicWidth() + this.getCompoundDrawablePadding();
        this.setPadding(0, 0, (int)(this.getWidth() - n), 0);
        canvas.translate((this.getWidth() - n) / 2.0f, 0.0f);
        return canvas;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(this.getTopCanvas(canvas));
    }
}
