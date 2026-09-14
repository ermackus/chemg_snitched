package com.kingagroot.kingdraw.ui.account;

import android.view.MenuItem;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.workstation.WebWorkActivity;
import com.kingagroot.kingdraw.base.MApplication;
import android.view.KeyEvent;
import com.kingagroot.kingdraw.config.NetConfig;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.content.Context;
import com.goodsrc.library.utils.NetworkUtil;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;
import com.kingagroot.kingdraw.widget.jsweb.OnJsWebListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class AccountWebActivity extends ToolBarActivity implements OnJsWebListener
{
    public JsWebView webView;
    
    public void onBackButtonShow(final boolean b) {
    }
    
    public void onBackPressed() {
        if (NetworkUtil.isNetworkConnected((Context)this)) {
            if (this.webView.canGoBack()) {
                this.webView.evaluateJavascript("kdH5.pageBack()", (ValueCallback)_$$Lambda$AccountWebActivity$8sETvcrcljWpS43_4b_7PiD_iDQ.INSTANCE);
            }
            else {
                this.finish();
            }
        }
        else {
            this.finish();
        }
    }
    
    public void onCloseWeb() {
        this.finish();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492915);
        (this.webView = (JsWebView)this.findViewById(2131297756)).loadUrl(NetConfig.jsAccountUrl);
        this.webView.setOnJsWebListener((OnJsWebListener)this);
    }
    
    public void onGetDeviceData() {
    }
    
    public void onGetUserToken() {
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            this.onBackPressed();
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public void onLoadFinish() {
    }
    
    public void onLogout() {
        MApplication.getInstance().userLogout();
    }
    
    public void onOpenAppUrl(final String s) {
    }
    
    public void onOpenUrl(final String s) {
        final Intent intent = new Intent((Context)this, (Class)WebWorkActivity.class);
        intent.putExtra(WebWorkActivity.URL_DATA, s);
        this.startActivity(intent);
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            this.onBackPressed();
        }
        return false;
    }
    
    public void onSetBadge(final int n) {
    }
    
    public void onUpdateBadge() {
    }
    
    public void onUpdateTitle(final String title) {
        this.setTitle((CharSequence)title);
    }
    
    public void onUserAccountChanged() {
    }
    
    public void onUserPwdChanged() {
    }
}
