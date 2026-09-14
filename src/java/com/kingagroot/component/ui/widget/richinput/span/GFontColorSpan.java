package com.kingagroot.component.ui.widget.richinput.span;

import android.text.style.ForegroundColorSpan;

public class GFontColorSpan extends ForegroundColorSpan implements BaseSpan
{
    public GFontColorSpan(final int n) {
        super(n);
    }
    
    public boolean equals(final Object o) {
        return o instanceof GFontColorSpan;
    }
    
    public String getTagName() {
        return "font-color";
    }
    
    public int hashCode() {
        return this.getTagName().hashCode();
    }
    
    public BaseSpan newInstance() {
        return (BaseSpan)new GFontColorSpan(this.getForegroundColor());
    }
}
