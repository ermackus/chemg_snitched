package com.kingagroot.kingdraw.core.graphics.svg.utils;

import com.kingagroot.kingdraw.core.graphics.svg.SVGExternalFileResolver;

public class CSSBase
{
    protected CSSParser.Ruleset cssRuleset;
    
    protected CSSBase(final String s) {
        this.cssRuleset = new CSSParser(CSSParser.Source.RenderOptions, null).parse(s);
    }
}
