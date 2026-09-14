package com.kingagroot.kingdraw.core.graphics;

import android.graphics.Color;

public class KDColor extends Color
{
    public static String getHexString(final int n) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(Integer.toHexString((n & 0xFF) | ((0xFF000000 & n) | (0xFF0000 & n) | (0xFF00 & n))));
        return sb.toString();
    }
    
    public static int parseColor(final String s) {
        if (s != null) {
            final int length = s.length();
            if (s.startsWith("0x") && length > 2) {
                return (int)Long.parseLong(s.substring(2, length), 16);
            }
            try {
                return Color.parseColor(s);
            }
            catch (final IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        return -16777216;
    }
}
