package com.alipay.sdk.m.c;

import android.content.ServiceConnection;
import android.content.Intent;
import android.content.Context;

public class b implements com.alipay.sdk.m.b.b
{
    public static final String a = "com.uodis.opendevice.aidl.OpenDeviceIdentifierService";
    public static final int b = 1;
    public static final int c = 2;
    
    public String a(final Context context) {
        final b.b$b b$b = new b.b$b((b$a)null);
        final Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        if (context.bindService(intent, (ServiceConnection)b$b, 1)) {
            try {
                return new b.b$c(b$b.a()).d();
            }
            catch (final Exception ex) {
                context.unbindService((ServiceConnection)b$b);
            }
            finally {
                context.unbindService((ServiceConnection)b$b);
            }
        }
        return null;
    }
}
