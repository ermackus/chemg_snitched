package com.alibaba.mtl.log.d;

import java.io.UnsupportedEncodingException;

public class c
{
    static final boolean J;
    
    static {
        J = (c.class.desiredAssertionStatus() ^ true);
    }
    
    private c() {
    }
    
    public static byte[] decode(final byte[] array, final int n) {
        return decode(array, 0, array.length, n);
    }
    
    public static byte[] decode(byte[] array, final int n, final int n2, final int n3) {
        final c.c$b c$b = new c.c$b(n3, new byte[n2 * 3 / 4]);
        if (!c$b.process(array, n, n2, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if (c$b.op == c$b.output.length) {
            return c$b.output;
        }
        array = new byte[c$b.op];
        System.arraycopy((Object)c$b.output, 0, (Object)array, 0, c$b.op);
        return array;
    }
    
    public static byte[] encode(final byte[] array, final int n) {
        return encode(array, 0, array.length, n);
    }
    
    public static byte[] encode(final byte[] array, final int n, final int n2, int n3) {
        final c.c$c c$c = new c.c$c(n3, (byte[])null);
        final int n4 = n2 / 3 * 4;
        final boolean do_padding = c$c.do_padding;
        final int n5 = 2;
        if (do_padding) {
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
        if (c$c.do_newline) {
            n6 = n3;
            if (n2 > 0) {
                final int n7 = (n2 - 1) / 57;
                int n8;
                if (c$c.do_cr) {
                    n8 = n5;
                }
                else {
                    n8 = 1;
                }
                n6 = n3 + (n7 + 1) * n8;
            }
        }
        c$c.output = new byte[n6];
        c$c.process(array, n, n2, true);
        if (!c.J && c$c.op != n6) {
            throw new AssertionError();
        }
        return c$c.output;
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
        public int op;
        public byte[] output;
    }
}
