package com.ta.utdid2.b.a;

import java.lang.reflect.Method;

public class d
{
    private static Class<?> a;
    private static Method a;
    private static Method b;
    public static boolean e;
    
    static {
        boolean e = false;
        if (getInt("alidebug", 0) == 1) {
            e = true;
        }
        d.e = e;
        d.a = null;
        d.a = null;
        d.b = null;
    }
    
    private static void a() {
        try {
            if (d.a == null) {
                d.a = (d.a = Class.forName("android.os.SystemProperties")).getDeclaredMethod("get", String.class);
                d.b = d.a.getDeclaredMethod("getInt", String.class, Integer.TYPE);
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static int getInt(final String s, int intValue) {
        a();
        try {
            intValue = (int)d.b.invoke((Object)d.a, new Object[] { s, intValue });
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        return intValue;
    }
}
