package com.kingagroot.kingdraw.widget.jsweb.KDNativeHander;

import android.webkit.ValueCallback;
import android.util.Log;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;

public abstract class KDNativeBaseHander
{
    protected JsWebView webView;
    
    public abstract void action(final String p0);
    
    protected void complete(final String s, final String s2) {
        if (this.webView == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("kd");
        sb.append(".");
        sb.append("callbackStatusData");
        sb.append("(");
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("'");
        sb2.append(s);
        sb2.append("'");
        sb.append(sb2.toString());
        sb.append(",");
        sb.append("'complete'");
        sb.append(",");
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("'");
        sb3.append(s2);
        sb3.append("'");
        sb.append(sb3.toString());
        sb.append(")");
        this.webView.post((Runnable)new Runnable(this, sb) {
            final KDNativeBaseHander this$0;
            final StringBuilder val$jsCallBackData;
            
            public void run() {
                this.this$0.webView.evaluateJavascript(this.val$jsCallBackData.toString(), (ValueCallback)_$$Lambda$KDNativeBaseHander$3$ur0pqZUFFeM3tOa_jJp5yQrjKWc.INSTANCE);
            }
        });
    }
    
    public abstract void destroy();
    
    public void dismissLoading() {
        this.webView.endLoading();
    }
    
    protected void error(final String s, final String s2) {
        if (this.webView == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("kd");
        sb.append(".");
        sb.append("callbackStatusData");
        sb.append("(");
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("'");
        sb2.append(s);
        sb2.append("'");
        sb.append(sb2.toString());
        sb.append(",");
        sb.append("'error'");
        sb.append(",");
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("'");
        sb3.append(s2);
        sb3.append("'");
        sb.append(sb3.toString());
        sb.append(")");
        sb.toString();
        this.webView.post((Runnable)new Runnable(this, sb) {
            final KDNativeBaseHander this$0;
            final StringBuilder val$jsCallBackData;
            
            public void run() {
                this.this$0.webView.evaluateJavascript(this.val$jsCallBackData.toString(), (ValueCallback)_$$Lambda$KDNativeBaseHander$4$9PJNGT00gaFAZW26_etDBLa6A3s.INSTANCE);
            }
        });
    }
    
    public abstract String handerName();
    
    protected void progress(final String s, final long n, final long n2) {
        if (this.webView == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("kd");
        sb.append(".");
        sb.append("callbackStatusData");
        sb.append("(");
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("'");
        sb2.append(s);
        sb2.append("'");
        sb.append(sb2.toString());
        sb.append(",");
        sb.append("'complete'");
        sb.append(",");
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("{");
        sb3.append(n);
        sb3.append(",");
        sb3.append(n2);
        sb3.append("}");
        sb.append(sb3.toString());
        sb.append(")");
        this.webView.post((Runnable)new Runnable(this, sb) {
            final KDNativeBaseHander this$0;
            final StringBuilder val$jsCallBackData;
            
            public void run() {
                this.this$0.webView.evaluateJavascript(this.val$jsCallBackData.toString(), (ValueCallback)_$$Lambda$KDNativeBaseHander$2$IXOg1SivB8X8pmQlJh_loKe2bf8.INSTANCE);
            }
        });
    }
    
    public void showLoading() {
        this.webView.startLoading();
    }
    
    protected void start(final String s, final String s2) {
        if (this.webView == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("kd");
        sb.append(".");
        sb.append("callbackStatusData");
        sb.append("(");
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("'");
        sb2.append(s);
        sb2.append("'");
        sb.append(sb2.toString());
        sb.append(",");
        sb.append("'start'");
        sb.append(",");
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("'");
        sb3.append(s2);
        sb3.append("'");
        sb.append(sb3.toString());
        sb.append(")");
        this.webView.post((Runnable)new Runnable(this, sb) {
            final KDNativeBaseHander this$0;
            final StringBuilder val$jsCallBackData;
            
            public void run() {
                this.this$0.webView.evaluateJavascript(this.val$jsCallBackData.toString(), (ValueCallback)_$$Lambda$KDNativeBaseHander$1$2msT5G9pkRTlgYkE5kaY1ctOzdQ.INSTANCE);
            }
        });
    }
}
