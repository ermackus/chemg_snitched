package com.tencent.bugly.crashreport.biz;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.proguard.x;
import android.app.Application;
import android.os.Build$VERSION;
import android.content.Context;
import com.tencent.bugly.proguard.z;
import android.app.Application$ActivityLifecycleCallbacks;

public class b
{
    public static a a;
    private static boolean b = false;
    private static int c = 10;
    private static long d = 300000L;
    private static long e = 30000L;
    private static long f = 0L;
    private static int g = 0;
    private static long h = 0L;
    private static long i = 0L;
    private static long j = 0L;
    private static Application$ActivityLifecycleCallbacks k;
    private static Class<?> l;
    private static boolean m = true;
    
    static /* synthetic */ String a(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append(z.a());
        sb.append("  ");
        sb.append(s);
        sb.append("  ");
        sb.append(s2);
        sb.append("\n");
        return sb.toString();
    }
    
    public static void a() {
        final a a = com.tencent.bugly.crashreport.biz.b.a;
        if (a != null) {
            a.a(2, false, 0L);
        }
    }
    
    public static void a(final long n) {
        long o = n;
        if (n < 0L) {
            o = com.tencent.bugly.crashreport.common.strategy.a.a().c().o;
        }
        com.tencent.bugly.crashreport.biz.b.f = o;
    }
    
