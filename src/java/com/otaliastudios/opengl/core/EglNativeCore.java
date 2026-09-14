package com.otaliastudios.opengl.core;

import android.opengl.EGLExt;
import android.util.Log;
import com.otaliastudios.opengl.internal.EglSurface;
import android.opengl.EGL14;
import com.otaliastudios.opengl.internal.EglKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.otaliastudios.opengl.internal.EglDisplay;
import com.otaliastudios.opengl.internal.EglContext;
import com.otaliastudios.opengl.internal.EglConfig;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0005\b\u0016\u0018\u0000 -2\u00020\u0001:\u0001-B\u001b\b\u0000\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0011J\u0015\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u0014J\u0015\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0018J\r\u0010\u0019\u001a\u00020\u001aH\u0010¢\u0006\u0002\b\u001bJ\u0015\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u001dJ\u001d\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u001dJ\u001d\u0010 \u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\"J\r\u0010#\u001a\u00020\u001aH\u0010¢\u0006\u0002\b$J\u0015\u0010%\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u000eH\u0000¢\u0006\u0002\b&J\u001d\u0010'\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020)H\u0000¢\u0006\u0002\b*J\u0015\u0010+\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0000¢\u0006\u0002\b,R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006." }, d2 = { "Lcom/otaliastudios/opengl/core/EglNativeCore;", "", "sharedContext", "Lcom/otaliastudios/opengl/internal/EglContext;", "flags", "", "(Lcom/otaliastudios/opengl/internal/EglContext;I)V", "eglConfig", "Lcom/otaliastudios/opengl/internal/EglConfig;", "eglContext", "eglDisplay", "Lcom/otaliastudios/opengl/internal/EglDisplay;", "glVersion", "createOffscreenSurface", "Lcom/otaliastudios/opengl/internal/EglSurface;", "width", "height", "createOffscreenSurface$library_release", "createWindowSurface", "surface", "createWindowSurface$library_release", "isSurfaceCurrent", "", "eglSurface", "isSurfaceCurrent$library_release", "makeCurrent", "", "makeCurrent$library_release", "makeSurfaceCurrent", "makeSurfaceCurrent$library_release", "drawSurface", "readSurface", "querySurface", "what", "querySurface$library_release", "release", "release$library_release", "releaseSurface", "releaseSurface$library_release", "setSurfacePresentationTime", "nsecs", "", "setSurfacePresentationTime$library_release", "swapSurfaceBuffers", "swapSurfaceBuffers$library_release", "Companion", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class EglNativeCore
{
    public static final Companion Companion;
    public static final int FLAG_RECORDABLE = 1;
    public static final int FLAG_TRY_GLES3 = 2;
    private EglConfig eglConfig;
    private EglContext eglContext;
    private EglDisplay eglDisplay;
    private int glVersion;
    
    static {
        Companion = new Companion(null);
    }
    
    public EglNativeCore() {
        this(null, 0, 3, null);
    }
    
    public EglNativeCore(final EglContext eglContext, int n) {
        Intrinsics.checkNotNullParameter((Object)eglContext, "sharedContext");
        this.eglDisplay = EglKt.getEGL_NO_DISPLAY();
        this.eglContext = EglKt.getEGL_NO_CONTEXT();
        this.glVersion = -1;
        final EglDisplay eglDisplay = new EglDisplay(EGL14.eglGetDisplay(0));
        this.eglDisplay = eglDisplay;
        if (eglDisplay == EglKt.getEGL_NO_DISPLAY()) {
            throw new RuntimeException("unable to get EGL14 display");
        }
        if (EGL14.eglInitialize(this.eglDisplay.getNative(), new int[1], 0, new int[1], 0)) {
            final EglNativeConfigChooser eglNativeConfigChooser = new EglNativeConfigChooser();
            final boolean b = (n & 0x1) != 0x0;
            if ((n & 0x2) != 0x0) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                final EglConfig config$library_release = eglNativeConfigChooser.getConfig$library_release(this.eglDisplay, 3, b);
                if (config$library_release != null) {
                    n = EglKt.getEGL_CONTEXT_CLIENT_VERSION();
                    final EglContext eglContext2 = new EglContext(EGL14.eglCreateContext(this.eglDisplay.getNative(), config$library_release.getNative(), eglContext.getNative(), new int[] { n, 3, EglKt.getEGL_NONE() }, 0));
                    try {
                        Egloo.checkEglError("eglCreateContext (3)");
                        this.eglConfig = config$library_release;
                        this.eglContext = eglContext2;
                        this.glVersion = 3;
                    }
                    catch (final Exception ex) {}
                }
            }
            if (this.eglContext == EglKt.getEGL_NO_CONTEXT()) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                final EglConfig config$library_release2 = eglNativeConfigChooser.getConfig$library_release(this.eglDisplay, 2, b);
                if (config$library_release2 == null) {
                    throw new RuntimeException("Unable to find a suitable EGLConfig");
                }
                n = EglKt.getEGL_CONTEXT_CLIENT_VERSION();
                final EglContext eglContext3 = new EglContext(EGL14.eglCreateContext(this.eglDisplay.getNative(), config$library_release2.getNative(), eglContext.getNative(), new int[] { n, 2, EglKt.getEGL_NONE() }, 0));
                Egloo.checkEglError("eglCreateContext (2)");
                this.eglConfig = config$library_release2;
                this.eglContext = eglContext3;
                this.glVersion = 2;
            }
            return;
        }
        throw new RuntimeException("unable to initialize EGL14");
    }
    
    public final EglSurface createOffscreenSurface$library_release(final int n, final int n2) {
        final int egl_WIDTH = EglKt.getEGL_WIDTH();
        final int egl_HEIGHT = EglKt.getEGL_HEIGHT();
        final int egl_NONE = EglKt.getEGL_NONE();
        final EglDisplay eglDisplay = this.eglDisplay;
        final EglConfig eglConfig = this.eglConfig;
        Intrinsics.checkNotNull((Object)eglConfig);
        final EglSurface eglSurface = new EglSurface(EGL14.eglCreatePbufferSurface(eglDisplay.getNative(), eglConfig.getNative(), new int[] { egl_WIDTH, n, egl_HEIGHT, n2, egl_NONE }, 0));
        Egloo.checkEglError("eglCreatePbufferSurface");
        if (eglSurface != EglKt.getEGL_NO_SURFACE()) {
            return eglSurface;
        }
        throw new RuntimeException("surface was null");
    }
    
    public final EglSurface createWindowSurface$library_release(final Object o) {
        Intrinsics.checkNotNullParameter(o, "surface");
        final int egl_NONE = EglKt.getEGL_NONE();
        final EglDisplay eglDisplay = this.eglDisplay;
        final EglConfig eglConfig = this.eglConfig;
        Intrinsics.checkNotNull((Object)eglConfig);
        final EglSurface eglSurface = new EglSurface(EGL14.eglCreateWindowSurface(eglDisplay.getNative(), eglConfig.getNative(), o, new int[] { egl_NONE }, 0));
        Egloo.checkEglError("eglCreateWindowSurface");
        if (eglSurface != EglKt.getEGL_NO_SURFACE()) {
            return eglSurface;
        }
        throw new RuntimeException("surface was null");
    }
    
    public final boolean isSurfaceCurrent$library_release(final EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter((Object)eglSurface, "eglSurface");
        return Intrinsics.areEqual((Object)this.eglContext, (Object)new EglContext(EGL14.eglGetCurrentContext())) && Intrinsics.areEqual((Object)eglSurface, (Object)new EglSurface(EGL14.eglGetCurrentSurface(EglKt.getEGL_DRAW())));
    }
    
    public void makeCurrent$library_release() {
        if (EGL14.eglMakeCurrent(this.eglDisplay.getNative(), EglKt.getEGL_NO_SURFACE().getNative(), EglKt.getEGL_NO_SURFACE().getNative(), this.eglContext.getNative())) {
            return;
        }
        throw new RuntimeException("eglMakeCurrent failed");
    }
    
    public final void makeSurfaceCurrent$library_release(final EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter((Object)eglSurface, "eglSurface");
        if (this.eglDisplay == EglKt.getEGL_NO_DISPLAY()) {
            Log.v("EglCore", "NOTE: makeSurfaceCurrent w/o display");
        }
        if (EGL14.eglMakeCurrent(this.eglDisplay.getNative(), eglSurface.getNative(), eglSurface.getNative(), this.eglContext.getNative())) {
            return;
        }
        throw new RuntimeException("eglMakeCurrent failed");
    }
    
    public final void makeSurfaceCurrent$library_release(final EglSurface eglSurface, final EglSurface eglSurface2) {
        Intrinsics.checkNotNullParameter((Object)eglSurface, "drawSurface");
        Intrinsics.checkNotNullParameter((Object)eglSurface2, "readSurface");
        if (this.eglDisplay == EglKt.getEGL_NO_DISPLAY()) {
            Log.v("EglCore", "NOTE: makeSurfaceCurrent w/o display");
        }
        if (EGL14.eglMakeCurrent(this.eglDisplay.getNative(), eglSurface.getNative(), eglSurface2.getNative(), this.eglContext.getNative())) {
            return;
        }
        throw new RuntimeException("eglMakeCurrent(draw,read) failed");
    }
    
    public final int querySurface$library_release(final EglSurface eglSurface, final int n) {
        Intrinsics.checkNotNullParameter((Object)eglSurface, "eglSurface");
        final int[] array = { 0 };
        EGL14.eglQuerySurface(this.eglDisplay.getNative(), eglSurface.getNative(), n, array, 0);
        return array[0];
    }
    
    public void release$library_release() {
        if (this.eglDisplay != EglKt.getEGL_NO_DISPLAY()) {
            EGL14.eglMakeCurrent(this.eglDisplay.getNative(), EglKt.getEGL_NO_SURFACE().getNative(), EglKt.getEGL_NO_SURFACE().getNative(), EglKt.getEGL_NO_CONTEXT().getNative());
            EGL14.eglDestroyContext(this.eglDisplay.getNative(), this.eglContext.getNative());
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.eglDisplay.getNative());
        }
        this.eglDisplay = EglKt.getEGL_NO_DISPLAY();
        this.eglContext = EglKt.getEGL_NO_CONTEXT();
        this.eglConfig = null;
    }
    
    public final void releaseSurface$library_release(final EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter((Object)eglSurface, "eglSurface");
        EGL14.eglDestroySurface(this.eglDisplay.getNative(), eglSurface.getNative());
    }
    
    public final void setSurfacePresentationTime$library_release(final EglSurface eglSurface, final long n) {
        Intrinsics.checkNotNullParameter((Object)eglSurface, "eglSurface");
        EGLExt.eglPresentationTimeANDROID(this.eglDisplay.getNative(), eglSurface.getNative(), n);
    }
    
    public final boolean swapSurfaceBuffers$library_release(final EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter((Object)eglSurface, "eglSurface");
        return EGL14.eglSwapBuffers(this.eglDisplay.getNative(), eglSurface.getNative());
    }
    
    @Metadata(d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0006" }, d2 = { "Lcom/otaliastudios/opengl/core/EglNativeCore$Companion;", "", "()V", "FLAG_RECORDABLE", "", "FLAG_TRY_GLES3", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
    public static final class Companion
    {
        private Companion() {
        }
    }
}
