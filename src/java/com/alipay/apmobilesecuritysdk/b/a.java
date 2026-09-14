package com.alipay.apmobilesecuritysdk.b;

import com.alipay.sdk.m.d0.d;

public final class a
{
    public static a b;
    public int a;
    
    static {
        a.b = new a();
    }
    
    public a() {
        this.a = 0;
    }
    
    public static a a() {
        return a.b;
    }
    
    public static String a(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(s2);
        return sb.toString();
    }
    
    public final void a(final int a) {
        this.a = a;
    }
    
    public final int b() {
        return this.a;
    }
    
    public final String c() {
        final String a = d.a();
        if (com.alipay.sdk.m.z.a.b(a)) {
            return a;
        }
        final int a2 = this.a;
        String s;
        if (a2 != 1) {
            if (a2 == 2) {
                return "https://mobilegwpre.alipay.com/mgw.htm";
            }
            if (a2 != 3) {
                if (a2 != 4) {
                    return "https://mobilegw.alipay.com/mgw.htm";
                }
                s = "://mobilegw.aaa.alipay.net/mgw.htm";
            }
            else {
                s = "://mobilegw-1-64.test.alipay.net/mgw.htm";
            }
        }
        else {
            s = "://mobilegw.stable.alipay.net/mgw.htm";
        }
        return a("http", s);
    }
}
