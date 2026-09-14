package com.alipay.sdk.m.n;

public final class a
{
    public static final int a = 128;
    public static final int b = 64;
    public static final int c = 24;
    public static final int d = 8;
    public static final int e = 16;
    public static final int f = 4;
    public static final int g = -128;
    public static final char h = '=';
    public static final byte[] i;
    public static final char[] j;
    
    static {
        i = new byte[128];
        j = new char[64];
        final int n = 0;
        for (int k = 0; k < 128; ++k) {
            com.alipay.sdk.m.n.a.i[k] = -1;
        }
        for (int l = 90; l >= 65; --l) {
            com.alipay.sdk.m.n.a.i[l] = (byte)(l - 65);
        }
        int n2 = 122;
        int n3;
        while (true) {
            n3 = 26;
            if (n2 < 97) {
                break;
            }
            com.alipay.sdk.m.n.a.i[n2] = (byte)(n2 - 97 + 26);
            --n2;
        }
        int n4 = 57;
        int n5;
        while (true) {
            n5 = 52;
            if (n4 < 48) {
                break;
            }
            com.alipay.sdk.m.n.a.i[n4] = (byte)(n4 - 48 + 52);
            --n4;
        }
        final byte[] m = com.alipay.sdk.m.n.a.i;
        m[43] = 62;
        m[47] = 63;
        for (int n6 = 0; n6 <= 25; ++n6) {
            com.alipay.sdk.m.n.a.j[n6] = (char)(n6 + 65);
        }
        int n7 = 0;
        int n8;
        int n9;
        while (true) {
            n8 = n;
            n9 = n5;
            if (n3 > 51) {
                break;
            }
            com.alipay.sdk.m.n.a.j[n3] = (char)(n7 + 97);
            ++n3;
            ++n7;
        }
        while (n9 <= 61) {
            com.alipay.sdk.m.n.a.j[n9] = (char)(n8 + 48);
            ++n9;
            ++n8;
        }
        final char[] j2 = com.alipay.sdk.m.n.a.j;
        j2[62] = '+';
        j2[63] = '/';
    }
    
    public static int a(final char[] array) {
        int i = 0;
        if (array == null) {
            return 0;
        }
        final int length = array.length;
        int n = 0;
        while (i < length) {
            int n2 = n;
            if (!c(array[i])) {
                array[n] = array[i];
                n2 = n + 1;
            }
            ++i;
            n = n2;
        }
        return n;
    }
    
    public static String a(final byte[] array) {
        if (array == null) {
            return null;
        }
        final int n = array.length * 8;
        if (n == 0) {
            return "";
        }
        final int n2 = n % 24;
        final int n3 = n / 24;
        int n4;
        if (n2 != 0) {
            n4 = n3 + 1;
        }
        else {
            n4 = n3;
        }
        final char[] array2 = new char[n4 * 4];
        int i = 0;
        int n5 = 0;
        int n6 = 0;
        while (i < n3) {
            final int n7 = n5 + 1;
            final byte b = array[n5];
            final int n8 = n7 + 1;
            final byte b2 = array[n7];
            final byte b3 = array[n8];
            final byte b4 = (byte)(b2 & 0xF);
            final byte b5 = (byte)(b & 0x3);
            int n9 = b >> 2;
            if ((b & 0xFFFFFF80) != 0x0) {
                n9 ^= 0xC0;
            }
            final byte b6 = (byte)n9;
            int n10 = b2 >> 4;
            if ((b2 & 0xFFFFFF80) != 0x0) {
                n10 ^= 0xF0;
            }
            final byte b7 = (byte)n10;
            int n11;
            if ((b3 & 0xFFFFFF80) == 0x0) {
                n11 = b3 >> 6;
            }
            else {
                n11 = (b3 >> 6 ^ 0xFC);
            }
            final byte b8 = (byte)n11;
            final int n12 = n6 + 1;
            final char[] j = com.alipay.sdk.m.n.a.j;
            array2[n6] = j[b6];
            final int n13 = n12 + 1;
            array2[n12] = j[b5 << 4 | b7];
            final int n14 = n13 + 1;
            array2[n13] = j[b4 << 2 | b8];
            array2[n14] = j[b3 & 0x3F];
            ++i;
            n6 = n14 + 1;
            n5 = n8 + 1;
        }
        if (n2 == 8) {
            final byte b9 = array[n5];
            final byte b10 = (byte)(b9 & 0x3);
            int n15 = b9 >> 2;
            if ((b9 & 0xFFFFFF80) != 0x0) {
                n15 ^= 0xC0;
            }
            final byte b11 = (byte)n15;
            final int n16 = n6 + 1;
            final char[] k = com.alipay.sdk.m.n.a.j;
            array2[n6] = k[b11];
            final int n17 = n16 + 1;
            array2[n16] = k[b10 << 4];
            array2[n17 + 1] = (array2[n17] = '=');
        }
        else if (n2 == 16) {
            final byte b12 = array[n5];
            final byte b13 = array[n5 + 1];
            final byte b14 = (byte)(b13 & 0xF);
            final byte b15 = (byte)(b12 & 0x3);
            int n18 = b12 >> 2;
            if ((b12 & 0xFFFFFF80) != 0x0) {
                n18 ^= 0xC0;
            }
            final byte b16 = (byte)n18;
            int n19 = b13 >> 4;
            if ((b13 & 0xFFFFFF80) != 0x0) {
                n19 ^= 0xF0;
            }
            final byte b17 = (byte)n19;
            final int n20 = n6 + 1;
            final char[] l = com.alipay.sdk.m.n.a.j;
            array2[n6] = l[b16];
            final int n21 = n20 + 1;
            array2[n20] = l[b17 | b15 << 4];
            array2[n21] = l[b14 << 2];
            array2[n21 + 1] = '=';
        }
        return new String(array2);
    }
    
