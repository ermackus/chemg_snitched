package com.alibaba.sdk.android.man.crashreporter;

import com.alibaba.sdk.android.man.crashreporter.b.a;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import com.alibaba.sdk.android.man.crashreporter.handler.b;

public final class MotuCrashReporter
{
    private static MotuCrashReporter instance;
    private ReporterConfigure configure;
    private b crashReportManager;
    private int crashReporterState;
    private c environment;
    private AtomicBoolean isEnable;
    List myExtListenerList;
    List mySenderListenerList;
    private long startupTime;
    private String strExtraInfo;
    
    private MotuCrashReporter() {
        this.startupTime = System.currentTimeMillis();
        this.isEnable = new AtomicBoolean(true);
        this.crashReporterState = -1;
        this.crashReportManager = null;
        this.myExtListenerList = (List)new ArrayList();
        this.mySenderListenerList = (List)new ArrayList();
        this.strExtraInfo = null;
    }
    
    public static MotuCrashReporter getInstance() {
        if (MotuCrashReporter.instance == null) {
            initMotuCrashReporter();
        }
        return MotuCrashReporter.instance;
    }
    
    private static MotuCrashReporter initMotuCrashReporter() {
        synchronized (MotuCrashReporter.class) {
            if (MotuCrashReporter.instance == null) {
                MotuCrashReporter.instance = new MotuCrashReporter();
            }
            return MotuCrashReporter.instance;
        }
    }
    
    public boolean enable(final Context context, final String appKey, final String appVersion, final String channel, final String userNick, final ReporterConfigure configure) {
        final AtomicBoolean isEnable = this.isEnable;
        if (isEnable != null && isEnable.get()) {
            if (configure != null) {
                this.configure = configure;
            }
            else {
                this.configure = new ReporterConfigure();
            }
            try {
                final String packageName = context.getApplicationInfo().packageName;
                if (packageName != null && packageName.equals((Object)"com.taobao.taobao")) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("package name:");
                    sb.append(packageName);
                    a.e(sb.toString());
                    if (context == null) {
                        a.e("enable failure. because context equal to null!");
                        return false;
                    }
                }
                else {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("package name:");
                    sb2.append(packageName);
                    a.e(sb2.toString());
                    if (context == null || appKey == null || appVersion == null) {
                        a.e("enable failure. because context or appKey or appVersion equal to null!");
                        return false;
                    }
                }
                a.b("start enable. context:", context.toString());
                String s;
                if (appKey == null) {
                    s = "use taobao detault";
                }
                else {
                    s = appKey;
                }
                a.b("start enable. appKey: ", s);
                String s2;
                if (appVersion == null) {
                    s2 = "use taobao default";
                }
                else {
                    s2 = appVersion;
                }
                a.b("start enable. appVersion: ", s2);
                if (this.environment == null) {
                    this.environment = new c();
                }
                this.environment.appKey = appKey;
                this.environment.appVersion = appVersion;
                this.environment.channel = channel;
                this.environment.userNick = userNick;
                this.environment.startupTime = this.startupTime;
                final com.alibaba.sdk.android.man.crashreporter.handler.a crashReportManager = new com.alibaba.sdk.android.man.crashreporter.handler.a();
                this.crashReportManager = (b)crashReportManager;
                if (((b)crashReportManager).a(context, this.configure, this.environment)) {
                    a.e("enable succ!");
                }
                else {
                    a.e("enable failure!");
                }
                return true;
            }
            catch (final Exception ex) {
                a.d("enable err", (Throwable)ex);
            }
        }
        return false;
    }
    
    public ReporterConfigure getConfigure() {
        return this.configure;
    }
    
    public int getCrashReporterState() {
        return this.crashReporterState;
    }
    
    public List getMyListenerList() {
        return this.myExtListenerList;
    }
    
    public List getMySenderListenerList() {
        return this.mySenderListenerList;
    }
    
    public String getStrExtraInfo() {
        return this.strExtraInfo;
    }
    
    public void setAppVersion(final String appVersion) {
        if (this.environment == null) {
            this.environment = new c();
        }
        if (appVersion != null) {
            a.b("set appVersion succ!", appVersion);
            this.environment.appVersion = appVersion;
        }
    }
    
    public void setCrashCaughtListener(final IUTCrashCaughtListener iutCrashCaughtListener) {
        String string;
        if (iutCrashCaughtListener == null) {
            string = "ext listener is null";
        }
        else {
            string = iutCrashCaughtListener.toString();
        }
        a.b("setCrashCaughtListener", string);
        final List myExtListenerList = this.myExtListenerList;
        if (myExtListenerList != null) {
            myExtListenerList.add((Object)iutCrashCaughtListener);
        }
    }
    
    public void setCrashReporterState(final int crashReporterState) {
        this.crashReporterState = crashReporterState;
    }
    
    public void setExtraInfo(final String strExtraInfo) {
        this.strExtraInfo = strExtraInfo;
    }
    
    public void setSenderListener(final com.alibaba.sdk.android.man.crashreporter.a a) {
        String string;
        if (a == null) {
            string = "sender listener is null";
        }
        else {
            string = a.toString();
        }
        a.b("setSenderListener", string);
        final List mySenderListenerList = this.mySenderListenerList;
        if (mySenderListenerList != null) {
            mySenderListenerList.add((Object)a);
        }
    }
    
    public void setTTid(final String channel) {
        if (this.environment == null) {
            this.environment = new c();
        }
        if (channel != null) {
            a.b("set ttid succ!", channel);
            this.environment.channel = channel;
        }
    }
    
    public void setUserNick(final String userNick) {
        if (this.environment == null) {
            this.environment = new c();
        }
        if (userNick != null) {
            a.b("set user nick succ!", userNick);
            this.environment.userNick = userNick;
        }
    }
    
    public boolean turnoffCrashReporter() {
        if (this.isEnable.compareAndSet(true, false)) {
            final b crashReportManager = this.crashReportManager;
            if (crashReportManager != null) {
                return crashReportManager.c();
            }
        }
        return false;
    }
}
