package com.alipay.apmobilesecuritysdk.d;

import org.json.JSONObject;
import com.alipay.sdk.m.z.a;
import com.alipay.apmobilesecuritysdk.e.e;
import java.util.HashMap;
import com.alipay.sdk.m.a0.f;
import com.alipay.sdk.m.a0.b;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import java.util.Map;
import android.content.Context;

public final class c
{
    public static Map<String, String> a(final Context context) {
        final b a = b.a((f)APSecuritySdk.getInstance(context));
        final HashMap hashMap = new HashMap();
        final com.alipay.apmobilesecuritysdk.e.f a2 = e.a(context);
        final String a3 = a.a(context);
        final String d = a.d(context);
        String s = a3;
        String e = d;
        if (a2 != null) {
            String b = a3;
            if (com.alipay.sdk.m.z.a.a(a3)) {
                b = a2.b();
            }
            s = b;
            e = d;
            if (com.alipay.sdk.m.z.a.a(d)) {
                e = a2.e();
                s = b;
            }
        }
        final com.alipay.apmobilesecuritysdk.e.f f = new com.alipay.apmobilesecuritysdk.e.f("", s, "", "", e);
        if (context != null) {
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("imei", (Object)f.a());
                jsonObject.put("imsi", (Object)f.b());
                jsonObject.put("mac", (Object)f.c());
                jsonObject.put("bluetoothmac", (Object)f.d());
                jsonObject.put("gsi", (Object)f.e());
                final String string = jsonObject.toString();
                com.alipay.apmobilesecuritysdk.f.a.a("device_feature_file_name", "device_feature_file_key", string);
                com.alipay.apmobilesecuritysdk.f.a.a(context, "device_feature_prefs_name", "device_feature_prefs_key", string);
            }
            catch (final Exception ex) {
                com.alipay.apmobilesecuritysdk.c.a.a((Throwable)ex);
            }
        }
        ((Map)hashMap).put((Object)"AD1", (Object)"");
        ((Map)hashMap).put((Object)"AD2", (Object)s);
        ((Map)hashMap).put((Object)"AD3", (Object)b.h(context));
        ((Map)hashMap).put((Object)"AD5", (Object)b.j(context));
        ((Map)hashMap).put((Object)"AD6", (Object)b.k(context));
        ((Map)hashMap).put((Object)"AD7", (Object)b.l(context));
        ((Map)hashMap).put((Object)"AD9", (Object)a.c(context));
        ((Map)hashMap).put((Object)"AD10", (Object)e);
        ((Map)hashMap).put((Object)"AD11", (Object)b.d());
        ((Map)hashMap).put((Object)"AD12", (Object)a.a());
        ((Map)hashMap).put((Object)"AD13", (Object)b.e());
        ((Map)hashMap).put((Object)"AD14", (Object)b.g());
        ((Map)hashMap).put((Object)"AD15", (Object)b.h());
        ((Map)hashMap).put((Object)"AD16", (Object)b.i());
        ((Map)hashMap).put((Object)"AD17", (Object)"");
        ((Map)hashMap).put((Object)"AD19", (Object)b.m(context));
        ((Map)hashMap).put((Object)"AD20", (Object)b.j());
        ((Map)hashMap).put((Object)"AD22", (Object)"");
        ((Map)hashMap).put((Object)"AD24", (Object)com.alipay.sdk.m.z.a.g(b.i(context)));
        ((Map)hashMap).put((Object)"AD26", (Object)a.b(context));
        ((Map)hashMap).put((Object)"AD27", (Object)b.o());
        ((Map)hashMap).put((Object)"AD28", (Object)b.q());
        ((Map)hashMap).put((Object)"AD29", (Object)b.s());
        ((Map)hashMap).put((Object)"AD30", (Object)b.p());
        ((Map)hashMap).put((Object)"AD31", (Object)b.r());
        ((Map)hashMap).put((Object)"AD32", (Object)b.m());
        ((Map)hashMap).put((Object)"AD33", (Object)b.n());
        ((Map)hashMap).put((Object)"AD34", (Object)b.n(context));
        ((Map)hashMap).put((Object)"AD35", (Object)b.o(context));
        ((Map)hashMap).put((Object)"AD36", (Object)a.e(context));
        ((Map)hashMap).put((Object)"AD37", (Object)b.l());
        ((Map)hashMap).put((Object)"AD38", (Object)b.k());
        ((Map)hashMap).put((Object)"AD39", (Object)b.f(context));
        ((Map)hashMap).put((Object)"AD40", (Object)b.g(context));
        ((Map)hashMap).put((Object)"AD41", (Object)b.b());
        ((Map)hashMap).put((Object)"AD42", (Object)b.c());
        return (Map<String, String>)hashMap;
    }
}