    public static void a(final Context context) {
        if (com.tencent.bugly.crashreport.biz.b.b) {
            if (context != null) {
                Application application = null;
                if (Build$VERSION.SDK_INT >= 14) {
                    if (context.getApplicationContext() instanceof Application) {
                        application = (Application)context.getApplicationContext();
                    }
                    if (application != null) {
                        try {
                            if (com.tencent.bugly.crashreport.biz.b.k != null) {
                                application.unregisterActivityLifecycleCallbacks(com.tencent.bugly.crashreport.biz.b.k);
                            }
                        }
                        catch (final Exception ex) {
                            if (!x.a((Throwable)ex)) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                com.tencent.bugly.crashreport.biz.b.b = false;
            }
        }
    }
    
    public static void a(final Context context, final BuglyStrategy buglyStrategy) {
        if (com.tencent.bugly.crashreport.biz.b.b) {
            return;
        }
        com.tencent.bugly.crashreport.biz.b.a = new a(context, com.tencent.bugly.crashreport.biz.b.m = com.tencent.bugly.crashreport.common.info.a.a(context).e);
        com.tencent.bugly.crashreport.biz.b.b = true;
        long appReportDelay;
        if (buglyStrategy != null) {
            com.tencent.bugly.crashreport.biz.b.l = buglyStrategy.getUserInfoActivity();
            appReportDelay = buglyStrategy.getAppReportDelay();
        }
        else {
            appReportDelay = 0L;
        }
        if (appReportDelay <= 0L) {
            c(context, buglyStrategy);
            return;
        }
        w.a().a((Runnable)new Runnable(context, buglyStrategy) {
            private Context a;
            private BuglyStrategy b;
            
            public final void run() {
                c(this.a, this.b);
            }
        }, appReportDelay);
    }
    
    public static void a(final StrategyBean strategyBean, final boolean b) {
        final a a = b.a;
        if (a != null && !b) {
            final w a2 = w.a();
            if (a2 != null) {
                a2.a((Runnable)new Runnable(a) {
                    private a a;
                    
                    public final void run() {
                        try {
                            com.tencent.bugly.crashreport.biz.a.a(this.a);
                        }
                        finally {
                            final Throwable t;
                            x.a(t);
                        }
                    }
                });
            }
        }
        if (strategyBean == null) {
            return;
        }
        if (strategyBean.o > 0L) {
            b.e = strategyBean.o;
        }
        if (strategyBean.t > 0) {
            b.c = strategyBean.t;
        }
        if (strategyBean.u > 0L) {
            b.d = strategyBean.u;
        }
    }
    
    private static void c(final Context context, final BuglyStrategy buglyStrategy) {
        boolean recordUserInfoOnceADay;
        boolean enableUserInfo;
        if (buglyStrategy != null) {
            recordUserInfoOnceADay = buglyStrategy.recordUserInfoOnceADay();
            enableUserInfo = buglyStrategy.isEnableUserInfo();
        }
        else {
            enableUserInfo = true;
            recordUserInfoOnceADay = false;
        }
        if (recordUserInfoOnceADay) {
            final com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a(context);
            final List<UserInfoBean> a2 = com.tencent.bugly.crashreport.biz.b.a.a(a.d);
            boolean b2 = false;
            Label_0180: {
                if (a2 != null) {
                    for (int i = 0; i < a2.size(); ++i) {
                        final UserInfoBean userInfoBean = (UserInfoBean)a2.get(i);
                        if (userInfoBean.n.equals((Object)a.k) && userInfoBean.b == 1) {
                            final long b = z.b();
                            if (b <= 0L) {
                                break;
                            }
                            if (userInfoBean.e >= b) {
                                if (userInfoBean.f <= 0L) {
                                    final a a3 = com.tencent.bugly.crashreport.biz.b.a;
                                    final w a4 = w.a();
                                    if (a4 != null) {
                                        a4.a((Runnable)new Runnable(a3) {
                                            private a a;
                                            
                                            public final void run() {
                                                try {
                                                    com.tencent.bugly.crashreport.biz.a.a(this.a);
                                                }
                                                finally {
                                                    final Throwable t;
                                                    x.a(t);
                                                }
                                            }
                                        });
                                    }
                                }
                                b2 = false;
                                break Label_0180;
                            }
                        }
                    }
                }
                b2 = true;
            }
            if (!b2) {
                return;
            }
            enableUserInfo = false;
        }
        final com.tencent.bugly.crashreport.common.info.a b3 = com.tencent.bugly.crashreport.common.info.a.b();
        final Application application = null;
        if (b3 != null) {
            final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            final int length = stackTrace.length;
            String className = null;
            int j = 0;
            boolean b4 = false;
            while (j < length) {
                final StackTraceElement stackTraceElement = stackTrace[j];
                if (stackTraceElement.getMethodName().equals((Object)"onCreate")) {
                    className = stackTraceElement.getClassName();
                }
                if (stackTraceElement.getClassName().equals((Object)"android.app.Activity")) {
                    b4 = true;
                }
                ++j;
            }
            if (className != null) {
                if (b4) {
                    b3.a(true);
                }
                else {
                    className = "background";
                }
            }
            else {
                className = "unknown";
            }
            b3.q = className;
        }
        if (enableUserInfo && Build$VERSION.SDK_INT >= 14) {
            Application application2 = application;
            if (context.getApplicationContext() instanceof Application) {
                application2 = (Application)context.getApplicationContext();
            }
            if (application2 != null) {
                try {
                    if (com.tencent.bugly.crashreport.biz.b.k == null) {
                        com.tencent.bugly.crashreport.biz.b.k = (Application$ActivityLifecycleCallbacks)new Application$ActivityLifecycleCallbacks() {
                            public final void onActivityCreated(final Activity activity, final Bundle bundle) {
                                String name;
                                if (activity != null) {
                                    name = activity.getClass().getName();
                                }
                                else {
                                    name = "unknown";
                                }
                                if (com.tencent.bugly.crashreport.biz.b.l != null && !com.tencent.bugly.crashreport.biz.b.l.getName().equals((Object)name)) {
                                    return;
                                }
                                x.c(">>> %s onCreated <<<", name);
                                final com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
                                if (b != null) {
                                    b.D.add((Object)com.tencent.bugly.crashreport.biz.b.a(name, "onCreated"));
                                }
                            }
                            
                            public final void onActivityDestroyed(final Activity activity) {
                                String name;
                                if (activity != null) {
                                    name = activity.getClass().getName();
                                }
                                else {
                                    name = "unknown";
                                }
                                if (com.tencent.bugly.crashreport.biz.b.l != null && !com.tencent.bugly.crashreport.biz.b.l.getName().equals((Object)name)) {
                                    return;
                                }
                                x.c(">>> %s onDestroyed <<<", name);
                                final com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
                                if (b != null) {
                                    b.D.add((Object)com.tencent.bugly.crashreport.biz.b.a(name, "onDestroyed"));
                                }
                            }
                            
                            public final void onActivityPaused(final Activity activity) {
                                String name;
                                if (activity != null) {
                                    name = activity.getClass().getName();
                                }
                                else {
                                    name = "unknown";
                                }
                                if (com.tencent.bugly.crashreport.biz.b.l != null && !com.tencent.bugly.crashreport.biz.b.l.getName().equals((Object)name)) {
                                    return;
                                }
                                x.c(">>> %s onPaused <<<", name);
                                final com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
                                if (b == null) {
                                    return;
                                }
                                b.D.add((Object)com.tencent.bugly.crashreport.biz.b.a(name, "onPaused"));
                                b.a(false);
                                b.s = System.currentTimeMillis();
                                b.t = b.s - b.r;
                                com.tencent.bugly.crashreport.biz.b.h = b.s;
                                if (b.t < 0L) {
                                    b.t = 0L;
                                }
                                if (activity != null) {
                                    b.q = "background";
                                    return;
                                }
                                b.q = "unknown";
                            }
                            
                            public final void onActivityResumed(final Activity activity) {
                                String name;
                                if (activity != null) {
                                    name = activity.getClass().getName();
                                }
                                else {
                                    name = "unknown";
                                }
                                if (com.tencent.bugly.crashreport.biz.b.l != null && !com.tencent.bugly.crashreport.biz.b.l.getName().equals((Object)name)) {
                                    return;
                                }
                                x.c(">>> %s onResumed <<<", name);
                                final com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
                                if (b == null) {
                                    return;
                                }
                                b.D.add((Object)com.tencent.bugly.crashreport.biz.b.a(name, "onResumed"));
                                b.a(true);
                                b.q = name;
                                b.r = System.currentTimeMillis();
                                b.u = b.r - com.tencent.bugly.crashreport.biz.b.i;
                                final long n = b.r - com.tencent.bugly.crashreport.biz.b.h;
                                long n2;
                                if (com.tencent.bugly.crashreport.biz.b.f > 0L) {
                                    n2 = com.tencent.bugly.crashreport.biz.b.f;
                                }
                                else {
                                    n2 = com.tencent.bugly.crashreport.biz.b.e;
                                }
                                if (n > n2) {
                                    b.d();
                                    com.tencent.bugly.crashreport.biz.b.g = com.tencent.bugly.crashreport.biz.b.g;
                                    x.a("[session] launch app one times (app in background %d seconds and over %d seconds)", n / 1000L, com.tencent.bugly.crashreport.biz.b.e / 1000L);
                                    if (com.tencent.bugly.crashreport.biz.b.g % com.tencent.bugly.crashreport.biz.b.c == 0) {
                                        com.tencent.bugly.crashreport.biz.b.a.a(4, com.tencent.bugly.crashreport.biz.b.m, 0L);
                                        return;
                                    }
                                    com.tencent.bugly.crashreport.biz.b.a.a(4, false, 0L);
                                    final long currentTimeMillis = System.currentTimeMillis();
                                    if (currentTimeMillis - com.tencent.bugly.crashreport.biz.b.j > com.tencent.bugly.crashreport.biz.b.d) {
                                        com.tencent.bugly.crashreport.biz.b.j = currentTimeMillis;
                                        x.a("add a timer to upload hot start user info", new Object[0]);
                                        if (com.tencent.bugly.crashreport.biz.b.m) {
                                            w.a().a((Runnable)com.tencent.bugly.crashreport.biz.b.a.new a(null, true), com.tencent.bugly.crashreport.biz.b.d);
                                        }
                                    }
                                }
                            }
                            
                            public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
                            }
                            
                            public final void onActivityStarted(final Activity activity) {
                            }
                            
                            public final void onActivityStopped(final Activity activity) {
                            }
                        };
                    }
                    application2.registerActivityLifecycleCallbacks(com.tencent.bugly.crashreport.biz.b.k);
                }
                catch (final Exception ex) {
                    if (!x.a((Throwable)ex)) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        if (com.tencent.bugly.crashreport.biz.b.m) {
            com.tencent.bugly.crashreport.biz.b.i = System.currentTimeMillis();
            com.tencent.bugly.crashreport.biz.b.a.a(1, false, 0L);
            x.a("[session] launch app, new start", new Object[0]);
            com.tencent.bugly.crashreport.biz.b.a.a();
            w.a().a((Runnable)com.tencent.bugly.crashreport.biz.b.a.new c(21600000L), 21600000L);
        }
    }
}
