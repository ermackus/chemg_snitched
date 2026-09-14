package com.alibaba.sdk.android.beacon;

import java.security.Key;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import android.content.Context;

final class c
{
    private static final char[] a;
    
    static {
        a = "0123456789abcdef".toCharArray();
    }
    
    static String a(final Context context) {
        String versionName;
        final String s = versionName = "";
        if (context != null) {
            try {
                versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                if (versionName == null) {
                    versionName = s;
                }
            }
            catch (final Exception ex) {
                ex.printStackTrace();
                versionName = s;
            }
        }
        return versionName;
    }
    
    static String a(String a, final String s) {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(a.getBytes(), "HmacSHA1");
        try {
            final Mac instance = Mac.getInstance("HmacSHA1");
            instance.init((Key)secretKeySpec);
            a = a(instance.doFinal(s.getBytes()));
            return a;
        }
        finally {
            final Throwable t;
            t.printStackTrace();
            return "";
        }
    }
    
    private static String a(final byte[] array) {
        final char[] array2 = new char[array.length * 2];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i] & 0xFF;
            final int n2 = i * 2;
            final char[] a = c.a;
            array2[n2] = a[n >>> 4];
            array2[n2 + 1] = a[n & 0xF];
        }
        return new String(array2);
    }
}
