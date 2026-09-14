package com.ut.mini.internal;

import java.util.Map;
import com.alibaba.mtl.appmonitor.AppMonitor;
import com.ut.mini.base.UTMIVariables;
import android.text.TextUtils;
import com.ut.device.UTDevice;
import com.alibaba.mtl.log.b;
import com.alibaba.mtl.log.c;
import com.alibaba.mtl.log.d.p;
import com.alibaba.mtl.log.a.a;
import android.util.Log;
import android.content.Context;

public class UTTeamWork
{
    private static UTTeamWork a;
    
    public static UTTeamWork getInstance() {
        synchronized (UTTeamWork.class) {
            if (UTTeamWork.a == null) {
                UTTeamWork.a = new UTTeamWork();
            }
            return UTTeamWork.a;
        }
    }
    
    public void clearHost4Https(final Context context) {
        if (context == null) {
            Log.w("UTTeamWork", "context is null");
            return;
        }
        com.alibaba.mtl.log.a.a.f("");
        p.a(context, "utanalytics_https_host", (String)null);
    }
    
    public void closeAuto1010Track() {
        c.a().o();
    }
    
    public void disableNetworkStatusChecker() {
    }
    
    public void dispatchLocalHits() {
    }
    
    public void enableUpload(final boolean s) {
        com.alibaba.mtl.log.a.s = s;
    }
    
    public String getUtsid() {
        try {
            String appkey;
            if (com.alibaba.mtl.log.a.a() != null) {
                appkey = com.alibaba.mtl.log.a.a().getAppkey();
            }
            else {
                appkey = null;
            }
            final String utdid = UTDevice.getUtdid(b.a().getContext());
            final long longValue = Long.valueOf(com.alibaba.mtl.log.a.B);
            if (!TextUtils.isEmpty((CharSequence)appkey) && !TextUtils.isEmpty((CharSequence)utdid)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(utdid);
                sb.append("_");
                sb.append(appkey);
                sb.append("_");
                sb.append(longValue);
                return sb.toString();
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void initialized() {
    }
    
    public void saveCacheDataToLocal() {
        com.alibaba.mtl.log.c.c.a().E();
    }
    
    public void setHost4Https(final Context context, final String s) {
        if (context == null) {
            Log.w("UTTeamWork", "context is null");
            return;
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            Log.w("UTTeamWork", "host or port is empty");
            return;
        }
        com.alibaba.mtl.log.a.a.f(s);
        p.a(context, "utanalytics_https_host", s);
    }
    
    public void setToAliyunOsPlatform() {
        UTMIVariables.getInstance().setToAliyunOSPlatform();
    }
    
    public void turnOffRealTimeDebug() {
        AppMonitor.turnOffRealTimeDebug();
    }
    
    public void turnOnRealTimeDebug(final Map<String, String> map) {
        AppMonitor.turnOnRealTimeDebug((Map)map);
    }
}
