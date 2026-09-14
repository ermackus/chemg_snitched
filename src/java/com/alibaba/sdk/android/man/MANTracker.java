package com.alibaba.sdk.android.man;

import java.util.Map;
import com.alibaba.sdk.android.man.network.NetworkEvent;
import com.alibaba.sdk.android.man.util.UTWrapper;
import com.alibaba.sdk.android.man.util.EventCommitTool;
import com.alibaba.sdk.android.man.util.MANLog;
import com.alibaba.sdk.android.man.customperf.MANCustomPerformance;
import android.util.Log;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTTracker;

public class MANTracker
{
    private static final String TAG;
    public String appKey;
    private volatile boolean isEnabled;
    
    static {
        TAG = MANTracker.class.getSimpleName();
    }
    
    private MANTracker() {
        this.isEnabled = true;
    }
    
    public static MANTracker getInstance() {
        return Singleton.instance;
    }
    
    private UTTracker getTrackerFromUt() {
        final UTTracker trackerByAppkey = UTAnalytics.getInstance().getTrackerByAppkey(this.appKey);
        if (trackerByAppkey == null) {
            Log.e("MAN", "\u8bf7\u5148\u521d\u59cb\u5316MAN");
        }
        return trackerByAppkey;
    }
    
    public void send(final MANCustomPerformance manCustomPerformance) {
        if (!this.isEnabled) {
            MANLog.Loge(MANTracker.TAG, "MAN init failed,can not work for now!");
            return;
        }
        EventCommitTool.commitCustomPerformanceEvent(manCustomPerformance);
        UTWrapper.commitPerfEvent("3");
    }
    
    public void send(final NetworkEvent networkEvent) {
        if (!this.isEnabled) {
            MANLog.Loge(MANTracker.TAG, "MAN init failed,can not work for now!");
            return;
        }
        if (networkEvent != null) {
            networkEvent.reportNetworkInfo();
            String s;
            if (networkEvent.isAdvancedStat()) {
                s = "2";
            }
            else {
                s = "1";
            }
            UTWrapper.commitPerfEvent(s);
        }
    }
    
    public void send(final Map<String, String> map) {
        if (!this.isEnabled) {
            MANLog.Loge(MANTracker.TAG, "MAN init failed,can not work for now!");
            return;
        }
        final UTTracker trackerFromUt = this.getTrackerFromUt();
        if (trackerFromUt == null) {
            return;
        }
        trackerFromUt.send((Map)map);
        UTWrapper.commitCustomEvent();
    }
    
    public void setEnableStatus(final boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    
    private static class Singleton
    {
        static MANTracker instance;
        
        static {
            Singleton.instance = new MANTracker(null);
        }
    }
}
