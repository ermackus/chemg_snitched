package com.kingagroot.kingdraw.utils;

import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.view.View;
import android.os.Build;
import android.content.ContentResolver;
import android.provider.Settings$Global;
import android.view.KeyCharacterMap;
import android.view.ViewConfiguration;
import android.graphics.Point;
import android.app.Activity;
import android.os.Build$VERSION;
import android.view.Display;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.content.Context;

public class VirtualUtils
{
    public static int getVirtualBarHeight(final Context context) {
        final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        final boolean b = false;
        int n;
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke((Object)defaultDisplay, new Object[] { displayMetrics });
            n = displayMetrics.heightPixels - defaultDisplay.getHeight();
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            n = 0;
        }
        if (isMIUI()) {
            if (isFullScreen(context)) {
                n = (b ? 1 : 0);
            }
        }
        else if (!hasDeviceNavigationBar(context)) {
            n = (b ? 1 : 0);
        }
        return n;
    }
    
    public static boolean hasDeviceNavigationBar(final Context context) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        boolean b = true;
        final boolean b2 = true;
        if (sdk_INT >= 17) {
            final Display defaultDisplay = ((Activity)context).getWindowManager().getDefaultDisplay();
            final Point point = new Point();
            final Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            return point2.y != point.y && b2;
        }
        final boolean hasPermanentMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        final boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            b = false;
        }
        return b;
    }
    
    public static boolean isFullScreen(final Context context) {
        final ContentResolver contentResolver = context.getContentResolver();
        boolean b = false;
        if (Settings$Global.getInt(contentResolver, "force_fsg_nav_bar", 0) != 0) {
            b = true;
        }
        return b;
    }
    
    public static boolean isMIUI() {
        return "xiaomi".equalsIgnoreCase(Build.MANUFACTURER);
    }
    
    public static void setLayoutHeight(final View view, final int height) {
        if (view.getParent() instanceof FrameLayout) {
            final FrameLayout$LayoutParams layoutParams = (FrameLayout$LayoutParams)view.getLayoutParams();
            layoutParams.height = height;
            view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            view.requestLayout();
        }
        else if (view.getParent() instanceof RelativeLayout) {
            final RelativeLayout$LayoutParams layoutParams2 = (RelativeLayout$LayoutParams)view.getLayoutParams();
            layoutParams2.height = height;
            view.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            view.requestLayout();
        }
        else if (view.getParent() instanceof LinearLayout) {
            final LinearLayout$LayoutParams layoutParams3 = (LinearLayout$LayoutParams)view.getLayoutParams();
            layoutParams3.height = height;
            view.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
            view.requestLayout();
        }
    }
}
