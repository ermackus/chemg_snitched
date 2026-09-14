package com.alibaba.sdk.android.man.crashreporter.handler.a;

import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import java.io.IOException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicBoolean;

public class a implements Thread$UncaughtExceptionHandler
{
    private Integer a;
    private Thread$UncaughtExceptionHandler a;
    private com.alibaba.sdk.android.man.crashreporter.handler.a crashReportManager;
    private final AtomicBoolean crashing;
    
    public a(final AtomicBoolean crashing, final com.alibaba.sdk.android.man.crashreporter.handler.a crashReportManager) {
        this.a = 20;
        this.crashReportManager = null;
        this.crashing = crashing;
        this.crashReportManager = crashReportManager;
        this.a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler((Thread$UncaughtExceptionHandler)this);
    }
    
    private String a(Throwable string) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter((Writer)stringWriter);
        try {
            string.printStackTrace(printWriter);
            string = (Throwable)stringWriter.toString();
            return (String)string;
        }
        finally {
            printWriter.close();
            try {
                stringWriter.close();
            }
            catch (final IOException ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("close StringWriter failed.", (Throwable)ex);
            }
        }
    }
    
    private Throwable a(Throwable cause) {
        for (int n = 1; cause.getCause() != null && cause != cause.getCause() && n <= this.a; ++n, cause = cause.getCause()) {}
        return cause;
    }
    
    private String b(Throwable a) {
        a = this.a(a);
        String simpleName;
        if (a != null) {
            simpleName = a.getClass().getSimpleName();
        }
        else {
            simpleName = "";
        }
        return simpleName;
    }
    
    public void b() {
        Thread.setDefaultUncaughtExceptionHandler(this.a);
    }
    
    public void uncaughtException(final Thread thread, final Throwable t) {
        if (this.crashing.compareAndSet(false, true)) {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("uncaughtException start.");
            Label_0119: {
                try {
                    MotuCrashReporter.getInstance().setCrashReporterState(0);
                    final String b = this.b(t);
                    final String a = this.a(t);
                    if (b != null && a != null) {
                        if (this.crashReportManager != null) {
                            this.crashReportManager.a(t, thread, b, a);
                        }
                        else {
                            com.alibaba.sdk.android.man.crashreporter.b.a.e("java: crash manager is null!");
                        }
                    }
                    else {
                        com.alibaba.sdk.android.man.crashreporter.b.a.e("uncaughtException exception or backtrace is null!");
                    }
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("uncaughtException end.");
                    if (this.a != null) {
                        break Label_0119;
                    }
                }
                finally {
                    try {
                        final Throwable t2;
                        com.alibaba.sdk.android.man.crashreporter.b.a.d("uncaughtException error.", t2);
                    }
                    finally {
                        com.alibaba.sdk.android.man.crashreporter.b.a.e("uncaughtException end.");
                        final Thread$UncaughtExceptionHandler a2 = this.a;
                        if (a2 != null) {
                            a2.uncaughtException(thread, t);
                        }
                    }
                }
            }
        }
    }
}
