package com.hjq.permissions;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.content.Context;

class PermissionDelegateImplV31 extends PermissionDelegateImplV30
{
    private static Intent getAlarmPermissionIntent(final Context context) {
        final Intent intent = new Intent("android.settings.REQUEST_SCHEDULE_EXACT_ALARM");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        Intent applicationDetailsIntent = intent;
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            applicationDetailsIntent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return applicationDetailsIntent;
    }
    
    private static boolean isGrantedAlarmPermission(final Context context) {
        return ((AlarmManager)context.getSystemService((Class)AlarmManager.class)).canScheduleExactAlarms();
    }
    
    @Override
    public Intent getPermissionIntent(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.SCHEDULE_EXACT_ALARM")) {
            return getAlarmPermissionIntent(context);
        }
        return super.getPermissionIntent(context, s);
    }
    
    @Override
    public boolean isGrantedPermission(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.SCHEDULE_EXACT_ALARM")) {
            return isGrantedAlarmPermission(context);
        }
        if (!PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_SCAN") && !PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_CONNECT") && !PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_ADVERTISE")) {
            return super.isGrantedPermission(context, s);
        }
        return PermissionUtils.checkSelfPermission(context, s);
    }
    
    @Override
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        final boolean equalsPermission = PermissionUtils.equalsPermission(s, "android.permission.SCHEDULE_EXACT_ALARM");
        final boolean b = false;
        final boolean b2 = false;
        final boolean b3 = false;
        if (equalsPermission) {
            return false;
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_SCAN") || PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_CONNECT") || PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_ADVERTISE")) {
            boolean b4 = b2;
            if (!PermissionUtils.checkSelfPermission((Context)activity, s)) {
                b4 = b2;
                if (!PermissionUtils.shouldShowRequestPermissionRationale(activity, s)) {
                    b4 = true;
                }
            }
            return b4;
        }
        if (activity.getApplicationInfo().targetSdkVersion < 31 || !PermissionUtils.equalsPermission(s, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
            return super.isPermissionPermanentDenied(activity, s);
        }
        if (!PermissionUtils.checkSelfPermission((Context)activity, "android.permission.ACCESS_FINE_LOCATION") && !PermissionUtils.checkSelfPermission((Context)activity, "android.permission.ACCESS_COARSE_LOCATION")) {
            boolean b5 = b3;
            if (!PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION")) {
                b5 = b3;
                if (!PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_COARSE_LOCATION")) {
                    b5 = true;
                }
            }
            return b5;
        }
        boolean b6 = b;
        if (!PermissionUtils.checkSelfPermission((Context)activity, s)) {
            b6 = b;
            if (!PermissionUtils.shouldShowRequestPermissionRationale(activity, s)) {
                b6 = true;
            }
        }
        return b6;
    }
}
