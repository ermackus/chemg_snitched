package com.tencent.bugly.crashreport.crash.jni;

import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.util.Map;

public interface NativeExceptionHandler
{
    void handleNativeException(final int p0, final int p1, final long p2, final long p3, final String p4, final String p5, final String p6, final String p7, final int p8, final String p9, final int p10, final int p11, final int p12, final String p13, final String p14);
    
    void handleNativeException2(final int p0, final int p1, final long p2, final long p3, final String p4, final String p5, final String p6, final String p7, final int p8, final String p9, final int p10, final int p11, final int p12, final String p13, final String p14, final String[] p15);
    
    CrashDetailBean packageCrashDatas(final String p0, final String p1, final long p2, final String p3, final String p4, final String p5, final String p6, final String p7, final String p8, final String p9, final String p10, final String p11, final byte[] p12, final Map<String, String> p13, final boolean p14, final boolean p15);
}
