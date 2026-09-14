package com.luck.picture.lib.utils;

import android.view.View;
import android.provider.Settings$Global;
import com.luck.picture.lib.immersive.RomUtils;
import android.view.ViewGroup;
import android.util.DisplayMetrics;
import android.app.Activity;
import android.graphics.Point;
import android.view.WindowManager;
import android.os.Build$VERSION;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.content.Context;

public class DensityUtil
{
    public static int dip2px(final Context context, final float n) {
        return (int)(n * context.getApplicationContext().getResources().getDisplayMetrics().density + 0.5f);
    }
    
    private static int getInternalDimensionSize(final Context context, final String s) {
        try {
            final int identifier = Resources.getSystem().getIdentifier(s, "dimen", "android");
            if (identifier <= 0) {
                return 0;
            }
            final int dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
            final int dimensionPixelSize2 = Resources.getSystem().getDimensionPixelSize(identifier);
            if (dimensionPixelSize2 >= dimensionPixelSize) {
                return dimensionPixelSize2;
            }
            final float n = dimensionPixelSize * Resources.getSystem().getDisplayMetrics().density / context.getResources().getDisplayMetrics().density;
            float n2;
            if (n >= 0.0f) {
                n2 = n + 0.5f;
            }
            else {
                n2 = n - 0.5f;
            }
            return (int)n2;
        }
        catch (final Resources$NotFoundException ex) {
            return 0;
        }
    }
    
    public static int getNavigationBarHeight(final Context context) {
        final int orientation = context.getResources().getConfiguration().orientation;
        boolean b = true;
        if (orientation != 1) {
            b = false;
        }
        if (isNavBarVisible(context)) {
            String s;
            if (b) {
                s = "navigation_bar_height";
            }
            else {
                s = "navigation_bar_height_landscape";
            }
            return getInternalDimensionSize(context, s);
        }
        return 0;
    }
    
    public static int getNavigationBarWidth(final Context context) {
        if (Build$VERSION.SDK_INT >= 14 && isNavBarVisible(context)) {
            return getInternalDimensionSize(context, "navigation_bar_width");
        }
        return 0;
    }
    
    public static int getRealScreenHeight(final Context context) {
        final WindowManager windowManager = (WindowManager)context.getApplicationContext().getSystemService("window");
        final Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        return point.y;
    }
    
    public static int getRealScreenWidth(final Context context) {
        final WindowManager windowManager = (WindowManager)context.getApplicationContext().getSystemService("window");
        final Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        return point.x;
    }
    
    private static String getResNameById(final Context context, final int n) {
        try {
            return context.getResources().getResourceEntryName(n);
        }
        catch (final Exception ex) {
            return "";
        }
    }
    
    public static int getScreenHeight(final Context context) {
        return getRealScreenHeight(context) - getStatusNavigationBarHeight(context);
    }
    
    private static float getSmallestWidthDp(final Activity activity) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build$VERSION.SDK_INT >= 16) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        return Math.min(displayMetrics.widthPixels / displayMetrics.density, displayMetrics.heightPixels / displayMetrics.density);
    }
    
    public static int getStatusBarHeight() {
        final Resources system = Resources.getSystem();
        return system.getDimensionPixelSize(system.getIdentifier("status_bar_height", "dimen", "android"));
    }
    
    public static int getStatusBarHeight(final Context context) {
        final Resources system = Resources.getSystem();
        final int identifier = system.getIdentifier("status_bar_height", "dimen", "android");
        int n;
        if (identifier > 0) {
            try {
                final int dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
                n = system.getDimensionPixelSize(identifier);
                if (n < dimensionPixelSize) {
                    final float n2 = dimensionPixelSize * system.getDisplayMetrics().density / context.getResources().getDisplayMetrics().density;
                    float n3;
                    if (n2 >= 0.0f) {
                        n3 = n2 + 0.5f;
                    }
                    else {
                        n3 = n2 - 0.5f;
                    }
                    n = (int)n3;
                }
            }
            catch (final Exception ex) {
                n = getStatusBarHeight();
            }
        }
        else {
            n = 0;
        }
        int dip2px = n;
        if (n == 0) {
            dip2px = dip2px(context, 26.0f);
        }
        return dip2px;
    }
    
    private static int getStatusNavigationBarHeight(final Context context) {
        if (isNavBarVisible(context)) {
            return getStatusBarHeight(context) + getNavigationBarHeight(context);
        }
        return getStatusBarHeight(context);
    }
    
    public static boolean isNavBarVisible(Context context) {
        final boolean b = context instanceof Activity;
        final boolean b2 = false;
        final boolean b3 = false;
        if (!b) {
            return false;
        }
        final Activity activity = (Activity)context;
        context = (Context)activity.getWindow().getDecorView();
        while (true) {
            for (int childCount = ((ViewGroup)context).getChildCount(), i = 0; i < childCount; ++i) {
                final View child = ((ViewGroup)context).getChildAt(i);
                final int id = child.getId();
                if (id != -1 && "navigationBarBackground".equals((Object)getResNameById((Context)activity, id)) && child.getVisibility() == 0) {
                    final int n = 1;
                    int n2 = n;
                    if (n != 0) {
                        if (RomUtils.isSamsung() && Build$VERSION.SDK_INT >= 17 && Build$VERSION.SDK_INT < 29) {
                            try {
                                final int int1 = Settings$Global.getInt(activity.getContentResolver(), "navigationbar_hide_bar_enabled");
                                boolean b4 = b3;
                                if (int1 == 0) {
                                    b4 = true;
                                }
                                return b4;
                            }
                            catch (final Exception ex) {}
                        }
                        int n3 = b2 ? 1 : 0;
                        if ((((ViewGroup)context).getSystemUiVisibility() & 0x2) == 0x0) {
                            n3 = 1;
                        }
                        n2 = n3;
                    }
                    return n2 != 0;
                }
            }
            final int n = 0;
            continue;
        }
    }
    
    public static boolean isNavigationAtBottom(final Activity activity) {
        final int orientation = activity.getResources().getConfiguration().orientation;
        boolean b = false;
        final boolean b2 = orientation == 1;
        if (getSmallestWidthDp(activity) >= 600.0f || b2) {
            b = true;
        }
        return b;
    }
}
