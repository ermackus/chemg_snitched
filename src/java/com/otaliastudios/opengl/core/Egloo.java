package com.otaliastudios.opengl.core;

import com.otaliastudios.opengl.internal.EglSurface;
import com.otaliastudios.opengl.internal.EglContext;
import com.otaliastudios.opengl.internal.EglDisplay;
import com.otaliastudios.opengl.internal.GlKt;
import kotlin.UInt;
import android.opengl.GLES20;
import kotlin.jvm.JvmStatic;
import android.util.Log;
import com.otaliastudios.opengl.internal.MiscKt;
import com.otaliastudios.opengl.internal.EglKt;
import android.opengl.EGL14;
import kotlin.jvm.internal.Intrinsics;
import com.otaliastudios.opengl.extensions.MatrixKt;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\rH\u0007J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\rH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014" }, d2 = { "Lcom/otaliastudios/opengl/core/Egloo;", "", "()V", "IDENTITY_MATRIX", "", "SIZE_OF_BYTE", "", "SIZE_OF_FLOAT", "SIZE_OF_INT", "SIZE_OF_SHORT", "checkEglError", "", "opName", "", "checkGlError", "checkGlProgramLocation", "location", "label", "logCurrent", "msg", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class Egloo
{
    public static final float[] IDENTITY_MATRIX;
    public static final Egloo INSTANCE;
    public static final int SIZE_OF_BYTE = 1;
    public static final int SIZE_OF_FLOAT = 4;
    public static final int SIZE_OF_INT = 4;
    public static final int SIZE_OF_SHORT = 2;
    
    static {
        INSTANCE = new Egloo();
        final float[] identity_MATRIX = new float[16];
        MatrixKt.makeIdentity(identity_MATRIX);
        IDENTITY_MATRIX = identity_MATRIX;
    }
    
    private Egloo() {
    }
    
    @JvmStatic
    public static final void checkEglError(String string) {
        Intrinsics.checkNotNullParameter((Object)string, "opName");
        final int eglGetError = EGL14.eglGetError();
        if (eglGetError == EglKt.getEGL_SUCCESS()) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Error during ");
        sb.append(string);
        sb.append(": EGL error 0x");
        sb.append(MiscKt.intToHexString(eglGetError));
        string = sb.toString();
        Log.e("Egloo", string);
        throw new RuntimeException(string);
    }
    
    @JvmStatic
    public static final void checkGlError(String string) {
        Intrinsics.checkNotNullParameter((Object)string, "opName");
        final int constructor-impl = UInt.constructor-impl(GLES20.glGetError());
        if (constructor-impl == GlKt.getGL_NO_ERROR()) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Error during ");
        sb.append(string);
        sb.append(": glError 0x");
        sb.append(MiscKt.intToHexString(constructor-impl));
        sb.append(": ");
        sb.append(MiscKt.gluErrorString(constructor-impl));
        string = sb.toString();
        Log.e("Egloo", string);
        throw new RuntimeException(string);
    }
    
    @JvmStatic
    public static final void checkGlProgramLocation(final int n, String string) {
        Intrinsics.checkNotNullParameter((Object)string, "label");
        if (n >= 0) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Unable to locate ");
        sb.append(string);
        sb.append(" in program");
        string = sb.toString();
        Log.e("Egloo", string);
        throw new RuntimeException(string);
    }
    
    @JvmStatic
    public static final void logCurrent(final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "msg");
        final EglDisplay eglDisplay = new EglDisplay(EGL14.eglGetCurrentDisplay());
        final EglContext eglContext = new EglContext(EGL14.eglGetCurrentContext());
        final EglSurface eglSurface = new EglSurface(EGL14.eglGetCurrentSurface(EglKt.getEGL_DRAW()));
        final StringBuilder sb = new StringBuilder();
        sb.append("Current EGL (");
        sb.append(s);
        sb.append("): display=");
        sb.append((Object)eglDisplay);
        sb.append(", context=");
        sb.append((Object)eglContext);
        sb.append(", surface=");
        sb.append((Object)eglSurface);
        Log.i("Egloo", sb.toString());
    }
}
