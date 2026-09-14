package com.otaliastudios.opengl.surface;

import com.otaliastudios.opengl.internal.EglKt;
import kotlin.jvm.internal.Intrinsics;
import com.otaliastudios.opengl.internal.EglSurface;
import com.otaliastudios.opengl.core.EglCore;
import kotlin.Metadata;

@Metadata(d1 = { "\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\u0010J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\b\u0010\u0019\u001a\u00020\u0017H\u0016J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010H\u0004J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0010H\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f" }, d2 = { "Lcom/otaliastudios/opengl/surface/EglNativeSurface;", "", "eglCore", "Lcom/otaliastudios/opengl/core/EglCore;", "eglSurface", "Lcom/otaliastudios/opengl/internal/EglSurface;", "(Lcom/otaliastudios/opengl/core/EglCore;Lcom/otaliastudios/opengl/internal/EglSurface;)V", "getEglCore$library_release", "()Lcom/otaliastudios/opengl/core/EglCore;", "setEglCore$library_release", "(Lcom/otaliastudios/opengl/core/EglCore;)V", "getEglSurface$library_release", "()Lcom/otaliastudios/opengl/internal/EglSurface;", "setEglSurface$library_release", "(Lcom/otaliastudios/opengl/internal/EglSurface;)V", "height", "", "width", "getHeight", "getWidth", "isCurrent", "", "makeCurrent", "", "makeNothingCurrent", "release", "setHeight", "setPresentationTime", "nsecs", "", "setWidth", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class EglNativeSurface
{
    private EglCore eglCore;
    private EglSurface eglSurface;
    private int height;
    private int width;
    
    public EglNativeSurface(final EglCore eglCore, final EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter((Object)eglCore, "eglCore");
        Intrinsics.checkNotNullParameter((Object)eglSurface, "eglSurface");
        this.eglCore = eglCore;
        this.eglSurface = eglSurface;
        this.width = -1;
        this.height = -1;
    }
    
    public final EglCore getEglCore$library_release() {
        return this.eglCore;
    }
    
    public final EglSurface getEglSurface$library_release() {
        return this.eglSurface;
    }
    
    public final int getHeight() {
        int n;
        if ((n = this.height) < 0) {
            n = this.eglCore.querySurface$library_release(this.eglSurface, EglKt.getEGL_HEIGHT());
        }
        return n;
    }
    
    public final int getWidth() {
        int n;
        if ((n = this.width) < 0) {
            n = this.eglCore.querySurface$library_release(this.eglSurface, EglKt.getEGL_WIDTH());
        }
        return n;
    }
    
    public final boolean isCurrent() {
        return this.eglCore.isSurfaceCurrent$library_release(this.eglSurface);
    }
    
    public final void makeCurrent() {
        this.eglCore.makeSurfaceCurrent$library_release(this.eglSurface);
    }
    
    public final void makeNothingCurrent() {
        this.eglCore.makeCurrent();
    }
    
    public void release() {
        this.eglCore.releaseSurface$library_release(this.eglSurface);
        this.eglSurface = EglKt.getEGL_NO_SURFACE();
        this.height = -1;
        this.width = -1;
    }
    
    public final void setEglCore$library_release(final EglCore eglCore) {
        Intrinsics.checkNotNullParameter((Object)eglCore, "<set-?>");
        this.eglCore = eglCore;
    }
    
    public final void setEglSurface$library_release(final EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter((Object)eglSurface, "<set-?>");
        this.eglSurface = eglSurface;
    }
    
    protected final void setHeight(final int height) {
        this.height = height;
    }
    
    public final void setPresentationTime(final long n) {
        this.eglCore.setSurfacePresentationTime$library_release(this.eglSurface, n);
    }
    
    protected final void setWidth(final int width) {
        this.width = width;
    }
}
