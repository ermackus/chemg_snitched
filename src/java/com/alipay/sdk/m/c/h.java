package com.alipay.sdk.m.c;

import com.alipay.sdk.m.d.a;
import com.alipay.sdk.m.p0.e;
import android.content.Context;
import com.alipay.sdk.m.b.b;

public class h implements b
{
    public String a(final Context context) {
        if (context == null) {
            return null;
        }
        final boolean c = e.c(context);
        a.b("getOAID", new Object[] { "isSupported", c });
        if (!c) {
            return null;
        }
        return e.a(context);
    }
}
