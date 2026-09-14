package com.lzf.easyfloat.utils;

import android.view.View;
import com.lzf.easyfloat.permission.rom.RomUtils;
import android.graphics.Point;
import android.content.res.Resources;
import kotlin.jvm.internal.Intrinsics;
import android.provider.Settings$Secure;
import android.content.ContentResolver;
import android.provider.Settings$Global;
import android.provider.Settings$System;
import android.view.Display;
import android.os.Build$VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.content.Context;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\nJ\u0016\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\nJ\u000e\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\"" }, d2 = { "Lcom/lzf/easyfloat/utils/DisplayUtils;", "", "()V", "TAG", "", "dp2px", "", "context", "Landroid/content/Context;", "dpVal", "", "getNavigationBarCurrentHeight", "getNavigationBarHeight", "getScreenHeight", "getScreenSize", "Landroid/graphics/Point;", "getScreenWidth", "getStatusBarHeight", "hasNavigationBar", "", "isHasNavigationBar", "isHuaWeiHideNav", "isMiuiFullScreen", "isVivoFullScreen", "px2dp", "pxVal", "px2sp", "pxValue", "rejectedNavHeight", "sp2px", "spValue", "statusBarHeight", "view", "Landroid/view/View;", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class DisplayUtils
{
    public static final DisplayUtils INSTANCE;
    private static final String TAG = "DisplayUtils--->";
    
    static {
        INSTANCE = new DisplayUtils();
    }
    
    private DisplayUtils() {
    }
    
    private final boolean isHasNavigationBar(final Context context) {
        final Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        final Display defaultDisplay = ((WindowManager)systemService).getDefaultDisplay();
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build$VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
        }
        final int heightPixels = displayMetrics.heightPixels;
        final int widthPixels = displayMetrics.widthPixels;
        final DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics2);
        final int heightPixels2 = displayMetrics2.heightPixels;
        final int widthPixels2 = displayMetrics2.widthPixels;
        final int navigationBarHeight = this.getNavigationBarHeight(context);
        boolean b = false;
        if (navigationBarHeight + heightPixels2 > heightPixels) {
            return false;
        }
        if (widthPixels - widthPixels2 > 0 || heightPixels - heightPixels2 > 0) {
            b = true;
        }
        return b;
    }
    
    private final boolean isHuaWeiHideNav(final Context context) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        boolean b = false;
        int n;
        if (sdk_INT < 21) {
            n = Settings$System.getInt(context.getContentResolver(), "navigationbar_is_min", 0);
        }
        else {
            n = Settings$Global.getInt(context.getContentResolver(), "navigationbar_is_min", 0);
        }
        if (n != 0) {
            b = true;
        }
        return b;
    }
    
    private final boolean isMiuiFullScreen(final Context context) {
        final ContentResolver contentResolver = context.getContentResolver();
        boolean b = false;
        if (Settings$Global.getInt(contentResolver, "force_fsg_nav_bar", 0) != 0) {
            b = true;
        }
        return b;
    }
    
    private final boolean isVivoFullScreen(final Context context) {
        final ContentResolver contentResolver = context.getContentResolver();
        boolean b = false;
        if (Settings$Secure.getInt(contentResolver, "navigation_gesture_on", 0) != 0) {
            b = true;
        }
        return b;
    }
    
    public final int dp2px(final Context context, final float n) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        final Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue((Object)resources, "context.resources");
        return (int)(n * resources.getDisplayMetrics().density + 0.5f);
    }
    
    public final int getNavigationBarCurrentHeight(final Context context) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        int navigationBarHeight;
        if (this.hasNavigationBar(context)) {
            navigationBarHeight = this.getNavigationBarHeight(context);
        }
        else {
            navigationBarHeight = 0;
        }
        return navigationBarHeight;
    }
    
    public final int getNavigationBarHeight(final Context context) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
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
    
    public final int getScreenHeight(final Context context) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        return this.getScreenSize(context).y;
    }
    
    public final Point getScreenSize(final Context context) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        final Point point = new Point();
        final Object systemService = context.getSystemService("window");
        if (systemService != null) {
            ((WindowManager)systemService).getDefaultDisplay().getRealSize(point);
            return point;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
    }
    
    public final int getScreenWidth(final Context context) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        final Object systemService = context.getSystemService("window");
        if (systemService != null) {
            final WindowManager windowManager = (WindowManager)systemService;
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            final Resources resources = context.getResources();
            Intrinsics.checkNotNullExpressionValue((Object)resources, "context.resources");
            int widthPixels;
            if (resources.getConfiguration().orientation == 1) {
                widthPixels = displayMetrics.widthPixels;
            }
            else {
                widthPixels = displayMetrics.widthPixels - this.getNavigationBarCurrentHeight(context);
            }
            return widthPixels;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
    }
    
    public final int getStatusBarHeight(final Context context) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        final Resources resources = context.getResources();
        final int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize;
        if (identifier > 0) {
            dimensionPixelSize = resources.getDimensionPixelSize(identifier);
        }
        else {
            dimensionPixelSize = 0;
        }
        return dimensionPixelSize;
    }
    
    public final boolean hasNavigationBar(final Context context) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        final int navigationBarHeight = this.getNavigationBarHeight(context);
        boolean hasNavigationBar = false;
        if (navigationBarHeight != 0) {
            if (!RomUtils.INSTANCE.checkIsHuaweiRom() || !this.isHuaWeiHideNav(context)) {
                if (!RomUtils.INSTANCE.checkIsMiuiRom() || !this.isMiuiFullScreen(context)) {
                    if (!RomUtils.INSTANCE.checkIsVivoRom() || !this.isVivoFullScreen(context)) {
                        hasNavigationBar = this.isHasNavigationBar(context);
                    }
                }
            }
        }
        return hasNavigationBar;
    }
    
    public final int px2dp(final Context context, final float n) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        final Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue((Object)resources, "context.resources");
        return (int)(n / resources.getDisplayMetrics().density + 0.5f);
    }
    
    public final int px2sp(final Context context, final float n) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        final Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue((Object)resources, "context.resources");
        return (int)(n / resources.getDisplayMetrics().scaledDensity + 0.5f);
    }
    
    public final int rejectedNavHeight(final Context context) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        final Point screenSize = this.getScreenSize(context);
        if (screenSize.x > screenSize.y) {
            return screenSize.y;
        }
        return screenSize.y - this.getNavigationBarCurrentHeight(context);
    }
    
    public final int sp2px(final Context context, final float n) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        final Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue((Object)resources, "context.resources");
        return (int)(n * resources.getDisplayMetrics().scaledDensity + 0.5f);
    }
    
    public final int statusBarHeight(final View view) {
        Intrinsics.checkNotNullParameter((Object)view, "view");
        final Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue((Object)context, "view.context");
        final Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue((Object)applicationContext, "view.context.applicationContext");
        return this.getStatusBarHeight(applicationContext);
    }
}
