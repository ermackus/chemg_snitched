package com.tencent.open.c;

import android.view.inputmethod.InputConnection;
import android.view.inputmethod.EditorInfo;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.open.log.SLog;
import android.content.Context;
import com.tencent.open.web.security.a;
import android.view.KeyEvent;

public class c extends b
{
    public static boolean a;
    private KeyEvent b;
    private a c;
    
    public c(final Context context) {
        super(context);
    }
    
    public boolean dispatchKeyEvent(KeyEvent b) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->dispatchKeyEvent, is device support: ");
        sb.append(com.tencent.open.c.c.a);
        SLog.d("openSDK_LOG.SecureWebView", sb.toString());
        if (!com.tencent.open.c.c.a) {
            return super.dispatchKeyEvent(b);
        }
        if (b.getAction() != 0) {
            return super.dispatchKeyEvent(b);
        }
        final int keyCode = b.getKeyCode();
        if (keyCode == 4) {
            return super.dispatchKeyEvent(b);
        }
        if (keyCode == 66) {
            return super.dispatchKeyEvent(b);
        }
        if (keyCode == 67) {
            com.tencent.open.web.security.a.b = true;
            return super.dispatchKeyEvent(b);
        }
        if (b.getUnicodeChar() == 0) {
            return super.dispatchKeyEvent(b);
        }
        if (SecureJsInterface.isPWDEdit) {
            final int unicodeChar = b.getUnicodeChar();
            if ((unicodeChar >= 33 && unicodeChar <= 95) || (unicodeChar >= 97 && unicodeChar <= 125)) {
                b = new KeyEvent(0, 17);
                this.b = b;
                return super.dispatchKeyEvent(b);
            }
        }
        return super.dispatchKeyEvent(b);
    }
    
    public InputConnection onCreateInputConnection(final EditorInfo editorInfo) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->create input connection, is edit: ");
        sb.append(SecureJsInterface.isPWDEdit);
        SLog.i("openSDK_LOG.SecureWebView", sb.toString());
        final InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("-->onCreateInputConnection, inputConn is ");
        sb2.append((Object)onCreateInputConnection);
        SLog.v("openSDK_LOG.SecureWebView", sb2.toString());
        if (onCreateInputConnection != null) {
            com.tencent.open.c.c.a = true;
            return (InputConnection)(this.c = new a(super.onCreateInputConnection(editorInfo), false));
        }
        com.tencent.open.c.c.a = false;
        return onCreateInputConnection;
    }
    
    public boolean onKeyDown(final int n, KeyEvent b) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->onKeyDown, is device support: ");
        sb.append(com.tencent.open.c.c.a);
        SLog.d("openSDK_LOG.SecureWebView", sb.toString());
        if (!com.tencent.open.c.c.a) {
            return super.onKeyDown(n, b);
        }
        if (b.getAction() != 0) {
            return super.onKeyDown(n, b);
        }
        final int keyCode = b.getKeyCode();
        if (keyCode == 4) {
            return super.onKeyDown(n, b);
        }
        if (keyCode == 66) {
            return super.onKeyDown(n, b);
        }
        if (keyCode == 67) {
            com.tencent.open.web.security.a.b = true;
            return super.onKeyDown(n, b);
        }
        if (b.getUnicodeChar() == 0) {
            return super.onKeyDown(n, b);
        }
        if (SecureJsInterface.isPWDEdit) {
            final int unicodeChar = b.getUnicodeChar();
            if ((unicodeChar >= 33 && unicodeChar <= 95) || (unicodeChar >= 97 && unicodeChar <= 125)) {
                b = new KeyEvent(0, 17);
                this.b = b;
                return super.onKeyDown(b.getKeyCode(), this.b);
            }
        }
        return super.onKeyDown(n, b);
    }
}
