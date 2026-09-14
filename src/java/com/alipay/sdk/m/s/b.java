package com.alipay.sdk.m.s;

import java.io.File;
import android.content.Context;

public class b
{
    public static b b;
    public Context a;
    
    public static b d() {
        if (com.alipay.sdk.m.s.b.b == null) {
            com.alipay.sdk.m.s.b.b = new b();
        }
        return com.alipay.sdk.m.s.b.b;
    }
    
    public static boolean e() {
        for (int i = 0; i < 10; ++i) {
            if (new File((new String[] { "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su" })[i]).exists()) {
                return true;
            }
        }
        return false;
    }
    
    public com.alipay.sdk.m.m.b a() {
        return com.alipay.sdk.m.m.b.b();
    }
    
    public void a(final Context context) {
        com.alipay.sdk.m.m.b.b();
        this.a = context.getApplicationContext();
    }
    
    public Context b() {
        return this.a;
    }
    
    public String c() {
        return com.alipay.sdk.m.w.b.c(null, this.a);
    }
}
