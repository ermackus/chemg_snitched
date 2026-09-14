package com.otaliastudios.opengl.draw;

import kotlin.jvm.internal.Intrinsics;
import android.graphics.RectF;
import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.nio.FloatBuffer;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J<\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0016J\b\u0010\u001d\u001a\u00020\u0014H\u0014J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\u000e\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u001bJ&\u0010\u001f\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u001bJ\u0012\u0010%\u001a\u00020\u00142\n\u0010&\u001a\u00060'j\u0002`(J&\u0010%\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00060\rj\u0002`\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006*" }, d2 = { "Lcom/otaliastudios/opengl/draw/GlRoundRect;", "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "()V", "bottom", "", "bottomLeftCorner", "bottomRightCorner", "left", "right", "top", "topLeftCorner", "topRightCorner", "vertexArray", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "getVertexArray", "()Ljava/nio/FloatBuffer;", "setVertexArray", "(Ljava/nio/FloatBuffer;)V", "addCornerArc", "", "array", "pivotX", "pivotY", "width", "height", "startAngle", "", "draw", "onViewportSizeChanged", "recompute", "setCornersPx", "corners", "topLeft", "topRight", "bottomLeft", "bottomRight", "setRect", "rect", "Landroid/graphics/RectF;", "Lcom/otaliastudios/opengl/geometry/RectF;", "Companion", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class GlRoundRect extends Gl2dDrawable
{
    private static final GlRoundRect.GlRoundRect$Companion Companion;
    @Deprecated
    private static final int POINTS_PER_CORNER = 20;
    private float bottom;
    private float bottomLeftCorner;
    private float bottomRightCorner;
    private float left;
    private float right;
    private float top;
    private float topLeftCorner;
    private float topRightCorner;
    private FloatBuffer vertexArray;
    
    static {
        Companion = new GlRoundRect.GlRoundRect$Companion((DefaultConstructorMarker)null);
    }
    
    public GlRoundRect() {
        this.top = 1.0f;
        this.bottom = -1.0f;
        this.left = -1.0f;
        this.right = 1.0f;
        this.vertexArray = BuffersJvmKt.floatBuffer(this.getCoordsPerVertex() * 82);
        this.recompute();
    }
    
    private final void addCornerArc(final FloatBuffer floatBuffer, final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = 1.0f / 19;
        float n7 = 0.0f;
        for (int i = 0; i < 20; ++i) {
            final double n8 = (float)((n5 + (n5 - 90 - n5) * n7) * 3.141592653589793 / 180);
            final double n9 = (float)Math.sin(n8) * n3;
            final double n10 = 2;
            final float n11 = n3 * n4 / (float)Math.sqrt((double)((float)Math.pow(n9, n10) + (float)Math.pow((double)((float)Math.cos(n8) * n4), n10)));
            floatBuffer.put(n + (float)Math.cos(n8) * n11);
            floatBuffer.put(n2 + n11 * (float)Math.sin(n8));
            n7 += n6;
        }
    }
    
    private final void recompute() {
        final FloatBuffer vertexArray = this.getVertexArray();
        vertexArray.clear();
        final float n = (this.right + this.left) / 2.0f;
        final float n2 = (this.top + this.bottom) / 2.0f;
        vertexArray.put(n);
        vertexArray.put(n2);
        final int viewportHeight = this.getViewportHeight();
        final int n3 = 1;
        final boolean b = viewportHeight > 0 && this.getViewportWidth() > 0;
        if (b && this.topLeftCorner > 0.0f) {
            final float n4 = this.topLeftCorner / this.getViewportWidth() * 2.0f;
            final float n5 = this.topLeftCorner / this.getViewportHeight() * 2.0f;
            this.addCornerArc(vertexArray, this.left + n4, this.top - n5, n4, n5, 180);
        }
        else {
            vertexArray.put(this.left);
            vertexArray.put(this.top);
        }
        if (b && this.topRightCorner > 0.0f) {
            final float n6 = this.topRightCorner / this.getViewportWidth() * 2.0f;
            final float n7 = this.topRightCorner / this.getViewportHeight() * 2.0f;
            this.addCornerArc(vertexArray, this.right - n6, this.top - n7, n6, n7, 90);
        }
        else {
            vertexArray.put(this.right);
            vertexArray.put(this.top);
        }
        if (b && this.bottomRightCorner > 0.0f) {
            final float n8 = this.bottomRightCorner / this.getViewportWidth() * 2.0f;
            final float n9 = this.bottomRightCorner / this.getViewportHeight() * 2.0f;
            this.addCornerArc(vertexArray, this.right - n8, this.bottom + n9, n8, n9, 0);
        }
        else {
            vertexArray.put(this.right);
            vertexArray.put(this.bottom);
        }
        int n10;
        if (b && this.bottomLeftCorner > 0.0f) {
            n10 = n3;
        }
        else {
            n10 = 0;
        }
        if (n10 != 0) {
            final float n11 = this.bottomLeftCorner / this.getViewportWidth() * 2.0f;
            final float n12 = this.bottomLeftCorner / this.getViewportHeight() * 2.0f;
            this.addCornerArc(vertexArray, this.left + n11, this.bottom + n12, n11, n12, -90);
        }
        else {
            vertexArray.put(this.left);
            vertexArray.put(this.bottom);
        }
        vertexArray.put(vertexArray.get(2));
        vertexArray.put(vertexArray.get(3));
        vertexArray.flip();
        this.notifyVertexArrayChange();
    }
    
    public void draw() {
        GLES20.glDrawArrays(GlKt.getGL_TRIANGLE_FAN(), 0, this.getVertexCount());
        Egloo.checkGlError("glDrawArrays");
    }
    
    public FloatBuffer getVertexArray() {
        return this.vertexArray;
    }
    
    protected void onViewportSizeChanged() {
        super.onViewportSizeChanged();
        this.recompute();
    }
    
    public final void setCornersPx(final int n) {
        this.setCornersPx(n, n, n, n);
    }
    
    public final void setCornersPx(final int n, final int n2, final int n3, final int n4) {
        this.topLeftCorner = (float)n;
        this.topRightCorner = (float)n2;
        this.bottomLeftCorner = (float)n3;
        this.bottomRightCorner = (float)n4;
        this.recompute();
    }
    
    public final void setRect(final float left, final float top, final float right, final float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.recompute();
    }
    
    public final void setRect(final RectF rectF) {
        Intrinsics.checkNotNullParameter((Object)rectF, "rect");
        this.setRect(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }
    
    public void setVertexArray(final FloatBuffer vertexArray) {
        Intrinsics.checkNotNullParameter((Object)vertexArray, "<set-?>");
        this.vertexArray = vertexArray;
    }
}
