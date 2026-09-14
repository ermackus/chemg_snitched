package com.otaliastudios.opengl.program;

import com.otaliastudios.opengl.core.GlBindableKt;
import kotlin.jvm.functions.Function0;
import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import kotlin.UInt;
import com.otaliastudios.opengl.draw.GlDrawable;
import kotlin.jvm.JvmStatic;
import kotlin.Deprecated;
import java.util.Arrays;
import com.otaliastudios.opengl.internal.GlKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Metadata;
import com.otaliastudios.opengl.core.GlBindable;

@Metadata(d1 = { "\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bB\u001b\b\u0016\u0012\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000b¢\u0006\u0002\u0010\fB+\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000b¢\u0006\u0002\u0010\u000fJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0004J\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0004J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010 \u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010!\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\"\u001a\u00020\u0015H\u0016J\b\u0010#\u001a\u00020\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006%" }, d2 = { "Lcom/otaliastudios/opengl/program/GlProgram;", "Lcom/otaliastudios/opengl/core/GlBindable;", "handle", "", "(I)V", "vertexShader", "", "fragmentShader", "(Ljava/lang/String;Ljava/lang/String;)V", "shaders", "", "Lcom/otaliastudios/opengl/program/GlShader;", "([Lcom/otaliastudios/opengl/program/GlShader;)V", "ownsHandle", "", "(IZ[Lcom/otaliastudios/opengl/program/GlShader;)V", "getHandle", "()I", "isReleased", "[Lcom/otaliastudios/opengl/program/GlShader;", "bind", "", "draw", "drawable", "Lcom/otaliastudios/opengl/draw/GlDrawable;", "modelViewProjectionMatrix", "", "getAttribHandle", "Lcom/otaliastudios/opengl/program/GlProgramLocation;", "name", "getUniformHandle", "onDraw", "onPostDraw", "onPreDraw", "release", "unbind", "Companion", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class GlProgram implements GlBindable
{
    public static final GlProgram.GlProgram$Companion Companion;
    private final int handle;
    private boolean isReleased;
    private final boolean ownsHandle;
    private final GlShader[] shaders;
    
    static {
        Companion = new GlProgram.GlProgram$Companion((DefaultConstructorMarker)null);
    }
    
    public GlProgram(final int n) {
        this(n, false, new GlShader[0]);
    }
    
    protected GlProgram(final int handle, final boolean ownsHandle, final GlShader... shaders) {
        Intrinsics.checkNotNullParameter((Object)shaders, "shaders");
        this.handle = handle;
        this.ownsHandle = ownsHandle;
        this.shaders = shaders;
    }
    
    public GlProgram(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexShader");
        Intrinsics.checkNotNullParameter((Object)s2, "fragmentShader");
        this(new GlShader[] { new GlShader(GlKt.getGL_VERTEX_SHADER(), s), new GlShader(GlKt.getGL_FRAGMENT_SHADER(), s2) });
    }
    
    public GlProgram(final GlShader... array) {
        Intrinsics.checkNotNullParameter((Object)array, "shaders");
        this(GlProgram.Companion.create((GlShader[])Arrays.copyOf((Object[])array, array.length)), true, (GlShader[])Arrays.copyOf((Object[])array, array.length));
    }
    
    @Deprecated(message = "Use create(GlShader) signature.")
    @JvmStatic
    public static final int create(final String s, final String s2) {
        return GlProgram.Companion.create(s, s2);
    }
    
    @JvmStatic
    public static final int create(final GlShader... array) {
        return GlProgram.Companion.create(array);
    }
    
    public static /* synthetic */ void draw$default(final GlProgram glProgram, final GlDrawable glDrawable, float[] modelMatrix, final int n, final Object o) {
        if (o == null) {
            if ((n & 0x2) != 0x0) {
                modelMatrix = glDrawable.getModelMatrix();
            }
            glProgram.draw(glDrawable, modelMatrix);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: draw");
    }
    
    public void bind() {
        GLES20.glUseProgram(UInt.constructor-impl(this.handle));
        Egloo.checkGlError("glUseProgram");
    }
    
    public final void draw(final GlDrawable glDrawable) {
        Intrinsics.checkNotNullParameter((Object)glDrawable, "drawable");
        draw$default(this, glDrawable, null, 2, null);
    }
    
    public final void draw(final GlDrawable glDrawable, final float[] array) {
        Intrinsics.checkNotNullParameter((Object)glDrawable, "drawable");
        Intrinsics.checkNotNullParameter((Object)array, "modelViewProjectionMatrix");
        Egloo.checkGlError("draw start");
        GlBindableKt.use((GlBindable)this, (Function0)new GlProgram$draw$1(this, glDrawable, array));
        Egloo.checkGlError("draw end");
    }
    
    protected final GlProgramLocation getAttribHandle(final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "name");
        return GlProgramLocation.Companion.getAttrib(this.handle, s);
    }
    
    public final int getHandle() {
        return this.handle;
    }
    
    protected final GlProgramLocation getUniformHandle(final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "name");
        return GlProgramLocation.Companion.getUniform(this.handle, s);
    }
    
    public void onDraw(final GlDrawable glDrawable) {
        Intrinsics.checkNotNullParameter((Object)glDrawable, "drawable");
        glDrawable.draw();
    }
    
    public void onPostDraw(final GlDrawable glDrawable) {
        Intrinsics.checkNotNullParameter((Object)glDrawable, "drawable");
    }
    
    public void onPreDraw(final GlDrawable glDrawable, final float[] array) {
        Intrinsics.checkNotNullParameter((Object)glDrawable, "drawable");
        Intrinsics.checkNotNullParameter((Object)array, "modelViewProjectionMatrix");
    }
    
    public void release() {
        if (!this.isReleased) {
            if (this.ownsHandle) {
                GLES20.glDeleteProgram(UInt.constructor-impl(this.handle));
            }
            final GlShader[] shaders = this.shaders;
            for (int length = shaders.length, i = 0; i < length; ++i) {
                shaders[i].release();
            }
            this.isReleased = true;
        }
    }
    
    public void unbind() {
        GLES20.glUseProgram(0);
    }
}
