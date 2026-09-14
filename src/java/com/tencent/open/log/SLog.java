package com.tencent.open.log;

import android.util.Log;
import android.text.TextUtils;
import com.tencent.open.utils.f;
import android.os.Environment;
import java.io.File;

public class SLog implements TraceLevel
{
    public static final String TAG = "openSDK_LOG";
    private static boolean c;
    public static SLog instance;
    protected a a;
    private Tracer b;
    
    private SLog() {
        this.a = new a(new b(a(), com.tencent.open.log.c.m, com.tencent.open.log.c.g, com.tencent.open.log.c.h, com.tencent.open.log.c.c, (long)com.tencent.open.log.c.i, 10, com.tencent.open.log.c.e, com.tencent.open.log.c.n));
    }
    
    protected static File a() {
        final String d = com.tencent.open.log.c.d;
        File file3 = null;
        try {
            final d$c b = d$b.b();
            if (b != null && b.c() > com.tencent.open.log.c.f) {
                final File file = new File(Environment.getExternalStorageDirectory(), d);
            }
            else {
                final File file2 = new File(f.c(), d);
            }
        }
        finally {
            final Throwable t;
            e("openSDK_LOG", "getLogFilePath:", t);
            file3 = null;
        }
        return file3;
    }
    
    private String a(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return "";
        }
        String s2 = s;
        if (d.a(s)) {
            s2 = "xxxxxx";
        }
        return s2;
    }
    
    public static final void d(final String s, final String s2) {
        getInstance().a(2, s, s2, null);
    }
    
    public static final void d(final String s, final String s2, final Throwable t) {
        getInstance().a(2, s, s2, t);
    }
    
    public static final void e(final String s, final String s2) {
        getInstance().a(16, s, s2, null);
    }
    
    public static final void e(final String s, final String s2, final Throwable t) {
        getInstance().a(16, s, s2, t);
    }
    
    public static void flushLogs() {
        getInstance().c();
    }
    
    public static SLog getInstance() {
        if (SLog.instance == null) {
            synchronized (SLog.class) {
                if (SLog.instance == null) {
                    SLog.instance = new SLog();
                    SLog.c = true;
                }
            }
        }
        return SLog.instance;
    }
    
    public static final void i(final String s, final String s2) {
        getInstance().a(4, s, s2, null);
    }
    
    public static final void i(final String s, final String s2, final Throwable t) {
        getInstance().a(4, s, s2, t);
    }
    
    public static void release() {
        synchronized (SLog.class) {
            getInstance().b();
            if (SLog.instance != null) {
                SLog.instance = null;
            }
        }
    }
    
    public static final void u(final String s, final String s2) {
        getInstance().a(32, s, s2, null);
    }
    
    public static final void u(final String s, final String s2, final Throwable t) {
        getInstance().a(32, s, s2, t);
    }
    
    public static final void v(final String s, final String s2) {
        getInstance().a(1, s, s2, null);
    }
    
    public static final void v(final String s, final String s2, final Throwable t) {
        getInstance().a(1, s, s2, t);
    }
    
    public static final void w(final String s, final String s2) {
        getInstance().a(8, s, s2, null);
    }
    
    public static final void w(final String s, final String s2, final Throwable t) {
        getInstance().a(8, s, s2, t);
    }
    
    protected void a(final int n, final String s, final String s2, final Throwable t) {
        if (SLog.c) {
            final String b = f.b();
            if (!TextUtils.isEmpty((CharSequence)b)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(b);
                sb.append(" SDK_VERSION:");
                sb.append("3.5.4.lite");
                final String string = sb.toString();
                if (this.a == null) {
                    return;
                }
                e.a.a(32, Thread.currentThread(), System.currentTimeMillis(), "openSDK_LOG", string, (Throwable)null);
                this.a.a(32, Thread.currentThread(), System.currentTimeMillis(), "openSDK_LOG", string, (Throwable)null);
                SLog.c = false;
            }
        }
        e.a.a(n, Thread.currentThread(), System.currentTimeMillis(), s, s2, t);
        if (d$a.a(com.tencent.open.log.c.b, n)) {
            final a a = this.a;
            if (a == null) {
                return;
            }
            a.a(n, Thread.currentThread(), System.currentTimeMillis(), s, s2, t);
        }
        final Tracer b2 = this.b;
        if (b2 != null) {
            try {
                b2.a(n, Thread.currentThread(), System.currentTimeMillis(), s, this.a(s2), t);
            }
            catch (final Exception ex) {
                Log.e(s, "Exception", (Throwable)ex);
            }
        }
    }
    
    protected void b() {
        final a a = this.a;
        if (a != null) {
            a.a();
            this.a.b();
            this.a = null;
        }
    }
    
    protected void c() {
        final a a = this.a;
        if (a != null) {
            a.a();
        }
    }
    
    public void setCustomLogger(final Tracer b) {
        this.b = b;
    }
}
