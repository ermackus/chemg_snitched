package com.kingagroot.kingdraw.utils;

import android.app.ActivityManager$MemoryInfo;
import android.app.ActivityManager;
import android.content.Context;

public class MemoryUtil
{
    public static float getMemoryInfo(final Context context) {
        final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
        final ActivityManager$MemoryInfo activityManager$MemoryInfo = new ActivityManager$MemoryInfo();
        activityManager.getMemoryInfo(activityManager$MemoryInfo);
        return (float)activityManager$MemoryInfo.totalMem;
    }
}
