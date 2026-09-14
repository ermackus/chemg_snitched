package com.alipay.apmobilesecuritysdk.d;

import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public final class a
{
    public static Map<String, String> a(final Context context, final Map<String, String> map) {
        synchronized (a.class) {
            final String a = com.alipay.sdk.m.z.a.a((Map)map, "appchannel", "");
            final HashMap hashMap = new HashMap();
            ((Map)hashMap).put((Object)"AA1", (Object)context.getPackageName());
            com.alipay.sdk.m.a0.a.a();
            ((Map)hashMap).put((Object)"AA2", (Object)com.alipay.sdk.m.a0.a.a(context));
            ((Map)hashMap).put((Object)"AA3", (Object)"APPSecuritySDK-ALIPAYSDK");
            ((Map)hashMap).put((Object)"AA4", (Object)"3.4.0.202311031119");
            ((Map)hashMap).put((Object)"AA6", (Object)a);
            return (Map<String, String>)hashMap;
        }
    }
}
