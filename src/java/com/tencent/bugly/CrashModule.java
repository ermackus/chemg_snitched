package com.tencent.bugly;

import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.proguard.n;
import com.tencent.bugly.proguard.x;
import android.text.TextUtils;
import android.content.Context;

public class CrashModule extends a
{
    public static final int MODULE_ID = 1004;
    private static int c;
    private static CrashModule e;
    private long a;
    private BuglyStrategy$a b;
    private boolean d;
    
    static {
        CrashModule.e = new CrashModule();
    }
    
    public CrashModule() {
        this.d = false;
    }
    
    private void a(final Context context, final BuglyStrategy buglyStrategy) {
        monitorenter(this);
        if (buglyStrategy == null) {
            monitorexit(this);
            return;
        }
        try {
            final String libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
            if (!TextUtils.isEmpty((CharSequence)libBuglySOFilePath)) {
                com.tencent.bugly.crashreport.common.info.a.a(context).n = libBuglySOFilePath;
                x.a("setted libBugly.so file path :%s", new Object[] { libBuglySOFilePath });
            }
            if (buglyStrategy.getCrashHandleCallback() != null) {
                this.b = buglyStrategy.getCrashHandleCallback();
                x.a("setted CrashHanldeCallback", new Object[0]);
            }
            if (buglyStrategy.getAppReportDelay() > 0L) {
                final long appReportDelay = buglyStrategy.getAppReportDelay();
                this.a = appReportDelay;
                x.a("setted delay: %d", new Object[] { appReportDelay });
            }
        }
        finally {
            monitorexit(this);
        }
    }
    
    public static CrashModule getInstance() {
        CrashModule.e.id = 1004;
        return CrashModule.e;
    }
    
    public String[] getTables() {
        return new String[] { "t_cr" };
    }
    
    public boolean hasInitialized() {
        synchronized (this) {
            return this.d;
        }
    }
    
    public void init(final Context context, final boolean b, final BuglyStrategy buglyStrategy) {
        monitorenter(this);
        if (context != null) {
            try {
                if (!this.d) {
                    x.a("Initializing crash module.", new Object[0]);
                    n.a().a(1004, ++CrashModule.c);
                    this.d = true;
                    CrashReport.setContext(context);
                    this.a(context, buglyStrategy);
                    final c a = com.tencent.bugly.crashreport.crash.c.a(1004, context, b, this.b, (o)null, (String)null);
                    a.e();
                    if (buglyStrategy != null) {
                        a.a(buglyStrategy.getCallBackType());
                        a.a(buglyStrategy.getCloseErrorCallback());
                    }
                    if (buglyStrategy != null && buglyStrategy.isEnableCatchAnrTrace()) {
                        a.j();
                    }
                    a.n();
                    if (buglyStrategy != null && !buglyStrategy.isEnableNativeCrashMonitor()) {
                        x.a("[crash] Closed native crash monitor!", new Object[0]);
                        a.f();
                    }
                    else {
                        a.g();
                    }
                    if (buglyStrategy != null && !buglyStrategy.isEnableANRCrashMonitor()) {
                        x.a("[crash] Closed ANR monitor!", new Object[0]);
                        a.i();
                    }
                    else {
                        a.h();
                    }
                    long appReportDelay;
                    if (buglyStrategy != null) {
                        appReportDelay = buglyStrategy.getAppReportDelay();
                    }
                    else {
                        appReportDelay = 0L;
                    }
                    a.a(appReportDelay);
                    a.m();
                    com.tencent.bugly.crashreport.crash.d.a(context);
                    final BuglyBroadcastReceiver instance = BuglyBroadcastReceiver.getInstance();
                    instance.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
                    instance.register(context);
                    n.a().a(1004, --CrashModule.c);
                    return;
                }
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
    }
    
    public void onServerStrategyChanged(final StrategyBean strategyBean) {
        if (strategyBean == null) {
            return;
        }
        final c a = com.tencent.bugly.crashreport.crash.c.a();
        if (a != null) {
            a.a(strategyBean);
        }
    }
}
