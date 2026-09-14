package com.alipay.apmobilesecuritysdk.d;

import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.sdk.m.z.a;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public final class b
{
    public static Map<String, String> a(final Context context, final Map<String, String> map) {
        synchronized (b.class) {
            final HashMap hashMap = new HashMap();
            final String a = com.alipay.sdk.m.z.a.a((Map)map, "tid", "");
            final String a2 = com.alipay.sdk.m.z.a.a((Map)map, "utdid", "");
            final String a3 = com.alipay.sdk.m.z.a.a((Map)map, "userId", "");
            final String a4 = com.alipay.sdk.m.z.a.a((Map)map, "appName", "");
            final String a5 = com.alipay.sdk.m.z.a.a((Map)map, "appKeyClient", "");
            final String a6 = com.alipay.sdk.m.z.a.a((Map)map, "tmxSessionId", "");
            final String f = h.f(context);
            final String a7 = com.alipay.sdk.m.z.a.a((Map)map, "sessionId", "");
            ((Map)hashMap).put((Object)"AC1", (Object)a);
            ((Map)hashMap).put((Object)"AC2", (Object)a2);
            ((Map)hashMap).put((Object)"AC3", (Object)"");
            ((Map)hashMap).put((Object)"AC4", (Object)f);
            ((Map)hashMap).put((Object)"AC5", (Object)a3);
            ((Map)hashMap).put((Object)"AC6", (Object)a6);
            ((Map)hashMap).put((Object)"AC7", (Object)"");
            ((Map)hashMap).put((Object)"AC8", (Object)a4);
            ((Map)hashMap).put((Object)"AC9", (Object)a5);
            if (com.alipay.sdk.m.z.a.b(a7)) {
                ((Map)hashMap).put((Object)"AC10", (Object)a7);
            }
            return (Map<String, String>)hashMap;
        }
    }
}
