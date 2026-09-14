package com.alipay.sdk.m.u;

public enum g
{
    c(0, "WIFI"), 
    d(1, "unicom2G"), 
    e(2, "mobile2G"), 
    f(4, "telecom2G"), 
    g(5, "telecom3G"), 
    h(6, "telecom3G"), 
    i(12, "telecom3G"), 
    j(8, "unicom3G"), 
    k(3, "unicom3G"), 
    l(13, "LTE"), 
    m(11, "IDEN"), 
    n(9, "HSUPA"), 
    o(10, "HSPA"), 
    p(15, "HSPAP"), 
    q(20, "5G"), 
    r(-1, "none");
    
    public static final g[] s;
    public int a;
    public String b;
    
    public g(final int a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public static g a(final int n) {
        for (final g g : values()) {
            if (g.a() == n) {
                return g;
            }
        }
        return g.r;
    }
    
    public final int a() {
        return this.a;
    }
    
    public final String b() {
        return this.b;
    }
}
