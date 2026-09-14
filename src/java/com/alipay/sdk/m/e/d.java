package com.alipay.sdk.m.e;

import java.lang.reflect.Type;

public final class d implements i, j
{
    public final Object a(final Object o) {
        return ((Enum)o).name();
    }
    
    public final Object a(final Object o, final Type type) {
        return Enum.valueOf((Class)type, o.toString());
    }
    
    public final boolean a(final Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }
}
