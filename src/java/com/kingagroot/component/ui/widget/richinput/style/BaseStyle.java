package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import android.text.Editable;

public interface BaseStyle<E>
{
    void addSpan(final Editable p0, final int p1, final int p2);
    
    void applyStyle(final Editable p0, final int p1, final int p2);
    
    BaseStyleView getView();
    
    boolean isCheck();
    
    void removeSpan(final Editable p0, final int p1, final int p2);
    
    void setCheck(final boolean p0);
}
