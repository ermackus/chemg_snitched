package com.hjq.permissions;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;

class PermissionDelegateImplV18 extends PermissionDelegateImplV14
{
    public Intent getPermissionIntent(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE")) {
            return NotificationListenerPermissionCompat.getPermissionIntent(context);
        }
        return super.getPermissionIntent(context, s);
    }
    
    public boolean isGrantedPermission(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE")) {
            return NotificationListenerPermissionCompat.isGrantedPermission(context);
        }
        return super.isGrantedPermission(context, s);
    }
    
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        return !PermissionUtils.equalsPermission(s, "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE") && super.isPermissionPermanentDenied(activity, s);
    }
}
