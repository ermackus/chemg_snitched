package com.hjq.permissions;

import android.app.Activity;
import android.provider.Settings$System;
import android.app.NotificationManager;
import android.os.PowerManager;
import android.content.Intent;
import android.content.Context;

class PermissionDelegateImplV23 extends PermissionDelegateImplV21
{
    private static Intent getIgnoreBatteryPermissionIntent(final Context context) {
        final Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        Intent intent2 = intent;
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            intent2 = new Intent("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS");
        }
        Intent applicationDetailsIntent = intent2;
        if (!PermissionUtils.areActivityIntent(context, intent2)) {
            applicationDetailsIntent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return applicationDetailsIntent;
    }
    
    private static Intent getNotDisturbPermissionIntent(final Context context) {
        Intent intent = new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_DETAIL_SETTINGS");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        if (PhoneRomUtils.isHarmonyOs() || PhoneRomUtils.isMagicOs()) {
            intent = new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS");
        }
        Intent applicationDetailsIntent = intent;
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            applicationDetailsIntent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return applicationDetailsIntent;
    }
    
    private static Intent getSettingPermissionIntent(final Context context) {
        final Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        Intent applicationDetailsIntent = intent;
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            applicationDetailsIntent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return applicationDetailsIntent;
    }
    
    private static boolean isGrantedIgnoreBatteryPermission(final Context context) {
        return ((PowerManager)context.getSystemService((Class)PowerManager.class)).isIgnoringBatteryOptimizations(context.getPackageName());
    }
    
    private static boolean isGrantedNotDisturbPermission(final Context context) {
        return ((NotificationManager)context.getSystemService((Class)NotificationManager.class)).isNotificationPolicyAccessGranted();
    }
    
    private static boolean isGrantedSettingPermission(final Context context) {
        return !AndroidVersion.isAndroid6() || Settings$System.canWrite(context);
    }
    
    @Override
    public Intent getPermissionIntent(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.WRITE_SETTINGS")) {
            return getSettingPermissionIntent(context);
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.ACCESS_NOTIFICATION_POLICY")) {
            return getNotDisturbPermissionIntent(context);
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS")) {
            return getIgnoreBatteryPermissionIntent(context);
        }
        return super.getPermissionIntent(context, s);
    }
    
    @Override
    public boolean isGrantedPermission(final Context context, final String s) {
        if (!AndroidVersion.isAndroid13()) {
            if (PermissionUtils.equalsPermission(s, "android.permission.POST_NOTIFICATIONS")) {
                return super.isGrantedPermission(context, s);
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.NEARBY_WIFI_DEVICES")) {
                return PermissionUtils.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION");
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.BODY_SENSORS_BACKGROUND")) {
                return PermissionUtils.checkSelfPermission(context, "android.permission.BODY_SENSORS");
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_IMAGES") || PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_VIDEO") || PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_AUDIO")) {
                return PermissionUtils.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE");
            }
        }
        final boolean android12 = AndroidVersion.isAndroid12();
        boolean b = true;
        if (!android12) {
            if (PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_SCAN")) {
                return PermissionUtils.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION");
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_CONNECT") || PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_ADVERTISE")) {
                return true;
            }
        }
        if (!AndroidVersion.isAndroid11() && PermissionUtils.equalsPermission(s, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
            if (!PermissionUtils.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") || !PermissionUtils.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                b = false;
            }
            return b;
        }
        if (!AndroidVersion.isAndroid10()) {
            if (PermissionUtils.equalsPermission(s, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                return PermissionUtils.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION");
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.ACTIVITY_RECOGNITION")) {
                return true;
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.ACCESS_MEDIA_LOCATION")) {
                return PermissionUtils.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE");
            }
        }
        if (!AndroidVersion.isAndroid9() && PermissionUtils.equalsPermission(s, "android.permission.ACCEPT_HANDOVER")) {
            return true;
        }
        if (!AndroidVersion.isAndroid8()) {
            if (PermissionUtils.equalsPermission(s, "android.permission.ANSWER_PHONE_CALLS")) {
                return true;
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.READ_PHONE_NUMBERS")) {
                return PermissionUtils.checkSelfPermission(context, "android.permission.READ_PHONE_STATE");
            }
        }
        if (PermissionUtils.equalsPermission(s, "com.android.permission.GET_INSTALLED_APPS") || PermissionUtils.equalsPermission(s, "android.permission.POST_NOTIFICATIONS")) {
            return super.isGrantedPermission(context, s);
        }
        if (!PermissionUtils.isSpecialPermission(s)) {
            return PermissionUtils.checkSelfPermission(context, s);
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.WRITE_SETTINGS")) {
            return isGrantedSettingPermission(context);
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.ACCESS_NOTIFICATION_POLICY")) {
            return isGrantedNotDisturbPermission(context);
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS")) {
            return isGrantedIgnoreBatteryPermission(context);
        }
        return super.isGrantedPermission(context, s);
    }
    
    @Override
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        final boolean android13 = AndroidVersion.isAndroid13();
        final boolean b = true;
        final boolean b2 = true;
        final boolean b3 = true;
        final boolean b4 = true;
        boolean b5 = true;
        final boolean b6 = true;
        final boolean b7 = true;
        final boolean b8 = true;
        if (!android13) {
            if (PermissionUtils.equalsPermission(s, "android.permission.POST_NOTIFICATIONS")) {
                return super.isPermissionPermanentDenied(activity, s);
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.NEARBY_WIFI_DEVICES")) {
                return !PermissionUtils.checkSelfPermission((Context)activity, "android.permission.ACCESS_FINE_LOCATION") && !PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION") && b8;
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.BODY_SENSORS_BACKGROUND")) {
                return !PermissionUtils.checkSelfPermission((Context)activity, "android.permission.BODY_SENSORS") && !PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.BODY_SENSORS") && b;
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_IMAGES") || PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_VIDEO") || PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_AUDIO")) {
                return !PermissionUtils.checkSelfPermission((Context)activity, "android.permission.READ_EXTERNAL_STORAGE") && !PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.READ_EXTERNAL_STORAGE") && b2;
            }
        }
        if (!AndroidVersion.isAndroid12()) {
            if (PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_SCAN")) {
                return !PermissionUtils.checkSelfPermission((Context)activity, "android.permission.ACCESS_FINE_LOCATION") && !PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION") && b3;
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_CONNECT") || PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_ADVERTISE")) {
                return false;
            }
        }
        if (!AndroidVersion.isAndroid10()) {
            if (PermissionUtils.equalsPermission(s, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                return !PermissionUtils.checkSelfPermission((Context)activity, "android.permission.ACCESS_FINE_LOCATION") && !PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION") && b4;
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.ACTIVITY_RECOGNITION")) {
                return false;
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.ACCESS_MEDIA_LOCATION")) {
                if (PermissionUtils.checkSelfPermission((Context)activity, "android.permission.READ_EXTERNAL_STORAGE") || PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.READ_EXTERNAL_STORAGE")) {
                    b5 = false;
                }
                return b5;
            }
        }
        if (!AndroidVersion.isAndroid9() && PermissionUtils.equalsPermission(s, "android.permission.ACCEPT_HANDOVER")) {
            return false;
        }
        if (!AndroidVersion.isAndroid8()) {
            if (PermissionUtils.equalsPermission(s, "android.permission.ANSWER_PHONE_CALLS")) {
                return false;
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.READ_PHONE_NUMBERS")) {
                return !PermissionUtils.checkSelfPermission((Context)activity, "android.permission.READ_PHONE_STATE") && !PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.READ_PHONE_STATE") && b6;
            }
        }
        if (!PermissionUtils.equalsPermission(s, "com.android.permission.GET_INSTALLED_APPS") && !PermissionUtils.equalsPermission(s, "android.permission.POST_NOTIFICATIONS")) {
            return !PermissionUtils.isSpecialPermission(s) && !PermissionUtils.checkSelfPermission((Context)activity, s) && !PermissionUtils.shouldShowRequestPermissionRationale(activity, s) && b7;
        }
        return super.isPermissionPermanentDenied(activity, s);
    }
}
