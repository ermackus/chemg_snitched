package com.alipay.sdk.m.a0;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public final class d
{
    public static final Map<String, a> a;
    
    static {
        a = (Map)new ConcurrentHashMap();
    }
    
    public static String a(final String s) {
        final Map<String, a> a = d.a;
        if (a == null) {
            return null;
        }
        final a a2 = (a)a.get((Object)s);
        if (a2 == null) {
            return null;
        }
        if (System.currentTimeMillis() - a2.b < a2.c) {
            final String a3 = a2.a;
            if (a3 != null) {
                return a3;
            }
        }
        d.a.remove((Object)s);
        return null;
    }
    
    public static void a(final String s, final String s2) {
        String a = s2;
        if (s2 == null) {
            a = "";
        }
        a a2;
        if ((a2 = (a)d.a.get((Object)s)) == null) {
            a2 = new a();
        }
        a2.a = a;
        a2.c = 86400000L;
        a2.b = System.currentTimeMillis();
        d.a.put((Object)s, (Object)a2);
    }
    
    public static final class a
    {
        public String a;
        public long b;
        public long c;
    }
}
