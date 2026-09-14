package com.alipay.sdk.m.f;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class a
{
    public static Class<?> a(Type rawType) {
        while (!(rawType instanceof Class)) {
            if (!(rawType instanceof ParameterizedType)) {
                throw new IllegalArgumentException("TODO");
            }
            rawType = ((ParameterizedType)rawType).getRawType();
        }
        return (Class)rawType;
    }
    
    public static boolean a(final Class<?> clazz) {
        return clazz.isPrimitive() || clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Double.class) || clazz.equals(Float.class) || clazz.equals(Boolean.class) || clazz.equals(Short.class) || clazz.equals(Character.class) || clazz.equals(Byte.class) || clazz.equals(Void.class);
    }
}
