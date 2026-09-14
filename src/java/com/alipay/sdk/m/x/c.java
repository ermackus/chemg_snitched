package com.alipay.sdk.m.x;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.text.TextUtils;
import android.webkit.WebView;
import android.content.Context;
import android.app.Activity;
import android.widget.FrameLayout;

public abstract class c extends FrameLayout
{
    public static final String c = "v1";
    public static final String d = "v2";
    public Activity a;
    public final String b;
    
    public c(final Activity a, final String b) {
        super((Context)a);
        this.a = a;
        this.b = b;
    }
    
    public static void a(final WebView webView) {
        if (webView == null) {
            return;
        }
        try {
            webView.resumeTimers();
        }
        finally {}
    }
    
    public abstract void a(final String p0);
    
    public void a(final String s, final String s2) {
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            CookieSyncManager.createInstance(this.a.getApplicationContext()).sync();
            CookieManager.getInstance().setCookie(s, s2);
            CookieSyncManager.getInstance().sync();
        }
    }
    
    public boolean a() {
        return "v1".equals((Object)this.b);
    }
    
    public abstract boolean b();
    
    public abstract void c();
}
