package com.otaliastudios.opengl.surface;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import kotlin.io.CloseableKt;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;
import android.graphics.Bitmap$CompressFormat;
import android.opengl.EGLSurface;
import com.otaliastudios.opengl.core.EglCore;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0017\b\u0014\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u000b\u001a\u00020\f¨\u0006\u0015" }, d2 = { "Lcom/otaliastudios/opengl/surface/EglSurface;", "Lcom/otaliastudios/opengl/surface/EglNativeSurface;", "eglCore", "Lcom/otaliastudios/opengl/core/EglCore;", "eglSurface", "Landroid/opengl/EGLSurface;", "(Lcom/otaliastudios/opengl/core/EglCore;Landroid/opengl/EGLSurface;)V", "Lcom/otaliastudios/opengl/internal/EglSurface;", "(Lcom/otaliastudios/opengl/core/EglCore;Lcom/otaliastudios/opengl/internal/EglSurface;)V", "toByteArray", "", "format", "Landroid/graphics/Bitmap$CompressFormat;", "toFile", "", "file", "Ljava/io/File;", "toOutputStream", "stream", "Ljava/io/OutputStream;", "Companion", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public abstract class EglSurface extends EglNativeSurface
{
    public static final EglSurface.EglSurface$Companion Companion;
    private static final String TAG;
    
    static {
        Companion = new EglSurface.EglSurface$Companion((DefaultConstructorMarker)null);
        final String simpleName = com.otaliastudios.opengl.internal.EglSurface.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue((Object)simpleName, "EglSurface::class.java.simpleName");
        TAG = simpleName;
    }
    
    protected EglSurface(final EglCore eglCore, final EGLSurface eglSurface) {
        Intrinsics.checkNotNullParameter((Object)eglCore, "eglCore");
        Intrinsics.checkNotNullParameter((Object)eglSurface, "eglSurface");
        this(eglCore, new com.otaliastudios.opengl.internal.EglSurface(eglSurface));
    }
    
    public EglSurface(final EglCore eglCore, final com.otaliastudios.opengl.internal.EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter((Object)eglCore, "eglCore");
        Intrinsics.checkNotNullParameter((Object)eglSurface, "eglSurface");
        super(eglCore, eglSurface);
    }
    
    public final byte[] toByteArray(final Bitmap$CompressFormat bitmap$CompressFormat) {
        Intrinsics.checkNotNullParameter((Object)bitmap$CompressFormat, "format");
        final Closeable closeable = (Closeable)new ByteArrayOutputStream();
        final Throwable t = null;
        try {
            final ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream)closeable;
            this.toOutputStream((OutputStream)byteArrayOutputStream, bitmap$CompressFormat);
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            Intrinsics.checkNotNullExpressionValue((Object)byteArray, "it.toByteArray()");
            CloseableKt.closeFinally(closeable, t);
            return byteArray;
        }
        finally {
            try {}
            finally {
                final Throwable t2;
                CloseableKt.closeFinally(closeable, t2);
            }
        }
    }
    
    public final void toFile(final File file, final Bitmap$CompressFormat bitmap$CompressFormat) throws IOException {
        Intrinsics.checkNotNullParameter((Object)file, "file");
        Intrinsics.checkNotNullParameter((Object)bitmap$CompressFormat, "format");
        final BufferedOutputStream bufferedOutputStream = null;
        BufferedOutputStream bufferedOutputStream3;
        try {
            final BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream((OutputStream)new FileOutputStream(file.toString()));
            try {
                this.toOutputStream((OutputStream)bufferedOutputStream2, bitmap$CompressFormat);
                bufferedOutputStream2.close();
                return;
            }
            finally {}
        }
        finally {
            bufferedOutputStream3 = bufferedOutputStream;
        }
        if (bufferedOutputStream3 != null) {
            bufferedOutputStream3.close();
        }
    }
    
    public final void toOutputStream(final OutputStream outputStream, final Bitmap$CompressFormat bitmap$CompressFormat) {
        Intrinsics.checkNotNullParameter((Object)outputStream, "stream");
        Intrinsics.checkNotNullParameter((Object)bitmap$CompressFormat, "format");
        if (this.isCurrent()) {
            final int width = this.getWidth();
            final int height = this.getHeight();
            final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(width * height * 4);
            allocateDirect.order(ByteOrder.LITTLE_ENDIAN);
            final Buffer buffer = (Buffer)allocateDirect;
            GLES20.glReadPixels(0, 0, width, height, 6408, 5121, buffer);
            Egloo.checkGlError("glReadPixels");
            allocateDirect.rewind();
            final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap$Config.ARGB_8888);
            bitmap.copyPixelsFromBuffer(buffer);
            bitmap.compress(bitmap$CompressFormat, 90, outputStream);
            bitmap.recycle();
            return;
        }
        throw new RuntimeException("Expected EGL context/surface is not current");
    }
}
