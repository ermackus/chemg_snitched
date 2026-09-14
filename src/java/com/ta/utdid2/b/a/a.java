package com.ta.utdid2.b.a;

import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class a
{
    public static byte[] a;
    
    static {
        com.ta.utdid2.b.a.a.a = new byte[] { 48, 48, 49, 55, 68, 67, 49, 66, 69, 50, 50, 53, 56, 53, 53, 52, 67, 70, 48, 50, 67, 53, 55, 66, 55, 56, 69, 55, 52, 48, 65, 53 };
    }
    
    public static String a(final byte[] array) {
        if (array == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(array.length * 2);
        for (int i = 0; i < array.length; ++i) {
            a(sb, array[i]);
        }
        return sb.toString();
    }
    
    private static void a(final StringBuffer sb, final byte b) {
        sb.append("0123456789ABCDEF".charAt(b >> 4 & 0xF));
        sb.append("0123456789ABCDEF".charAt(b & 0xF));
    }
    
    public static byte[] a(final String s) {
        final int n = s.length() / 2;
        final byte[] array = new byte[n];
        for (int i = 0; i < n; ++i) {
            final int n2 = i * 2;
            array[i] = Integer.valueOf(s.substring(n2, n2 + 2), 16).byteValue();
        }
        return array;
    }
    
    private static byte[] a(final byte[] array) throws Exception {
        return a(new String(com.ta.utdid2.b.a.a.a, 0, 32));
    }
    
    private static byte[] a(final byte[] array, final byte[] array2) throws Exception {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, (Key)secretKeySpec, (AlgorithmParameterSpec)new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(array2);
    }
    
    private static byte[] b(final byte[] array, final byte[] array2) throws Exception {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, (Key)secretKeySpec, (AlgorithmParameterSpec)new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(array2);
    }
    
    public static String d(final String s, final String s2) {
        byte[] a;
        try {
            a = a(a(s.getBytes()), s2.getBytes());
        }
        catch (final Exception ex) {
            a = null;
        }
        if (a != null) {
            return a(a);
        }
        return null;
    }
    
    public static String e(String s, final String s2) {
        try {
            s = new String(b(a(s.getBytes()), a(s2)));
            return s;
        }
        catch (final Exception ex) {
            return null;
        }
    }
}
