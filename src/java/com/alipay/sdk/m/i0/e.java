package com.alipay.sdk.m.i0;

import java.util.ArrayList;
import android.text.TextUtils;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class e extends BroadcastReceiver
{
    public final void onReceive(final Context context, final Intent intent) {
        if (context != null) {
            if (intent != null) {
                boolean contains = false;
                final int intExtra = intent.getIntExtra("openIdNotifyFlag", 0);
                f.a("shouldUpdateId, notifyFlag : ".concat(String.valueOf(intExtra)));
                Label_0097: {
                    if (intExtra == 1) {
                        if (!TextUtils.equals((CharSequence)intent.getStringExtra("openIdPackage"), (CharSequence)context.getPackageName())) {
                            break Label_0097;
                        }
                    }
                    else if (intExtra == 2) {
                        final ArrayList stringArrayListExtra = intent.getStringArrayListExtra("openIdPackageList");
                        if (stringArrayListExtra != null) {
                            contains = stringArrayListExtra.contains((Object)context.getPackageName());
                        }
                        break Label_0097;
                    }
                    else if (intExtra != 0) {
                        break Label_0097;
                    }
                    contains = true;
                }
                if (!contains) {
                    return;
                }
                final String stringExtra = intent.getStringExtra("openIdType");
                final f a = f.a();
                a a2;
                if ("oaid".equals((Object)stringExtra)) {
                    a2 = a.b;
                }
                else if ("vaid".equals((Object)stringExtra)) {
                    a2 = a.d;
                }
                else if ("aaid".equals((Object)stringExtra)) {
                    a2 = a.c;
                }
                else if ("udid".equals((Object)stringExtra)) {
                    a2 = a.a;
                }
                else {
                    a2 = null;
                }
                if (a2 == null) {
                    return;
                }
                a2.b();
            }
        }
    }
}
