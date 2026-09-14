package com.alibaba.sdk.android.utils;

import android.util.Log;

public class d
{
    private static boolean c;
    
    public static void a(final String s, final String s2) {
        if (d.c) {
            Log.d(s, s2);
        }
    }
    
    public static void a(final String s, final Throwable t) {
        if (d.c && t != null) {
            Log.e(s, t.toString(), t);
        }
    }
    
    public static void b(final String s, final String s2) {
        if (d.c) {
            Log.i(s, s2);
        }
    }
    
    public static void c(final String s, final String s2) {
        if (d.c) {
            Log.e(s, s2);
        }
    }
    
    public static boolean c() {
        return d.c;
    }
    
    public static void setLogEnabled(final boolean c) {
        d.c = c;
    }
}
