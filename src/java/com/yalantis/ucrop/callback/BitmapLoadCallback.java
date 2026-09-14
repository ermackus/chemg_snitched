package com.yalantis.ucrop.callback;

import android.net.Uri;
import com.yalantis.ucrop.model.ExifInfo;
import android.graphics.Bitmap;

public interface BitmapLoadCallback
{
    void onBitmapLoaded(final Bitmap p0, final ExifInfo p1, final Uri p2, final Uri p3);
    
    void onFailure(final Exception p0);
}
