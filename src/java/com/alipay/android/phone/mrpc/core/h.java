package com.alipay.android.phone.mrpc.core;

import android.content.Context;

public final class h extends w
{
    public Context a;
    
    public h(final Context a) {
        this.a = a;
    }
    
    public final <T> T a(final Class<T> clazz, final aa aa) {
        return (T)new x((g)new i(this, aa)).a((Class)clazz);
    }
}
