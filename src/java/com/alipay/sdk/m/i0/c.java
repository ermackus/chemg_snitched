package com.alipay.sdk.m.i0;

import android.text.TextUtils;

public class c
{
    public String a;
    public Boolean b;
    
    public void a(final boolean b) {
        this.b = b;
    }
    
    public boolean a() {
        return this.b != null;
    }
    
    public boolean a(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && TextUtils.equals((CharSequence)this.a, (CharSequence)s);
    }
    
    public void b(final String a) {
        this.a = a;
    }
    
    public boolean b() {
        final Boolean b = this.b;
        return b != null && b;
    }
}
