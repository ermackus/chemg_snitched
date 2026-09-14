package com.alipay.mobilesecuritysdk.face;

import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.sdk.m.z.a;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public class SecurityClientMobile
{
    public static String GetApdid(final Context context, final Map<String, String> map) {
        synchronized (SecurityClientMobile.class) {
            final HashMap hashMap = new HashMap();
            ((Map)hashMap).put((Object)"utdid", (Object)a.a((Map)map, "utdid", ""));
            ((Map)hashMap).put((Object)"tid", (Object)a.a((Map)map, "tid", ""));
            ((Map)hashMap).put((Object)"userId", (Object)a.a((Map)map, "userId", ""));
            APSecuritySdk.getInstance(context).initToken(0, (Map)hashMap, (APSecuritySdk.InitResultListener)null);
            return com.alipay.apmobilesecuritysdk.a.a.a(context);
        }
    }
}
