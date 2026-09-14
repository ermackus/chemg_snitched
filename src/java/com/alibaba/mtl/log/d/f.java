package com.alibaba.mtl.log.d;

public class f
{
    public static byte[] getBytes(int n) {
        final byte b = (byte)(n % 256);
        n >>= 8;
        final byte b2 = (byte)(n % 256);
        n >>= 8;
        return new byte[] { (byte)((n >> 8) % 256), (byte)(n % 256), b2, b };
    }
}
