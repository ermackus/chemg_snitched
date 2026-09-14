package com.otaliastudios.cameraview.engine.metering;

import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PointF;
import com.otaliastudios.cameraview.engine.offset.Axis;
import com.otaliastudios.cameraview.engine.offset.Reference;
import com.otaliastudios.cameraview.engine.offset.Angles;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.CameraLogger;
import android.hardware.Camera$Area;
import com.otaliastudios.cameraview.metering.MeteringTransform;

public class Camera1MeteringTransform implements MeteringTransform<Camera$Area>
{
    protected static final CameraLogger LOG;
    protected static final String TAG;
    private final int displayToSensor;
    private final Size previewSize;
    
    static {
        LOG = CameraLogger.create(TAG = Camera1MeteringTransform.class.getSimpleName());
    }
    
    public Camera1MeteringTransform(final Angles angles, final Size previewSize) {
        this.displayToSensor = -angles.offset(Reference.SENSOR, Reference.VIEW, Axis.ABSOLUTE);
        this.previewSize = previewSize;
    }
    
    public PointF transformMeteringPoint(PointF pointF) {
        final PointF pointF2 = new PointF();
        pointF2.x = pointF.x / this.previewSize.getWidth() * 2000.0f - 1000.0f;
        pointF2.y = pointF.y / this.previewSize.getHeight() * 2000.0f - 1000.0f;
        pointF = new PointF();
        final double n = this.displayToSensor * 3.141592653589793 / 180.0;
        pointF.x = (float)(pointF2.x * Math.cos(n) - pointF2.y * Math.sin(n));
        pointF.y = (float)(pointF2.x * Math.sin(n) + pointF2.y * Math.cos(n));
        Camera1MeteringTransform.LOG.i(new Object[] { "scaled:", pointF2, "rotated:", pointF });
        return pointF;
    }
    
    public Camera$Area transformMeteringRegion(final RectF rectF, final int n) {
        final Rect rect = new Rect();
        rectF.round(rect);
        return new Camera$Area(rect, n);
    }
}
