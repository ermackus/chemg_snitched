package com.luck.picture.lib.widget;

import android.text.TextPaint;
import android.graphics.Paint$Style;
import android.graphics.Canvas;
import android.content.res.TypedArray;
import com.luck.picture.lib.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;

public class MediumBoldTextView extends AppCompatTextView
{
    private float mStrokeWidth;
    
    public MediumBoldTextView(final Context context) {
        this(context, null);
    }
    
    public MediumBoldTextView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public MediumBoldTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mStrokeWidth = 0.6f;
        final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(set, R$styleable.PictureMediumBoldTextView, n, 0);
        this.mStrokeWidth = obtainStyledAttributes.getFloat(R$styleable.PictureMediumBoldTextView_stroke_Width, this.mStrokeWidth);
        obtainStyledAttributes.recycle();
    }
    
    protected void onDraw(final Canvas canvas) {
        final TextPaint paint = this.getPaint();
        final float strokeWidth = paint.getStrokeWidth();
        final float mStrokeWidth = this.mStrokeWidth;
        if (strokeWidth != mStrokeWidth) {
            paint.setStrokeWidth(mStrokeWidth);
            paint.setStyle(Paint$Style.FILL_AND_STROKE);
        }
        super.onDraw(canvas);
    }
    
    public void setStrokeWidth(final float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        this.invalidate();
    }
}
