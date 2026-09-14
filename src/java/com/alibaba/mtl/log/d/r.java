package com.alibaba.mtl.log.d;

public class r
{
    public static String get(String s) {
        try {
            final Class<?> forName = Class.forName("android.os.SystemProperties");
            s = (String)forName.getMethod("get", String.class).invoke((Object)forName, new Object[] { s });
        }
        catch (final Exception ex) {
            s = "";
        }
        return s;
    }
    
    public static String get(String s, final String s2) {
        try {
            final Class<?> forName = Class.forName("android.os.SystemProperties");
            s = (String)forName.getMethod("get", String.class, String.class).invoke((Object)forName, new Object[] { s, s2 });
            return s;
        }
        catch (final Exception ex) {
            s = s2;
            return s;
        }
    }
}
