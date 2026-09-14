package com.alipay.sdk.m.h;

import com.alipay.sdk.m.g.c;

public class a extends com.alipay.sdk.m.g.a
{
    public static final boolean d;
    
    static {
        d = (a.class.desiredAssertionStatus() ^ true);
    }
    
    public a(final byte[] array) {
        super(array);
    }
    
    public static a a(final String s, final long n, final b b, final short n2, final e e) throws Exception {
        final byte[] a = c.a((byte)1);
        if (!com.alipay.sdk.m.h.a.d && a.length != 1) {
            throw new AssertionError();
        }
        final byte[] a2 = c.a(s.charAt(0), s.charAt(1));
        if (!com.alipay.sdk.m.h.a.d && a2.length != 2) {
            throw new AssertionError();
        }
        final byte[] a3 = c.a(n);
        if (!com.alipay.sdk.m.h.a.d && a3.length != 8) {
            throw new AssertionError();
        }
        final byte[] b2 = c.b();
        if (!com.alipay.sdk.m.h.a.d && b2.length != 2) {
            throw new AssertionError();
        }
        b.a();
        final byte[] a4 = c.a(b.a);
        if (!com.alipay.sdk.m.h.a.d && a4.length != 1) {
            throw new AssertionError();
        }
        final byte[] a5 = c.a(b.b);
        if (!com.alipay.sdk.m.h.a.d && a5.length != 1) {
            throw new AssertionError();
        }
        final byte[] array = b.c.clone();
        if (!com.alipay.sdk.m.h.a.d && array.length != (b.b & 0xFF)) {
            throw new AssertionError();
        }
        final byte[] a6 = c.a(n2);
        if (!com.alipay.sdk.m.h.a.d && a6.length != 2) {
            throw new AssertionError();
        }
        final byte[] b3 = c.b();
        if (!com.alipay.sdk.m.h.a.d && b3.length != 2) {
            throw new AssertionError();
        }
        e.a();
        final byte[] a7 = c.a(e.a);
        if (!com.alipay.sdk.m.h.a.d && a7.length != 1) {
            throw new AssertionError();
        }
        final byte[] array2 = e.b.clone();
        if (!com.alipay.sdk.m.h.a.d && array2.length != (e.a & 0xFF)) {
            throw new AssertionError();
        }
        final byte[] c = com.alipay.sdk.m.g.c.c();
        if (!com.alipay.sdk.m.h.a.d && c.length != 4) {
            throw new AssertionError();
        }
        return new a(com.alipay.sdk.m.g.c.a(new byte[][] { a, a2, a3, b2, a4, a5, array, a6, b3, a7, array2, c }));
    }
    
    public static a c() {
        try {
            return a("EX", 0L, new com.alipay.sdk.m.h.c(""), (short)0, new f());
        }
        catch (final Exception ex) {
            return null;
        }
    }
}
