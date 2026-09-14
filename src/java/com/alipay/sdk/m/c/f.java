package com.alipay.sdk.m.c;

import com.alipay.sdk.m.h0.a;
import android.content.Context;
import com.alipay.sdk.m.b.b;

public class f implements b
{
    public boolean a;
    
    public f() {
        this.a = false;
    }
    
    public String a(final Context context) {
        if (context == null) {
            return null;
        }
        if (!this.a) {
            com.alipay.sdk.m.h0.a.e(context);
            this.a = true;
        }
        final boolean a = com.alipay.sdk.m.h0.a.a();
        com.alipay.sdk.m.d.a.b("getOAID", new Object[] { "isSupported", a });
        if (!a) {
            return null;
        }
        return com.alipay.sdk.m.h0.a.b(context);
    }
}
