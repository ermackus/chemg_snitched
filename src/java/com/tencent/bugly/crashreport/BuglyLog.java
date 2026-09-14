package com.tencent.bugly.crashreport;

import com.tencent.bugly.proguard.y;
import android.util.Log;
import com.tencent.bugly.b;

public class BuglyLog
{
    public static void d(String s, final String s2) {
        String s3 = s;
        if (s == null) {
            s3 = "";
        }
        if ((s = s2) == null) {
            s = "null";
        }
        if (b.c) {
            Log.d(s3, s);
        }
        y.a("D", s3, s);
    }
    
    public static void e(String s, final String s2) {
        String s3 = s;
        if (s == null) {
            s3 = "";
        }
        if ((s = s2) == null) {
            s = "null";
        }
        if (b.c) {
            Log.e(s3, s);
        }
        y.a("E", s3, s);
    }
    
    public static void e(String s, final String s2, final Throwable t) {
        String s3 = s;
        if (s == null) {
            s3 = "";
        }
        if ((s = s2) == null) {
            s = "null";
        }
        if (b.c) {
            Log.e(s3, s, t);
        }
        y.a("E", s3, t);
    }
    
    public static void i(String s, final String s2) {
        String s3 = s;
        if (s == null) {
            s3 = "";
        }
        if ((s = s2) == null) {
            s = "null";
        }
        if (b.c) {
            Log.i(s3, s);
        }
        y.a("I", s3, s);
    }
    
    public static void setCache(final int n) {
        y.a(n);
    }
    
    public static void v(String s, final String s2) {
        String s3 = s;
        if (s == null) {
            s3 = "";
        }
        if ((s = s2) == null) {
            s = "null";
        }
        if (b.c) {
            Log.v(s3, s);
        }
        y.a("V", s3, s);
    }
    
    public static void w(String s, final String s2) {
        String s3 = s;
        if (s == null) {
            s3 = "";
        }
        if ((s = s2) == null) {
            s = "null";
        }
        if (b.c) {
            Log.w(s3, s);
        }
        y.a("W", s3, s);
    }
}
