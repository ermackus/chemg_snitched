package com.goodsrc.library.utils;

import java.util.List;
import android.view.DisplayCutout;
import android.view.WindowInsets;
import android.os.Build;
import android.os.Build$VERSION;
import android.app.Activity;
import android.util.Log;
import android.content.Context;

public class NotchUtil
{
    public static int[] getNotchSizeHw(final Context context) {
        final int[] array2;
        final int[] array = array2 = new int[2];
        array2[1] = (array2[0] = 0);
        int[] array3;
        try {
            final Class loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            array3 = (int[])loadClass.getMethod("getNotchSize", (Class[])new Class[0]).invoke((Object)loadClass, new Object[0]);
        }
        catch (final Exception ex) {
            Log.e("test", "getNotchSize Exception");
            array3 = array;
        }
        return array3;
    }
    
    private static boolean hasNotchHw(final Activity activity) {
        try {
            final Class loadClass = activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return (boolean)loadClass.getMethod("hasNotchInScreen", (Class[])new Class[0]).invoke((Object)loadClass, new Object[0]);
        }
        catch (final Exception ex) {
            return false;
        }
    }
    
    public static boolean hasNotchInScreen(final Activity activity) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        final boolean b = false;
        if (sdk_INT >= 28) {
            final WindowInsets rootWindowInsets = activity.getWindow().getDecorView().getRootWindowInsets();
            boolean b2 = b;
            if (rootWindowInsets != null) {
                final DisplayCutout displayCutout = rootWindowInsets.getDisplayCutout();
                b2 = b;
                if (displayCutout != null) {
                    final List boundingRects = displayCutout.getBoundingRects();
                    b2 = b;
                    if (boundingRects != null) {
                        b2 = b;
                        if (boundingRects.size() > 0) {
                            b2 = true;
                        }
                    }
                }
            }
            return b2;
        }
        final String manufacturer = Build.MANUFACTURER;
        if (manufacturer.isEmpty()) {
            return false;
        }
        if (manufacturer.equalsIgnoreCase("HUAWEI")) {
            return hasNotchHw(activity);
        }
        if (manufacturer.equalsIgnoreCase("xiaomi")) {
            return hasNotchXiaoMi(activity);
        }
        if (manufacturer.equalsIgnoreCase("oppo")) {
            return hasNotchOPPO(activity);
        }
        return manufacturer.equalsIgnoreCase("vivo") && hasNotchVIVO(activity);
    }
    
    private static boolean hasNotchOPPO(final Activity activity) {
        return activity.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }
    
    private static boolean hasNotchVIVO(final Activity activity) {
        try {
            final Class<?> forName = Class.forName("android.util.FtFeature");
            return (boolean)forName.getMethod("isFeatureSupport", Integer.TYPE).invoke((Object)forName, new Object[] { 32 });
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private static boolean hasNotchXiaoMi(final Activity activity) {
        boolean b = false;
        try {
            final Class<?> forName = Class.forName("android.os.SystemProperties");
            if ((int)forName.getMethod("getInt", String.class, Integer.TYPE).invoke((Object)forName, new Object[] { "ro.miui.notch", 0 }) == 1) {
                b = true;
            }
            return b;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
