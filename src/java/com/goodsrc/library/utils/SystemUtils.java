package com.goodsrc.library.utils;

import android.util.DisplayMetrics;
import android.content.Context;

public class SystemUtils
{
    public static int getScreenDefaultHeight(final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int n;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            n = displayMetrics.heightPixels;
        }
        else {
            n = displayMetrics.widthPixels;
        }
        return n;
    }
    
    public static int getScreenDefaultWidth(final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int n;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            n = displayMetrics.widthPixels;
        }
        else {
            n = displayMetrics.heightPixels;
        }
        return n;
    }
    
    public static int getScreenHeight(final Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    
    public static int getScreenOrientation(final Context context) {
        return context.getResources().getConfiguration().orientation;
    }
    
    public static int getScreenWidth(final Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
