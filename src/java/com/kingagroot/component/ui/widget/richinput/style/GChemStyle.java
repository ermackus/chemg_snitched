package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import com.kingagroot.component.ui.widget.richinput.span.GSubscriptSpan;
import com.kingagroot.component.ui.widget.richinput.span.GSuperscriptSpan;
import android.text.Editable;
import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GChemStyleSpan;

public class GChemStyle extends MetricAffectingStyle<GChemStyleSpan>
{
    private final GChemSubStyle chemSubStyle;
    
    public GChemStyle() {
        this.chemSubStyle = new GChemSubStyle();
    }
    
    public GChemStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
        this.chemSubStyle = new GChemSubStyle(baseStyleView);
    }
    
    public void addSpan(final Editable editable, final int n, final int n2) {
        if (n < n2) {
            final GSuperscriptSpan[] array = (GSuperscriptSpan[])editable.getSpans(n, n2, (Class)GSuperscriptSpan.class);
            final GSubscriptSpan[] array2 = (GSubscriptSpan[])editable.getSpans(n, n2, (Class)GSubscriptSpan.class);
            final int length = array.length;
            final int n3 = 0;
            for (int i = 0; i < length; ++i) {
                this.removeMarginSapan(editable, n, n2, (BaseSpan)array[i]);
            }
            for (int length2 = array2.length, j = n3; j < length2; ++j) {
                this.removeMarginSapan(editable, n, n2, (BaseSpan)array2[j]);
            }
        }
        super.addSpan(editable, n, n2);
    }
    
    public void applyStyle(final Editable editable, final int n, final int n2) {
        super.applyStyle(editable, n, n2);
        this.chemSubStyle.applyStyle(editable, n, n2);
    }
    
    GChemStyleSpan creatSpan() {
        return new GChemStyleSpan();
    }
    
    public void setCheck(final boolean b) {
        super.setCheck(b);
        this.chemSubStyle.setCheck(b);
    }
}
