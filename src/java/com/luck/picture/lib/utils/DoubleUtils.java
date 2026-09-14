package com.luck.picture.lib.utils;

import android.os.SystemClock;

public class DoubleUtils
{
    private static final long TIME = 600L;
    private static long lastClickTime;
    
    public static boolean isFastDoubleClick() {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - DoubleUtils.lastClickTime < 600L) {
            return true;
        }
        DoubleUtils.lastClickTime = elapsedRealtime;
        return false;
    }
}
