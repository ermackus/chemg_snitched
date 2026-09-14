package com.tencent.bugly.crashreport.crash;

import com.tencent.bugly.crashreport.common.info.AppInfo;
import java.util.Iterator;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.z;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import android.content.Context;

public final class c
{
    public static int a = 0;
    public static boolean b = false;
    public static int c = 2;
    public static boolean d = true;
    public static int e = 20480;
    public static int f = 20480;
    public static long g = 604800000L;
    public static String h;
    public static boolean i = false;
    public static String j;
    public static int k = 5000;
    public static boolean l = true;
    public static boolean m;
    public static String n;
    public static String o;
    private static c r;
    public final b p;
    private final Context q;
    private final e s;
    private final NativeCrashHandler t;
    private a u;
    private w v;
    private final com.tencent.bugly.crashreport.crash.anr.b w;
    private Boolean x;
    private int y;
    private boolean z;
    
    private c(final int a, Context a2, final w v, final boolean b, final BuglyStrategy.a a3, final o o, final String s) {
        this.y = 31;
        this.z = false;
        com.tencent.bugly.crashreport.crash.c.a = a;
        a2 = com.tencent.bugly.proguard.z.a(a2);
        this.q = a2;
        this.u = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.v = v;
        final u a4 = com.tencent.bugly.proguard.u.a();
        final p a5 = com.tencent.bugly.proguard.p.a();
        this.p = new b(a, a2, a4, a5, this.u, a3, o);
        final com.tencent.bugly.crashreport.common.info.a a6 = com.tencent.bugly.crashreport.common.info.a.a(a2);
        this.s = new e(a2, this.p, this.u, a6);
        final NativeCrashHandler instance = NativeCrashHandler.getInstance(a2, a6, this.p, this.u, v, b, s);
        this.t = instance;
        a6.E = (com.tencent.bugly.crashreport.a)instance;
        this.w = com.tencent.bugly.crashreport.crash.anr.b.a(a2, this.u, a6, v, a5, this.p, a3);
    }
    
    public static c a() {
        synchronized (c.class) {
            return com.tencent.bugly.crashreport.crash.c.r;
        }
    }
    
    public static c a(final int n, final Context context, final boolean b, final BuglyStrategy.a a, final o o, final String s) {
        synchronized (c.class) {
            if (com.tencent.bugly.crashreport.crash.c.r == null) {
                com.tencent.bugly.crashreport.crash.c.r = new c(1004, context, w.a(), b, a, null, null);
            }
            return com.tencent.bugly.crashreport.crash.c.r;
        }
    }
    
    public final void a(final int y) {
        this.y = y;
    }
    
    public final void a(final long n) {
        com.tencent.bugly.proguard.w.a().a((Runnable)new Thread(this) {
            private c a;
            
            public final void run() {
                if (!com.tencent.bugly.proguard.z.a(this.a.q, "local_crash_lock", 10000L)) {
                    return;
                }
                final List<CrashDetailBean> a = this.a.p.a();
                if (a != null && a.size() > 0) {
                    final int size = a.size();
                    int n = 0;
                    com.tencent.bugly.proguard.x.c("Size of crash list: %s", size);
                    final int size2 = a.size();
                    Object o;
                    if (size2 > 20L) {
                        o = new ArrayList();
                        Collections.sort((List)a);
                        while (n < 20L) {
                            ((List)o).add(a.get(size2 - 1 - n));
                            ++n;
                        }
                    }
                    else {
                        o = a;
                    }
                    this.a.p.a((List<CrashDetailBean>)o, 0L, false, false, false);
                }
                com.tencent.bugly.proguard.z.b(this.a.q, "local_crash_lock");
            }
        }, n);
    }
    
    public final void a(final StrategyBean strategyBean) {
        this.s.a(strategyBean);
        this.t.onStrategyChanged(strategyBean);
        this.w.c();
        com.tencent.bugly.proguard.w.a().a((Runnable)new Thread(this) {
            private c a;
            
            public final void run() {
                if (!com.tencent.bugly.proguard.z.a(this.a.q, "local_crash_lock", 10000L)) {
                    return;
                }
                final List<CrashDetailBean> a = this.a.p.a();
                if (a != null && a.size() > 0) {
                    final int size = a.size();
                    int n = 0;
                    com.tencent.bugly.proguard.x.c("Size of crash list: %s", size);
                    final int size2 = a.size();
                    Object o;
                    if (size2 > 20L) {
                        o = new ArrayList();
                        Collections.sort((List)a);
                        while (n < 20L) {
                            ((List)o).add(a.get(size2 - 1 - n));
                            ++n;
                        }
                    }
                    else {
                        o = a;
                    }
                    this.a.p.a((List<CrashDetailBean>)o, 0L, false, false, false);
                }
                com.tencent.bugly.proguard.z.b(this.a.q, "local_crash_lock");
            }
        }, 3000L);
    }
    
