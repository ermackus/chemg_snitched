package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GFontColorSpan;

public class GFontColorStyle extends MetricAffectingStyle<GFontColorSpan>
{
    int color;
    
    public GFontColorStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
    }
    
    GFontColorSpan creatSpan() {
        return new GFontColorSpan(this.getColor());
    }
    
    public int getColor() {
        return this.color;
    }
    
    public void setColor(final int color) {
        this.color = color;
    }
}
