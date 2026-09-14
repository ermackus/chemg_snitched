package com.otaliastudios.cameraview.markers;

import android.graphics.PointF;

public interface AutoFocusMarker extends Marker
{
    void onAutoFocusEnd(final AutoFocusTrigger p0, final boolean p1, final PointF p2);
    
    void onAutoFocusStart(final AutoFocusTrigger p0, final PointF p1);
}
