package com.ta.utdid2.b.a;

public class i
{
    public static int a(final String s) {
        final int length = s.length();
        int n = 0;
        int i = 0;
        if (length > 0) {
            final char[] charArray = s.toCharArray();
            n = 0;
            while (i < charArray.length) {
                n = n * 31 + charArray[i];
                ++i;
            }
        }
        return n;
    }
    
    public static boolean a(final String s) {
        return s == null || s.length() <= 0;
    }
}
