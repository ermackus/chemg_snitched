package com.alipay.sdk.m.n;

import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class e
{
    public static String a = "DESede/CBC/PKCS5Padding";
    
    public static String a(final String s, final String s2, final String s3) {
        String s4;
        try {
            s4 = new String(a(s, com.alipay.sdk.m.n.a.a(s2), s3));
        }
        catch (final Exception ex) {
            s4 = null;
        }
        return s4;
    }
    
    public static byte[] a(final String s, final byte[] array, final String s2) {
        byte[] doFinal;
        try {
            final SecretKeySpec secretKeySpec = new SecretKeySpec(s.getBytes(), "DESede");
            final Cipher instance = Cipher.getInstance(e.a);
            instance.init(2, (Key)secretKeySpec, (AlgorithmParameterSpec)new IvParameterSpec(c.a(instance, s2)));
            doFinal = instance.doFinal(array);
        }
        catch (final Exception ex) {
            doFinal = null;
        }
        return doFinal;
    }
    
    public static String b(String a, final String s, final String s2) {
        try {
            a = a.a(b(a, s.getBytes(), s2));
        }
        catch (final Exception ex) {
            a = null;
        }
        return a;
    }
    
    public static byte[] b(final String s, final byte[] array, final String s2) {
        byte[] doFinal;
        try {
            final SecretKeySpec secretKeySpec = new SecretKeySpec(s.getBytes(), "DESede");
            final Cipher instance = Cipher.getInstance(e.a);
            instance.init(1, (Key)secretKeySpec, (AlgorithmParameterSpec)new IvParameterSpec(c.a(instance, s2)));
            doFinal = instance.doFinal(array);
        }
        catch (final Exception ex) {
            doFinal = null;
        }
        return doFinal;
    }
}
