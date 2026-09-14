package com.alibaba.sdk.android.man.crashreporter.b;

import android.util.Log;

public class a
{
    public static final String TAG = "MotuCrashReporter";
    
    public static void a(final String s, final String s2, final Throwable t) {
        Log.d(s, s2, t);
    }
    
    public static void a(final String s, final Throwable t) {
        a("MotuCrashReporter", s, t);
    }
    
    public static void b(final String s, final String s2) {
        Log.d(s, s2);
    }
    
    public static void b(final String s, final String s2, final Throwable t) {
        Log.i(s, s2, t);
    }
    
    public static void b(final String s, final Throwable t) {
        b("MotuCrashReporter", s, t);
    }
    
    public static void c(final String s, final String s2) {
        Log.i(s, s2);
    }
    
    public static void c(final String s, final String s2, final Throwable t) {
        Log.w(s, s2, t);
    }
    
    public static void c(final String s, final Throwable t) {
        c("MotuCrashReporter", s, t);
    }
    
    public static void d(final String s, final String s2) {
        Log.w(s, s2);
    }
    
    public static void d(final String s, final String s2, final Throwable t) {
        Log.e(s, s2, t);
    }
    
    public static void d(final String s, final Throwable t) {
        d("MotuCrashReporter", s, t);
    }
    
    public static void e(final String s) {
        b("MotuCrashReporter", s);
    }
    
    public static void e(final String s, final String s2) {
        Log.e(s, s2);
    }
    
    public static void f(final String s) {
        c("MotuCrashReporter", s);
    }
    
    public static void g(final String s) {
        d("MotuCrashReporter", s);
    }
    
    public static void h(final String s) {
        e("MotuCrashReporter", s);
    }
}
