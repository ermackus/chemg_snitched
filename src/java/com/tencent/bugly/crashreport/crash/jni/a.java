package com.tencent.bugly.crashreport.crash.jni;

import com.tencent.bugly.proguard.y;
import com.tencent.bugly.crashreport.crash.c;
import java.util.Iterator;
import java.util.Map;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.crashreport.crash.b;
import android.content.Context;

public final class a implements NativeExceptionHandler
{
    private final Context a;
    private final b b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final com.tencent.bugly.crashreport.common.strategy.a d;
    
    public a(final Context a, final com.tencent.bugly.crashreport.common.info.a c, final b b, final com.tencent.bugly.crashreport.common.strategy.a d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public final void handleNativeException(final int n, final int n2, final long n3, final long n4, final String s, final String s2, final String s3, final String s4, final int n5, final String s5, final int n6, final int n7, final int n8, final String s6, final String s7) {
        x.a("Native Crash Happen v1", new Object[0]);
        this.handleNativeException2(n, n2, n3, n4, s, s2, s3, s4, n5, s5, n6, n7, n8, s6, s7, null);
    }
    
    public final void handleNativeException2(int i, final int n, final long n2, long n3, String s, final String s2, String s3, String a, final int n4, String string, final int n5, final int n6, final int n7, String a2, final String s4, final String[] array) {
        x.a("Native Crash Happen v2", new Object[0]);
        try {
            final String a3 = com.tencent.bugly.crashreport.crash.jni.b.a(s3);
            s3 = "UNKNOWN";
            String s5;
            if (n4 > 0) {
                final StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("(");
                sb.append(string);
                sb.append(")");
                string = sb.toString();
                a2 = "UNKNOWN";
                s5 = "KERNEL";
            }
            else {
                if (n5 > 0) {
                    s3 = AppInfo.a(n5);
                }
                if (!s3.equals((Object)String.valueOf(n5))) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(s3);
                    sb2.append("(");
                    sb2.append(n5);
                    sb2.append(")");
                    s3 = sb2.toString();
                }
                s5 = string;
                string = s;
                a2 = s3;
            }
            final HashMap hashMap = new HashMap();
            if (array != null) {
                String[] split;
                for (i = 0; i < array.length; ++i) {
                    s3 = array[i];
                    if (s3 != null) {
                        x.a("Extra message[%d]: %s", new Object[] { i, s3 });
                        split = s3.split("=");
                        if (split.length == 2) {
                            ((Map)hashMap).put((Object)split[0], (Object)split[1]);
                        }
                        else {
                            x.d("bad extraMsg %s", new Object[] { s3 });
                        }
                    }
                }
            }
            else {
                x.c("not found extraMsg", new Object[0]);
            }
            s = (String)((Map)hashMap).get((Object)"HasPendingException");
            boolean b;
            if (s != null && s.equals((Object)"true")) {
                x.a("Native crash happened with a Java pending exception.", new Object[0]);
                b = true;
            }
            else {
                b = false;
            }
            String d = (String)((Map)hashMap).get((Object)"ExceptionProcessName");
            if (d != null && d.length() != 0) {
                x.c("Name of crash process: %s", new Object[] { d });
            }
            else {
                d = this.c.d;
            }
            s3 = (String)((Map)hashMap).get((Object)"ExceptionThreadName");
            Label_0676: {
                if (s3 != null && s3.length() != 0) {
                    x.c("Name of crash thread: %s", new Object[] { s3 });
                    while (true) {
                        for (final Thread thread : Thread.getAllStackTraces().keySet()) {
                            if (thread.getName().equals((Object)s3)) {
                                final StringBuilder sb3 = new StringBuilder();
                                sb3.append(s3);
                                sb3.append("(");
                                sb3.append(thread.getId());
                                sb3.append(")");
                                s3 = sb3.toString();
                                i = 1;
                                s = s3;
                                if (i == 0) {
                                    final StringBuilder sb4 = new StringBuilder();
                                    sb4.append(s3);
                                    sb4.append("(");
                                    sb4.append(n);
                                    sb4.append(")");
                                    s = sb4.toString();
                                }
                                break Label_0676;
                            }
                        }
                        i = 0;
                        continue;
                    }
                }
                final Thread currentThread = Thread.currentThread();
                final StringBuilder sb5 = new StringBuilder();
                sb5.append(currentThread.getName());
                sb5.append("(");
                sb5.append(currentThread.getId());
                sb5.append(")");
                s = sb5.toString();
            }
            n3 /= 1000L;
            s3 = (String)((Map)hashMap).get((Object)"SysLogPath");
            final String s6 = (String)((Map)hashMap).get((Object)"JniLogPath");
            if (!this.d.b()) {
                x.d("no remote but still store!", new Object[0]);
            }
            if (!this.d.c().e && this.d.b()) {
                x.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                a2 = z.a();
                final StringBuilder sb6 = new StringBuilder();
                sb6.append(string);
                sb6.append("\n");
                sb6.append(s2);
                sb6.append("\n");
                sb6.append(a3);
                com.tencent.bugly.crashreport.crash.b.a("NATIVE_CRASH", a2, d, s, sb6.toString(), (CrashDetailBean)null);
                z.b(a);
                return;
            }
            try {
                final CrashDetailBean packageCrashDatas = this.packageCrashDatas(d, s, n2 * 1000L + n3, string, s2, a3, s5, a2, a, s3, s6, s4, null, null, true, b);
                if (packageCrashDatas == null) {
                    x.e("pkg crash datas fail!", new Object[0]);
                    return;
                }
                a = z.a();
                final StringBuilder sb7 = new StringBuilder();
                sb7.append(string);
                sb7.append("\n");
                sb7.append(s2);
                sb7.append("\n");
                sb7.append(a3);
                com.tencent.bugly.crashreport.crash.b.a("NATIVE_CRASH", a, d, s, sb7.toString(), packageCrashDatas);
                try {
                    if (!this.b.b(packageCrashDatas)) {
                        i = 1;
                    }
                    else {
                        i = 0;
                    }
                    s = null;
                    final NativeCrashHandler instance = NativeCrashHandler.getInstance();
                    if (instance != null) {
                        s = instance.getDumpFilePath();
                    }
                    com.tencent.bugly.crashreport.crash.jni.b.a(true, s);
                    if (i != 0) {
                        this.b.a(packageCrashDatas, 3000L, true);
                    }
                    this.b.c(packageCrashDatas);
                    return;
                }
                finally {}
            }
            finally {}
        }
        finally {}
        final Throwable t;
        if (!x.a(t)) {
            t.printStackTrace();
        }
    }
    
