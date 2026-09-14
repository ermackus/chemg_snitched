package com.kingagroot.kingdraw.ui;

import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.workstation.WebWorkActivity;
import com.goodsrc.ui.library.widget.AppManager;
import android.view.KeyEvent;
import android.os.Bundle;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;
import com.kingagroot.kingdraw.widget.jsweb.OnJsWebListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class MessageWebActivity extends ToolBarActivity implements OnJsWebListener
{
    public static String URL = "URL";
    public JsWebView webView;
    
    public void onBackButtonShow(final boolean b) {
    }
    
    public void onBackPressed() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
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
        (this.webView = (JsWebView)this.findViewById(2131297756)).loadUrl(this.getIntent().getStringExtra(MessageWebActivity.URL));
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
    }
    
    public void onOpenAppUrl(final String s) {
    }
    
    public void onOpenUrl(final String s) {
        final Intent intent = new Intent((Context)AppManager.getInstance().getLastActivity(), (Class)WebWorkActivity.class);
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
