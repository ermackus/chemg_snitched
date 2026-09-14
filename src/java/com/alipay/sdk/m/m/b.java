package com.alipay.sdk.m.m;

import android.os.Build;
import com.alipay.sdk.m.u.n;
import android.content.SharedPreferences;
import com.alipay.sdk.m.u.c;
import java.util.Random;
import android.content.pm.PackageInfo;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import android.content.Context;
import com.alipay.sdk.m.j.a;

public class b
{
    public static final String d = "virtualImeiAndImsi";
    public static final String e = "virtual_imei";
    public static final String f = "virtual_imsi";
    public static volatile b g;
    public String a;
    public String b;
    public String c;
    
    public b() {
        this.b = "sdk-and-lite";
        final String a = com.alipay.sdk.m.j.a.a();
        if (!com.alipay.sdk.m.j.a.b()) {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.b);
            sb.append('_');
            sb.append(a);
            this.b = sb.toString();
        }
    }
    
    public static String a(final Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }
    
    public static void a(final String f) {
        synchronized (b.class) {
            if (TextUtils.isEmpty((CharSequence)f)) {
                return;
            }
            PreferenceManager.getDefaultSharedPreferences(com.alipay.sdk.m.s.b.d().b()).edit().putString("trideskey", f).apply();
            com.alipay.sdk.m.l.a.f = f;
        }
    }
    
    public static b b() {
        synchronized (b.class) {
            if (b.g == null) {
                b.g = new b();
            }
            return b.g;
        }
    }
    
    public static String b(final Context context) {
        if (context == null) {
            return "";
        }
        try {
            final StringBuilder sb = new StringBuilder();
            final String packageName = context.getPackageName();
            final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            sb.append("(");
            sb.append(packageName);
            sb.append(";");
            sb.append(packageInfo.versionCode);
            sb.append(")");
            return sb.toString();
        }
        catch (final Exception ex) {
            return "";
        }
    }
    
    public static String c() {
        final String hexString = Long.toHexString(System.currentTimeMillis());
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder();
        sb.append(hexString);
        sb.append(random.nextInt(9000) + 1000);
        return sb.toString();
    }
    
    public static String d() {
        return "-1;-1";
    }
    
    public static String e() {
        return "1";
    }
    
    public static String f() {
        final Context b = com.alipay.sdk.m.s.b.d().b();
        final SharedPreferences sharedPreferences = b.getSharedPreferences("virtualImeiAndImsi", 0);
        String s;
        if (TextUtils.isEmpty((CharSequence)(s = sharedPreferences.getString("virtual_imei", (String)null)))) {
            if (TextUtils.isEmpty((CharSequence)com.alipay.sdk.m.t.a.a(b).d())) {
                s = c();
            }
            else {
                s = c.b(b).b();
            }
            sharedPreferences.edit().putString("virtual_imei", s).apply();
        }
        return s;
    }
    
    public static String g() {
        final Context b = com.alipay.sdk.m.s.b.d().b();
        final SharedPreferences sharedPreferences = b.getSharedPreferences("virtualImeiAndImsi", 0);
        String s;
        if (TextUtils.isEmpty((CharSequence)(s = sharedPreferences.getString("virtual_imsi", (String)null)))) {
            if (TextUtils.isEmpty((CharSequence)com.alipay.sdk.m.t.a.a(b).d())) {
                final String c = com.alipay.sdk.m.s.b.d().c();
                if (!TextUtils.isEmpty((CharSequence)c) && c.length() >= 18) {
                    s = c.substring(3, 18);
                }
                else {
                    s = c();
                }
            }
            else {
                s = c.b(b).c();
            }
            sharedPreferences.edit().putString("virtual_imsi", s).apply();
        }
        return s;
    }
    
    public static String h() {
        return "00";
    }
    
    public static String i() {
        return "-1";
    }
    
    public String a() {
        return this.c;
    }
    
    public String a(final com.alipay.sdk.m.s.a a, final com.alipay.sdk.m.t.a a2, final boolean b) {
        final Context b2 = b.d().b();
        final c b3 = com.alipay.sdk.m.u.c.b(b2);
        if (TextUtils.isEmpty((CharSequence)this.a)) {
            final String f = n.f();
            final String e = n.e();
            final String c = n.c(b2);
            final String e2 = n.e(b2);
            final String f2 = n.f(b2);
            final String a3 = a(b2);
            final StringBuilder sb = new StringBuilder();
            sb.append("Msp/15.8.17");
            sb.append(" (");
            sb.append(f);
            sb.append(";");
            sb.append(e);
            sb.append(";");
            sb.append(c);
            sb.append(";");
            sb.append(e2);
            sb.append(";");
            sb.append(f2);
            sb.append(";");
            sb.append(a3);
            this.a = sb.toString();
        }
        final String b4 = com.alipay.sdk.m.u.c.d(b2).b();
        final String b5 = n.b(b2);
        final String e3 = e();
        final String c2 = b3.c();
        final String b6 = b3.b();
        final String g = g();
        final String f3 = f();
        if (a2 != null) {
            this.c = a2.c();
        }
        final String replace = Build.MANUFACTURER.replace((CharSequence)";", (CharSequence)" ");
        final String replace2 = Build.MODEL.replace((CharSequence)";", (CharSequence)" ");
        final boolean e4 = b.e();
        final String d = b3.d();
        final String i = i();
        final String h = h();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(this.a);
        sb2.append(";");
        sb2.append(b4);
        sb2.append(";");
        sb2.append(b5);
        sb2.append(";");
        sb2.append(e3);
        sb2.append(";");
        sb2.append(c2);
        sb2.append(";");
        sb2.append(b6);
        sb2.append(";");
        sb2.append(this.c);
        sb2.append(";");
        sb2.append(replace);
        sb2.append(";");
        sb2.append(replace2);
        sb2.append(";");
        sb2.append(e4);
        sb2.append(";");
        sb2.append(d);
        sb2.append(";");
        sb2.append(d());
        sb2.append(";");
        sb2.append(this.b);
        sb2.append(";");
        sb2.append(g);
        sb2.append(";");
        sb2.append(f3);
        sb2.append(";");
        sb2.append(i);
        sb2.append(";");
        sb2.append(h);
        if (a2 != null) {
            final String a4 = com.alipay.sdk.m.w.b.a(a, b2, com.alipay.sdk.m.t.a.a(b2).d(), com.alipay.sdk.m.w.b.c(a, b2));
            if (!TextUtils.isEmpty((CharSequence)a4)) {
                sb2.append(";;;");
                sb2.append(a4);
            }
        }
        sb2.append(")");
        return sb2.toString();
    }
}
