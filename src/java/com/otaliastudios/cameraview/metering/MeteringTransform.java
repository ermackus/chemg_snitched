package com.otaliastudios.cameraview.metering;

import android.graphics.RectF;
import android.graphics.PointF;

public interface MeteringTransform<T>
{
    PointF transformMeteringPoint(final PointF p0);
    
    T transformMeteringRegion(final RectF p0, final int p1);
}
