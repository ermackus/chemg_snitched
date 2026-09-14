package com.alibaba.mtl.log.d;

public class n
{
    private static a a(final String s) {
        if (s != null) {
            final a a = new a();
            int i = 0;
            for (int j = 0; j < 256; ++j) {
                a.d[j] = j;
            }
            a.x = 0;
            a.y = 0;
            int n = 0;
            int n2 = 0;
            while (i < 256) {
                try {
                    n2 = (s.charAt(n) + a.d[i] + n2) % 256;
                    final int n3 = a.d[i];
                    a.d[i] = a.d[n2];
                    a.d[n2] = n3;
                    n = (n + 1) % s.length();
                    ++i;
                    continue;
                }
                catch (final Exception ex) {
                    return null;
                }
                break;
            }
            return a;
        }
        return null;
    }
    
    private static byte[] a(final byte[] array, final a a) {
        if (array != null && a != null) {
            int x = a.x;
            int y = a.y;
            for (int i = 0; i < array.length; ++i) {
                x = (x + 1) % 256;
                y = (a.d[x] + y) % 256;
                final int n = a.d[x];
                a.d[x] = a.d[y];
                a.d[y] = n;
                array[i] ^= (byte)a.d[(a.d[x] + a.d[y]) % 256];
            }
            a.x = x;
            a.y = y;
            return array;
        }
        return null;
    }
    
    public static byte[] a(final byte[] array, final String s) {
        if (array != null && s != null) {
            final a a = a(s);
            if (a != null) {
                return a(array, a);
            }
        }
        return null;
    }
    
    private static class a
    {
        public int[] d;
        public int x;
        public int y;
        
        private a() {
            this.d = new int[256];
        }
    }
}
