package com.luck.picture.lib.interfaces;

import androidx.fragment.app.Fragment;

public interface OnPermissionDescriptionListener
{
    void onDismiss(final Fragment p0);
    
    void onPermissionDescription(final Fragment p0, final String[] p1);
}
