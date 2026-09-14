package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GBlodItalicSpan;

public class GBlodItalicStyle extends MetricAffectingStyle<GBlodItalicSpan>
{
    public GBlodItalicStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
    }
    
    GBlodItalicSpan creatSpan() {
        return new GBlodItalicSpan();
    }
}
