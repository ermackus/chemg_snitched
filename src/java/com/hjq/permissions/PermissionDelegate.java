package com.hjq.permissions;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;

public interface PermissionDelegate
{
    Intent getPermissionIntent(final Context p0, final String p1);
    
    boolean isGrantedPermission(final Context p0, final String p1);
    
    boolean isPermissionPermanentDenied(final Activity p0, final String p1);
}
