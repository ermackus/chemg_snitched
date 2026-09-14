package com.alibaba.sdk.android.man.crashreporter.a;

import java.io.FileNotFoundException;
import com.alibaba.sdk.android.man.crashreporter.e.e;
import java.io.FileInputStream;
import java.io.File;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import android.os.Looper;
import java.util.HashMap;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import java.util.Map;
import com.alibaba.sdk.android.man.crashreporter.d.c;
import android.content.Context;

public class a implements b
{
    private static int k = 0;
    private static int l = 0;
    private static int m = 10;
    private static int n = 10;
    private Context a;
    private com.alibaba.sdk.android.man.crashreporter.a.a.b a;
    private c a;
    private Map<String, String> a;
    private c b;
    private com.alibaba.sdk.android.man.crashreporter.c environment;
    private String m;
    private String n;
    private String o;
    private String p;
    
    public a() {
        this.a = null;
        this.a = null;
        this.m = null;
        this.a = null;
        this.n = null;
        this.o = null;
        this.p = "";
        this.environment = null;
        this.a = null;
        this.b = null;
    }
    
    private CrashReportDataForSave a(final int n, final ReporterConfigure reporterConfigure, final String s, final String path, String hashCode) {
        if (n == 2) {
            return null;
        }
        final BaseDataContent a = this.a.a();
        if (a != null) {
            try {
                this.a(reporterConfigure, a, 0);
                a.userNick = hashCode;
                a.appVersion = this.environment.appVersion;
                if (!reporterConfigure.enableDeduplication) {
                    a.path = null;
                    a.times = 0;
                    a.hashCode = null;
                    this.a.a(a);
                    return null;
                }
                hashCode = a.hashCode;
                final String path2 = a.path;
                final Integer times = a.times;
                if (hashCode != null && times != 0 && s.equals((Object)hashCode)) {
                    if (times == 1) {
                        a.hashCode = hashCode;
                        a.times = times + 1;
                        a.path = path;
                        this.a.a(a);
                    }
                    else if (times >= 2) {
                        a.hashCode = hashCode;
                        final Integer value = times + 1;
                        a.times = value;
                        a.path = path2;
                        this.a.a(a);
                        final CrashReportDataForSave a2 = this.b.a(path2, (int)value);
                        if (a2 == null) {
                            this.a.b(true);
                            return null;
                        }
                        return a2;
                    }
                }
                else {
                    a.hashCode = s;
                    a.times = 1;
                    a.path = path;
                    this.a.a(a);
                }
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("parse base data file error.", (Throwable)ex);
            }
        }
        else {
            final BaseDataContent baseDataContent = new BaseDataContent();
            baseDataContent.abortFlag = String.format("%s%s", new Object[] { "#APPLICATION_CRASHED:", System.currentTimeMillis() });
            baseDataContent.path = path;
            baseDataContent.times = 1;
            baseDataContent.appVersion = this.environment.appVersion;
            baseDataContent.userNick = hashCode;
            baseDataContent.hashCode = s;
            this.a.a(baseDataContent);
        }
        return null;
    }
    
    private String a() {
        return null;
    }
    
    private String a(final ReporterConfigure reporterConfigure, final com.alibaba.sdk.android.man.crashreporter.a.b.a a, final com.alibaba.sdk.android.man.crashreporter.a.b.b b) {
        String a2;
        if (reporterConfigure != null && reporterConfigure.enableDumpAllThread) {
            a2 = b.a(a.c());
        }
        else {
            a2 = "";
        }
        return a2;
    }
    
