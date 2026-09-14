package com.alipay.sdk.m.w;

import com.alipay.sdk.m.u.n;
import com.alipay.sdk.m.u.e;
import java.util.concurrent.Callable;
import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import android.util.Pair;
import java.util.concurrent.ConcurrentHashMap;

public class a
{
    public static final String a = "CDT";
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static ConcurrentHashMap<Integer, Pair<Long, ?>> g;
    public static ExecutorService h;
    
    static {
        com.alipay.sdk.m.w.a.h = Executors.newFixedThreadPool(16);
    }
    
    public static Context a(final Context context) {
        if (context == null) {
            return null;
        }
        return context.getApplicationContext();
    }
    
    public static Pair<Boolean, ?> a(final int n, final TimeUnit timeUnit, final long n2) {
        final ConcurrentHashMap<Integer, Pair<Long, ?>> g = com.alipay.sdk.m.w.a.g;
        final Boolean value = false;
        if (g == null) {
            return (Pair<Boolean, ?>)new Pair((Object)value, (Object)null);
        }
        final Pair pair = (Pair)g.get((Object)n);
        if (pair == null) {
            return (Pair<Boolean, ?>)new Pair((Object)value, (Object)null);
        }
        final Long n3 = (Long)pair.first;
        final Object second = pair.second;
        if (n3 != null && SystemClock.elapsedRealtime() - n3 <= TimeUnit.MILLISECONDS.convert(n2, timeUnit)) {
            return (Pair<Boolean, ?>)new Pair((Object)true, second);
        }
        return (Pair<Boolean, ?>)new Pair((Object)value, (Object)null);
    }
    
    public static <T> T a(final int n, final long n2, final TimeUnit timeUnit, final a<Object, Boolean> a, final Callable<T> callable, final boolean b, final long n3, final TimeUnit timeUnit2, final a a2, final boolean b2) {
        try {
            final Pair<Boolean, ?> a3 = a(n, timeUnit, n2);
            if ((boolean)a3.first && a.a(a3.second)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(n);
                sb.append(" got ");
                sb.append(a3.second);
                com.alipay.sdk.m.u.e.d("getC", sb.toString());
                return (T)a3.second;
            }
            Object o;
            if (b2 && n.h()) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(n);
                com.alipay.sdk.m.k.a.b(a2, "biz", "ch_get_main", sb2.toString());
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(n);
                sb3.append(" skip");
                com.alipay.sdk.m.u.e.d("getC", sb3.toString());
                o = null;
            }
            else {
                if (b) {
                    o = a.h.submit((Callable)callable).get(n3, timeUnit2);
                }
                else {
                    o = callable.call();
                }
                a(n, o);
            }
            final StringBuilder sb4 = new StringBuilder();
            sb4.append(n);
            sb4.append(" new ");
            sb4.append(o);
            com.alipay.sdk.m.u.e.d("getC", sb4.toString());
            return (T)o;
        }
        finally {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("ch_get_e|");
            sb5.append(n);
            final Throwable t;
            com.alipay.sdk.m.u.e.a("CDT", sb5.toString(), t);
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("ch_get_e|");
            sb6.append(n);
            com.alipay.sdk.m.k.a.a(a2, "biz", sb6.toString(), t);
            final StringBuilder sb7 = new StringBuilder();
            sb7.append(n);
            sb7.append(" err");
            com.alipay.sdk.m.u.e.d("getC", sb7.toString());
            return null;
        }
    }
    
    public static void a() {
        synchronized (a.class) {
            com.alipay.sdk.m.w.a.g = null;
        }
    }
    
    public static void a(final int n, final Object o) {
        synchronized (a.class) {
            if (com.alipay.sdk.m.w.a.g == null) {
                com.alipay.sdk.m.w.a.g = (ConcurrentHashMap<Integer, Pair<Long, ?>>)new ConcurrentHashMap();
            }
            com.alipay.sdk.m.w.a.g.put((Object)n, (Object)new Pair((Object)SystemClock.elapsedRealtime(), o));
        }
    }
    
    public interface a<T, R>
    {
        R a(final T p0);
    }
}
