package com.alipay.sdk.interior;

import com.alipay.sdk.m.u.e;
import com.alipay.sdk.m.k.a;
import android.os.SystemClock;
import com.alipay.sdk.m.s.b;
import android.content.Context;

public class Log
{
    public static long a;
    
    public static boolean forcedLogReport(final Context context) {
        try {
            b.d().a(context);
            final long a = SystemClock.elapsedRealtime() / 1000L;
            if (a - Log.a < 600L) {
                return false;
            }
            Log.a = a;
            com.alipay.sdk.m.k.a.a(context);
            return true;
        }
        catch (final Exception ex) {
            e.a((Throwable)ex);
            return false;
        }
    }
    
    public static void setupLogCallback(final ISdkLogCallback sdkLogCallback) {
        e.a(sdkLogCallback);
    }
    
    public interface ISdkLogCallback
    {
        void onLogLine(final String p0);
    }
}
