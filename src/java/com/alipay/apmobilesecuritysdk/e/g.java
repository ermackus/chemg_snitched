package com.alipay.apmobilesecuritysdk.e;

import android.content.SharedPreferences$Editor;
import com.alipay.sdk.m.y.c;
import com.alipay.sdk.m.z.a;
import com.alipay.sdk.m.b0.e;
import android.content.Context;

public final class g
{
    public static String a(final Context context, final String s) {
        synchronized (g.class) {
            final StringBuilder sb = new StringBuilder("openApi");
            sb.append(s);
            final String a = e.a(context, "openapi_file_pri", sb.toString(), "");
            if (com.alipay.sdk.m.z.a.a(a)) {
                return "";
            }
            final String b = c.b(c.a(), a);
            if (com.alipay.sdk.m.z.a.a(b)) {
                return "";
            }
            return b;
        }
    }
    
    public static void a() {
        final Class<g> clazz;
        monitorenter(clazz = g.class);
        monitorexit(clazz);
    }
    
    public static void a(final Context context) {
        synchronized (g.class) {
            final SharedPreferences$Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (edit != null) {
                edit.clear();
                edit.commit();
            }
        }
    }
    
    public static void a(final Context context, final String s, final String s2) {
        synchronized (g.class) {
            final SharedPreferences$Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (edit != null) {
                final StringBuilder sb = new StringBuilder("openApi");
                sb.append(s);
                edit.putString(sb.toString(), c.a(c.a(), s2));
                edit.commit();
            }
            monitorexit(g.class);
        }
    }
}
