package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import org.json.JSONObject;

public final class a
{
    public static b a(final String s) {
        try {
            if (!com.alipay.sdk.m.z.a.a(s)) {
                final JSONObject jsonObject = new JSONObject(s);
                return new b(jsonObject.optString("apdid"), jsonObject.optString("deviceInfoHash"), jsonObject.optString("timestamp"));
            }
        }
        catch (final Exception ex) {
            com.alipay.apmobilesecuritysdk.c.a.a((Throwable)ex);
        }
        return null;
    }
    
    public static void a() {
        final Class<a> clazz;
        monitorenter(clazz = a.class);
        monitorexit(clazz);
    }
    
    public static void a(final Context context) {
        synchronized (a.class) {
            com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v3", "deviceid", "");
            com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v3", "wxcasxx", "");
        }
    }
    
    public static void a(final Context context, final b b) {
        final Class<a> clazz;
        monitorenter(clazz = a.class);
        try {
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("apdid", (Object)b.a);
                jsonObject.put("deviceInfoHash", (Object)b.b);
                jsonObject.put("timestamp", (Object)b.c);
                final String string = jsonObject.toString();
                com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v3", "deviceid", string);
                com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v3", "wxcasxx", string);
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
    
    public static b b() {
        synchronized (a.class) {
            final String a = com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v3", "wxcasxx");
            if (com.alipay.sdk.m.z.a.a(a)) {
                return null;
            }
            return a(a);
        }
    }
    
    public static b b(final Context context) {
        synchronized (a.class) {
            String s;
            if (com.alipay.sdk.m.z.a.a(s = com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v3", "deviceid"))) {
                s = com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v3", "wxcasxx");
            }
            return a(s);
        }
    }
    
    public static b c(final Context context) {
        synchronized (a.class) {
            final String a = com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v3", "deviceid");
            if (com.alipay.sdk.m.z.a.a(a)) {
                return null;
            }
            return a(a);
        }
    }
}
