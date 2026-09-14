package com.alipay.sdk.m.b0;

import java.util.Map;
import java.util.HashMap;
import com.alipay.sdk.m.y.c;
import com.alipay.sdk.m.z.a;
import android.content.Context;

public final class d
{
    public static void a(final Context context, final String s, final String s2, final String s3) {
        synchronized (d.class) {
            if (!a.a(s) && !a.a(s2)) {
                if (context != null) {
                    try {
                        final String a = c.a(c.a(), s3);
                        final HashMap hashMap = new HashMap();
                        ((Map)hashMap).put((Object)s2, (Object)a);
                        e.a(context, s, (Map<String, String>)hashMap);
                    }
                    finally {}
                }
            }
        }
    }
}
