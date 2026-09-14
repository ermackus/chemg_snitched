package com.alibaba.mtl.appmonitor.d;

import android.text.TextUtils;
import java.util.Set;

public class b
{
    private a a;
    private Set<String> c;
    
    public boolean b(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return false;
        }
        final boolean contains = this.c.contains((Object)s);
        if (this.a == b.a.b) {
            return contains;
        }
        return contains ^ true;
    }
    
    private enum a
    {
        private static final a[] a;
        
        b, 
        c;
    }
}
