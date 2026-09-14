package com.kingagroot.kingdraw.core.graphics.svg.utils;

public class CSSParseException extends Exception
{
    public CSSParseException(final String s) {
        super(s);
    }
    
    public CSSParseException(final String s, final Exception ex) {
        super(s, (Throwable)ex);
    }
}
