package com.alipay.sdk.m.e;

import java.util.Collection;
import java.util.Set;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import org.json.alipay.a;
import java.lang.reflect.Type;

public final class k implements i
{
    public final Object a(final Object o, final Type type) {
        if (!o.getClass().equals(a.class)) {
            return null;
        }
        final a a = (a)o;
        final HashSet set = new HashSet();
        final boolean b = type instanceof ParameterizedType;
        int i = 0;
        Object o2;
        if (b) {
            o2 = ((ParameterizedType)type).getActualTypeArguments()[0];
        }
        else {
            o2 = Object.class;
        }
        while (i < a.a()) {
            ((Collection)set).add(e.a(a.a(i), (Type)o2));
            ++i;
        }
        return set;
    }
    
    public final boolean a(final Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }
}
