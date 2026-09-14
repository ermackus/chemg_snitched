package com.luck.picture.lib.interfaces;

import androidx.fragment.app.Fragment;

public interface OnPermissionsInterceptListener
{
    boolean hasPermissions(final Fragment p0, final String[] p1);
    
    void requestPermission(final Fragment p0, final String[] p1, final OnRequestPermissionListener p2);
}
