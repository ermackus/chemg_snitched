package com.alipay.sdk.m.g0;

import com.alipay.sdk.m.f0.c;
import com.alipay.sdk.m.d0.d;
import android.content.Context;

public class b implements a
{
    public static a a;
    public static com.alipay.sdk.m.d0.a b;
    
    public static a a(final Context context, final String s) {
        if (context == null) {
            return null;
        }
        if (com.alipay.sdk.m.g0.b.a == null) {
            com.alipay.sdk.m.g0.b.b = d.a(context, s);
            com.alipay.sdk.m.g0.b.a = (a)new b();
        }
        return com.alipay.sdk.m.g0.b.a;
    }
    
    public c a(final com.alipay.sdk.m.f0.d d) {
        return com.alipay.sdk.m.f0.b.a(com.alipay.sdk.m.g0.b.b.a(com.alipay.sdk.m.f0.b.a(d)));
    }
    
    public boolean logCollect(final String s) {
        return com.alipay.sdk.m.g0.b.b.logCollect(s);
    }
}
