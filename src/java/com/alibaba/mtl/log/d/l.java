package com.alibaba.mtl.log.d;

import android.content.Intent;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import com.alibaba.mtl.log.a;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Context;

public class l
{
    private static a a;
    private static b a;
    private static String[] a;
    
    static {
        l.a = new String[] { "Unknown", "Unknown" };
        l.a = new b();
        l.a = new a();
    }
    
    private static String a(final int n) {
        switch (n) {
            default: {
                return "Unknown";
            }
            case 13: {
                return "4G";
            }
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15: {
                return "3G";
            }
            case 1:
            case 2:
            case 4:
            case 7:
            case 11: {
                return "2G";
            }
        }
    }
    
    public static void b(final Context context) {
        if (context == null) {
            return;
        }
        context.registerReceiver((BroadcastReceiver)l.a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
    
    public static void c(final Context context) {
        if (context == null) {
            return;
        }
        final b a = l.a;
        if (a == null) {
            return;
        }
        context.unregisterReceiver((BroadcastReceiver)a);
    }
    
    public static String[] getNetworkState(final Context context) {
        return l.a;
    }
    
    public static boolean isConnected() {
        final Context context = com.alibaba.mtl.log.a.getContext();
        if (context == null) {
            return true;
        }
        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
            if (connectivityManager != null) {
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
            return true;
        }
        catch (final Exception ex) {
            return true;
        }
    }
    
    public static String u() {
        final Context context = com.alibaba.mtl.log.a.getContext();
        if (context == null) {
            return "Unknown";
        }
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
                return "Unknown";
            }
            final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "Unknown";
            }
            if (activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() == 1) {
                    return "wifi";
                }
                if (activeNetworkInfo.getType() == 0) {
                    return a(activeNetworkInfo.getSubtype());
                }
            }
            return "Unknown";
        }
        finally {
            return "Unknown";
        }
    }
    
    private static class a implements Runnable
    {
        private Context a;
        
        public a a(final Context a) {
            this.a = a;
            return this;
        }
        
        public void run() {
            final Context a = this.a;
            if (a == null) {
                return;
            }
            try {
                if (a.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.a.getPackageName()) != 0) {
                    l.a[0] = "Unknown";
                    return;
                }
                final ConnectivityManager connectivityManager = (ConnectivityManager)this.a.getSystemService("connectivity");
                if (connectivityManager == null) {
                    l.a[0] = "Unknown";
                    return;
                }
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    if (1 == activeNetworkInfo.getType()) {
                        l.a[0] = "Wi-Fi";
                    }
                    else if (activeNetworkInfo.getType() == 0) {
                        l.a[0] = "2G/3G";
                        l.a[1] = activeNetworkInfo.getSubtypeName();
                    }
                }
            }
            catch (final Exception ex) {}
        }
    }
    
    private static class b extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            s.a().b((Runnable)l.a.a(context));
        }
    }
}
