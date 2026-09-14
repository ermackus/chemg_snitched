package com.kingagroot.kingdraw.core.utils;

import android.util.Log;

public class L
{
    private static final String TAG = "Core";
    private static boolean isDebug = true;
    
    private L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    
    public static void d(final String s) {
        if (L.isDebug) {
            Log.d("Core", s);
        }
    }
    
    public static void e(final String s) {
        if (L.isDebug) {
            Log.e("Core", s);
        }
    }
    
    public static void i(final String s) {
        if (L.isDebug) {
            Log.i("Core", s);
        }
    }
    
    public static void v(final String s) {
        if (L.isDebug) {
            Log.v("Core", s);
        }
    }
}
