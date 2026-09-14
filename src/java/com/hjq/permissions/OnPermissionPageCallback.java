package com.hjq.permissions;

public interface OnPermissionPageCallback
{
    void onDenied();
    
    void onGranted();
}
