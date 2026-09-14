package com.otaliastudios.opengl.internal;

import kotlin.jvm.internal.Intrinsics;
import android.opengl.EGLContext;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010" }, d2 = { "Lcom/otaliastudios/opengl/internal/EglContext;", "", "native", "Landroid/opengl/EGLContext;", "(Landroid/opengl/EGLContext;)V", "getNative", "()Landroid/opengl/EGLContext;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class EglContext
{
    private final EGLContext native;
    
    public EglContext(final EGLContext native1) {
        this.native = native1;
    }
    
    public final EGLContext component1() {
        return this.native;
    }
    
    public final EglContext copy(final EGLContext eglContext) {
        return new EglContext(eglContext);
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof EglContext && Intrinsics.areEqual((Object)this.native, (Object)((EglContext)o).native));
    }
    
    public final EGLContext getNative() {
        return this.native;
    }
    
    @Override
    public int hashCode() {
        final EGLContext native1 = this.native;
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
        sb.append("EglContext(native=");
        sb.append((Object)this.native);
        sb.append(')');
        return sb.toString();
    }
}
