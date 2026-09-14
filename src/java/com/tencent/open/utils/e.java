package com.tencent.open.utils;

import java.security.MessageDigest;
import com.tencent.open.log.SLog;
import android.util.Base64;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class e
{
    private static final char[] a;
    
    static {
        a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    public static String a(String encodeToString, final String s, final byte[] array) {
        try {
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(array);
            final SecretKeySpec secretKeySpec = new SecretKeySpec(s.getBytes(), "AES");
            final Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(1, (Key)secretKeySpec, (AlgorithmParameterSpec)ivParameterSpec);
            encodeToString = Base64.encodeToString(instance.doFinal(encodeToString.getBytes()), 0);
            return encodeToString;
        }
        catch (final Exception ex) {
            SLog.e("DESUtils", "encryptAES", (Throwable)ex);
            return null;
        }
    }
    
    public static String a(final byte[] array) {
        if (array != null && array.length != 0) {
            final char[] array2 = new char[array.length * 2];
            for (int i = 0; i < array.length; ++i) {
                final byte b = array[i];
                final int n = i * 2;
                final char[] a = e.a;
                array2[n + 1] = a[b & 0xF];
                array2[n] = a[(byte)(b >>> 4) & 0xF];
            }
            return new String(array2);
        }
        return "";
    }
    
    public static byte[] a(final String s) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(s.getBytes());
            return instance.digest();
        }
        catch (final Exception ex) {
            SLog.e("DESUtils", "encryptSha", (Throwable)ex);
            return null;
        }
    }
}
