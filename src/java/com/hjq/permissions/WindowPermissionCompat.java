package com.hjq.permissions;

import android.provider.Settings;
import android.content.Intent;
import android.content.Context;

final class WindowPermissionCompat
{
    private static final int OP_SYSTEM_ALERT_WINDOW_DEFAULT_VALUE = 24;
    private static final String OP_SYSTEM_ALERT_WINDOW_FIELD_NAME = "OP_SYSTEM_ALERT_WINDOW";
    
    static Intent getPermissionIntent(final Context context) {
        if (AndroidVersion.isAndroid6()) {
            if (AndroidVersion.isAndroid11() && PhoneRomUtils.isMiui() && PhoneRomUtils.isMiuiOptimization()) {
                return StartActivityManager.addSubIntentToMainIntent(PermissionIntentManager.getMiuiPermissionPageIntent(context), PermissionIntentManager.getApplicationDetailsIntent(context));
            }
            final Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
            intent.setData(PermissionUtils.getPackageNameUri(context));
            if (PermissionUtils.areActivityIntent(context, intent)) {
                return intent;
            }
            return PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        else {
            if (PhoneRomUtils.isEmui()) {
                return StartActivityManager.addSubIntentToMainIntent(PermissionIntentManager.getEmuiWindowPermissionPageIntent(context), PermissionIntentManager.getApplicationDetailsIntent(context));
            }
            if (PhoneRomUtils.isMiui()) {
                Intent miuiWindowPermissionPageIntent = null;
                if (PhoneRomUtils.isMiuiOptimization()) {
                    miuiWindowPermissionPageIntent = PermissionIntentManager.getMiuiWindowPermissionPageIntent(context);
                }
                return StartActivityManager.addSubIntentToMainIntent(miuiWindowPermissionPageIntent, PermissionIntentManager.getApplicationDetailsIntent(context));
            }
            if (PhoneRomUtils.isColorOs()) {
                return StartActivityManager.addSubIntentToMainIntent(PermissionIntentManager.getColorOsWindowPermissionPageIntent(context), PermissionIntentManager.getApplicationDetailsIntent(context));
            }
            if (PhoneRomUtils.isOriginOs()) {
                return StartActivityManager.addSubIntentToMainIntent(PermissionIntentManager.getOriginOsWindowPermissionPageIntent(context), PermissionIntentManager.getApplicationDetailsIntent(context));
            }
            if (PhoneRomUtils.isOneUi()) {
                return StartActivityManager.addSubIntentToMainIntent(PermissionIntentManager.getOneUiWindowPermissionPageIntent(context), PermissionIntentManager.getApplicationDetailsIntent(context));
            }
            return PermissionIntentManager.getApplicationDetailsIntent(context);
        }
    }
    
    static boolean isGrantedPermission(final Context context) {
        if (AndroidVersion.isAndroid6()) {
            return Settings.canDrawOverlays(context);
        }
        return !AndroidVersion.isAndroid4_4() || PermissionUtils.checkOpNoThrow(context, "OP_SYSTEM_ALERT_WINDOW", 24);
    }
}
