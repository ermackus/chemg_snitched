package com.yalantis.ucrop.callback;

import android.net.Uri;

public interface BitmapCropCallback
{
    void onBitmapCropped(final Uri p0, final int p1, final int p2, final int p3, final int p4);
    
    void onCropFailure(final Throwable p0);
}
