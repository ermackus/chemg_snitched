package com.otaliastudios.opengl.draw;

import kotlin.jvm.internal.Intrinsics;
import android.graphics.PointF;
import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.extensions.MatrixKt;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import java.nio.FloatBuffer;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020(H\u0014J\b\u0010*\u001a\u00020(H\u0002J\b\u0010+\u001a\u00020(H\u0002R,\u0010\b\u001a\u00060\u0006j\u0002`\u00072\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R$\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R$\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00060\u001dj\u0002`\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006," }, d2 = { "Lcom/otaliastudios/opengl/draw/GlPolygon;", "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "sides", "", "(I)V", "value", "Landroid/graphics/PointF;", "Lcom/otaliastudios/opengl/geometry/PointF;", "center", "getCenter", "()Landroid/graphics/PointF;", "setCenter", "(Landroid/graphics/PointF;)V", "", "centerX", "getCenterX", "()F", "setCenterX", "(F)V", "centerY", "getCenterY", "setCenterY", "radius", "getRadius", "setRadius", "rotation", "getRotation", "setRotation", "vertexArray", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "getVertexArray", "()Ljava/nio/FloatBuffer;", "setVertexArray", "(Ljava/nio/FloatBuffer;)V", "viewportScaleX", "viewportScaleY", "viewportTranslationX", "viewportTranslationY", "draw", "", "onViewportSizeChanged", "onViewportSizeOrCenterChanged", "updateArray", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class GlPolygon extends Gl2dDrawable
{
    private float centerX;
    private float centerY;
    private float radius;
    private float rotation;
    private final int sides;
    private FloatBuffer vertexArray;
    private float viewportScaleX;
    private float viewportScaleY;
    private float viewportTranslationX;
    private float viewportTranslationY;
    
    public GlPolygon(final int sides) {
        this.sides = sides;
        if (sides >= 3) {
            this.viewportScaleX = 1.0f;
            this.viewportScaleY = 1.0f;
            this.radius = 1.0f;
            this.vertexArray = BuffersJvmKt.floatBuffer((sides + 2) * this.getCoordsPerVertex());
            this.updateArray();
            return;
        }
        throw new IllegalArgumentException("Polygon should have at least 3 sides.");
    }
    
    private final void onViewportSizeOrCenterChanged() {
        MatrixKt.scale$default(this.getModelMatrix(), 1.0f / this.viewportScaleX, 1.0f / this.viewportScaleY, 0.0f, 4, (Object)null);
        MatrixKt.translate$default(this.getModelMatrix(), -this.viewportTranslationX, -this.viewportTranslationY, 0.0f, 4, (Object)null);
        if (this.getViewportWidth() > this.getViewportHeight()) {
            final float viewportScaleX = this.getViewportHeight() / (float)this.getViewportWidth();
            this.viewportScaleX = viewportScaleX;
            this.viewportScaleY = 1.0f;
            this.viewportTranslationX = this.centerX * (1 - viewportScaleX);
            this.viewportTranslationY = 0.0f;
        }
        else if (this.getViewportWidth() < this.getViewportHeight()) {
            final float viewportScaleY = this.getViewportWidth() / (float)this.getViewportHeight();
            this.viewportScaleY = viewportScaleY;
            this.viewportScaleX = 1.0f;
            this.viewportTranslationY = this.centerY * (1 - viewportScaleY);
            this.viewportTranslationX = 0.0f;
        }
        else {
            this.viewportScaleX = 1.0f;
            this.viewportScaleY = 1.0f;
            this.viewportTranslationX = 0.0f;
            this.viewportTranslationY = 0.0f;
        }
        MatrixKt.translate$default(this.getModelMatrix(), this.viewportTranslationX, this.viewportTranslationY, 0.0f, 4, (Object)null);
        MatrixKt.scale$default(this.getModelMatrix(), this.viewportScaleX, this.viewportScaleY, 0.0f, 4, (Object)null);
    }
    
    private final void updateArray() {
        final FloatBuffer vertexArray = this.getVertexArray();
        vertexArray.clear();
        vertexArray.put(this.centerX);
        vertexArray.put(this.centerY);
        float n = this.rotation * 0.017453292f;
        final int sides = this.sides;
        final float n2 = 6.2831855f / sides;
        for (int i = 0; i < sides; ++i) {
            final float centerX = this.getCenterX();
            final float radius = this.getRadius();
            final double n3 = n;
            vertexArray.put(centerX + radius * (float)Math.cos(n3));
            vertexArray.put(this.getCenterY() + this.getRadius() * (float)Math.sin(n3));
            n += n2;
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
    
    public final PointF getCenter() {
        return new PointF(this.centerX, this.centerY);
    }
    
    public final float getCenterX() {
        return this.centerX;
    }
    
    public final float getCenterY() {
        return this.centerY;
    }
    
    public final float getRadius() {
        return this.radius;
    }
    
    public final float getRotation() {
        return this.rotation;
    }
    
    public FloatBuffer getVertexArray() {
        return this.vertexArray;
    }
    
    protected void onViewportSizeChanged() {
        super.onViewportSizeChanged();
        this.onViewportSizeOrCenterChanged();
    }
    
    public final void setCenter(final PointF pointF) {
        Intrinsics.checkNotNullParameter((Object)pointF, "value");
        this.setCenterX(pointF.x);
        this.setCenterY(pointF.y);
    }
    
    public final void setCenterX(final float centerX) {
        this.centerX = centerX;
        this.updateArray();
        this.onViewportSizeOrCenterChanged();
    }
    
    public final void setCenterY(final float centerY) {
        this.centerY = centerY;
        this.updateArray();
        this.onViewportSizeOrCenterChanged();
    }
    
    public final void setRadius(final float radius) {
        this.radius = radius;
        this.updateArray();
    }
    
    public final void setRotation(final float n) {
        this.rotation = n % 360;
        this.updateArray();
    }
    
    public void setVertexArray(final FloatBuffer vertexArray) {
        Intrinsics.checkNotNullParameter((Object)vertexArray, "<set-?>");
        this.vertexArray = vertexArray;
    }
}
