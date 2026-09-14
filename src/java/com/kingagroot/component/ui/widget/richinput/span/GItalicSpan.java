package com.kingagroot.component.ui.widget.richinput.span;

import android.text.style.StyleSpan;

public class GItalicSpan extends StyleSpan implements BaseSpan
{
    public GItalicSpan() {
        super(2);
    }
    
    public boolean equals(final Object o) {
        return o instanceof GItalicSpan;
    }
    
    public String getTagName() {
        return "i";
    }
    
    public int hashCode() {
        return this.getTagName().hashCode();
    }
    
    public BaseSpan newInstance() {
        return (BaseSpan)new GItalicSpan();
    }
}
