package com.alipay.sdk.m.n0;

import android.content.Context;
import java.util.zip.Adler32;
import com.alipay.sdk.m.l0.f;

public class c
{
    public static b a;
    public static final Object b;
    
    static {
        b = new Object();
    }
    
    public static long a(final b b) {
        if (b != null) {
            final String format = String.format("%s%s%s%s%s", new Object[] { b.c(), b.d(), b.a(), b.e(), b.b() });
            if (!f.a(format)) {
                final Adler32 adler32 = new Adler32();
                adler32.reset();
                adler32.update(format.getBytes());
                return adler32.getValue();
            }
        }
        return 0L;
    }
    
    public static b a(final Context context) {
        if (context != null) {
            final Object b = c.b;
            synchronized (b) {
                final String b2 = d.a(context).b();
                if (!f.a(b2)) {
                    String substring = b2;
                    if (b2.endsWith("\n")) {
                        substring = b2.substring(0, b2.length() - 1);
                    }
                    final b b3 = new b();
                    final long currentTimeMillis = System.currentTimeMillis();
                    final String a = com.alipay.sdk.m.l0.d.a(context);
                    final String b4 = com.alipay.sdk.m.l0.d.b(context);
                    b3.c(a);
                    b3.a(a);
                    b3.b(currentTimeMillis);
                    b3.b(b4);
                    b3.d(substring);
                    b3.a(a(b3));
                    return b3;
                }
            }
        }
        return null;
    }
    
    public static b b(final Context context) {
        synchronized (c.class) {
            final b a = c.a;
            if (a != null) {
                return a;
            }
            if (context != null) {
                return c.a = a(context);
            }
            return null;
        }
    }
}
