package com.alipay.sdk.m.y;

import java.util.Arrays;
import java.lang.reflect.Method;
import javax.crypto.spec.PBEKeySpec;
import java.nio.ByteBuffer;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;

public final class c
{
    public static String a = "idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#";
    
    public static String a() {
        String string = new String();
        for (int i = 0; i < c.a.length() - 1; i += 4) {
            final StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(c.a.charAt(i));
            string = sb.toString();
        }
        return string;
    }
    
    public static String a(String a, final String s) {
        try {
            final PBEKeySpec a2 = a(a);
            final byte[] bytes = s.getBytes();
            final byte[] b = b();
            final SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret((KeySpec)a2).getEncoded(), "AES");
            final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, (Key)secretKeySpec, (AlgorithmParameterSpec)new IvParameterSpec(b));
            final byte[] salt = a2.getSalt();
            final ByteBuffer allocate = ByteBuffer.allocate(salt.length + instance.getOutputSize(bytes.length));
            allocate.put(salt);
            instance.doFinal(ByteBuffer.wrap(bytes), allocate);
            a = a(allocate.array());
            return a;
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    public static String a(final byte[] array) {
        if (array == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(array.length * 2);
        for (int i = 0; i < array.length; ++i) {
            final byte b = array[i];
            sb.append("0123456789ABCDEF".charAt(b >> 4 & 0xF));
            sb.append("0123456789ABCDEF".charAt(b & 0xF));
        }
        return sb.toString();
    }
    
    public static PBEKeySpec a(final String s) {
        final Class<?> forName = Class.forName(new String(com.alipay.sdk.m.y.a.a("amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20=")));
        final Object instance = forName.newInstance();
        final byte[] array = new byte[16];
        final Method method = forName.getMethod("nextBytes", array.getClass());
        method.setAccessible(true);
        method.invoke(instance, new Object[] { array });
        return new PBEKeySpec(s.toCharArray(), array, 10, 128);
    }
    
    public static String b(final String s, final String s2) {
        try {
            final PBEKeySpec a = a(s);
            final int n = s2.length() / 2;
            final byte[] array = new byte[n];
            for (int i = 0; i < n; ++i) {
                final int n2 = i * 2;
                array[i] = Integer.valueOf(s2.substring(n2, n2 + 2), 16).byteValue();
            }
            final byte[] b = b();
            byte[] doFinal;
            if (n <= 16) {
                doFinal = null;
            }
            else {
                final SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret((KeySpec)new PBEKeySpec(a.getPassword(), Arrays.copyOf(array, 16), 10, 128)).getEncoded(), "AES");
                final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, (Key)secretKeySpec, (AlgorithmParameterSpec)new IvParameterSpec(b));
                doFinal = instance.doFinal(array, 16, n - 16);
            }
            if (doFinal == null) {
                throw new Exception();
            }
            final String s3 = new String(doFinal);
            if (com.alipay.sdk.m.z.a.c(s3)) {
                return s3;
            }
            return null;
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    public static byte[] b() {
        try {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 48; i += 2) {
                sb.append("AsAgAtA5A6AdAgABABACADAfAsAdAfAsAgAaAgA3A5A6=8=0".charAt(i));
            }
            return com.alipay.sdk.m.y.a.a(sb.toString());
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
