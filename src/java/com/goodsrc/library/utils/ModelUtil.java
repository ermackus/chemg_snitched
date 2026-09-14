package com.goodsrc.library.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import org.xutils.http.RequestParams;

public class ModelUtil
{
    public static RequestParams RequestParamsReflect(final Object o, final RequestParams requestParams) {
        if (o == null) {
            return requestParams;
        }
        final Field[] declaredFields = o.getClass().getDeclaredFields();
        int i = 0;
        try {
            while (i < declaredFields.length) {
                final String name = declaredFields[i].getName();
                if (declaredFields[i].getGenericType().toString().equals((Object)"class java.lang.String")) {
                    final String upperCase = name.substring(0, 1).toUpperCase(Locale.ENGLISH);
                    final StringBuilder sb = new StringBuilder();
                    sb.append(upperCase);
                    sb.append(name.substring(1));
                    final String string = sb.toString();
                    final Class<?> class1 = o.getClass();
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("get");
                    sb2.append(string);
                    final String s = (String)class1.getMethod(sb2.toString(), (Class<?>[])new Class[0]).invoke(o, new Object[0]);
                    if (s != null) {
                        requestParams.addBodyParameter(string, s);
                    }
                }
                ++i;
            }
            return requestParams;
        }
        catch (final InvocationTargetException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (final IllegalArgumentException ex2) {
            ex2.printStackTrace();
            return null;
        }
        catch (final IllegalAccessException ex3) {
            ex3.printStackTrace();
            return null;
        }
        catch (final NoSuchMethodException ex4) {
            ex4.printStackTrace();
            return null;
        }
    }
}
