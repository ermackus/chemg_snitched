package com.alipay.sdk.m.d;

import android.util.Log;

public class a
{
    public static final String a = "OpenId";
    public static boolean b;
    
    public static String a(Object o, final Object o2) {
        Object o3 = o;
        if (o == null) {
            o3 = "";
        }
        if ((o = o2) == null) {
            o = "";
        }
        return String.format("%s:%s", new Object[] { o3, o });
    }
    
    public static String a(final String s, final Object... array) {
        if (s == null && array == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        String s2;
        if ((s2 = s) == null) {
            s2 = "-";
        }
        int n = 0;
        sb.append(String.format("[%s] ", new Object[] { s2 }));
        if (array != null) {
            final int length = array.length;
            while (true) {
                final int n2 = n + 1;
                if (n2 >= array.length) {
                    break;
                }
                sb.append(a(array[n], array[n2]));
                if (n2 < length - 1) {
                    sb.append(",");
                }
                n = n2 + 1;
            }
            if (n == array.length - 1) {
                sb.append(array[n]);
            }
        }
        return sb.toString();
    }
    
    public static void a(final boolean b) {
        final StringBuilder sb = new StringBuilder();
        sb.append("setDebug:");
        sb.append(b);
        Log.d("OpenId", sb.toString());
        com.alipay.sdk.m.d.a.b = b;
    }
    
    public static void b(final String s, final Object... array) {
        if (com.alipay.sdk.m.d.a.b) {
            Log.d("OpenId", a(s, array));
        }
    }
    
    public static void c(final String s, final Object... array) {
        if (com.alipay.sdk.m.d.a.b) {
            Log.e("OpenId", a(s, array));
        }
    }
    
    public static void d(final String s, final Object... array) {
        if (com.alipay.sdk.m.d.a.b) {
            Log.i("OpenId", a(s, array));
        }
    }
    
    public static void e(final String s, final Object... array) {
        if (com.alipay.sdk.m.d.a.b) {
            Log.w("OpenId", a(s, array));
        }
    }
}
