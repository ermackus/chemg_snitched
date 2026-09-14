package com.hjq.permissions;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;

class PermissionDelegateImplV21 extends PermissionDelegateImplV19
{
    private static Intent getPackagePermissionIntent(final Context context) {
        final Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
        if (AndroidVersion.isAndroid10()) {
            intent.setData(PermissionUtils.getPackageNameUri(context));
        }
        Intent applicationDetailsIntent = intent;
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            applicationDetailsIntent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return applicationDetailsIntent;
    }
    
    private static boolean isGrantedPackagePermission(final Context context) {
        return PermissionUtils.checkOpNoThrow(context, "android:get_usage_stats");
    }
    
    @Override
    public Intent getPermissionIntent(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.PACKAGE_USAGE_STATS")) {
            return getPackagePermissionIntent(context);
        }
        return super.getPermissionIntent(context, s);
    }
    
    @Override
    public boolean isGrantedPermission(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.PACKAGE_USAGE_STATS")) {
            return isGrantedPackagePermission(context);
        }
        return super.isGrantedPermission(context, s);
    }
    
    @Override
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        return !PermissionUtils.equalsPermission(s, "android.permission.PACKAGE_USAGE_STATS") && super.isPermissionPermanentDenied(activity, s);
    }
}
