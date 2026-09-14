package com.kingagroot.component.ui.widget.richinput.span;

import android.text.style.StyleSpan;

public class GBlodItalicSpan extends StyleSpan implements BaseSpan
{
    public GBlodItalicSpan() {
        super(3);
    }
    
    public int getStyle() {
        return 3;
    }
    
    public String getTagName() {
        return "bi";
    }
    
    public BaseSpan newInstance() {
        return (BaseSpan)new GBlodItalicSpan();
    }
}