    public final void a(final CrashDetailBean crashDetailBean) {
        this.p.e(crashDetailBean);
    }
    
    public final void a(final Thread thread, final Throwable t, final boolean b, final String s, final byte[] array, final boolean b2) {
        this.v.a((Runnable)new Runnable(this, false, thread, t, null, null, b2) {
            private boolean a;
            private Thread b;
            private Throwable c;
            private String d;
            private byte[] e;
            private boolean f;
            private c g;
            
            public final void run() {
                try {
                    com.tencent.bugly.proguard.x.c("post a throwable %b", this.a);
                    this.g.s.a(this.b, this.c, false, this.d, this.e);
                    if (this.f) {
                        com.tencent.bugly.proguard.x.a("clear user datas", new Object[0]);
                        com.tencent.bugly.crashreport.common.info.a.a(this.g.q).u();
                    }
                }
                finally {
                    final Throwable t;
                    if (!com.tencent.bugly.proguard.x.b(t)) {
                        t.printStackTrace();
                    }
                    com.tencent.bugly.proguard.x.e("java catch error: %s", this.c.toString());
                }
            }
        });
    }
    
    public final void a(final boolean z) {
        this.z = z;
    }
    
    public final void a(final boolean b, final boolean b2, final boolean b3) {
        synchronized (this) {
            this.t.testNativeCrash(b, b2, b3);
        }
    }
    
    public final boolean b() {
        final Boolean x = this.x;
        if (x != null) {
            return x;
        }
        final String d = com.tencent.bugly.crashreport.common.info.a.b().d;
        final List<r> a = com.tencent.bugly.proguard.p.a().a(1);
        final ArrayList list = new ArrayList();
        if (a != null && a.size() > 0) {
            for (final r r : a) {
                if (d.equals((Object)r.c)) {
                    this.x = true;
                    ((List)list).add((Object)r);
                }
            }
            if (((List)list).size() > 0) {
                com.tencent.bugly.proguard.p.a().a((List<r>)list);
            }
            return true;
        }
        this.x = false;
        return false;
    }
    
    public final void c() {
        synchronized (this) {
            this.s.a();
            this.t.setUserOpened(true);
            this.w.a(true);
        }
    }
    
    public final void d() {
        synchronized (this) {
            this.s.b();
            this.t.setUserOpened(false);
            this.w.a(false);
        }
    }
    
    public final void e() {
        this.s.a();
    }
    
    public final void f() {
        this.t.setUserOpened(false);
    }
    
    public final void g() {
        this.t.setUserOpened(true);
    }
    
    public final void h() {
        this.w.a(true);
    }
    
    public final void i() {
        this.w.a(false);
    }
    
    public final void j() {
        this.t.enableCatchAnrTrace();
    }
    
    public final void k() {
        monitorenter(this);
        int n = 0;
        while (true) {
            final int n2 = n + 1;
            if (n < 30) {
                try {
                    com.tencent.bugly.proguard.x.a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", n2);
                    com.tencent.bugly.proguard.z.b(5000L);
                    n = n2;
                    continue;
                }
                finally {
                    try {
                        final Throwable t;
                        if (!com.tencent.bugly.proguard.x.a(t)) {
                            t.printStackTrace();
                        }
                        return;
                    }
                    finally {
                        monitorexit(this);
                    }
                }
                break;
            }
            break;
        }
        monitorexit(this);
    }
    
    public final boolean l() {
        return this.w.a();
    }
    
    public final void m() {
        this.t.checkUploadRecordCrash();
    }
    
    public final void n() {
        if (com.tencent.bugly.crashreport.common.info.a.b().d.equals((Object)AppInfo.a(this.q))) {
            this.t.removeEmptyNativeRecordFiles();
        }
    }
    
    public final boolean o() {
        return this.z;
    }
    
    public final boolean p() {
        return (this.y & 0x10) > 0;
    }
    
    public final boolean q() {
        return (this.y & 0x8) > 0;
    }
    
    public final boolean r() {
        return (this.y & 0x4) > 0;
    }
    
    public final boolean s() {
        return (this.y & 0x2) > 0;
    }
    
    public final boolean t() {
        return (this.y & 0x1) > 0;
    }
}
