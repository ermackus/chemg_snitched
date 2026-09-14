package com.kingagroot.kingdraw.ui;

import com.kingagroot.kingdraw.ui.workstation.WebWorkActivity;
import java.net.URISyntaxException;
import android.net.Uri;
import com.kingagroot.kingdraw.config.ShareData;
import java.io.Serializable;
import com.kingagroot.kingdraw.ui.workstation.WebStationActivity;
import com.kingagroot.kingdraw.ui.workstation.TemplateActivity;
import com.kingagroot.kingdraw.ui.workstation.PediasActivity;
import android.content.Intent;
import com.goodsrc.library.utils.NetworkUtil;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.kingdraw.ui.workstation.WorkStationModel;
import com.kingagroot.kingdraw.config.NetConfig;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.IntentFilter;
import android.content.Context;
import android.os.Bundle;
import com.kingagroot.kingdraw.utils.link.GroupListLink$OnGroupListener;
import com.kingagroot.kingdraw.utils.link.GroupListLink;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.kingdraw.NewMainActivity;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.BroadcastReceiver;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.widget.jsweb.OnJsWebListener;

public class WorkWebFragment extends BaseFragment implements OnJsWebListener
{
    static final boolean $assertionsDisabled = false;
    private LocalBroadcastManager broadcastManager;
    BroadcastReceiver receiver;
    private SwipeRefreshLayout swipeFresh;
    private JsWebView webWork;
    
    public WorkWebFragment() {
        this.receiver = (BroadcastReceiver)new WorkWebFragment$1(this);
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
        intentFilter.addAction("QUIT_GROUP");
        intentFilter.addAction("INTENT_ACTION_REFRESH");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2131493064, viewGroup, false);
        this.swipeFresh = (SwipeRefreshLayout)inflate.findViewById(2131297436);
        this.webWork = (JsWebView)inflate.findViewById(2131297763);
        this.swipeFresh.setOnRefreshListener((SwipeRefreshLayout$OnRefreshListener)new _$$Lambda$WorkWebFragment$8x0gEbjnkv0PlS96Xaxoqrm1goo(this));
        this.swipeFresh.setColorSchemeResources(new int[] { 2131099773, 2131099702 });
        this.webWork.loadUrl(NetConfig.jsWorkUrl);
        this.webWork.setOnJsWebListener((OnJsWebListener)this);
        this.webWork.setSwipeFresh(this.swipeFresh);
        return inflate;
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
        this.swipeFresh.setRefreshing(false);
    }
    
    public void onLogout() {
    }
    
    public void onOpenAppUrl(String s) {
        final WorkStationModel workStationModel = (WorkStationModel)GsonUtil.fromJson(s, (Class)WorkStationModel.class);
        final String httpToHttps = NetworkUtil.httpToHttps(workStationModel.getUrl());
        workStationModel.setUrl(httpToHttps);
        s = (String)new Intent();
        if (httpToHttps.startsWith(NetConfig.pediaUrl)) {
            ((Intent)s).setClass((Context)this.getActivity(), (Class)PediasActivity.class);
        }
        else if (httpToHttps.startsWith("https://chem.kingdraw")) {
            ((Intent)s).setClass((Context)this.getActivity(), (Class)TemplateActivity.class);
            ((Intent)s).putExtra(WebStationActivity.MODEL_DATA, (Serializable)workStationModel);
        }
        else {
            try {
                if (ShareData.getGatewayState()) {
                    ((Intent)s).setClass((Context)this.getActivity(), (Class)WebStationActivity.class);
                    ((Intent)s).putExtra(WebStationActivity.MODEL_DATA, (Serializable)workStationModel);
                }
                else if (JsWebView.checkDomain(httpToHttps)) {
                    ((Intent)s).setClass((Context)this.getActivity(), (Class)WebStationActivity.class);
                    ((Intent)s).putExtra(WebStationActivity.MODEL_DATA, (Serializable)workStationModel);
                }
                else {
                    ((Intent)s).setAction("android.intent.action.VIEW");
                    ((Intent)s).setData(Uri.parse(httpToHttps));
                }
            }
            catch (final URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
        this.startActivity((Intent)s);
    }
    
    public void onOpenUrl(final String s) {
        final Intent intent = new Intent((Context)this.getActivity(), (Class)WebWorkActivity.class);
        intent.putExtra(WebWorkActivity.URL_DATA, s);
        this.startActivity(intent);
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
    
    public void webRefresh() {
        final JsWebView webWork = this.webWork;
        if (webWork != null) {
            webWork.loadUrl(NetConfig.jsWorkUrl);
        }
    }
}
