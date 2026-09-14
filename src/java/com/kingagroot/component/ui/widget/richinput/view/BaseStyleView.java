package com.kingagroot.component.ui.widget.richinput.view;

import android.text.Editable;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import java.util.List;
import android.content.Context;

public interface BaseStyleView
{
    Context getContext();
    
    List<OnClickListener> getOnClickListener();
    
    boolean isCheck();
    
    void setBaseRichEditor(final BaseRichEditor p0);
    
    void setCheck(final boolean p0);
    
    void setOnClickListener(final OnClickListener p0);
    
    void setStyleClass(final Class<? extends BaseSpan> p0);
    
    public interface OnClickListener
    {
        void onClick(final Editable p0, final int p1, final int p2);
    }
}
