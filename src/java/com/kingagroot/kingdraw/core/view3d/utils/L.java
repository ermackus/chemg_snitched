package com.kingagroot.kingdraw.core.view3d.utils;

import android.util.Log;

public class L
{
    private static final String TAG = "3D_Core";
    private static boolean isDebug = true;
    
    private L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    
    public static void d(final String s) {
        if (L.isDebug) {
            Log.d("3D_Core", s);
        }
    }
    
    public static void e(final String s) {
        if (L.isDebug) {
            Log.e("3D_Core", s);
        }
    }
    
    public static void i(final String s) {
        if (L.isDebug) {
            Log.i("3D_Core", s);
        }
    }
    
    public static void v(final String s) {
        if (L.isDebug) {
            Log.v("3D_Core", s);
        }
    }
}
