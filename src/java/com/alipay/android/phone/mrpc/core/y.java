package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

public final class y implements InvocationHandler
{
    public g a;
    public Class<?> b;
    public z c;
    
    public y(final g a, final Class<?> b, final z c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final Object invoke(final Object o, final Method method, final Object[] array) {
        return this.c.a(method, array);
    }
}
