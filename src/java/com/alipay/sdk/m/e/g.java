package com.alipay.sdk.m.e;

import java.util.Map;
import org.json.alipay.b;
import java.lang.reflect.Type;
import java.lang.reflect.Field;
import java.util.TreeMap;

public final class g implements i, j
{
    public final Object a(final Object o) {
        final TreeMap treeMap = new TreeMap();
        Class<?> clazz = o.getClass();
        while (true) {
            final Field[] declaredFields = clazz.getDeclaredFields();
            if (clazz.equals(Object.class)) {
                break;
            }
            if (declaredFields != null && declaredFields.length > 0) {
                for (final Field field : declaredFields) {
                    Object b = null;
                    Label_0137: {
                        if (field != null) {
                            if (o != null) {
                                if (!"this$0".equals((Object)field.getName())) {
                                    final boolean accessible = field.isAccessible();
                                    field.setAccessible(true);
                                    final Object value = field.get(o);
                                    if (value != null) {
                                        field.setAccessible(accessible);
                                        b = f.b(value);
                                        break Label_0137;
                                    }
                                }
                            }
                        }
                        b = null;
                    }
                    if (b != null) {
                        ((Map)treeMap).put((Object)field.getName(), b);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return treeMap;
    }
    
    public final Object a(final Object o, final Type type) {
        if (!o.getClass().equals(b.class)) {
            return null;
        }
        final b b = (b)o;
        Class superclass = (Class)type;
        final Object instance = superclass.newInstance();
        while (!superclass.equals(Object.class)) {
            final Field[] declaredFields = superclass.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (final Field field : declaredFields) {
                    final String name = field.getName();
                    final Type genericType = field.getGenericType();
                    if (b.b(name)) {
                        field.setAccessible(true);
                        field.set(instance, e.a(b.a(name), genericType));
                    }
                }
            }
            superclass = superclass.getSuperclass();
        }
        return instance;
    }
    
    public final boolean a(final Class<?> clazz) {
        return true;
    }
}
