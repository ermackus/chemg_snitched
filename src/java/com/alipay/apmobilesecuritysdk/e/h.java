package com.alipay.apmobilesecuritysdk.e;

import android.content.SharedPreferences$Editor;
import com.alipay.sdk.m.y.b;
import java.util.UUID;
import com.alipay.sdk.m.b0.e;
import com.alipay.sdk.m.b0.a;
import android.content.Context;

public class h
{
    public static String a = "";
    
    public static long a(final Context context) {
        final String a = com.alipay.sdk.m.b0.a.a(context, "vkeyid_settings", "update_time_interval");
        final boolean b = com.alipay.sdk.m.z.a.b(a);
        long long1 = 86400000L;
        if (!b) {
            return long1;
        }
        try {
            long1 = Long.parseLong(a);
            return long1;
        }
        catch (final Exception ex) {
            long1 = long1;
            return long1;
        }
    }
    
    public static void a(final Context context, final String s) {
        a(context, "update_time_interval", s);
    }
    
    public static void a(final Context context, final String s, final long n) {
        final StringBuilder sb = new StringBuilder("vkey_valid");
        sb.append(s);
        com.alipay.sdk.m.b0.a.a(context, "vkeyid_settings", sb.toString(), String.valueOf(n));
    }
    
    public static void a(final Context context, final String s, final String s2) {
        com.alipay.sdk.m.b0.a.a(context, "vkeyid_settings", s, s2);
    }
    
    public static void a(final Context context, final boolean b) {
        String s;
        if (b) {
            s = "1";
        }
        else {
            s = "0";
        }
        a(context, "log_switch", s);
    }
    
    public static String b(final Context context) {
        return com.alipay.sdk.m.b0.a.a(context, "vkeyid_settings", "last_apdid_env");
    }
    
    public static void b(final Context context, final String s) {
        a(context, "last_machine_boot_time", s);
    }
    
    public static void c(final Context context, final String s) {
        a(context, "last_apdid_env", s);
    }
    
    public static boolean c(final Context context) {
        final String a = com.alipay.sdk.m.b0.a.a(context, "vkeyid_settings", "log_switch");
        return a != null && "1".equals((Object)a);
    }
    
    public static String d(final Context context) {
        return com.alipay.sdk.m.b0.a.a(context, "vkeyid_settings", "dynamic_key");
    }
    
    public static void d(final Context context, final String s) {
        a(context, "agent_switch", s);
    }
    
    public static String e(final Context context) {
        return com.alipay.sdk.m.b0.a.a(context, "vkeyid_settings", "apse_degrade");
    }
    
    public static void e(final Context context, final String s) {
        a(context, "dynamic_key", s);
    }
    
    public static String f(final Context context) {
        synchronized (h.class) {
            if (com.alipay.sdk.m.z.a.a(h.a) && com.alipay.sdk.m.z.a.a(h.a = e.a(context, "alipay_vkey_random", "random", ""))) {
                final String a = b.a(UUID.randomUUID().toString());
                if ((h.a = a) != null) {
                    final SharedPreferences$Editor edit = context.getSharedPreferences("alipay_vkey_random", 0).edit();
                    if (edit != null) {
                        edit.putString("random", a);
                        edit.commit();
                    }
                }
            }
            return h.a;
        }
    }
    
    public static void f(final Context context, final String s) {
        a(context, "webrtc_url", s);
    }
    
    public static void g(final Context context, final String s) {
        a(context, "apse_degrade", s);
    }
    
    public static long h(final Context context, final String s) {
        try {
            final StringBuilder sb = new StringBuilder("vkey_valid");
            sb.append(s);
            final String a = com.alipay.sdk.m.b0.a.a(context, "vkeyid_settings", sb.toString());
            if (com.alipay.sdk.m.z.a.a(a)) {}
            return Long.parseLong(a);
        }
        finally {
            return 0L;
        }
    }
}
