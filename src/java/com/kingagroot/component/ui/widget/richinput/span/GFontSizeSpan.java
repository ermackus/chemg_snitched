package com.kingagroot.component.ui.widget.richinput.span;

import android.text.TextPaint;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.text.style.AbsoluteSizeSpan;

public class GFontSizeSpan extends AbsoluteSizeSpan implements BaseSpan
{
    public GFontSizeSpan(final int n) {
        super(n);
    }
    
    public int getSize() {
        return super.getSize();
    }
    
    public String getTagName() {
        return "font-size";
    }
    
    public int getUnitySize() {
        return (int)GDensityUtil.px2sp((float)this.getSize());
    }
    
    public BaseSpan newInstance() {
        return (BaseSpan)new GFontSizeSpan(this.getSize());
    }
    
    public void updateDrawState(final TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setTextSize(textPaint.getTextSize() * 1.8f);
        if (textPaint.baselineShift != 0) {
            if (textPaint.baselineShift > 0) {
                new GSubscriptSpan().updateDrawState(textPaint);
            }
            else {
                new GSuperscriptSpan().updateDrawState(textPaint);
            }
        }
    }
    
    public void updateMeasureState(final TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setTextSize(textPaint.getTextSize() * 1.8f);
        if (textPaint.baselineShift != 0) {
            if (textPaint.baselineShift > 0) {
                new GSubscriptSpan().updateMeasureState(textPaint);
            }
            else {
                new GSuperscriptSpan().updateMeasureState(textPaint);
            }
        }
    }
}
