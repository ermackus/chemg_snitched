package com.alipay.sdk.m.c;

import android.text.TextUtils;
import android.os.Build;
import com.alipay.sdk.m.b.b;
import android.content.Context;

public class a
{
    public static final String a = "ro.build.version.emui";
    public static final String b = "hw_sc.build.platform.version";
    
    public static b a(final Context context) {
        final String brand = Build.BRAND;
        com.alipay.sdk.m.d.a.b("Device", "Brand", brand);
        if (TextUtils.isEmpty((CharSequence)brand)) {
            return null;
        }
        if (brand.equalsIgnoreCase("huawei") || brand.equalsIgnoreCase("honor") || brand.equalsIgnoreCase("\u534e\u4e3a")) {
            return (b)new com.alipay.sdk.m.c.b();
        }
        if (brand.equalsIgnoreCase("xiaomi") || brand.equalsIgnoreCase("redmi") || brand.equalsIgnoreCase("meitu") || brand.equalsIgnoreCase("\u5c0f\u7c73") || brand.equalsIgnoreCase("blackshark")) {
            return (b)new i();
        }
        if (brand.equalsIgnoreCase("vivo")) {
            return (b)new h();
        }
        if (brand.equalsIgnoreCase("oppo") || brand.equalsIgnoreCase("oneplus") || brand.equalsIgnoreCase("realme")) {
            return (b)new f();
        }
        if (brand.equalsIgnoreCase("lenovo") || brand.equalsIgnoreCase("zuk")) {
            return (b)new c();
        }
        if (brand.equalsIgnoreCase("nubia")) {
            return (b)new e();
        }
        if (brand.equalsIgnoreCase("samsung")) {
            return (b)new g();
        }
        if (a()) {
            return (b)new com.alipay.sdk.m.c.b();
        }
        if (!brand.equalsIgnoreCase("meizu") && !brand.equalsIgnoreCase("mblu")) {
            return null;
        }
        return (b)new d();
    }
    
    public static String a(String s) {
        try {
            s = (String)Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke((Object)null, new Object[] { s });
        }
        finally {
            s = "";
        }
        return s;
    }
    
    public static boolean a() {
        final String a = a("ro.build.version.emui");
        final String a2 = a("hw_sc.build.platform.version");
        return !TextUtils.isEmpty((CharSequence)a) || !TextUtils.isEmpty((CharSequence)a2);
    }
}
