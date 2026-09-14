package com.ta.utdid2.device;

import com.ta.utdid2.b.a.g;
import android.content.Context;
import java.util.zip.Adler32;
import com.ta.utdid2.b.a.i;

public class b
{
    private static a a;
    static final Object e;
    static String k = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
    
    static {
        e = new Object();
    }
    
    static long a(final a a) {
        if (a != null) {
            final String format = String.format("%s%s%s%s%s", new Object[] { a.f(), a.getDeviceId(), a.a(), a.e(), a.d() });
            if (!i.a(format)) {
                final Adler32 adler32 = new Adler32();
                adler32.reset();
                adler32.update(format.getBytes());
                return adler32.getValue();
            }
        }
        return 0L;
    }
    
    private static a a(final Context context) {
        if (context != null) {
            new a();
            final Object e = b.e;
            synchronized (e) {
                final String value = c.a(context).getValue();
                if (!i.a(value)) {
                    String substring = value;
                    if (value.endsWith("\n")) {
                        substring = value.substring(0, value.length() - 1);
                    }
                    final a a = new a();
                    final long currentTimeMillis = System.currentTimeMillis();
                    final String a2 = g.a(context);
                    final String b = g.b(context);
                    a.d(a2);
                    a.b(a2);
                    a.b(currentTimeMillis);
                    a.c(b);
                    a.e(substring);
                    a.a(a(a));
                    return a;
                }
            }
        }
        return null;
    }
    
    public static a b(final Context context) {
        synchronized (b.class) {
            if (b.a != null) {
                return b.a;
            }
            if (context != null) {
                return b.a = a(context);
            }
            return null;
        }
    }
}
