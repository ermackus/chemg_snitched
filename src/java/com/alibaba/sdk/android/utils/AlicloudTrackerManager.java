package com.alibaba.sdk.android.utils;

import com.alibaba.sdk.android.utils.crashdefend.SDKMessageCallback;
import android.util.Log;
import android.text.TextUtils;
import android.content.Context;
import java.util.HashMap;
import android.app.Application;
import java.util.Map;
import com.alibaba.sdk.android.utils.crashdefend.b;

public class AlicloudTrackerManager
{
    private static AlicloudTrackerManager a;
    private c a;
    private b a;
    private Map<String, AlicloudTracker> c;
    
    private AlicloudTrackerManager(final Application application) {
        this.a = null;
        this.a = new c();
        final HashMap hashMap = new HashMap(4);
        ((Map)hashMap).put((Object)"kVersion", (Object)"1.1.4");
        ((Map)hashMap).put((Object)"packageName", (Object)application.getPackageName());
        this.a.a(application, (Map<String, String>)hashMap);
        this.c = (Map<String, AlicloudTracker>)new HashMap();
        this.a = b.a((Context)application, this.a);
    }
    
    public static AlicloudTrackerManager getInstance(final Application application) {
        final Class<AlicloudTrackerManager> clazz;
        monitorenter(clazz = AlicloudTrackerManager.class);
        if (application == null) {
            monitorexit(clazz);
            return null;
        }
        try {
            if (AlicloudTrackerManager.a == null) {
                AlicloudTrackerManager.a = new AlicloudTrackerManager(application);
            }
            return AlicloudTrackerManager.a;
        }
        finally {
            monitorexit(clazz);
        }
    }
    
    public AlicloudTracker getTracker(final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
            Log.e("AlicloudTrackerManager", "sdkId or sdkVersion is null");
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(s2);
        final String string = sb.toString();
        if (this.c.containsKey((Object)string)) {
            return (AlicloudTracker)this.c.get((Object)string);
        }
        final AlicloudTracker alicloudTracker = new AlicloudTracker(this.a, s, s2);
        this.c.put((Object)string, (Object)alicloudTracker);
        return alicloudTracker;
    }
    
    public boolean registerCrashDefend(final String a, final String b, final int a2, final int b2, final SDKMessageCallback sdkMessageCallback) {
        if (this.a != null) {
            final com.alibaba.sdk.android.utils.crashdefend.c c = new com.alibaba.sdk.android.utils.crashdefend.c();
            c.a = a;
            c.b = b;
            c.a = a2;
            c.b = b2;
            return this.a.a(c, sdkMessageCallback);
        }
        return false;
    }
    
    public void unregisterCrashDefend(final String s, final String s2) {
        this.a.d(s, s2);
    }
}
