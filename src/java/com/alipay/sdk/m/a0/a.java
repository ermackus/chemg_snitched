package com.alipay.sdk.m.a0;

import android.content.Context;

public final class a
{
    public static a a;
    
    static {
        com.alipay.sdk.m.a0.a.a = new a();
    }
    
    public static a a() {
        return com.alipay.sdk.m.a0.a.a;
    }
    
    public static String a(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
        }
        catch (final Exception ex) {
            return "0.0.0";
        }
    }
}
