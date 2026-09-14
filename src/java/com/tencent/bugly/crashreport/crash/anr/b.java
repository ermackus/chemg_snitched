package com.tencent.bugly.crashreport.crash.anr;

import com.tencent.bugly.proguard.aa;
import android.os.Build$VERSION;
import java.io.IOException;
import java.util.Map$Entry;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import android.text.TextUtils;
import android.os.Looper;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import java.util.Map;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.y;
import java.util.HashMap;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.util.Iterator;
import java.util.List;
import android.os.Process;
import android.app.ActivityManager;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import android.app.ActivityManager$ProcessErrorStateInfo;
import com.tencent.bugly.proguard.ab;
import android.os.FileObserver;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.crashreport.common.info.a;
import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;
import com.tencent.bugly.proguard.ac;

public final class b implements ac
{
    private static b m;
    private AtomicInteger a;
    private long b;
    private final Context c;
    private final a d;
    private final w e;
    private String f;
    private final com.tencent.bugly.crashreport.crash.b g;
    private FileObserver h;
    private boolean i;
    private ab j;
    private int k;
    private ActivityManager$ProcessErrorStateInfo l;
    
    private b(final Context context, final com.tencent.bugly.crashreport.common.strategy.a a, final a d, final w e, final com.tencent.bugly.crashreport.crash.b g) {
        this.a = new AtomicInteger(0);
        this.b = -1L;
        this.i = true;
        this.c = z.a(context);
        this.f = context.getDir("bugly", 0).getAbsolutePath();
        this.d = d;
        this.e = e;
        this.g = g;
        this.l = new ActivityManager$ProcessErrorStateInfo();
    }
    
    private ActivityManager$ProcessErrorStateInfo a(final Context context, final long n) {
        try {
            x.c("to find!", new Object[0]);
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            int n2 = 0;
            while (true) {
                x.c("waiting!", new Object[0]);
                final List processesInErrorState = activityManager.getProcessesInErrorState();
                if (processesInErrorState != null) {
                    for (final ActivityManager$ProcessErrorStateInfo activityManager$ProcessErrorStateInfo : processesInErrorState) {
                        if (activityManager$ProcessErrorStateInfo.condition == 2) {
                            x.c("found!", new Object[0]);
                            return activityManager$ProcessErrorStateInfo;
                        }
                    }
                }
                z.b(500L);
                if (n2 >= 20L) {
                    x.c("end!", new Object[0]);
                    break;
                }
                ++n2;
            }
        }
        catch (final OutOfMemoryError outOfMemoryError) {
            this.l.pid = Process.myPid();
            final ActivityManager$ProcessErrorStateInfo l = this.l;
            final StringBuilder sb = new StringBuilder("bugly sdk waitForAnrProcessStateChanged encount error:");
            sb.append(outOfMemoryError.getMessage());
            l.shortMsg = sb.toString();
            return this.l;
        }
        catch (final Exception ex) {
            x.b((Throwable)ex);
        }
        return null;
    }
    
