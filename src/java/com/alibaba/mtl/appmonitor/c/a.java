package com.alibaba.mtl.appmonitor.c;

import java.util.HashMap;
import java.util.Map;

public class a
{
    private static a a;
    private Map<Class<? extends b>, c<? extends b>> o;
    
    static {
        com.alibaba.mtl.appmonitor.c.a.a = new a();
    }
    
    private a() {
        this.o = (Map<Class<? extends b>, c<? extends b>>)new HashMap();
    }
    
    public static a a() {
        return com.alibaba.mtl.appmonitor.c.a.a;
    }
    
    private <T extends b> c<T> a(final Class<T> clazz) {
        synchronized (this) {
            c c;
            if ((c = (c)this.o.get((Object)clazz)) == null) {
                c = new c();
                this.o.put((Object)clazz, (Object)c);
            }
            return c;
        }
    }
    
    public <T extends b> T a(final Class<T> clazz, final Object... array) {
        b a;
        final b b = a = this.a(clazz).a();
        if (b == null) {
            try {
                a = clazz.newInstance();
            }
            catch (final Exception ex) {
                com.alibaba.mtl.appmonitor.b.b.a((Throwable)ex);
                a = b;
            }
        }
        if (a != null) {
            a.fill(array);
        }
        return (T)a;
    }
    
    public <T extends b> void a(final T t) {
        if (t != null && !(t instanceof e)) {
            if (!(t instanceof d)) {
                this.a(t.getClass()).a((b)t);
            }
        }
    }
}
