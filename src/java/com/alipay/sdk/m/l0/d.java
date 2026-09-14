package com.alipay.sdk.m.l0;

import android.text.TextUtils;
import android.content.Context;
import java.util.Random;

public class d
{
    public static String a() {
        final int n = (int)(System.currentTimeMillis() / 1000L);
        final int n2 = (int)System.nanoTime();
        final int nextInt = new Random().nextInt();
        final int nextInt2 = new Random().nextInt();
        final byte[] a = c.a(n);
        final byte[] a2 = c.a(n2);
        final byte[] a3 = c.a(nextInt);
        final byte[] a4 = c.a(nextInt2);
        final byte[] array = new byte[16];
        System.arraycopy((Object)a, 0, (Object)array, 0, 4);
        System.arraycopy((Object)a2, 0, (Object)array, 4, 4);
        System.arraycopy((Object)a3, 0, (Object)array, 8, 4);
        System.arraycopy((Object)a4, 0, (Object)array, 12, 4);
        return b.c(array, 2);
    }
    
    public static String a(final Context context) {
        String b = null;
        if (f.a((String)null)) {
            b = b();
        }
        String a = b;
        if (f.a(b)) {
            a = a();
        }
        return a;
    }
    
    public static String b() {
        String s;
        if (TextUtils.isEmpty((CharSequence)(s = g.a("ro.aliyun.clouduuid", "")))) {
            s = g.a("ro.sys.aliyun.clouduuid", "");
        }
        String c = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            c = c();
        }
        return c;
    }
    
    public static String b(final Context context) {
        return "";
    }
    
    public static String c() {
        String s;
        try {
            s = (String)Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", (Class<?>[])new Class[0]).invoke((Object)null, new Object[0]);
        }
        catch (final Exception ex) {
            s = "";
        }
        return s;
    }
}
