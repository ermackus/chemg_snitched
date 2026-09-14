package com.alipay.sdk.m.i0;

import android.util.Log;
import android.content.Context;
import java.lang.reflect.Method;

public class b
{
    public static final String a = "OpenIdHelper";
    public static Method b;
    
    public static String a(final Context context) {
        final f a = f.a();
        return a.a(context.getApplicationContext(), a.c);
    }
    
    public static void a(final boolean b) {
        f.a();
        f.a(b);
    }
    
    public static final boolean a() {
        Context context = null;
        try {
            if (com.alipay.sdk.m.i0.b.b == null) {
                (com.alipay.sdk.m.i0.b.b = Class.forName("android.app.ActivityThread").getMethod("currentApplication", (Class<?>[])new Class[0])).setAccessible(true);
            }
            context = (Context)com.alipay.sdk.m.i0.b.b.invoke((Object)null, new Object[0]);
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder("ActivityThread:currentApplication --> ");
            sb.append(ex.toString());
            Log.e("OpenIdHelper", sb.toString());
        }
        return context != null && f.a().a(context, false);
    }
    
    public static String b(final Context context) {
        final f a = f.a();
        return a.a(context.getApplicationContext(), a.b);
    }
    
    public static String c(final Context context) {
        final f a = f.a();
        return a.a(context.getApplicationContext(), a.a);
    }
    
    public static String d(final Context context) {
        final f a = f.a();
        return a.a(context.getApplicationContext(), a.d);
    }
}
