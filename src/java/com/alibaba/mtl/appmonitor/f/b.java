package com.alibaba.mtl.appmonitor.f;

public class b
{
    public static boolean c(final String s) {
        return d(s) ^ true;
    }
    
    public static boolean d(final String s) {
        if (s != null) {
            final int length = s.length();
            if (length != 0) {
                for (int i = 0; i < length; ++i) {
                    if (!Character.isWhitespace(s.charAt(i))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
