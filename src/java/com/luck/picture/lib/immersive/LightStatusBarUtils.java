package com.luck.picture.lib.immersive;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import android.view.WindowManager$LayoutParams;
import android.view.View;
import android.view.Window;
import android.os.Build$VERSION;
import android.app.Activity;

public class LightStatusBarUtils
{
    private static void initStatusBarStyle(final Activity activity, final boolean b, final boolean b2) {
        if (Build$VERSION.SDK_INT >= 16) {
            if (b && b2) {
                activity.getWindow().getDecorView().setSystemUiVisibility(256);
            }
            else if (!b && !b2) {
                activity.getWindow().getDecorView().setSystemUiVisibility(1280);
            }
            else if (!b && b2) {
                activity.getWindow().getDecorView().setSystemUiVisibility(1280);
            }
        }
    }
    
    private static void setAndroidNativeLightStatusBar(final Activity activity, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        Label_0159: {
            if (!b3) {
                break Label_0159;
            }
            try {
                final Window window = activity.getWindow();
                if (Build$VERSION.SDK_INT >= 21) {
                    if (b && b2) {
                        if (b4 && Build$VERSION.SDK_INT >= 23) {
                            window.getDecorView().setSystemUiVisibility(8448);
                        }
                        else {
                            window.getDecorView().setSystemUiVisibility(256);
                        }
                    }
                    else if (!b && !b2) {
                        if (b4 && Build$VERSION.SDK_INT >= 23) {
                            window.getDecorView().setSystemUiVisibility(9472);
                        }
                        else {
                            window.getDecorView().setSystemUiVisibility(1280);
                        }
                    }
                    else {
                        if (b || !b2) {
                            return;
                        }
                        if (b4 && Build$VERSION.SDK_INT >= 23) {
                            window.getDecorView().setSystemUiVisibility(9472);
                        }
                        else {
                            window.getDecorView().setSystemUiVisibility(1280);
                        }
                    }
                }
                return;
                Label_0190: {
                    final View decorView;
                    decorView.setSystemUiVisibility(0);
                }
                return;
                final View decorView = activity.getWindow().getDecorView();
                iftrue(Label_0190:)(!b4 || Build$VERSION.SDK_INT < 23);
                decorView.setSystemUiVisibility(8192);
            }
            catch (final Exception ex) {}
        }
    }
    
    private static boolean setFlymeLightStatusBar(final Activity activity, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        final boolean b5 = true;
        final boolean b6 = true;
        Label_0159: {
            if (activity == null) {
                break Label_0159;
            }
            initStatusBarStyle(activity, b, b2);
            boolean b7;
            try {
                final WindowManager$LayoutParams attributes = activity.getWindow().getAttributes();
                final Field declaredField = WindowManager$LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                final Field declaredField2 = WindowManager$LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                final int int1 = declaredField.getInt((Object)null);
                final int int2 = declaredField2.getInt((Object)attributes);
                int n;
                if (b4) {
                    n = (int1 | int2);
                }
                else {
                    n = (~int1 & int2);
                }
                declaredField2.setInt((Object)attributes, n);
                activity.getWindow().setAttributes(attributes);
                b7 = b5;
                final int n2 = RomUtils.getFlymeVersion();
                final int n3 = 7;
                if (n2 >= n3) {
                    final Activity activity2 = activity;
                    final boolean b8 = b;
                    final boolean b9 = b2;
                    final boolean b10 = b3;
                    final boolean b11 = b4;
                    setAndroidNativeLightStatusBar(activity2, b8, b9, b10, b11);
                    b7 = b5;
                    return b7;
                }
                return b7;
            }
            catch (final Exception ex) {
                b7 = false;
            }
            while (true) {
                try {
                    final int n2 = RomUtils.getFlymeVersion();
                    final int n3 = 7;
                    if (n2 >= n3) {
                        final Activity activity2 = activity;
                        final boolean b8 = b;
                        final boolean b9 = b2;
                        final boolean b10 = b3;
                        final boolean b11 = b4;
                        setAndroidNativeLightStatusBar(activity2, b8, b9, b10, b11);
                        b7 = b5;
                    }
                    return b7;
                    b7 = false;
                    return b7;
                    setAndroidNativeLightStatusBar(activity, b, b2, b3, b4);
                    return b7;
                }
                catch (final Exception ex2) {
                    b7 = b6;
                    continue;
                }
                break;
            }
        }
    }
    
    public static void setLightStatusBar(final Activity activity, final boolean b) {
        setLightStatusBar(activity, false, false, false, b);
    }
    
    public static void setLightStatusBar(final Activity activity, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        final int lightStatusBarAvailableRomType = RomUtils.getLightStatusBarAvailableRomType();
        if (lightStatusBarAvailableRomType != 1) {
            if (lightStatusBarAvailableRomType != 2) {
                if (lightStatusBarAvailableRomType == 3) {
                    setAndroidNativeLightStatusBar(activity, b, b2, b3, b4);
                }
            }
            else {
                setFlymeLightStatusBar(activity, b, b2, b3, b4);
            }
        }
        else if (RomUtils.getMIUIVersionCode() >= 7) {
            setAndroidNativeLightStatusBar(activity, b, b2, b3, b4);
        }
        else {
            setMIUILightStatusBar(activity, b, b2, b3, b4);
        }
    }
    
    public static void setLightStatusBarAboveAPI23(final Activity activity, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (Build$VERSION.SDK_INT >= 23) {
            setLightStatusBar(activity, b, b2, b3, b4);
        }
    }
    
    private static boolean setMIUILightStatusBar(final Activity activity, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        initStatusBarStyle(activity, b, b2);
        final Class<? extends Window> class1 = activity.getWindow().getClass();
        try {
            final Class<?> forName = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            final int int1 = forName.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt((Object)forName);
            final Method method = class1.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            final Window window = activity.getWindow();
            int n;
            if (b4) {
                n = int1;
            }
            else {
                n = 0;
            }
            method.invoke((Object)window, new Object[] { n, int1 });
            return true;
        }
        catch (final Exception ex) {
            setAndroidNativeLightStatusBar(activity, b, b2, b3, b4);
            return false;
        }
    }
}
