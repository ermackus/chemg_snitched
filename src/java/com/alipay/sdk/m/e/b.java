package com.alipay.sdk.m.e;

import java.util.List;
import org.json.alipay.a;
import java.util.Iterator;
import java.lang.reflect.ParameterizedType;
import java.util.EnumSet;
import java.util.TreeSet;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.AbstractCollection;
import java.util.Collection;
import java.lang.reflect.Type;

public final class b implements i, j
{
    public static Collection<Object> a(Class<?> none, final Type type) {
        if (none == AbstractCollection.class) {
            none = new ArrayList();
            return (Collection<Object>)none;
        }
        if (((Class)none).isAssignableFrom(HashSet.class)) {
            none = new HashSet();
            return (Collection<Object>)none;
        }
        if (((Class)none).isAssignableFrom(LinkedHashSet.class)) {
            none = new LinkedHashSet();
            return (Collection<Object>)none;
        }
        if (((Class)none).isAssignableFrom(TreeSet.class)) {
            none = new TreeSet();
            return (Collection<Object>)none;
        }
        if (((Class)none).isAssignableFrom(ArrayList.class)) {
            none = new ArrayList();
            return (Collection<Object>)none;
        }
        if (((Class)none).isAssignableFrom(EnumSet.class)) {
            Object o;
            if (type instanceof ParameterizedType) {
                o = ((ParameterizedType)type).getActualTypeArguments()[0];
            }
            else {
                o = Object.class;
            }
            none = EnumSet.noneOf((Class)o);
            return (Collection<Object>)none;
        }
        try {
            none = ((Class<Collection>)none).newInstance();
            return (Collection<Object>)none;
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder("create instane error, class ");
            sb.append(((Class)none).getName());
            throw new IllegalArgumentException(sb.toString());
        }
    }
    
    public final Object a(final Object o) {
        final ArrayList list = new ArrayList();
        final Iterator iterator = ((Iterable)o).iterator();
        while (iterator.hasNext()) {
            ((List)list).add(f.b(iterator.next()));
        }
        return list;
    }
    
    public final Object a(final Object o, Type type) {
        if (!o.getClass().equals(a.class)) {
            return null;
        }
        final Class a = com.alipay.sdk.m.f.a.a(type);
        final a a2 = (a)o;
        final Collection<Object> a3 = a(a, type);
        if (type instanceof ParameterizedType) {
            final Type[] actualTypeArguments = ((ParameterizedType)type).getActualTypeArguments();
            int i = 0;
            type = actualTypeArguments[0];
            while (i < a2.a()) {
                a3.add(e.a(a2.a(i), type));
                ++i;
            }
            return a3;
        }
        throw new IllegalArgumentException("Does not support the implement for generics.");
    }
    
    public final boolean a(final Class<?> cls) {
        return Collection.class.isAssignableFrom(cls);
    }
}
