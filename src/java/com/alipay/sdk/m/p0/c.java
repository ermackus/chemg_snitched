package com.alipay.sdk.m.p0;

import android.os.SystemClock;
import android.util.Log;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.content.Context;

public class c
{
    public static String A;
    public static volatile c B;
    public static volatile b C;
    public static final String a = "VMS_IDLG_SDK_Client";
    public static final String b = "content://com.vivo.vms.IdProvider/IdentifierId";
    public static final String c = "persist.sys.identifierid.supported";
    public static final String d = "appid";
    public static final String e = "type";
    public static final String f = "OAID";
    public static final String g = "VAID";
    public static final String h = "AAID";
    public static final int i = 0;
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 4;
    public static final int m = 11;
    public static final int n = 2000;
    public static Context o;
    public static boolean p;
    public static d q;
    public static d r;
    public static d s;
    public static Object t;
    public static HandlerThread u;
    public static Handler v;
    public static String w;
    public static String x;
    public static String y;
    public static String z;
    
    static {
        com.alipay.sdk.m.p0.c.t = new Object();
    }
    
    public static c a(final Context context) {
        if (com.alipay.sdk.m.p0.c.B == null) {
            synchronized (c.class) {
                com.alipay.sdk.m.p0.c.o = context.getApplicationContext();
                com.alipay.sdk.m.p0.c.B = new c();
            }
        }
        if (com.alipay.sdk.m.p0.c.C == null) {
            synchronized (c.class) {
                com.alipay.sdk.m.p0.c.o = context.getApplicationContext();
                g();
                com.alipay.sdk.m.p0.c.C = new b(com.alipay.sdk.m.p0.c.o);
                f();
            }
        }
        return com.alipay.sdk.m.p0.c.B;
    }
    
