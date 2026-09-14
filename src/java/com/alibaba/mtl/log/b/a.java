package com.alibaba.mtl.log.b;

import android.text.TextUtils;
import com.alibaba.mtl.log.d.i;
import java.util.List;

public class a
{
    private static StringBuilder a;
    private static volatile long e;
    private static long f;
    private static long g;
    private static long h;
    private static long i;
    private static long j;
    private static long k;
    private static long l;
    private static long m;
    private static long n;
    private static long o;
    private static long p;
    private static long q;
    private static long r;
    private static long s;
    private static long t;
    private static int u;
    private static long u;
    private static int v;
    private static long v;
    private static int w;
    private static long w;
    private static long x;
    private static long y;
    
    static {
        com.alibaba.mtl.log.b.a.a = new StringBuilder();
    }
    
    public static void A() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.t;
        }
    }
    
    public static void B() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.u;
        }
    }
    
    public static void C() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.w;
            if (com.alibaba.mtl.log.b.a.e == 0L && com.alibaba.mtl.log.b.a.g == 0L) {
                return;
            }
            if (com.alibaba.mtl.log.a.o || com.alibaba.mtl.log.b.a.w >= 6) {
                c(true);
            }
        }
    }
    
    public static void a(final List<com.alibaba.mtl.log.model.a> list, final int n) {
        final Class<a> clazz;
        monitorenter(clazz = a.class);
        if (list == null) {
            monitorexit(clazz);
            return;
        }
        int i = 0;
        int n2 = 0;
        try {
            while (i < list.size()) {
                final com.alibaba.mtl.log.model.a a = (com.alibaba.mtl.log.model.a)list.get(i);
                int n3 = n2;
                if (a != null) {
                    int n4 = n2;
                    if (!"6005".equalsIgnoreCase(a.X)) {
                        n4 = n2 + 1;
                    }
                    com.alibaba.mtl.log.b.a.a.append(a.ab);
                    n3 = n4;
                    if (i != list.size() - 1) {
                        com.alibaba.mtl.log.b.a.a.append(",");
                        n3 = n4;
                    }
                }
                ++i;
                n2 = n3;
            }
            com.alibaba.mtl.log.d.i.a("CoreStatics", new Object[] { "[uploadInc]:", com.alibaba.mtl.log.b.a.g, "count:", n });
            com.alibaba.mtl.log.d.i.a("CoreStatics", new Object[] { "[uploadInc]:", com.alibaba.mtl.log.b.a.g += n });
            if (n2 != n) {
                com.alibaba.mtl.log.d.i.a("CoreStatics", "Mutil Process Upload Error");
            }
        }
        finally {
            monitorexit(clazz);
        }
    }
    
    @Deprecated
    public static void c(final boolean b) {
        final Class<a> clazz;
        monitorenter(clazz = a.class);
        monitorexit(clazz);
    }
    
    public static void d(final int n) {
        synchronized (a.class) {
            com.alibaba.mtl.log.b.a.u += n;
        }
    }
    
    private static boolean e(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && "6005".equalsIgnoreCase(s.trim());
    }
    
    public static void m(final String s) {
        synchronized (a.class) {
            if (e(s)) {
                return;
            }
            if ("65501".equalsIgnoreCase(s)) {
                ++com.alibaba.mtl.log.b.a.y;
            }
            else if ("65133".equalsIgnoreCase(s)) {
                ++com.alibaba.mtl.log.b.a.w;
            }
            else if ("65502".equalsIgnoreCase(s)) {
                ++com.alibaba.mtl.log.b.a.x;
            }
            else if ("65503".equalsIgnoreCase(s)) {
                ++com.alibaba.mtl.log.b.a.v;
            }
            ++com.alibaba.mtl.log.b.a.e;
        }
    }
    
    public static void n(final String s) {
        synchronized (a.class) {
            if (e(s)) {
                return;
            }
            ++com.alibaba.mtl.log.b.a.f;
        }
    }
    
    public static void s() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.h;
        }
    }
    
    public static void t() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.i;
        }
    }
    
    public static void u() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.n;
        }
    }
    
    public static void v() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.o;
        }
    }
    
    public static void w() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.p;
        }
    }
    
    public static void x() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.q;
        }
    }
    
    public static void y() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.r;
        }
    }
    
    public static void z() {
        synchronized (a.class) {
            ++com.alibaba.mtl.log.b.a.s;
        }
    }
}
