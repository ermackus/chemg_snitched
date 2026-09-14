package com.alibaba.sdk.android.utils;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class e
{
    private static final char[] a;
    
    static {
        a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
    
    public static String a(final String s) throws NoSuchAlgorithmException {
        return a(MessageDigest.getInstance("MD5").digest(s.getBytes()));
    }
    
    public static String a(final byte[] array) {
        final StringBuilder sb = new StringBuilder(array.length * 2);
        for (int i = 0; i < array.length; ++i) {
            sb.append(e.a[(array[i] & 0xF0) >>> 4]);
            sb.append(e.a[array[i] & 0xF]);
        }
        return sb.toString();
    }
    
    public static void a(final double n) {
        final long n2 = (long)(n * 1000.0);
        try {
            Thread.sleep(n2);
        }
        catch (final InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public static boolean a(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static String b(final String s) throws NoSuchAlgorithmException {
        return a(MessageDigest.getInstance("SHA-1").digest(s.getBytes()));
    }
}
