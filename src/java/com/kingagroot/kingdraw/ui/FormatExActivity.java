package com.kingagroot.kingdraw.ui;

import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import com.kingagroot.kingdraw.config.APIConfig;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.webkit.WebView;
import android.view.View$OnClickListener;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.goodsrc.ui.library.BaseActivity;

public class FormatExActivity extends BaseActivity
{
    private SwipeRefreshLayout swipeRefreshLayout;
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492907);
        final Toolbar supportActionBar = (Toolbar)this.findViewById(2131297503);
        supportActionBar.setTitle((CharSequence)this.getResources().getString(2131820818));
        this.setSupportActionBar(supportActionBar);
        supportActionBar.setNavigationOnClickListener((View$OnClickListener)new _$$Lambda$FormatExActivity$DyjLS_aPrPZH_oVbMeIEZ5Cqvu4(this));
        (this.swipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(2131297437)).setEnabled(false);
        final WebView webView = (WebView)this.findViewById(2131297754);
        if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
            webView.loadUrl(APIConfig.FORMAT_ZH);
        }
        else {
            webView.loadUrl(APIConfig.FORMAT_EN);
        }
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.setWebViewClient((WebViewClient)new FormatExActivity$1(this));
    }
}