    private String a(final ReporterConfigure reporterConfigure, final CrashReportDataForSave crashReportDataForSave) {
        try {
            final HashMap hashMap = new HashMap();
            com.alibaba.sdk.android.man.crashreporter.a.d.a.a((Map)hashMap, this.a);
            if (this.a != null) {
                String b;
                if ((b = this.a.b()) == null) {
                    b = "no status info";
                }
                ((Map)hashMap).put((Object)"appStatus", (Object)b);
            }
            int intValue = 0;
            Label_0096: {
                if (reporterConfigure.enableDeduplication) {
                    final BaseDataContent a = this.a.a();
                    if (a != null) {
                        intValue = a.times;
                        break Label_0096;
                    }
                }
                intValue = 1;
            }
            crashReportDataForSave.times = intValue;
            if (crashReportDataForSave.times != null) {
                if (crashReportDataForSave.times > 1) {
                    ((Map)hashMap).put((Object)"ts", (Object)String.format("%s", new Object[] { crashReportDataForSave.times - 1 }));
                }
                else {
                    ((Map)hashMap).put((Object)"ts", (Object)"1");
                }
            }
            final String b2 = com.alibaba.sdk.android.man.crashreporter.a.c.a.b((Map)hashMap);
            String b3;
            if (b2 != null) {
                b3 = com.alibaba.sdk.android.man.crashreporter.e.b.b(b2.getBytes());
            }
            else {
                b3 = null;
            }
            if (b3 != null) {
                return b3;
            }
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getMetaData err!", (Throwable)ex);
        }
        return null;
    }
    
    private String a(final com.alibaba.sdk.android.man.crashreporter.a.b.a a, final com.alibaba.sdk.android.man.crashreporter.a.b.b b) {
        return b.a(com.alibaba.sdk.android.man.crashreporter.a.b.a.d());
    }
    
    private String a(final com.alibaba.sdk.android.man.crashreporter.a.c.a.a a, final ReporterConfigure reporterConfigure) {
        try {
            final byte[] a2 = new com.alibaba.sdk.android.man.crashreporter.a.c.b().a(a, this.a, (Map)this.a(reporterConfigure.enableMaxThreadNumber, reporterConfigure.enableMaxThreadStackTraceNumber, reporterConfigure.enableSysLogcatMaxCount, reporterConfigure.enableSysLogcatLinkMaxCount));
            if (a2 == null) {
                a.e("reporter build failure!");
            }
            return com.alibaba.sdk.android.man.crashreporter.e.b.b(a2);
        }
        catch (final Exception ex) {
            a.d("reporter build err!", (Throwable)ex);
            return null;
        }
    }
    
    private void a(final ReporterConfigure reporterConfigure, final int enableMaxThreadNumber, final int enableMaxThreadStackTraceNumber, final int enableSysLogcatMaxCount, final int enableSysLogcatLinkMaxCount) {
        if (reporterConfigure != null) {
            reporterConfigure.enableMaxThreadNumber = enableMaxThreadNumber;
            reporterConfigure.enableMaxThreadStackTraceNumber = enableMaxThreadStackTraceNumber;
            reporterConfigure.enableSysLogcatMaxCount = enableSysLogcatMaxCount;
            reporterConfigure.enableSysLogcatLinkMaxCount = enableSysLogcatLinkMaxCount;
        }
    }
    
    private void a(final com.alibaba.sdk.android.man.crashreporter.a.c.a.a a) {
        try {
            if (this.environment == null) {
                return;
            }
            if (this.environment.userNick == null || this.environment.userNick.length() <= 0) {
                a.e("user nick is null or length <= 0!");
                this.environment.userNick = this.a.h();
            }
            if (this.environment.appKey == null) {
                a.e("use taobao default appKey,because your appKey is null!");
                this.environment.appKey = this.environment.k;
            }
            if (this.environment.appVersion == null) {
                a.e("use taobao app base or default Version,because your appVersion is null!");
                final String d = com.alibaba.sdk.android.man.crashreporter.a.d.a.d(this.a);
                if (d != null) {
                    this.environment.appVersion = d;
                }
                else {
                    this.environment.appVersion = this.environment.l;
                }
            }
            if (a != null) {
                a.c.put((Object)"sdkname", (Object)"MOTU");
                a.c.put((Object)"sdkVersion", (Object)"2.0.0");
                a.c.put((Object)"platform", (Object)"ANDROID");
                a.c.put((Object)"launchedTime", (Object)this.environment.startupTime);
                a.c.put((Object)"channel", (Object)this.environment.channel);
                a.c.put((Object)"user", (Object)this.environment.userNick);
                a.c.put((Object)"appKey", (Object)this.environment.appKey);
                a.c.put((Object)"appVersion", (Object)this.environment.appVersion);
            }
        }
        catch (final Exception ex) {
            a.d("set base info failure", (Throwable)ex);
        }
    }
    
