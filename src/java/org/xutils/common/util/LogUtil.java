package org.xutils.common.util;

import android.text.TextUtils;
import android.util.Log;
import org.xutils.x;

public class LogUtil
{
    public static String customTagPrefix = "x_log";
    
    private LogUtil() {
    }
    
    public static void d(final String s) {
        if (!x.isDebug()) {
            return;
        }
        Log.d(generateTag(), s);
    }
    
    public static void d(final String s, final Throwable t) {
        if (!x.isDebug()) {
            return;
        }
        Log.d(generateTag(), s, t);
    }
    
    public static void e(final String s) {
        if (!x.isDebug()) {
            return;
        }
        Log.e(generateTag(), s);
    }
    
    public static void e(final String s, final Throwable t) {
        if (!x.isDebug()) {
            return;
        }
        Log.e(generateTag(), s, t);
    }
    
    private static String generateTag() {
        final StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        final String className = stackTraceElement.getClassName();
        String s = String.format("%s.%s(L:%d)", new Object[] { className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), stackTraceElement.getLineNumber() });
        if (!TextUtils.isEmpty((CharSequence)LogUtil.customTagPrefix)) {
            final StringBuilder sb = new StringBuilder();
            sb.append(LogUtil.customTagPrefix);
            sb.append(":");
            sb.append(s);
            s = sb.toString();
        }
        return s;
    }
    
    public static void i(final String s) {
        if (!x.isDebug()) {
            return;
        }
        Log.i(generateTag(), s);
    }
    
    public static void i(final String s, final Throwable t) {
        if (!x.isDebug()) {
            return;
        }
        Log.i(generateTag(), s, t);
    }
    
    public static void v(final String s) {
        if (!x.isDebug()) {
            return;
        }
        Log.v(generateTag(), s);
    }
    
    public static void v(final String s, final Throwable t) {
        if (!x.isDebug()) {
            return;
        }
        Log.v(generateTag(), s, t);
    }
    
    public static void w(final String s) {
        if (!x.isDebug()) {
            return;
        }
        Log.w(generateTag(), s);
    }
    
    public static void w(final String s, final Throwable t) {
        if (!x.isDebug()) {
            return;
        }
        Log.w(generateTag(), s, t);
    }
    
    public static void w(final Throwable t) {
        if (!x.isDebug()) {
            return;
        }
        Log.w(generateTag(), t);
    }
    
    public static void wtf(final String s) {
        if (!x.isDebug()) {
            return;
        }
        Log.wtf(generateTag(), s);
    }
    
    public static void wtf(final String s, final Throwable t) {
        if (!x.isDebug()) {
            return;
        }
        Log.wtf(generateTag(), s, t);
    }
    
    public static void wtf(final Throwable t) {
        if (!x.isDebug()) {
            return;
        }
        Log.wtf(generateTag(), t);
    }
}
