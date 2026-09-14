package com.alipay.apmobilesecuritysdk.e;

import org.json.JSONObject;
import com.alipay.apmobilesecuritysdk.f.a;
import android.content.Context;

public final class e
{
    public static f a(final Context context) {
        if (context == null) {
            return null;
        }
        String s;
        if (com.alipay.sdk.m.z.a.a(s = a.a(context, "device_feature_prefs_name", "device_feature_prefs_key"))) {
            s = a.a("device_feature_file_name", "device_feature_file_key");
        }
        if (com.alipay.sdk.m.z.a.a(s)) {
            return null;
        }
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final f f = new f();
            f.a(jsonObject.getString("imei"));
            f.b(jsonObject.getString("imsi"));
            f.c(jsonObject.getString("mac"));
            f.d(jsonObject.getString("bluetoothmac"));
            f.e(jsonObject.getString("gsi"));
            return f;
        }
        catch (final Exception ex) {
            com.alipay.apmobilesecuritysdk.c.a.a((Throwable)ex);
            return null;
        }
    }
}