    public final CrashDetailBean packageCrashDatas(final String a, String b, final long r, String a2, final String p16, String q, final String k, final String j, final String v, final String s, final String s2, final String l, final byte[] y, final Map<String, String> s3, final boolean b2, final boolean b3) {
        final boolean i = com.tencent.bugly.crashreport.crash.c.a().l();
        if (i) {
            x.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        final CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.h();
        crashDetailBean.f = this.c.k;
        crashDetailBean.g = this.c.q();
        crashDetailBean.m = this.c.g();
        crashDetailBean.n = a2;
        final String s4 = "";
        if (i) {
            a2 = " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
        }
        else {
            a2 = "";
        }
        crashDetailBean.o = a2;
        crashDetailBean.p = p16;
        if (q == null) {
            q = s4;
        }
        crashDetailBean.q = q;
        crashDetailBean.r = r;
        crashDetailBean.u = z.a(crashDetailBean.q.getBytes());
        crashDetailBean.A = a;
        crashDetailBean.B = b;
        crashDetailBean.I = this.c.s();
        crashDetailBean.h = this.c.p();
        crashDetailBean.i = this.c.B();
        crashDetailBean.v = v;
        final NativeCrashHandler instance = NativeCrashHandler.getInstance();
        if (instance != null) {
            b = instance.getDumpFilePath();
        }
        else {
            b = null;
        }
        a2 = b.a(b, v);
        if (!z.a(a2)) {
            crashDetailBean.V = a2;
        }
        crashDetailBean.W = b.b(b);
        crashDetailBean.w = b.a(s, com.tencent.bugly.crashreport.crash.c.e, (String)null, false);
        crashDetailBean.x = b.a(s2, com.tencent.bugly.crashreport.crash.c.e, (String)null, true);
        crashDetailBean.J = j;
        crashDetailBean.K = k;
        crashDetailBean.L = l;
        crashDetailBean.F = this.c.k();
        crashDetailBean.G = this.c.j();
        crashDetailBean.H = this.c.l();
        if (b2) {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.e();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.i();
            if (crashDetailBean.w == null) {
                crashDetailBean.w = z.a(this.a, com.tencent.bugly.crashreport.crash.c.e, (String)null);
            }
            crashDetailBean.y = y.a();
            crashDetailBean.M = this.c.a;
            crashDetailBean.N = this.c.a();
            crashDetailBean.z = z.a(com.tencent.bugly.crashreport.crash.c.f, false);
            int index = crashDetailBean.q.indexOf("java:\n");
            if (index > 0) {
                index += 6;
                if (index < crashDetailBean.q.length()) {
                    b = crashDetailBean.q.substring(index, crashDetailBean.q.length() - 1);
                    if (b.length() > 0 && crashDetailBean.z.containsKey((Object)crashDetailBean.B)) {
                        a2 = (String)crashDetailBean.z.get((Object)crashDetailBean.B);
                        final int index2 = a2.indexOf(b);
                        if (index2 > 0) {
                            b = a2.substring(index2);
                            crashDetailBean.z.put((Object)crashDetailBean.B, (Object)b);
                            crashDetailBean.q = crashDetailBean.q.substring(0, index);
                            final StringBuilder sb = new StringBuilder();
                            sb.append(crashDetailBean.q);
                            sb.append(b);
                            crashDetailBean.q = sb.toString();
                        }
                    }
                }
            }
            if (a == null) {
                crashDetailBean.A = this.c.d;
            }
            this.b.d(crashDetailBean);
            crashDetailBean.Q = this.c.z();
            crashDetailBean.R = this.c.A();
            crashDetailBean.S = this.c.t();
            crashDetailBean.T = this.c.y();
        }
        else {
            crashDetailBean.C = -1L;
            crashDetailBean.D = -1L;
            crashDetailBean.E = -1L;
            if (crashDetailBean.w == null) {
                crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.M = -1L;
            crashDetailBean.Q = -1;
            crashDetailBean.R = -1;
            crashDetailBean.S = s3;
            crashDetailBean.T = this.c.y();
            crashDetailBean.z = null;
            if (a == null) {
                crashDetailBean.A = "unknown(record)";
            }
            if (y != null) {
                crashDetailBean.y = y;
            }
        }
        return crashDetailBean;
    }
}
