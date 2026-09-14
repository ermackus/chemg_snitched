package com.goodsrc.ui.library;

import java.util.Map;
import android.app.Activity;
import android.content.Context;
import com.goodsrc.library.utils.AppUtil;
import com.alibaba.sdk.android.man.util.MANLog;
import android.app.Application;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.man.MANHitBuilders;

public class MANServiceConfig
{
    private static final String AppSecret = "cd783007e784427f8f11f1570f61d64e";
    private static String appKey = "333609086";
    
    public static void addFileUpEvent() {
        final MANHitBuilders.MANCustomHitBuilder manCustomHitBuilder = new MANHitBuilders.MANCustomHitBuilder("FileUpload");
        manCustomHitBuilder.setDurationOnEvent(0L);
        final MANService service = MANServiceProvider.getService();
        if (service != null && service.getMANAnalytics() != null) {
            service.getMANAnalytics().getDefaultTracker().send(manCustomHitBuilder.build());
        }
    }
    
    public static void deleteUser() {
    }
    
    public static void init(final Application application, final boolean b) {
        MANLog.enableLog();
        final MANService service = MANServiceProvider.getService();
        if (b) {
            service.getMANAnalytics().turnOnDebug();
        }
        service.getMANAnalytics().turnOffCrashReporter();
        service.getMANAnalytics().setChannel("KingDraw_Android");
        if (b) {
            service.getMANAnalytics().init(application, application.getApplicationContext(), "333610581", "c616afea2309441f8057e7ca0129204d");
        }
        else {
            service.getMANAnalytics().init(application, application.getApplicationContext(), MANServiceConfig.appKey, "cd783007e784427f8f11f1570f61d64e");
        }
        service.getMANAnalytics().turnOffAutoPageTrack();
        if (b) {
            service.getMANAnalytics().setAppVersion("0.9.0");
        }
        else {
            service.getMANAnalytics().setAppVersion(AppUtil.getVersionName((Context)application));
        }
    }
    
    public static void pageAppear(final Activity activity) {
        final MANService service = MANServiceProvider.getService();
        if (service != null && service.getMANPageHitHelper() != null) {
            service.getMANPageHitHelper().pageAppear(activity);
        }
    }
    
    public static void pageDisAppear(final Activity activity) {
        final MANService service = MANServiceProvider.getService();
        if (service != null && service.getMANPageHitHelper() != null) {
            service.getMANPageHitHelper().pageDisAppear(activity);
        }
    }
    
    public static void updatePageProperties(final Map<String, String> map) {
        final MANService service = MANServiceProvider.getService();
        if (service != null && service.getMANPageHitHelper() != null) {
            service.getMANPageHitHelper().updatePageProperties((Map)map);
        }
    }
    
    public static void userLogin(final String s) {
        final MANService service = MANServiceProvider.getService();
        if (service != null && service.getMANAnalytics() != null) {
            service.getMANAnalytics().updateUserAccount(s, s);
        }
    }
    
    public static void userRegister(final String s) {
        final MANService service = MANServiceProvider.getService();
        if (service != null && service.getMANAnalytics() != null) {
            service.getMANAnalytics().userRegister(s);
        }
    }
}
