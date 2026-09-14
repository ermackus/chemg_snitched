package com.hjq.permissions;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;

class PermissionDelegateImplV19 extends PermissionDelegateImplV18
{
    @Override
    public Intent getPermissionIntent(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.SYSTEM_ALERT_WINDOW")) {
            return WindowPermissionCompat.getPermissionIntent(context);
        }
        if (PermissionUtils.equalsPermission(s, "com.android.permission.GET_INSTALLED_APPS")) {
            return GetInstalledAppsPermissionCompat.getPermissionIntent(context);
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.NOTIFICATION_SERVICE")) {
            return NotificationPermissionCompat.getPermissionIntent(context);
        }
        if (!AndroidVersion.isAndroid13() && PermissionUtils.equalsPermission(s, "android.permission.POST_NOTIFICATIONS")) {
            return NotificationPermissionCompat.getPermissionIntent(context);
        }
        return super.getPermissionIntent(context, s);
    }
    
    @Override
    public boolean isGrantedPermission(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.SYSTEM_ALERT_WINDOW")) {
            return WindowPermissionCompat.isGrantedPermission(context);
        }
        if (PermissionUtils.equalsPermission(s, "com.android.permission.GET_INSTALLED_APPS")) {
            return GetInstalledAppsPermissionCompat.isGrantedPermission(context);
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.NOTIFICATION_SERVICE")) {
            return NotificationPermissionCompat.isGrantedPermission(context);
        }
        if (!AndroidVersion.isAndroid13() && PermissionUtils.equalsPermission(s, "android.permission.POST_NOTIFICATIONS")) {
            return NotificationPermissionCompat.isGrantedPermission(context);
        }
        return super.isGrantedPermission(context, s);
    }
    
    @Override
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.SYSTEM_ALERT_WINDOW")) {
            return false;
        }
        if (PermissionUtils.equalsPermission(s, "com.android.permission.GET_INSTALLED_APPS")) {
            return GetInstalledAppsPermissionCompat.isPermissionPermanentDenied(activity);
        }
        return !PermissionUtils.equalsPermission(s, "android.permission.NOTIFICATION_SERVICE") && (AndroidVersion.isAndroid13() || !PermissionUtils.equalsPermission(s, "android.permission.POST_NOTIFICATIONS")) && super.isPermissionPermanentDenied(activity, s);
    }
}
