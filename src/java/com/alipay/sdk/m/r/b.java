package com.alipay.sdk.m.r;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class b
{
    public a a;
    public String b;
    public String[] c;
    
    public b(final String b) {
        this.b = b;
    }
    
    public b(final String b, final a a) {
        this.b = b;
        this.a = a;
    }
    
    public static List<b> a(final JSONObject jsonObject) {
        final ArrayList list = new ArrayList();
        if (jsonObject == null) {
            return (List<b>)list;
        }
        final String[] a = a(jsonObject.optString("name", ""));
        for (int i = 0; i < a.length; ++i) {
            final a a2 = com.alipay.sdk.m.r.a.a(a[i]);
            if (a2 != com.alipay.sdk.m.r.a.b) {
                final b b = new b(a[i], a2);
                b.c = b(a[i]);
                ((List)list).add((Object)b);
            }
        }
        return (List<b>)list;
    }
    
    public static void a(final b b) {
        final String[] c = b.c();
        if (c.length != 3) {
            return;
        }
        if (TextUtils.equals((CharSequence)"tid", (CharSequence)c[0])) {
            final com.alipay.sdk.m.t.a a = com.alipay.sdk.m.t.a.a(b.d().b());
            if (!TextUtils.isEmpty((CharSequence)c[1])) {
                if (!TextUtils.isEmpty((CharSequence)c[2])) {
                    a.a(c[1], c[2]);
                }
            }
        }
    }
    
    public static String[] a(final String s) {
        String[] split;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            split = s.split(";");
        }
        else {
            split = null;
        }
        return split;
    }
    
    public static String[] b(final String s) {
        final ArrayList list = new ArrayList();
        final int index = s.indexOf(40);
        final int lastIndex = s.lastIndexOf(41);
        if (index != -1 && lastIndex != -1 && lastIndex > index) {
            final String[] split = s.substring(index + 1, lastIndex).split("' *, *'", -1);
            for (int length = split.length, i = 0; i < length; ++i) {
                ((List)list).add((Object)split[i].trim().replaceAll("'", "").replaceAll("\"", ""));
            }
            return (String[])((List)list).toArray((Object[])new String[0]);
        }
        return null;
    }
    
    public a a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
    
    public String[] c() {
        return this.c;
    }
}
