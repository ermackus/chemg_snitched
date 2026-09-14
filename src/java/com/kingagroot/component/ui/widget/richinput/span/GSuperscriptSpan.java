package com.kingagroot.component.ui.widget.richinput.span;

import android.text.TextPaint;
import android.text.style.SuperscriptSpan;

public class GSuperscriptSpan extends SuperscriptSpan implements BaseSpan
{
    private static final float scale = 0.7f;
    
    public boolean equals(final Object o) {
        return o instanceof GSuperscriptSpan;
    }
    
    public String getTagName() {
        return "sup";
    }
    
    public int hashCode() {
        return this.getTagName().hashCode();
    }
    
    public BaseSpan newInstance() {
        return (BaseSpan)new GSuperscriptSpan();
    }
    
    public void updateDrawState(final TextPaint textPaint) {
        textPaint.baselineShift = (int)(textPaint.ascent() / 2.0f) + 0;
        textPaint.setTextSize(textPaint.getTextSize() * 0.7f);
    }
    
    public void updateMeasureState(final TextPaint textPaint) {
        textPaint.baselineShift = (int)(textPaint.ascent() / 2.0f) + 0;
        textPaint.setTextSize(textPaint.getTextSize() * 0.7f);
    }
}
