package com.tencent.open;

import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.os.Build$VERSION;
import com.tencent.open.log.SLog;
import android.content.Context;
import android.webkit.WebChromeClient;
import android.app.Dialog;

public abstract class b extends Dialog
{
    protected a a;
    protected final WebChromeClient b;
    
    public b(final Context context, final int n) {
        super(context, n);
        this.b = new WebChromeClient() {
            final b a;
            
            public void onConsoleMessage(final String s, final int n, final String s2) {
                final StringBuilder sb = new StringBuilder();
                sb.append("WebChromeClient onConsoleMessage");
                sb.append(s);
                sb.append(" -- From 222 line ");
                sb.append(n);
                sb.append(" of ");
                sb.append(s2);
                SLog.i("openSDK_LOG.JsDialog", sb.toString());
                if (Build$VERSION.SDK_INT == 7) {
                    this.a.a(s);
                }
            }
            
            public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
                if (consoleMessage == null) {
                    return false;
                }
                final StringBuilder sb = new StringBuilder();
                sb.append("WebChromeClient onConsoleMessage");
                sb.append(consoleMessage.message());
                sb.append(" -- From  111 line ");
                sb.append(consoleMessage.lineNumber());
                sb.append(" of ");
                sb.append(consoleMessage.sourceId());
                SLog.i("openSDK_LOG.JsDialog", sb.toString());
                if (Build$VERSION.SDK_INT > 7) {
                    final b a = this.a;
                    String message;
                    if (consoleMessage == null) {
                        message = "";
                    }
                    else {
                        message = consoleMessage.message();
                    }
                    a.a(message);
                }
                return true;
            }
        };
    }
    
    protected abstract void a(final String p0);
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.a = new a();
    }
}
