package com.hjq.permissions;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;

class PermissionDelegateImplV26 extends PermissionDelegateImplV23
{
    private static Intent getInstallPermissionIntent(final Context context) {
        final Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        Intent applicationDetailsIntent = intent;
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            applicationDetailsIntent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return applicationDetailsIntent;
    }
    
    private static Intent getPictureInPicturePermissionIntent(final Context context) {
        final Intent intent = new Intent("android.settings.PICTURE_IN_PICTURE_SETTINGS");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        Intent applicationDetailsIntent = intent;
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            applicationDetailsIntent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return applicationDetailsIntent;
    }
    
    private static boolean isGrantedInstallPermission(final Context context) {
        return context.getPackageManager().canRequestPackageInstalls();
    }
    
    private static boolean isGrantedPictureInPicturePermission(final Context context) {
        return PermissionUtils.checkOpNoThrow(context, "android:picture_in_picture");
    }
    
    public Intent getPermissionIntent(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.REQUEST_INSTALL_PACKAGES")) {
            return getInstallPermissionIntent(context);
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.PICTURE_IN_PICTURE")) {
            return getPictureInPicturePermissionIntent(context);
        }
        return super.getPermissionIntent(context, s);
    }
    
    public boolean isGrantedPermission(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.REQUEST_INSTALL_PACKAGES")) {
            return isGrantedInstallPermission(context);
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.PICTURE_IN_PICTURE")) {
            return isGrantedPictureInPicturePermission(context);
        }
        if (!PermissionUtils.equalsPermission(s, "android.permission.READ_PHONE_NUMBERS") && !PermissionUtils.equalsPermission(s, "android.permission.ANSWER_PHONE_CALLS")) {
            return super.isGrantedPermission(context, s);
        }
        return PermissionUtils.checkSelfPermission(context, s);
    }
    
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        final boolean equalsPermission = PermissionUtils.equalsPermission(s, "android.permission.REQUEST_INSTALL_PACKAGES");
        final boolean b = false;
        if (equalsPermission) {
            return false;
        }
        if (PermissionUtils.equalsPermission(s, "android.permission.PICTURE_IN_PICTURE")) {
            return false;
        }
        if (!PermissionUtils.equalsPermission(s, "android.permission.READ_PHONE_NUMBERS") && !PermissionUtils.equalsPermission(s, "android.permission.ANSWER_PHONE_CALLS")) {
            return super.isPermissionPermanentDenied(activity, s);
        }
        boolean b2 = b;
        if (!PermissionUtils.checkSelfPermission((Context)activity, s)) {
            b2 = b;
            if (!PermissionUtils.shouldShowRequestPermissionRationale(activity, s)) {
                b2 = true;
            }
        }
        return b2;
    }
}
