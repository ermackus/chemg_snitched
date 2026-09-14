package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;

public final class l
{
    static {
        final byte[] array2;
        final byte[] array = array2 = new byte[16];
        array2[0] = 48;
        array2[1] = 49;
        array2[2] = 50;
        array2[3] = 51;
        array2[4] = 52;
        array2[5] = 53;
        array2[6] = 54;
        array2[7] = 55;
        array2[8] = 56;
        array2[9] = 57;
        array2[10] = 65;
        array2[11] = 66;
        array2[12] = 67;
        array2[13] = 68;
        array2[14] = 69;
        array2[15] = 70;
        final byte[] array3 = new byte[256];
        final byte[] array4 = new byte[256];
        for (int i = 0; i < 256; ++i) {
            array3[i] = array[i >>> 4];
            array4[i] = array[i & 0xF];
        }
    }
    
    public static boolean a(final int n, final int n2) {
        return n == n2;
    }
    
    public static boolean a(final long n, final long n2) {
        return n == n2;
    }
    
    public static boolean a(final Object o, final Object obj) {
        return o.equals(obj);
    }
    
    public static boolean a(final boolean b, final boolean b2) {
        return b == b2;
    }
    
    public static byte[] a(final ByteBuffer byteBuffer) {
        final int position = byteBuffer.position();
        final byte[] array = new byte[position];
        System.arraycopy((Object)byteBuffer.array(), 0, (Object)array, 0, position);
        return array;
    }
}
