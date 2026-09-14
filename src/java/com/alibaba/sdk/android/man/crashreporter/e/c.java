package com.alibaba.sdk.android.man.crashreporter.e;

public class c
{
    public static byte[] d(int n) {
        final byte b = (byte)(n % 256);
        n >>= 8;
        final byte b2 = (byte)(n % 256);
        n >>= 8;
        return new byte[] { (byte)((n >> 8) % 256), (byte)(n % 256), b2, b };
    }
    
    public static byte[] e(final int n) {
        return new byte[] { (byte)((n >> 8) % 256), (byte)(n % 256) };
    }
}
