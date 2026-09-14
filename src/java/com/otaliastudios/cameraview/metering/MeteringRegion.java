package com.otaliastudios.cameraview.metering;

import com.otaliastudios.cameraview.size.Size;
import android.graphics.PointF;
import android.graphics.RectF;

class MeteringRegion implements Comparable<MeteringRegion>
{
    static final int MAX_WEIGHT = 1000;
    final RectF mRegion;
    final int mWeight;
    
    MeteringRegion(final RectF mRegion, final int mWeight) {
        this.mRegion = mRegion;
        this.mWeight = mWeight;
    }
    
    private void updateRect(final RectF rectF, final PointF pointF) {
        rectF.left = Math.min(rectF.left, pointF.x);
        rectF.top = Math.min(rectF.top, pointF.y);
        rectF.right = Math.max(rectF.right, pointF.x);
        rectF.bottom = Math.max(rectF.bottom, pointF.y);
    }
    
    MeteringRegion clip(final RectF rectF) {
        final RectF rectF2 = new RectF();
        rectF2.set(Math.max(rectF.left, this.mRegion.left), Math.max(rectF.top, this.mRegion.top), Math.min(rectF.right, this.mRegion.right), Math.min(rectF.bottom, this.mRegion.bottom));
        return new MeteringRegion(rectF2, this.mWeight);
    }
    
    MeteringRegion clip(final Size size) {
        return this.clip(new RectF(0.0f, 0.0f, (float)size.getWidth(), (float)size.getHeight()));
    }
    
    public int compareTo(final MeteringRegion meteringRegion) {
        return -Integer.valueOf(this.mWeight).compareTo(Integer.valueOf(meteringRegion.mWeight));
    }
    
    MeteringRegion transform(final MeteringTransform meteringTransform) {
        final RectF rectF = new RectF(Float.MAX_VALUE, Float.MAX_VALUE, -3.4028235E38f, -3.4028235E38f);
        final PointF pointF = new PointF();
        pointF.set(this.mRegion.left, this.mRegion.top);
        final PointF transformMeteringPoint = meteringTransform.transformMeteringPoint(pointF);
        this.updateRect(rectF, transformMeteringPoint);
        transformMeteringPoint.set(this.mRegion.right, this.mRegion.top);
        final PointF transformMeteringPoint2 = meteringTransform.transformMeteringPoint(transformMeteringPoint);
        this.updateRect(rectF, transformMeteringPoint2);
        transformMeteringPoint2.set(this.mRegion.right, this.mRegion.bottom);
        final PointF transformMeteringPoint3 = meteringTransform.transformMeteringPoint(transformMeteringPoint2);
        this.updateRect(rectF, transformMeteringPoint3);
        transformMeteringPoint3.set(this.mRegion.left, this.mRegion.bottom);
        this.updateRect(rectF, meteringTransform.transformMeteringPoint(transformMeteringPoint3));
        return new MeteringRegion(rectF, this.mWeight);
    }
}
