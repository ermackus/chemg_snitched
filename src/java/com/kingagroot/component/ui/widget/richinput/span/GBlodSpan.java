package com.kingagroot.component.ui.widget.richinput.span;

import android.text.style.StyleSpan;

public class GBlodSpan extends StyleSpan implements BaseSpan
{
    public GBlodSpan() {
        super(1);
    }
    
    public boolean equals(final Object o) {
        return o instanceof GBlodSpan;
    }
    
    public int getStyle() {
        return 1;
    }
    
    public String getTagName() {
        return "b";
    }
    
    public int hashCode() {
        return this.getTagName().hashCode();
    }
    
    public BaseSpan newInstance() {
        return (BaseSpan)new GBlodSpan();
    }
}
