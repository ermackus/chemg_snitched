package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Method;

public abstract class a implements v
{
    public Method a;
    public byte[] b;
    public String c;
    public int d;
    public String e;
    public boolean f;
    
    public a(final Method a, final int d, final String c, final byte[] b, final String e, final boolean f) {
        this.a = a;
        this.d = d;
        this.c = c;
        this.b = b;
        this.e = e;
        this.f = f;
    }
}
