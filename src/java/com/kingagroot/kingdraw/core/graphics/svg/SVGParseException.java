package com.kingagroot.kingdraw.core.graphics.svg;

import org.xml.sax.SAXException;

public class SVGParseException extends SAXException
{
    public SVGParseException(final String s) {
        super(s);
    }
    
    public SVGParseException(final String s, final Exception ex) {
        super(s, ex);
    }
}
