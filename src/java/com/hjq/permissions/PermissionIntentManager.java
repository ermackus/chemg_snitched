package com.hjq.permissions;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;

final class PermissionIntentManager
{
    private static final String COLOR_OS_SAFE_CENTER_APP_PACKAGE_NAME_1 = "com.oppo.safe";
    private static final String COLOR_OS_SAFE_CENTER_APP_PACKAGE_NAME_2 = "com.color.safecenter";
    private static final String COLOR_OS_SAFE_CENTER_APP_PACKAGE_NAME_3 = "com.oplus.safecenter";
    private static final String EMUI_MOBILE_MANAGER_APP_PACKAGE_NAME = "com.huawei.systemmanager";
    private static final String MIUI_MOBILE_MANAGER_APP_PACKAGE_NAME = "com.miui.securitycenter";
    private static final String ORIGIN_OS_MOBILE_MANAGER_APP_PACKAGE_NAME = "com.iqoo.secure";
    
    static Intent getAndroidSettingAppIntent() {
        return new Intent("android.settings.SETTINGS");
    }
    
    static Intent getApplicationDetailsIntent(final Context context) {
        final Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        if (PermissionUtils.areActivityIntent(context, intent)) {
            return intent;
        }
        final Intent intent2 = new Intent("android.settings.APPLICATION_SETTINGS");
        if (PermissionUtils.areActivityIntent(context, intent2)) {
            return intent2;
        }
        final Intent intent3 = new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS");
        if (PermissionUtils.areActivityIntent(context, intent3)) {
            return intent3;
        }
        return getAndroidSettingAppIntent();
    }
    
    static Intent getColorOsWindowPermissionPageIntent(final Context context) {
        Intent intent = new Intent("com.oppo.safe.permission.PermissionTopActivity");
        final Intent oppoSafeCenterAppIntent = getOppoSafeCenterAppIntent(context);
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            intent = null;
        }
        Intent addSubIntentToMainIntent = intent;
        if (PermissionUtils.areActivityIntent(context, oppoSafeCenterAppIntent)) {
            addSubIntentToMainIntent = StartActivityManager.addSubIntentToMainIntent(intent, oppoSafeCenterAppIntent);
        }
        return addSubIntentToMainIntent;
    }
    
    static Intent getEmuiWindowPermissionPageIntent(final Context context) {
        final Intent intent = new Intent();
        intent.setClassName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");
        Intent intent2 = new Intent();
        intent2.setClassName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity");
        final Intent huaWeiMobileManagerAppIntent = getHuaWeiMobileManagerAppIntent(context);
        String romVersionName;
        if ((romVersionName = PhoneRomUtils.getRomVersionName()) == null) {
            romVersionName = "";
        }
        Intent intent3;
        if (romVersionName.startsWith("3.0")) {
            if (!PermissionUtils.areActivityIntent(context, intent2)) {
                intent2 = null;
            }
            intent3 = intent2;
            if (PermissionUtils.areActivityIntent(context, intent)) {
                intent3 = StartActivityManager.addSubIntentToMainIntent(intent2, intent);
            }
        }
        else {
            if (PermissionUtils.areActivityIntent(context, intent)) {
                intent3 = intent;
            }
            else {
                intent3 = null;
            }
            if (PermissionUtils.areActivityIntent(context, intent2)) {
                intent3 = StartActivityManager.addSubIntentToMainIntent(intent3, intent2);
            }
        }
        Intent addSubIntentToMainIntent = intent3;
        if (PermissionUtils.areActivityIntent(context, huaWeiMobileManagerAppIntent)) {
            addSubIntentToMainIntent = StartActivityManager.addSubIntentToMainIntent(intent3, huaWeiMobileManagerAppIntent);
        }
        return addSubIntentToMainIntent;
    }
    
    static Intent getHuaWeiMobileManagerAppIntent(final Context context) {
        final Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.huawei.systemmanager");
        if (PermissionUtils.areActivityIntent(context, launchIntentForPackage)) {
            return launchIntentForPackage;
        }
        return null;
    }
    
    static Intent getMiuiPermissionPageIntent(final Context context) {
        Intent putExtra = new Intent().setAction("miui.intent.action.APP_PERM_EDITOR").putExtra("extra_pkgname", context.getPackageName());
        final Intent xiaoMiMobileManagerAppIntent = getXiaoMiMobileManagerAppIntent(context);
        if (!PermissionUtils.areActivityIntent(context, putExtra)) {
            putExtra = null;
        }
        Intent addSubIntentToMainIntent = putExtra;
        if (PermissionUtils.areActivityIntent(context, xiaoMiMobileManagerAppIntent)) {
            addSubIntentToMainIntent = StartActivityManager.addSubIntentToMainIntent(putExtra, xiaoMiMobileManagerAppIntent);
        }
        return addSubIntentToMainIntent;
    }
    
    static Intent getMiuiWindowPermissionPageIntent(final Context context) {
        return getMiuiPermissionPageIntent(context);
    }
    
    static Intent getOneUiPermissionPageIntent(final Context context) {
        final Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$AppOpsDetailsActivity");
        final Bundle bundle = new Bundle();
        bundle.putString("package", context.getPackageName());
        intent.putExtra(":settings:show_fragment_args", bundle);
        intent.setData(PermissionUtils.getPackageNameUri(context));
        if (PermissionUtils.areActivityIntent(context, intent)) {
            return intent;
        }
        return null;
    }
    
    static Intent getOneUiWindowPermissionPageIntent(final Context context) {
        return getOneUiPermissionPageIntent(context);
    }
    
    static Intent getOppoSafeCenterAppIntent(final Context context) {
        final Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.oppo.safe");
        if (PermissionUtils.areActivityIntent(context, launchIntentForPackage)) {
            return launchIntentForPackage;
        }
        final Intent launchIntentForPackage2 = context.getPackageManager().getLaunchIntentForPackage("com.color.safecenter");
        if (PermissionUtils.areActivityIntent(context, launchIntentForPackage2)) {
            return launchIntentForPackage2;
        }
        final Intent launchIntentForPackage3 = context.getPackageManager().getLaunchIntentForPackage("com.oplus.safecenter");
        if (PermissionUtils.areActivityIntent(context, launchIntentForPackage3)) {
            return launchIntentForPackage3;
        }
        return null;
    }
    
    static Intent getOriginOsPermissionPageIntent(final Context context) {
        final Intent intent = new Intent("permission.intent.action.softPermissionDetail");
        intent.putExtra("packagename", context.getPackageName());
        if (PermissionUtils.areActivityIntent(context, intent)) {
            return intent;
        }
        return null;
    }
    
    static Intent getOriginOsWindowPermissionPageIntent(final Context context) {
        final Intent vivoMobileManagerAppIntent = getVivoMobileManagerAppIntent(context);
        if (PermissionUtils.areActivityIntent(context, vivoMobileManagerAppIntent)) {
            return vivoMobileManagerAppIntent;
        }
        return null;
    }
    
    static Intent getVivoMobileManagerAppIntent(final Context context) {
        final Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.iqoo.secure");
        if (PermissionUtils.areActivityIntent(context, launchIntentForPackage)) {
            return launchIntentForPackage;
        }
        return null;
    }
    
    static Intent getXiaoMiMobileManagerAppIntent(final Context context) {
        final Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.miui.securitycenter");
        if (PermissionUtils.areActivityIntent(context, launchIntentForPackage)) {
            return launchIntentForPackage;
        }
        return null;
    }
}
