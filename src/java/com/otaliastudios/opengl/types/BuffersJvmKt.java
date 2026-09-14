package com.otaliastudios.opengl.types;

import java.nio.ShortBuffer;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import kotlin.jvm.internal.Intrinsics;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import kotlin.Metadata;

@Metadata(d1 = { "\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\b\u001a\u00060\tj\u0002`\n2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0006\u0010\u0003\u001a\u00020\u0004*\n\u0010\u000e\"\u00020\u000f2\u00020\u000f*\n\u0010\u0010\"\u00020\u00012\u00020\u0001*\n\u0010\u0011\"\u00020\u00062\u00020\u0006*\n\u0010\u0012\"\u00020\t2\u00020\t*\n\u0010\u0013\"\u00020\f2\u00020\f¨\u0006\u0014" }, d2 = { "byteBuffer", "Ljava/nio/ByteBuffer;", "Lcom/otaliastudios/opengl/types/ByteBuffer;", "size", "", "floatBuffer", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "intBuffer", "Ljava/nio/IntBuffer;", "Lcom/otaliastudios/opengl/types/IntBuffer;", "shortBuffer", "Ljava/nio/ShortBuffer;", "Lcom/otaliastudios/opengl/types/ShortBuffer;", "Buffer", "Ljava/nio/Buffer;", "ByteBuffer", "FloatBuffer", "IntBuffer", "ShortBuffer", "library_release" }, k = 2, mv = { 1, 5, 1 }, xi = 48)
public final class BuffersJvmKt
{
    public static final ByteBuffer byteBuffer(final int n) {
        final ByteBuffer order = ByteBuffer.allocateDirect(n * 1).order(ByteOrder.nativeOrder());
        order.limit(order.capacity());
        Intrinsics.checkNotNullExpressionValue((Object)order, "allocateDirect(size * Egloo.SIZE_OF_BYTE)\n        .order(ByteOrder.nativeOrder())\n        .also { it.limit(it.capacity()) }");
        return order;
    }
    
    public static final FloatBuffer floatBuffer(final int n) {
        final FloatBuffer floatBuffer = byteBuffer(n * 4).asFloatBuffer();
        Intrinsics.checkNotNullExpressionValue((Object)floatBuffer, "byteBuffer(size * Egloo.SIZE_OF_FLOAT).asFloatBuffer()");
        return floatBuffer;
    }
    
    public static final IntBuffer intBuffer(final int n) {
        final IntBuffer intBuffer = byteBuffer(n * 4).asIntBuffer();
        Intrinsics.checkNotNullExpressionValue((Object)intBuffer, "byteBuffer(size * Egloo.SIZE_OF_INT).asIntBuffer()");
        return intBuffer;
    }
    
    public static final ShortBuffer shortBuffer(final int n) {
        final ShortBuffer shortBuffer = byteBuffer(n * 2).asShortBuffer();
        Intrinsics.checkNotNullExpressionValue((Object)shortBuffer, "byteBuffer(size * Egloo.SIZE_OF_SHORT).asShortBuffer()");
        return shortBuffer;
    }
}
