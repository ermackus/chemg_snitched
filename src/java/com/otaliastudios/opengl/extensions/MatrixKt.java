package com.otaliastudios.opengl.extensions;

import com.otaliastudios.opengl.internal.MiscKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0010\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0002*\u00020\u0002\u001a*\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007\u001a\u0012\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\r\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007\u001a(\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007\u001a\u0012\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007\u001a\u0012\u0010\u0010\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007\u001a\u0012\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007\u001a(\u0010\u0012\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007\u001a\u0012\u0010\u0013\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0007\u001a\u0012\u0010\u0015\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0007\u001a\u0012\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0007¨\u0006\u0017" }, d2 = { "checkSize", "", "", "clear", "makeIdentity", "rotate", "angle", "", "x", "y", "z", "rotateX", "rotateY", "rotateZ", "scale", "scaleX", "scaleY", "scaleZ", "translate", "translateX", "translation", "translateY", "translateZ", "library_release" }, k = 2, mv = { 1, 5, 1 }, xi = 48)
public final class MatrixKt
{
    private static final void checkSize(final float[] array) {
        if (array.length == 16) {
            return;
        }
        throw new RuntimeException("Need a 16 values matrix.");
    }
    
    public static final float[] clear(final float[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        return makeIdentity(array);
    }
    
    public static final float[] makeIdentity(final float[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        checkSize(array);
        MiscKt.matrixMakeIdentity(array);
        return array;
    }
    
    public static final float[] rotate(final float[] array, final float n, final float n2, final float n3, final float n4) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        checkSize(array);
        MiscKt.matrixRotate(array, n, n2, n3, n4);
        return array;
    }
    
    public static final float[] rotateX(final float[] array, final float n) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        return rotate(array, n, 1.0f, 0.0f, 0.0f);
    }
    
    public static final float[] rotateY(final float[] array, final float n) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        return rotate(array, n, 0.0f, 1.0f, 0.0f);
    }
    
    public static final float[] rotateZ(final float[] array, final float n) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        return rotate(array, n, 0.0f, 0.0f, 1.0f);
    }
    
    public static final float[] scale(final float[] array, final float n, final float n2, final float n3) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        checkSize(array);
        MiscKt.matrixScale(array, n, n2, n3);
        return array;
    }
    
    public static /* synthetic */ float[] scale$default(final float[] array, float n, float n2, float n3, final int n4, final Object o) {
        if ((n4 & 0x1) != 0x0) {
            n = 1.0f;
        }
        if ((n4 & 0x2) != 0x0) {
            n2 = 1.0f;
        }
        if ((n4 & 0x4) != 0x0) {
            n3 = 1.0f;
        }
        return scale(array, n, n2, n3);
    }
    
    public static final float[] scaleX(final float[] array, final float n) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        return scale$default(array, n, 0.0f, 0.0f, 6, null);
    }
    
    public static final float[] scaleY(final float[] array, final float n) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        return scale$default(array, 0.0f, n, 0.0f, 5, null);
    }
    
    public static final float[] scaleZ(final float[] array, final float n) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        return scale$default(array, 0.0f, 0.0f, n, 3, null);
    }
    
    public static final float[] translate(final float[] array, final float n, final float n2, final float n3) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        checkSize(array);
        MiscKt.matrixTranslate(array, n, n2, n3);
        return array;
    }
    
    public static /* synthetic */ float[] translate$default(final float[] array, float n, float n2, float n3, final int n4, final Object o) {
        if ((n4 & 0x1) != 0x0) {
            n = 0.0f;
        }
        if ((n4 & 0x2) != 0x0) {
            n2 = 0.0f;
        }
        if ((n4 & 0x4) != 0x0) {
            n3 = 0.0f;
        }
        return translate(array, n, n2, n3);
    }
    
    public static final float[] translateX(final float[] array, final float n) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        return translate$default(array, n, 0.0f, 0.0f, 6, null);
    }
    
    public static final float[] translateY(final float[] array, final float n) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        return translate$default(array, 0.0f, n, 0.0f, 5, null);
    }
    
    public static final float[] translateZ(final float[] array, final float n) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        return translate$default(array, 0.0f, 0.0f, n, 3, null);
    }
}
