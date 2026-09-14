package com.ut.mini.crashhandler;

import java.util.Map;

public interface IUTCrashCaughtListner
{
    Map<String, String> onCrashCaught(final Thread p0, final Throwable p1);
}
