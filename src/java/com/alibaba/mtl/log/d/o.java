package com.alibaba.mtl.log.d;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public final class o
{
    public static Object a(final Class clazz, final String name) {
        try {
            final Method declaredMethod = clazz.getDeclaredMethod(name, (Class[])new Class[0]);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke((Object)null, new Object[0]);
        }
        catch (final InvocationTargetException ex) {
            ex.printStackTrace();
        }
        catch (final IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        catch (final IllegalArgumentException ex3) {
            ex3.printStackTrace();
        }
        catch (final NoSuchMethodException ex4) {
            ex4.printStackTrace();
        }
        catch (final SecurityException ex5) {
            ex5.printStackTrace();
        }
        return null;
    }
    
    public static Object a(Object invoke, final String name) {
        try {
            final Method declaredMethod = invoke.getClass().getDeclaredMethod(name, (Class<?>[])new Class[0]);
            declaredMethod.setAccessible(true);
            invoke = declaredMethod.invoke(invoke, new Object[0]);
            return invoke;
        }
        catch (final InvocationTargetException ex) {
            ex.printStackTrace();
        }
        catch (final IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        catch (final IllegalArgumentException ex3) {
            ex3.printStackTrace();
        }
        catch (final NoSuchMethodException ex4) {
            ex4.printStackTrace();
        }
        catch (final SecurityException ex5) {
            ex5.printStackTrace();
        }
        return null;
    }
    
    public static Object a(Object invoke, final String name, final Object[] array, final Class... parameterTypes) {
        try {
            final Method declaredMethod = invoke.getClass().getDeclaredMethod(name, (Class<?>[])parameterTypes);
            declaredMethod.setAccessible(true);
            invoke = declaredMethod.invoke(invoke, array);
            return invoke;
        }
        catch (final InvocationTargetException ex) {
            ex.printStackTrace();
        }
        catch (final IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        catch (final IllegalArgumentException ex3) {
            ex3.printStackTrace();
        }
        catch (final NoSuchMethodException ex4) {
            ex4.printStackTrace();
        }
        catch (final SecurityException ex5) {
            ex5.printStackTrace();
        }
        return null;
    }
    
    public static Object a(final String className, final String s) {
        try {
            final Class<?> forName = Class.forName(className);
            if (forName != null) {
                return a(forName, s);
            }
            return null;
        }
        catch (final ClassNotFoundException ex) {
            return null;
        }
    }
}
