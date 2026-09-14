package com.otaliastudios.opengl.core;

import com.otaliastudios.opengl.internal.EglKt;
import java.util.Iterator;
import android.util.Log;
import kotlin.collections.IntIterator;
import kotlin.collections.ArraysKt;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import kotlin.jvm.internal.Intrinsics;
import com.otaliastudios.opengl.internal.EglConfig;
import com.otaliastudios.opengl.internal.EglDisplay;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000e¨\u0006\u0010" }, d2 = { "Lcom/otaliastudios/opengl/core/EglNativeConfigChooser;", "", "()V", "getConfig", "Lcom/otaliastudios/opengl/internal/EglConfig;", "display", "Lcom/otaliastudios/opengl/internal/EglDisplay;", "version", "", "recordable", "", "getConfig$library_release", "getConfigSpec", "", "getConfigSpec$library_release", "Companion", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class EglNativeConfigChooser
{
    public static final Companion Companion;
    private static final int EGL_RECORDABLE_ANDROID = 12610;
    
    static {
        Companion = new Companion(null);
    }
    
    public final EglConfig getConfig$library_release(final EglDisplay eglDisplay, final int n, final boolean b) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        final int[] configSpec$library_release = this.getConfigSpec$library_release(n, b);
        final EglConfig[] array = { null };
        final int[] array2 = { 0 };
        final EGLConfig[] array3 = { null };
        final boolean eglChooseConfig = EGL14.eglChooseConfig(eglDisplay.getNative(), configSpec$library_release, 0, array3, 0, 1, array2, 0);
        if (eglChooseConfig) {
            final Iterator iterator = ((Iterable)ArraysKt.getIndices((Object[])array)).iterator();
            while (iterator.hasNext()) {
                final int nextInt = ((IntIterator)iterator).nextInt();
                final EGLConfig eglConfig = array3[nextInt];
                EglConfig eglConfig2;
                if (eglConfig == null) {
                    eglConfig2 = null;
                }
                else {
                    eglConfig2 = new EglConfig(eglConfig);
                }
                array[nextInt] = eglConfig2;
            }
        }
        if (!eglChooseConfig) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Unable to find RGB8888 / ");
            sb.append(n);
            sb.append(" EGLConfig");
            Log.w("EglConfigChooser", sb.toString());
            return null;
        }
        return array[0];
    }
    
    public final int[] getConfigSpec$library_release(int egl_OPENGL_ES2_BIT, final boolean b) {
        if (egl_OPENGL_ES2_BIT >= 3) {
            egl_OPENGL_ES2_BIT = (EglKt.getEGL_OPENGL_ES2_BIT() | EglKt.getEGL_OPENGL_ES3_BIT_KHR());
        }
        else {
            egl_OPENGL_ES2_BIT = EglKt.getEGL_OPENGL_ES2_BIT();
        }
        final int egl_RED_SIZE = EglKt.getEGL_RED_SIZE();
        final int egl_GREEN_SIZE = EglKt.getEGL_GREEN_SIZE();
        final int egl_BLUE_SIZE = EglKt.getEGL_BLUE_SIZE();
        final int egl_ALPHA_SIZE = EglKt.getEGL_ALPHA_SIZE();
        final int egl_SURFACE_TYPE = EglKt.getEGL_SURFACE_TYPE();
        final int egl_WINDOW_BIT = EglKt.getEGL_WINDOW_BIT();
        final int egl_PBUFFER_BIT = EglKt.getEGL_PBUFFER_BIT();
        final int egl_RENDERABLE_TYPE = EglKt.getEGL_RENDERABLE_TYPE();
        int egl_NONE;
        if (b) {
            egl_NONE = 12610;
        }
        else {
            egl_NONE = EglKt.getEGL_NONE();
        }
        return new int[] { egl_RED_SIZE, 8, egl_GREEN_SIZE, 8, egl_BLUE_SIZE, 8, egl_ALPHA_SIZE, 8, egl_SURFACE_TYPE, egl_WINDOW_BIT | egl_PBUFFER_BIT, egl_RENDERABLE_TYPE, egl_OPENGL_ES2_BIT, egl_NONE, b ? 1 : 0, EglKt.getEGL_NONE() };
    }
    
    @Metadata(d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005" }, d2 = { "Lcom/otaliastudios/opengl/core/EglNativeConfigChooser$Companion;", "", "()V", "EGL_RECORDABLE_ANDROID", "", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
    public static final class Companion
    {
        private Companion() {
        }
    }
}
