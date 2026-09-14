package com.alibaba.sdk.android.man.crashreporter.handler.c;

import com.alibaba.sdk.android.man.crashreporter.e.h;
import com.alibaba.sdk.android.man.crashreporter.b.a;

public class b
{
    public static void a(final String s, final int n, final int n2) {
        String s2;
        if (n == 0) {
            s2 = "CRASH_HANDLE";
        }
        else if (n == 1) {
            s2 = "NATIVE_CRASH_HANDLE";
        }
        else if (n == 2) {
            s2 = "ANR_HANDLE";
        }
        else {
            s2 = "";
        }
        Class<?> forName = null;
        try {
            forName = Class.forName("com.taobao.statistis.TBS$Ext");
        }
        catch (final ClassNotFoundException ex) {}
        if (forName == null) {
            a.e("com.taobao.stdatistis.TBS.Ext is null");
            return;
        }
        h.a(forName, "commitEvent", new Object[] { "", n2, s, s2 }, new Class[0]);
        a.e("commitEvent call succ");
        return;
        final Throwable t;
        a.d("watchDog error.", t);
    }
}
