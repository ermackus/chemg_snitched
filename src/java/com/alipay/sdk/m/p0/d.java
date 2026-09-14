package com.alipay.sdk.m.p0;

import android.util.Log;
import android.os.Handler;
import android.database.ContentObserver;

public class d extends ContentObserver
{
    public static final String d = "VMS_IDLG_SDK_Observer";
    public String a;
    public int b;
    public c c;
    
    public d(final c c, final int b, final String a) {
        super((Handler)null);
        this.c = c;
        this.b = b;
        this.a = a;
    }
    
    public void onChange(final boolean b) {
        final c c = this.c;
        if (c != null) {
            c.a(this.b, this.a);
        }
        else {
            Log.e("VMS_IDLG_SDK_Observer", "mIdentifierIdClient is null");
        }
    }
}
