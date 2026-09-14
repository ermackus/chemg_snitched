package com.alibaba.mtl.log.d;

import android.text.TextUtils;
import java.util.Comparator;
import java.util.Arrays;

public class g
{
    private static g a;
    private a a;
    private b a;
    
    static {
        g.a = new g();
    }
    
    private g() {
        this.a = new b();
        this.a = new a();
    }
    
    public static g a() {
        return g.a;
    }
    
    public String[] a(final String[] array, final boolean b) {
        Object o;
        if (b) {
            o = this.a;
        }
        else {
            o = this.a;
        }
        if (o != null && array != null && array.length > 0) {
            Arrays.sort((Object[])array, (Comparator)o);
            return array;
        }
        return null;
    }
    
    private class a implements Comparator<String>
    {
        final g b;
        
        private a(final g b) {
            this.b = b;
        }
        
        public int compare(final String s, final String s2) {
            if (!TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2)) {
                return s.compareTo(s2);
            }
            return 0;
        }
    }
    
    private class b implements Comparator<String>
    {
        final g b;
        
        private b(final g b) {
            this.b = b;
        }
        
        public int compare(final String s, final String s2) {
            if (!TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2)) {
                return s.compareTo(s2) * -1;
            }
            return 0;
        }
    }
}
