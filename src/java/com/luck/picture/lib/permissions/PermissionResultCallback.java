package com.luck.picture.lib.permissions;

public interface PermissionResultCallback
{
    void onDenied();
    
    void onGranted();
}
