package com.goodsrc.library.utils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.spec.KeySpec;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import android.util.Base64;

public class Des
{
    public static final String ALGORITHM_DES = "DES/ECB/PKCS7Padding";
    
    public static String decode(final String s, final String s2) {
        return decode(s, Base64.decode(s2, 0));
    }
    
    public static String decode(String s, final byte[] array) {
        try {
            final Cipher instance = Cipher.getInstance("DES/ECB/PKCS7Padding");
            instance.init(2, getRawKey(s), new SecureRandom());
            s = new String(instance.doFinal(array));
            return s;
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    public static String encode(final String s) {
        return null;
    }
    
    public static String encode(String encodeToString, final String s) {
        try {
            final SecureRandom secureRandom = new SecureRandom();
            final SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret((KeySpec)new DESKeySpec(encodeToString.getBytes()));
            final Cipher instance = Cipher.getInstance("DES/ECB/PKCS7Padding");
            instance.init(1, (Key)generateSecret, secureRandom);
            encodeToString = Base64.encodeToString(instance.doFinal(s.getBytes()), 0);
            return encodeToString;
        }
        finally {
            final Throwable t;
            t.printStackTrace();
            return null;
        }
    }
    
    private static Key getRawKey(final String s) throws Exception {
        return (Key)SecretKeyFactory.getInstance("DES").generateSecret((KeySpec)new DESKeySpec(s.getBytes()));
    }
}
