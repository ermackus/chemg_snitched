package com.qw.curtain.lib;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.content.Context;

class InnerUtils
{
    static int getScreenHeight(final Context context) {
        final WindowManager windowManager = (WindowManager)context.getSystemService("window");
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
    
    static int getScreenWidth(final Context context) {
        final WindowManager windowManager = (WindowManager)context.getSystemService("window");
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
    
    static int getStatusBarHeight(final Context context) {
        final int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize;
        if (identifier > 0) {
            dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
        }
        else {
            dimensionPixelSize = 0;
        }
        return dimensionPixelSize;
    }
}
