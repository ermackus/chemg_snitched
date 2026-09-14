package com.otaliastudios.cameraview.metering;

import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import com.otaliastudios.cameraview.size.Size;
import android.graphics.RectF;
import android.graphics.PointF;
import java.util.List;

public class MeteringRegions
{
    private static final float BLUR_FACTOR_SIZE = 1.5f;
    static final float BLUR_FACTOR_WEIGHT = 0.1f;
    private static final float POINT_AREA = 0.05f;
    final List<MeteringRegion> mRegions;
    
    private MeteringRegions(final List<MeteringRegion> mRegions) {
        this.mRegions = mRegions;
    }
    
    private static RectF expand(final PointF pointF, float y, float n) {
        final float x = pointF.x;
        final float n2 = y / 2.0f;
        y = pointF.y;
        n /= 2.0f;
        return new RectF(x - n2, y - n, pointF.x + n2, pointF.y + n);
    }
    
    public static MeteringRegions fromArea(final Size size, final RectF rectF) {
        return fromArea(size, rectF, 1000);
    }
    
    public static MeteringRegions fromArea(final Size size, final RectF rectF, final int n) {
        return fromArea(size, rectF, n, false);
    }
    
    public static MeteringRegions fromArea(final Size size, final RectF rectF, final int n, final boolean b) {
        final ArrayList list = new ArrayList();
        final PointF pointF = new PointF(rectF.centerX(), rectF.centerY());
        final float width = rectF.width();
        final float height = rectF.height();
        ((List)list).add((Object)new MeteringRegion(rectF, n));
        if (b) {
            ((List)list).add((Object)new MeteringRegion(expand(pointF, width * 1.5f, height * 1.5f), Math.round(n * 0.1f)));
        }
        final ArrayList list2 = new ArrayList();
        final Iterator iterator = ((List)list).iterator();
        while (iterator.hasNext()) {
            ((List)list2).add((Object)((MeteringRegion)iterator.next()).clip(size));
        }
        return new MeteringRegions((List<MeteringRegion>)list2);
    }
    
    public static MeteringRegions fromPoint(final Size size, final PointF pointF) {
        return fromPoint(size, pointF, 1000);
    }
    
    public static MeteringRegions fromPoint(final Size size, final PointF pointF, final int n) {
        return fromArea(size, expand(pointF, size.getWidth() * 0.05f, size.getHeight() * 0.05f), n, true);
    }
    
    public <T> List<T> get(final int n, final MeteringTransform<T> meteringTransform) {
        final ArrayList list = new ArrayList();
        Collections.sort((List)this.mRegions);
        for (final MeteringRegion meteringRegion : this.mRegions) {
            ((List)list).add((Object)meteringTransform.transformMeteringRegion(meteringRegion.mRegion, meteringRegion.mWeight));
        }
        return (List<T>)((List)list).subList(0, Math.min(n, ((List)list).size()));
    }
    
    public MeteringRegions transform(final MeteringTransform meteringTransform) {
        final ArrayList list = new ArrayList();
        final Iterator iterator = this.mRegions.iterator();
        while (iterator.hasNext()) {
            ((List)list).add((Object)((MeteringRegion)iterator.next()).transform(meteringTransform));
        }
        return new MeteringRegions((List<MeteringRegion>)list);
    }
}
