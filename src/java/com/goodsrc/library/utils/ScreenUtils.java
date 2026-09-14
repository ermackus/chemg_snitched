package com.goodsrc.library.utils;

import android.content.res.Resources$Theme;
import android.util.TypedValue;
import android.os.Build;
import android.app.Activity;
import android.view.ViewConfiguration;
import android.content.res.Resources;
import android.content.Context;
import java.lang.reflect.Method;
import android.os.Build$VERSION;

public class ScreenUtils
{
    private static String getNavBarOverride() {
        final int sdk_INT = Build$VERSION.SDK_INT;
        String s = null;
        if (sdk_INT < 19) {
            return s;
        }
        try {
            final Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
            declaredMethod.setAccessible(true);
            s = (String)declaredMethod.invoke((Object)null, new Object[] { "qemu.hw.mainkeys" });
            return s;
        }
        finally {
            s = s;
            return s;
        }
    }
    
    public static int getNavigationBarHeight(final Context context) {
        final Resources resources = context.getResources();
        final int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int dimensionPixelSize;
        if (identifier > 0) {
            dimensionPixelSize = resources.getDimensionPixelSize(identifier);
        }
        else {
            dimensionPixelSize = 0;
        }
        return dimensionPixelSize;
    }
    
    public static int getScreenHeight(final Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    
    public static int getScreenWidth(final Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    
    public static int getStatusBarHeight(final Context context) {
        final Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }
    
    public static boolean hasNavBar(final Context context) {
        final Resources resources = context.getResources();
        final int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        final boolean b = true;
        if (identifier != 0) {
            boolean boolean1 = resources.getBoolean(identifier);
            final String navBarOverride = getNavBarOverride();
            if ("1".equals((Object)navBarOverride)) {
                boolean1 = false;
            }
            else if ("0".equals((Object)navBarOverride)) {
                boolean1 = b;
            }
            return boolean1;
        }
        return ViewConfiguration.get(context).hasPermanentMenuKey() ^ true;
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
    
    public static boolean hasNotchInScreen(final Context context) {
        final Activity activity = (Activity)context;
        final int sdk_INT = Build$VERSION.SDK_INT;
        boolean b = false;
        if (sdk_INT >= 28) {
            if (activity.getWindow().getDecorView().getRootWindowInsets().getDisplayCutout() != null) {
                b = true;
            }
            return b;
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
    
    public static boolean isFullScreen(final Context context) {
        final Activity activity = (Activity)context;
        final TypedValue typedValue = new TypedValue();
        final Resources$Theme theme = activity.getTheme();
        boolean b = true;
        theme.obtainStyledAttributes(new int[] { 16843277 }).getValue(0, typedValue);
        if (typedValue.type == 18) {
            if (typedValue.data == 0) {
                b = false;
            }
            return b;
        }
        return false;
    }
    
    public static boolean isImmerse(final Context context) {
        final Activity activity = (Activity)context;
        final int sdk_INT = Build$VERSION.SDK_INT;
        boolean b = false;
        if (sdk_INT >= 19) {
            b = b;
            if ((activity.getWindow().getAttributes().flags & 0x4000000) == 0x4000000) {
                b = true;
            }
        }
        return b;
    }
    
    public static boolean isNavigationBarShow(final Context context) {
        final boolean hasNavBar = hasNavBar(context);
        boolean b2;
        final boolean b = b2 = false;
        if (hasNavBar) {
            final int systemUiVisibility = ((Activity)context).getWindow().getDecorView().getSystemUiVisibility();
            if (Build$VERSION.SDK_INT < 19) {
                b2 = b;
                if (systemUiVisibility != 0) {
                    return b2;
                }
            }
            else {
                b2 = b;
                if (systemUiVisibility != 0) {
                    return b2;
                }
            }
            b2 = true;
        }
        return b2;
    }
}
