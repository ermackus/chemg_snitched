package com.alipay.sdk.m.j0;

import android.util.Log;
import android.content.Context;
import java.lang.reflect.Method;

public class b
{
    public static final String a = "IdentifierManager";
    public static Object b;
    public static Class<?> c;
    public static Method d;
    public static Method e;
    public static Method f;
    public static Method g;
    
    static {
        try {
            com.alipay.sdk.m.j0.b.b = (com.alipay.sdk.m.j0.b.c = Class.forName("com.android.id.impl.IdProviderImpl")).newInstance();
            com.alipay.sdk.m.j0.b.d = com.alipay.sdk.m.j0.b.c.getMethod("getUDID", Context.class);
            com.alipay.sdk.m.j0.b.e = com.alipay.sdk.m.j0.b.c.getMethod("getOAID", Context.class);
            com.alipay.sdk.m.j0.b.f = com.alipay.sdk.m.j0.b.c.getMethod("getVAID", Context.class);
            com.alipay.sdk.m.j0.b.g = com.alipay.sdk.m.j0.b.c.getMethod("getAAID", Context.class);
        }
        catch (final Exception ex) {
            Log.e("IdentifierManager", "reflect exception!", (Throwable)ex);
        }
    }
    
    public static String a(final Context context) {
        return a(context, com.alipay.sdk.m.j0.b.g);
    }
    
    public static String a(final Context context, final Method method) {
        final Object b = com.alipay.sdk.m.j0.b.b;
        if (b != null && method != null) {
            try {
                final Object invoke = method.invoke(b, new Object[] { context });
                if (invoke != null) {
                    return (String)invoke;
                }
            }
            catch (final Exception ex) {
                Log.e("IdentifierManager", "invoke exception!", (Throwable)ex);
            }
        }
        return null;
    }
    
    public static boolean a() {
        return com.alipay.sdk.m.j0.b.c != null && com.alipay.sdk.m.j0.b.b != null;
    }
    
    public static String b(final Context context) {
        return a(context, com.alipay.sdk.m.j0.b.e);
    }
    
    public static String c(final Context context) {
        return a(context, com.alipay.sdk.m.j0.b.d);
    }
    
    public static String d(final Context context) {
        return a(context, com.alipay.sdk.m.j0.b.f);
    }
}
