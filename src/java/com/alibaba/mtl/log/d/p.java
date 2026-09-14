package com.alibaba.mtl.log.d;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.content.Context;

public class p
{
    public static void a(final Context context, final String s, final String s2) {
        if (context == null) {
            return;
        }
        final SharedPreferences sharedPreferences = context.getSharedPreferences("ut_setting", 4);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.putString(s, s2);
                edit.apply();
            }
        }
    }
    
    public static String getString(final Context context, final String s) {
        if (context == null) {
            return null;
        }
        final SharedPreferences sharedPreferences = context.getSharedPreferences("ut_setting", 4);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(s, (String)null);
        }
        return null;
    }
}
