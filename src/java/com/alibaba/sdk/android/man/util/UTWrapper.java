package com.alibaba.sdk.android.man.util;

import com.alibaba.sdk.android.utils.AlicloudTrackerManager;
import android.app.Application;
import com.alibaba.sdk.android.utils.AMSDevReporter;
import android.content.Context;
import java.util.HashMap;
import com.alibaba.sdk.android.utils.AlicloudTracker;
import java.util.Map;
import com.alibaba.sdk.android.beacon.Beacon;

public class UTWrapper
{
    public static final String BIZ_CRASH_ACTIVE = "biz_crash_active";
    public static final String BIZ_CUSTOM_ACTIVE = "biz_custom_active";
    public static final String BIZ_PAGE_ACTIVE = "biz_page_active";
    public static final String BIZ_PERF_ACTIVE = "biz_perf_active";
    public static final String BIZ_USER_ACTIVE = "biz_user_active";
    public static final String BZ_ACTIVE = "biz_active";
    private static final Beacon.OnServiceErrListener ERR_LISTENER;
    private static final String MODULE = "man";
    public static final String PAGE_ASSIST_TYPE = "1";
    public static final String PAGE_BASIC_TYPE = "2";
    public static final String PERF_ADVANCE_TYPE = "2";
    public static final String PERF_CUSTOM_TYPE = "3";
    public static final String PERF_STANDARD_TYPE = "1";
    private static final String TAG = "UTWrapper";
    private static final Beacon.OnUpdateListener UPDATE_LISTENER;
    public static final String USER_LOGIN_TYPE = "2";
    public static final String USER_REGIST_TYPE = "1";
    private static Beacon beacon;
    private static boolean enable = true;
    private static Map<String, Boolean> isAlreadySendUtMap;
    private static AlicloudTracker tracker;
    
    static {
        UTWrapper.isAlreadySendUtMap = (Map<String, Boolean>)new HashMap();
        UPDATE_LISTENER = (Beacon.OnUpdateListener)new UTWrapper$1();
        ERR_LISTENER = (Beacon.OnServiceErrListener)new UTWrapper$2();
    }
    
    public static void commitCrashEvent() {
        if (UTWrapper.enable) {
            if (!isAlreadySendUI("biz_crash_active")) {
                commitEvent("biz_crash_active");
            }
        }
    }
    
    public static void commitCustomEvent() {
        if (UTWrapper.enable) {
            if (!isAlreadySendUI("biz_custom_active")) {
                commitEvent("biz_custom_active");
            }
        }
    }
    
    public static void commitDAUEvent(final Context context) {
        if (UTWrapper.enable && !isAlreadySendUI("biz_active")) {
            commitEvent("biz_active");
            final HashMap hashMap = new HashMap();
            ((Map)hashMap).put((Object)AMSDevReporter.AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_VERSION.toString(), (Object)"1.2.4");
            AMSDevReporter.asyncReport(context.getApplicationContext(), AMSDevReporter.AMSSdkTypeEnum.AMS_MAN, (Map<String, Object>)hashMap);
        }
    }
    
    private static void commitEvent(final String s) {
        commitEvent(s, 0L, null);
    }
    
    private static void commitEvent(final String s, final long n, final Map<String, String> map) {
        if (!UTWrapper.enable) {
            return;
        }
        try {
            UTWrapper.tracker.sendCustomHit(s, n, map);
        }
        finally {
            final StringBuilder sb = new StringBuilder();
            sb.append("commitEvent ");
            sb.append(s);
            sb.append(" Exception ");
            final Throwable t;
            sb.append(t.getMessage());
            MANLog.Loge("UTWrapper", sb.toString());
            t.printStackTrace();
        }
    }
    
    public static void commitPageEvent(final String s) {
        if (UTWrapper.enable) {
            final StringBuilder sb = new StringBuilder();
            sb.append("biz_page_active");
            sb.append(s);
            if (!isAlreadySendUI(sb.toString())) {
                final HashMap hashMap = new HashMap();
                ((Map)hashMap).put((Object)"type", (Object)s);
                commitEvent("biz_page_active", 0L, (Map<String, String>)hashMap);
            }
        }
    }
    
    public static void commitPerfEvent(final String s) {
        if (UTWrapper.enable) {
            final StringBuilder sb = new StringBuilder();
            sb.append("biz_perf_active");
            sb.append(s);
            if (!isAlreadySendUI(sb.toString())) {
                final HashMap hashMap = new HashMap();
                ((Map)hashMap).put((Object)"type", (Object)s);
                commitEvent("biz_perf_active", 0L, (Map<String, String>)hashMap);
            }
        }
    }
    
    public static void commitUserEvent(final String s) {
        if (UTWrapper.enable) {
            final StringBuilder sb = new StringBuilder();
            sb.append("biz_user_active");
            sb.append(s);
            if (!isAlreadySendUI(sb.toString())) {
                final HashMap hashMap = new HashMap();
                ((Map)hashMap).put((Object)"type", (Object)s);
                commitEvent("biz_user_active", 0L, (Map<String, String>)hashMap);
            }
        }
    }
    
    private static boolean isAlreadySendUI(final String s) {
        if (UTWrapper.isAlreadySendUtMap == null) {
            UTWrapper.isAlreadySendUtMap = (Map<String, Boolean>)new HashMap();
        }
        if (UTWrapper.isAlreadySendUtMap.get((Object)s) == null) {
            UTWrapper.isAlreadySendUtMap.put((Object)s, (Object)new Boolean(true));
            return false;
        }
        return true;
    }
    
    public static boolean isApkDebugable(final Context context) {
        boolean b = false;
        try {
            if ((context.getApplicationInfo().flags & 0x2) != 0x0) {
                b = true;
            }
            return b;
        }
        finally {
            return b;
        }
    }
    
    public static void utInit(final String s, final String s2, final Application application) {
        try {
            if (isApkDebugable((Context)application)) {
                AMSDevReporter.setLogEnabled(true);
            }
            (UTWrapper.tracker = AlicloudTrackerManager.getInstance(application).getTracker("man", "1.2.4")).setGlobalProperty("appKey", s);
            final HashMap hashMap = new HashMap();
            ((Map)hashMap).put((Object)"sdkId", (Object)"man");
            ((Map)hashMap).put((Object)"sdkVer", (Object)"1.2.4");
            MANLog.Logd("UTWrapper", "call utInit");
            (UTWrapper.beacon = new Beacon.Builder().appKey(s).appSecret(s2).loopInterval(3600000L).extras((Map<String, String>)hashMap).build()).stop();
            UTWrapper.beacon.addUpdateListener(UTWrapper.UPDATE_LISTENER);
            UTWrapper.beacon.addServiceErrListener(UTWrapper.ERR_LISTENER);
            UTWrapper.beacon.start((Context)application);
        }
        finally {
            final StringBuilder sb = new StringBuilder();
            sb.append("utInit Exception ");
            final Throwable t;
            sb.append(t.getMessage());
            MANLog.Loge("UTWrapper", sb.toString());
            t.printStackTrace();
        }
    }
}
