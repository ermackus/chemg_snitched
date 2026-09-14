package com.ut.mini.sdkevents;

import com.alibaba.mtl.log.model.LogField;
import com.ut.mini.UTInterfaceCallDelegate;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import java.util.List;
import android.content.Context;
import android.app.ActivityManager$RunningTaskInfo;
import android.app.ActivityManager;
import com.alibaba.mtl.log.b;
import com.ut.mini.UTTracker;
import com.alibaba.mtl.log.d.i;
import com.ut.mini.UTAnalytics;
import java.util.Map;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import android.os.SystemClock;
import com.alibaba.mtl.log.c;
import com.ut.mini.core.appstatus.UTMCAppStatusCallbacks;
import com.ut.mini.plugin.UTPlugin;

public class UTMI1010_2001Event extends UTPlugin implements UTMCAppStatusCallbacks
{
    private long C;
    private long D;
    private long E;
    
    public UTMI1010_2001Event() {
        this.C = 0L;
        this.D = 0L;
        this.E = 0L;
    }
    
    private void a(final long n) {
        if (!c.a().d()) {
            long n2 = 0L;
            if (n > 0L) {
                if (0L != this.E) {
                    n2 = SystemClock.elapsedRealtime() - this.E;
                }
                final StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(n);
                final String string = sb.toString();
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(n2);
                final UTOriginalCustomHitBuilder utOriginalCustomHitBuilder = new UTOriginalCustomHitBuilder("UT", 1010, string, sb2.toString(), null, null);
                final UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
                if (defaultTracker != null) {
                    defaultTracker.send(utOriginalCustomHitBuilder.build());
                }
                else {
                    i.a("Record app display event error", (Object)"Fatal Error,must call setRequestAuthentication method first.");
                }
            }
        }
    }
    
    private static boolean m() {
        try {
            final Context context = b.a().getContext();
            if (context != null) {
                final String packageName = context.getPackageName();
                if (packageName != null) {
                    final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
                    if (activityManager != null) {
                        try {
                            final List runningTasks = activityManager.getRunningTasks(1);
                            if (runningTasks != null && runningTasks.size() > 0) {
                                final ComponentName topActivity = ((ActivityManager$RunningTaskInfo)runningTasks.get(0)).topActivity;
                                if (topActivity != null && packageName.contains((CharSequence)topActivity.getPackageName())) {
                                    return false;
                                }
                            }
                        }
                        catch (final Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            return true;
        }
        catch (final Exception ex2) {
            return false;
        }
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityDestroyed(final Activity activity) {
    }
    
    public void onActivityPaused(final Activity activity) {
        UTInterfaceCallDelegate.pageDisAppearByAuto(activity);
    }
    
    public void onActivityResumed(final Activity activity) {
        UTInterfaceCallDelegate.pageAppearByAuto(activity);
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityStarted(final Activity activity) {
    }
    
    public void onActivityStopped(final Activity activity) {
    }
    
    public void onPluginMsgArrivedFromSDK(final int n, final Object o) {
        if (n == 3) {
            final Map map = (Map)o;
            if (map.containsKey((Object)LogField.EVENTID.toString()) && "2001".equals((Object)map.get((Object)LogField.EVENTID.toString()))) {
                long long1 = 0L;
                Label_0094: {
                    if (map.containsKey((Object)LogField.ARG3.toString())) {
                        final String s = (String)map.get((Object)LogField.ARG3.toString());
                        try {
                            long1 = Long.parseLong(s);
                            break Label_0094;
                        }
                        catch (final Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    long1 = 0L;
                }
                this.C += long1;
                if (m()) {
                    this.a(this.C);
                    this.C = 0L;
                }
            }
        }
    }
    
    public void onSwitchBackground() {
        this.a(SystemClock.elapsedRealtime() - this.D);
        this.E = SystemClock.elapsedRealtime();
    }
    
    public void onSwitchForeground() {
        this.D = SystemClock.elapsedRealtime();
    }
    
    public int[] returnRequiredMsgIds() {
        return new int[] { 3 };
    }
}
