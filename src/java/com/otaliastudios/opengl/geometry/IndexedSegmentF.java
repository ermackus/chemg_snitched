package com.otaliastudios.opengl.geometry;

import kotlin.jvm.internal.Intrinsics;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B5\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\u0004\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0001H\u0016R\u0011\u0010\u0002\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0015" }, d2 = { "Lcom/otaliastudios/opengl/geometry/IndexedSegmentF;", "Lcom/otaliastudios/opengl/geometry/SegmentF;", "i", "Lcom/otaliastudios/opengl/geometry/IndexedPointF;", "j", "(Lcom/otaliastudios/opengl/geometry/IndexedPointF;Lcom/otaliastudios/opengl/geometry/IndexedPointF;)V", "", "ix", "", "iy", "jx", "jy", "(IIFFFF)V", "getI", "()I", "getJ", "hasIndex", "", "index", "intersects", "other", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class IndexedSegmentF extends SegmentF
{
    private final int i;
    private final int j;
    
    public IndexedSegmentF(final int i, final int j, final float n, final float n2, final float n3, final float n4) {
        super(n, n2, n3, n4);
        this.i = i;
        this.j = j;
    }
    
    public IndexedSegmentF(final IndexedPointF indexedPointF, final IndexedPointF indexedPointF2) {
        Intrinsics.checkNotNullParameter((Object)indexedPointF, "i");
        Intrinsics.checkNotNullParameter((Object)indexedPointF2, "j");
        this(indexedPointF.getIndex(), indexedPointF2.getIndex(), indexedPointF.x, indexedPointF.y, indexedPointF2.x, indexedPointF2.y);
    }
    
    public final int getI() {
        return this.i;
    }
    
    public final int getJ() {
        return this.j;
    }
    
    public final boolean hasIndex(final int n) {
        return n == this.i || n == this.j;
    }
    
    public boolean intersects(final SegmentF segmentF) {
        Intrinsics.checkNotNullParameter((Object)segmentF, "other");
        if (segmentF instanceof IndexedSegmentF) {
            final IndexedSegmentF indexedSegmentF = (IndexedSegmentF)segmentF;
            if (indexedSegmentF.hasIndex(this.i) && indexedSegmentF.hasIndex(this.j)) {
                return true;
            }
            if (indexedSegmentF.hasIndex(this.i) || indexedSegmentF.hasIndex(this.j)) {
                return false;
            }
        }
        return super.intersects(segmentF);
    }
}
