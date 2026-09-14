package com.alipay.android.phone.mrpc.core;

public final class p extends u
{
    public int c;
    public String d;
    public long e;
    public long f;
    public String g;
    public HttpUrlHeader h;
    
    public p(final HttpUrlHeader h, final int c, final String d, final byte[] a) {
        this.h = h;
        this.c = c;
        this.d = d;
        super.a = a;
    }
    
    public final HttpUrlHeader a() {
        return this.h;
    }
    
    public final void a(final long e) {
        this.e = e;
    }
    
    public final void a(final String g) {
        this.g = g;
    }
    
    public final void b(final long f) {
        this.f = f;
    }
}
