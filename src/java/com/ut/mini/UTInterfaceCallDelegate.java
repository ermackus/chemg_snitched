package com.ut.mini;

import android.app.Activity;

public class UTInterfaceCallDelegate
{
    public static void pageAppearByAuto(final Activity activity) {
        UTPageHitHelper.getInstance().pageAppearByAuto(activity);
    }
    
    public static void pageDisAppearByAuto(final Activity activity) {
        UTPageHitHelper.getInstance().pageDisAppearByAuto(activity);
    }
}
