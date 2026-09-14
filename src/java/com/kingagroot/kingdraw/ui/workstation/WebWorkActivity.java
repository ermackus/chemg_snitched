package com.kingagroot.kingdraw.ui.workstation;

import java.util.List;
import android.view.KeyEvent;
import android.content.IntentFilter;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.goodsrc.library.utils.UriUtil;
import com.luck.picture.lib.entity.LocalMedia;
import android.content.Intent;
import com.kingagroot.kingdraw.widget.jsweb.OnJsWebListener;
import android.webkit.WebChromeClient;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import com.luck.picture.lib.engine.ImageEngine;
import com.kingagroot.kingdraw.utils.GlideEngine;
import com.luck.picture.lib.config.SelectMimeType;
import androidx.appcompat.app.AppCompatActivity;
import com.luck.picture.lib.basic.PictureSelector;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;
import com.kingagroot.kingdraw.utils.GToolBar;
import android.content.BroadcastReceiver;
import android.net.Uri;
import android.webkit.ValueCallback;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.goodsrc.ui.library.BaseActivity;

public class WebWorkActivity extends BaseActivity
{
    public static String INTENT_KEY_APPID = "intent_key_appid";
    public static String INTENT_KEY_TITLE = "intent_key_title";
    public static String URL_DATA = "URL_DATA";
    private LocalBroadcastManager broadcastManager;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private ValueCallback<Uri> mUploadMessage;
    BroadcastReceiver receiver;
    private GToolBar toolbar;
    private JsWebView webView;
    
    public WebWorkActivity() {
        this.receiver = (BroadcastReceiver)new WebWorkActivity$1(this);
    }
    
    private void choosePic() {
        int language;
        if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_EN)) {
            language = 2;
        }
        else {
            language = 0;
        }
        PictureSelector.create((AppCompatActivity)this).openGallery(SelectMimeType.ofImage()).setImageEngine((ImageEngine)GlideEngine.createGlideEngine()).setLanguage(language).isDisplayCamera(true).setRequestedOrientation(-1).setImageSpanCount(4).setSelectionMode(1).isMaxSelectEnabledMask(true).isPageStrategy(true).isPreviewImage(true).isDirectReturnSingle(true).forResult(188);
    }
    
    private void initView() {
        this.toolbar = (GToolBar)this.findViewById(2131297503);
        this.webView = (JsWebView)this.findViewById(2131297762);
        this.toolbar.setsetNavigationOnBackClickListener((View$OnClickListener)new _$$Lambda$WebWorkActivity$eH47cpT7zgMH2DQFojSqODIpKvI(this));
        this.toolbar.setsetNavigationOnCloseClickListener((View$OnClickListener)new _$$Lambda$WebWorkActivity$tOGy54V_V443G6U_GLW1tpdShEw(this));
        final String stringExtra = this.getIntent().getStringExtra(WebWorkActivity.URL_DATA);
        final String stringExtra2 = this.getIntent().getStringExtra(WebWorkActivity.INTENT_KEY_TITLE);
        final String stringExtra3 = this.getIntent().getStringExtra(WebWorkActivity.INTENT_KEY_APPID);
        if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
            this.webView.loadUrl(stringExtra);
        }
        this.webView.setAppId(stringExtra3);
        if (!TextUtils.isEmpty((CharSequence)stringExtra2)) {
            this.setTitle((CharSequence)stringExtra2);
        }
        this.webView.setWebChromeClient((WebChromeClient)new WebWorkActivity.WebWorkActivity$MyWebChromeClient(this, (WebWorkActivity$1)null));
        this.webView.setOnJsWebListener((OnJsWebListener)new WebWorkActivity$2(this));
    }
    
    private void onActivityResultAboveL(final Intent intent) {
        Uri[] array;
        if (intent != null) {
            array = new Uri[] { UriUtil.File2Uri(((LocalMedia)((List)PictureSelector.obtainSelectorList(intent)).get(0)).getRealPath()) };
        }
        else {
            array = null;
        }
        this.mUploadCallbackAboveL.onReceiveValue((Object)array);
        this.mUploadCallbackAboveL = null;
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (this.mUploadMessage == null && this.mUploadCallbackAboveL == null) {
            return;
        }
        if (n2 != -1) {
            final ValueCallback<Uri[]> mUploadCallbackAboveL = this.mUploadCallbackAboveL;
            if (mUploadCallbackAboveL != null) {
                mUploadCallbackAboveL.onReceiveValue((Object)null);
                this.mUploadCallbackAboveL = null;
            }
            final ValueCallback<Uri> mUploadMessage = this.mUploadMessage;
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue((Object)null);
                this.mUploadMessage = null;
            }
            return;
        }
        if (n == 188) {
            Uri file2Uri;
            if (intent != null) {
                file2Uri = UriUtil.File2Uri(((LocalMedia)((List)PictureSelector.obtainSelectorList(intent)).get(0)).getRealPath());
            }
            else {
                file2Uri = null;
            }
            if (this.mUploadCallbackAboveL != null) {
                this.onActivityResultAboveL(intent);
            }
            else {
                final ValueCallback<Uri> mUploadMessage2 = this.mUploadMessage;
                if (mUploadMessage2 != null) {
                    mUploadMessage2.onReceiveValue((Object)file2Uri);
                    this.mUploadMessage = null;
                }
            }
        }
    }
    
    public void onBackPressed() {
        if (!JsWebView.netErrCn.equals((Object)this.webView.getUrl()) && !JsWebView.netErrEn.equals((Object)this.webView.getUrl())) {
            if (this.webView.canGoBack()) {
                this.webView.goBack();
            }
            else {
                super.onBackPressed();
            }
        }
        else {
            super.onBackPressed();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492940);
        this.initView();
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("INTENT_ACTION_REFRESH");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    protected void onDestroy() {
        super.onDestroy();
        final JsWebView webView = this.webView;
        if (webView != null) {
            webView.finish();
        }
        final LocalBroadcastManager broadcastManager = this.broadcastManager;
        if (broadcastManager != null) {
            final BroadcastReceiver receiver = this.receiver;
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
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
