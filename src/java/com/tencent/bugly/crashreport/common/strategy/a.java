package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcelable;
import com.tencent.bugly.proguard.ap;
import java.util.Iterator;
import com.tencent.bugly.crashreport.biz.b;
import java.util.Map;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import android.content.Context;
import com.tencent.bugly.proguard.w;
import java.util.List;

public final class a
{
    public static int a = 1000;
    private static a b;
    private static String h;
    private final List<com.tencent.bugly.a> c;
    private final w d;
    private final StrategyBean e;
    private StrategyBean f;
    private Context g;
    
    private a(final Context g, final List<com.tencent.bugly.a> c) {
        this.f = null;
        this.g = g;
        Label_0067: {
            if (com.tencent.bugly.crashreport.common.info.a.a(g) != null) {
                final String z = com.tencent.bugly.crashreport.common.info.a.a(g).z;
                String s;
                if ("oversea".equals((Object)z)) {
                    s = "https://astat.bugly.qcloud.com/rqd/async";
                }
                else {
                    if (!"na_https".equals((Object)z)) {
                        break Label_0067;
                    }
                    s = "https://astat.bugly.cros.wr.pvp.net/:8180/rqd/async";
                }
                StrategyBean.a = s;
                StrategyBean.b = s;
            }
        }
        this.e = new StrategyBean();
        this.c = c;
        this.d = w.a();
    }
    
    public static a a() {
        synchronized (a.class) {
            return com.tencent.bugly.crashreport.common.strategy.a.b;
        }
    }
    
    public static a a(final Context context, final List<com.tencent.bugly.a> list) {
        synchronized (a.class) {
            if (com.tencent.bugly.crashreport.common.strategy.a.b == null) {
                com.tencent.bugly.crashreport.common.strategy.a.b = new a(context, list);
            }
            return com.tencent.bugly.crashreport.common.strategy.a.b;
        }
    }
    
    public static void a(final String h) {
        if (!z.a(h) && z.c(h)) {
            com.tencent.bugly.crashreport.common.strategy.a.h = h;
            return;
        }
        x.d("URL user set is invalid.", new Object[0]);
    }
    
    public static StrategyBean d() {
        final List<r> a = p.a().a(2);
        if (a != null && a.size() > 0) {
            final r r = (r)a.get(0);
            if (r.g != null) {
                return z.a(r.g, StrategyBean.CREATOR);
            }
        }
        return null;
    }
    
