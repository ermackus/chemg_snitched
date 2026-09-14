package com.kingagroot.kingdraw.ui;

import com.kingagroot.kingdraw.NewMainActivity;
import android.content.Intent;
import com.kingagroot.kingdraw.config.NetConfig;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.IntentFilter;
import android.content.Context;
import android.os.Bundle;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;
import android.content.BroadcastReceiver;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.widget.jsweb.OnJsWebListener;
import com.kingagroot.kingdraw.ui.jpushbase.JpushBaseFragment;

public class MessageWebFragment extends JpushBaseFragment implements OnJsWebListener
{
    private LocalBroadcastManager broadcastManager;
    BroadcastReceiver receiver;
    private JsWebView webMessage;
    
    public MessageWebFragment() {
        this.receiver = (BroadcastReceiver)new MessageWebFragment$1(this);
    }
    
    public void onBackButtonShow(final boolean b) {
    }
    
    public void onCloseWeb() {
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this.requireActivity());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("login_data_changed");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final ViewGroup viewGroup2 = (ViewGroup)layoutInflater.inflate(2131493057, viewGroup, false);
        (this.webMessage = (JsWebView)viewGroup2.findViewById(2131297758)).setOnJsWebListener((OnJsWebListener)this);
        this.webMessage.loadUrl(NetConfig.jsMessageUrl);
        return (View)viewGroup2;
    }
    
    public void onDestroy() {
        super.onDestroy();
        final LocalBroadcastManager broadcastManager = this.broadcastManager;
        if (broadcastManager != null) {
            final BroadcastReceiver receiver = this.receiver;
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
        }
    }
    
    public void onGetDeviceData() {
    }
    
    public void onGetUserToken() {
    }
    
    public void onLoadFinish() {
    }
    
    public void onLogout() {
    }
    
    public void onOpenAppUrl(final String s) {
    }
    
    public void onOpenUrl(final String s) {
        final Intent intent = new Intent(this.getContext(), (Class)MessageWebActivity.class);
        intent.putExtra(MessageWebActivity.URL, s);
        this.startActivity(intent);
    }
    
    public void onResume() {
        super.onResume();
        NewMainActivity.getMainActivity().getUnReadCount();
    }
    
    public void onSetBadge(final int n) {
    }
    
    public void onUpdateBadge() {
    }
    
    public void onUpdateTitle(final String s) {
    }
    
    public void onUserAccountChanged() {
    }
    
    public void onUserPwdChanged() {
    }
}
