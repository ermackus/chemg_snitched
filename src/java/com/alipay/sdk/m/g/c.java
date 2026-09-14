package com.alipay.sdk.m.g;

import java.util.Arrays;
import android.util.Base64;
import java.security.SecureRandom;

public class c
{
    public static volatile SecureRandom a;
    public static final char[] b;
    
    static {
        b = "0123456789ABCDEF".toCharArray();
    }
    
    public static String a(final byte[] array) {
        return Base64.encodeToString(array, 3);
    }
    
    public static SecureRandom a() {
        if (c.a != null) {
            return c.a;
        }
        synchronized (c.class) {
            if (c.a == null) {
                c.a = new SecureRandom();
            }
            return c.a;
        }
    }
    
    public static byte[] a(final byte b) {
        return new byte[] { b };
    }
    
    public static byte[] a(final char c) {
        return new byte[] { (byte)(c & '\u00ff') };
    }
    
    public static byte[] a(final char c, final char c2) {
        return new byte[] { (byte)(c & '\u00ff'), (byte)(c2 & '\u00ff') };
    }
    
    public static byte[] a(final int n) {
        return new byte[] { (byte)n, (byte)(n >> 8), (byte)(n >> 16), (byte)(n >> 24) };
    }
    
    public static byte[] a(final long n) {
        return new byte[] { (byte)n, (byte)(n >> 8), (byte)(n >> 16), (byte)(n >> 24), (byte)(n >> 32), (byte)(n >> 40), (byte)(n >> 48), (byte)(n >> 56) };
    }
    
    public static byte[] a(final short n) {
        return new byte[] { (byte)n, (byte)(n >> 8) };
    }
    
    public static byte[] a(final byte[]... array) {
        final int length = array.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            n += array[i].length;
            ++i;
        }
        byte[] copy = null;
        final int length2 = array.length;
        int j = 0;
        int length3 = 0;
        while (j < length2) {
            final byte[] array2 = array[j];
            if (copy == null) {
                copy = Arrays.copyOf(array2, n);
                length3 = array2.length;
            }
            else {
                System.arraycopy((Object)array2, 0, (Object)copy, length3, array2.length);
                length3 += array2.length;
            }
            ++j;
        }
        return copy;
    }
    
    public static String b(final byte[] array) {
        final char[] array2 = new char[array.length * 2];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i] & 0xFF;
            final int n2 = i * 2;
            final char[] b = c.b;
            array2[n2] = b[n >>> 4];
            array2[n2 + 1] = b[n & 0xF];
        }
        return new String(array2);
    }
    
    public static byte[] b() {
        final byte[] array = new byte[2];
        a().nextBytes(array);
        return array;
    }
    
    public static byte[] c() {
        final byte[] array = new byte[4];
        a().nextBytes(array);
        return array;
    }
}
