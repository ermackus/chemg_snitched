package com.otaliastudios.cameraview.engine.metering;

import android.graphics.RectF;
import com.otaliastudios.cameraview.engine.offset.Axis;
import com.otaliastudios.cameraview.engine.offset.Reference;
import com.otaliastudios.cameraview.size.AspectRatio;
import android.hardware.camera2.CaptureRequest;
import android.graphics.Rect;
import android.graphics.PointF;
import com.otaliastudios.cameraview.size.Size;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest$Builder;
import com.otaliastudios.cameraview.engine.offset.Angles;
import com.otaliastudios.cameraview.CameraLogger;
import android.hardware.camera2.params.MeteringRectangle;
import com.otaliastudios.cameraview.metering.MeteringTransform;

public class Camera2MeteringTransform implements MeteringTransform<MeteringRectangle>
{
    protected static final CameraLogger LOG;
    protected static final String TAG;
    private final Angles angles;
    private final CaptureRequest$Builder builder;
    private final CameraCharacteristics characteristics;
    private final boolean previewIsCropping;
    private final Size previewSize;
    private final Size previewStreamSize;
    
    static {
        LOG = CameraLogger.create(TAG = Camera2MeteringTransform.class.getSimpleName());
    }
    
    public Camera2MeteringTransform(final Angles angles, final Size previewSize, final Size previewStreamSize, final boolean previewIsCropping, final CameraCharacteristics characteristics, final CaptureRequest$Builder builder) {
        this.angles = angles;
        this.previewSize = previewSize;
        this.previewStreamSize = previewStreamSize;
        this.previewIsCropping = previewIsCropping;
        this.characteristics = characteristics;
        this.builder = builder;
    }
    
    private Size applyActiveArrayCoordinates(final Size size, final PointF pointF) {
        final Rect rect = (Rect)this.builder.get(CaptureRequest.SCALER_CROP_REGION);
        final float x = pointF.x;
        final float n = 0.0f;
        float n2;
        if (rect == null) {
            n2 = 0.0f;
        }
        else {
            n2 = (float)rect.left;
        }
        pointF.x = x + n2;
        final float y = pointF.y;
        float n3;
        if (rect == null) {
            n3 = n;
        }
        else {
            n3 = (float)rect.top;
        }
        pointF.y = y + n3;
        Rect rect2;
        if ((rect2 = (Rect)this.characteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE)) == null) {
            rect2 = new Rect(0, 0, size.getWidth(), size.getHeight());
        }
        return new Size(rect2.width(), rect2.height());
    }
    
    private Size applyCropRegionCoordinates(final Size size, final PointF pointF) {
        final Rect rect = (Rect)this.builder.get(CaptureRequest.SCALER_CROP_REGION);
        int n;
        if (rect == null) {
            n = size.getWidth();
        }
        else {
            n = rect.width();
        }
        int n2;
        if (rect == null) {
            n2 = size.getHeight();
        }
        else {
            n2 = rect.height();
        }
        pointF.x += (n - size.getWidth()) / 2.0f;
        pointF.y += (n2 - size.getHeight()) / 2.0f;
        return new Size(n, n2);
    }
    
    private Size applyPreviewCropping(final Size size, final PointF pointF) {
        final Size previewStreamSize = this.previewStreamSize;
        final int width = size.getWidth();
        final int height = size.getHeight();
        final AspectRatio of = AspectRatio.of(previewStreamSize);
        final AspectRatio of2 = AspectRatio.of(size);
        int round = width;
        int round2 = height;
        if (this.previewIsCropping) {
            if (of.toFloat() > of2.toFloat()) {
                final float n = of.toFloat() / of2.toFloat();
                pointF.x += size.getWidth() * (n - 1.0f) / 2.0f;
                round = Math.round(size.getWidth() * n);
                round2 = height;
            }
            else {
                final float n2 = of2.toFloat() / of.toFloat();
                pointF.y += size.getHeight() * (n2 - 1.0f) / 2.0f;
                round2 = Math.round(size.getHeight() * n2);
                round = width;
            }
        }
        return new Size(round, round2);
    }
    
    private Size applyPreviewScale(final Size size, final PointF pointF) {
        final Size previewStreamSize = this.previewStreamSize;
        pointF.x *= previewStreamSize.getWidth() / (float)size.getWidth();
        pointF.y *= previewStreamSize.getHeight() / (float)size.getHeight();
        return previewStreamSize;
    }
    
    private Size applyPreviewToSensorRotation(final Size size, final PointF pointF) {
        final int offset = this.angles.offset(Reference.SENSOR, Reference.VIEW, Axis.ABSOLUTE);
        final boolean b = offset % 180 != 0;
        final float x = pointF.x;
        final float y = pointF.y;
        if (offset == 0) {
            pointF.x = x;
            pointF.y = y;
        }
        else if (offset == 90) {
            pointF.x = y;
            pointF.y = size.getWidth() - x;
        }
        else if (offset == 180) {
            pointF.x = size.getWidth() - x;
            pointF.y = size.getHeight() - y;
        }
        else {
            if (offset != 270) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Unexpected angle ");
                sb.append(offset);
                throw new IllegalStateException(sb.toString());
            }
            pointF.x = size.getHeight() - y;
            pointF.y = x;
        }
        Size flip = size;
        if (b) {
            flip = size.flip();
        }
        return flip;
    }
    
    public PointF transformMeteringPoint(final PointF pointF) {
        final PointF pointF2 = new PointF(pointF.x, pointF.y);
        final Size applyActiveArrayCoordinates = this.applyActiveArrayCoordinates(this.applyCropRegionCoordinates(this.applyPreviewToSensorRotation(this.applyPreviewScale(this.applyPreviewCropping(this.previewSize, pointF2), pointF2), pointF2), pointF2), pointF2);
        Camera2MeteringTransform.LOG.i(new Object[] { "input:", pointF, "output (before clipping):", pointF2 });
        if (pointF2.x < 0.0f) {
            pointF2.x = 0.0f;
        }
        if (pointF2.y < 0.0f) {
            pointF2.y = 0.0f;
        }
        if (pointF2.x > applyActiveArrayCoordinates.getWidth()) {
            pointF2.x = (float)applyActiveArrayCoordinates.getWidth();
        }
        if (pointF2.y > applyActiveArrayCoordinates.getHeight()) {
            pointF2.y = (float)applyActiveArrayCoordinates.getHeight();
        }
        Camera2MeteringTransform.LOG.i(new Object[] { "input:", pointF, "output (after clipping):", pointF2 });
        return pointF2;
    }
    
    public MeteringRectangle transformMeteringRegion(final RectF rectF, final int n) {
        final Rect rect = new Rect();
        rectF.round(rect);
        return new MeteringRectangle(rect, n);
    }
}
