package com.alipay.apmobilesecuritysdk.c;

import android.os.Build;
import com.alipay.sdk.m.c0.d;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.Context;

public final class a
{
    public static void a(final Context context, final String s, final String s2, String format) {
        synchronized (a.class) {
            final com.alipay.sdk.m.c0.a b = b(context, s, s2, format);
            final StringBuilder sb = new StringBuilder();
            sb.append(context.getFilesDir().getAbsolutePath());
            sb.append("/log/ap");
            final String string = sb.toString();
            format = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(format);
            sb2.append(".log");
            d.a(string, sb2.toString(), b.toString());
        }
    }
    
    public static void a(final String s) {
        synchronized (a.class) {
            d.a(s);
        }
    }
    
    public static void a(final Throwable t) {
        synchronized (a.class) {
            d.a(t);
        }
    }
    
    public static com.alipay.sdk.m.c0.a b(final Context context, final String s, final String s2, final String s3) {
        String s4;
        try {
            context.getPackageName();
        }
        finally {
            s4 = "";
        }
        return new com.alipay.sdk.m.c0.a(Build.MODEL, s4, "APPSecuritySDK-ALIPAYSDK", "3.4.0.202311031119", s, s2, s3);
    }
}
