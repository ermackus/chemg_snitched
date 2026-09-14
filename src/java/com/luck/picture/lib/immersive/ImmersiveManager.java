package com.luck.picture.lib.immersive;

import androidx.core.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import com.luck.picture.lib.utils.DensityUtil;
import android.view.Window;
import android.app.Activity;
import android.os.Build$VERSION;
import androidx.appcompat.app.AppCompatActivity;

public class ImmersiveManager
{
    private static final String TAG_FAKE_STATUS_BAR_VIEW = "TAG_FAKE_STATUS_BAR_VIEW";
    private static final String TAG_MARGIN_ADDED = "TAG_MARGIN_ADDED";
    private static final String TAG_NAVIGATION_BAR_VIEW = "TAG_NAVIGATION_BAR_VIEW";
    
    public static void immersiveAboveAPI23(final AppCompatActivity appCompatActivity, final int n, final int n2, final boolean b) {
        immersiveAboveAPI23(appCompatActivity, false, false, n, n2, b);
    }
    
    public static void immersiveAboveAPI23(final AppCompatActivity appCompatActivity, final boolean b, final boolean b2, final int statusBarColor, final int navigationBarColor, final boolean b3) {
        try {
            final Window window = appCompatActivity.getWindow();
            if (Build$VERSION.SDK_INT >= 19 && Build$VERSION.SDK_INT < 21) {
                if (b3) {
                    initBarBelowLOLLIPOP((Activity)appCompatActivity);
                }
                else {
                    window.setFlags(67108864, 67108864);
                }
            }
            else if (Build$VERSION.SDK_INT >= 21) {
                final boolean b4 = false;
                final boolean b5 = true;
                if (b && b2) {
                    window.clearFlags(201326592);
                    boolean b6 = b4;
                    if (statusBarColor == 0) {
                        b6 = true;
                    }
                    LightStatusBarUtils.setLightStatusBar((Activity)appCompatActivity, true, true, b6, b3);
                    window.addFlags(Integer.MIN_VALUE);
                }
                else if (!b && !b2) {
                    if (Build$VERSION.SDK_INT < 23 && b3) {
                        initBarBelowLOLLIPOP((Activity)appCompatActivity);
                    }
                    else {
                        window.requestFeature(1);
                        window.clearFlags(201326592);
                        LightStatusBarUtils.setLightStatusBar((Activity)appCompatActivity, false, false, statusBarColor == 0 && b5, b3);
                        window.addFlags(Integer.MIN_VALUE);
                    }
                }
                else {
                    if (b) {
                        return;
                    }
                    window.requestFeature(1);
                    window.clearFlags(201326592);
                    LightStatusBarUtils.setLightStatusBar((Activity)appCompatActivity, false, true, statusBarColor == 0, b3);
                    window.addFlags(Integer.MIN_VALUE);
                }
                window.setStatusBarColor(statusBarColor);
                window.setNavigationBarColor(navigationBarColor);
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void initBarBelowLOLLIPOP(final Activity activity) {
        final Window window = activity.getWindow();
        window.addFlags(67108864);
        setupStatusBarView(activity);
        if (DensityUtil.isNavBarVisible((Context)activity)) {
            window.addFlags(134217728);
            setupNavBarView(activity);
        }
    }
    
    private static void setupNavBarView(final Activity activity) {
        final Window window = activity.getWindow();
        View viewWithTag;
        if ((viewWithTag = window.getDecorView().findViewWithTag((Object)"TAG_NAVIGATION_BAR_VIEW")) == null) {
            viewWithTag = new View((Context)activity);
            viewWithTag.setTag((Object)"TAG_NAVIGATION_BAR_VIEW");
            ((ViewGroup)window.getDecorView()).addView(viewWithTag);
        }
        FrameLayout$LayoutParams layoutParams;
        if (DensityUtil.isNavigationAtBottom(activity)) {
            layoutParams = new FrameLayout$LayoutParams(-1, DensityUtil.getNavigationBarHeight((Context)activity));
            layoutParams.gravity = 80;
        }
        else {
            layoutParams = new FrameLayout$LayoutParams(DensityUtil.getNavigationBarWidth((Context)activity), -1);
            layoutParams.gravity = 8388613;
        }
        viewWithTag.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        viewWithTag.setBackgroundColor(0);
        viewWithTag.setVisibility(0);
    }
    
    private static void setupStatusBarView(final Activity activity) {
        final Window window = activity.getWindow();
        View viewWithTag;
        if ((viewWithTag = window.getDecorView().findViewWithTag((Object)"TAG_FAKE_STATUS_BAR_VIEW")) == null) {
            viewWithTag = new View((Context)activity);
            final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(-1, DensityUtil.getStatusBarHeight((Context)activity));
            layoutParams.gravity = 48;
            viewWithTag.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            viewWithTag.setVisibility(0);
            viewWithTag.setTag((Object)"TAG_MARGIN_ADDED");
            ((ViewGroup)window.getDecorView()).addView(viewWithTag);
        }
        viewWithTag.setBackgroundColor(0);
    }
    
    public static void translucentStatusBar(final Activity activity, final boolean b) {
        final Window window = activity.getWindow();
        if (Build$VERSION.SDK_INT >= 21) {
            window.addFlags(Integer.MIN_VALUE);
        }
        window.clearFlags(67108864);
        if (Build$VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        final View decorView = window.getDecorView();
        if (Build$VERSION.SDK_INT >= 23) {
            if (b) {
                decorView.setSystemUiVisibility(9472);
            }
            else {
                window.getDecorView().setSystemUiVisibility(1280);
            }
        }
        else if (b) {
            initBarBelowLOLLIPOP(activity);
        }
        else {
            window.getDecorView().setSystemUiVisibility(1280);
        }
        final View child = ((ViewGroup)window.findViewById(16908290)).getChildAt(0);
        if (child != null) {
            child.setFitsSystemWindows(false);
            ViewCompat.requestApplyInsets(child);
        }
    }
}
