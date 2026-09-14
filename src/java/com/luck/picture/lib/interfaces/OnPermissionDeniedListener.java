package com.luck.picture.lib.interfaces;

import androidx.fragment.app.Fragment;

public interface OnPermissionDeniedListener
{
    void onDenied(final Fragment p0, final String[] p1, final int p2, final OnCallbackListener<Boolean> p3);
}
