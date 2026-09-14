package com.alipay.apmobilesecuritysdk.f;

import java.util.LinkedList;

public final class b
{
    public static b a;
    public Thread b;
    public LinkedList<Runnable> c;
    
    static {
        b.a = new b();
    }
    
    public b() {
        this.b = null;
        this.c = (LinkedList<Runnable>)new LinkedList();
    }
    
    public static b a() {
        return b.a;
    }
    
    public final void a(final Runnable runnable) {
        synchronized (this) {
            this.c.add((Object)runnable);
            if (this.b == null) {
                (this.b = new Thread((Runnable)new c(this))).start();
            }
        }
    }
}
