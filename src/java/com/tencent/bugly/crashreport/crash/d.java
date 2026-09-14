package com.tencent.bugly.crashreport.crash;

import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.LinkedHashMap;
import com.tencent.bugly.proguard.y;
import java.util.Map;
import com.tencent.bugly.proguard.z;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.w;
import android.content.Context;
import com.tencent.bugly.crashreport.common.strategy.a;

public final class d
{
    private static d a;
    private a b;
    private com.tencent.bugly.crashreport.common.info.a c;
    private b d;
    private Context e;
    
    private d(final Context e) {
        final c a = com.tencent.bugly.crashreport.crash.c.a();
        if (a == null) {
            return;
        }
        this.b = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.c = com.tencent.bugly.crashreport.common.info.a.a(e);
        this.d = a.p;
        this.e = e;
        w.a().a((Runnable)new Runnable(this) {
            private d a;
            
            public final void run() {
                com.tencent.bugly.crashreport.crash.d.a(this.a);
            }
        });
    }
    
    public static d a(final Context context) {
        if (d.a == null) {
            d.a = new d(context);
        }
        return d.a;
    }
    
    static /* synthetic */ void a(final d d) {
        x.c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            final Class<?> forName = Class.forName("com.tencent.bugly.agent.GameAgent");
            d.c.getClass();
            z.a(forName, "sdkPackageName", "com.tencent.bugly", null);
            x.c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        }
        finally {
            x.a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }
    
    static /* synthetic */ void a(final d d, final Thread thread, int b, final String s, final String s2, final String s3, final Map map) {
        Thread currentThread;
        if (thread == null) {
            currentThread = Thread.currentThread();
        }
        else {
            currentThread = thread;
        }
        final int n = 5;
        String s4;
        if (b != 4) {
            if (b != 5 && b != 6) {
                if (b != 8) {
                    x.d("[ExtraCrashManager] Unknown extra crash type: %d", b);
                    return;
                }
                s4 = "H5";
            }
            else {
                s4 = "Cocos";
            }
        }
        else {
            s4 = "Unity";
        }
        x.e("[ExtraCrashManager] %s Crash Happen", s4);
        try {
            if (!d.b.b()) {
                x.d("[ExtraCrashManager] There is no remote strategy, but still store it.", new Object[0]);
            }
            final StrategyBean c = d.b.c();
            if (!c.e && d.b.b()) {
                x.e("[ExtraCrashManager] Crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                final String a = z.a();
                final String d2 = d.c.d;
                final String name = currentThread.getName();
                final StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("\n");
                sb.append(s2);
                sb.append("\n");
                sb.append(s3);
                b.a(s4, a, d2, name, sb.toString(), null);
                x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
            if (b != 5 && b != 6) {
                if (b == 8) {
                    if (!c.k) {
                        x.e("[ExtraCrashManager] %s report is disabled.", s4);
                        x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                        return;
                    }
                }
            }
            else if (!c.j) {
                x.e("[ExtraCrashManager] %s report is disabled.", s4);
                x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
            if (b == 8) {
                b = n;
            }
            final CrashDetailBean crashDetailBean = new CrashDetailBean();
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.e();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.F = d.c.k();
            crashDetailBean.G = d.c.j();
            crashDetailBean.H = d.c.l();
            crashDetailBean.w = z.a(d.e, com.tencent.bugly.crashreport.crash.c.e, null);
            crashDetailBean.b = b;
            crashDetailBean.e = d.c.h();
            crashDetailBean.f = d.c.k;
            crashDetailBean.g = d.c.q();
            crashDetailBean.m = d.c.g();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(s);
            crashDetailBean.n = sb2.toString();
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(s2);
            crashDetailBean.o = sb3.toString();
            String p7 = "";
            String q;
            if (s3 != null) {
                final String[] split = s3.split("\n");
                if (split.length > 0) {
                    p7 = split[0];
                }
                q = s3;
            }
            else {
                q = "";
            }
            crashDetailBean.p = p7;
            crashDetailBean.q = q;
            crashDetailBean.r = System.currentTimeMillis();
            crashDetailBean.u = z.a(crashDetailBean.q.getBytes());
            crashDetailBean.z = z.a(com.tencent.bugly.crashreport.crash.c.f, false);
            crashDetailBean.A = d.c.d;
            final StringBuilder sb4 = new StringBuilder();
            sb4.append(currentThread.getName());
            sb4.append("(");
            sb4.append(currentThread.getId());
            sb4.append(")");
            crashDetailBean.B = sb4.toString();
            crashDetailBean.I = d.c.s();
            crashDetailBean.h = d.c.p();
            crashDetailBean.M = d.c.a;
            crashDetailBean.N = d.c.a();
            if (!com.tencent.bugly.crashreport.crash.c.a().o()) {
                d.d.d(crashDetailBean);
            }
            crashDetailBean.Q = d.c.z();
            crashDetailBean.R = d.c.A();
            crashDetailBean.S = d.c.t();
            crashDetailBean.T = d.c.y();
            crashDetailBean.y = y.a();
            if (crashDetailBean.O == null) {
                crashDetailBean.O = (Map<String, String>)new LinkedHashMap();
            }
            if (map != null) {
                crashDetailBean.O.putAll(map);
            }
            final String a2 = z.a();
            final String d3 = d.c.d;
            final String name2 = currentThread.getName();
            final StringBuilder sb5 = new StringBuilder();
            sb5.append(s);
            sb5.append("\n");
            sb5.append(s2);
            sb5.append("\n");
            sb5.append(s3);
            b.a(s4, a2, d3, name2, sb5.toString(), crashDetailBean);
            if (!d.d.a(crashDetailBean)) {
                d.d.a(crashDetailBean, 3000L, false);
            }
            x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
        }
        finally {
            try {
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
            }
            finally {
                x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
            }
        }
    }
    
    public static void a(final Thread thread, final int n, final String s, final String s2, final String s3, final Map<String, String> map) {
        w.a().a((Runnable)new Runnable(thread, n, s, s2, s3, map) {
            private Thread a;
            private int b;
            private String c;
            private String d;
            private String e;
            private Map f;
            
            public final void run() {
                try {
                    if (com.tencent.bugly.crashreport.crash.d.a == null) {
                        x.e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                        return;
                    }
                    com.tencent.bugly.crashreport.crash.d.a(com.tencent.bugly.crashreport.crash.d.a, this.a, this.b, this.c, this.d, this.e, this.f);
                }
                finally {
                    final Throwable t;
                    if (!x.b(t)) {
                        t.printStackTrace();
                    }
                    x.e("[ExtraCrashManager] Crash error %s %s %s", this.c, this.d, this.e);
                }
            }
        });
    }
}
