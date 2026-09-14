package com.tencent.open.c;

import java.lang.reflect.Method;
import android.webkit.WebSettings;
import com.tencent.open.log.SLog;
import android.os.Build$VERSION;
import android.content.Context;
import android.webkit.WebView;

public class b extends WebView
{
    public b(final Context context) {
        super(context);
        this.a();
    }
    
    protected void a() {
        if (Build$VERSION.SDK_INT >= 11) {
            this.removeJavascriptInterface("searchBoxJavaBridge_");
            this.removeJavascriptInterface("accessibility");
            this.removeJavascriptInterface("accessibilityTraversal");
            SLog.i("openSDK_LOG.OpenWebView", "removeJSInterface");
        }
    }
    
    public void destroy() {
        try {
            this.getSettings().setBuiltInZoomControls(true);
            this.getSettings().setDisplayZoomControls(false);
            this.setVisibility(8);
            SLog.i("openSDK_LOG.OpenWebView", "-->OpenWebView.destroy setBuiltInZoomControls");
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.OpenWebView", "-->OpenWebView.destroy setBuiltInZoomControls", (Throwable)ex);
        }
        super.destroy();
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final WebSettings settings = this.getSettings();
        if (settings == null) {
            return;
        }
        settings.setSavePassword(false);
        final Class<? extends WebSettings> class1 = settings.getClass();
        try {
            final Method method = class1.getMethod("removeJavascriptInterface", String.class);
            if (method != null) {
                method.invoke((Object)this, new Object[] { "searchBoxJavaBridge_" });
                method.invoke((Object)this, new Object[] { "accessibility" });
                method.invoke((Object)this, new Object[] { "accessibilityTraversal" });
                SLog.i("openSDK_LOG.OpenWebView", "remove js interface");
            }
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("remove js interface.e:");
            sb.append(ex.toString());
            SLog.e("openSDK_LOG.OpenWebView", sb.toString());
        }
    }
}
