package com.kingagroot.kingdraw.utils;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import android.app.Activity;

public class GoogleCheckUtil
{
    public static final String TAG = "GoogleCheckUtil";
    
    public static boolean onCheckGooglePlayServices(final Activity activity) {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable((Context)activity) == 0;
    }
}
