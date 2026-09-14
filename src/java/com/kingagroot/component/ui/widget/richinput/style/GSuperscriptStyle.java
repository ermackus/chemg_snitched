package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GSuperscriptSpan;

public class GSuperscriptStyle extends GscriptStyle<GSuperscriptSpan>
{
    public GSuperscriptStyle() {
    }
    
    public GSuperscriptStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
    }
    
    @Override
    GSuperscriptSpan creatSpan() {
        return new GSuperscriptSpan();
    }
}