    private void a(final CrashReportDataForSave crashReportDataForSave) {
        try {
            final String i = this.b.i();
            final String a = this.b.a((long)crashReportDataForSave.triggeredTime);
            crashReportDataForSave.path = String.format("%s/%s%s", new Object[] { i, a, this.b.j() });
            crashReportDataForSave.fileName = a;
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("set crash report file path err", (Throwable)ex);
        }
    }
    
    private boolean a() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
    
    public CrashReportDataForSave a() {
        return this.b(null, null, null, null);
    }
    
    public CrashReportDataForSave a(String a) {
        try {
            final ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
            this.a(configure, a.k = 30, a.l = 5, a.m = 60, a.n = 20);
            com.alibaba.sdk.android.man.crashreporter.a.b.a a2;
            if (a != null) {
                a2 = com.alibaba.sdk.android.man.crashreporter.a.b.a.a(a, false);
            }
            else {
                a2 = com.alibaba.sdk.android.man.crashreporter.a.b.a.a();
            }
            final com.alibaba.sdk.android.man.crashreporter.a.b.b b = new com.alibaba.sdk.android.man.crashreporter.a.b.b();
            final String string = a2.toString();
            a = b.a(a2.c());
            final String a3 = b.a(com.alibaba.sdk.android.man.crashreporter.a.b.a.e());
            final com.alibaba.sdk.android.man.crashreporter.a.c.a.a a4 = new com.alibaba.sdk.android.man.crashreporter.a.c.a.a();
            final CrashReportDataForSave crashReportDataForSave = new CrashReportDataForSave();
            crashReportDataForSave.triggeredTime = System.currentTimeMillis();
            crashReportDataForSave.type = 2;
            this.a(crashReportDataForSave);
            this.a(a4);
            a4.c.put((Object)"triggeredTime", (Object)crashReportDataForSave.triggeredTime);
            a4.c.put((Object)"exception", (Object)string);
            a4.c.put((Object)"threads", (Object)a);
            a4.c.put((Object)"backtrace", (Object)a3);
            a4.c.put((Object)"isMainThread", (Object)true);
            a4.c.put((Object)"type", (Object)"ANDROID_ANR");
            crashReportDataForSave.content = this.a(a4, configure);
            crashReportDataForSave.metaDataBase64 = this.a(configure, crashReportDataForSave);
            crashReportDataForSave.utPage = this.a();
            a.e("build stuck data end!");
            this.b.b(crashReportDataForSave);
            return crashReportDataForSave;
        }
        catch (final Exception ex) {
            a.d("buildStuckReport err!", (Throwable)ex);
            return null;
        }
    }
    
