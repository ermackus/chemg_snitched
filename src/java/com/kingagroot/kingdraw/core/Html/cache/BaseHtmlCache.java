package com.kingagroot.kingdraw.core.Html.cache;

import com.kingagroot.kingdraw.core.Html.BaseText;

interface BaseHtmlCache
{
    void addCache(final String p0, final BaseText p1);
    
    void clearCache();
    
    BaseText getTextLine(final String p0);
}
