package com.alibaba.mtl.log;

import com.alibaba.mtl.log.c.c;
import java.util.HashMap;
import com.alibaba.mtl.log.d.s;
import com.alibaba.mtl.log.d.l;
import java.util.Map;
import com.alibaba.mtl.log.d.b;
import com.alibaba.mtl.log.upload.UploadEngine;
import android.util.Log;
import com.alibaba.mtl.log.d.i;
import android.text.TextUtils;
import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;
import com.alibaba.mtl.log.sign.IRequestAuth;

public class a
{
    public static String B;
    public static IRequestAuth a;
    private static boolean a = false;
    public static long b = -1L;
    public static final AtomicInteger d;
    private static Context mContext;
    public static boolean o = false;
    public static boolean p = false;
    private static boolean q = false;
    public static boolean r = false;
    public static int s = 10000;
    public static boolean s;
    public static int t;
    
    static {
        com.alibaba.mtl.log.a.q = (10000 <= 0);
        com.alibaba.mtl.log.a.B = String.valueOf(System.currentTimeMillis());
        d = new AtomicInteger(0);
        com.alibaba.mtl.log.a.r = true;
        com.alibaba.mtl.log.a.a = null;
        com.alibaba.mtl.log.a.s = true;
    }
    
    public static IRequestAuth a() {
        final IRequestAuth a = com.alibaba.mtl.log.a.a;
        if (a == null || TextUtils.isEmpty((CharSequence)a.getAppkey())) {
            if (i.l()) {
                throw new RuntimeException("please Set <meta-data android:value=\"YOU KEY\" android:name=\"com.alibaba.apmplus.app_key\"></meta-data> in app AndroidManifest.xml ");
            }
            Log.w("UTDC", "please Set <meta-data android:value=\"YOU KEY\" android:name=\"com.alibaba.apmplus.app_key\"></meta-data> in app AndroidManifest.xml ");
        }
        return com.alibaba.mtl.log.a.a;
    }
    
    public static void a(final Context context) {
        final Class<a> clazz;
        monitorenter(clazz = a.class);
        Label_0042: {
            if (context != null) {
                break Label_0042;
            }
            try {
                final StringBuilder sb = new StringBuilder();
                sb.append("UTDC init failed ,context:");
                sb.append((Object)context);
                i.a("UTDC", sb.toString());
                return;
                while (true) {
                    com.alibaba.mtl.log.a.a = true;
                    com.alibaba.mtl.log.a.mContext = context.getApplicationContext();
                    UploadEngine.getInstance().start();
                    return;
                    iftrue(Label_0065:)(com.alibaba.mtl.log.a.a);
                    continue;
                }
                Label_0065:;
            }
            finally {
                monitorexit(clazz);
            }
        }
    }
    
    public static void a(final IRequestAuth a) {
        a.a = a;
        if (a != null) {
            com.alibaba.mtl.log.d.b.p(a.getAppkey());
        }
    }
    
    public static void a(final String s, final String s2, final String s3, final String s4, final String s5, final Map<String, String> map) {
        if (com.alibaba.mtl.log.a.mContext == null) {
            i.a("UTDC", "please call UTDC.init(context) before commit log,and this log will be discarded");
            return;
        }
        if (com.alibaba.mtl.log.a.a == null) {
            i.a("UTDC", "please call UTDC.setRequestAuthentication(auth) before commit log,and this log will be discarded");
            return;
        }
        b(s, s2, s3, s4, s5, map);
    }
    
    public static String b() {
        try {
            return l.getNetworkState(getContext())[0];
        }
        catch (final Exception ex) {
            return "Unknown";
        }
    }
    
    private static void b(final String s, final String s2, final String s3, final String s4, final String s5, final Map<String, String> map) {
        s.a().b((Runnable)new Runnable(s, s2, s3, s4, s5, new HashMap((Map)map)) {
            final String C;
            final String D;
            final String E;
            final String F;
            final String G;
            final HashMap a;
            
            public void run() {
                i.a("UTDC", new Object[] { "[commit] page:", this.C, "eventId:", this.D, "arg1:", this.E, "arg2:", this.F, "arg3:", this.G, "args:", this.a });
                try {
                    com.alibaba.mtl.log.b.a.m(this.D);
                    c.a().a(new com.alibaba.mtl.log.model.a(this.C, this.D, this.E, this.F, this.G, (Map<String, String>)this.a));
                }
                finally {}
            }
        });
    }
    
    public static String c() {
        final String s = "Unknown";
        try {
            final String[] networkState = l.getNetworkState(getContext());
            String s2 = s;
            if (networkState[0].equals((Object)"2G/3G")) {
                s2 = networkState[1];
            }
            return s2;
        }
        catch (final Exception ex) {
            return s;
        }
    }
    
    public static String d() {
        return "";
    }
    
    public static String e() {
        return "";
    }
    
    public static Context getContext() {
        return com.alibaba.mtl.log.a.mContext;
    }
    
    public static void k() {
        i.a("UTDC", new Object[] { "[onBackground]" });
        com.alibaba.mtl.log.a.o = true;
        com.alibaba.mtl.log.b.a.C();
    }
    
    public static void l() {
        i.a("UTDC", new Object[] { "[onForeground]" });
        com.alibaba.mtl.log.a.o = false;
        UploadEngine.getInstance().start();
    }
    
    public static void m() {
        UploadEngine.getInstance().start();
    }
    
    public static void setChannel(final String s) {
        com.alibaba.mtl.log.d.b.o(s);
    }
}