    public CrashReportDataForSave a(String b, final String s, final String toUTCrashMsg, final Map map) {
        try {
            final ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
            com.alibaba.sdk.android.man.crashreporter.a.a.k = 0;
            this.a(configure, com.alibaba.sdk.android.man.crashreporter.a.a.l = 0, 0, com.alibaba.sdk.android.man.crashreporter.a.a.m = 400, com.alibaba.sdk.android.man.crashreporter.a.a.n = 200);
            final com.alibaba.sdk.android.man.crashreporter.a.b.a a = com.alibaba.sdk.android.man.crashreporter.a.b.a.a("", false);
            final com.alibaba.sdk.android.man.crashreporter.a.b.b b2 = new com.alibaba.sdk.android.man.crashreporter.a.b.b();
            final String a2 = this.a(configure, a, b2);
            final String a3 = this.a(a, b2);
            final com.alibaba.sdk.android.man.crashreporter.a.c.a.a a4 = new com.alibaba.sdk.android.man.crashreporter.a.c.a.a();
            final CrashReportDataForSave crashReportDataForSave = new CrashReportDataForSave();
            crashReportDataForSave.triggeredTime = System.currentTimeMillis();
            crashReportDataForSave.toUTCrashMsg = toUTCrashMsg;
            crashReportDataForSave.hashCode = String.format("%s", new Object[] { i.a(s) });
            crashReportDataForSave.type = 0;
            this.a(crashReportDataForSave);
            this.a(a4);
            final CrashReportDataForSave a5 = this.a(crashReportDataForSave.type, configure, crashReportDataForSave.hashCode, crashReportDataForSave.path, this.environment.userNick);
            if (a5 != null) {
                return a5;
            }
            a4.c.put((Object)"triggeredTime", (Object)crashReportDataForSave.triggeredTime);
            a4.c.put((Object)"exception", (Object)b);
            a4.c.put((Object)"backtrace", (Object)s);
            a4.c.put((Object)"threads", (Object)a2);
            a4.c.put((Object)"currentThread", (Object)a3);
            if (this.a()) {
                a4.c.put((Object)"isMainThread", (Object)true);
            }
            else {
                a4.c.put((Object)"isMainThread", (Object)false);
            }
            a4.c.put((Object)"type", (Object)"ANDROID");
            b = com.alibaba.sdk.android.man.crashreporter.a.c.a.b(map);
            a4.c.put((Object)"extData", (Object)b);
            crashReportDataForSave.content = this.a(a4, configure);
            crashReportDataForSave.metaDataBase64 = this.a(configure, crashReportDataForSave);
            crashReportDataForSave.utPage = this.a();
            com.alibaba.sdk.android.man.crashreporter.b.a.e("build java crash data end!");
            this.b.b(crashReportDataForSave);
            return crashReportDataForSave;
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("buildJavaCrashReport err!", (Throwable)ex);
            return null;
        }
    }
    
