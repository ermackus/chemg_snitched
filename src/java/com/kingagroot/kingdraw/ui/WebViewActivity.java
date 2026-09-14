package com.kingagroot.kingdraw.ui;

import android.view.KeyEvent;
import android.content.Context;
import com.goodsrc.library.utils.NetworkUtil;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.content.Intent;
import com.goodsrc.ui.library.widget.notch.NotchCallBack;
import android.view.View;
import android.app.Activity;
import com.goodsrc.ui.library.widget.notch.NotchContext;
import android.view.View$OnClickListener;
import java.util.HashMap;
import android.webkit.WebView;
import com.kingagroot.kingdraw.utils.GToolBar;
import java.util.Map;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.widget.LinearLayout;
import android.widget.ImageView;
import com.goodsrc.ui.library.BaseActivity;

public class WebViewActivity extends BaseActivity
{
    public static final String ASSET_NAME_KEY = "asset_name_key";
    public static final String HTML_KEY = "html_key";
    public static final String INTENT_KEY_OPENSHARFILE = "intent_key_opensharfile";
    public static final String TITLE_KEY = "title_key";
    public static final String URL_KEY = "url_key";
    private ImageView ivNoNetwork;
    private LinearLayout llNotch;
    private boolean openSharFile;
    private SwipeRefreshLayout swipeRefreshLayout;
    private final Map<String, String> titles;
    private GToolBar toolbar;
    private WebView webview;
    
    public WebViewActivity() {
        this.titles = (Map<String, String>)new HashMap();
        this.openSharFile = true;
    }
    
    private void init() {
        this.toolbar = (GToolBar)this.findViewById(2131297503);
        this.webview = (WebView)this.findViewById(2131297762);
        this.ivNoNetwork = (ImageView)this.findViewById(2131296932);
        (this.swipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(2131297437)).setEnabled(false);
        this.toolbar.setsetNavigationOnBackClickListener((View$OnClickListener)new WebViewActivity$1(this));
        this.toolbar.setsetNavigationOnCloseClickListener((View$OnClickListener)new WebViewActivity$2(this));
        this.llNotch = (LinearLayout)this.findViewById(2131297012);
        if (this.getResources().getConfiguration().orientation == 2) {
            final NotchContext notchContext = new NotchContext((Activity)this, (View)this.llNotch);
            notchContext.checkNotchInScreen((NotchCallBack)new WebViewActivity$3(this, notchContext));
        }
        this.openSharFile = this.getIntent().getBooleanExtra("intent_key_opensharfile", true);
    }
    
    private void initData() {
        final Intent intent = this.getIntent();
        if (intent.hasExtra("title_key")) {
            this.setTitle((CharSequence)intent.getStringExtra("title_key"));
        }
        if (intent.hasExtra("url_key")) {
            this.webview.loadUrl(intent.getStringExtra("url_key"));
        }
        else if (intent.hasExtra("html_key")) {
            this.webview.loadDataWithBaseURL((String)null, intent.getStringExtra("html_key"), "text/html", "utf-8", (String)null);
        }
        else if (intent.hasExtra("asset_name_key")) {
            final String stringExtra = intent.getStringExtra("asset_name_key");
            final WebView webview = this.webview;
            final StringBuilder sb = new StringBuilder();
            sb.append("file:///android_asset/");
            sb.append(stringExtra);
            webview.loadUrl(sb.toString());
        }
    }
    
    private void initSet() {
        final WebSettings settings = this.webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSavePassword(false);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setMixedContentMode(0);
        this.webview.setWebViewClient((WebViewClient)new WebViewActivity$4(this));
        this.webview.setWebChromeClient((WebChromeClient)new WebViewActivity$5(this));
    }
    
    public boolean isOpenSharFile() {
        return this.openSharFile;
    }
    
    public void onBackPressed() {
        if (this.webview.canGoBack()) {
            this.webview.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492939);
        this.init();
        if (NetworkUtil.isNetworkConnected((Context)this)) {
            this.ivNoNetwork.setVisibility(8);
            this.swipeRefreshLayout.setVisibility(0);
            this.initSet();
            this.initData();
        }
        else {
            this.ivNoNetwork.setVisibility(0);
            this.swipeRefreshLayout.setVisibility(8);
            this.setTitle((CharSequence)this.getString(2131821065));
        }
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            this.onBackPressed();
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public void setTitle(final CharSequence charSequence) {
        this.toolbar.setTitle(charSequence.toString());
    }
}
