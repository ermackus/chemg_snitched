package com.ut.mini.core.appstatus;

import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;

public class UTMCAppStatusRegHelper
{
    public static void registeActivityLifecycleCallbacks(final Application application) {
        if (application != null) {
            application.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)UTMCAppStatusMonitor.getInstance());
        }
    }
    
    public static void registerAppStatusCallbacks(final UTMCAppStatusCallbacks utmcAppStatusCallbacks) {
        if (utmcAppStatusCallbacks != null) {
            UTMCAppStatusMonitor.getInstance().registerAppStatusCallbacks(utmcAppStatusCallbacks);
        }
    }
    
    public static void unRegisterAppStatusCallbacks(final UTMCAppStatusCallbacks utmcAppStatusCallbacks) {
        if (utmcAppStatusCallbacks != null) {
            UTMCAppStatusMonitor.getInstance().unregisterAppStatusCallbacks(utmcAppStatusCallbacks);
        }
    }
    
    public static void unregisterActivityLifecycleCallbacks(final Application application) {
        if (application != null) {
            application.unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)UTMCAppStatusMonitor.getInstance());
        }
    }
}
