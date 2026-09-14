package com.otaliastudios.opengl.core;

import android.opengl.GLES20;
import kotlin.UInt;
import com.otaliastudios.opengl.internal.GlKt;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0004J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\u0016\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006\u0015" }, d2 = { "Lcom/otaliastudios/opengl/core/GlViewportAware;", "", "()V", "viewportArray", "", "<set-?>", "", "viewportHeight", "getViewportHeight", "()I", "setViewportHeight", "(I)V", "viewportWidth", "getViewportWidth", "setViewportWidth", "ensureViewportSize", "", "onViewportSizeChanged", "setViewportSize", "width", "height", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public abstract class GlViewportAware
{
    private final int[] viewportArray;
    private int viewportHeight;
    private int viewportWidth;
    
    public GlViewportAware() {
        this.viewportArray = new int[4];
        this.viewportWidth = -1;
        this.viewportHeight = -1;
    }
    
    protected final void ensureViewportSize() {
        if (this.viewportHeight == -1 || this.viewportWidth == -1) {
            GLES20.glGetIntegerv(UInt.constructor-impl(GlKt.getGL_VIEWPORT()), this.viewportArray, 0);
            final int[] viewportArray = this.viewportArray;
            this.setViewportSize(viewportArray[2], viewportArray[3]);
        }
    }
    
    public final int getViewportHeight() {
        return this.viewportHeight;
    }
    
    public final int getViewportWidth() {
        return this.viewportWidth;
    }
    
    protected void onViewportSizeChanged() {
    }
    
    protected final void setViewportHeight(final int viewportHeight) {
        this.viewportHeight = viewportHeight;
    }
    
    public final void setViewportSize(final int viewportWidth, final int viewportHeight) {
        if (viewportWidth != this.viewportWidth || viewportHeight != this.viewportHeight) {
            this.viewportWidth = viewportWidth;
            this.viewportHeight = viewportHeight;
            this.onViewportSizeChanged();
        }
    }
    
    protected final void setViewportWidth(final int viewportWidth) {
        this.viewportWidth = viewportWidth;
    }
}
