package com.kingagroot.kingdraw.ui.workstation;

import java.util.List;
import android.view.MenuItem;
import android.content.Context;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import com.kingagroot.kingdraw.config.NetConfig;
import android.os.Bundle;
import com.goodsrc.library.utils.UriUtil;
import com.luck.picture.lib.entity.LocalMedia;
import android.content.Intent;
import android.webkit.WebSettings;
import com.luck.picture.lib.engine.ImageEngine;
import com.kingagroot.kingdraw.utils.GlideEngine;
import com.luck.picture.lib.config.SelectMimeType;
import androidx.appcompat.app.AppCompatActivity;
import com.luck.picture.lib.basic.PictureSelector;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;
import android.net.Uri;
import android.webkit.ValueCallback;
import com.kingagroot.kingdraw.widget.jsweb.OnJsWebListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class CreateGroupActivity extends ToolBarActivity implements OnJsWebListener
{
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private ValueCallback<Uri> mUploadMessage;
    public JsWebView webView;
    
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
    
    private void initWebSetting() {
        final WebSettings settings = this.webView.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(1);
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
        (this.webView = (JsWebView)this.findViewById(2131297756)).loadUrl(NetConfig.jsGroupCreate);
        this.initWebSetting();
        this.webView.setOnJsWebListener((OnJsWebListener)this);
        this.webView.setWebChromeClient((WebChromeClient)new CreateGroupActivity.CreateGroupActivity$MyWebChromeClient(this, (CreateGroupActivity$1)null));
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
    
    public void onPointerCaptureChanged(final boolean b) {
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
