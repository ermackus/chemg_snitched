package com.goodsrc.library.utils;

import android.util.Log;
import com.goodsrc.library.core.LibraryApplication;

public class L
{
    private static String TAG = "Core";
    
    private L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    
    public static void d(final String s) {
        if (LibraryApplication.isDebug) {
            Log.d(L.TAG, s);
        }
    }
    
    public static void e(final String s) {
        if (LibraryApplication.isDebug) {
            Log.e(L.TAG, s);
        }
    }
    
    public static void e(final String s, final Throwable t) {
        if (LibraryApplication.isDebug) {
            Log.e(L.TAG, s, t);
        }
    }
    
    public static void i(final String s) {
        if (LibraryApplication.isDebug) {
            Log.i(L.TAG, s);
        }
    }
    
    public static void setTag(final String tag) {
        L.TAG = tag;
    }
    
    public static void v(final String s) {
        if (LibraryApplication.isDebug) {
            Log.v(L.TAG, s);
        }
    }
}
