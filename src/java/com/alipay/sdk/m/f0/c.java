package com.alipay.sdk.m.f0;

public class c extends a
{
    public static final int l = 1;
    public static final int m = 2;
    public static final int n = 3;
    public static final String o = "APPKEY_ERROR";
    public static final String p = "SUCCESS";
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    
    public c() {
        this.k = "";
    }
    
    public String a() {
        String f;
        if ((f = this.f) == null) {
            f = "0";
        }
        return f;
    }
    
    public boolean b() {
        return "1".equals((Object)this.e);
    }
    
    public int c() {
        if (super.a) {
            if (com.alipay.sdk.m.z.a.a(this.c)) {
                return 2;
            }
            return 1;
        }
        else {
            if ("APPKEY_ERROR".equals((Object)super.b)) {
                return 3;
            }
            return 2;
        }
    }
}
