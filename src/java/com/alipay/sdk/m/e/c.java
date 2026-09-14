package com.alipay.sdk.m.e;

import java.lang.reflect.Type;
import java.util.Date;

public final class c implements i, j
{
    public final Object a(final Object o) {
        return ((Date)o).getTime();
    }
    
    public final Object a(final Object o, final Type type) {
        return new Date((long)o);
    }
    
    public final boolean a(final Class<?> cls) {
        return Date.class.isAssignableFrom(cls);
    }
}
