package com.luck.picture.lib.utils;

import androidx.fragment.app.FragmentActivity;
import android.content.ContextWrapper;
import android.app.Activity;
import android.content.Context;

public class ActivityCompatHelper
{
    private static final int MIN_FRAGMENT_COUNT = 1;
    
    public static boolean assertValidRequest(final Context context) {
        if (context instanceof Activity) {
            return isDestroy((Activity)context) ^ true;
        }
        if (context instanceof ContextWrapper) {
            final ContextWrapper contextWrapper = (ContextWrapper)context;
            if (contextWrapper.getBaseContext() instanceof Activity) {
                return isDestroy((Activity)contextWrapper.getBaseContext()) ^ true;
            }
        }
        return true;
    }
    
    public static boolean checkFragmentNonExits(final FragmentActivity fragmentActivity, final String s) {
        final boolean destroy = isDestroy((Activity)fragmentActivity);
        boolean b = false;
        if (destroy) {
            return false;
        }
        if (fragmentActivity.getSupportFragmentManager().findFragmentByTag(s) == null) {
            b = true;
        }
        return b;
    }
    
    public static boolean checkRootFragment(final FragmentActivity fragmentActivity) {
        final boolean destroy = isDestroy((Activity)fragmentActivity);
        boolean b = false;
        if (destroy) {
            return false;
        }
        if (fragmentActivity.getSupportFragmentManager().getBackStackEntryCount() == 1) {
            b = true;
        }
        return b;
    }
    
    public static boolean isDestroy(final Activity activity) {
        final boolean b = true;
        if (activity == null) {
            return true;
        }
        boolean b2 = b;
        if (!activity.isFinishing()) {
            b2 = (activity.isDestroyed() && b);
        }
        return b2;
    }
}
