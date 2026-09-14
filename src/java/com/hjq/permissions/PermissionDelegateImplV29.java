package com.hjq.permissions;

import android.app.Activity;
import android.os.Environment;
import android.content.Context;

class PermissionDelegateImplV29 extends PermissionDelegateImplV28
{
    private boolean hasReadStoragePermission(final Context context) {
        final boolean android13 = AndroidVersion.isAndroid13();
        boolean b = false;
        final boolean b2 = false;
        if (android13 && AndroidVersion.getTargetSdkVersionCode(context) >= 33) {
            if (!PermissionUtils.checkSelfPermission(context, "android.permission.READ_MEDIA_IMAGES")) {
                final boolean b3 = b2;
                if (!this.isGrantedPermission(context, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
                    return b3;
                }
            }
            return true;
        }
        if (AndroidVersion.isAndroid11() && AndroidVersion.getTargetSdkVersionCode(context) >= 30) {
            if (PermissionUtils.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") || this.isGrantedPermission(context, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
                b = true;
            }
            return b;
        }
        return PermissionUtils.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE");
    }
    
    private static boolean isUseDeprecationExternalStorage() {
        return Environment.isExternalStorageLegacy();
    }
    
    @Override
    public boolean isGrantedPermission(final Context context, final String s) {
        final boolean equalsPermission = PermissionUtils.equalsPermission(s, "android.permission.ACCESS_MEDIA_LOCATION");
        final boolean b = false;
        if (equalsPermission) {
            boolean b2 = b;
            if (this.hasReadStoragePermission(context)) {
                b2 = b;
                if (PermissionUtils.checkSelfPermission(context, "android.permission.ACCESS_MEDIA_LOCATION")) {
                    b2 = true;
                }
            }
            return b2;
        }
        if (!PermissionUtils.equalsPermission(s, "android.permission.ACCESS_BACKGROUND_LOCATION") && !PermissionUtils.equalsPermission(s, "android.permission.ACTIVITY_RECOGNITION")) {
            return (AndroidVersion.isAndroid11() || !PermissionUtils.equalsPermission(s, "android.permission.MANAGE_EXTERNAL_STORAGE") || isUseDeprecationExternalStorage()) && super.isGrantedPermission(context, s);
        }
        return PermissionUtils.checkSelfPermission(context, s);
    }
    
    @Override
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        final boolean equalsPermission = PermissionUtils.equalsPermission(s, "android.permission.ACCESS_BACKGROUND_LOCATION");
        final boolean b = false;
        final boolean b2 = false;
        final boolean b3 = false;
        if (equalsPermission) {
            if (!PermissionUtils.checkSelfPermission((Context)activity, "android.permission.ACCESS_FINE_LOCATION")) {
                return PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION") ^ true;
            }
            boolean b4 = b3;
            if (!PermissionUtils.checkSelfPermission((Context)activity, s)) {
                b4 = b3;
                if (!PermissionUtils.shouldShowRequestPermissionRationale(activity, s)) {
                    b4 = true;
                }
            }
            return b4;
        }
        else {
            if (PermissionUtils.equalsPermission(s, "android.permission.ACCESS_MEDIA_LOCATION")) {
                boolean b5 = b;
                if (this.hasReadStoragePermission((Context)activity)) {
                    b5 = b;
                    if (!PermissionUtils.checkSelfPermission((Context)activity, s)) {
                        b5 = b;
                        if (!PermissionUtils.shouldShowRequestPermissionRationale(activity, s)) {
                            b5 = true;
                        }
                    }
                }
                return b5;
            }
            if (PermissionUtils.equalsPermission(s, "android.permission.ACTIVITY_RECOGNITION")) {
                boolean b6 = b2;
                if (!PermissionUtils.checkSelfPermission((Context)activity, s)) {
                    b6 = b2;
                    if (!PermissionUtils.shouldShowRequestPermissionRationale(activity, s)) {
                        b6 = true;
                    }
                }
                return b6;
            }
            return (!AndroidVersion.isAndroid11() && PermissionUtils.equalsPermission(s, "android.permission.MANAGE_EXTERNAL_STORAGE") && !isUseDeprecationExternalStorage()) || super.isPermissionPermanentDenied(activity, s);
        }
    }
}
