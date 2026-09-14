package com.yalantis.ucrop.util;

import android.util.Log;
import android.os.Build$VERSION;
import android.opengl.GLES20;
import android.opengl.EGL14;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.egl.EGLDisplay;
import android.opengl.GLES10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGL10;

public class EglUtils
{
    private static final String TAG = "EglUtils";
    
    private EglUtils() {
    }
    
    private static int getMaxTextureEgl10() {
        final EGL10 egl10 = (EGL10)EGLContext.getEGL();
        final EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eglGetDisplay, new int[2]);
        final EGLConfig[] array = { null };
        final int[] array2 = { 0 };
        egl10.eglChooseConfig(eglGetDisplay, new int[] { 12351, 12430, 12329, 0, 12339, 1, 12344 }, array, 1, array2);
        if (array2[0] == 0) {
            return 0;
        }
        final EGLConfig eglConfig = array[0];
        final EGLSurface eglCreatePbufferSurface = egl10.eglCreatePbufferSurface(eglGetDisplay, eglConfig, new int[] { 12375, 64, 12374, 64, 12344 });
        final EGLContext eglCreateContext = egl10.eglCreateContext(eglGetDisplay, eglConfig, EGL10.EGL_NO_CONTEXT, new int[] { 12440, 1, 12344 });
        egl10.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext);
        final int[] array3 = { 0 };
        GLES10.glGetIntegerv(3379, array3, 0);
        egl10.eglMakeCurrent(eglGetDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        egl10.eglDestroySurface(eglGetDisplay, eglCreatePbufferSurface);
        egl10.eglDestroyContext(eglGetDisplay, eglCreateContext);
        egl10.eglTerminate(eglGetDisplay);
        return array3[0];
    }
    
    private static int getMaxTextureEgl14() {
        final android.opengl.EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        final int[] array = new int[2];
        EGL14.eglInitialize(eglGetDisplay, array, 0, array, 1);
        final android.opengl.EGLConfig[] array2 = { null };
        final int[] array3 = { 0 };
        EGL14.eglChooseConfig(eglGetDisplay, new int[] { 12351, 12430, 12329, 0, 12352, 4, 12339, 1, 12344 }, 0, array2, 0, 1, array3, 0);
        if (array3[0] == 0) {
            return 0;
        }
        final android.opengl.EGLConfig eglConfig = array2[0];
        final android.opengl.EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eglGetDisplay, eglConfig, new int[] { 12375, 64, 12374, 64, 12344 }, 0);
        final android.opengl.EGLContext eglCreateContext = EGL14.eglCreateContext(eglGetDisplay, eglConfig, EGL14.EGL_NO_CONTEXT, new int[] { 12440, 2, 12344 }, 0);
        EGL14.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext);
        final int[] array4 = { 0 };
        GLES20.glGetIntegerv(3379, array4, 0);
        EGL14.eglMakeCurrent(eglGetDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
        EGL14.eglDestroySurface(eglGetDisplay, eglCreatePbufferSurface);
        EGL14.eglDestroyContext(eglGetDisplay, eglCreateContext);
        EGL14.eglTerminate(eglGetDisplay);
        return array4[0];
    }
    
    public static int getMaxTextureSize() {
        try {
            if (Build$VERSION.SDK_INT >= 17) {
                return getMaxTextureEgl14();
            }
            return getMaxTextureEgl10();
        }
        catch (final Exception ex) {
            Log.d("EglUtils", "getMaxTextureSize: ", (Throwable)ex);
            return 0;
        }
    }
}
