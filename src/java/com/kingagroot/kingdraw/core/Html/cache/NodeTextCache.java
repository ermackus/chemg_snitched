package com.kingagroot.kingdraw.core.Html.cache;

import com.kingagroot.kingdraw.core.Html.BaseText;
import androidx.collection.LruCache;

class NodeTextCache implements BaseHtmlCache
{
    private LruCache<String, BaseText> LruCache;
    
    protected NodeTextCache() {
        this.LruCache = (LruCache<String, BaseText>)new LruCache((int)Runtime.getRuntime().maxMemory() / 1024 / 20);
    }
    
    public void addCache(final String s, final BaseText baseText) {
        this.LruCache.put((Object)s, (Object)baseText);
    }
    
    public void clearCache() {
        this.LruCache.evictAll();
    }
    
    public BaseText getTextLine(final String s) {
        return (BaseText)this.LruCache.get((Object)s);
    }
}
