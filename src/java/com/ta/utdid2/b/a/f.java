package com.ta.utdid2.b.a;

import android.net.NetworkInfo;
import android.util.Log;
import android.content.Context;
import android.net.ConnectivityManager;

public class f
{
    private static ConnectivityManager a;
    private static final int[] d;
    
    static {
        d = new int[] { 4, 7, 2, 1 };
    }
    
    public static ConnectivityManager a(final Context context) {
        if (context == null) {
            Log.e("NetworkUtils", "context is null!");
            return null;
        }
        if (f.a == null) {
            f.a = (ConnectivityManager)context.getSystemService("connectivity");
        }
        return f.a;
    }
    
    public static boolean a(final Context context) {
        final ConnectivityManager a = a(context);
        if (a != null) {
            try {
                final NetworkInfo activeNetworkInfo = a.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isConnected();
                }
                return false;
            }
            catch (final Exception ex) {
                Log.e("NetworkUtils", ex.toString());
                return false;
            }
        }
        Log.e("NetworkUtils", "connManager is null!");
        return false;
    }
    
    public static boolean b(final Context context) {
        final ConnectivityManager a = a(context);
        if (a != null) {
            try {
                final NetworkInfo activeNetworkInfo = a.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    final int subtype = activeNetworkInfo.getSubtype();
                    if (com.ta.utdid2.b.a.d.e) {
                        final StringBuilder sb = new StringBuilder("subType:");
                        sb.append(subtype);
                        sb.append(": name:");
                        sb.append(activeNetworkInfo.getSubtypeName());
                        Log.d("NetworkUtils", sb.toString());
                    }
                    final int[] d = f.d;
                    for (int length = d.length, i = 0; i < length; ++i) {
                        if (d[i] == subtype) {
                            return true;
                        }
                    }
                }
                else {
                    Log.e("NetworkUtils", "networkInfo is null!");
                }
            }
            catch (final Exception ex) {
                Log.e("NetworkUtils", ex.toString());
            }
        }
        else {
            Log.e("NetworkUtils", "connManager is null!");
        }
        return false;
    }
}
