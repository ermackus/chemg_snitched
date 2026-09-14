package com.kingagroot.component.ui.widget.richinput;

import android.text.Editable;
import com.kingagroot.component.ui.widget.richinput.style.BaseStyle;

public interface StyleBaseManager
{
    void addSpan(final BaseStyle p0);
    
    void applyStyle(final Editable p0, final int p1, final int p2);
    
    void onSelectionChanged(final int p0, final int p1);
}
