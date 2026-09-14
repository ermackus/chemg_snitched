package com.alibaba.sdk.android.utils;

import com.ut.mini.UTHitBuilders$UTCustomHitBuilder;
import com.ut.mini.IUTApplication;
import com.ut.mini.UTAnalytics;
import android.app.Application;
import android.util.Log;
import java.util.Map;

public class c
{
    private boolean b;
    private Map<String, String> d;
    
    public c() {
        this.b = true;
    }
    
    private boolean b() {
        boolean b = false;
        try {
            Class.forName("com.ut.mini.UTAnalytics");
        }
        finally {
            final Throwable t;
            Log.e("Utils:DataTracker", "ut not exist", t);
            b = false;
        }
        return b;
    }
    
    public void a(final Application application, final Map<String, String> d) {
        this.d = d;
        if (!(this.b = this.b())) {
            Log.e("Utils:DataTracker", "init failed due to ut not exsits");
            return;
        }
        try {
            UTAnalytics.getInstance().setAppApplicationInstance4sdk(application, (IUTApplication)new c$1(this));
        }
        finally {
            final Throwable t;
            Log.e("Utils:DataTracker", "init data tracker failed.", t);
        }
    }
    
    public void sendCustomHit(final String s, final long durationOnEvent, final Map<String, String> properties) {
        if (!this.b) {
            Log.e("Utils:DataTracker", "send custom hit failed due to ut not exists");
            return;
        }
        try {
            final UTHitBuilders$UTCustomHitBuilder utHitBuilders$UTCustomHitBuilder = new UTHitBuilders$UTCustomHitBuilder(s);
            utHitBuilders$UTCustomHitBuilder.setDurationOnEvent(durationOnEvent);
            utHitBuilders$UTCustomHitBuilder.setProperties((Map)properties);
            utHitBuilders$UTCustomHitBuilder.setProperties((Map)this.d);
            UTAnalytics.getInstance().getTrackerByAppkey("24527540").send(utHitBuilders$UTCustomHitBuilder.build());
        }
        finally {
            final Throwable t;
            Log.e("Utils:DataTracker", "send custom hit failed", t);
        }
    }
}
