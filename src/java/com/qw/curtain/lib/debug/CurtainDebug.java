package com.qw.curtain.lib.debug;

import android.util.Log;

public class CurtainDebug
{
    private static boolean isDebug;
    
    public static void d(final String s, final String s2) {
        if (CurtainDebug.isDebug) {
            Log.d(s, s2);
        }
    }
    
    public static void e(final String s, final String s2) {
        if (CurtainDebug.isDebug) {
            Log.e(s, s2);
        }
    }
    
    public static void i(final String s, final String s2) {
        if (CurtainDebug.isDebug) {
            Log.i(s, s2);
        }
    }
    
    public static boolean isDebug() {
        return CurtainDebug.isDebug;
    }
    
    public static void setDebug(final boolean isDebug) {
        CurtainDebug.isDebug = isDebug;
    }
    
    public static void v(final String s, final String s2) {
        if (CurtainDebug.isDebug) {
            Log.v(s, s2);
        }
    }
    
    public static void w(final String s, final String s2) {
        if (CurtainDebug.isDebug) {
            Log.w(s, s2);
        }
    }
}
