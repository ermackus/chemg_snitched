package com.kingagroot.component.ui.widget.richinput.style;

import android.text.Editable;
import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GBlodSpan;

public class GBlodStyle extends MetricAffectingStyle<GBlodSpan>
{
    public GBlodStyle() {
    }
    
    public GBlodStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
        if (baseStyleView != null) {
            baseStyleView.setStyleClass(this.styleClass);
        }
    }
    
    public void applyStyle(final Editable editable, final int n, final int n2) {
        super.applyStyle(editable, n, n2);
    }
    
    GBlodSpan creatSpan() {
        return new GBlodSpan();
    }
}
