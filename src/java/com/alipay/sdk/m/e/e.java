package com.alipay.sdk.m.e;

import java.util.Iterator;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class e
{
    public static List<i> a;
    
    static {
        (e.a = (List<i>)new ArrayList()).add((Object)new l());
        e.a.add((Object)new d());
        e.a.add((Object)new c());
        e.a.add((Object)new h());
        e.a.add((Object)new k());
        e.a.add((Object)new b());
        e.a.add((Object)new a());
        e.a.add((Object)new g());
    }
    
    public static final <T> T a(final Object o, final Type type) {
        for (final i i : e.a) {
            if (i.a(com.alipay.sdk.m.f.a.a(type))) {
                final Object a = i.a(o, type);
                if (a != null) {
                    return (T)a;
                }
                continue;
            }
        }
        return null;
    }
    
    public static final Object a(String trim, final Type type) {
        if (trim != null && trim.length() != 0) {
            trim = trim.trim();
            Object o;
            if (trim.startsWith("[") && trim.endsWith("]")) {
                o = new org.json.alipay.a(trim);
            }
            else {
                if (!trim.startsWith("{") || !trim.endsWith("}")) {
                    return a((Object)trim, type);
                }
                o = new org.json.alipay.b(trim);
            }
            return a(o, type);
        }
        return null;
    }
}
