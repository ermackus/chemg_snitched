package com.alipay.sdk.m.a0;

import android.os.Build$VERSION;
import android.os.Build;
import java.io.File;

public final class e
{
    public static e a;
    
    static {
        e.a = new e();
    }
    
    public static e a() {
        return e.a;
    }
    
    public static String a(String s, final String s2) {
        try {
            s = (String)Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke((Object)null, new Object[] { s, s2 });
            return s;
        }
        catch (final Exception ex) {
            return s2;
        }
    }
    
    public static String b() {
        return "android";
    }
    
    public static boolean c() {
        int n = 0;
        while (true) {
            if (n >= 5) {
                return false;
            }
            try {
                final StringBuilder sb = new StringBuilder();
                sb.append((new String[] { "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/" })[n]);
                sb.append("su");
                if (new File(sb.toString()).exists()) {
                    return true;
                }
                ++n;
                continue;
            }
            catch (final Exception ex) {
                return false;
            }
        }
    }
    
    public static boolean d() {
        boolean b = false;
        try {
            if (!Build.HARDWARE.contains((CharSequence)"goldfish") && !Build.PRODUCT.contains((CharSequence)"sdk") && !Build.FINGERPRINT.contains((CharSequence)"generic")) {
                return false;
            }
            b = true;
            return b;
        }
        catch (final Exception ex) {
            return b;
        }
    }
    
    public static String e() {
        return Build.BOARD;
    }
    
    public static String f() {
        return Build.BRAND;
    }
    
    public static String g() {
        return Build.DEVICE;
    }
    
    public static String h() {
        return Build.DISPLAY;
    }
    
    public static String i() {
        return Build$VERSION.INCREMENTAL;
    }
    
    public static String j() {
        return Build.MANUFACTURER;
    }
    
    public static String k() {
        return Build.MODEL;
    }
    
    public static String l() {
        return Build.PRODUCT;
    }
    
    public static String m() {
        return Build$VERSION.RELEASE;
    }
    
    public static String n() {
        return Build$VERSION.SDK;
    }
    
    public static String o() {
        return Build.TAGS;
    }
    
    public static String p() {
        return a("ro.kernel.qemu", "0");
    }
}
