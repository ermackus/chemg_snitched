package com.hjq.permissions;

import java.util.List;

public interface OnPermissionCallback
{
    void onDenied(final List<String> p0, final boolean p1);
    
    void onGranted(final List<String> p0, final boolean p1);
}
