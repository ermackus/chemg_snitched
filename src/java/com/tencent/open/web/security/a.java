package com.tencent.open.web.security;

import android.view.KeyEvent;
import com.tencent.open.log.SLog;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

public class a extends InputConnectionWrapper
{
    public static String a;
    public static boolean b;
    public static boolean c;
    
    public a(final InputConnection inputConnection, final boolean b) {
        super(inputConnection, b);
    }
    
    public boolean commitText(final CharSequence charSequence, final int n) {
        com.tencent.open.web.security.a.c = true;
        com.tencent.open.web.security.a.a = charSequence.toString();
        final StringBuilder sb = new StringBuilder();
        sb.append("-->commitText: ");
        sb.append(charSequence.toString());
        SLog.v("openSDK_LOG.CaptureInputConnection", sb.toString());
        return super.commitText(charSequence, n);
    }
    
    public boolean sendKeyEvent(final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            SLog.i("openSDK_LOG.CaptureInputConnection", "sendKeyEvent");
            com.tencent.open.web.security.a.a = String.valueOf((char)keyEvent.getUnicodeChar());
            com.tencent.open.web.security.a.c = true;
            final StringBuilder sb = new StringBuilder();
            sb.append("s: ");
            sb.append(com.tencent.open.web.security.a.a);
            SLog.d("openSDK_LOG.CaptureInputConnection", sb.toString());
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("-->sendKeyEvent: ");
        sb2.append(com.tencent.open.web.security.a.a);
        SLog.d("openSDK_LOG.CaptureInputConnection", sb2.toString());
        return super.sendKeyEvent(keyEvent);
    }
    
    public boolean setComposingText(final CharSequence charSequence, final int n) {
        com.tencent.open.web.security.a.c = true;
        com.tencent.open.web.security.a.a = charSequence.toString();
        final StringBuilder sb = new StringBuilder();
        sb.append("-->setComposingText: ");
        sb.append(charSequence.toString());
        SLog.v("openSDK_LOG.CaptureInputConnection", sb.toString());
        return super.setComposingText(charSequence, n);
    }
}
