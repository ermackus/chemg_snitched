package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GItalicSpan;

public class GItalicStyle extends MetricAffectingStyle<GItalicSpan>
{
    public GItalicStyle() {
    }
    
    public GItalicStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
        if (baseStyleView != null) {
            baseStyleView.setStyleClass(this.styleClass);
        }
    }
    
    GItalicSpan creatSpan() {
        return new GItalicSpan();
    }
}
