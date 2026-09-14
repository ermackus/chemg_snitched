package com.alibaba.sdk.android.utils;

import java.util.concurrent.ThreadFactory;

public class a implements ThreadFactory
{
    public Thread newThread(final Runnable runnable) {
        final Thread thread = new Thread(runnable);
        thread.setUncaughtExceptionHandler((Thread$UncaughtExceptionHandler)new b());
        return thread;
    }
}
