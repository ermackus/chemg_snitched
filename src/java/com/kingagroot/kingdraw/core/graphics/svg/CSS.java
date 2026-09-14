package com.kingagroot.kingdraw.core.graphics.svg;

import com.kingagroot.kingdraw.core.graphics.svg.utils.CSSBase;

public class CSS extends CSSBase
{
    private CSS(final String s) {
        super(s);
    }
    
    public static CSS getFromString(final String s) {
        return new CSS(s);
    }
}
