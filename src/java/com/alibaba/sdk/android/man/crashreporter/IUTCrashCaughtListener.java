package com.alibaba.sdk.android.man.crashreporter;

import java.util.Map;

public interface IUTCrashCaughtListener
{
    Map<String, Object> onCrashCaught(final Thread p0, final Throwable p1);
}
