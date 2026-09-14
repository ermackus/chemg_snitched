package com.kingagroot.component.ui.widget.richinput.span;

import android.text.TextPaint;
import android.text.style.SubscriptSpan;

public class GSubscriptSpan extends SubscriptSpan implements BaseSpan
{
    private static final float scale = 0.7f;
    
    public boolean equals(final Object o) {
        return o instanceof GSubscriptSpan;
    }
    
    public String getTagName() {
        return "sub";
    }
    
    public int hashCode() {
        return this.getTagName().hashCode();
    }
    
    public BaseSpan newInstance() {
        return (BaseSpan)new GSubscriptSpan();
    }
    
    public void updateDrawState(final TextPaint textPaint) {
        textPaint.baselineShift = 0 - (int)(textPaint.ascent() / 4.0f);
        textPaint.setTextSize(textPaint.getTextSize() * 0.7f);
    }
    
    public void updateMeasureState(final TextPaint textPaint) {
        textPaint.baselineShift = 0 - (int)(textPaint.ascent() / 4.0f);
        textPaint.setTextSize(textPaint.getTextSize() * 0.7f);
    }
}
