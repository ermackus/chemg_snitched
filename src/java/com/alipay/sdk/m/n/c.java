package com.alipay.sdk.m.n;

import android.text.TextUtils;
import java.security.SecureRandom;
import javax.crypto.Cipher;

public class c
{
    public static byte[] a(final Cipher cipher, final String s) {
        final SecureRandom secureRandom = new SecureRandom();
        final int blockSize = cipher.getBlockSize();
        String value = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            value = String.valueOf(secureRandom.nextDouble());
        }
        final int n = blockSize * 2;
        final byte[] array = new byte[n];
        final byte[] array2 = new byte[blockSize];
        secureRandom.nextBytes(array2);
        for (int i = 1; i < n; ++i) {
            array[i] = (byte)(value.codePointAt(i % value.length()) & 0x7F);
            if (i >= blockSize) {
                array[i] &= array[0];
            }
        }
        System.arraycopy((Object)array, blockSize, (Object)array2, 0, blockSize);
        return array2;
    }
}
