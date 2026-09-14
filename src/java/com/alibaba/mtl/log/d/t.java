package com.alibaba.mtl.log.d;

import java.util.Map;

public class t
{
    public static void send(final Map<String, String> map) {
        try {
            final Object a = o.a("com.ut.mini.UTAnalytics", "getInstance");
            if (a != null) {
                final Object a2 = o.a(a, "getDefaultTracker");
                if (a2 != null) {
                    o.a(a2, "send", new Object[] { map }, Map.class);
                }
            }
        }
        catch (final Exception ex) {}
    }
}
