package com.otaliastudios.opengl.geometry;

import kotlin.jvm.internal.Intrinsics;
import android.graphics.PointF;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.Lazy;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\n\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0006B%\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0000H\u0016J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u001b\u0010\u0012\u001a\u00020\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u000e¨\u0006\u001d" }, d2 = { "Lcom/otaliastudios/opengl/geometry/SegmentF;", "", "i", "Landroid/graphics/PointF;", "Lcom/otaliastudios/opengl/geometry/PointF;", "j", "(Landroid/graphics/PointF;Landroid/graphics/PointF;)V", "ix", "", "iy", "jx", "jy", "(FFFF)V", "getIx", "()F", "getIy", "getJx", "getJy", "length", "getLength", "length$delegate", "Lkotlin/Lazy;", "intersects", "", "other", "orientation", "", "x", "y", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class SegmentF
{
    private final float ix;
    private final float iy;
    private final float jx;
    private final float jy;
    private final Lazy length$delegate;
    
    public SegmentF(final float ix, final float iy, final float jx, final float jy) {
        this.ix = ix;
        this.iy = iy;
        this.jx = jx;
        this.jy = jy;
        this.length$delegate = LazyKt.lazy((Function0)new SegmentF$length$2(this));
    }
    
    public SegmentF(final PointF pointF, final PointF pointF2) {
        Intrinsics.checkNotNullParameter((Object)pointF, "i");
        Intrinsics.checkNotNullParameter((Object)pointF2, "j");
        this(pointF.x, pointF.y, pointF2.x, pointF2.y);
    }
    
    public final float getIx() {
        return this.ix;
    }
    
    public final float getIy() {
        return this.iy;
    }
    
    public final float getJx() {
        return this.jx;
    }
    
    public final float getJy() {
        return this.jy;
    }
    
    public final float getLength() {
        return ((Number)this.length$delegate.getValue()).floatValue();
    }
    
    public boolean intersects(final SegmentF segmentF) {
        Intrinsics.checkNotNullParameter((Object)segmentF, "other");
        final float min = Math.min(this.ix, this.jx);
        final float max = Math.max(this.ix, this.jx);
        final float min2 = Math.min(segmentF.ix, segmentF.jx);
        final float max2 = Math.max(segmentF.ix, segmentF.jx);
        if (min > max2) {
            return false;
        }
        final float n = fcmpg(max, min2);
        if (n < 0) {
            return false;
        }
        final float min3 = Math.min(this.iy, this.jy);
        final float max3 = Math.max(this.iy, this.jy);
        final float min4 = Math.min(segmentF.iy, segmentF.jy);
        final float max4 = Math.max(segmentF.iy, segmentF.jy);
        if (min3 > max4) {
            return false;
        }
        final float n2 = fcmpg(max3, min4);
        if (n2 < 0) {
            return false;
        }
        final int orientation = this.orientation(segmentF.ix, segmentF.iy);
        final int orientation2 = this.orientation(segmentF.jx, segmentF.jy);
        if (orientation > 0 && orientation2 > 0) {
            return false;
        }
        if (orientation < 0 && orientation2 < 0) {
            return false;
        }
        final int orientation3 = segmentF.orientation(this.ix, this.iy);
        final int orientation4 = segmentF.orientation(this.jx, this.jy);
        if (orientation3 > 0 && orientation4 > 0) {
            return false;
        }
        if (orientation3 < 0 && orientation4 < 0) {
            return false;
        }
        if (orientation == 0 && orientation2 == 0 && orientation3 == 0 && orientation4 == 0) {
            final float n3 = fcmpg(min, max2);
            return (n3 != 0 || min3 != max4) && (n3 != 0 || n2 != 0) && (n != 0 || min3 != max4) && (n != 0 || n2 != 0);
        }
        return (this.ix != segmentF.ix || this.iy != segmentF.iy) && (this.jx != segmentF.jx || this.jy != segmentF.jy) && (this.ix != segmentF.jx || this.iy != segmentF.jy) && (this.jx != segmentF.ix || this.jy != segmentF.iy);
    }
    
    public final int orientation(final float n, final float n2) {
        final float jx = this.jx;
        final float ix = this.ix;
        final float jy = this.jy;
        return (int)Math.signum((jx - ix) * (n2 - jy) - (jy - this.iy) * (n - jx));
    }
}
