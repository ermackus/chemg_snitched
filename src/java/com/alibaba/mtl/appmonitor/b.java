package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.a.e;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;

class b implements Runnable
{
    private static long a = 300000L;
    private static b a;
    private static boolean j;
    
    private b() {
    }
    
    static void destroy() {
        s.a().f(5);
        b.j = false;
        b.a = null;
    }
    
    static void init() {
        if (!b.j) {
            i.a("CleanTask", new Object[] { "init TimeoutEventManager" });
            b.a = new b();
            s.a().a(5, (Runnable)b.a, b.a);
            b.j = true;
        }
    }
    
    public void run() {
        i.a("CleanTask", new Object[] { "clean TimeoutEvent" });
        e.a().g();
        s.a().a(5, (Runnable)b.a, b.a);
    }
}
