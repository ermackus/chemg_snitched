package com.alipay.sdk.m.u;

import android.os.SystemClock;

public class b
{
    public static final long a = 3000L;
    public static long b = -1L;
    
    public static boolean a() {
        synchronized (b.class) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            boolean b;
            if (elapsedRealtime - com.alipay.sdk.m.u.b.b >= 3000L) {
                com.alipay.sdk.m.u.b.b = elapsedRealtime;
                b = false;
            }
            else {
                b = true;
            }
            return b;
        }
    }
}
