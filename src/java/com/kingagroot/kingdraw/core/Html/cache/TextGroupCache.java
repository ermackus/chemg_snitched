package com.kingagroot.kingdraw.core.Html.cache;

import com.kingagroot.kingdraw.core.Html.BaseText;

public class TextGroupCache extends TextCache
{
    private static NodeTextCache nodeTextCache;
    private static TextGroupCache textCache;
    
    public static TextGroupCache getNodeTextCacheInstance() {
        if (TextGroupCache.textCache == null) {
            TextGroupCache.textCache = new TextGroupCache();
        }
        return TextGroupCache.textCache;
    }
    
    public static TextGroupCache getTextCacheInstance() {
        if (TextGroupCache.textCache == null) {
            TextGroupCache.textCache = new TextGroupCache();
        }
        return TextGroupCache.textCache;
    }
}
