package com.ut.mini;

import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import com.alibaba.mtl.log.c;
import com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication;
import com.ut.mini.core.sign.UTBaseRequestAuthentication;
import com.ut.mini.core.sign.IUTRequestAuthentication;
import com.ut.mini.internal.UTTeamWork;
import android.content.Context;
import com.alibaba.mtl.appmonitor.AppMonitor;
import com.alibaba.mtl.log.b;
import android.app.Application;
import android.text.TextUtils;
import com.alibaba.mtl.log.d.i;
import com.ut.mini.core.appstatus.UTMCAppStatusCallbacks;
import com.ut.mini.core.appstatus.UTMCAppStatusRegHelper;
import com.ut.mini.base.UTMIVariables;
import com.ut.mini.plugin.UTPlugin;
import com.ut.mini.plugin.UTPluginMgr;
import com.ut.mini.sdkevents.UTMI1010_2001Event;
import android.os.Build$VERSION;
import java.util.HashMap;
import java.util.Map;

public class UTAnalytics
{
    private static UTAnalytics a;
    private boolean M;
    private boolean N;
    private UTTracker a;
    private Map<String, UTTracker> w;
    private Map<String, UTTracker> x;
    
    private UTAnalytics() {
        this.w = (Map<String, UTTracker>)new HashMap();
        this.x = (Map<String, UTTracker>)new HashMap();
        if (Build$VERSION.SDK_INT < 14) {
            final UTMI1010_2001Event utmi1010_2001EventInstance = new UTMI1010_2001Event();
            UTPluginMgr.getInstance().registerPlugin((UTPlugin)utmi1010_2001EventInstance, false);
            UTMIVariables.getInstance().setUTMI1010_2001EventInstance(utmi1010_2001EventInstance);
        }
        else {
            final UTMI1010_2001Event utmi1010_2001EventInstance2 = new UTMI1010_2001Event();
            UTMCAppStatusRegHelper.registerAppStatusCallbacks((UTMCAppStatusCallbacks)utmi1010_2001EventInstance2);
            UTMIVariables.getInstance().setUTMI1010_2001EventInstance(utmi1010_2001EventInstance2);
        }
    }
    
    public static UTAnalytics getInstance() {
        synchronized (UTAnalytics.class) {
            if (UTAnalytics.a == null) {
                UTAnalytics.a = new UTAnalytics();
            }
            return UTAnalytics.a;
        }
    }
    
    public UTTracker getDefaultTracker() {
        synchronized (this) {
            if (this.a == null) {
                this.a = new UTTracker();
            }
            if (this.a == null) {
                i.a("getDefaultTracker error", (Object)"Fatal Error,must call setRequestAuthentication method first.");
            }
            return this.a;
        }
    }
    