    public final void a(final long n) {
        this.d.a((Runnable)new Thread(this) {
            private a a;
            
            public final void run() {
                try {
                    final Map<String, byte[]> a = p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, null, true);
                    if (a != null) {
                        final byte[] array = (byte[])a.get((Object)"device");
                        final byte[] array2 = (byte[])a.get((Object)"gateway");
                        if (array != null) {
                            com.tencent.bugly.crashreport.common.info.a.a(this.a.g).e(new String(array));
                        }
                        if (array2 != null) {
                            com.tencent.bugly.crashreport.common.info.a.a(this.a.g).d(new String(array2));
                        }
                    }
                    this.a.f = com.tencent.bugly.crashreport.common.strategy.a.d();
                    if (this.a.f != null) {
                        if (!z.a(com.tencent.bugly.crashreport.common.strategy.a.h) && z.c(com.tencent.bugly.crashreport.common.strategy.a.h)) {
                            this.a.f.p = com.tencent.bugly.crashreport.common.strategy.a.h;
                            this.a.f.q = com.tencent.bugly.crashreport.common.strategy.a.h;
                        }
                        else {
                            this.a.f.p = StrategyBean.a;
                            this.a.f.q = StrategyBean.b;
                        }
                    }
                }
                finally {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                }
                final a a2 = this.a;
                a2.a(a2.f, false);
            }
        }, n);
    }
    
    protected final void a(final StrategyBean strategyBean, final boolean b) {
        x.c("[Strategy] Notify %s", b.class.getName());
        b.a(strategyBean, b);
        for (final com.tencent.bugly.a a : this.c) {
            try {
                x.c("[Strategy] Notify %s", a.getClass().getName());
                a.onServerStrategyChanged(strategyBean);
            }
            finally {
                final Throwable t;
                if (x.a(t)) {
                    continue;
                }
                t.printStackTrace();
            }
        }
    }
    
    public final void a(final ap ap) {
        if (ap == null) {
            return;
        }
        if (this.f != null && ap.h == this.f.n) {
            return;
        }
        final StrategyBean f = new StrategyBean();
        f.e = ap.a;
        f.g = ap.c;
        f.f = ap.b;
        if (z.a(com.tencent.bugly.crashreport.common.strategy.a.h) || !z.c(com.tencent.bugly.crashreport.common.strategy.a.h)) {
            if (z.c(ap.d)) {
                x.c("[Strategy] Upload url changes to %s", ap.d);
                f.p = ap.d;
            }
            if (z.c(ap.e)) {
                x.c("[Strategy] Exception upload url changes to %s", ap.e);
                f.q = ap.e;
            }
        }
        if (ap.f != null && !z.a(ap.f.a)) {
            f.r = ap.f.a;
        }
        if (ap.h != 0L) {
            f.n = ap.h;
        }
        if (ap.g != null && ap.g.size() > 0) {
            f.s = (Map<String, String>)ap.g;
            final String s = (String)ap.g.get((Object)"B11");
            if (s != null && s.equals((Object)"1")) {
                f.h = true;
            }
            else {
                f.h = false;
            }
            final String s2 = (String)ap.g.get((Object)"B3");
            if (s2 != null) {
                f.v = Long.valueOf(s2);
            }
            f.o = ap.i;
            f.u = ap.i;
            final String s3 = (String)ap.g.get((Object)"B27");
            if (s3 != null && s3.length() > 0) {
                try {
                    final int int1 = Integer.parseInt(s3);
                    if (int1 > 0) {
                        f.t = int1;
                    }
                }
                catch (final Exception ex) {
                    if (!x.a((Throwable)ex)) {
                        ex.printStackTrace();
                    }
                }
            }
            final String s4 = (String)ap.g.get((Object)"B25");
            if (s4 != null && s4.equals((Object)"1")) {
                f.j = true;
            }
            else {
                f.j = false;
            }
        }
        x.a("[Strategy] enableCrashReport:%b, enableQuery:%b, enableUserInfo:%b, enableAnr:%b, enableBlock:%b, enableSession:%b, enableSessionTimer:%b, sessionOverTime:%d, enableCocos:%b, strategyLastUpdateTime:%d", f.e, f.g, f.f, f.h, f.i, f.l, f.m, f.o, f.j, f.n);
        this.f = f;
        if (!z.c(ap.d)) {
            x.c("[Strategy] download url is null", new Object[0]);
            this.f.p = "";
        }
        if (!z.c(ap.e)) {
            x.c("[Strategy] download crashurl is null", new Object[0]);
            this.f.q = "";
        }
        p.a().b(2);
        final r r = new r();
        r.b = 2;
        r.a = f.c;
        r.e = f.d;
        r.g = z.a((Parcelable)f);
        p.a().a(r);
        this.a(f, true);
    }
    
    public final boolean b() {
        synchronized (this) {
            return this.f != null;
        }
    }
    
    public final StrategyBean c() {
        final StrategyBean f = this.f;
        if (f != null) {
            if (!z.c(f.p)) {
                this.f.p = StrategyBean.a;
            }
            if (!z.c(this.f.q)) {
                this.f.q = StrategyBean.b;
            }
            return this.f;
        }
        if (!z.a(com.tencent.bugly.crashreport.common.strategy.a.h) && z.c(com.tencent.bugly.crashreport.common.strategy.a.h)) {
            this.e.p = com.tencent.bugly.crashreport.common.strategy.a.h;
            this.e.q = com.tencent.bugly.crashreport.common.strategy.a.h;
        }
        return this.e;
    }
}
