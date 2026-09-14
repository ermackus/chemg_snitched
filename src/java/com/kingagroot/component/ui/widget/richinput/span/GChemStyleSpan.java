package com.kingagroot.component.ui.widget.richinput.span;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class GChemStyleSpan extends MetricAffectingSpan implements BaseSpan
{
    public boolean equals(final Object o) {
        return o instanceof GChemStyleSpan;
    }
    
    public String getTagName() {
        return "chem";
    }
    
    public int hashCode() {
        return this.getTagName().hashCode();
    }
    
    public BaseSpan newInstance() {
        return (BaseSpan)new GChemStyleSpan();
    }
    
    public void updateDrawState(final TextPaint textPaint) {
    }
    
    public void updateMeasureState(final TextPaint textPaint) {
    }
}
