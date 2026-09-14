package com.otaliastudios.opengl.core;

import kotlin.jvm.JvmStatic;
import com.otaliastudios.opengl.internal.EglConfig;
import com.otaliastudios.opengl.internal.EglDisplay;
import kotlin.jvm.internal.Intrinsics;
import android.opengl.EGLConfig;
import android.opengl.EGLDisplay;
import android.opengl.GLSurfaceView$EGLConfigChooser;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\u0011" }, d2 = { "Lcom/otaliastudios/opengl/core/EglConfigChooser;", "Lcom/otaliastudios/opengl/core/EglNativeConfigChooser;", "()V", "GLES2", "Landroid/opengl/GLSurfaceView$EGLConfigChooser;", "getGLES2$annotations", "GLES3", "getGLES3$annotations", "getConfig", "Landroid/opengl/EGLConfig;", "display", "Landroid/opengl/EGLDisplay;", "version", "", "recordable", "", "Chooser", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class EglConfigChooser extends EglNativeConfigChooser
{
    public static final GLSurfaceView$EGLConfigChooser GLES2;
    public static final GLSurfaceView$EGLConfigChooser GLES3;
    public static final EglConfigChooser INSTANCE;
    
    static {
        INSTANCE = new EglConfigChooser();
        GLES2 = (GLSurfaceView$EGLConfigChooser)new EglConfigChooser.EglConfigChooser$Chooser(2);
        GLES3 = (GLSurfaceView$EGLConfigChooser)new EglConfigChooser.EglConfigChooser$Chooser(3);
    }
    
    private EglConfigChooser() {
    }
    
    @JvmStatic
    public static final EGLConfig getConfig(final EGLDisplay eglDisplay, final int n, final boolean b) {
        Intrinsics.checkNotNullParameter((Object)eglDisplay, "display");
        final EglConfig config$library_release = EglConfigChooser.INSTANCE.getConfig$library_release(new EglDisplay(eglDisplay), n, b);
        EGLConfig native1;
        if (config$library_release == null) {
            native1 = null;
        }
        else {
            native1 = config$library_release.getNative();
        }
        return native1;
    }
}