    public String a(final String s) {
        if (s != null) {
            final String s2 = null;
            try {
                final File file = new File(s);
                String s3 = s2;
                if (file.exists()) {
                    s3 = s2;
                    if (file.isFile()) {
                        final FileInputStream fileInputStream = new FileInputStream(file);
                        final byte[] array = new byte[fileInputStream.available()];
                        fileInputStream.read(array);
                        s3 = new String(array, "GB2312");
                        e.i(s);
                    }
                }
                if (s3 != null) {
                    return s3;
                }
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("get native stack trace err!", (Throwable)ex);
            }
            catch (final FileNotFoundException ex2) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("native file not found err!", (Throwable)ex2);
            }
        }
        return "";
    }
    
    public Map<String, String> a() {
        final com.alibaba.sdk.android.man.crashreporter.a.a.b a = this.a;
        if (a != null) {
            return (Map<String, String>)a.a();
        }
        return null;
    }
    
    public Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> a(final int k, final int l, final int m, final int n) {
        monitorenter(this);
        try {
            try {
                this.a(MotuCrashReporter.getInstance().getConfigure(), com.alibaba.sdk.android.man.crashreporter.a.a.k = k, com.alibaba.sdk.android.man.crashreporter.a.a.l = l, com.alibaba.sdk.android.man.crashreporter.a.a.m = m, com.alibaba.sdk.android.man.crashreporter.a.a.n = n);
                if (this.a == null) {
                    this.a = (com.alibaba.sdk.android.man.crashreporter.a.a.b)new com.alibaba.sdk.android.man.crashreporter.a.a.a();
                }
                if (this.a != null && this.environment != null && this.a != null) {
                    final Map b = this.a.b();
                    this.a((com.alibaba.sdk.android.man.crashreporter.a.c.a.a)null);
                    b.put((Object)com.alibaba.sdk.android.man.crashreporter.global.a.I, (Object)com.alibaba.sdk.android.man.crashreporter.e.a.f(this.a));
                    b.put((Object)com.alibaba.sdk.android.man.crashreporter.global.a.H, (Object)com.alibaba.sdk.android.man.crashreporter.e.a.e(this.a));
                    b.put((Object)com.alibaba.sdk.android.man.crashreporter.global.a.C, (Object)this.environment.appKey);
                    b.put((Object)com.alibaba.sdk.android.man.crashreporter.global.a.D, (Object)this.environment.appVersion);
                    b.put((Object)com.alibaba.sdk.android.man.crashreporter.global.a.E, (Object)this.environment.channel);
                    b.put((Object)com.alibaba.sdk.android.man.crashreporter.global.a.F, (Object)this.environment.userNick);
                    b.put((Object)com.alibaba.sdk.android.man.crashreporter.global.a.v, (Object)this.a.b());
                    monitorexit(this);
                    return (Map<com.alibaba.sdk.android.man.crashreporter.global.a, String>)b;
                }
                monitorexit(this);
                return null;
            }
            finally {}
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("get internal Data failed", (Throwable)ex);
            monitorexit(this);
            return null;
        }
        monitorexit(this);
    }
    
    public void a(final ReporterConfigure reporterConfigure, final BaseDataContent baseDataContent, final int n) {
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            if (baseDataContent != null && reporterConfigure.enableAbortCount) {
                if (n == 0) {
                    final String abortFlag = baseDataContent.abortFlag;
                    if (abortFlag != null && !abortFlag.contains((CharSequence)"#APPLICATION_CRASHED:")) {
                        baseDataContent.abortFlag = String.format("%s%s%s", new Object[] { abortFlag, "#APPLICATION_CRASHED:", currentTimeMillis });
                    }
                    else if (abortFlag == null) {
                        baseDataContent.abortFlag = String.format("%s%s", new Object[] { "#APPLICATION_CRASHED:", currentTimeMillis });
                    }
                }
                else if (n == 1) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("abort content APPLICATION_STARTED");
                    if (baseDataContent.abortFlag != null && baseDataContent.abortFlag.contains((CharSequence)"#APPLICATION_STARTED:")) {
                        return;
                    }
                    baseDataContent.appVersion = this.environment.appVersion;
                    baseDataContent.abortFlag = String.format("%s%s", new Object[] { "#APPLICATION_STARTED:", currentTimeMillis });
                }
                else if (n == 2) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("remove abort content flag ");
                    if (baseDataContent.abortFlag == null) {
                        return;
                    }
                    baseDataContent.abortFlag = null;
                }
            }
            else if (reporterConfigure.enableAbortCount && n == 1) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("abort content APPLICATION_STARTED");
                final BaseDataContent baseDataContent2 = new BaseDataContent();
                baseDataContent2.appVersion = this.environment.appVersion;
                baseDataContent2.abortFlag = String.format("%s%s", new Object[] { "#APPLICATION_STARTED:", currentTimeMillis });
            }
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("build abort flag failure!", (Throwable)ex);
        }
    }
    
    public void a(final Map a, final String n, final String o, final String p4) {
        this.a = (Map<String, String>)a;
        this.n = n;
        this.o = o;
        this.p = p4;
    }
    
    public boolean a(final Context a, final ReporterConfigure reporterConfigure, final com.alibaba.sdk.android.man.crashreporter.c environment, final c b, final c a2) {
        Label_0065: {
            if (a == null) {
                break Label_0065;
            }
            try {
                if (this.a == null) {
                    this.a = (com.alibaba.sdk.android.man.crashreporter.a.a.b)new com.alibaba.sdk.android.man.crashreporter.a.a.a();
                }
                ((com.alibaba.sdk.android.man.crashreporter.a.a.b)(this.a = a)).a(reporterConfigure, a, a2, (b)this);
                this.environment = environment;
                this.a = a2;
                this.b = b;
                return true;
                a.h("init builder failure!");
                return false;
            }
            catch (final Exception ex) {
                a.d("init builder err!", (Throwable)ex);
                return false;
            }
        }
    }
    
    public boolean a(final ReporterConfigure reporterConfigure) {
        monitorenter(this);
        monitorexit(this);
        return false;
    }
    
    public CrashReportDataForSave b(String s, String n, final String s2, final Map map) {
        String o = null;
        Label_0020: {
            Label_0017: {
                if (s == null) {
                    Label_0537: {
                        try {
                            o = this.o;
                            break Label_0020;
                        }
                        catch (final Exception ex) {
                            break Label_0537;
                        }
                        break Label_0017;
                    }
                    final Exception ex;
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("buildNativeCrashReport err!", (Throwable)ex);
                    return null;
                }
            }
            o = s;
        }
        if (s2 == null) {
            s = this.p;
        }
        else {
            s = s2;
        }
        if (n == null) {
            n = this.n;
        }
        Map a;
        if (map == null) {
            a = this.a;
        }
        else {
            a = map;
        }
        final ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
        com.alibaba.sdk.android.man.crashreporter.a.a.k = 15;
        this.a(configure, com.alibaba.sdk.android.man.crashreporter.a.a.l = 15, 15, com.alibaba.sdk.android.man.crashreporter.a.a.m = 100, com.alibaba.sdk.android.man.crashreporter.a.a.n = 50);
        final String a2 = this.a(configure, com.alibaba.sdk.android.man.crashreporter.a.b.a.a("", false), new com.alibaba.sdk.android.man.crashreporter.a.b.b());
        final com.alibaba.sdk.android.man.crashreporter.a.c.a.a a3 = new com.alibaba.sdk.android.man.crashreporter.a.c.a.a();
        final CrashReportDataForSave crashReportDataForSave = new CrashReportDataForSave();
        crashReportDataForSave.triggeredTime = System.currentTimeMillis();
        String s3 = s;
        if (s.length() <= 0) {
            s3 = a2;
        }
        crashReportDataForSave.hashCode = String.format("%s", new Object[] { i.a(s3) });
        crashReportDataForSave.nativeCrashPath = o;
        crashReportDataForSave.type = 1;
        s = this.b.i();
        final String trim = o.trim();
        final String substring = trim.substring(trim.lastIndexOf("/") + 1);
        crashReportDataForSave.path = String.format("%s/%s", new Object[] { s, substring });
        crashReportDataForSave.fileName = substring;
        this.a(a3);
        final CrashReportDataForSave a4 = this.a(crashReportDataForSave.type, configure, crashReportDataForSave.hashCode, crashReportDataForSave.path, this.environment.userNick);
        if (a4 != null) {
            return a4;
        }
        a3.c.put((Object)"triggeredTime", (Object)crashReportDataForSave.triggeredTime);
        a3.c.put((Object)"exception", (Object)n);
        a3.c.put((Object)"threads", (Object)a2);
        a3.c.put((Object)"currentThread", (Object)s3);
        if (this.a()) {
            a3.c.put((Object)"isMainThread", (Object)true);
        }
        else {
            a3.c.put((Object)"isMainThread", (Object)false);
        }
        a3.c.put((Object)"type", (Object)"ANDROID_NATIVE");
        s = com.alibaba.sdk.android.man.crashreporter.a.c.a.b(a);
        a3.c.put((Object)"extData", (Object)s);
        crashReportDataForSave.content = this.a(a3, configure);
        crashReportDataForSave.metaDataBase64 = this.a(configure, crashReportDataForSave);
        crashReportDataForSave.utPage = this.a();
        com.alibaba.sdk.android.man.crashreporter.b.a.e("build native crash data end!");
        this.b.b(crashReportDataForSave);
        return crashReportDataForSave;
    }
}
