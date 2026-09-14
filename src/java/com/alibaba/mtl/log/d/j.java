package com.alibaba.mtl.log.d;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class j
{
    public static char[] a;
    
    static {
        j.a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
    
    public static String a(final byte[] array) {
        final StringBuilder sb = new StringBuilder(array.length * 2);
        for (int i = 0; i < array.length; ++i) {
            sb.append(j.a[(array[i] & 0xF0) >>> 4]);
            sb.append(j.a[array[i] & 0xF]);
        }
        return sb.toString();
    }
    
    public static byte[] a(byte[] digest) {
        if (digest != null) {
            try {
                final MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(digest);
                digest = instance.digest();
                return digest;
            }
            catch (final NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    public static String b(byte[] a) {
        a = a(a);
        if (a != null) {
            return a(a);
        }
        return "0000000000000000";
    }
}