    private CrashDetailBean a(final com.tencent.bugly.crashreport.crash.anr.a a) {
        final CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.e();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.F = this.d.k();
            crashDetailBean.G = this.d.j();
            crashDetailBean.H = this.d.l();
            if (!com.tencent.bugly.crashreport.common.info.b.m()) {
                crashDetailBean.w = z.a(this.c, com.tencent.bugly.crashreport.crash.c.e, (String)null);
            }
            crashDetailBean.b = 3;
            crashDetailBean.e = this.d.h();
            crashDetailBean.f = this.d.k;
            crashDetailBean.g = this.d.q();
            crashDetailBean.m = this.d.g();
            crashDetailBean.n = "ANR_EXCEPTION";
            crashDetailBean.o = a.f;
            crashDetailBean.q = a.g;
            (crashDetailBean.P = (Map)new HashMap()).put((Object)"BUGLY_CR_01", (Object)a.e);
            int index = -1;
            if (crashDetailBean.q != null) {
                index = crashDetailBean.q.indexOf("\n");
            }
            String substring;
            if (index > 0) {
                substring = crashDetailBean.q.substring(0, index);
            }
            else {
                substring = "GET_FAIL";
            }
            crashDetailBean.p = substring;
            crashDetailBean.r = a.c;
            if (crashDetailBean.q != null) {
                crashDetailBean.u = z.a(crashDetailBean.q.getBytes());
            }
            crashDetailBean.z = a.b;
            crashDetailBean.A = a.a;
            crashDetailBean.B = "main(1)";
            crashDetailBean.I = this.d.s();
            crashDetailBean.h = this.d.p();
            crashDetailBean.i = this.d.B();
            crashDetailBean.v = a.d;
            crashDetailBean.L = this.d.o;
            crashDetailBean.M = this.d.a;
            crashDetailBean.N = this.d.a();
            if (!com.tencent.bugly.crashreport.common.info.b.m()) {
                this.g.d(crashDetailBean);
            }
            crashDetailBean.Q = this.d.z();
            crashDetailBean.R = this.d.A();
            crashDetailBean.S = this.d.t();
            crashDetailBean.T = this.d.y();
            crashDetailBean.y = y.a();
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
        }
        return crashDetailBean;
    }
    
    public static b a(final Context context, final com.tencent.bugly.crashreport.common.strategy.a a, final a a2, final w w, final p p7, final com.tencent.bugly.crashreport.crash.b b, final BuglyStrategy$a buglyStrategy$a) {
        if (b.m == null) {
            b.m = new b(context, a, a2, w, b);
        }
        return b.m;
    }
    
    private boolean a(final Context context, final String s, final ActivityManager$ProcessErrorStateInfo activityManager$ProcessErrorStateInfo, final long c, final Map<String, String> b) {
        final com.tencent.bugly.crashreport.crash.anr.a a = new com.tencent.bugly.crashreport.crash.anr.a();
        a.c = c;
        String a2;
        if (activityManager$ProcessErrorStateInfo != null) {
            a2 = activityManager$ProcessErrorStateInfo.processName;
        }
        else {
            a2 = AppInfo.a(Process.myPid());
        }
        a.a = a2;
        final String s2 = "";
        String shortMsg;
        if (activityManager$ProcessErrorStateInfo != null) {
            shortMsg = activityManager$ProcessErrorStateInfo.shortMsg;
        }
        else {
            shortMsg = "";
        }
        a.f = shortMsg;
        String longMsg = s2;
        if (activityManager$ProcessErrorStateInfo != null) {
            longMsg = activityManager$ProcessErrorStateInfo.longMsg;
        }
        a.e = longMsg;
        a.b = b;
        final Thread thread = Looper.getMainLooper().getThread();
        if (b != null) {
            for (final String s3 : b.keySet()) {
                if (s3.startsWith(thread.getName())) {
                    a.g = (String)b.get((Object)s3);
                    break;
                }
            }
        }
        if (TextUtils.isEmpty((CharSequence)a.g)) {
            a.g = "main stack is null , some error may be encountered.";
        }
        final long c2 = a.c;
        final String d = a.d;
        final String a3 = a.a;
        final String g = a.g;
        final String f = a.f;
        final String e = a.e;
        int size;
        if (a.b == null) {
            size = 0;
        }
        else {
            size = a.b.size();
        }
        x.c("anr tm:%d\ntr:%s\nproc:%s\nmain stack:%s\nsMsg:%s\n lMsg:%s\n threads:%d", new Object[] { c2, d, a3, g, f, e, size });
        x.a("found visiable anr , start to upload!", new Object[0]);
        final CrashDetailBean a4 = this.a(a);
        if (a4 == null) {
            x.e("pack anr fail!", new Object[0]);
            return false;
        }
        c.a().a(a4);
        if (a4.a >= 0L) {
            x.a("backup anr record success!", new Object[0]);
        }
        else {
            x.d("backup anr record fail!", new Object[0]);
        }
        if (s != null && new File(s).exists()) {
            final String f2 = this.f;
            final StringBuilder sb = new StringBuilder("bugly_trace_");
            sb.append(c);
            sb.append(".txt");
            a.d = new File(f2, sb.toString()).getAbsolutePath();
            this.a.set(3);
            if (a(s, a.d, a.a)) {
                x.a("backup trace success", new Object[0]);
            }
        }
        else {
            final File h = this.h();
            x.a("traceFile is %s", new Object[] { h });
            if (h != null) {
                a4.v = h.getAbsolutePath();
            }
        }
        b.a("ANR", z.a(), a.a, "main", a.g, a4);
        if (!this.g.a(a4)) {
            this.g.a(a4, 3000L, true);
        }
        this.g.c(a4);
        return true;
    }
    
    private static boolean a(String ex, String s, String s2) {
        final TraceFileHelper$a targetDumpInfo = TraceFileHelper.readTargetDumpInfo(s2, (String)ex, true);
        if (targetDumpInfo != null && targetDumpInfo.d != null) {
            if (targetDumpInfo.d.size() > 0) {
                final File file = new File(s);
                try {
                    if (!file.exists()) {
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        file.createNewFile();
                    }
                    if (!file.exists() || !file.canWrite()) {
                        x.e("backup file create fail %s", new Object[] { s });
                        return false;
                    }
                    Object o = null;
                    final IOException ex2 = ex = null;
                    try {
                        try {
                            ex = ex2;
                            ex = ex2;
                            final FileWriter fileWriter = new FileWriter(file, false);
                            ex = ex2;
                            s = (String)new BufferedWriter((Writer)fileWriter);
                            try {
                                o = targetDumpInfo.d.get((Object)"main");
                                if (o != null && o.length >= 3) {
                                    s2 = o[0];
                                    ex = o[1];
                                    o = o[2];
                                    final StringBuilder sb = new StringBuilder("\"main\" tid=");
                                    sb.append((String)o);
                                    sb.append(" :\n");
                                    sb.append(s2);
                                    sb.append("\n");
                                    sb.append((String)ex);
                                    sb.append("\n\n");
                                    ((BufferedWriter)s).write(sb.toString());
                                    ((BufferedWriter)s).flush();
                                }
                                for (final Map$Entry map$Entry : targetDumpInfo.d.entrySet()) {
                                    if (!((String)map$Entry.getKey()).equals((Object)"main")) {
                                        if (map$Entry.getValue() == null || ((String[])map$Entry.getValue()).length < 3) {
                                            continue;
                                        }
                                        o = ((String[])map$Entry.getValue())[0];
                                        ex = (IOException)((String[])map$Entry.getValue())[1];
                                        final String s3 = ((String[])map$Entry.getValue())[2];
                                        final StringBuilder sb2 = new StringBuilder("\"");
                                        sb2.append((String)map$Entry.getKey());
                                        sb2.append("\" tid=");
                                        sb2.append(s3);
                                        sb2.append(" :\n");
                                        sb2.append((String)o);
                                        sb2.append("\n");
                                        sb2.append((String)ex);
                                        sb2.append("\n\n");
                                        ((BufferedWriter)s).write(sb2.toString());
                                        ((BufferedWriter)s).flush();
                                    }
                                }
                                try {
                                    ((BufferedWriter)s).close();
                                }
                                catch (final IOException ex) {
                                    if (!x.a((Throwable)ex)) {
                                        ex.printStackTrace();
                                    }
                                }
                                return true;
                            }
                            catch (final IOException ex3) {}
                            finally {
                                ex = (IOException)s;
                            }
                        }
                        finally {}
                    }
                    catch (final IOException ex4) {
                        s = (String)o;
                    }
                    final IOException ex4;
                    if (!x.a((Throwable)ex4)) {
                        ex4.printStackTrace();
                    }
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append(ex4.getClass().getName());
                    sb3.append(":");
                    sb3.append(ex4.getMessage());
                    x.e("dump trace fail %s", new Object[] { sb3.toString() });
                    if (s != null) {
                        try {
                            ((BufferedWriter)s).close();
                        }
                        catch (final IOException ex5) {
                            if (!x.a((Throwable)ex5)) {
                                ex5.printStackTrace();
                            }
                        }
                    }
                    return false;
                    if (ex != null) {
                        try {
                            ((BufferedWriter)ex).close();
                        }
                        catch (final IOException ex6) {
                            if (!x.a((Throwable)ex6)) {
                                ex6.printStackTrace();
                            }
                        }
                    }
                }
                catch (final Exception ex7) {
                    if (!x.a((Throwable)ex7)) {
                        ex7.printStackTrace();
                    }
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append(ex7.getClass().getName());
                    sb4.append(":");
                    sb4.append(ex7.getMessage());
                    x.e("backup file create error! %s  %s", new Object[] { sb4.toString(), s });
                    return false;
                }
            }
        }
        x.e("not found trace dump for %s", new Object[] { s2 });
        return false;
    }
    
    private void b(final boolean b) {
        synchronized (this) {
            if (Build$VERSION.SDK_INT <= 19) {
                if (b) {
                    this.d();
                    return;
                }
                this.e();
            }
            else {
                if (b) {
                    this.i();
                    return;
                }
                this.j();
            }
        }
    }
    
    private void c(final boolean i) {
        synchronized (this) {
            if (this.i != i) {
                x.a("user change anr %b", new Object[] { i });
                this.i = i;
            }
        }
    }
    
    private void d() {
        synchronized (this) {
            if (this.f()) {
                x.d("start when started!", new Object[0]);
                return;
            }
            final b$1 h = new b$1(this, "/data/anr/", 8);
            this.h = (FileObserver)h;
            try {
                ((FileObserver)h).startWatching();
                x.a("start anr monitor!", new Object[0]);
                this.e.a((Runnable)new b$2(this));
            }
            finally {
                this.h = null;
                x.d("start anr monitor failed!", new Object[0]);
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
            }
        }
    }
    
    private void e() {
        synchronized (this) {
            if (!this.f()) {
                x.d("close when closed!", new Object[0]);
                return;
            }
            try {
                this.h.stopWatching();
                this.h = null;
                x.d("close anr monitor!", new Object[0]);
            }
            finally {
                x.d("stop anr monitor failed!", new Object[0]);
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
            }
        }
    }
    
    private boolean f() {
        synchronized (this) {
            return this.h != null;
        }
    }
    
    private boolean g() {
        synchronized (this) {
            return this.i;
        }
    }
    
    private File h() {
        final long currentTimeMillis = System.currentTimeMillis();
        final File file = new File(this.f);
        if (file.exists() && file.isDirectory()) {
            try {
                final File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    return null;
                }
                for (final File file2 : listFiles) {
                    final String name = file2.getName();
                    if (name.startsWith("bugly_trace_")) {
                        try {
                            final int index = name.indexOf(".txt");
                            if (index > 0) {
                                final long long1 = Long.parseLong(name.substring(12, index));
                                final long n = (currentTimeMillis - long1) / 1000L;
                                x.c("current time %d trace time is %d s", new Object[] { currentTimeMillis, long1 });
                                x.c("current time minus trace time is %d s", new Object[] { n });
                                if (n < 30L) {
                                    return file2;
                                }
                            }
                        }
                        finally {
                            final StringBuilder sb = new StringBuilder("Trace file that has invalid format: ");
                            sb.append(name);
                            x.c(sb.toString(), new Object[0]);
                        }
                    }
                }
            }
            finally {
                final Throwable t;
                x.a(t);
            }
        }
        return null;
    }
    
    private void i() {
        synchronized (this) {
            if (this.f()) {
                x.d("start when started!", new Object[0]);
                return;
            }
            if (TextUtils.isEmpty((CharSequence)this.f)) {
                return;
            }
            if (this.j == null || !this.j.isAlive()) {
                final ab j = new ab();
                this.j = j;
                final StringBuilder sb = new StringBuilder("Bugly-ThreadMonitor");
                sb.append(this.k++);
                j.setName(sb.toString());
                this.j.a();
                this.j.a((ac)this);
                this.j.d();
                if (this.e != null) {
                    this.e.a((Runnable)new b$3(this));
                }
            }
            final b$4 h = new b$4(this, this.f, 256);
            this.h = (FileObserver)h;
            try {
                ((FileObserver)h).startWatching();
                x.a("startWatchingPrivateAnrDir! dumFilePath is %s", new Object[] { this.f });
                this.e.a((Runnable)new b$5(this));
            }
            finally {
                this.h = null;
                x.d("startWatchingPrivateAnrDir failed!", new Object[0]);
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
            }
        }
    }
    
    private void j() {
        synchronized (this) {
            if (!this.f()) {
                x.d("close when closed!", new Object[0]);
                return;
            }
            if (this.j != null) {
                this.j.c();
                this.j.b();
                this.j.b((ac)this);
                this.j = null;
            }
            x.a("stopWatchingPrivateAnrDir", new Object[0]);
            try {
                this.h.stopWatching();
                this.h = null;
                x.d("close anr monitor!", new Object[0]);
            }
            finally {
                x.d("stop anr monitor failed!", new Object[0]);
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
            }
        }
    }
    
    public final void a(final String s) {
        synchronized (this) {
            if (this.a.get() != 0) {
                x.c("trace started return ", new Object[0]);
                return;
            }
            this.a.set(1);
            monitorexit(this);
            try {
                x.c("read trace first dump for create time!", new Object[0]);
                final TraceFileHelper$a firstDumpInfo = TraceFileHelper.readFirstDumpInfo(s, false);
                long c;
                if (firstDumpInfo != null) {
                    c = firstDumpInfo.c;
                }
                else {
                    c = -1L;
                }
                long currentTimeMillis = c;
                if (c == -1L) {
                    x.d("trace dump fail could not get time!", new Object[0]);
                    currentTimeMillis = System.currentTimeMillis();
                }
                if (Math.abs(currentTimeMillis - this.b) < 10000L) {
                    x.d("should not process ANR too Fre in %d", new Object[] { 10000 });
                }
                else {
                    this.b = currentTimeMillis;
                    this.a.set(1);
                    try {
                        final Map a = z.a(com.tencent.bugly.crashreport.crash.c.f, false);
                        if (a != null && a.size() > 0) {
                            final ActivityManager$ProcessErrorStateInfo a2 = this.a(this.c, 10000L);
                            if ((this.l = a2) == null) {
                                x.c("proc state is unvisiable!", new Object[0]);
                            }
                            else if (a2.pid != Process.myPid()) {
                                x.c("not mind proc!", new Object[] { this.l.processName });
                            }
                            else {
                                x.a("found visiable anr , start to process!", new Object[0]);
                                this.a(this.c, s, this.l, currentTimeMillis, (Map<String, String>)a);
                            }
                        }
                        else {
                            x.d("can't get all thread skip this anr", new Object[0]);
                        }
                    }
                    finally {
                        final Throwable t;
                        x.a(t);
                        x.e("get all thread stack fail!", new Object[0]);
                    }
                }
                this.a.set(0);
            }
            finally {
                try {
                    final Throwable t2;
                    if (!x.a(t2)) {
                        t2.printStackTrace();
                    }
                    x.e("handle anr error %s", new Object[] { t2.getClass().toString() });
                }
                finally {
                    this.a.set(0);
                }
            }
        }
    }
    
    public final void a(final boolean b) {
        this.c(b);
        final boolean g = this.g();
        final com.tencent.bugly.crashreport.common.strategy.a a = com.tencent.bugly.crashreport.common.strategy.a.a();
        boolean b2 = g;
        if (a != null) {
            b2 = (g && a.c().e);
        }
        if (b2 != this.f()) {
            x.a("anr changed to %b", new Object[] { b2 });
            this.b(b2);
        }
    }
    
    public final boolean a() {
        return this.a.get() != 0;
    }
    
    public final boolean a(final aa aa) {
        final HashMap hashMap = new HashMap();
        if (aa.e().equals(Looper.getMainLooper())) {
            Map<String, String> map = null;
            try {
                z.a(200000, false);
            }
            finally {
                final Throwable t;
                x.b(t);
                ((Map)hashMap).put((Object)"main", (Object)t.getMessage());
                map = (Map<String, String>)hashMap;
            }
            x.c("onThreadBlock found visiable anr , start to process!", new Object[0]);
            this.a(this.c, "", null, System.currentTimeMillis(), map);
        }
        else {
            x.c("anr handler onThreadBlock only care main thread ,current thread is: %s", new Object[] { aa.d() });
        }
        return true;
    }
    
    protected final void b() {
        final long b = z.b();
        final long g = com.tencent.bugly.crashreport.crash.c.g;
        final File file = new File(this.f);
        if (file.exists() && file.isDirectory()) {
            try {
                final File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    if (listFiles.length != 0) {
                        final int length = listFiles.length;
                        int i = 0;
                        int n = 0;
                        while (i < length) {
                            final File file2 = listFiles[i];
                            final String name = file2.getName();
                            final StringBuilder sb = new StringBuilder("Number Trace file : ");
                            sb.append(name);
                            x.c(sb.toString(), new Object[0]);
                            final boolean startsWith = name.startsWith("bugly_trace_");
                            int index = n;
                            Label_0231: {
                                if (startsWith) {
                                    try {
                                        index = name.indexOf(".txt");
                                        if (index > 0 && Long.parseLong(name.substring(12, index)) >= b - g) {
                                            index = n;
                                            break Label_0231;
                                        }
                                    }
                                    finally {
                                        final StringBuilder sb2 = new StringBuilder("Trace file that has invalid format: ");
                                        sb2.append(name);
                                        x.c(sb2.toString(), new Object[0]);
                                    }
                                    index = n;
                                    if (file2.delete()) {
                                        index = n + 1;
                                    }
                                }
                            }
                            ++i;
                            n = index;
                        }
                        final StringBuilder sb3 = new StringBuilder("Number of overdue trace files that has deleted: ");
                        sb3.append(n);
                        x.c(sb3.toString(), new Object[0]);
                    }
                }
            }
            finally {
                final Throwable t;
                x.a(t);
            }
        }
    }
    
    public final void c() {
        synchronized (this) {
            x.d("customer decides whether to open or close.", new Object[0]);
        }
    }
}
