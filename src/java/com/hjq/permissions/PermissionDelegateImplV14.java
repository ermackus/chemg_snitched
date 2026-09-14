package com.hjq.permissions;

import android.app.Activity;
import android.net.VpnService;
import android.content.Intent;
import android.content.Context;

class PermissionDelegateImplV14 implements PermissionDelegate
{
    private static Intent getVpnPermissionIntent(final Context context) {
        Intent intent;
        if (!PermissionUtils.areActivityIntent(context, intent = VpnService.prepare(context))) {
            intent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return intent;
    }
    
    private static boolean isGrantedVpnPermission(final Context context) {
        return VpnService.prepare(context) == null;
    }
    
    public Intent getPermissionIntent(final Context context, final String s) {
        if (PermissionUtils.equalsPermission(s, "android.permission.BIND_VPN_SERVICE")) {
            return getVpnPermissionIntent(context);
        }
        return PermissionIntentManager.getApplicationDetailsIntent(context);
    }
    
    public boolean isGrantedPermission(final Context context, final String s) {
        return !PermissionUtils.equalsPermission(s, "android.permission.BIND_VPN_SERVICE") || isGrantedVpnPermission(context);
    }
    
    public boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        return false;
    }
}
