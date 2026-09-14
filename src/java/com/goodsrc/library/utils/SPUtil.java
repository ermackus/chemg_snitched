package com.goodsrc.library.utils;

import java.util.Set;
import com.goodsrc.library.core.LibraryApplication;
import android.content.SharedPreferences;

public class SPUtil
{
    public static final String DEFALUT_NAME = "defalut_name";
    
    private SPUtil() {
    }
    
    public static void clearSharePreference(final String s) {
        getSharedPreferences(s).edit().clear().apply();
    }
    
    public static boolean getBoolean(final String s, final String s2, final boolean b) {
        return getSharedPreferences(s).getBoolean(s2, b);
    }
    
    public static boolean getBooleanDefault(final String s, final boolean b) {
        return getSharedPreferences("defalut_name").getBoolean(s, b);
    }
    
    public static int getInt(final String s, final String s2, final int n) {
        return getSharedPreferences(s).getInt(s2, n);
    }
    
    public static int getIntDefault(final String s, final int n) {
        return getSharedPreferences("defalut_name").getInt(s, n);
    }
    
    public static long getLong(final String s, final String s2, final long n) {
        return getSharedPreferences(s).getLong(s2, n);
    }
    
    private static SharedPreferences getSharedPreferences(final String s) {
        return LibraryApplication.getContext().getSharedPreferences(s, 0);
    }
    
    public static String getString(final String s, final String s2, final String s3) {
        return getSharedPreferences(s).getString(s2, s3);
    }
    
    public static String getStringDefault(final String s, final String s2) {
        return getSharedPreferences("defalut_name").getString(s, s2);
    }
    
    public static Set<String> getStringSet(final String s, final String s2, final Set<String> set) {
        return (Set<String>)getSharedPreferences(s).getStringSet(s2, (Set)set);
    }
    
    public static boolean setBoolean(final String s, final String s2, final boolean b) {
        return getSharedPreferences(s).edit().putBoolean(s2, b).commit();
    }
    
    public static boolean setBooleanDefault(final String s, final boolean b) {
        return getSharedPreferences("defalut_name").edit().putBoolean(s, b).commit();
    }
    
    public static boolean setInt(final String s, final String s2, final int n) {
        return getSharedPreferences(s).edit().putInt(s2, n).commit();
    }
    
    public static boolean setIntDefault(final String s, final int n) {
        return getSharedPreferences("defalut_name").edit().putInt(s, n).commit();
    }
    
    public static boolean setLong(final String s, final String s2, final long n) {
        return getSharedPreferences(s).edit().putLong(s2, n).commit();
    }
    
    public static boolean setString(final String s, final String s2, final String s3) {
        return getSharedPreferences(s).edit().putString(s2, s3).commit();
    }
    
    public static boolean setStringDefault(final String s, final String s2) {
        return getSharedPreferences("defalut_name").edit().putString(s, s2).commit();
    }
    
    public static boolean setStringSet(final String s, final String s2, final Set<String> set) {
        return getSharedPreferences(s).edit().putStringSet(s2, (Set)set).commit();
    }
}
