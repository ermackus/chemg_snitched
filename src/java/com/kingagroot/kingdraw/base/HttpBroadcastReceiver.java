package com.kingagroot.kingdraw.base;

import com.kingagroot.kingdraw.NewMainActivity;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class HttpBroadcastReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals((Object)"kingagroot.intent.action.http_relogin") && MApplication.getInstance().isLogin()) {
            intent = new Intent(context, (Class)NewMainActivity.class);
            intent.setFlags(335544320);
            intent.putExtra("intent_key_login_out", true);
            context.startActivity(intent);
        }
    }
}
