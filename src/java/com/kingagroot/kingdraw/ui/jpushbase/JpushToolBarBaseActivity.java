package com.kingagroot.kingdraw.ui.jpushbase;

import android.content.Context;
import android.content.IntentFilter;
import android.content.Intent;
import android.content.BroadcastReceiver;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.goodsrc.ui.library.ToolBarActivity;

public class JpushToolBarBaseActivity extends ToolBarActivity
{
    private LocalBroadcastManager broadcastManager;
    BroadcastReceiver receiver;
    
    public JpushToolBarBaseActivity() {
        this.receiver = (BroadcastReceiver)new JpushToolBarBaseActivity$1(this);
    }
    
    protected void onNewJpushMsg(final Intent intent) {
    }
    
    protected void onPause() {
        super.onPause();
        this.broadcastManager.unregisterReceiver(this.receiver);
    }
    
    protected void onResume() {
        super.onResume();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("jpush_new_messages_action");
        if (this.broadcastManager == null) {
            this.broadcastManager = LocalBroadcastManager.getInstance((Context)this);
        }
        this.broadcastManager.registerReceiver(this.receiver, new IntentFilter(intentFilter));
    }
}
