package com.hjq.permissions;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;

final class NotificationPermissionCompat
{
    private static final int OP_POST_NOTIFICATION_DEFAULT_VALUE = 11;
    private static final String OP_POST_NOTIFICATION_FIELD_NAME = "OP_POST_NOTIFICATION";
    
    static Intent getPermissionIntent(final Context context) {
        Intent intent;
        if (AndroidVersion.isAndroid8()) {
            intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        }
        else if (AndroidVersion.isAndroid5()) {
            intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        }
        else {
            intent = null;
        }
        Intent applicationDetailsIntent = intent;
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            applicationDetailsIntent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return applicationDetailsIntent;
    }
    
    static boolean isGrantedPermission(final Context context) {
        if (AndroidVersion.isAndroid7()) {
            return ((NotificationManager)context.getSystemService((Class)NotificationManager.class)).areNotificationsEnabled();
        }
        return !AndroidVersion.isAndroid4_4() || PermissionUtils.checkOpNoThrow(context, "OP_POST_NOTIFICATION", 11);
    }
}
