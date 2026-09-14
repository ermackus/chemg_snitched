package com.tencent.bugly;

import java.util.Map;
import com.tencent.bugly.crashreport.common.info.a;

public class BuglyStrategy
{
    protected int a;
    protected boolean b;
    private String c;
    private String d;
    private String e;
    private long f;
    private String g;
    private String h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private Class<?> m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private a r;
    
    public BuglyStrategy() {
        this.i = true;
        this.j = true;
        this.k = true;
        this.l = true;
        this.m = null;
        this.n = true;
        this.o = true;
        this.p = true;
        this.q = false;
        this.a = 31;
        this.b = false;
    }
    
    public String getAppChannel() {
        synchronized (this) {
            if (this.d == null) {
                return com.tencent.bugly.crashreport.common.info.a.b().m;
            }
            return this.d;
        }
    }
    
    public String getAppPackageName() {
        synchronized (this) {
            if (this.e == null) {
                return com.tencent.bugly.crashreport.common.info.a.b().c;
            }
            return this.e;
        }
    }
    
    public long getAppReportDelay() {
        synchronized (this) {
            return this.f;
        }
    }
    
    public String getAppVersion() {
        synchronized (this) {
            if (this.c == null) {
                return com.tencent.bugly.crashreport.common.info.a.b().k;
            }
            return this.c;
        }
    }
    
    public int getCallBackType() {
        synchronized (this) {
            return this.a;
        }
    }
    
    public boolean getCloseErrorCallback() {
        synchronized (this) {
            return this.b;
        }
    }
    
    public a getCrashHandleCallback() {
        synchronized (this) {
            return this.r;
        }
    }
    
    public String getDeviceID() {
        synchronized (this) {
            return this.h;
        }
    }
    
    public String getLibBuglySOFilePath() {
        synchronized (this) {
            return this.g;
        }
    }
    
    public Class<?> getUserInfoActivity() {
        synchronized (this) {
            return this.m;
        }
    }
    
    public boolean isBuglyLogUpload() {
        synchronized (this) {
            return this.n;
        }
    }
    
    public boolean isEnableANRCrashMonitor() {
        synchronized (this) {
            return this.j;
        }
    }
    
    public boolean isEnableCatchAnrTrace() {
        synchronized (this) {
            return this.k;
        }
    }
    
    public boolean isEnableNativeCrashMonitor() {
        synchronized (this) {
            return this.i;
        }
    }
    
    public boolean isEnableUserInfo() {
        synchronized (this) {
            return this.l;
        }
    }
    
    public boolean isReplaceOldChannel() {
        return this.o;
    }
    
    public boolean isUploadProcess() {
        synchronized (this) {
            return this.p;
        }
    }
    
    public boolean recordUserInfoOnceADay() {
        synchronized (this) {
            return this.q;
        }
    }
    
    public BuglyStrategy setAppChannel(final String d) {
        synchronized (this) {
            this.d = d;
            return this;
        }
    }
    
    public BuglyStrategy setAppPackageName(final String e) {
        synchronized (this) {
            this.e = e;
            return this;
        }
    }
    
    public BuglyStrategy setAppReportDelay(final long f) {
        synchronized (this) {
            this.f = f;
            return this;
        }
    }
    
    public BuglyStrategy setAppVersion(final String c) {
        synchronized (this) {
            this.c = c;
            return this;
        }
    }
    
    public BuglyStrategy setBuglyLogUpload(final boolean n) {
        synchronized (this) {
            this.n = n;
            return this;
        }
    }
    
    public void setCallBackType(final int a) {
        synchronized (this) {
            this.a = a;
        }
    }
    
    public void setCloseErrorCallback(final boolean b) {
        synchronized (this) {
            this.b = b;
        }
    }
    
    public BuglyStrategy setCrashHandleCallback(final a r) {
        synchronized (this) {
            this.r = r;
            return this;
        }
    }
    
    public BuglyStrategy setDeviceID(final String h) {
        synchronized (this) {
            this.h = h;
            return this;
        }
    }
    
    public BuglyStrategy setEnableANRCrashMonitor(final boolean j) {
        synchronized (this) {
            this.j = j;
            return this;
        }
    }
    
    public void setEnableCatchAnrTrace(final boolean k) {
        this.k = k;
    }
    
    public BuglyStrategy setEnableNativeCrashMonitor(final boolean i) {
        synchronized (this) {
            this.i = i;
            return this;
        }
    }
    
    public BuglyStrategy setEnableUserInfo(final boolean l) {
        synchronized (this) {
            this.l = l;
            return this;
        }
    }
    
    public BuglyStrategy setLibBuglySOFilePath(final String g) {
        synchronized (this) {
            this.g = g;
            return this;
        }
    }
    
    public BuglyStrategy setRecordUserInfoOnceADay(final boolean q) {
        synchronized (this) {
            this.q = q;
            return this;
        }
    }
    
    public void setReplaceOldChannel(final boolean o) {
        this.o = o;
    }
    
    public BuglyStrategy setUploadProcess(final boolean p) {
        synchronized (this) {
            this.p = p;
            return this;
        }
    }
    
    public BuglyStrategy setUserInfoActivity(final Class<?> m) {
        synchronized (this) {
            this.m = m;
            return this;
        }
    }
    
    public static class a
    {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_BLOCK = 7;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 30000;
        
        public Map<String, String> onCrashHandleStart(final int n, final String s, final String s2, final String s3) {
            monitorenter(this);
            monitorexit(this);
            return null;
        }
        
        public byte[] onCrashHandleStart2GetExtraDatas(final int n, final String s, final String s2, final String s3) {
            monitorenter(this);
            monitorexit(this);
            return null;
        }
    }
}
