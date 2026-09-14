package com.otaliastudios.opengl.internal;

import kotlin.jvm.internal.Intrinsics;
import android.opengl.EGLSurface;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010" }, d2 = { "Lcom/otaliastudios/opengl/internal/EglSurface;", "", "native", "Landroid/opengl/EGLSurface;", "(Landroid/opengl/EGLSurface;)V", "getNative", "()Landroid/opengl/EGLSurface;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class EglSurface
{
    private final EGLSurface native;
    
    public EglSurface(final EGLSurface native1) {
        this.native = native1;
    }
    
    public final EGLSurface component1() {
        return this.native;
    }
    
    public final EglSurface copy(final EGLSurface eglSurface) {
        return new EglSurface(eglSurface);
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof EglSurface && Intrinsics.areEqual((Object)this.native, (Object)((EglSurface)o).native));
    }
    
    public final EGLSurface getNative() {
        return this.native;
    }
    
    @Override
    public int hashCode() {
        final EGLSurface native1 = this.native;
        int hashCode;
        if (native1 == null) {
            hashCode = 0;
        }
        else {
            hashCode = native1.hashCode();
        }
        return hashCode;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("EglSurface(native=");
        sb.append((Object)this.native);
        sb.append(')');
        return sb.toString();
    }
}
