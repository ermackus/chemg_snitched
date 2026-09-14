package com.otaliastudios.opengl.draw;

import com.otaliastudios.opengl.geometry.SegmentF;
import java.util.Comparator;
import kotlin.collections.CollectionsKt;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import android.graphics.PointF;
import android.opengl.GLES20;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.core.Egloo;
import java.util.Iterator;
import kotlin.Unit;
import com.otaliastudios.opengl.types.BuffersKt;
import java.nio.Buffer;
import java.util.ArrayList;
import com.otaliastudios.opengl.geometry.IndexedSegmentF;
import java.util.List;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\u0018\u0010\u0014\u001a\u00020\u000e2\u0010\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0016j\u0002`\u00170\u0010J\"\u0010\u0014\u001a\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00102\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0010R\u001e\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b" }, d2 = { "Lcom/otaliastudios/opengl/draw/Gl2dMesh;", "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "()V", "vertexArray", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "getVertexArray", "()Ljava/nio/FloatBuffer;", "setVertexArray", "(Ljava/nio/FloatBuffer;)V", "vertexIndices", "Ljava/nio/ByteBuffer;", "Lcom/otaliastudios/opengl/types/ByteBuffer;", "computeIndicesFromIndexedSegments", "", "segments", "", "Lcom/otaliastudios/opengl/geometry/IndexedSegmentF;", "draw", "release", "setPoints", "points", "Landroid/graphics/PointF;", "Lcom/otaliastudios/opengl/geometry/PointF;", "x", "", "y", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class Gl2dMesh extends Gl2dDrawable
{
    private FloatBuffer vertexArray;
    private ByteBuffer vertexIndices;
    
    public Gl2dMesh() {
        this.vertexArray = BuffersJvmKt.floatBuffer(6);
    }
    
    private final void computeIndicesFromIndexedSegments(final List<IndexedSegmentF> list) {
        final List list2 = (List)new ArrayList();
        final int n = list.size() - 1;
        if (n >= 0) {
            int n2 = 0;
            while (true) {
                final int n3 = n2 + 1;
                final IndexedSegmentF indexedSegmentF = (IndexedSegmentF)list.get(n2);
                final int size = list.size();
                if (n3 < size) {
                    int n4 = n3;
                    int n5 = 0;
                    int n6 = 0;
                    while (true) {
                        final int n7 = n4 + 1;
                        if (n5 != 0 && n6 != 0) {
                            break;
                        }
                        final IndexedSegmentF indexedSegmentF2 = (IndexedSegmentF)list.get(n4);
                        int n11 = 0;
                        int n12 = 0;
                        Label_0434: {
                            int n8;
                            float n9;
                            float n10;
                            if (indexedSegmentF.hasIndex(indexedSegmentF2.getI())) {
                                n8 = indexedSegmentF2.getJ();
                                n9 = indexedSegmentF2.getJx();
                                n10 = indexedSegmentF2.getJy();
                            }
                            else {
                                n11 = n5;
                                n12 = n6;
                                if (!indexedSegmentF.hasIndex(indexedSegmentF2.getJ())) {
                                    break Label_0434;
                                }
                                n8 = indexedSegmentF2.getI();
                                n9 = indexedSegmentF2.getIx();
                                n10 = indexedSegmentF2.getIy();
                            }
                            final int orientation = indexedSegmentF.orientation(n9, n10);
                            if (orientation == 0) {
                                n11 = n5;
                                n12 = n6;
                            }
                            else if (orientation > 0 && n5 != 0) {
                                n11 = n5;
                                n12 = n6;
                            }
                            else if (orientation < 0 && n6 != 0) {
                                n11 = n5;
                                n12 = n6;
                            }
                            else {
                                final int size2 = list.size();
                                n11 = n5;
                                n12 = n6;
                                if (n7 < size2) {
                                    int n13 = n7;
                                    while (true) {
                                        final int n14 = n13 + 1;
                                        final IndexedSegmentF indexedSegmentF3 = (IndexedSegmentF)list.get(n13);
                                        if (indexedSegmentF3.hasIndex(n8) && (indexedSegmentF3.hasIndex(indexedSegmentF.getI()) || indexedSegmentF3.hasIndex(indexedSegmentF.getJ()))) {
                                            list2.add((Object)(byte)indexedSegmentF.getI());
                                            list2.add((Object)(byte)indexedSegmentF.getJ());
                                            list2.add((Object)(byte)n8);
                                            if (orientation > 0) {
                                                n5 = 1;
                                            }
                                            n11 = n5;
                                            n12 = n6;
                                            if (orientation < 0) {
                                                n12 = 1;
                                                n11 = n5;
                                                break;
                                            }
                                            break;
                                        }
                                        else {
                                            if (n14 >= size2) {
                                                n11 = n5;
                                                n12 = n6;
                                                break;
                                            }
                                            n13 = n14;
                                        }
                                    }
                                }
                            }
                        }
                        if (n7 >= size) {
                            break;
                        }
                        n4 = n7;
                        n5 = n11;
                        n6 = n12;
                    }
                }
                if (n3 > n) {
                    break;
                }
                n2 = n3;
            }
        }
        final ByteBuffer vertexIndices = this.vertexIndices;
        if (vertexIndices != null) {
            BuffersKt.dispose((Buffer)vertexIndices);
        }
        final ByteBuffer byteBuffer = BuffersJvmKt.byteBuffer(list2.size());
        final Iterator iterator = ((Iterable)list2).iterator();
        while (iterator.hasNext()) {
            byteBuffer.put(((Number)iterator.next()).byteValue());
        }
        byteBuffer.clear();
        final Unit instance = Unit.INSTANCE;
        this.vertexIndices = byteBuffer;
    }
    
    public void draw() {
        final ByteBuffer vertexIndices = this.vertexIndices;
        if (vertexIndices != null) {
            Egloo.checkGlError("glDrawElements start");
            GLES20.glDrawElements(GlKt.getGL_TRIANGLES(), vertexIndices.limit(), GlKt.getGL_UNSIGNED_BYTE(), (Buffer)vertexIndices);
            Egloo.checkGlError("glDrawElements end");
        }
    }
    
    public FloatBuffer getVertexArray() {
        return this.vertexArray;
    }
    
    public void release() {
        super.release();
        final ByteBuffer vertexIndices = this.vertexIndices;
        if (vertexIndices != null) {
            BuffersKt.dispose((Buffer)vertexIndices);
        }
    }
    
    public final void setPoints(final List<? extends PointF> list) {
        Intrinsics.checkNotNullParameter((Object)list, "points");
        final Iterable iterable = (Iterable)list;
        final Collection collection = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        final Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            collection.add((Object)((PointF)iterator.next()).x);
        }
        final List list2 = (List)collection;
        final Collection collection2 = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        final Iterator iterator2 = iterable.iterator();
        while (iterator2.hasNext()) {
            collection2.add((Object)((PointF)iterator2.next()).y);
        }
        this.setPoints((List<Float>)list2, (List<Float>)collection2);
    }
    
    public final void setPoints(final List<Float> list, final List<Float> list2) {
        Intrinsics.checkNotNullParameter((Object)list, "x");
        Intrinsics.checkNotNullParameter((Object)list2, "y");
        if (list.size() == list2.size()) {
            final int size = list.size();
            final int n = size * 2;
            if (this.getVertexArray().capacity() < n) {
                BuffersKt.dispose((Buffer)this.getVertexArray());
                this.setVertexArray(BuffersJvmKt.floatBuffer(n));
            }
            else {
                this.getVertexArray().clear();
            }
            final List list3 = (List)new ArrayList();
            if (size > 0) {
                int n2 = 0;
                while (true) {
                    final int n3 = n2 + 1;
                    final float floatValue = ((Number)list.get(n2)).floatValue();
                    final float floatValue2 = ((Number)list2.get(n2)).floatValue();
                    this.getVertexArray().put(floatValue);
                    this.getVertexArray().put(floatValue2);
                    if (n3 < size) {
                        int n4 = n3;
                        while (true) {
                            final int n5 = n4 + 1;
                            list3.add((Object)new IndexedSegmentF(n2, n4, floatValue, floatValue2, ((Number)list.get(n4)).floatValue(), ((Number)list2.get(n4)).floatValue()));
                            if (n5 >= size) {
                                break;
                            }
                            n4 = n5;
                        }
                    }
                    if (n3 >= size) {
                        break;
                    }
                    n2 = n3;
                }
            }
            this.getVertexArray().flip();
            this.notifyVertexArrayChange();
            if (list3.size() > 1) {
                CollectionsKt.sortWith(list3, (Comparator)new Gl2dMesh$setPoints$$inlined$sortBy$1());
            }
            final List list4 = (List)new ArrayList();
            for (final IndexedSegmentF indexedSegmentF : list3) {
                final Iterable iterable = (Iterable)list4;
                boolean b = false;
                Label_0413: {
                    if (!(iterable instanceof Collection) || !((Collection)iterable).isEmpty()) {
                        final Iterator iterator2 = iterable.iterator();
                        while (iterator2.hasNext()) {
                            if (((IndexedSegmentF)iterator2.next()).intersects((SegmentF)indexedSegmentF)) {
                                b = false;
                                break Label_0413;
                            }
                        }
                    }
                    b = true;
                }
                if (b) {
                    list4.add((Object)indexedSegmentF);
                }
            }
            this.computeIndicesFromIndexedSegments((List<IndexedSegmentF>)list4);
            return;
        }
        throw new IllegalArgumentException("x.size != y.size");
    }
    
    public void setVertexArray(final FloatBuffer vertexArray) {
        Intrinsics.checkNotNullParameter((Object)vertexArray, "<set-?>");
        this.vertexArray = vertexArray;
    }
}
