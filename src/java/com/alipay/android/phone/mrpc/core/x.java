package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public final class x
{
    public g a;
    public z b;
    
    public x(final g a) {
        this.a = a;
        this.b = new z(this);
    }
    
    public final g a() {
        return this.a;
    }
    
    public final <T> T a(final Class<T> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, (InvocationHandler)new y(this.a, clazz, this.b));
    }
}
