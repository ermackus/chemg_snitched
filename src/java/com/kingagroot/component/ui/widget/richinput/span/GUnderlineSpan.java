package com.kingagroot.component.ui.widget.richinput.span;

import android.text.style.UnderlineSpan;

public class GUnderlineSpan extends UnderlineSpan implements BaseSpan
{
    public String getTagName() {
        return "u";
    }
    
    public BaseSpan newInstance() {
        return (BaseSpan)new GUnderlineSpan();
    }
}
