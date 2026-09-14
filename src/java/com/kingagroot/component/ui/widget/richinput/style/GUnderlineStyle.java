package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GUnderlineSpan;

public class GUnderlineStyle extends MetricAffectingStyle<GUnderlineSpan>
{
    public GUnderlineStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
    }
    
    GUnderlineSpan creatSpan() {
        return new GUnderlineSpan();
    }
}
