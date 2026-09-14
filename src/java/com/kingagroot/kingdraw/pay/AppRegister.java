package com.kingagroot.kingdraw.pay;

import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class AppRegister extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        WXAPIFactory.createWXAPI(context, (String)null, false).registerApp("wxdbab8ce2bc2c4ed2");
    }
}
