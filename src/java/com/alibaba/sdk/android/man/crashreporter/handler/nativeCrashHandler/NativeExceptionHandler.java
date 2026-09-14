package com.alibaba.sdk.android.man.crashreporter.handler.nativeCrashHandler;

public interface NativeExceptionHandler
{
    void onNativeException(final int p0, final int p1, final long p2, final long p3, final String p4, final String p5, final String p6, final String p7, final int p8, final String p9, final int p10, final int p11, final int p12, final String p13, final String p14);
    
    void onNativeExceptionStart(final String p0, final String p1, final String p2);
}
