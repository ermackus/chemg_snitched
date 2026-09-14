package com.alibaba.sdk.android.man.util;

import android.util.Log;

public class MANLog
{
    private static boolean isPrintLog;
    
    private MANLog() {
    }
    
    public static void Logd(final String s, final String s2) {
        if (MANLog.isPrintLog && s != null && s2 != null) {
            Log.d(s, s2);
        }
    }
    
    public static void Logd(final String s, final String s2, final long n) {
        if (MANLog.isPrintLog && s != null && s2 != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(Thread.currentThread().getId());
            sb.append(" - ");
            sb.append(s2);
            sb.append((System.nanoTime() - n) / 1000000L);
            Log.i(s, sb.toString());
        }
    }
    
    public static void Loge(final String s, final String s2) {
        if (MANLog.isPrintLog && s != null && s2 != null) {
            Log.e(s, s2);
        }
    }
    
    public static void Logf(final String s, final String s2) {
        if (s != null && s2 != null) {
            Log.d(s, s2);
        }
    }
    
    public static void Logi(final String s, final String s2) {
        if (MANLog.isPrintLog && s != null && s2 != null) {
            Log.i(s, s2);
        }
    }
    
    public static void Logv(final String s, final String s2) {
        if (MANLog.isPrintLog && s != null && s2 != null) {
            Log.v(s, s2);
        }
    }
    
    public static void Logw(final String s, final String s2) {
        if (MANLog.isPrintLog && s != null && s2 != null) {
            Log.w(s, s2);
        }
    }
    
    public static void enableLog() {
        MANLog.isPrintLog = true;
    }
    
    public static boolean isPrintLog() {
        return MANLog.isPrintLog;
    }
}
