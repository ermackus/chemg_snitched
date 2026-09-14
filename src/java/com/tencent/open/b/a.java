package com.tencent.open.b;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.database.Cursor;
import com.tencent.open.log.SLog;
import android.content.Context;
import android.net.Uri;

public class a
{
    protected static final Uri a;
    
    static {
        a = Uri.parse("content://telephony/carriers/preferapn");
    }
    
    public static String a(final Context context) {
        final int d = d(context);
        if (d == 2) {
            return "wifi";
        }
        if (d == 1) {
            return "cmwap";
        }
        if (d == 4) {
            return "cmnet";
        }
        if (d == 16) {
            return "uniwap";
        }
        if (d == 8) {
            return "uninet";
        }
        if (d == 64) {
            return "wap";
        }
        if (d == 32) {
            return "net";
        }
        if (d == 512) {
            return "ctwap";
        }
        if (d == 256) {
            return "ctnet";
        }
        if (d == 2048) {
            return "3gnet";
        }
        if (d == 1024) {
            return "3gwap";
        }
        final String b = b(context);
        if (b != null && b.length() != 0) {
            return b;
        }
        return "none";
    }
    
    public static String b(final Context context) {
        return "";
    }
    
    public static String c(final Context context) {
        try {
            final Cursor query = context.getContentResolver().query(com.tencent.open.b.a.a, (String[])null, (String)null, (String[])null, (String)null);
            if (query == null) {
                return null;
            }
            query.moveToFirst();
            if (query.isAfterLast()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            final String string = query.getString(query.getColumnIndex("proxy"));
            if (query != null) {
                query.close();
            }
            return string;
        }
        catch (final SecurityException ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getApnProxy has exception: ");
            sb.append(ex.getMessage());
            SLog.e("openSDK_LOG.APNUtil", sb.toString());
            return "";
        }
    }
    
    public static int d(final Context context) {
        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return 128;
            }
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return 128;
            }
            if (activeNetworkInfo.getTypeName().toUpperCase().equals((Object)"WIFI")) {
                return 2;
            }
            final String lowerCase = activeNetworkInfo.getExtraInfo().toLowerCase();
            if (lowerCase.startsWith("cmwap")) {
                return 1;
            }
            if (lowerCase.startsWith("cmnet") || lowerCase.startsWith("epc.tmobile.com")) {
                return 4;
            }
            if (lowerCase.startsWith("uniwap")) {
                return 16;
            }
            if (lowerCase.startsWith("uninet")) {
                return 8;
            }
            if (lowerCase.startsWith("wap")) {
                return 64;
            }
            if (lowerCase.startsWith("net")) {
                return 32;
            }
            if (lowerCase.startsWith("ctwap")) {
                return 512;
            }
            if (lowerCase.startsWith("ctnet")) {
                return 256;
            }
            if (lowerCase.startsWith("3gwap")) {
                return 1024;
            }
            if (lowerCase.startsWith("3gnet")) {
                return 2048;
            }
            if (lowerCase.startsWith("#777")) {
                final String c = c(context);
                if (c != null && c.length() > 0) {
                    return 512;
                }
                return 256;
            }
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getMProxyType has exception: ");
            sb.append(ex.getMessage());
            SLog.e("openSDK_LOG.APNUtil", sb.toString());
        }
        return 128;
    }
    
    public static String e(final Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "MOBILE";
        }
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getTypeName();
        }
        return "MOBILE";
    }
}
