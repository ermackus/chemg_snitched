package com.alipay.sdk.m.u;

import android.text.TextUtils;
import android.net.NetworkInfo;
import com.alipay.sdk.m.s.a;
import com.alipay.sdk.m.w.b;
import android.content.Context;

public class c
{
    public static final String a = "00:00:00:00:00:00";
    public static c b;
    
    public c(final Context context) {
    }
    
    public static String a(final Context context) {
        return b(context).a().substring(0, 8);
    }
    
    public static c b(final Context context) {
        if (c.b == null) {
            c.b = new c(context);
        }
        return c.b;
    }
    
    public static String c(final Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        }
        finally {
            return "";
        }
    }
    
    public static g d(final Context context) {
        try {
            final NetworkInfo a = com.alipay.sdk.m.w.b.a(null, context);
            if (a != null && a.getType() == 0) {
                return g.a(a.getSubtype());
            }
            if (a != null && a.getType() == 1) {
                return g.c;
            }
            return g.r;
        }
        catch (final Exception ex) {
            return g.r;
        }
    }
    
    public String a() {
        final String b = this.b();
        final StringBuilder sb = new StringBuilder();
        sb.append(b);
        sb.append("|");
        final String string = sb.toString();
        final String c = this.c();
        String s;
        if (TextUtils.isEmpty((CharSequence)c)) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            sb2.append("000000000000000");
            s = sb2.toString();
        }
        else {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(string);
            sb3.append(c);
            s = sb3.toString();
        }
        return s;
    }
    
    public String b() {
        return "000000000000000";
    }
    
    public String c() {
        return "000000000000000";
    }
    
    public String d() {
        return "00:00:00:00:00:00";
    }
}
