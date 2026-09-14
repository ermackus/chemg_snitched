package com.alipay.sdk.m.c;

import com.alipay.sdk.m.d.a;
import android.content.Context;
import com.alipay.sdk.m.b.b;

public class d implements b
{
    public String a(final Context context) {
        if (context == null) {
            return null;
        }
        final boolean a = com.alipay.sdk.m.i0.b.a();
        com.alipay.sdk.m.d.a.b("getOAID", new Object[] { "isSupported", a });
        if (!a) {
            return null;
        }
        return com.alipay.sdk.m.i0.b.b(context);
    }
}
