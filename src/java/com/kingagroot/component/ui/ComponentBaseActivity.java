package com.kingagroot.component.ui;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.content.Context;
import com.goodsrc.library.utils.NetworkUtil;
import android.view.View;
import com.kingagroot.component.ui.widget.NetErrorView;
import com.kingagroot.component.ui.widget.LoadingDialog;
import android.widget.LinearLayout;
import com.goodsrc.ui.library.BaseActivity;

public class ComponentBaseActivity extends BaseActivity
{
    private LinearLayout content;
    private LoadingDialog loadingDialog;
    private NetErrorView netError;
    View root;
    
    protected boolean checkNetwork() {
        final boolean networkConnected = NetworkUtil.isNetworkConnected((Context)this);
        if (!networkConnected) {
            this.netError.setVisibility(0);
        }
        else {
            this.netError.setVisibility(8);
        }
        return networkConnected;
    }
    
    public void dismissLoading() {
        final LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final View inflate = LayoutInflater.from((Context)this).inflate(R$layout.component_activity_base, (ViewGroup)null);
        this.root = inflate;
        this.netError = (NetErrorView)inflate.findViewById(R$id.net_error);
        this.content = (LinearLayout)this.root.findViewById(R$id.base_content);
    }
    
    public void setContentView(final int n) {
        this.content.addView(LayoutInflater.from((Context)this).inflate(n, (ViewGroup)null), -1, -1);
        super.setContentView(this.root);
    }
    
    public void setContentView(final View view) {
        this.content.addView(view, -1, -1);
        super.setContentView(this.root);
    }
    
    public void showLoading(final String s) {
        this.showLoading(s, 0L);
    }
    
    public void showLoading(final String textMessage, final long n) {
        final LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null) {
            this.loadingDialog = new LoadingDialog((BaseActivity)this);
        }
        else {
            loadingDialog.dismiss();
        }
        this.loadingDialog.setCancelable(false);
        this.loadingDialog.setTextMessage(textMessage).setOnTouchOutside(false);
        if (n > 0L) {
            this.loadingDialog.show(n);
        }
        else {
            this.loadingDialog.show();
        }
    }
}
