package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;

class PermissionDelegateImplV28 extends PermissionDelegateImplV26
{
    @Override
    public boolean isGrantedPermission(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.ACCEPT_HANDOVER")) {
            return PermissionUtils.checkSelfPermission(context, s);
        }
        return super.isGrantedPermission(context, s);
    }
    
    @Override
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.ACCEPT_HANDOVER")) {
            return !PermissionUtils.checkSelfPermission((Context)activity, s) && !PermissionUtils.shouldShowRequestPermissionRationale(activity, s);
        }
        return super.isPermissionPermanentDenied(activity, s);
    }
}
