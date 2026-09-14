package com.tencent.open.web.security;

import org.json.JSONException;
import org.json.JSONObject;
import com.tencent.open.log.SLog;
import android.webkit.WebView;
import com.tencent.open.a$a;

public class c extends a$a
{
    private String d;
    
    public c(final WebView webView, final long n, final String s, final String d) {
        super(webView, n, s);
        this.d = d;
    }
    
    private void b(final String s) {
        final WebView webView = (WebView)this.a.get();
        if (webView != null) {
            final StringBuffer sb = new StringBuffer("javascript:");
            sb.append("if(!!");
            sb.append(this.d);
            sb.append("){");
            sb.append(this.d);
            sb.append("(");
            sb.append(s);
            sb.append(")}");
            final String string = sb.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("-->callback, callback: ");
            sb2.append(string);
            SLog.v("openSDK_LOG.SecureJsListener", sb2.toString());
            webView.loadUrl(string);
        }
    }
    
    public void a() {
        SLog.d("openSDK_LOG.SecureJsListener", "-->onNoMatchMethod...");
    }
    
    public void a(final Object o) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->onComplete, result: ");
        sb.append(o);
        SLog.v("openSDK_LOG.SecureJsListener", sb.toString());
    }
    
    public void a(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->onCustomCallback, js: ");
        sb.append(s);
        SLog.v("openSDK_LOG.SecureJsListener", sb.toString());
        final JSONObject jsonObject = new JSONObject();
        int n;
        if (!com.tencent.open.c.c.a) {
            n = -4;
        }
        else {
            n = 0;
        }
        try {
            jsonObject.put("result", n);
            jsonObject.put("sn", this.b);
            jsonObject.put("data", (Object)s);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        this.b(jsonObject.toString());
    }
}
