package com.otaliastudios.opengl.draw;

import kotlin.jvm.internal.Intrinsics;
import android.graphics.RectF;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\nj\u0002`\u000bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f" }, d2 = { "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "Lcom/otaliastudios/opengl/draw/GlDrawable;", "()V", "coordsPerVertex", "", "getCoordsPerVertex", "()I", "getBounds", "", "rect", "Landroid/graphics/RectF;", "Lcom/otaliastudios/opengl/geometry/RectF;", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public abstract class Gl2dDrawable extends GlDrawable
{
    private final int coordsPerVertex;
    
    public Gl2dDrawable() {
        this.coordsPerVertex = 2;
    }
    
    public final void getBounds(final RectF rectF) {
        Intrinsics.checkNotNullParameter((Object)rectF, "rect");
        float min = Float.MAX_VALUE;
        float min2 = Float.MAX_VALUE;
        float max = -3.4028235E38f;
        float max2 = -3.4028235E38f;
        int n = 0;
        while (this.getVertexArray().hasRemaining()) {
            final float value = this.getVertexArray().get();
            if (n % 2 == 0) {
                min = Math.min(min, value);
                max2 = Math.max(max2, value);
            }
            else {
                max = Math.max(max, value);
                min2 = Math.min(min2, value);
            }
            ++n;
        }
        this.getVertexArray().rewind();
        rectF.set(min, max, max2, min2);
    }
    
    public final int getCoordsPerVertex() {
        return this.coordsPerVertex;
    }
}
