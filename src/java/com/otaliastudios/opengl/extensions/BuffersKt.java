package com.otaliastudios.opengl.extensions;

import java.nio.ShortBuffer;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ReplaceWith;
import kotlin.Deprecated;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\n\u0010\u0003\u001a\u00020\u0004\"\u00020\u0005\u001a\u0014\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0016\u0010\b\u001a\u00060\tj\u0002`\n2\n\u0010\u0003\u001a\u00020\u000b\"\u00020\f\u001a\u0014\u0010\b\u001a\u00060\tj\u0002`\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0016\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\u0010\u0003\u001a\u00020\u0010\"\u00020\u0007\u001a\u0016\u0010\u0011\u001a\u00060\u0012j\u0002`\u00132\n\u0010\u0003\u001a\u00020\u0014\"\u00020\u0015\u001a\u000e\u0010\u0016\u001a\u00060\u0001j\u0002`\u0002*\u00020\u0004\u001a\u000e\u0010\u0016\u001a\u00060\tj\u0002`\n*\u00020\u000b\u001a\u000e\u0010\u0016\u001a\u00060\u000ej\u0002`\u000f*\u00020\u0010\u001a\u000e\u0010\u0016\u001a\u00060\u0012j\u0002`\u0013*\u00020\u0014¨\u0006\u0017" }, d2 = { "byteBufferOf", "Ljava/nio/ByteBuffer;", "Lcom/otaliastudios/opengl/types/ByteBuffer;", "elements", "", "", "size", "", "floatBufferOf", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "", "", "intBufferOf", "Ljava/nio/IntBuffer;", "Lcom/otaliastudios/opengl/types/IntBuffer;", "", "shortBufferOf", "Ljava/nio/ShortBuffer;", "Lcom/otaliastudios/opengl/types/ShortBuffer;", "", "", "toBuffer", "library_release" }, k = 2, mv = { 1, 5, 1 }, xi = 48)
public final class BuffersKt
{
    @Deprecated(message = "Do not use this.", replaceWith = @ReplaceWith(expression = "ByteBuffer(size)", imports = {}))
    public static final ByteBuffer byteBufferOf(final int n) {
        return BuffersJvmKt.byteBuffer(n);
    }
    
    public static final ByteBuffer byteBufferOf(final byte... array) {
        Intrinsics.checkNotNullParameter((Object)array, "elements");
        return toBuffer(Arrays.copyOf(array, array.length));
    }
    
    @Deprecated(message = "Do not use this.", replaceWith = @ReplaceWith(expression = "FloatBuffer(size)", imports = {}))
    public static final FloatBuffer floatBufferOf(final int n) {
        return BuffersJvmKt.floatBuffer(n);
    }
    
    public static final FloatBuffer floatBufferOf(final float... array) {
        Intrinsics.checkNotNullParameter((Object)array, "elements");
        return toBuffer(Arrays.copyOf(array, array.length));
    }
    
    public static final IntBuffer intBufferOf(final int... array) {
        Intrinsics.checkNotNullParameter((Object)array, "elements");
        return toBuffer(Arrays.copyOf(array, array.length));
    }
    
    public static final ShortBuffer shortBufferOf(final short... array) {
        Intrinsics.checkNotNullParameter((Object)array, "elements");
        return toBuffer(Arrays.copyOf(array, array.length));
    }
    
    public static final ByteBuffer toBuffer(final byte[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        final ByteBuffer byteBuffer = BuffersJvmKt.byteBuffer(array.length);
        byteBuffer.put(array);
        byteBuffer.flip();
        return byteBuffer;
    }
    
    public static final FloatBuffer toBuffer(final float[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        final FloatBuffer floatBuffer = BuffersJvmKt.floatBuffer(array.length);
        floatBuffer.put(array);
        floatBuffer.flip();
        return floatBuffer;
    }
    
    public static final IntBuffer toBuffer(final int[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        final IntBuffer intBuffer = BuffersJvmKt.intBuffer(array.length);
        intBuffer.put(array);
        intBuffer.flip();
        return intBuffer;
    }
    
    public static final ShortBuffer toBuffer(final short[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "<this>");
        final ShortBuffer shortBuffer = BuffersJvmKt.shortBuffer(array.length);
        shortBuffer.put(array);
        shortBuffer.flip();
        return shortBuffer;
    }
}
