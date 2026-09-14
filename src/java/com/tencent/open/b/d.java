package com.tencent.open.b;

import android.os.Environment;
import android.os.Build$VERSION;
import android.os.Build;
import android.util.DisplayMetrics;
import java.util.Locale;
import android.view.WindowManager;
import android.text.TextUtils;
import android.content.Context;

public class d
{
    private static String a;
    private static String b;
    
    public static String a() {
        return "";
    }
    
    public static String a(final Context context) {
        if (!TextUtils.isEmpty((CharSequence)d.a)) {
            return d.a;
        }
        if (context == null) {
            return "";
        }
        d.a = "";
        final WindowManager windowManager = (WindowManager)context.getSystemService("window");
        if (windowManager != null) {
            final int width = windowManager.getDefaultDisplay().getWidth();
            final int height = windowManager.getDefaultDisplay().getHeight();
            final StringBuilder sb = new StringBuilder();
            sb.append(width);
            sb.append("x");
            sb.append(height);
            d.a = sb.toString();
        }
        return d.a;
    }
    
    public static String b() {
        return Locale.getDefault().getLanguage();
    }
    
    public static String b(final Context context) {
        return "";
    }
    
    public static String c(final Context context) {
        return "";
    }
    
    public static String d(final Context context) {
        return "";
    }
    
    public static String e(final Context context) {
        try {
            if (d.b == null) {
                final WindowManager windowManager = (WindowManager)context.getSystemService("window");
                final DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                final StringBuilder sb = new StringBuilder();
                sb.append("imei=");
                sb.append(b(context));
                sb.append('&');
                sb.append("model=");
                sb.append(Build.MODEL);
                sb.append('&');
                sb.append("os=");
                sb.append(Build$VERSION.RELEASE);
                sb.append('&');
                sb.append("apilevel=");
                sb.append(Build$VERSION.SDK_INT);
                sb.append('&');
                String b;
                if ((b = com.tencent.open.b.a.b(context)) == null) {
                    b = "";
                }
                sb.append("network=");
                sb.append(b);
                sb.append('&');
                sb.append("sdcard=");
                int n;
                if (Environment.getExternalStorageState().equals((Object)"mounted")) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                sb.append(n);
                sb.append('&');
                sb.append("display=");
                sb.append(displayMetrics.widthPixels);
                sb.append('*');
                sb.append(displayMetrics.heightPixels);
                sb.append('&');
                sb.append("manu=");
                sb.append(Build.MANUFACTURER);
                sb.append("&");
                sb.append("wifi=");
                sb.append(com.tencent.open.b.a.e(context));
                d.b = sb.toString();
            }
            return d.b;
        }
        catch (final Exception ex) {
            return null;
        }
    }
}