    public UTTracker getTracker(final String s) {
        synchronized (this) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                i.a("getTracker", (Object)"TrackId is null.");
                return null;
            }
            if (this.w.containsKey((Object)s)) {
                return (UTTracker)this.w.get((Object)s);
            }
            final UTTracker utTracker = new UTTracker();
            utTracker.q(s);
            this.w.put((Object)s, (Object)utTracker);
            return utTracker;
        }
    }
    
    public UTTracker getTrackerByAppkey(final String s) {
        synchronized (this) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                i.a("getTracker", (Object)"TrackId is null.");
                return null;
            }
            if (this.x.containsKey((Object)s)) {
                return (UTTracker)this.x.get((Object)s);
            }
            final UTTracker utTracker = new UTTracker();
            utTracker.r(s);
            this.x.put((Object)s, (Object)utTracker);
            return utTracker;
        }
    }
    
    @Deprecated
    public void setAppApplicationInstance(final Application appApplicationInstance) {
        b.a().setAppApplicationInstance(appApplicationInstance);
        AppMonitor.init(appApplicationInstance);
    }
    
    public void setAppApplicationInstance(final Application appApplicationInstance, final IUTApplication iutApplication) {
        final Throwable t2;
        try {
            if (this.M) {
                return;
            }
            if (appApplicationInstance != null && iutApplication != null && appApplicationInstance.getApplicationContext() != null) {
                getInstance().setContext(appApplicationInstance.getApplicationContext());
                getInstance().setAppApplicationInstance(appApplicationInstance);
                if (iutApplication.isUTLogEnable()) {
                    getInstance().turnOnDebug();
                }
                getInstance().setChannel(iutApplication.getUTChannel());
                getInstance().setAppVersion(iutApplication.getUTAppVersion());
                getInstance().setRequestAuthentication(iutApplication.getUTRequestAuthInstance());
                this.N = true;
                this.M = true;
                return;
            }
            throw new IllegalArgumentException("application and callback must not be null");
        }
        finally {
            final String s = null;
            final Throwable t = t2;
            i.a(s, (Object)t);
        }
        try {
            final String s = null;
            final Throwable t = t2;
            i.a(s, (Object)t);
        }
        finally {}
    }
    
    public void setAppApplicationInstance4sdk(final Application appApplicationInstance, final IUTApplication iutApplication) {
        final Throwable t2;
        try {
            if (this.N) {
                return;
            }
            if (appApplicationInstance != null && iutApplication != null && appApplicationInstance.getApplicationContext() != null) {
                getInstance().setContext(appApplicationInstance.getApplicationContext());
                getInstance().setAppApplicationInstance(appApplicationInstance);
                if (iutApplication.isUTLogEnable()) {
                    getInstance().turnOnDebug();
                }
                getInstance().setChannel(iutApplication.getUTChannel());
                getInstance().setAppVersion(iutApplication.getUTAppVersion());
                getInstance().setRequestAuthentication(iutApplication.getUTRequestAuthInstance());
                this.N = true;
                return;
            }
            throw new IllegalArgumentException("application and callback must not be null");
        }
        finally {
            final String s = null;
            final Throwable t = t2;
            i.a(s, (Object)t);
        }
        try {
            final String s = null;
            final Throwable t = t2;
            i.a(s, (Object)t);
        }
        finally {}
    }
    
    @Deprecated
    public void setAppVersion(final String appVersion) {
        b.a().setAppVersion(appVersion);
    }
    
    @Deprecated
    public void setChannel(final String channel) {
        AppMonitor.setChannel(channel);
    }
    
    @Deprecated
    public void setContext(final Context context) {
        b.a().setContext(context);
        if (context != null) {
            UTTeamWork.getInstance().initialized();
        }
    }
    
    @Deprecated
    public void setRequestAuthentication(final IUTRequestAuthentication iutRequestAuthentication) {
        if (iutRequestAuthentication == null) {
            i.a("setRequestAuthentication", (Object)"Fatal Error,pRequestAuth must not be null.");
        }
        if (iutRequestAuthentication instanceof UTBaseRequestAuthentication) {
            final String appkey = iutRequestAuthentication.getAppkey();
            final UTBaseRequestAuthentication utBaseRequestAuthentication = (UTBaseRequestAuthentication)iutRequestAuthentication;
            final String appSecret = utBaseRequestAuthentication.getAppSecret();
            String s;
            if (utBaseRequestAuthentication.isEncode()) {
                s = "1";
            }
            else {
                s = "0";
            }
            AppMonitor.setRequestAuthInfo(false, appkey, appSecret, s);
        }
        else {
            AppMonitor.setRequestAuthInfo(true, iutRequestAuthentication.getAppkey(), (String)null, ((UTSecuritySDKRequestAuthentication)iutRequestAuthentication).getAuthCode());
        }
    }
    
    public void turnOffAutoPageTrack() {
        UTPageHitHelper.getInstance().turnOffAutoPageTrack();
    }
    
    @Deprecated
    public void turnOnDebug() {
        b.a().turnOnDebug();
    }
    
    public void updateSessionProperties(final Map<String, String> map) {
        final Map a = c.a().a();
        final HashMap hashMap = new HashMap();
        if (a != null) {
            ((Map)hashMap).putAll(a);
        }
        ((Map)hashMap).putAll((Map)map);
        c.a().c((Map)hashMap);
    }
    
    public void updateUserAccount(final String s, final String s2) {
        b.a().updateUserAccount(s, s2);
    }
    
    public void userRegister(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final UTTracker defaultTracker = this.getDefaultTracker();
            if (defaultTracker != null) {
                defaultTracker.send((Map<String, String>)new UTOriginalCustomHitBuilder("UT", 1006, s, (String)null, (String)null, (Map)null).build());
            }
            else {
                i.a("Record userRegister event error", (Object)"Fatal Error,must call setRequestAuthentication method first.");
            }
        }
        else {
            i.a("userRegister", (Object)"Fatal Error,usernick can not be null or empty!");
        }
    }
}
