package com.kingagroot.kingdraw.utils;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public class PreferencesUtils
{
    public static Object getData(final SharedPreferences sharedPreferences, final String s, final Object o) {
        return getData(sharedPreferences, s, o, o.getClass());
    }
    
    public static Object getData(final SharedPreferences sharedPreferences, final String s, final Object o, final Class<?> clazz) {
        if (Integer.class == clazz || Integer.TYPE == clazz) {
            return sharedPreferences.getInt(s, (int)o);
        }
        if (Boolean.class == clazz || Boolean.TYPE == clazz) {
            return sharedPreferences.getBoolean(s, (boolean)o);
        }
        if (String.class == clazz) {
            return sharedPreferences.getString(s, (String)o);
        }
        if (Float.class == clazz || Float.TYPE == clazz) {
            return sharedPreferences.getFloat(s, (float)o);
        }
        if (Long.class != clazz && Long.TYPE != clazz) {
            return null;
        }
        return sharedPreferences.getLong(s, (long)o);
    }
    
    public static void saveData(final SharedPreferences sharedPreferences, final String s, final Object o) {
        final Class<?> class1 = o.getClass();
        final SharedPreferences$Editor edit = sharedPreferences.edit();
        if (Integer.class != class1 && Integer.TYPE != class1) {
            if (Boolean.class != class1 && Boolean.TYPE != class1) {
                if (String.class == class1) {
                    edit.putString(s, (String)o);
                }
                else if (Float.class != class1 && Float.TYPE != class1) {
                    if (Long.class == class1 || Long.TYPE == class1) {
                        edit.putLong(s, (long)o);
                    }
                }
                else {
                    edit.putFloat(s, (float)o);
                }
            }
            else {
                edit.putBoolean(s, (boolean)o);
            }
        }
        else {
            edit.putInt(s, (int)o);
        }
        edit.commit();
    }
}
