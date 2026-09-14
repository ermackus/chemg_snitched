package com.alibaba.mtl.appmonitor.f;

public class a
{
    public static int a(final String s) {
        if (!b.c(s)) {
            return 0;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (final NumberFormatException ex) {
            return 0;
        }
    }
}
