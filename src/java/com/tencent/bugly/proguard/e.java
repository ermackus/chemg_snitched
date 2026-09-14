package com.tencent.bugly.proguard;

public final class e
{
    private static final char[] a;
    
    static {
        a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
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
        return null;
    }
}
