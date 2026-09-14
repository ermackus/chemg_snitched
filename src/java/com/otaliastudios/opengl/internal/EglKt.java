package com.otaliastudios.opengl.internal;

import android.opengl.EGLExt;
import java.util.Iterator;
import kotlin.collections.IntIterator;
import kotlin.collections.ArraysKt;
import android.opengl.EGLConfig;
import kotlin.jvm.internal.Intrinsics;
import android.opengl.EGL14;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000L\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u0007\u001a>\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u00103\u001a\u0002042\u000e\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u000107062\u0006\u00108\u001a\u00020\u00012\u0006\u00109\u001a\u000204H\u0080\b¢\u0006\u0002\u0010:\u001a)\u0010;\u001a\u00020\u00112\u0006\u00102\u001a\u00020\u00152\u0006\u0010<\u001a\u0002072\u0006\u0010=\u001a\u00020\u00112\u0006\u00103\u001a\u000204H\u0080\b\u001a!\u0010>\u001a\u00020\u00192\u0006\u00102\u001a\u00020\u00152\u0006\u0010<\u001a\u0002072\u0006\u00103\u001a\u000204H\u0080\b\u001a)\u0010?\u001a\u00020\u00192\u0006\u00102\u001a\u00020\u00152\u0006\u0010<\u001a\u0002072\u0006\u0010@\u001a\u00020A2\u0006\u00103\u001a\u000204H\u0080\b\u001a\u0019\u0010B\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010C\u001a\u00020\u0011H\u0080\b\u001a\u0019\u0010D\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u0019H\u0080\b\u001a\t\u0010E\u001a\u00020\u0011H\u0080\b\u001a\t\u0010F\u001a\u00020\u0015H\u0080\b\u001a\u0011\u0010G\u001a\u00020\u00192\u0006\u0010H\u001a\u00020\u0001H\u0080\b\u001a\t\u0010I\u001a\u00020\u0015H\u0080\b\u001a\t\u0010J\u001a\u00020\u0001H\u0080\b\u001a!\u0010K\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010L\u001a\u0002042\u0006\u0010M\u001a\u000204H\u0080\b\u001a)\u0010N\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010O\u001a\u00020\u00192\u0006\u0010P\u001a\u00020\u00192\u0006\u0010C\u001a\u00020\u0011H\u0080\b\u001a!\u0010Q\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u00192\u0006\u0010R\u001a\u00020SH\u0080\b\u001a)\u0010T\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u00192\u0006\u0010U\u001a\u00020\u00012\u0006\u0010V\u001a\u000204H\u0080\b\u001a\t\u0010W\u001a\u000201H\u0080\b\u001a\u0019\u0010X\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u0019H\u0080\b\u001a\u0011\u0010Y\u001a\u0002012\u0006\u00102\u001a\u00020\u0015H\u0080\b\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0014\u0010\u0006\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0014\u0010\b\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u0014\u0010\n\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0003\"\u0014\u0010\f\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0003\"\u0014\u0010\u000e\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0003\"\u0014\u0010\u0010\u001a\u00020\u0011X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0014\u0010\u0014\u001a\u00020\u0015X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0014\u0010\u0018\u001a\u00020\u0019X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0014\u0010\u001c\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0003\"\u0014\u0010\u001e\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0003\"\u0014\u0010 \u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0003\"\u0014\u0010\"\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0003\"\u0014\u0010$\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0003\"\u0014\u0010&\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0003\"\u0014\u0010(\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0003\"\u0014\u0010*\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0003\"\u0014\u0010,\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0003\"\u0014\u0010.\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0003¨\u0006Z" }, d2 = { "EGL_ALPHA_SIZE", "", "getEGL_ALPHA_SIZE", "()I", "EGL_BLUE_SIZE", "getEGL_BLUE_SIZE", "EGL_CONTEXT_CLIENT_VERSION", "getEGL_CONTEXT_CLIENT_VERSION", "EGL_DRAW", "getEGL_DRAW", "EGL_GREEN_SIZE", "getEGL_GREEN_SIZE", "EGL_HEIGHT", "getEGL_HEIGHT", "EGL_NONE", "getEGL_NONE", "EGL_NO_CONTEXT", "Lcom/otaliastudios/opengl/internal/EglContext;", "getEGL_NO_CONTEXT", "()Lcom/otaliastudios/opengl/internal/EglContext;", "EGL_NO_DISPLAY", "Lcom/otaliastudios/opengl/internal/EglDisplay;", "getEGL_NO_DISPLAY", "()Lcom/otaliastudios/opengl/internal/EglDisplay;", "EGL_NO_SURFACE", "Lcom/otaliastudios/opengl/internal/EglSurface;", "getEGL_NO_SURFACE", "()Lcom/otaliastudios/opengl/internal/EglSurface;", "EGL_OPENGL_ES2_BIT", "getEGL_OPENGL_ES2_BIT", "EGL_OPENGL_ES3_BIT_KHR", "getEGL_OPENGL_ES3_BIT_KHR", "EGL_PBUFFER_BIT", "getEGL_PBUFFER_BIT", "EGL_READ", "getEGL_READ", "EGL_RED_SIZE", "getEGL_RED_SIZE", "EGL_RENDERABLE_TYPE", "getEGL_RENDERABLE_TYPE", "EGL_SUCCESS", "getEGL_SUCCESS", "EGL_SURFACE_TYPE", "getEGL_SURFACE_TYPE", "EGL_WIDTH", "getEGL_WIDTH", "EGL_WINDOW_BIT", "getEGL_WINDOW_BIT", "eglChooseConfig", "", "display", "attributes", "", "configs", "", "Lcom/otaliastudios/opengl/internal/EglConfig;", "configsSize", "numConfigs", "(Lcom/otaliastudios/opengl/internal/EglDisplay;[I[Lcom/otaliastudios/opengl/internal/EglConfig;I[I)Z", "eglCreateContext", "config", "sharedContext", "eglCreatePbufferSurface", "eglCreateWindowSurface", "surface", "", "eglDestroyContext", "context", "eglDestroySurface", "eglGetCurrentContext", "eglGetCurrentDisplay", "eglGetCurrentSurface", "which", "eglGetDefaultDisplay", "eglGetError", "eglInitialize", "major", "minor", "eglMakeCurrent", "draw", "read", "eglPresentationTime", "nanoseconds", "", "eglQuerySurface", "attribute", "out", "eglReleaseThread", "eglSwapBuffers", "eglTerminate", "library_release" }, k = 2, mv = { 1, 5, 1 }, xi = 48)
public final class EglKt
{
    private static final int EGL_ALPHA_SIZE;
    private static final int EGL_BLUE_SIZE;
    private static final int EGL_CONTEXT_CLIENT_VERSION;
    private static final int EGL_DRAW;
    private static final int EGL_GREEN_SIZE;
    private static final int EGL_HEIGHT;
    private static final int EGL_NONE;
    private static final EglContext EGL_NO_CONTEXT;
    private static final EglDisplay EGL_NO_DISPLAY;
    private static final EglSurface EGL_NO_SURFACE;
    private static final int EGL_OPENGL_ES2_BIT;
    private static final int EGL_OPENGL_ES3_BIT_KHR;
    private static final int EGL_PBUFFER_BIT;
    private static final int EGL_READ;
    private static final int EGL_RED_SIZE;
    private static final int EGL_RENDERABLE_TYPE;
    private static final int EGL_SUCCESS;
    private static final int EGL_SURFACE_TYPE;
    private static final int EGL_WIDTH;
    private static final int EGL_WINDOW_BIT;
    
