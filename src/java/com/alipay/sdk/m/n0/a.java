package com.alipay.sdk.m.n0;

import com.alipay.sdk.m.l0.f;
import android.content.Context;

public class a
{
    public static String a(final Context context) {
        final b b = c.b(context);
        String c;
        if (b != null && !f.a(b.c())) {
            c = b.c();
        }
        else {
            c = "ffffffffffffffffffffffff";
        }
        return c;
    }
    
    public static String b(final Context context) {
        final String a = d.a(context).a();
        if (a != null) {
            final String s = a;
            if (!f.a(a)) {
                return s;
            }
        }
        return "ffffffffffffffffffffffff";
    }
    
    @Deprecated
    public static String c(final Context context) {
        return a(context);
    }
    
    @Deprecated
    public static String d(final Context context) {
        return b(context);
    }
}
