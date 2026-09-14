package com.alibaba.sdk.android.man.crashreporter.handler;

import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import com.alibaba.sdk.android.man.util.UTWrapper;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import com.alibaba.sdk.android.man.crashreporter.IUTCrashCaughtListener;
import java.util.HashMap;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import java.util.Map;
import com.alibaba.sdk.android.man.crashreporter.handler.nativeCrashHandler.NativeCrashHandler;
import java.util.concurrent.atomic.AtomicBoolean;
import com.alibaba.sdk.android.man.crashreporter.d.c;
import android.content.Context;

public class a implements b
{
    private static int w = 61005;
    private Context a;
    private com.alibaba.sdk.android.man.crashreporter.a.b a;
    private com.alibaba.sdk.android.man.crashreporter.c.b a;
    private c a;
    private com.alibaba.sdk.android.man.crashreporter.handler.a.a a;
    private com.alibaba.sdk.android.man.crashreporter.handler.b.a a;
    private c b;
    private AtomicBoolean crashing;
    private NativeCrashHandler nativeCrashHandler;
    
    public a() {
        this.a = null;
        this.a = null;
        this.b = null;
        this.a = null;
        this.a = null;
        this.a = null;
        this.nativeCrashHandler = null;
        this.a = null;
        this.crashing = new AtomicBoolean(false);
    }
    
    private Map a(final Throwable t, final Thread thread) {
        final List myListenerList = MotuCrashReporter.getInstance().getMyListenerList();
        final String strExtraInfo = MotuCrashReporter.getInstance().getStrExtraInfo();
        final HashMap hashMap = new HashMap();
        int i = 0;
        try {
            while (i < myListenerList.size()) {
                final IUTCrashCaughtListener iutCrashCaughtListener = (IUTCrashCaughtListener)myListenerList.get(i);
                com.alibaba.sdk.android.man.crashreporter.b.a.b("ext listener is:", iutCrashCaughtListener.toString());
                final Map onCrashCaught = iutCrashCaughtListener.onCrashCaught(thread, t);
                if (onCrashCaught != null) {
                    for (final Map$Entry map$Entry : onCrashCaught.entrySet()) {
                        ((Map)hashMap).put(map$Entry.getKey(), (Object)map$Entry.getValue().toString());
                    }
                }
                ++i;
            }
            if (strExtraInfo != null) {
                ((Map)hashMap).put((Object)"exaInfo", (Object)strExtraInfo);
            }
            if (((Map)hashMap).size() > 0) {
                return (Map)hashMap;
            }
        }
        finally {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("Listener's extraMsg store error.", t);
        }
        return null;
    }
    
