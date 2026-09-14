package com.alipay.sdk.m.l0;

import java.io.UnsupportedEncodingException;

public class b
{
    public static final boolean a;
    
    static {
        a = (b.class.desiredAssertionStatus() ^ true);
    }
    
    public static byte[] a(final byte[] array, final int n) {
        return a(array, 0, array.length, n);
    }
    
    public static byte[] a(byte[] a, int b, final int n, final int n2) {
        final b.b$b b$b = new b.b$b(n2, new byte[n * 3 / 4]);
        if (!b$b.a(a, b, n, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        b = ((a)b$b).b;
        a = ((a)b$b).a;
        if (b == a.length) {
            return a;
        }
        final byte[] array = new byte[b];
        System.arraycopy((Object)a, 0, (Object)array, 0, b);
        return array;
    }
    
    public static byte[] b(final byte[] array, final int n) {
        return b(array, 0, array.length, n);
    }
    
    public static byte[] b(final byte[] array, final int n, final int n2, int n3) {
        final b.b$c b$c = new b.b$c(n3, (byte[])null);
        final int n4 = n2 / 3 * 4;
        final boolean f = b$c.f;
        final int n5 = 2;
        if (f) {
            n3 = n4;
            if (n2 % 3 > 0) {
                n3 = n4 + 4;
            }
        }
        else {
            n3 = n2 % 3;
            if (n3 != 1) {
                if (n3 != 2) {
                    n3 = n4;
                }
                else {
                    n3 = n4 + 3;
                }
            }
            else {
                n3 = n4 + 2;
            }
        }
        int n6 = n3;
        if (b$c.g) {
            n6 = n3;
            if (n2 > 0) {
                final int n7 = (n2 - 1) / 57;
                int n8;
                if (b$c.h) {
                    n8 = n5;
                }
                else {
                    n8 = 1;
                }
                n6 = n3 + (n7 + 1) * n8;
            }
        }
        ((a)b$c).a = new byte[n6];
        b$c.a(array, n, n2, true);
        if (!b.a && ((a)b$c).b != n6) {
            throw new AssertionError();
        }
        return ((a)b$c).a;
    }
    
    public static String c(final byte[] array, final int n) {
        try {
            return new String(b(array, n), "US-ASCII");
        }
        catch (final UnsupportedEncodingException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    public abstract static class a
    {
        public byte[] a;
        public int b;
    }
}
