package com.hjq.permissions;

import android.content.pm.PermissionInfo;
import android.provider.Settings$SettingNotFoundException;
import android.provider.Settings$Secure;
import android.content.pm.PackageManager$NameNotFoundException;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Intent;
import android.content.Context;

final class GetInstalledAppsPermissionCompat
{
    private static final int MIUI_OP_GET_INSTALLED_APPS_DEFAULT_VALUE = 10022;
    private static final String MIUI_OP_GET_INSTALLED_APPS_FIELD_NAME = "OP_GET_INSTALLED_APPS";
    
    static Intent getPermissionIntent(final Context context) {
        if (PhoneRomUtils.isMiui()) {
            Intent miuiPermissionPageIntent = null;
            if (PhoneRomUtils.isMiuiOptimization()) {
                miuiPermissionPageIntent = PermissionIntentManager.getMiuiPermissionPageIntent(context);
            }
            return StartActivityManager.addSubIntentToMainIntent(miuiPermissionPageIntent, PermissionIntentManager.getApplicationDetailsIntent(context));
        }
        return PermissionIntentManager.getApplicationDetailsIntent(context);
    }
    
    static boolean isGrantedPermission(final Context context) {
        if (!AndroidVersion.isAndroid4_4()) {
            return true;
        }
        if (AndroidVersion.isAndroid6() && isSupportGetInstalledAppsPermission(context)) {
            return PermissionUtils.checkSelfPermission(context, "com.android.permission.GET_INSTALLED_APPS");
        }
        return !PhoneRomUtils.isMiui() || !isMiuiSupportGetInstalledAppsPermission() || !PhoneRomUtils.isMiuiOptimization() || PermissionUtils.checkOpNoThrow(context, "OP_GET_INSTALLED_APPS", 10022);
    }
    
    private static boolean isMiuiSupportGetInstalledAppsPermission() {
        if (!AndroidVersion.isAndroid4_4()) {
            return true;
        }
        try {
            Class.forName(AppOpsManager.class.getName()).getDeclaredField("OP_GET_INSTALLED_APPS");
            return true;
        }
        catch (final NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        catch (final ClassNotFoundException ex2) {
            ex2.printStackTrace();
        }
        return true;
    }
    
    static boolean isPermissionPermanentDenied(final Activity activity) {
        final boolean android4_4 = AndroidVersion.isAndroid4_4();
        final boolean b = false;
        if (!android4_4) {
            return false;
        }
        if (AndroidVersion.isAndroid6() && isSupportGetInstalledAppsPermission((Context)activity)) {
            boolean b2 = b;
            if (!PermissionUtils.checkSelfPermission((Context)activity, "com.android.permission.GET_INSTALLED_APPS")) {
                b2 = b;
                if (!PermissionUtils.shouldShowRequestPermissionRationale(activity, "com.android.permission.GET_INSTALLED_APPS")) {
                    b2 = true;
                }
            }
            return b2;
        }
        return PhoneRomUtils.isMiui() && isMiuiSupportGetInstalledAppsPermission() && PhoneRomUtils.isMiuiOptimization() && (isGrantedPermission((Context)activity) ^ true);
    }
    
    private static boolean isSupportGetInstalledAppsPermission(final Context context) {
        final boolean b = false;
        final boolean b2 = false;
        boolean b3 = false;
        try {
            final PermissionInfo permissionInfo = context.getPackageManager().getPermissionInfo("com.android.permission.GET_INSTALLED_APPS", 0);
            if (permissionInfo != null) {
                if (AndroidVersion.isAndroid9()) {
                    if (permissionInfo.getProtection() == 1) {
                        b3 = true;
                    }
                    return b3;
                }
                final int protectionLevel = permissionInfo.protectionLevel;
                boolean b4 = b;
                if ((protectionLevel & 0xF) == 0x1) {
                    b4 = true;
                }
                return b4;
            }
        }
        catch (final PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            final int int1 = Settings$Secure.getInt(context.getContentResolver(), "oem_installed_apps_runtime_permission_enable");
            boolean b5 = b2;
            if (int1 == 1) {
                b5 = true;
            }
            return b5;
        }
        catch (final Settings$SettingNotFoundException ex2) {
            ex2.printStackTrace();
            return false;
        }
    }
}
