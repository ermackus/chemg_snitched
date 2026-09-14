package com.kingagroot.component.ui.utils;

import java.security.MessageDigest;

public class MD5
{
    public static final String TAG = "MD5";
    
    public static String convertMD5(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] ^= 't';
        }
        return new String(charArray);
    }
    
    public static String string2MD5(final String s) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            final char[] charArray = s.toCharArray();
            final byte[] array = new byte[charArray.length];
            final int n = 0;
            for (int i = 0; i < charArray.length; ++i) {
                array[i] = (byte)charArray[i];
            }
            final byte[] digest = instance.digest(array);
            final StringBuilder sb = new StringBuilder();
            for (int length = digest.length, j = n; j < length; ++j) {
                final int n2 = digest[j] & 0xFF;
                if (n2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(n2));
            }
            return sb.toString();
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
