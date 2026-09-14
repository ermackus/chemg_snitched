package com.kingagroot.component.ui.widget.richinput.html;

import android.text.Spanned;
import com.kingagroot.component.ui.widget.richinput.GAlignEnum;

public class HtmlParserResult
{
    private GAlignEnum gAlignEnum;
    private Spanned spanned;
    
    public Spanned getSpanned() {
        return this.spanned;
    }
    
    public GAlignEnum getgAlignEnum() {
        return this.gAlignEnum;
    }
    
    public void setSpanned(final Spanned spanned) {
        this.spanned = spanned;
    }
    
    public void setgAlignEnum(final GAlignEnum gAlignEnum) {
        this.gAlignEnum = gAlignEnum;
    }
}
