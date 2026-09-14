package com.tencent.bugly;

import java.util.Map;
import com.tencent.bugly.proguard.o;
import android.text.TextUtils;
import com.tencent.bugly.proguard.n;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import android.util.Log;
import com.tencent.bugly.proguard.x;
import android.content.Context;
import java.util.ArrayList;
import com.tencent.bugly.proguard.p;
import java.util.List;

public final class b
{
    public static boolean a = true;
    public static List<a> b;
    public static boolean c;
    private static p d;
    private static boolean e;
    
    static {
        com.tencent.bugly.b.b = (List<a>)new ArrayList();
    }
    
    public static void a(final Context context) {
        synchronized (b.class) {
            a(context, null);
        }
    }
    
    public static void a(final Context context, final BuglyStrategy buglyStrategy) {
        synchronized (b.class) {
            if (com.tencent.bugly.b.e) {
                x.d("[init] initial Multi-times, ignore this.", new Object[0]);
                return;
            }
            if (context == null) {
                Log.w(x.a, "[init] context of init() is null, check it.");
                return;
            }
            final com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a(context);
            if (a(a)) {
                com.tencent.bugly.b.a = false;
                return;
            }
            final String f = a.f();
            if (f == null) {
                Log.e(x.a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
                return;
            }
            a(context, f, a.v, buglyStrategy);
        }
    }
    
    public static void a(final Context context, String s, final boolean b, final BuglyStrategy buglyStrategy) {
        synchronized (b.class) {
            if (b.e) {
                x.d("[init] initial Multi-times, ignore this.", new Object[0]);
                return;
            }
            if (context == null) {
                Log.w(x.a, "[init] context is null, check it.");
                return;
            }
            if (s == null) {
                Log.e(x.a, "init arg 'crashReportAppID' should not be null!");
                return;
            }
            b.e = true;
            if (b) {
                b.c = true;
                x.b = true;
                x.d("Bugly debug\u6a21\u5f0f\u5f00\u542f\uff0c\u8bf7\u5728\u53d1\u5e03\u65f6\u628aisDebug\u5173\u95ed\u3002 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                x.e("--------------------------------------------------------------------------------------------", new Object[0]);
                x.d("Bugly debug\u6a21\u5f0f\u5c06\u6709\u4ee5\u4e0b\u884c\u4e3a\u7279\u6027 -- The following list shows the behaviour of debug model: ", new Object[0]);
                x.d("[1] \u8f93\u51fa\u8be6\u7ec6\u7684Bugly SDK\u7684Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                x.d("[2] \u6bcf\u4e00\u6761Crash\u90fd\u4f1a\u88ab\u7acb\u5373\u4e0a\u62a5 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                x.d("[3] \u81ea\u5b9a\u4e49\u65e5\u5fd7\u5c06\u4f1a\u5728Logcat\u4e2d\u8f93\u51fa -- Custom log will be output to logcat.", new Object[0]);
                x.e("--------------------------------------------------------------------------------------------", new Object[0]);
                x.b("[init] Open debug mode of Bugly.", new Object[0]);
            }
            x.a(" crash report start initializing...", new Object[0]);
            x.b("[init] Bugly start initializing...", new Object[0]);
            x.a("[init] Bugly complete version: v%s", "3.3.3");
            final Context a = z.a(context);
            final com.tencent.bugly.crashreport.common.info.a a2 = com.tencent.bugly.crashreport.common.info.a.a(a);
            a2.o();
            y.a(a);
            b.d = p.a(a, b.b);
            u.a(a);
            final com.tencent.bugly.crashreport.common.strategy.a a3 = com.tencent.bugly.crashreport.common.strategy.a.a(a, b.b);
            final n a4 = n.a(a);
            if (a(a2)) {
                b.a = false;
                return;
            }
            a2.a(s);
            x.a("[param] Set APP ID:%s", s);
            if (buglyStrategy != null) {
                s = buglyStrategy.getAppVersion();
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    String substring = s;
                    if (s.length() > 100) {
                        substring = s.substring(0, 100);
                        x.d("appVersion %s length is over limit %d substring to %s", s, 100, substring);
                    }
                    a2.k = substring;
                    x.a("[param] Set App version: %s", buglyStrategy.getAppVersion());
                }
                try {
                    if (buglyStrategy.isReplaceOldChannel()) {
                        s = buglyStrategy.getAppChannel();
                        if (!TextUtils.isEmpty((CharSequence)s)) {
                            String substring2 = s;
                            if (s.length() > 100) {
                                substring2 = s.substring(0, 100);
                                x.d("appChannel %s length is over limit %d substring to %s", s, 100, substring2);
                            }
                            b.d.a(556, "app_channel", substring2.getBytes(), null, false);
                            a2.m = substring2;
                        }
                    }
                    else {
                        final Map<String, byte[]> a5 = b.d.a(556, null, true);
                        if (a5 != null) {
                            final byte[] array = (byte[])a5.get((Object)"app_channel");
                            if (array != null) {
                                a2.m = new String(array);
                            }
                        }
                    }
                    x.a("[param] Set App channel: %s", a2.m);
                }
                catch (final Exception ex) {
                    if (b.c) {
                        ex.printStackTrace();
                    }
                }
                s = buglyStrategy.getAppPackageName();
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    String substring3 = s;
                    if (s.length() > 100) {
                        substring3 = s.substring(0, 100);
                        x.d("appPackageName %s length is over limit %d substring to %s", s, 100, substring3);
                    }
                    a2.c = substring3;
                    x.a("[param] Set App package: %s", buglyStrategy.getAppPackageName());
                }
                s = buglyStrategy.getDeviceID();
                if (s != null) {
                    String substring4 = s;
                    if (s.length() > 100) {
                        substring4 = s.substring(0, 100);
                        x.d("deviceId %s length is over limit %d substring to %s", s, 100, substring4);
                    }
                    a2.c(substring4);
                    x.a("[param] Set device ID: %s", substring4);
                }
                a2.e = buglyStrategy.isUploadProcess();
                y.a = buglyStrategy.isBuglyLogUpload();
            }
            for (int i = 0; i < b.b.size(); ++i) {
                try {
                    if (a4.a(((a)b.b.get(i)).id)) {
                        ((a)b.b.get(i)).init(a, b, buglyStrategy);
                    }
                }
                finally {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                }
            }
            b.a(a, buglyStrategy);
            long appReportDelay;
            if (buglyStrategy != null) {
                appReportDelay = buglyStrategy.getAppReportDelay();
            }
            else {
                appReportDelay = 0L;
            }
            a3.a(appReportDelay);
            x.b("[init] Bugly initialization finished.", new Object[0]);
        }
    }
    
    public static void a(final a a) {
        synchronized (b.class) {
            if (!com.tencent.bugly.b.b.contains((Object)a)) {
                com.tencent.bugly.b.b.add((Object)a);
            }
        }
    }
    
    private static boolean a(final com.tencent.bugly.crashreport.common.info.a a) {
        final List<String> p = a.p;
        a.getClass();
        return p != null && p.contains((Object)"bugly");
    }
}
