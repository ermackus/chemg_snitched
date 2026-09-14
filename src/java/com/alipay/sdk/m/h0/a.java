package com.alipay.sdk.m.h0;

import com.alipay.sdk.m.a.a$c;
import com.alipay.sdk.m.a.a$b;
import android.content.Context;

public class a
{
    public static String a(final Context context) {
        if (a$b.a) {
            return a$c.b.a.a(context.getApplicationContext(), "AUID");
        }
        throw new RuntimeException("SDK Need Init First!");
    }
    
    public static boolean a() {
        if (a$b.a) {
            return a$b.b;
        }
        throw new RuntimeException("SDK Need Init First!");
    }
    
    public static String b(final Context context) {
        if (a$b.a) {
            return a$c.b.a.a(context.getApplicationContext(), "OUID");
        }
        throw new RuntimeException("SDK Need Init First!");
    }
    
    public static String c(final Context context) {
        if (a$b.a) {
            return a$c.b.a.a(context.getApplicationContext(), "GUID");
        }
        throw new RuntimeException("SDK Need Init First!");
    }
    
    public static String d(final Context context) {
        if (a$b.a) {
            return a$c.b.a.a(context.getApplicationContext(), "DUID");
        }
        throw new RuntimeException("SDK Need Init First!");
    }
    
    public static void e(final Context context) {
        a$b.b = a$c.b.a.a(context.getApplicationContext());
        a$b.a = true;
    }
}
