package com.tencent.bugly.crashreport.crash;

import java.util.Map;
import android.os.Process;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.HashMap;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.crashreport.common.strategy.a;
import android.content.Context;

public final class e implements Thread$UncaughtExceptionHandler
{
    private static String h;
    private static final Object i;
    private Context a;
    private b b;
    private a c;
    private com.tencent.bugly.crashreport.common.info.a d;
    private Thread$UncaughtExceptionHandler e;
    private Thread$UncaughtExceptionHandler f;
    private boolean g;
    private int j;
    
    static {
        i = new Object();
    }
    
    public e(final Context a, final b b, final a c, final com.tencent.bugly.crashreport.common.info.a d) {
        this.g = false;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    private static String a(final Throwable t, final int n) {
        if (t == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        try {
            if (t.getStackTrace() != null) {
                Block_7: {
                    for (final StackTraceElement stackTraceElement : t.getStackTrace()) {
                        if (n > 0 && sb.length() >= n) {
                            break Block_7;
                        }
                        sb.append(stackTraceElement.toString());
                        sb.append("\n");
                    }
                    return sb.toString();
                }
                final StringBuilder sb2 = new StringBuilder("\n[Stack over limit size :");
                sb2.append(n);
                sb2.append(" , has been cutted !]");
                sb.append(sb2.toString());
                return sb.toString();
            }
        }
        finally {
            x.e("gen stack error %s", t.toString());
        }
        return sb.toString();
    }
    
    private static boolean a(final Thread$UncaughtExceptionHandler thread$UncaughtExceptionHandler) {
        if (thread$UncaughtExceptionHandler == null) {
            return true;
        }
        final String name = thread$UncaughtExceptionHandler.getClass().getName();
        for (final StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            final String className = stackTraceElement.getClassName();
            final String methodName = stackTraceElement.getMethodName();
            if (name.equals((Object)className) && "uncaughtException".equals((Object)methodName)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean a(final Thread thread) {
        final Object i = e.i;
        synchronized (i) {
            if (e.h != null && thread.getName().equals((Object)e.h)) {
                return true;
            }
            e.h = thread.getName();
            return false;
        }
    }
    
    private CrashDetailBean b(final Thread thread, final Throwable t, final boolean b, final String s, final byte[] u) {
        if (t == null) {
            x.d("We can do nothing with a null throwable.", new Object[0]);
            return null;
        }
        final boolean l = com.tencent.bugly.crashreport.crash.c.a().l();
        String s2;
        if (l && b) {
            s2 = " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
        }
        else {
            s2 = "";
        }
        if (l && b) {
            x.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        final CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.g();
        crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.e();
        crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.i();
        crashDetailBean.F = this.d.k();
        crashDetailBean.G = this.d.j();
        crashDetailBean.H = this.d.l();
        crashDetailBean.w = z.a(this.a, com.tencent.bugly.crashreport.crash.c.e, null);
        crashDetailBean.y = y.a();
        int length;
        if (crashDetailBean.y == null) {
            length = 0;
        }
        else {
            length = crashDetailBean.y.length;
        }
        x.a("user log size:%d", length);
        int b2;
        if (b) {
            b2 = 0;
        }
        else {
            b2 = 2;
        }
        crashDetailBean.b = b2;
        crashDetailBean.e = this.d.h();
        crashDetailBean.f = this.d.k;
        crashDetailBean.g = this.d.q();
        crashDetailBean.m = this.d.g();
        final String name = t.getClass().getName();
        String b3;
        if ((b3 = b(t, 1000)) == null) {
            b3 = "";
        }
        x.e("stack frame :%d, has cause %b", t.getStackTrace().length, t.getCause() != null);
        String string;
        if (t.getStackTrace().length > 0) {
            string = t.getStackTrace()[0].toString();
        }
        else {
            string = "";
        }
        Throwable cause;
        for (cause = t; cause != null && cause.getCause() != null; cause = cause.getCause()) {}
        String q;
        if (cause != null && cause != t) {
            crashDetailBean.n = cause.getClass().getName();
            crashDetailBean.o = b(cause, 1000);
            if (crashDetailBean.o == null) {
                crashDetailBean.o = "";
            }
            if (cause.getStackTrace().length > 0) {
                crashDetailBean.p = cause.getStackTrace()[0].toString();
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(":");
            sb.append(b3);
            sb.append("\n");
            sb.append(string);
            sb.append("\n......");
            sb.append("\nCaused by:\n");
            sb.append(crashDetailBean.n);
            sb.append(":");
            sb.append(crashDetailBean.o);
            sb.append("\n");
            q = a(cause, com.tencent.bugly.crashreport.crash.c.f);
            sb.append(q);
            crashDetailBean.q = sb.toString();
        }
        else {
            crashDetailBean.n = name;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(b3);
            sb2.append(s2);
            crashDetailBean.o = sb2.toString();
            if (crashDetailBean.o == null) {
                crashDetailBean.o = "";
            }
            crashDetailBean.p = string;
            q = a(t, com.tencent.bugly.crashreport.crash.c.f);
            crashDetailBean.q = q;
        }
        crashDetailBean.r = System.currentTimeMillis();
        crashDetailBean.u = z.a(crashDetailBean.q.getBytes());
        try {
            crashDetailBean.z = z.a(com.tencent.bugly.crashreport.crash.c.f, false);
            crashDetailBean.A = this.d.d;
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(thread.getName());
            sb3.append("(");
            sb3.append(thread.getId());
            sb3.append(")");
            crashDetailBean.B = sb3.toString();
            crashDetailBean.z.put((Object)crashDetailBean.B, (Object)q);
            crashDetailBean.I = this.d.s();
            crashDetailBean.h = this.d.p();
            crashDetailBean.i = this.d.B();
            crashDetailBean.M = this.d.a;
            crashDetailBean.N = this.d.a();
            if (b) {
                this.b.d(crashDetailBean);
            }
            else {
                final boolean b4 = s != null && s.length() > 0;
                final boolean b5 = u != null && u.length > 0;
                if (b4) {
                    (crashDetailBean.O = (Map<String, String>)new HashMap(1)).put((Object)"UserData", (Object)s);
                }
                if (b5) {
                    crashDetailBean.U = u;
                }
            }
            crashDetailBean.Q = this.d.z();
            crashDetailBean.R = this.d.A();
            crashDetailBean.S = this.d.t();
            crashDetailBean.T = this.d.y();
        }
        finally {
            final Throwable t2;
            x.e("handle crash error %s", t2.toString());
        }
        return crashDetailBean;
    }
    
    private static String b(final Throwable t, final int n) {
        if (t.getMessage() == null) {
            return "";
        }
        if (t.getMessage().length() <= 1000) {
            return t.getMessage();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(t.getMessage().substring(0, 1000));
        sb.append("\n[Message over limit size:1000");
        sb.append(", has been cutted!]");
        return sb.toString();
    }
    
    public final void a() {
        synchronized (this) {
            if (this.j >= 10) {
                x.a("java crash handler over %d, no need set.", 10);
                return;
            }
            this.g = true;
            final Thread$UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (defaultUncaughtExceptionHandler != null) {
                if (this.getClass().getName().equals((Object)defaultUncaughtExceptionHandler.getClass().getName())) {
                    return;
                }
                if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals((Object)defaultUncaughtExceptionHandler.getClass().getName())) {
                    x.a("backup system java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.f = defaultUncaughtExceptionHandler;
                    this.e = defaultUncaughtExceptionHandler;
                }
                else {
                    x.a("backup java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.e = defaultUncaughtExceptionHandler;
                }
            }
            Thread.setDefaultUncaughtExceptionHandler((Thread$UncaughtExceptionHandler)this);
            ++this.j;
            x.a("registered java monitor: %s", this.toString());
        }
    }
    
    public final void a(final StrategyBean strategyBean) {
        monitorenter(this);
        if (strategyBean != null) {
            try {
                if (strategyBean.e != this.g) {
                    x.a("java changed to %b", strategyBean.e);
                    if (strategyBean.e) {
                        this.a();
                        return;
                    }
                    this.b();
                }
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
    }
    
    public final void a(final Thread thread, final Throwable t, final boolean b, String s, final byte[] array) {
        if (b) {
            x.e("Java Crash Happen cause by %s(%d)", thread.getName(), thread.getId());
            if (a(thread)) {
                x.a("this class has handled this exception", new Object[0]);
                if (this.f != null) {
                    x.a("call system handler", new Object[0]);
                    this.f.uncaughtException(thread, t);
                }
                else {
                    x.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }
        }
        else {
            x.e("Java Catch Happen", new Object[0]);
        }
        try {
            if (!this.g) {
                x.c("Java crash handler is disable. Just return.", new Object[0]);
                if (b) {
                    final Thread$UncaughtExceptionHandler e = this.e;
                    if (e != null && a(e)) {
                        x.e("sys default last handle start!", new Object[0]);
                        this.e.uncaughtException(thread, t);
                        x.e("sys default last handle end!", new Object[0]);
                        return;
                    }
                    if (this.f != null) {
                        x.e("system handle start!", new Object[0]);
                        this.f.uncaughtException(thread, t);
                        x.e("system handle end!", new Object[0]);
                        return;
                    }
                    x.e("crashreport last handle start!", new Object[0]);
                    x.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    x.e("crashreport last handle end!", new Object[0]);
                }
                return;
            }
            if (!this.c.b()) {
                x.d("no remote but still store!", new Object[0]);
            }
            if (!this.c.c().e && this.c.b()) {
                x.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                if (b) {
                    s = "JAVA_CRASH";
                }
                else {
                    s = "JAVA_CATCH";
                }
                b.a(s, z.a(), this.d.d, thread.getName(), z.a(t), null);
                if (b) {
                    final Thread$UncaughtExceptionHandler e2 = this.e;
                    if (e2 != null && a(e2)) {
                        x.e("sys default last handle start!", new Object[0]);
                        this.e.uncaughtException(thread, t);
                        x.e("sys default last handle end!", new Object[0]);
                        return;
                    }
                    if (this.f != null) {
                        x.e("system handle start!", new Object[0]);
                        this.f.uncaughtException(thread, t);
                        x.e("system handle end!", new Object[0]);
                        return;
                    }
                    x.e("crashreport last handle start!", new Object[0]);
                    x.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    x.e("crashreport last handle end!", new Object[0]);
                }
                return;
            }
            final CrashDetailBean b2 = this.b(thread, t, b, s, array);
            if (b2 == null) {
                x.e("pkg crash datas fail!", new Object[0]);
                if (b) {
                    final Thread$UncaughtExceptionHandler e3 = this.e;
                    if (e3 != null && a(e3)) {
                        x.e("sys default last handle start!", new Object[0]);
                        this.e.uncaughtException(thread, t);
                        x.e("sys default last handle end!", new Object[0]);
                        return;
                    }
                    if (this.f != null) {
                        x.e("system handle start!", new Object[0]);
                        this.f.uncaughtException(thread, t);
                        x.e("system handle end!", new Object[0]);
                        return;
                    }
                    x.e("crashreport last handle start!", new Object[0]);
                    x.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    x.e("crashreport last handle end!", new Object[0]);
                }
                return;
            }
            if (b) {
                s = "JAVA_CRASH";
            }
            else {
                s = "JAVA_CATCH";
            }
            b.a(s, z.a(), this.d.d, thread.getName(), z.a(t), b2);
            if (!this.b.a(b2)) {
                this.b.a(b2, 3000L, b);
            }
            if (b) {
                this.b.c(b2);
            }
            if (!b) {
                return;
            }
            final Thread$UncaughtExceptionHandler e4 = this.e;
            if (e4 != null && a(e4)) {
                x.e("sys default last handle start!", new Object[0]);
                this.e.uncaughtException(thread, t);
                x.e("sys default last handle end!", new Object[0]);
                return;
            }
            if (this.f != null) {
                x.e("system handle start!", new Object[0]);
                this.f.uncaughtException(thread, t);
                x.e("system handle end!", new Object[0]);
                return;
            }
            x.e("crashreport last handle start!", new Object[0]);
            x.e("current process die", new Object[0]);
            Process.killProcess(Process.myPid());
            System.exit(1);
            x.e("crashreport last handle end!", new Object[0]);
        }
        finally {
            try {
                final Throwable t2;
                if (!x.a(t2)) {
                    t2.printStackTrace();
                }
                if (b) {
                    final Thread$UncaughtExceptionHandler e5 = this.e;
                    if (e5 != null && a(e5)) {
                        x.e("sys default last handle start!", new Object[0]);
                        this.e.uncaughtException(thread, t);
                        x.e("sys default last handle end!", new Object[0]);
                        return;
                    }
                    if (this.f != null) {
                        x.e("system handle start!", new Object[0]);
                        this.f.uncaughtException(thread, t);
                        x.e("system handle end!", new Object[0]);
                        return;
                    }
                    x.e("crashreport last handle start!", new Object[0]);
                    x.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    x.e("crashreport last handle end!", new Object[0]);
                }
            }
            finally {
                if (b) {
                    final Thread$UncaughtExceptionHandler e6 = this.e;
                    if (e6 != null && a(e6)) {
                        x.e("sys default last handle start!", new Object[0]);
                        this.e.uncaughtException(thread, t);
                        x.e("sys default last handle end!", new Object[0]);
                    }
                    else if (this.f != null) {
                        x.e("system handle start!", new Object[0]);
                        this.f.uncaughtException(thread, t);
                        x.e("system handle end!", new Object[0]);
                    }
                    else {
                        x.e("crashreport last handle start!", new Object[0]);
                        x.e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        x.e("crashreport last handle end!", new Object[0]);
                    }
                }
            }
        }
    }
    
    public final void b() {
        synchronized (this) {
            this.g = false;
            x.a("close java monitor!", new Object[0]);
            if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains((CharSequence)"bugly")) {
                x.a("Java monitor to unregister: %s", this.toString());
                Thread.setDefaultUncaughtExceptionHandler(this.e);
                --this.j;
            }
        }
    }
    
    public final void uncaughtException(final Thread thread, final Throwable t) {
        final Object i = com.tencent.bugly.crashreport.crash.e.i;
        synchronized (i) {
            this.a(thread, t, true, null, null);
        }
    }
}
