package com.alipay.sdk.m.e;

import java.util.Iterator;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public final class f
{
    public static List<j> a;
    
    static {
        (f.a = (List<j>)new ArrayList()).add((Object)new l());
        f.a.add((Object)new d());
        f.a.add((Object)new c());
        f.a.add((Object)new h());
        f.a.add((Object)new b());
        f.a.add((Object)new a());
        f.a.add((Object)new g());
    }
    
    public static String a(Object b) {
        if (b == null) {
            return null;
        }
        b = b(b);
        if (com.alipay.sdk.m.f.a.a(b.getClass())) {
            return org.json.alipay.b.c(b.toString());
        }
        if (Collection.class.isAssignableFrom(b.getClass())) {
            return new org.json.alipay.a((Collection)b).toString();
        }
        if (Map.class.isAssignableFrom(b.getClass())) {
            return new org.json.alipay.b((Map)b).toString();
        }
        final StringBuilder sb = new StringBuilder("Unsupported Class : ");
        sb.append((Object)b.getClass());
        throw new IllegalArgumentException(sb.toString());
    }
    
    public static Object b(final Object o) {
        if (o == null) {
            return null;
        }
        for (final j j : f.a) {
            if (j.a(o.getClass())) {
                final Object a = j.a(o);
                if (a != null) {
                    return a;
                }
                continue;
            }
        }
        final StringBuilder sb = new StringBuilder("Unsupported Class : ");
        sb.append((Object)o.getClass());
        throw new IllegalArgumentException(sb.toString());
    }
}
