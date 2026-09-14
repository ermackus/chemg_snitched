package org.xutils.db.table;

import java.lang.reflect.Field;
import org.xutils.common.util.LogUtil;
import java.lang.reflect.Method;
import org.xutils.db.converter.ColumnConverterFactory;
import java.util.Collection;
import java.util.HashSet;

public final class ColumnUtils
{
    private static final HashSet<Class<?>> AUTO_INCREMENT_TYPES;
    private static final HashSet<Class<?>> BOOLEAN_TYPES;
    private static final HashSet<Class<?>> INTEGER_TYPES;
    
    static {
        BOOLEAN_TYPES = new HashSet(2);
        INTEGER_TYPES = new HashSet(2);
        AUTO_INCREMENT_TYPES = new HashSet(4);
        ColumnUtils.BOOLEAN_TYPES.add((Object)Boolean.TYPE);
        ColumnUtils.BOOLEAN_TYPES.add((Object)Boolean.class);
        ColumnUtils.INTEGER_TYPES.add((Object)Integer.TYPE);
        ColumnUtils.INTEGER_TYPES.add((Object)Integer.class);
        ColumnUtils.AUTO_INCREMENT_TYPES.addAll((Collection)ColumnUtils.INTEGER_TYPES);
        ColumnUtils.AUTO_INCREMENT_TYPES.add((Object)Long.TYPE);
        ColumnUtils.AUTO_INCREMENT_TYPES.add((Object)Long.class);
    }
    
    private ColumnUtils() {
    }
    
    public static Object convert2DbValueIfNeeded(final Object o) {
        Object fieldValue2DbValue = o;
        if (o != null) {
            fieldValue2DbValue = ColumnConverterFactory.getColumnConverter(o.getClass()).fieldValue2DbValue(o);
        }
        return fieldValue2DbValue;
    }
    
    private static Method findBooleanGetMethod(final Class<?> clazz, String string) {
        if (!string.startsWith("is")) {
            final StringBuilder sb = new StringBuilder();
            sb.append("is");
            sb.append(string.substring(0, 1).toUpperCase());
            sb.append(string.substring(1));
            string = sb.toString();
        }
        try {
            return clazz.getDeclaredMethod(string, (Class[])new Class[0]);
        }
        catch (final NoSuchMethodException ex) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(clazz.getName());
            sb2.append("#");
            sb2.append(string);
            sb2.append(" not exist");
            LogUtil.d(sb2.toString());
            return null;
        }
    }
    
    private static Method findBooleanSetMethod(final Class<?> clazz, String name, final Class<?> clazz2) {
        if (name.startsWith("is")) {
            final StringBuilder sb = new StringBuilder();
            sb.append("set");
            sb.append(name.substring(2, 3).toUpperCase());
            sb.append(name.substring(3));
            name = sb.toString();
        }
        else {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("set");
            sb2.append(name.substring(0, 1).toUpperCase());
            sb2.append(name.substring(1));
            name = sb2.toString();
        }
        try {
            return clazz.getDeclaredMethod(name, clazz2);
        }
        catch (final NoSuchMethodException ex) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(clazz.getName());
            sb3.append("#");
            sb3.append(name);
            sb3.append(" not exist");
            LogUtil.d(sb3.toString());
            return null;
        }
    }
    
    static Method findGetMethod(final Class<?> obj, final Field field) {
        final boolean equals = Object.class.equals(obj);
        Method booleanGetMethod = null;
        if (equals) {
            return null;
        }
        final String name = field.getName();
        if (isBoolean(field.getType())) {
            booleanGetMethod = findBooleanGetMethod(obj, name);
        }
        Method declaredMethod;
        if ((declaredMethod = booleanGetMethod) == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("get");
            sb.append(name.substring(0, 1).toUpperCase());
            sb.append(name.substring(1));
            final String string = sb.toString();
            try {
                declaredMethod = obj.getDeclaredMethod(string, (Class[])new Class[0]);
            }
            catch (final NoSuchMethodException ex) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(obj.getName());
                sb2.append("#");
                sb2.append(string);
                sb2.append(" not exist");
                LogUtil.d(sb2.toString());
                declaredMethod = booleanGetMethod;
            }
        }
        if (declaredMethod == null) {
            return findGetMethod(obj.getSuperclass(), field);
        }
        return declaredMethod;
    }
    
    static Method findSetMethod(final Class<?> obj, final Field field) {
        final boolean equals = Object.class.equals(obj);
        Method booleanSetMethod = null;
        if (equals) {
            return null;
        }
        final String name = field.getName();
        final Class type = field.getType();
        if (isBoolean(type)) {
            booleanSetMethod = findBooleanSetMethod(obj, name, type);
        }
        Method declaredMethod;
        if ((declaredMethod = booleanSetMethod) == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("set");
            sb.append(name.substring(0, 1).toUpperCase());
            sb.append(name.substring(1));
            final String string = sb.toString();
            try {
                declaredMethod = obj.getDeclaredMethod(string, type);
            }
            catch (final NoSuchMethodException ex) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(obj.getName());
                sb2.append("#");
                sb2.append(string);
                sb2.append(" not exist");
                LogUtil.d(sb2.toString());
                declaredMethod = booleanSetMethod;
            }
        }
        if (declaredMethod == null) {
            return findSetMethod(obj.getSuperclass(), field);
        }
        return declaredMethod;
    }
    
    public static boolean isAutoIdType(final Class<?> clazz) {
        return ColumnUtils.AUTO_INCREMENT_TYPES.contains((Object)clazz);
    }
    
    public static boolean isBoolean(final Class<?> clazz) {
        return ColumnUtils.BOOLEAN_TYPES.contains((Object)clazz);
    }
    
    public static boolean isInteger(final Class<?> clazz) {
        return ColumnUtils.INTEGER_TYPES.contains((Object)clazz);
    }
}
