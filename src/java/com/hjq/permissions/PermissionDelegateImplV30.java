package com.hjq.permissions;

import android.app.Activity;
import android.os.Environment;
import android.content.Intent;
import android.content.Context;

class PermissionDelegateImplV30 extends PermissionDelegateImplV29
{
    private static Intent getManageStoragePermissionIntent(final Context context) {
        final Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        Intent intent2 = intent;
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            intent2 = new Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION");
        }
        Intent applicationDetailsIntent = intent2;
        if (!PermissionUtils.areActivityIntent(context, intent2)) {
            applicationDetailsIntent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return applicationDetailsIntent;
    }
    
    private static boolean isGrantedManageStoragePermission() {
        return Environment.isExternalStorageManager();
    }
    
    @Override
    public Intent getPermissionIntent(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
            return getManageStoragePermissionIntent(context);
        }
        return super.getPermissionIntent(context, s);
    }
    
    @Override
    public boolean isGrantedPermission(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
            return isGrantedManageStoragePermission();
        }
        return super.isGrantedPermission(context, s);
    }
    
    @Override
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        return !PermissionUtils.equalsPermission(s, "android.permission.MANAGE_EXTERNAL_STORAGE") && super.isPermissionPermanentDenied(activity, s);
    }
}
