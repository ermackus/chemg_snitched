package com.alipay.sdk.m.c;

import com.alipay.sdk.m.k0.a;
import android.content.ServiceConnection;
import android.content.Intent;
import android.content.Context;
import com.alipay.sdk.m.b.b;

public class g implements b
{
    public String a(final Context context) {
        final g.g$b g$b = new g.g$b((g$a)null);
        final Intent intent = new Intent();
        intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
        if (context.bindService(intent, (ServiceConnection)g$b, 1)) {
            try {
                return a.a.a(g$b.a()).a();
            }
            catch (final Exception ex) {
                context.unbindService((ServiceConnection)g$b);
            }
            finally {
                context.unbindService((ServiceConnection)g$b);
            }
        }
        return null;
    }
}
