package com.otaliastudios.opengl.internal;

import android.opengl.Matrix;
import android.util.Log;
import kotlin.jvm.internal.Intrinsics;
import android.opengl.GLU;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000(\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0006\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0080\b\u001a\u0019\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0080\b\u001a\u0019\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0080\b\u001a\u0019\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0080\b\u001a\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0000\u001a\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0000\u001a \u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rH\u0000\u001a0\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0000\u001a(\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0000\u001a(\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0000¨\u0006\u001c" }, d2 = { "gluErrorString", "", "value", "", "intToHexString", "loge", "", "tag", "message", "logi", "logv", "logw", "matrixClone", "", "matrix", "matrixMakeIdentity", "matrixMultiply", "result", "left", "right", "matrixRotate", "angle", "", "x", "y", "z", "matrixScale", "matrixTranslate", "library_release" }, k = 2, mv = { 1, 5, 1 }, xi = 48)
public final class MiscKt
{
    public static final String gluErrorString(final int n) {
        final String gluErrorString = GLU.gluErrorString(n);
        Intrinsics.checkNotNullExpressionValue((Object)gluErrorString, "gluErrorString(value)");
        return gluErrorString;
    }
    
    public static final String intToHexString(final int n) {
        final String hexString = Integer.toHexString(n);
        Intrinsics.checkNotNullExpressionValue((Object)hexString, "toHexString(value)");
        return hexString;
    }
    
    public static final void loge(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "tag");
        Intrinsics.checkNotNullParameter((Object)s2, "message");
        Log.e(s, s2);
    }
    
    public static final void logi(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "tag");
        Intrinsics.checkNotNullParameter((Object)s2, "message");
        Log.i(s, s2);
    }
    
    public static final void logv(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "tag");
        Intrinsics.checkNotNullParameter((Object)s2, "message");
        Log.v(s, s2);
    }
    
    public static final void logw(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "tag");
        Intrinsics.checkNotNullParameter((Object)s2, "message");
        Log.w(s, s2);
    }
    
    public static final float[] matrixClone(final float[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "matrix");
        return array.clone();
    }
    
    public static final void matrixMakeIdentity(final float[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "matrix");
        Matrix.setIdentityM(array, 0);
    }
    
    public static final void matrixMultiply(final float[] array, final float[] array2, final float[] array3) {
        Intrinsics.checkNotNullParameter((Object)array, "result");
        Intrinsics.checkNotNullParameter((Object)array2, "left");
        Intrinsics.checkNotNullParameter((Object)array3, "right");
        Matrix.multiplyMM(array, 0, array2, 0, array3, 0);
    }
    
    public static final void matrixRotate(final float[] array, final float n, final float n2, final float n3, final float n4) {
        Intrinsics.checkNotNullParameter((Object)array, "matrix");
        Matrix.rotateM(array, 0, n, n2, n3, n4);
    }
    
    public static final void matrixScale(final float[] array, final float n, final float n2, final float n3) {
        Intrinsics.checkNotNullParameter((Object)array, "matrix");
        Matrix.scaleM(array, 0, n, n2, n3);
    }
    
    public static final void matrixTranslate(final float[] array, final float n, final float n2, final float n3) {
        Intrinsics.checkNotNullParameter((Object)array, "matrix");
        Matrix.translateM(array, 0, n, n2, n3);
    }
}
