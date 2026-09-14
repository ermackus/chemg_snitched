package com.kingagroot.kingdraw.ui.jpushbase;

import android.content.IntentFilter;
import android.content.Intent;
import android.content.BroadcastReceiver;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.ui.BaseFragment;

public class JpushBaseFragment extends BaseFragment
{
    private LocalBroadcastManager broadcastManager;
    BroadcastReceiver receiver;
    
    public JpushBaseFragment() {
        this.receiver = (BroadcastReceiver)new JpushBaseFragment$1(this);
    }
    
    protected void onNewJpushMsg(final Intent intent) {
    }
    
    public void onPause() {
        super.onPause();
        this.broadcastManager.unregisterReceiver(this.receiver);
    }
    
    public void onResume() {
        super.onResume();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("jpush_new_messages_action");
        if (this.broadcastManager == null) {
            this.broadcastManager = LocalBroadcastManager.getInstance(this.getContext());
        }
        this.broadcastManager.registerReceiver(this.receiver, new IntentFilter(intentFilter));
    }
}
