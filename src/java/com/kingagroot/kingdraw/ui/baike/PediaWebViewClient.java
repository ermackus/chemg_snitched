package com.kingagroot.kingdraw.ui.baike;

import com.goodsrc.ui.library.BaseActivity;
import android.graphics.Bitmap;
import android.webkit.WebView;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.kingagroot.component.ui.widget.LoadingDialog;
import android.content.Context;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;

public class PediaWebViewClient extends BridgeWebViewClient
{
    private Context context;
    private LoadingDialog loadingDialog;
    
    public PediaWebViewClient(final BridgeWebView bridgeWebView) {
        super(bridgeWebView);
    }
    
    public PediaWebViewClient(final BridgeWebView bridgeWebView, final Context context) {
        super(bridgeWebView);
        this.context = context;
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        super.onPageFinished(webView, s);
        this.stopMyDialog();
    }
    
    public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
        super.onPageStarted(webView, s, bitmap);
        this.startMyDialog();
    }
    
    public void startMyDialog() {
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog((BaseActivity)this.context);
        }
        this.loadingDialog.setTextMessage("\u7f51\u9875\u52a0\u8f7d\u4e2d");
        this.loadingDialog.setCancelable(false);
        this.loadingDialog.setOnTouchOutside(false);
        if (!this.loadingDialog.isShowing()) {
            this.loadingDialog.show();
        }
    }
    
    public void stopMyDialog() {
        final LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog != null) {
            if (loadingDialog.isShowing()) {
                this.loadingDialog.dismiss();
            }
            this.loadingDialog = null;
        }
    }
}
