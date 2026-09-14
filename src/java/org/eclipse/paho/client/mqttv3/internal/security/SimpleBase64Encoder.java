package org.eclipse.paho.client.mqttv3.internal.security;

public class SimpleBase64Encoder
{
    private static final char[] PWDCHARS_ARRAY;
    private static final String PWDCHARS_STRING = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    static {
        PWDCHARS_ARRAY = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    }
    
    public static byte[] decode(final String s) {
        final byte[] bytes = s.getBytes();
        int length = bytes.length;
        final byte[] array = new byte[length * 3 / 4];
        int n = 0;
        int n2 = 0;
        while (true) {
            final int n3 = 2;
            if (length < 4) {
                break;
            }
            long from64 = from64(bytes, n, 4);
            length -= 4;
            final int n4 = n + 4;
            for (int i = n3; i >= 0; --i) {
                array[n2 + i] = (byte)(from64 & 0xFFL);
                from64 >>= 8;
            }
            n2 += 3;
            n = n4;
        }
        if (length == 3) {
            long from65 = from64(bytes, n, 3);
            for (int j = 1; j >= 0; --j) {
                array[n2 + j] = (byte)(from65 & 0xFFL);
                from65 >>= 8;
            }
        }
        if (length == 2) {
            array[n2] = (byte)(from64(bytes, n, 2) & 0xFFL);
        }
        return array;
    }
    
    public static String encode(final byte[] array) {
        int i = array.length;
        final StringBuffer sb = new StringBuffer((i + 2) / 3 * 4);
        int n = 0;
        while (i >= 3) {
            sb.append(to64((array[n] & 0xFF) << 16 | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF), 4));
            n += 3;
            i -= 3;
        }
        if (i == 2) {
            sb.append(to64((array[n] & 0xFF) << 8 | (array[n + 1] & 0xFF), 3));
        }
        if (i == 1) {
            sb.append(to64(array[n] & 0xFF, 2));
        }
        return sb.toString();
    }
    
    private static final long from64(final byte[] array, int n, int n2) {
        final int n3 = 0;
        long n4 = 0L;
        int i = n2;
        n2 = n3;
        while (i > 0) {
            --i;
            final byte b = array[n];
            long n5;
            if (b == 47) {
                n5 = 1L;
            }
            else {
                n5 = 0L;
            }
            long n6 = n5;
            if (b >= 48) {
                n6 = n5;
                if (b <= 57) {
                    n6 = b + 2 - 48;
                }
            }
            long n7 = n6;
            if (b >= 65) {
                n7 = n6;
                if (b <= 90) {
                    n7 = b + 12 - 65;
                }
            }
            long n8 = n7;
            if (b >= 97) {
                n8 = n7;
                if (b <= 122) {
                    n8 = b + 38 - 97;
                }
            }
            n4 += n8 << n2;
            n2 += 6;
            ++n;
        }
        return n4;
    }
    
    private static final String to64(long n, int i) {
        final StringBuffer sb = new StringBuffer(i);
        while (i > 0) {
            --i;
            sb.append(SimpleBase64Encoder.PWDCHARS_ARRAY[(int)(0x3FL & n)]);
            n >>= 6;
        }
        return sb.toString();
    }
}
