package com.alipay.sdk.m.e;

import java.util.List;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public final class a implements i, j
{
    public final Object a(final Object o) {
        final Object[] array = (Object[])o;
        final ArrayList list = new ArrayList();
        for (int length = array.length, i = 0; i < length; ++i) {
            ((List)list).add(f.b(array[i]));
        }
        return list;
    }
    
    public final Object a(final Object o, final Type type) {
        if (!o.getClass().equals(org.json.alipay.a.class)) {
            return null;
        }
        final org.json.alipay.a a = (org.json.alipay.a)o;
        if (!(type instanceof GenericArrayType)) {
            final Class componentType = ((Class)type).getComponentType();
            final int a2 = a.a();
            final Object instance = Array.newInstance((Class)componentType, a2);
            for (int i = 0; i < a2; ++i) {
                Array.set(instance, i, e.a(a.a(i), (Type)componentType));
            }
            return instance;
        }
        throw new IllegalArgumentException("Does not support generic array!");
    }
    
    public final boolean a(final Class<?> clazz) {
        return clazz.isArray();
    }
}
