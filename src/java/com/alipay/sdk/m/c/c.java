package com.alipay.sdk.m.c;

import com.alipay.sdk.m.d.a;
import android.content.Context;
import com.alipay.sdk.m.b.b;

public class c implements b
{
    public static final int d = 1;
    public com.alipay.sdk.m.r0.b a;
    public boolean b;
    public boolean c;
    
    public c() {
        this.b = false;
        this.c = false;
    }
    
    public String a(final Context context) {
        if (context == null) {
            return null;
        }
        if (!this.b) {
            final com.alipay.sdk.m.r0.b a = new com.alipay.sdk.m.r0.b();
            this.a = a;
            this.c = (a.a(context, null) == 1);
            this.b = true;
        }
        com.alipay.sdk.m.d.a.b("getOAID", new Object[] { "isSupported", this.c });
        if (this.c && this.a.e()) {
            return this.a.b();
        }
        return null;
    }
}
