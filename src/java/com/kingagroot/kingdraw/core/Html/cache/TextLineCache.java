package com.kingagroot.kingdraw.core.Html.cache;

import com.kingagroot.kingdraw.core.Html.BaseText;

public class TextLineCache extends TextCache
{
    private static TextLineCache textCache;
    
    public static TextLineCache getInstance() {
        if (TextLineCache.textCache == null) {
            TextLineCache.textCache = new TextLineCache();
        }
        return TextLineCache.textCache;
    }
}
