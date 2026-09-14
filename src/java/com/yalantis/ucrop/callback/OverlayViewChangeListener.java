package com.yalantis.ucrop.callback;

import android.graphics.RectF;

public interface OverlayViewChangeListener
{
    void onCropRectUpdated(final RectF p0);
    
    void postTranslate(final float p0, final float p1);
}
