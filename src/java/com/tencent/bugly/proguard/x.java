package com.tencent.bugly.proguard;

import android.util.Log;
import java.util.Locale;

public final class x
{
    public static String a = "CrashReport";
    public static boolean b = false;
    private static String c = "CrashReportInfo";
    
    private static boolean a(final int n, final String s, final Object... array) {
        if (!x.b) {
            return false;
        }
        String format;
        if (s == null) {
            format = "null";
        }
        else {
            format = s;
            if (array != null) {
                if (array.length == 0) {
                    format = s;
                }
                else {
                    format = String.format(Locale.US, s, array);
                }
            }
        }
        if (n == 0) {
            Log.i(x.a, format);
            return true;
        }
        if (n == 1) {
            Log.d(x.a, format);
            return true;
        }
        if (n == 2) {
            Log.w(x.a, format);
            return true;
        }
        if (n == 3) {
            Log.e(x.a, format);
            return true;
        }
        if (n != 5) {
            return false;
        }
        Log.i(x.c, format);
        return true;
    }
    
    public static boolean a(final Class clazz, final String s, final Object... array) {
        return a(0, String.format(Locale.US, "[%s] %s", new Object[] { clazz.getSimpleName(), s }), array);
    }
    
    public static boolean a(final String s, final Object... array) {
        return a(0, s, array);
    }
    
    public static boolean a(final Throwable t) {
        return x.b && a(2, z.a(t), new Object[0]);
    }
    
    public static boolean b(final Class clazz, final String s, final Object... array) {
        return a(1, String.format(Locale.US, "[%s] %s", new Object[] { clazz.getSimpleName(), s }), array);
    }
    
    public static boolean b(final String s, final Object... array) {
        return a(5, s, array);
    }
    
    public static boolean b(final Throwable t) {
        return x.b && a(3, z.a(t), new Object[0]);
    }
    
    public static boolean c(final String s, final Object... array) {
        return a(1, s, array);
    }
    
    public static boolean d(final String s, final Object... array) {
        return a(2, s, array);
    }
    
    public static boolean e(final String s, final Object... array) {
        return a(3, s, array);
    }
}