    private boolean a(final String s) {
        final List mySenderListenerList = MotuCrashReporter.getInstance().getMySenderListenerList();
        if (mySenderListenerList != null) {
            try {
                if (mySenderListenerList.size() != 0) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("start call sender listener!");
                    for (int i = 0; i < mySenderListenerList.size(); ++i) {
                        ((com.alibaba.sdk.android.man.crashreporter.a)mySenderListenerList.get(i)).a(s);
                    }
                    return true;
                }
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("call sender listener err", (Throwable)ex);
            }
        }
        return false;
    }
    
    private String b(final String s) {
        String replaceAll = s;
        try {
            if (!i.b(s)) {
                replaceAll = s.replaceAll("\n", "++");
            }
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getMessageToUTArgs err.", (Throwable)ex);
            replaceAll = s;
        }
        return replaceAll;
    }
    
    public void a(final String s, final String s2, final String s3) {
        try {
            if (this.a != null && this.b != null && this.a != null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("native crash handler start.");
                this.a.b(s2, s, s3, this.a(null, null));
                UTWrapper.commitCrashEvent();
            }
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("handle native stackTrace failure", (Throwable)ex);
        }
    }
    
    public void a(final Throwable t, final Thread thread, final String s, final String s2) {
        try {
            if (this.a != null && this.b != null && this.a != null) {
                com.alibaba.sdk.android.man.crashreporter.handler.c.b.a("TBCRASH_REPORTER_SDK", 0, com.alibaba.sdk.android.man.crashreporter.handler.a.w);
                com.alibaba.sdk.android.man.crashreporter.b.a.e("crash handler start.");
                this.a.a(s, s2, this.b(s2), this.a(t, thread));
                UTWrapper.commitCrashEvent();
            }
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.a("handleJavaCrash err!", (Throwable)ex);
        }
    }
    
    public boolean a(final Context a, final ReporterConfigure reporterConfigure, final com.alibaba.sdk.android.man.crashreporter.c c) {
        Label_0325: {
            if (a == null) {
                break Label_0325;
            }
            try {
                this.a = a;
                this.a = (com.alibaba.sdk.android.man.crashreporter.a.b)new com.alibaba.sdk.android.man.crashreporter.a.a();
                this.b = (c)new com.alibaba.sdk.android.man.crashreporter.d.b();
                this.a = (c)new com.alibaba.sdk.android.man.crashreporter.d.a(a, c);
                this.a = (com.alibaba.sdk.android.man.crashreporter.c.b)new com.alibaba.sdk.android.man.crashreporter.c.a();
                if (!this.a.a(a, reporterConfigure, c, this.b, this.a)) {
                    return false;
                }
                if (!this.b.c(a)) {
                    return false;
                }
                if (!this.a.a(a, this.a, this.b, this.a)) {
                    return false;
                }
                if (reporterConfigure.enableCatchUncaughtException) {
                    this.a = new com.alibaba.sdk.android.man.crashreporter.handler.a.a(this.crashing, this);
                }
                if (reporterConfigure.enableCatchNativeException) {
                    final NativeCrashHandler init = NativeCrashHandler.init(a);
                    this.nativeCrashHandler = init;
                    if (init.regist(this.crashing, this, reporterConfigure.enableDebug, c)) {
                        com.alibaba.sdk.android.man.crashreporter.b.a.e("native crash handler regist succ!");
                    }
                }
                if (c.appVersion != null) {
                    final String[] split = c.appVersion.split("\\.");
                    if (split != null && split.length >= 4) {
                        if (reporterConfigure.enableCatchANRException) {
                            this.a = new com.alibaba.sdk.android.man.crashreporter.handler.b.a(a, this, this.crashing, reporterConfigure.enabeANRTimeoutInterval, reporterConfigure.enableANRMainThreadOnly);
                        }
                        if (reporterConfigure.enableDebug) {
                            com.alibaba.sdk.android.man.crashreporter.handler.c.a.a("isDebug", c.appVersion);
                        }
                    }
                }
                this.a.b(this.a.a(0, 0, 0, 0));
                return true;
                com.alibaba.sdk.android.man.crashreporter.b.a.e("init handler failure!");
                return false;
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("init handler err", (Throwable)ex);
                return false;
            }
        }
    }
    
    public void b(final String s) {
        try {
            if (this.a != null && this.b != null && this.a != null) {
                com.alibaba.sdk.android.man.crashreporter.handler.c.b.a("TBCRASH_REPORTER_SDK", 2, com.alibaba.sdk.android.man.crashreporter.handler.a.w);
                com.alibaba.sdk.android.man.crashreporter.b.a.e("ANR handler start.");
                final CrashReportDataForSave a = this.a.a(s);
                if (a != null && !this.a(a.content)) {
                    this.a.a(a, this.a.a(0, 0, 0, 0), 2);
                }
                UTWrapper.commitCrashEvent();
            }
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("handle stuck failure", (Throwable)ex);
        }
    }
    
    public boolean c() {
        final com.alibaba.sdk.android.man.crashreporter.handler.a.a a = this.a;
        if (a != null) {
            a.b();
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Java crash handler is removed success.");
        }
        else {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Java crash handler is null.");
        }
        final NativeCrashHandler nativeCrashHandler = this.nativeCrashHandler;
        if (nativeCrashHandler != null) {
            if (!nativeCrashHandler.removeNativeCrashHandler()) {
                return false;
            }
        }
        else {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Native crash handler is null.");
        }
        final com.alibaba.sdk.android.man.crashreporter.handler.b.a a2 = this.a;
        if (a2 != null) {
            a2.c();
        }
        else {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Stuck crash handler is null.");
        }
        return true;
    }
}
