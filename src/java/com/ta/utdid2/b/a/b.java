package com.ta.utdid2.b.a;

import java.io.UnsupportedEncodingException;

public class b
{
    static final boolean a;
    
    static {
        a = (b.class.desiredAssertionStatus() ^ true);
    }
    
    private b() {
    }
    
    public static byte[] decode(final String s, final int n) {
        return decode(s.getBytes(), n);
    }
    
    public static byte[] decode(final byte[] array, final int n) {
        return decode(array, 0, array.length, n);
    }
    
    public static byte[] decode(byte[] array, final int n, final int n2, final int n3) {
        final b.b$b b$b = new b.b$b(n3, new byte[n2 * 3 / 4]);
        if (!b$b.a(array, n, n2, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if (b$b.a == b$b.b.length) {
            return b$b.b;
        }
        array = new byte[b$b.a];
        System.arraycopy((Object)b$b.b, 0, (Object)array, 0, b$b.a);
        return array;
    }
    
    public static byte[] encode(final byte[] array, final int n) {
        return encode(array, 0, array.length, n);
    }
    
    public static byte[] encode(final byte[] array, final int n, final int n2, int n3) {
        final b.b$c b$c = new b.b$c(n3, (byte[])null);
        final int n4 = n2 / 3 * 4;
        final boolean b = b$c.b;
        final int n5 = 2;
        if (b) {
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
        if (b$c.c) {
            n6 = n3;
            if (n2 > 0) {
                final int n7 = (n2 - 1) / 57;
                int n8;
                if (b$c.d) {
                    n8 = n5;
                }
                else {
                    n8 = 1;
                }
                n6 = n3 + (n7 + 1) * n8;
            }
        }
        b$c.b = new byte[n6];
        b$c.a(array, n, n2, true);
        if (!com.ta.utdid2.b.a.b.a && b$c.a != n6) {
            throw new AssertionError();
        }
        return b$c.b;
    }
    
    public static String encodeToString(final byte[] array, final int n) {
        try {
            return new String(encode(array, n), "US-ASCII");
        }
        catch (final UnsupportedEncodingException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    abstract static class a
    {
        public int a;
        public byte[] b;
    }
}
