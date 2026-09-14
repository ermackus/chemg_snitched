package com.kingagroot.component.ui.widget.richinput.style;

import android.text.Editable;
import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GChemSubSpan;

public class GChemSubStyle extends MetricAffectingStyle<GChemSubSpan>
{
    public GChemSubStyle() {
    }
    
    public GChemSubStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
    }
    
    private int findNumEndIndex(final Editable editable, int n, final int n2) {
        int n3;
        while (true) {
            n3 = n2;
            if (n >= n2) {
                break;
            }
            final int n4 = n + 1;
            if (!Character.isDigit(editable.subSequence(n, n4).charAt(0))) {
                n3 = n;
                break;
            }
            n = n4;
        }
        return n3;
    }
    
    public void applyStyle(final Editable editable, final int n, final int n2) {
        if (this.isCheck()) {
            if (n < n2) {
                int i = n;
                while (i < n2) {
                    final int n3 = i + 1;
                    if (Character.isDigit(editable.subSequence(i, n3).charAt(0))) {
                        final int numEndIndex = this.findNumEndIndex(editable, n3, n2);
                        this.addSpan(editable, i, numEndIndex);
                        i = numEndIndex + 1;
                    }
                    else {
                        this.removeSpan(editable, i, n3);
                        i = n3;
                    }
                }
            }
        }
        else {
            this.removeSpan(editable, n, n2);
        }
        this.clearEmptySpan(editable, n, n2);
    }
    
    GChemSubSpan creatSpan() {
        return new GChemSubSpan();
    }
}