    public static String a(String s, final String s2) {
        try {
            try {
                final Class<?> forName = Class.forName("android.os.SystemProperties");
                s = (String)forName.getMethod("get", String.class, String.class).invoke((Object)forName, new Object[] { s, "unknown" });
                return s;
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
            return s2;
        }
        finally {
            return s2;
        }
    }
    
    public static void a(final Context context, final int n, final String s) {
        if (n != 0) {
            if (n != 1) {
                if (n == 2) {
                    com.alipay.sdk.m.p0.c.s = new d(com.alipay.sdk.m.p0.c.B, 2, s);
                    final ContentResolver contentResolver = context.getContentResolver();
                    final StringBuilder sb = new StringBuilder();
                    sb.append("content://com.vivo.vms.IdProvider/IdentifierId/AAID_");
                    sb.append(s);
                    contentResolver.registerContentObserver(Uri.parse(sb.toString()), false, (ContentObserver)com.alipay.sdk.m.p0.c.s);
                }
            }
            else {
                com.alipay.sdk.m.p0.c.r = new d(com.alipay.sdk.m.p0.c.B, 1, s);
                final ContentResolver contentResolver2 = context.getContentResolver();
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("content://com.vivo.vms.IdProvider/IdentifierId/VAID_");
                sb2.append(s);
                contentResolver2.registerContentObserver(Uri.parse(sb2.toString()), false, (ContentObserver)com.alipay.sdk.m.p0.c.r);
            }
        }
        else {
            com.alipay.sdk.m.p0.c.q = new d(com.alipay.sdk.m.p0.c.B, 0, null);
            context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), true, (ContentObserver)com.alipay.sdk.m.p0.c.q);
        }
    }
    
    private void b(final int n, final String s) {
        final Message obtainMessage = com.alipay.sdk.m.p0.c.v.obtainMessage();
        obtainMessage.what = 11;
        final Bundle data = new Bundle();
        data.putInt("type", n);
        if (n == 1 || n == 2) {
            data.putString("appid", s);
        }
        obtainMessage.setData(data);
        com.alipay.sdk.m.p0.c.v.sendMessage(obtainMessage);
    }
    
    public static /* synthetic */ String c(final String w) {
        return com.alipay.sdk.m.p0.c.w = w;
    }
    
    public static /* synthetic */ b d() {
        return com.alipay.sdk.m.p0.c.C;
    }
    
    public static /* synthetic */ Object e() {
        return com.alipay.sdk.m.p0.c.t;
    }
    
    public static void f() {
        com.alipay.sdk.m.p0.c.p = "1".equals((Object)a("persist.sys.identifierid.supported", "0"));
    }
    
    public static void g() {
        (com.alipay.sdk.m.p0.c.u = new HandlerThread("SqlWorkThread")).start();
        com.alipay.sdk.m.p0.c.v = new Handler(com.alipay.sdk.m.p0.c.u.getLooper()) {
            public void handleMessage(final Message message) {
                if (message.what == 11) {
                    com.alipay.sdk.m.p0.c.c(com.alipay.sdk.m.p0.c.d().a(message.getData().getInt("type"), message.getData().getString("appid")));
                    final Object e = com.alipay.sdk.m.p0.c.e();
                    synchronized (e) {
                        com.alipay.sdk.m.p0.c.e().notify();
                        return;
                    }
                }
                Log.e("VMS_IDLG_SDK_Client", "message type valid");
            }
        };
    }
    
    public String a() {
        if (!this.c()) {
            return null;
        }
        final String x = com.alipay.sdk.m.p0.c.x;
        if (x != null) {
            return x;
        }
        this.a(0, null);
        if (com.alipay.sdk.m.p0.c.q == null) {
            a(com.alipay.sdk.m.p0.c.o, 0, null);
        }
        return com.alipay.sdk.m.p0.c.x;
    }
    
    public String a(final String s) {
        if (!this.c()) {
            return null;
        }
        final String z = com.alipay.sdk.m.p0.c.z;
        if (z != null) {
            return z;
        }
        this.a(2, s);
        if (com.alipay.sdk.m.p0.c.s == null && com.alipay.sdk.m.p0.c.z != null) {
            a(com.alipay.sdk.m.p0.c.o, 2, s);
        }
        return com.alipay.sdk.m.p0.c.z;
    }
    
    public void a(final int n, final String s) {
        final Object t = com.alipay.sdk.m.p0.c.t;
        synchronized (t) {
            this.b(n, s);
            final long uptimeMillis = SystemClock.uptimeMillis();
            try {
                com.alipay.sdk.m.p0.c.t.wait(2000L);
            }
            catch (final InterruptedException ex) {
                ex.printStackTrace();
            }
            if (SystemClock.uptimeMillis() - uptimeMillis < 2000L) {
                if (n != 0) {
                    if (n != 1) {
                        if (n != 2) {
                            if (n != 4) {
                                return;
                            }
                        }
                        else if (com.alipay.sdk.m.p0.c.w != null) {
                            com.alipay.sdk.m.p0.c.z = com.alipay.sdk.m.p0.c.w;
                            com.alipay.sdk.m.p0.c.w = null;
                        }
                        else {
                            Log.e("VMS_IDLG_SDK_Client", "get aaid failed");
                        }
                        com.alipay.sdk.m.p0.c.A = com.alipay.sdk.m.p0.c.w;
                        com.alipay.sdk.m.p0.c.w = null;
                    }
                    else if (com.alipay.sdk.m.p0.c.w != null) {
                        com.alipay.sdk.m.p0.c.y = com.alipay.sdk.m.p0.c.w;
                        com.alipay.sdk.m.p0.c.w = null;
                    }
                    else {
                        Log.e("VMS_IDLG_SDK_Client", "get vaid failed");
                    }
                }
                else {
                    com.alipay.sdk.m.p0.c.x = com.alipay.sdk.m.p0.c.w;
                    com.alipay.sdk.m.p0.c.w = null;
                }
            }
            else {
                Log.d("VMS_IDLG_SDK_Client", "query timeout");
            }
        }
    }
    
    public String b() {
        if (!this.c()) {
            return null;
        }
        this.a(4, null);
        return com.alipay.sdk.m.p0.c.A;
    }
    
    public String b(final String s) {
        if (!this.c()) {
            return null;
        }
        final String y = com.alipay.sdk.m.p0.c.y;
        if (y != null) {
            return y;
        }
        this.a(1, s);
        if (com.alipay.sdk.m.p0.c.r == null && com.alipay.sdk.m.p0.c.y != null) {
            a(com.alipay.sdk.m.p0.c.o, 1, s);
        }
        return com.alipay.sdk.m.p0.c.y;
    }
    
    public boolean c() {
        return com.alipay.sdk.m.p0.c.p;
    }
}
