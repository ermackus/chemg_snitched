package com.hjq.permissions;

import java.util.List;
import android.app.Activity;

public interface IPermissionInterceptor
{
    void deniedPermissionRequest(final Activity p0, final List<String> p1, final List<String> p2, final boolean p3, final OnPermissionCallback p4);
    
    void finishPermissionRequest(final Activity p0, final List<String> p1, final boolean p2, final OnPermissionCallback p3);
    
    void grantedPermissionRequest(final Activity p0, final List<String> p1, final List<String> p2, final boolean p3, final OnPermissionCallback p4);
    
    void launchPermissionRequest(final Activity p0, final List<String> p1, final OnPermissionCallback p2);
}
