package com.king.zxing.util;

import android.util.Log;

public class LogUtils
{
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int PRINTLN = 1;
    public static final String TAG = "ZXingLite";
    public static final String TAG_FORMAT = "%s.%s(%s:%d)";
    public static final int VERBOSE = 2;
    public static final String VERTICAL = "|";
    public static final int WARN = 5;
    private static boolean isShowLog = true;
    private static int priority = 1;
    
    private LogUtils() {
        throw new AssertionError();
    }
    
    public static void d(final String s) {
        if (LogUtils.isShowLog && LogUtils.priority <= 3) {
            Log.d(getCallerStackLogTag(), String.valueOf((Object)s));
        }
    }
    
    public static void d(final String s, final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 3) {
            Log.d(getCallerStackLogTag(), String.valueOf((Object)s), t);
        }
    }
    
    public static void d(final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 3) {
            Log.d(getCallerStackLogTag(), getStackTraceString(t));
        }
    }
    
    public static void e(final String s) {
        if (LogUtils.isShowLog && LogUtils.priority <= 6) {
            Log.e(getCallerStackLogTag(), String.valueOf((Object)s));
        }
    }
    
    public static void e(final String s, final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 6) {
            Log.e(getCallerStackLogTag(), String.valueOf((Object)s), t);
        }
    }
    
    public static void e(final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 6) {
            Log.e(getCallerStackLogTag(), getStackTraceString(t));
        }
    }
    
    private static String generateTag(final StackTraceElement stackTraceElement) {
        final String className = stackTraceElement.getClassName();
        final String format = String.format("%s.%s(%s:%d)", new Object[] { className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), stackTraceElement.getFileName(), stackTraceElement.getLineNumber() });
        final StringBuilder sb = new StringBuilder();
        sb.append("ZXingLite");
        sb.append("|");
        sb.append(format);
        return sb.toString();
    }
    
    private static String getCallerStackLogTag() {
        return generateTag(getStackTraceElement(5));
    }
    
    public static int getPriority() {
        return LogUtils.priority;
    }
    
    public static StackTraceElement getStackTraceElement(final int n) {
        return Thread.currentThread().getStackTrace()[n];
    }
    
    private static String getStackTraceString(final Throwable t) {
        return Log.getStackTraceString(t);
    }
    
    public static void i(final String s) {
        if (LogUtils.isShowLog && LogUtils.priority <= 4) {
            Log.i(getCallerStackLogTag(), String.valueOf((Object)s));
        }
    }
    
    public static void i(final String s, final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 4) {
            Log.i(getCallerStackLogTag(), String.valueOf((Object)s), t);
        }
    }
    
    public static void i(final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 4) {
            Log.i(getCallerStackLogTag(), getStackTraceString(t));
        }
    }
    
    public static boolean isShowLog() {
        return LogUtils.isShowLog;
    }
    
    public static void print(final Object o) {
        if (LogUtils.isShowLog && LogUtils.priority <= 1) {
            System.out.print(o);
        }
    }
    
    public static void print(final String s) {
        if (LogUtils.isShowLog && LogUtils.priority <= 1) {
            System.out.print(s);
        }
    }
    
    public static void printf(final String s) {
        if (LogUtils.isShowLog && LogUtils.priority <= 1) {
            System.out.printf(s, new Object[0]);
        }
    }
    
    public static void println(final Object o) {
        if (LogUtils.isShowLog && LogUtils.priority <= 1) {
            System.out.println(o);
        }
    }
    
    public static void println(final String s) {
        if (LogUtils.isShowLog && LogUtils.priority <= 1) {
            System.out.println(s);
        }
    }
    
    public static void setPriority(final int priority) {
        LogUtils.priority = priority;
    }
    
    public static void setShowLog(final boolean isShowLog) {
        LogUtils.isShowLog = isShowLog;
    }
    
    public static void v(final String s) {
        if (LogUtils.isShowLog && LogUtils.priority <= 2) {
            Log.v(getCallerStackLogTag(), String.valueOf((Object)s));
        }
    }
    
    public static void v(final String s, final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 2) {
            Log.v(getCallerStackLogTag(), String.valueOf((Object)s), t);
        }
    }
    
    public static void v(final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 2) {
            Log.v(getCallerStackLogTag(), getStackTraceString(t));
        }
    }
    
    public static void w(final String s) {
        if (LogUtils.isShowLog && LogUtils.priority <= 5) {
            Log.w(getCallerStackLogTag(), String.valueOf((Object)s));
        }
    }
    
    public static void w(final String s, final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 5) {
            Log.w(getCallerStackLogTag(), String.valueOf((Object)s), t);
        }
    }
    
    public static void w(final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 5) {
            Log.w(getCallerStackLogTag(), getStackTraceString(t));
        }
    }
    
    public static void wtf(final String s) {
        if (LogUtils.isShowLog && LogUtils.priority <= 7) {
            Log.wtf(getCallerStackLogTag(), String.valueOf((Object)s));
        }
    }
    
    public static void wtf(final String s, final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 7) {
            Log.wtf(getCallerStackLogTag(), String.valueOf((Object)s), t);
        }
    }
    
    public static void wtf(final Throwable t) {
        if (LogUtils.isShowLog && LogUtils.priority <= 7) {
            Log.wtf(getCallerStackLogTag(), getStackTraceString(t));
        }
    }
}
