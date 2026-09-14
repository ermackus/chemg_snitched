package com.alipay.sdk.m.l0;

public class e
{
    public static b a(final String s) {
        if (s != null) {
            final b b = new b(null);
            final int n = 0;
            for (int i = 0; i < 256; ++i) {
                b.a[i] = i;
            }
            b.b = 0;
            b.c = 0;
            int n2 = 0;
            int n3 = 0;
            int j = n;
            while (j < 256) {
                try {
                    n3 = (s.charAt(n2) + b.a[j] + n3) % 256;
                    final int n4 = b.a[j];
                    b.a[j] = b.a[n3];
                    b.a[n3] = n4;
                    n2 = (n2 + 1) % s.length();
                    ++j;
                    continue;
                }
                catch (final Exception ex) {
                    return null;
                }
                break;
            }
            return b;
        }
        return null;
    }
    
    public static byte[] a(final byte[] array) {
        if (array != null) {
            final b a = a("QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK");
            if (a != null) {
                return a(array, a);
            }
        }
        return null;
    }
    
    public static byte[] a(final byte[] array, final b b) {
        if (array != null && b != null) {
            int b2 = b.b;
            int c = b.c;
            for (int i = 0; i < array.length; ++i) {
                b2 = (b2 + 1) % 256;
                final int[] a = b.a;
                c = (a[b2] + c) % 256;
                final int n = a[b2];
                a[b2] = a[c];
                a[c] = n;
                array[i] ^= (byte)a[(a[b2] + a[c]) % 256];
            }
            b.b = b2;
            b.c = c;
            return array;
        }
        return null;
    }
    
    public static class b
    {
        public int[] a;
        public int b;
        public int c;
        
        public b() {
            this.a = new int[256];
        }
    }
}
