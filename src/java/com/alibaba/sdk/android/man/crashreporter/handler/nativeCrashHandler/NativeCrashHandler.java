package com.alibaba.sdk.android.man.crashreporter.handler.nativeCrashHandler;

import com.alibaba.sdk.android.man.crashreporter.c;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import java.io.File;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import com.alibaba.sdk.android.man.crashreporter.handler.a;

public class NativeCrashHandler implements NativeExceptionHandler
{
    private static boolean LOAD_SUCCESS;
    private static NativeCrashHandler nativeCrashHandler;
    private final String MOTU_PATH;
    private final String TOMBSTONE_PATH;
    private a crashReportManager;
    private AtomicBoolean crashing;
    private final String motuPath;
    
    static {
        try {
            System.loadLibrary("Motu");
            NativeCrashHandler.LOAD_SUCCESS = true;
        }
        catch (final Error error) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("load motu library error.", (Throwable)error);
        }
    }
    
    private NativeCrashHandler(final Context context) {
        this.MOTU_PATH = "motu";
        this.TOMBSTONE_PATH = "tombstone";
        this.crashReportManager = null;
        this.motuPath = String.format("%s/%s", new Object[] { context.getDir("tombstone", 0).getAbsolutePath(), "motu" });
        final File file = new File(this.motuPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    
    public static NativeCrashHandler getInstance() {
        synchronized (NativeCrashHandler.class) {
            return NativeCrashHandler.nativeCrashHandler;
        }
    }
    
    public static NativeCrashHandler init(final Context context) {
        synchronized (NativeCrashHandler.class) {
            if (NativeCrashHandler.nativeCrashHandler == null) {
                NativeCrashHandler.nativeCrashHandler = new NativeCrashHandler(context);
            }
            return NativeCrashHandler.nativeCrashHandler;
        }
    }
    
    public static native String regist(final String p0, final boolean p1, final int p2, final long p3, final String p4);
    
    public static native String resetSigHandler();
    
    public static native String unregist();
    
    public NativeExceptionHandler getNativeExceptionHandler() {
        return (NativeExceptionHandler)NativeCrashHandler.nativeCrashHandler;
    }
    
    public void onNativeException(final int n, final int n2, final long n3, final long n4, final String s, final String s2, final String s3, final String s4, final int n5, final String s5, final int n6, final int n7, final int n8, final String s6, final String s7) {
        while (true) {
            if (s4 != null) {
                try {
                    if (this.crashReportManager != null) {
                        this.crashReportManager.a(s5, s4, s3);
                    }
                    else {
                        com.alibaba.sdk.android.man.crashreporter.b.a.e("native: crash manager is null!");
                    }
                    try {
                        unregist();
                    }
                    catch (final UnsatisfiedLinkError unsatisfiedLinkError) {
                        com.alibaba.sdk.android.man.crashreporter.b.a.d("unregist native crash err,UnsatisfiedLinkError:", (Throwable)unsatisfiedLinkError);
                    }
                    catch (final Exception ex) {
                        com.alibaba.sdk.android.man.crashreporter.b.a.d("unregist native crash err", (Throwable)ex);
                    }
                    return;
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("native crash stack or path is null!");
                }
                catch (final Exception ex2) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("onNativeException err", (Throwable)ex2);
                }
                return;
            }
            continue;
        }
    }
    
    public void onNativeExceptionStart(final String s, final String s2, final String s3) {
        com.alibaba.sdk.android.man.crashreporter.b.a.e("onNativeExceptionStart call back.");
        while (true) {
            if (s != null) {
                try {
                    MotuCrashReporter.getInstance().setCrashReporterState(1);
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("stuck handler is closed");
                    if (this.crashReportManager != null) {
                        this.crashReportManager.a(s2, s, s3);
                        return;
                    }
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("native: crash manager is null!");
                    return;
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("native crash stack or path is null!");
                }
                catch (final Exception ex) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("onNativeException err", (Throwable)ex);
                }
                return;
            }
            continue;
        }
    }
    
    public boolean regist(final AtomicBoolean crashing, final a crashReportManager, final boolean b, final c c) {
        if (NativeCrashHandler.LOAD_SUCCESS) {
            this.crashing = crashing;
            this.crashReportManager = crashReportManager;
            String appVersion;
            if ((appVersion = c.appVersion) == null) {
                appVersion = "";
            }
            try {
                return regist(this.motuPath, false, 1, c.startupTime, appVersion) != null;
            }
            catch (final UnsatisfiedLinkError unsatisfiedLinkError) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("regist native crash err,UnsatisfiedLinkError:", (Throwable)unsatisfiedLinkError);
                return false;
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("regist native crash err", (Throwable)ex);
            }
        }
        return false;
    }
    
    public boolean removeNativeCrashHandler() {
        if (resetSigHandler() != null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Native crash handler is removed success");
            return true;
        }
        com.alibaba.sdk.android.man.crashreporter.b.a.e("Native crash handler is removed failed");
        return false;
    }
}
