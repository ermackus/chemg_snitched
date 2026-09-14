package com.otaliastudios.opengl.program;

import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import kotlin.UInt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\u0006\u0010\f\u001a\u00020\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u000f" }, d2 = { "Lcom/otaliastudios/opengl/program/GlShader;", "", "type", "", "source", "", "(ILjava/lang/String;)V", "id", "(II)V", "getId", "()I", "getType", "release", "", "Companion", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class GlShader
{
    private static final Companion Companion;
    private final int id;
    private final int type;
    
    static {
        Companion = new Companion(null);
    }
    
    public GlShader(final int type, final int id) {
        this.type = type;
        this.id = id;
    }
    
    public GlShader(final int n, final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "source");
        this(n, GlShader.Companion.compile(n, s));
    }
    
    public final int getId() {
        return this.id;
    }
    
    public final int getType() {
        return this.type;
    }
    
    public final void release() {
        GLES20.glDeleteShader(UInt.constructor-impl(this.id));
    }
    
    @Metadata(d1 = { "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b" }, d2 = { "Lcom/otaliastudios/opengl/program/GlShader$Companion;", "", "()V", "compile", "", "type", "source", "", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
    private static final class Companion
    {
        private final int compile(final int n, String string) {
            final int constructor-impl = UInt.constructor-impl(GLES20.glCreateShader(UInt.constructor-impl(n)));
            Egloo.checkGlError(Intrinsics.stringPlus("glCreateShader type=", (Object)n));
            GLES20.glShaderSource(constructor-impl, string);
            GLES20.glCompileShader(constructor-impl);
            final int[] array = { 0 };
            GLES20.glGetShaderiv(constructor-impl, GlKt.getGL_COMPILE_STATUS(), array, 0);
            if (array[0] != 0) {
                return constructor-impl;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("Could not compile shader ");
            sb.append(n);
            sb.append(": '");
            sb.append((Object)GLES20.glGetShaderInfoLog(constructor-impl));
            sb.append("' source: ");
            sb.append(string);
            string = sb.toString();
            GLES20.glDeleteShader(constructor-impl);
            throw new RuntimeException(string);
        }
    }
}
