package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import com.kingagroot.component.ui.widget.richinput.span.GChemStyleSpan;
import com.kingagroot.component.ui.widget.richinput.span.GChemSubSpan;
import android.text.Editable;
import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;

public class GscriptStyle<E> extends MetricAffectingStyle<E>
{
    public GscriptStyle() {
    }
    
    public GscriptStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
    }
    
    public void addSpan(final Editable editable, final int n, final int n2) {
        if (n < n2) {
            final GChemSubSpan[] array = (GChemSubSpan[])editable.getSpans(n, n2, (Class)GChemSubSpan.class);
            final GChemStyleSpan[] array2 = (GChemStyleSpan[])editable.getSpans(n, n2, (Class)GChemStyleSpan.class);
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
    
    E creatSpan() {
        return null;
    }
}
