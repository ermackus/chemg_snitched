package com.hjq.permissions;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;

class PermissionDelegateImplV33 extends PermissionDelegateImplV31
{
    @Override
    public Intent getPermissionIntent(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.POST_NOTIFICATIONS")) {
            return NotificationPermissionCompat.getPermissionIntent(context);
        }
        return super.getPermissionIntent(context, s);
    }
    
    @Override
    public boolean isGrantedPermission(final Context context, final String s) {
        final boolean equalsPermission = PermissionUtils.equalsPermission(s, "android.permission.BODY_SENSORS_BACKGROUND");
        final boolean b = false;
        final boolean b2 = false;
        if (equalsPermission) {
            boolean b3 = b2;
            if (PermissionUtils.checkSelfPermission(context, "android.permission.BODY_SENSORS")) {
                b3 = b2;
                if (PermissionUtils.checkSelfPermission(context, "android.permission.BODY_SENSORS_BACKGROUND")) {
                    b3 = true;
                }
            }
            return b3;
        }
        if (!PermissionUtils.equalsPermission(s, "android.permission.POST_NOTIFICATIONS") && !PermissionUtils.equalsPermission(s, "android.permission.NEARBY_WIFI_DEVICES") && !PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_IMAGES") && !PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_VIDEO") && !PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_AUDIO")) {
            if (AndroidVersion.getTargetSdkVersionCode(context) >= 33) {
                if (PermissionUtils.equalsPermission(s, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    return true;
                }
                if (PermissionUtils.equalsPermission(s, "android.permission.READ_EXTERNAL_STORAGE")) {
                    boolean b4 = b;
                    if (PermissionUtils.checkSelfPermission(context, "android.permission.READ_MEDIA_IMAGES")) {
                        b4 = b;
                        if (PermissionUtils.checkSelfPermission(context, "android.permission.READ_MEDIA_VIDEO")) {
                            b4 = b;
                            if (PermissionUtils.checkSelfPermission(context, "android.permission.READ_MEDIA_AUDIO")) {
                                b4 = true;
                            }
                        }
                    }
                    return b4;
                }
            }
            return super.isGrantedPermission(context, s);
        }
        return PermissionUtils.checkSelfPermission(context, s);
    }
    
    @Override
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        final boolean equalsPermission = PermissionUtils.equalsPermission(s, "android.permission.BODY_SENSORS_BACKGROUND");
        boolean b = true;
        final boolean b2 = true;
        final boolean b3 = true;
        if (equalsPermission) {
            if (!PermissionUtils.checkSelfPermission((Context)activity, "android.permission.BODY_SENSORS")) {
                return PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.BODY_SENSORS") ^ true;
            }
            return !PermissionUtils.checkSelfPermission((Context)activity, s) && !PermissionUtils.shouldShowRequestPermissionRationale(activity, s) && b3;
        }
        else {
            if (!PermissionUtils.equalsPermission(s, "android.permission.POST_NOTIFICATIONS") && !PermissionUtils.equalsPermission(s, "android.permission.NEARBY_WIFI_DEVICES") && !PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_IMAGES") && !PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_VIDEO") && !PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_AUDIO")) {
                if (AndroidVersion.getTargetSdkVersionCode((Context)activity) >= 33) {
                    if (PermissionUtils.equalsPermission(s, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                        return false;
                    }
                    if (PermissionUtils.equalsPermission(s, "android.permission.READ_EXTERNAL_STORAGE")) {
                        if (PermissionUtils.checkSelfPermission((Context)activity, "android.permission.READ_MEDIA_IMAGES") || PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.READ_MEDIA_IMAGES") || PermissionUtils.checkSelfPermission((Context)activity, "android.permission.READ_MEDIA_VIDEO") || PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.READ_MEDIA_VIDEO") || PermissionUtils.checkSelfPermission((Context)activity, "android.permission.READ_MEDIA_AUDIO") || PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.READ_MEDIA_AUDIO")) {
                            b = false;
                        }
                        return b;
                    }
                }
                return super.isPermissionPermanentDenied(activity, s);
            }
            return !PermissionUtils.checkSelfPermission((Context)activity, s) && !PermissionUtils.shouldShowRequestPermissionRationale(activity, s) && b2;
        }
    }
}
