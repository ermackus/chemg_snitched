package com.alipay.android.phone.mrpc.core;

import android.content.Context;

public final class s
{
    public static Boolean a;
    
    public static final boolean a(final Context context) {
        final Boolean a = s.a;
        if (a != null) {
            return a;
        }
        try {
            return s.a = ((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 0x2) != 0x0);
        }
        catch (final Exception ex) {
            return false;
        }
    }
}
