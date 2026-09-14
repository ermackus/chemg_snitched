package com.alibaba.sdk.android.utils;

import android.util.Log;

public class b implements Thread$UncaughtExceptionHandler
{
    public void uncaughtException(final Thread thread, final Throwable t) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Catch an uncaught exception, ");
        sb.append(thread.getName());
        sb.append(", error message: ");
        sb.append(t.getMessage());
        Log.e("AlicloudUtils", sb.toString());
        t.printStackTrace();
    }
}