    static {
        EGL_NO_CONTEXT = new EglContext(EGL14.EGL_NO_CONTEXT);
        EGL_NO_DISPLAY = new EglDisplay(EGL14.EGL_NO_DISPLAY);
        EGL_NO_SURFACE = new EglSurface(EGL14.EGL_NO_SURFACE);
        EGL_SUCCESS = 12288;
        EGL_NONE = 12344;
        EGL_WIDTH = 12375;
        EGL_HEIGHT = 12374;
        EGL_READ = 12378;
        EGL_DRAW = 12377;
        EGL_CONTEXT_CLIENT_VERSION = 12440;
        EGL_OPENGL_ES2_BIT = 4;
        EGL_OPENGL_ES3_BIT_KHR = 64;
        EGL_RED_SIZE = 12324;
        EGL_GREEN_SIZE = 12323;
        EGL_BLUE_SIZE = 12322;
        EGL_ALPHA_SIZE = 12321;
        EGL_SURFACE_TYPE = 12339;
        EGL_WINDOW_BIT = 4;
        EGL_PBUFFER_BIT = 1;
        EGL_RENDERABLE_TYPE = 12352;
    }
    
    public static final boolean eglChooseConfig(final EglDisplay eglDisplay, final int[] array, final EglConfig[] array2, int nextInt, final int[] array3) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)array, "attributes");
        Intrinsics.checkNotNullParameter((Object)array2, "configs");
        Intrinsics.checkNotNullParameter((Object)array3, "numConfigs");
        final EGLConfig[] array4 = new EGLConfig[array2.length];
        final boolean eglChooseConfig = EGL14.eglChooseConfig(eglDisplay.getNative(), array, 0, array4, 0, nextInt, array3, 0);
        if (eglChooseConfig) {
            final Iterator iterator = ((Iterable)ArraysKt.getIndices((Object[])array2)).iterator();
            while (iterator.hasNext()) {
                nextInt = ((IntIterator)iterator).nextInt();
                final EGLConfig eglConfig = array4[nextInt];
                EglConfig eglConfig2;
                if (eglConfig == null) {
                    eglConfig2 = null;
                }
                else {
                    eglConfig2 = new EglConfig(eglConfig);
                }
                array2[nextInt] = eglConfig2;
            }
        }
        return eglChooseConfig;
    }
    
    public static final EglContext eglCreateContext(final EglDisplay eglDisplay, final EglConfig eglConfig, final EglContext eglContext, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)eglConfig, "config");
        Intrinsics.checkNotNullParameter((Object)eglContext, "sharedContext");
        Intrinsics.checkNotNullParameter((Object)array, "attributes");
        return new EglContext(EGL14.eglCreateContext(eglDisplay.getNative(), eglConfig.getNative(), eglContext.getNative(), array, 0));
    }
    
    public static final EglSurface eglCreatePbufferSurface(final EglDisplay eglDisplay, final EglConfig eglConfig, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)eglConfig, "config");
        Intrinsics.checkNotNullParameter((Object)array, "attributes");
        return new EglSurface(EGL14.eglCreatePbufferSurface(eglDisplay.getNative(), eglConfig.getNative(), array, 0));
    }
    
    public static final EglSurface eglCreateWindowSurface(final EglDisplay eglDisplay, final EglConfig eglConfig, final Object o, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)eglConfig, "config");
        Intrinsics.checkNotNullParameter(o, "surface");
        Intrinsics.checkNotNullParameter((Object)array, "attributes");
        return new EglSurface(EGL14.eglCreateWindowSurface(eglDisplay.getNative(), eglConfig.getNative(), o, array, 0));
    }
    
    public static final boolean eglDestroyContext(final EglDisplay eglDisplay, final EglContext eglContext) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)eglContext, "context");
        return EGL14.eglDestroyContext(eglDisplay.getNative(), eglContext.getNative());
    }
    
    public static final boolean eglDestroySurface(final EglDisplay eglDisplay, final EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)eglSurface, "surface");
        return EGL14.eglDestroySurface(eglDisplay.getNative(), eglSurface.getNative());
    }
    
    public static final EglContext eglGetCurrentContext() {
        return new EglContext(EGL14.eglGetCurrentContext());
    }
    
    public static final EglDisplay eglGetCurrentDisplay() {
        return new EglDisplay(EGL14.eglGetCurrentDisplay());
    }
    
    public static final EglSurface eglGetCurrentSurface(final int n) {
        return new EglSurface(EGL14.eglGetCurrentSurface(n));
    }
    
    public static final EglDisplay eglGetDefaultDisplay() {
        return new EglDisplay(EGL14.eglGetDisplay(0));
    }
    
    public static final int eglGetError() {
        return EGL14.eglGetError();
    }
    
    public static final boolean eglInitialize(final EglDisplay eglDisplay, final int[] array, final int[] array2) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)array, "major");
        Intrinsics.checkNotNullParameter((Object)array2, "minor");
        return EGL14.eglInitialize(eglDisplay.getNative(), array, 0, array2, 0);
    }
    
    public static final boolean eglMakeCurrent(final EglDisplay eglDisplay, final EglSurface eglSurface, final EglSurface eglSurface2, final EglContext eglContext) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)eglSurface, "draw");
        Intrinsics.checkNotNullParameter((Object)eglSurface2, "read");
        Intrinsics.checkNotNullParameter((Object)eglContext, "context");
        return EGL14.eglMakeCurrent(eglDisplay.getNative(), eglSurface.getNative(), eglSurface2.getNative(), eglContext.getNative());
    }
    
    public static final boolean eglPresentationTime(final EglDisplay eglDisplay, final EglSurface eglSurface, final long n) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)eglSurface, "surface");
        return EGLExt.eglPresentationTimeANDROID(eglDisplay.getNative(), eglSurface.getNative(), n);
    }
    
    public static final boolean eglQuerySurface(final EglDisplay eglDisplay, final EglSurface eglSurface, final int n, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)eglSurface, "surface");
        Intrinsics.checkNotNullParameter((Object)array, "out");
        return EGL14.eglQuerySurface(eglDisplay.getNative(), eglSurface.getNative(), n, array, 0);
    }
    
    public static final boolean eglReleaseThread() {
        return EGL14.eglReleaseThread();
    }
    
    public static final boolean eglSwapBuffers(final EglDisplay eglDisplay, final EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        Intrinsics.checkNotNullParameter((Object)eglSurface, "surface");
        return EGL14.eglSwapBuffers(eglDisplay.getNative(), eglSurface.getNative());
    }
    
    public static final boolean eglTerminate(final EglDisplay eglDisplay) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        return EGL14.eglTerminate(eglDisplay.getNative());
    }
    
    public static final int getEGL_ALPHA_SIZE() {
        return EglKt.EGL_ALPHA_SIZE;
    }
    
    public static final int getEGL_BLUE_SIZE() {
        return EglKt.EGL_BLUE_SIZE;
    }
    
    public static final int getEGL_CONTEXT_CLIENT_VERSION() {
        return EglKt.EGL_CONTEXT_CLIENT_VERSION;
    }
    
    public static final int getEGL_DRAW() {
        return EglKt.EGL_DRAW;
    }
    
    public static final int getEGL_GREEN_SIZE() {
        return EglKt.EGL_GREEN_SIZE;
    }
    
    public static final int getEGL_HEIGHT() {
        return EglKt.EGL_HEIGHT;
    }
    
    public static final int getEGL_NONE() {
        return EglKt.EGL_NONE;
    }
    
    public static final EglContext getEGL_NO_CONTEXT() {
        return EglKt.EGL_NO_CONTEXT;
    }
    
    public static final EglDisplay getEGL_NO_DISPLAY() {
        return EglKt.EGL_NO_DISPLAY;
    }
    
    public static final EglSurface getEGL_NO_SURFACE() {
        return EglKt.EGL_NO_SURFACE;
    }
    
    public static final int getEGL_OPENGL_ES2_BIT() {
        return EglKt.EGL_OPENGL_ES2_BIT;
    }
    
    public static final int getEGL_OPENGL_ES3_BIT_KHR() {
        return EglKt.EGL_OPENGL_ES3_BIT_KHR;
    }
    
    public static final int getEGL_PBUFFER_BIT() {
        return EglKt.EGL_PBUFFER_BIT;
    }
    
    public static final int getEGL_READ() {
        return EglKt.EGL_READ;
    }
    
    public static final int getEGL_RED_SIZE() {
        return EglKt.EGL_RED_SIZE;
    }
    
    public static final int getEGL_RENDERABLE_TYPE() {
        return EglKt.EGL_RENDERABLE_TYPE;
    }
    
    public static final int getEGL_SUCCESS() {
        return EglKt.EGL_SUCCESS;
    }
    
    public static final int getEGL_SURFACE_TYPE() {
        return EglKt.EGL_SURFACE_TYPE;
    }
    
    public static final int getEGL_WIDTH() {
        return EglKt.EGL_WIDTH;
    }
    
    public static final int getEGL_WINDOW_BIT() {
        return EglKt.EGL_WINDOW_BIT;
    }
}
