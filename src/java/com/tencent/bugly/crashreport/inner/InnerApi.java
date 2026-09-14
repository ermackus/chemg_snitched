package com.tencent.bugly.crashreport.inner;

import java.util.Map;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.proguard.x;

public class InnerApi
{
    public static void postCocos2dxCrashAsync(final int n, final String s, final String s2, final String s3) {
        if (s == null || s2 == null || s3 == null) {
            x.e("post cocos2d-x fail args null", new Object[0]);
            return;
        }
        if (n != 5 && n != 6) {
            x.e("post cocos2d-x fail category illeagle: %d", n);
            return;
        }
        x.a("post cocos2d-x crash %s %s", s, s2);
        d.a(Thread.currentThread(), n, s, s2, s3, null);
    }
    
    public static void postH5CrashAsync(final Thread thread, final String s, final String s2, final String s3, final Map<String, String> map) {
        if (s != null && s2 != null && s3 != null) {
            x.a("post h5 crash %s %s", s, s2);
            d.a(thread, 8, s, s2, s3, map);
            return;
        }
        x.e("post h5 fail args null", new Object[0]);
    }
    
    public static void postU3dCrashAsync(final String s, final String s2, final String s3) {
        if (s == null || s2 == null || s3 == null) {
            x.e("post u3d fail args null", new Object[0]);
        }
        x.a("post u3d crash %s %s", s, s2);
        d.a(Thread.currentThread(), 4, s, s2, s3, null);
    }
}
