package com.alipay.sdk.m.l0;

import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class a
{
    public static String a(final String s) {
        byte[] a;
        try {
            a = a(a(), s.getBytes());
        }
        catch (final Exception ex) {
            a = null;
        }
        if (a != null) {
            return a(a);
        }
        return null;
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
    
    public static void a(final StringBuffer sb, final byte b) {
        sb.append("0123456789ABCDEF".charAt(b >> 4 & 0xF));
        sb.append("0123456789ABCDEF".charAt(b & 0xF));
    }
    
    public static byte[] a() throws Exception {
        return e.a(new byte[] { 33, 83, -50, -89, -84, -114, 80, 99, 10, 63, 22, -65, -11, 30, 101, -118 });
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
    
    public static byte[] a(final byte[] array, final byte[] array2) throws Exception {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, (Key)secretKeySpec, (AlgorithmParameterSpec)new IvParameterSpec(b()));
        return instance.doFinal(array2);
    }
    
    public static String b(String s) {
        try {
            s = new String(b(a(), a(s)));
            return s;
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    public static byte[] b() {
        try {
            final byte[] a = b.a("IUQSvE6r1TfFPdPEjfklLw==".getBytes("UTF-8"), 2);
            if (a != null) {
                return e.a(a);
            }
            return new byte[16];
        }
        catch (final Exception ex) {
            return new byte[16];
        }
    }
    
    public static byte[] b(final byte[] array, final byte[] array2) throws Exception {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, (Key)secretKeySpec, (AlgorithmParameterSpec)new IvParameterSpec(b()));
        return instance.doFinal(array2);
    }
}
