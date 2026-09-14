package com.goodsrc.ui.library.widget.notch;

import android.util.Log;
import java.lang.reflect.Method;

public class SystemProperties
{
    private static final String TAG;
    private static Method getStringProperty;
    private static SystemProperties sSystemProperties;
    
    static {
        TAG = SystemProperties.class.getSimpleName();
    }
    
    private SystemProperties() {
        SystemProperties.getStringProperty = this.getMethod(this.getClass("android.os.SystemProperties"));
    }
    
    private Class getClass(final String className) {
        try {
            return Class.forName(className);
        }
        catch (final ClassNotFoundException ex) {
            Log.e(SystemProperties.TAG, ex.getMessage());
            try {
                return ClassLoader.getSystemClassLoader().loadClass(className);
            }
            catch (final ClassNotFoundException ex2) {
                Log.e(SystemProperties.TAG, ex2.getMessage());
                return null;
            }
        }
    }
    
    public static SystemProperties getInstance() {
        if (SystemProperties.sSystemProperties == null) {
            synchronized (SystemProperties.class) {
                if (SystemProperties.sSystemProperties == null) {
                    SystemProperties.sSystemProperties = new SystemProperties();
                }
            }
        }
        return SystemProperties.sSystemProperties;
    }
    
    private Method getMethod(final Class clazz) {
        Method method = null;
        if (clazz != null) {
            try {
                method = clazz.getMethod("get", String.class);
            }
            catch (final Exception ex) {
                Log.e(SystemProperties.TAG, ex.getMessage());
                method = method;
            }
        }
        return method;
    }
    
    public final String get(String trim) {
        if (trim == null) {
            return "";
        }
        try {
            final Method getStringProperty = SystemProperties.getStringProperty;
            Object invoke = null;
            if (getStringProperty != null) {
                invoke = SystemProperties.getStringProperty.invoke((Object)null, new Object[] { trim });
            }
            trim = (String)invoke;
            if (trim != null) {
                trim = trim.trim();
                return trim;
            }
            return "";
        }
        catch (final Exception ex) {
            return "";
        }
    }
}
