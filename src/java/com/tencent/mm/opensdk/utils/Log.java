package com.tencent.mm.opensdk.utils;

public class Log
{
    private static ILog logImpl;
    
    public static void d(final String s, final String s2) {
        final ILog logImpl = Log.logImpl;
        if (logImpl == null) {
            android.util.Log.d(s, s2);
        }
        else {
            logImpl.d(s, s2);
        }
    }
    
    public static void e(final String s, final String s2) {
        final ILog logImpl = Log.logImpl;
        if (logImpl == null) {
            android.util.Log.e(s, s2);
        }
        else {
            logImpl.e(s, s2);
        }
    }
    
    public static void i(final String s, final String s2) {
        final ILog logImpl = Log.logImpl;
        if (logImpl == null) {
            android.util.Log.i(s, s2);
        }
        else {
            logImpl.i(s, s2);
        }
    }
    
    public static void setLogImpl(final ILog logImpl) {
        Log.logImpl = logImpl;
    }
    
    public static void v(final String s, final String s2) {
        final ILog logImpl = Log.logImpl;
        if (logImpl == null) {
            android.util.Log.v(s, s2);
        }
        else {
            logImpl.v(s, s2);
        }
    }
    
    public static void w(final String s, final String s2) {
        final ILog logImpl = Log.logImpl;
        if (logImpl == null) {
            android.util.Log.w(s, s2);
        }
        else {
            logImpl.w(s, s2);
        }
    }
}
