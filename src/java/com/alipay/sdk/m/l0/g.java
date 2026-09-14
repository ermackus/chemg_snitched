package com.alipay.sdk.m.l0;

public class g
{
    public static String a(String s, String s2) {
        try {
            final Class<?> forName = Class.forName("android.os.SystemProperties");
            s = (s2 = (String)forName.getMethod("get", String.class, String.class).invoke((Object)forName, new Object[] { s, s2 }));
            return s2;
        }
        catch (final Exception ex) {
            return s2;
        }
    }
}
