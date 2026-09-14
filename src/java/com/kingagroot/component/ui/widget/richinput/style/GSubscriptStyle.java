package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GSubscriptSpan;

public class GSubscriptStyle extends GscriptStyle<GSubscriptSpan>
{
    public GSubscriptStyle() {
    }
    
    public GSubscriptStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
    }
    
    @Override
    GSubscriptSpan creatSpan() {
        return new GSubscriptSpan();
    }
}
