package com.alipay.sdk.m.e;

import com.alipay.sdk.m.f.a;
import org.json.alipay.b;
import java.util.Iterator;
import java.util.Map$Entry;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.IdentityHashMap;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Map;
import java.lang.reflect.Type;

public final class h implements i, j
{
    public static Map<Object, Object> a(Type rawType) {
        while (rawType != Properties.class) {
            if (rawType == Hashtable.class) {
                return (Map<Object, Object>)new Hashtable();
            }
            if (rawType == IdentityHashMap.class) {
                return (Map<Object, Object>)new IdentityHashMap();
            }
            if (rawType == SortedMap.class || rawType == TreeMap.class) {
                return (Map<Object, Object>)new TreeMap();
            }
            if (rawType == ConcurrentMap.class || rawType == ConcurrentHashMap.class) {
                return (Map<Object, Object>)new ConcurrentHashMap();
            }
            if (rawType == Map.class || rawType == HashMap.class) {
                return (Map<Object, Object>)new HashMap();
            }
            if (rawType == LinkedHashMap.class) {
                return (Map<Object, Object>)new LinkedHashMap();
            }
            if (!(rawType instanceof ParameterizedType)) {
                final Class clazz = (Class)rawType;
                if (!clazz.isInterface()) {
                    try {
                        return (Map<Object, Object>)clazz.newInstance();
                    }
                    catch (final Exception ex) {
                        final StringBuilder sb = new StringBuilder("unsupport type ");
                        sb.append((Object)rawType);
                        throw new IllegalArgumentException(sb.toString(), (Throwable)ex);
                    }
                }
                final StringBuilder sb2 = new StringBuilder("unsupport type ");
                sb2.append((Object)rawType);
                throw new IllegalArgumentException(sb2.toString());
            }
            rawType = ((ParameterizedType)rawType).getRawType();
        }
        return (Map<Object, Object>)new Properties();
    }
    
    public final Object a(final Object o) {
        final TreeMap treeMap = new TreeMap();
        for (final Map$Entry map$Entry : ((Map)o).entrySet()) {
            if (!(map$Entry.getKey() instanceof String)) {
                throw new IllegalArgumentException("Map key must be String!");
            }
            ((Map)treeMap).put((Object)map$Entry.getKey(), f.b(map$Entry.getValue()));
        }
        return treeMap;
    }
    
    public final Object a(Object o, Type type) {
        if (!o.getClass().equals(b.class)) {
            return null;
        }
        final b b = (b)o;
        final Map<Object, Object> a = a(type);
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Deserialize Map must be Generics!");
        }
        final ParameterizedType parameterizedType = (ParameterizedType)type;
        final Type type2 = parameterizedType.getActualTypeArguments()[0];
        type = parameterizedType.getActualTypeArguments()[1];
        if (String.class == type2) {
            final Iterator a2 = b.a();
            while (a2.hasNext()) {
                final String s = (String)a2.next();
                if (com.alipay.sdk.m.f.a.a((Class)type)) {
                    o = b.a(s);
                }
                else {
                    o = e.a(b.a(s), type);
                }
                a.put((Object)s, o);
            }
            return a;
        }
        throw new IllegalArgumentException("Deserialize Map Key must be String.class");
    }
    
    public final boolean a(final Class<?> cls) {
        return Map.class.isAssignableFrom(cls);
    }
}
