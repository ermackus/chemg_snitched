package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import org.json.JSONObject;
import com.alipay.sdk.m.z.a;

public final class d
{
    public static c a(final String s) {
        try {
            if (!a.a(s)) {
                final JSONObject jsonObject = new JSONObject(s);
                return new c(jsonObject.optString("apdid"), jsonObject.optString("deviceInfoHash"), jsonObject.optString("timestamp"), jsonObject.optString("tid"), jsonObject.optString("utdid"));
            }
        }
        catch (final Exception ex) {
            com.alipay.apmobilesecuritysdk.c.a.a((Throwable)ex);
        }
        return null;
    }
    
    public static void a() {
        final Class<d> clazz;
        monitorenter(clazz = d.class);
        monitorexit(clazz);
    }
    
    public static void a(final Context context) {
        synchronized (d.class) {
            com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4", "");
            com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v4", "key_wxcasxx_v4", "");
        }
    }
    
    public static void a(final Context context, final c c) {
        final Class<d> clazz;
        monitorenter(clazz = d.class);
        try {
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("apdid", (Object)c.a);
                jsonObject.put("deviceInfoHash", (Object)c.b);
                jsonObject.put("timestamp", (Object)c.c);
                jsonObject.put("tid", (Object)c.d);
                jsonObject.put("utdid", (Object)c.e);
                final String string = jsonObject.toString();
                com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4", string);
                com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v4", "key_wxcasxx_v4", string);
                monitorexit(clazz);
                return;
            }
            finally {}
        }
        catch (final Exception ex) {
            com.alipay.apmobilesecuritysdk.c.a.a((Throwable)ex);
            monitorexit(clazz);
            return;
        }
        monitorexit(clazz);
    }
    
    public static c b() {
        synchronized (d.class) {
            final String a = com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v4", "key_wxcasxx_v4");
            if (com.alipay.sdk.m.z.a.a(a)) {
                return null;
            }
            return a(a);
        }
    }
    
    public static c b(final Context context) {
        synchronized (d.class) {
            String s;
            if (a.a(s = com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4"))) {
                s = com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v4", "key_wxcasxx_v4");
            }
            return a(s);
        }
    }
    
    public static c c(final Context context) {
        synchronized (d.class) {
            final String a = com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            if (com.alipay.sdk.m.z.a.a(a)) {
                return null;
            }
            return a(a);
        }
    }
}
