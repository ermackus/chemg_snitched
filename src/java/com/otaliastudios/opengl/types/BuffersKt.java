package com.otaliastudios.opengl.types;

import kotlin.jvm.internal.Intrinsics;
import java.nio.Buffer;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u0003¨\u0006\u0004" }, d2 = { "dispose", "", "Ljava/nio/Buffer;", "Lcom/otaliastudios/opengl/types/Buffer;", "library_release" }, k = 2, mv = { 1, 5, 1 }, xi = 48)
public final class BuffersKt
{
    public static final void dispose(final Buffer buffer) {
        Intrinsics.checkNotNullParameter((Object)buffer, "<this>");
        if (buffer instanceof Disposable) {
            ((Disposable)buffer).dispose();
        }
    }
}
