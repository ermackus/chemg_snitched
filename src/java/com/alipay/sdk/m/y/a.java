package com.alipay.sdk.m.y;

public final class a
{
    public static char[] a;
    public static byte[] b;
    
    static {
        com.alipay.sdk.m.y.a.a = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        com.alipay.sdk.m.y.a.b = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
    }
    
    public static byte[] a(final String s) {
        final StringBuffer sb = new StringBuffer();
        final byte[] bytes = s.getBytes("US-ASCII");
        final int length = bytes.length;
        int i = 0;
    Label_0145:
        while (i < length) {
            int n;
            byte b2;
            while (true) {
                final byte[] b = com.alipay.sdk.m.y.a.b;
                n = i + 1;
                b2 = b[bytes[i]];
                if (n >= length || b2 != -1) {
                    break;
                }
                i = n;
            }
            if (b2 == -1) {
                break;
            }
            int n2;
            byte b4;
            while (true) {
                final byte[] b3 = com.alipay.sdk.m.y.a.b;
                n2 = n + 1;
                b4 = b3[bytes[n]];
                if (n2 >= length || b4 != -1) {
                    break;
                }
                n = n2;
            }
            if (b4 == -1) {
                break;
            }
            sb.append((char)(b2 << 2 | (b4 & 0x30) >>> 4));
            int n3 = n2;
            int n4;
            byte b6;
            while (true) {
                n4 = n3 + 1;
                final byte b5 = bytes[n3];
                if (b5 == 61) {
                    break Label_0145;
                }
                b6 = com.alipay.sdk.m.y.a.b[b5];
                if (n4 >= length || b6 != -1) {
                    break;
                }
                n3 = n4;
            }
            if (b6 == -1) {
                break;
            }
            sb.append((char)((b4 & 0xF) << 4 | (b6 & 0x3C) >>> 2));
            int n5 = n4;
            while (true) {
                i = n5 + 1;
                final byte b7 = bytes[n5];
                if (b7 == 61) {
                    break Label_0145;
                }
                final byte b8 = com.alipay.sdk.m.y.a.b[b7];
                if (i < length && b8 == -1) {
                    n5 = i;
                }
                else {
                    if (b8 != -1) {
                        sb.append((char)(b8 | (b6 & 0x3) << 6));
                        break;
                    }
                    break Label_0145;
                }
            }
        }
        return sb.toString().getBytes("iso8859-1");
    }
}
