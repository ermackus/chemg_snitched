package com.alipay.sdk.m.r;

import android.text.TextUtils;

public enum a
{
    b("none"), 
    c("js://wappay"), 
    d("js://update"), 
    e("loc:openweb"), 
    f("loc:setResult"), 
    g("loc:exit");
    
    public static final a[] h;
    public String a;
    
    public a(final String a) {
        this.a = a;
    }
    
    public static a a(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return a.b;
        }
        final a b = a.b;
        final a[] values = values();
        final int length = values.length;
        int n = 0;
        a a;
        while (true) {
            a = b;
            if (n >= length) {
                break;
            }
            a = values[n];
            if (s.startsWith(a.a)) {
                break;
            }
            ++n;
        }
        return a;
    }
}
