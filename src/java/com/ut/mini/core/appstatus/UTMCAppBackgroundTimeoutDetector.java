package com.ut.mini.core.appstatus;

import java.util.Map;
import java.util.HashMap;
import com.alibaba.mtl.log.c;
import android.os.SystemClock;
import android.os.Bundle;
import android.app.Activity;

public class UTMCAppBackgroundTimeoutDetector implements UTMCAppStatusCallbacks
{
    private static UTMCAppBackgroundTimeoutDetector a;
    private long B;
    
    private UTMCAppBackgroundTimeoutDetector() {
        this.B = 0L;
    }
    
    public static UTMCAppBackgroundTimeoutDetector getInstance() {
        synchronized (UTMCAppBackgroundTimeoutDetector.class) {
            if (UTMCAppBackgroundTimeoutDetector.a == null) {
                UTMCAppBackgroundTimeoutDetector.a = new UTMCAppBackgroundTimeoutDetector();
            }
            return UTMCAppBackgroundTimeoutDetector.a;
        }
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityDestroyed(final Activity activity) {
    }
    
    public void onActivityPaused(final Activity activity) {
    }
    
    public void onActivityResumed(final Activity activity) {
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityStarted(final Activity activity) {
    }
    
    public void onActivityStopped(final Activity activity) {
    }
    
    public void onSwitchBackground() {
        this.B = SystemClock.elapsedRealtime();
    }
    
    public void onSwitchForeground() {
        if (0L != this.B && SystemClock.elapsedRealtime() - this.B > 30000L) {
            c.a().c((Map)new HashMap());
        }
        this.B = 0L;
    }
}
