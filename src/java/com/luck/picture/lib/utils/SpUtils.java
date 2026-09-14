package com.luck.picture.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils
{
    private static SharedPreferences pictureSpUtils;
    
    public static boolean getBoolean(final Context context, final String s, final boolean b) {
        return getSp(context).getBoolean(s, b);
    }
    
    private static SharedPreferences getSp(final Context context) {
        if (SpUtils.pictureSpUtils == null) {
            SpUtils.pictureSpUtils = context.getSharedPreferences("PictureSpUtils", 0);
        }
        return SpUtils.pictureSpUtils;
    }
    
    public static void putBoolean(final Context context, final String s, final boolean b) {
        getSp(context).edit().putBoolean(s, b).apply();
    }
    
    public static void putString(final Context context, final String s, final String s2) {
        getSp(context).edit().putString(s, s2).apply();
    }
}