    public static boolean a(final char c) {
        return c < '\u0080' && com.alipay.sdk.m.n.a.i[c] != -1;
    }
    
    public static byte[] a(final String s) {
        final Object o = null;
        if (s == null) {
            return null;
        }
        final char[] charArray = s.toCharArray();
        final int a = a(charArray);
        if (a % 4 != 0) {
            return null;
        }
        final int n = a / 4;
        if (n == 0) {
            return new byte[0];
        }
        final byte[] array = new byte[n * 3];
        int i = 0;
        int n2 = 0;
        int n3 = 0;
        while (i < n - 1) {
            final int n4 = n2 + 1;
            final char c = charArray[n2];
            if (a(c)) {
                final int n5 = n4 + 1;
                final char c2 = charArray[n4];
                if (a(c2)) {
                    n2 = n5 + 1;
                    final char c3 = charArray[n5];
                    if (a(c3)) {
                        final char c4 = charArray[n2];
                        if (a(c4)) {
                            final byte[] j = com.alipay.sdk.m.n.a.i;
                            final byte b = j[c];
                            final byte b2 = j[c2];
                            final byte b3 = j[c3];
                            final byte b4 = j[c4];
                            final int n6 = n3 + 1;
                            array[n3] = (byte)(b << 2 | b2 >> 4);
                            final int n7 = n6 + 1;
                            array[n6] = (byte)((b2 & 0xF) << 4 | (b3 >> 2 & 0xF));
                            n3 = n7 + 1;
                            array[n7] = (byte)(b3 << 6 | b4);
                            ++i;
                            ++n2;
                            continue;
                        }
                    }
                }
            }
            return null;
        }
        final int n8 = n2 + 1;
        final char c5 = charArray[n2];
        Object o2 = o;
        if (a(c5)) {
            final int n9 = n8 + 1;
            final char c6 = charArray[n8];
            if (!a(c6)) {
                o2 = o;
            }
            else {
                final byte[] k = com.alipay.sdk.m.n.a.i;
                final byte b5 = k[c5];
                final byte b6 = k[c6];
                final char c7 = charArray[n9];
                final char c8 = charArray[n9 + 1];
                if (a(c7) && a(c8)) {
                    final byte[] l = com.alipay.sdk.m.n.a.i;
                    final byte b7 = l[c7];
                    final byte b8 = l[c8];
                    final int n10 = n3 + 1;
                    array[n3] = (byte)(b5 << 2 | b6 >> 4);
                    array[n10] = (byte)((b6 & 0xF) << 4 | (b7 >> 2 & 0xF));
                    array[n10 + 1] = (byte)(b8 | b7 << 6);
                    return array;
                }
                if (b(c7) && b(c8)) {
                    if ((b6 & 0xF) != 0x0) {
                        return null;
                    }
                    final int n11 = i * 3;
                    final byte[] array2 = new byte[n11 + 1];
                    System.arraycopy((Object)array, 0, (Object)array2, 0, n11);
                    array2[n3] = (byte)(b5 << 2 | b6 >> 4);
                    return array2;
                }
                else {
                    o2 = o;
                    if (!b(c7)) {
                        o2 = o;
                        if (b(c8)) {
                            final byte b9 = com.alipay.sdk.m.n.a.i[c7];
                            if ((b9 & 0x3) != 0x0) {
                                return null;
                            }
                            final int n12 = i * 3;
                            o2 = new byte[n12 + 2];
                            System.arraycopy((Object)array, 0, o2, 0, n12);
                            o2[n3] = (byte)(b5 << 2 | b6 >> 4);
                            o2[n3 + 1] = (byte)((b9 >> 2 & 0xF) | (b6 & 0xF) << 4);
                        }
                    }
                }
            }
        }
        return (byte[])o2;
    }
    
    public static boolean b(final char c) {
        return c == '=';
    }
    
    public static boolean c(final char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }
}
