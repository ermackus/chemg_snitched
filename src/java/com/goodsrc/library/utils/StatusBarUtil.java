package com.goodsrc.library.utils;

import java.lang.reflect.Field;
import android.view.WindowManager$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build$VERSION;
import android.app.Activity;
import androidx.core.graphics.ColorUtils;
import android.content.Context;
import java.lang.reflect.Method;
import android.view.Window;
import com.goodsrc.library.R;

public class StatusBarUtil
{
    private static final int STATUS_BAR_VIEW_ID;
    
    static {
        STATUS_BAR_VIEW_ID = R.id.m_status_bar_ids;
    }
    
    public static boolean MIUISetStatusBarLightMode(final Window window, final boolean b) {
        final boolean b2 = true;
        Label_0121: {
            if (window == null) {
                break Label_0121;
            }
            final Class<? extends Window> class1 = window.getClass();
            try {
                final Class<?> forName = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                final int int1 = forName.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt((Object)forName);
                final Method method = class1.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                boolean b3;
                if (b) {
                    method.invoke((Object)window, new Object[] { int1, int1 });
                    b3 = b2;
                }
                else {
                    method.invoke((Object)window, new Object[] { 0, int1 });
                    b3 = b2;
                }
                return b3;
                b3 = false;
                return b3;
            }
            catch (final Exception ex) {
                return false;
            }
        }
    }
    
    public static int getHeight(final Context context) {
        final boolean b = false;
        int dimensionPixelSize;
        try {
            final int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            dimensionPixelSize = (b ? 1 : 0);
            if (identifier > 0) {
                dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            dimensionPixelSize = (b ? 1 : 0);
        }
        return dimensionPixelSize;
    }
    
    public static boolean isDarkColor(final int n) {
        return ColorUtils.calculateLuminance(n) < 0.5;
    }
    
    public static void setColor(final Context context, final int n) {
        if (context instanceof Activity) {
            setColor(((Activity)context).getWindow(), n);
        }
    }
    
    public static void setColor(final Window window, final int statusBarColor) {
        if (Build$VERSION.SDK_INT >= 21) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.clearFlags(134217728);
            window.getDecorView().setSystemUiVisibility(256);
            window.setStatusBarColor(statusBarColor);
            setTextDark(window, isDarkColor(statusBarColor) ^ true);
        }
        else if (Build$VERSION.SDK_INT >= 19) {
            setColor(window, ColorUtils.blendARGB(0, statusBarColor, 0.5f), false);
        }
    }
    
    public static void setColor(final Window window, final int n, final boolean b) {
        final Context context = window.getContext();
        window.addFlags(67108864);
        window.clearFlags(134217728);
        final ViewGroup viewGroup = (ViewGroup)window.getDecorView();
        final View viewById = viewGroup.findViewById(16908290);
        if (viewById != null) {
            int height;
            if (b) {
                height = 0;
            }
            else {
                height = getHeight(context);
            }
            viewById.setPadding(0, height, 0, 0);
        }
        final View viewById2 = viewGroup.findViewById(StatusBarUtil.STATUS_BAR_VIEW_ID);
        if (viewById2 != null) {
            viewById2.setBackgroundColor(n);
            if (viewById2.getVisibility() == 8) {
                viewById2.setVisibility(0);
            }
        }
        else {
            final View view = new View(context);
            view.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, getHeight(context)));
            view.setBackgroundColor(n);
            view.setId(StatusBarUtil.STATUS_BAR_VIEW_ID);
            viewGroup.addView(view);
        }
    }
    
    private static void setFlymeDark(final Window window, final boolean b) {
        if (window != null) {
            try {
                final WindowManager$LayoutParams attributes = window.getAttributes();
                final Field declaredField = WindowManager$LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                final Field declaredField2 = WindowManager$LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                final int int1 = declaredField.getInt((Object)null);
                final int int2 = declaredField2.getInt((Object)attributes);
                int n;
                if (b) {
                    n = (int2 | int1);
                }
                else {
                    n = (~int1 & int2);
                }
                declaredField2.setInt((Object)attributes, n);
                window.setAttributes(attributes);
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private static void setMIUIDark(final Window window, final boolean b) {
        try {
            final Class<? extends Window> class1 = window.getClass();
            final Class<?> forName = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            final int int1 = forName.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt((Object)forName);
            final Method method = class1.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            int n;
            if (b) {
                n = int1;
            }
            else {
                n = 0;
            }
            method.invoke((Object)window, new Object[] { n, int1 });
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void setTextDark(final Context context, final boolean b) {
        if (context instanceof Activity) {
            setTextDark(((Activity)context).getWindow(), b);
        }
    }
    
    public static void setTextDark(final Window window, final boolean b) {
        if (Build$VERSION.SDK_INT >= 23) {
            final View decorView = window.getDecorView();
            final int systemUiVisibility = decorView.getSystemUiVisibility();
            if (b) {
                decorView.setSystemUiVisibility(systemUiVisibility | 0x2000);
            }
            else {
                decorView.setSystemUiVisibility(systemUiVisibility & 0xFFFFDFFF);
            }
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            final SystemInfoUtil.SystemInfo systemInfo = SystemInfoUtil.getSystemInfo();
            if (systemInfo.getOs().equals((Object)"sys_miui")) {
                setMIUIDark(window, b);
            }
            else if (systemInfo.getOs().equals((Object)"sys_flyme")) {
                setFlymeDark(window, b);
            }
        }
    }
    
    public static void setTransparent(final Context context) {
        if (context instanceof Activity) {
            setTransparent(((Activity)context).getWindow());
        }
    }
    
    public static void setTransparent(final Window window) {
        if (Build$VERSION.SDK_INT >= 21) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.clearFlags(134217728);
            window.getDecorView().setSystemUiVisibility(1280);
            window.setStatusBarColor(0);
        }
        else if (Build$VERSION.SDK_INT >= 19) {
            setColor(window, Integer.MIN_VALUE, true);
        }
    }
}
