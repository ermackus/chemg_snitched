package com.yalantis.ucrop;

import android.widget.ImageView;
import android.graphics.Bitmap;
import android.net.Uri;
import android.content.Context;

public interface UCropImageEngine
{
    void loadImage(final Context p0, final Uri p1, final int p2, final int p3, final OnCallbackListener<Bitmap> p4);
    
    void loadImage(final Context p0, final String p1, final ImageView p2);
    
    public interface OnCallbackListener<T>
    {
        void onCall(final T p0);
    }
}
