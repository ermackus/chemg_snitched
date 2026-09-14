package com.github.lzyzsd.jsbridge;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import android.graphics.Bitmap;
import java.util.Iterator;
import java.util.List;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BridgeWebViewClient extends WebViewClient
{
    private BridgeWebView webView;
    
    public BridgeWebViewClient(final BridgeWebView webView) {
        this.webView = webView;
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        super.onPageFinished(webView, s);
        BridgeUtil.webViewLoadLocalJs(webView, "WebViewJavascriptBridge.js");
        if (this.webView.getStartupMessage() != null) {
            final Iterator iterator = this.webView.getStartupMessage().iterator();
            while (iterator.hasNext()) {
                this.webView.dispatchMessage((Message)iterator.next());
            }
            this.webView.setStartupMessage(null);
        }
    }
    
    public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
        super.onPageStarted(webView, s, bitmap);
    }
    
    public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        super.onReceivedError(webView, n, s, s2);
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, String decode) {
        try {
            decode = URLDecoder.decode(decode, "UTF-8");
        }
        catch (final UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        if (decode.startsWith("yy://return/")) {
            this.webView.handlerReturnData(decode);
            return true;
        }
        if (decode.startsWith("yy://")) {
            this.webView.flushMessageQueue();
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, decode);
    }
}
