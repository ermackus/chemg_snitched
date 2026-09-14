package com.alipay.apmobilesecuritysdk.a;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import com.alipay.sdk.m.a0.f;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import java.io.File;
import android.os.Environment;
import com.alipay.apmobilesecuritysdk.e.b;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.sdk.m.f0.d;
import com.alipay.sdk.m.f0.c;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.e.h;
import android.content.Context;

public final class a
{
    public Context a;
    public com.alipay.apmobilesecuritysdk.b.a b;
    public int c;
    
    public a(final Context a) {
        this.b = com.alipay.apmobilesecuritysdk.b.a.a();
        this.c = 4;
        this.a = a;
    }
    
    public static String a(final Context context) {
        String s;
        if (com.alipay.sdk.m.z.a.a(s = b(context))) {
            s = h.f(context);
        }
        return s;
    }
    
    public static String a(final Context context, final String s) {
        try {
            b();
            final String a = i.a(s);
            if (!com.alipay.sdk.m.z.a.a(a)) {
                return a;
            }
            final String a2 = g.a(context, s);
            i.a(s, a2);
            if (!com.alipay.sdk.m.z.a.a(a2)) {
                return a2;
            }
            return "";
        }
        finally {
            return "";
        }
    }
    
    public static boolean a() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final int n = (int)(Math.random() * 24.0 * 60.0 * 60.0);
        int n2 = 0;
        while (true) {
            if (n2 >= 3) {
                return false;
            }
            try {
                final String[] split = (new String[] { "2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12" })[n2].split(" ");
                if (split != null && split.length == 2) {
                    final Date date = new Date();
                    final StringBuilder sb = new StringBuilder();
                    sb.append(split[0]);
                    sb.append(" 00:00:00");
                    final Date parse = simpleDateFormat.parse(sb.toString());
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(split[1]);
                    sb2.append(" 23:59:59");
                    final Date parse2 = simpleDateFormat.parse(sb2.toString());
                    final Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, n * 1);
                    final Date time = instance.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                }
                ++n2;
                continue;
            }
            catch (final Exception ex) {
                return false;
            }
        }
    }
    
    private c b(final Map<String, String> map) {
        String a = "";
        try {
            final Context a2 = this.a;
            final d d = new d();
            final String a3 = com.alipay.sdk.m.z.a.a((Map)map, "appName", "");
            final String a4 = com.alipay.sdk.m.z.a.a((Map)map, "sessionId", "");
            final String a5 = com.alipay.sdk.m.z.a.a((Map)map, "rpcVersion", "");
            final String a6 = a(a2, a3);
            final String securityToken = UmidSdkWrapper.getSecurityToken(a2);
            final String d2 = h.d(a2);
            if (com.alipay.sdk.m.z.a.b(a4)) {
                d.c = a4;
            }
            else {
                d.c = a6;
            }
            d.d = securityToken;
            d.e = d2;
            d.a = "android";
            final com.alipay.apmobilesecuritysdk.e.c c = com.alipay.apmobilesecuritysdk.e.d.c(a2);
            String a7;
            String c2;
            if (c != null) {
                a7 = c.a;
                c2 = c.c;
            }
            else {
                c2 = "";
                a7 = "";
            }
            String c3 = c2;
            String a8 = a7;
            if (com.alipay.sdk.m.z.a.a(a7)) {
                final b c4 = com.alipay.apmobilesecuritysdk.e.a.c(a2);
                c3 = c2;
                a8 = a7;
                if (c4 != null) {
                    a8 = c4.a;
                    c3 = c4.c;
                }
            }
            final com.alipay.apmobilesecuritysdk.e.c b = com.alipay.apmobilesecuritysdk.e.d.b();
            String c5;
            if (b != null) {
                a = b.a;
                c5 = b.c;
            }
            else {
                c5 = "";
            }
            String a9 = a;
            String c6 = c5;
            if (com.alipay.sdk.m.z.a.a(a)) {
                final b b2 = com.alipay.apmobilesecuritysdk.e.a.b();
                a9 = a;
                c6 = c5;
                if (b2 != null) {
                    a9 = b2.a;
                    c6 = b2.c;
                }
            }
            d.h = a8;
            d.g = a9;
            d.j = a5;
            if (com.alipay.sdk.m.z.a.a(a8)) {
                d.b = a9;
                c3 = c6;
            }
            else {
                d.b = a8;
            }
            d.i = c3;
            d.f = e.a(a2, map);
            return com.alipay.sdk.m.d0.d.b(this.a, this.b.c()).a(d);
        }
        finally {
            final Throwable t;
            t.printStackTrace();
            com.alipay.apmobilesecuritysdk.c.a.a(t);
            return null;
        }
    }
    
    public static String b(final Context context) {
        try {
            final String b = i.b();
            if (!com.alipay.sdk.m.z.a.a(b)) {
                return b;
            }
            final com.alipay.apmobilesecuritysdk.e.c b2 = com.alipay.apmobilesecuritysdk.e.d.b(context);
            if (b2 != null) {
                i.a(b2);
                final String a = b2.a;
                if (com.alipay.sdk.m.z.a.b(a)) {
                    return a;
                }
            }
            final b b3 = com.alipay.apmobilesecuritysdk.e.a.b(context);
            if (b3 != null) {
                i.a(b3);
                final String a2 = b3.a;
                if (com.alipay.sdk.m.z.a.b(a2)) {
                    return a2;
                }
            }
            return "";
        }
        finally {
            return "";
        }
    }
    
    public static void b() {
        int n = 0;
        while (true) {
            if (n >= 5) {
                return;
            }
            try {
                final String s = (new String[] { "device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1" })[n];
                final File externalStorageDirectory = Environment.getExternalStorageDirectory();
                final StringBuilder sb = new StringBuilder(".SystemConfig/");
                sb.append(s);
                final File file = new File(externalStorageDirectory, sb.toString());
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
                ++n;
                continue;
            }
            finally {}
        }
    }
    
    public final int a(final Map<String, String> map) {
        try {
            com.alipay.apmobilesecuritysdk.c.a.a(this.a, com.alipay.sdk.m.z.a.a((Map)map, "tid", ""), com.alipay.sdk.m.z.a.a((Map)map, "utdid", ""), a(this.a));
            final String a = com.alipay.sdk.m.z.a.a((Map)map, "appName", "");
            b();
            b(this.a);
            a(this.a, a);
            i.a();
            final boolean a2 = a();
            final int n = 0;
            boolean b = false;
            Label_0269: {
                Label_0267: {
                    if (!a2 && !com.alipay.apmobilesecuritysdk.common.a.a(this.a)) {
                        e.a();
                        if (!(com.alipay.sdk.m.z.a.a(e.b(this.a, map), i.c()) ^ true)) {
                            final String a3 = com.alipay.sdk.m.z.a.a((Map)map, "tid", "");
                            final String a4 = com.alipay.sdk.m.z.a.a((Map)map, "utdid", "");
                            if (!com.alipay.sdk.m.z.a.b(a3) || com.alipay.sdk.m.z.a.a(a3, i.d())) {
                                if (!com.alipay.sdk.m.z.a.b(a4) || com.alipay.sdk.m.z.a.a(a4, i.e())) {
                                    if (i.a(this.a, a)) {
                                        if (!com.alipay.sdk.m.z.a.a(a(this.a, a))) {
                                            if (!com.alipay.sdk.m.z.a.a(b(this.a))) {
                                                break Label_0267;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if (!com.alipay.sdk.m.z.a.a(a(this.a, a))) {
                        if (!com.alipay.sdk.m.z.a.a(b(this.a))) {
                            break Label_0267;
                        }
                    }
                    b = true;
                    break Label_0269;
                }
                b = false;
            }
            final Context a5 = this.a;
            com.alipay.sdk.m.a0.b.a((f)APSecuritySdk.getInstance(this.a));
            h.b(a5, String.valueOf((Object)com.alipay.sdk.m.a0.b.m()));
            int c2 = 0;
            Label_0710: {
                if (b) {
                    new com.alipay.apmobilesecuritysdk.c.b();
                    UmidSdkWrapper.startUmidTaskSync(this.a, com.alipay.apmobilesecuritysdk.b.a.a().b());
                    final c b2 = this.b(map);
                    int c;
                    if (b2 != null) {
                        c = b2.c();
                    }
                    else {
                        c = 2;
                    }
                    if (c != 1) {
                        if (c == 3) {
                            c2 = 1;
                            break Label_0710;
                        }
                        String string;
                        if (b2 != null) {
                            final StringBuilder sb = new StringBuilder("Server error, result:");
                            sb.append(((com.alipay.sdk.m.f0.a)b2).b);
                            string = sb.toString();
                        }
                        else {
                            string = "Server error, returned null";
                        }
                        com.alipay.apmobilesecuritysdk.c.a.a(string);
                        if (com.alipay.sdk.m.z.a.a(a(this.a, a))) {
                            c2 = 4;
                            break Label_0710;
                        }
                    }
                    else {
                        h.a(this.a, b2.b());
                        h.d(this.a, b2.a());
                        h.e(this.a, b2.g);
                        h.a(this.a, b2.h);
                        h.f(this.a, b2.i);
                        h.g(this.a, b2.k);
                        i.c(e.b(this.a, map));
                        i.a(a, b2.d);
                        i.b(b2.c);
                        i.d(b2.j);
                        String s = com.alipay.sdk.m.z.a.a((Map)map, "tid", "");
                        if (com.alipay.sdk.m.z.a.b(s) && !com.alipay.sdk.m.z.a.a(s, i.d())) {
                            i.e(s);
                        }
                        else {
                            s = i.d();
                        }
                        i.e(s);
                        String s2 = com.alipay.sdk.m.z.a.a((Map)map, "utdid", "");
                        if (com.alipay.sdk.m.z.a.b(s2) && !com.alipay.sdk.m.z.a.a(s2, i.e())) {
                            i.f(s2);
                        }
                        else {
                            s2 = i.e();
                        }
                        i.f(s2);
                        i.a();
                        com.alipay.apmobilesecuritysdk.e.d.a(this.a, i.g());
                        com.alipay.apmobilesecuritysdk.e.d.a();
                        com.alipay.apmobilesecuritysdk.e.a.a(this.a, new b(i.b(), i.c(), i.f()));
                        com.alipay.apmobilesecuritysdk.e.a.a();
                        g.a(this.a, a, i.a(a));
                        g.a();
                        h.a(this.a, a, System.currentTimeMillis());
                    }
                }
                c2 = 0;
            }
            this.c = c2;
            final com.alipay.sdk.m.g0.a b3 = com.alipay.sdk.m.d0.d.b(this.a, this.b.c());
            final Context a6 = this.a;
            NetworkInfo activeNetworkInfo = null;
            final ConnectivityManager connectivityManager = (ConnectivityManager)a6.getSystemService("connectivity");
            if (connectivityManager != null) {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            }
            int n2 = n;
            if (activeNetworkInfo != null) {
                n2 = n;
                if (activeNetworkInfo.isConnected()) {
                    n2 = n;
                    if (activeNetworkInfo.getType() == 1) {
                        n2 = 1;
                    }
                }
            }
            if (n2 != 0 && h.c(a6)) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(a6.getFilesDir().getAbsolutePath());
                sb2.append("/log/ap");
                new com.alipay.sdk.m.c0.b(sb2.toString(), b3).a();
            }
        }
        catch (final Exception ex) {
            com.alipay.apmobilesecuritysdk.c.a.a((Throwable)ex);
        }
        return this.c;
    }
}
