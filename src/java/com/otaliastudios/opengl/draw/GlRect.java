package com.otaliastudios.opengl.draw;

import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import android.graphics.RectF;
import android.opengl.GLES20;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.core.Egloo;
import kotlin.Unit;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.nio.FloatBuffer;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fJ&\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011J\u0014\u0010\b\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0017J\u0010\u0010\b\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0017R\u001e\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0018" }, d2 = { "Lcom/otaliastudios/opengl/draw/GlRect;", "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "()V", "vertexArray", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "getVertexArray", "()Ljava/nio/FloatBuffer;", "setVertexArray", "(Ljava/nio/FloatBuffer;)V", "draw", "", "setRect", "rect", "Landroid/graphics/RectF;", "Lcom/otaliastudios/opengl/geometry/RectF;", "left", "", "top", "right", "bottom", "array", "", "Companion", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class GlRect extends Gl2dDrawable
{
    private static final GlRect.GlRect$Companion Companion;
    @Deprecated
    private static final float[] FULL_RECTANGLE_COORDS;
    private FloatBuffer vertexArray;
    
    static {
        Companion = new GlRect.GlRect$Companion((DefaultConstructorMarker)null);
        FULL_RECTANGLE_COORDS = new float[] { -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f };
    }
    
    public GlRect() {
        final FloatBuffer floatBuffer = BuffersJvmKt.floatBuffer(GlRect.FULL_RECTANGLE_COORDS.length);
        floatBuffer.put(GlRect.FULL_RECTANGLE_COORDS);
        floatBuffer.clear();
        final Unit instance = Unit.INSTANCE;
        this.vertexArray = floatBuffer;
    }
    
    public void draw() {
        Egloo.checkGlError("glDrawArrays start");
        GLES20.glDrawArrays(GlKt.getGL_TRIANGLE_STRIP(), 0, this.getVertexCount());
        Egloo.checkGlError("glDrawArrays end");
    }
    
    public FloatBuffer getVertexArray() {
        return this.vertexArray;
    }
    
    public final void setRect(final float n, final float n2, final float n3, final float n4) {
        this.getVertexArray().clear();
        this.getVertexArray().put(n);
        this.getVertexArray().put(n4);
        this.getVertexArray().put(n3);
        this.getVertexArray().put(n4);
        this.getVertexArray().put(n);
        this.getVertexArray().put(n2);
        this.getVertexArray().put(n3);
        this.getVertexArray().put(n2);
        this.getVertexArray().flip();
        this.notifyVertexArrayChange();
    }
    
    public final void setRect(final RectF rectF) {
        Intrinsics.checkNotNullParameter((Object)rectF, "rect");
        this.setRect(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }
    
    @kotlin.Deprecated(message = "Use setRect", replaceWith = @ReplaceWith(expression = "setRect(rect)", imports = {}))
    public void setVertexArray(final RectF rect) {
        Intrinsics.checkNotNullParameter((Object)rect, "rect");
        this.setRect(rect);
    }
    
    public void setVertexArray(final FloatBuffer vertexArray) {
        Intrinsics.checkNotNullParameter((Object)vertexArray, "<set-?>");
        this.vertexArray = vertexArray;
    }
    
    @kotlin.Deprecated(message = "Use setRect", replaceWith = @ReplaceWith(expression = "setRect(rect)", imports = {}))
    public void setVertexArray(final float[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "array");
        if (array.length == this.getCoordsPerVertex() * 4) {
            this.getVertexArray().clear();
            this.getVertexArray().put(array);
            this.getVertexArray().flip();
            this.notifyVertexArrayChange();
            return;
        }
        throw new IllegalArgumentException("Vertex array should have 8 values.");
    }
}
