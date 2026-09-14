package com.goodsrc.library.utils;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;

public class AES
{
    private static final Charset CHARSET_UTF8;
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
    
    static {
        CHARSET_UTF8 = StandardCharsets.UTF_8;
    }
    
    public static String decrypt(String s, final String s2) {
        try {
            s = new String(decrypt(s, Base64Decoder.decodeToBytes(s2)), AES.CHARSET_UTF8);
            return s;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    private static byte[] decrypt(final String s, final byte[] array) throws Exception {
        final Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
        instance.init(2, (Key)new SecretKeySpec(s.getBytes(AES.CHARSET_UTF8), "AES"));
        return instance.doFinal(array);
    }
    
    public static String encrypt(String encode, final String s) {
        try {
            encode = Base64Encoder.encode(encrypt(encode, s.getBytes(AES.CHARSET_UTF8)));
            return encode;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    private static byte[] encrypt(final String s, final byte[] array) throws Exception {
        final Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
        instance.init(1, (Key)new SecretKeySpec(s.getBytes(AES.CHARSET_UTF8), "AES"));
        return instance.doFinal(array);
    }
    
    private static void merge(final char[] array, final int n, int n2, final int n3, final char[] array2) {
        int n4 = n2 + 1;
        final int n5 = 0;
        int n6 = n;
        int n7 = 0;
        int n8;
        int n9;
        while (true) {
            n8 = n6;
            n9 = n7;
            if (n6 > n2) {
                break;
            }
            n8 = n6;
            n9 = n7;
            if (n4 > n3) {
                break;
            }
            if (array[n6] <= array[n4]) {
                array2[n7] = array[n6];
                ++n7;
                ++n6;
            }
            else {
                array2[n7] = array[n4];
                ++n7;
                ++n4;
            }
        }
        int n10;
        int n11;
        while (true) {
            n10 = n4;
            n11 = n9;
            if (n8 > n2) {
                break;
            }
            array2[n9] = array[n8];
            ++n9;
            ++n8;
        }
        int i;
        while (true) {
            n2 = n5;
            i = n;
            if (n10 > n3) {
                break;
            }
            array2[n11] = array[n10];
            ++n11;
            ++n10;
        }
        while (i <= n3) {
            array[i] = array2[n2];
            ++i;
            ++n2;
        }
    }
    
    private static void sort(final char[] array) {
        sort(array, 0, array.length - 1, new char[array.length]);
    }
    
    private static void sort(final char[] array, final int n, final int n2, final char[] array2) {
        if (n < n2) {
            final int n3 = (n + n2) / 2;
            sort(array, n, n3, array2);
            sort(array, n3 + 1, n2, array2);
            merge(array, n, n3, n2, array2);
        }
    }
    
    public static String sortByACS(final String s) {
        final char[] charArray = s.toCharArray();
        sort(charArray);
        final StringBuilder sb = new StringBuilder();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            sb.append(charArray[i]);
        }
        return sb.toString();
    }
}
