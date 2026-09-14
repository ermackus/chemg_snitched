package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import com.alipay.sdk.m.z.a;
import java.util.HashMap;
import java.util.Map;

public final class i
{
    public static String a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static String e = "";
    public static Map<String, String> f;
    
    static {
        i.f = (Map<String, String>)new HashMap();
    }
    
    public static String a(String string) {
        synchronized (i.class) {
            final StringBuilder sb = new StringBuilder("apdidTokenCache");
            sb.append(string);
            string = sb.toString();
            if (i.f.containsKey((Object)string)) {
                string = (String)i.f.get((Object)string);
                if (com.alipay.sdk.m.z.a.b(string)) {
                    return string;
                }
            }
            return "";
        }
    }
    
    public static void a() {
        final Class<i> clazz;
        monitorenter(clazz = i.class);
        monitorexit(clazz);
    }
    
    public static void a(final b b) {
        final Class<i> clazz;
        monitorenter(clazz = i.class);
        if (b != null) {
            try {
                i.a = b.a;
                i.b = b.b;
                i.c = b.c;
            }
            finally {
                monitorexit(clazz);
            }
        }
        monitorexit(clazz);
    }
    
    public static void a(final c c) {
        final Class<i> clazz;
        monitorenter(clazz = i.class);
        if (c != null) {
            try {
                i.a = c.a;
                i.b = c.b;
                i.d = c.d;
                i.e = c.e;
                i.c = c.c;
            }
            finally {
                monitorexit(clazz);
            }
        }
        monitorexit(clazz);
    }
    
    public static void a(String string, final String s) {
        synchronized (i.class) {
            final StringBuilder sb = new StringBuilder("apdidTokenCache");
            sb.append(string);
            string = sb.toString();
            if (i.f.containsKey((Object)string)) {
                i.f.remove((Object)string);
            }
            i.f.put((Object)string, (Object)s);
        }
    }
    
    public static boolean a(final Context context, final String s) {
        final Class<i> clazz;
        monitorenter(clazz = i.class);
        while (true) {
            try {
                long a;
                if ((a = h.a(context)) < 0L) {
                    a = 86400000L;
                }
                Label_0059: {
                    try {
                        if (Math.abs(System.currentTimeMillis() - h.h(context, s)) < a) {
                            return true;
                        }
                        break Label_0059;
                    }
                    finally {
                        final Context context2 = context;
                        com.alipay.apmobilesecuritysdk.c.a.a((Throwable)context2);
                    }
                    try {
                        final Context context2 = context;
                        com.alipay.apmobilesecuritysdk.c.a.a((Throwable)context2);
                        final boolean b = false;
                    }
                    finally {
                        monitorexit(clazz);
                    }
                }
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    public static String b() {
        synchronized (i.class) {
            return i.a;
        }
    }
    
    public static void b(final String a) {
        i.a = a;
    }
    
    public static String c() {
        synchronized (i.class) {
            return i.b;
        }
    }
    
    public static void c(final String b) {
        i.b = b;
    }
    
    public static String d() {
        synchronized (i.class) {
            return i.d;
        }
    }
    
    public static void d(final String c) {
        i.c = c;
    }
    
    public static String e() {
        synchronized (i.class) {
            return i.e;
        }
    }
    
    public static void e(final String d) {
        i.d = d;
    }
    
    public static String f() {
        synchronized (i.class) {
            return i.c;
        }
    }
    
    public static void f(final String e) {
        i.e = e;
    }
    
    public static c g() {
        synchronized (i.class) {
            return new c(i.a, i.b, i.c, i.d, i.e);
        }
    }
    
    public static void h() {
        i.f.clear();
        i.a = "";
        i.b = "";
        i.d = "";
        i.e = "";
        i.c = "";
    }
}
