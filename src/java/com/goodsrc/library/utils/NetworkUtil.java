package com.goodsrc.library.utils;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.Context;
import android.net.Uri;

public class NetworkUtil
{
    public static String getValueByName(String replace, final String s) {
        final String[] split = replace.substring(replace.indexOf("?") + 1).split("&");
        final int length = split.length;
        int n = 0;
        while (true) {
            replace = "";
            if (n >= length) {
                break;
            }
            replace = split[n];
            if (replace.contains((CharSequence)s)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("=");
                replace = replace.replace((CharSequence)sb.toString(), (CharSequence)"");
                break;
            }
            ++n;
        }
        return replace;
    }
    
    public static String httpToHttps(final String s) {
        return Uri.parse(s).buildUpon().scheme("https").build().toString();
    }
    
    public static boolean isNetworkConnected(final Context context) {
        if (context != null) {
            final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
