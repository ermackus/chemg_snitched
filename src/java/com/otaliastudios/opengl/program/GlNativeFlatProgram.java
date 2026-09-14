package com.otaliastudios.opengl.program;

import java.nio.Buffer;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import kotlin.jvm.internal.Intrinsics;
import com.otaliastudios.opengl.draw.GlDrawable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0004H\u0016R \u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015" }, d2 = { "Lcom/otaliastudios/opengl/program/GlNativeFlatProgram;", "Lcom/otaliastudios/opengl/program/GlProgram;", "()V", "color", "", "getColor$annotations", "getColor", "()[F", "setColor", "([F)V", "fragmentColorHandle", "Lcom/otaliastudios/opengl/program/GlProgramLocation;", "vertexMvpMatrixHandle", "vertexPositionHandle", "onPostDraw", "", "drawable", "Lcom/otaliastudios/opengl/draw/GlDrawable;", "onPreDraw", "modelViewProjectionMatrix", "Companion", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class GlNativeFlatProgram extends GlProgram
{
    private static final GlNativeFlatProgram.GlNativeFlatProgram$Companion Companion;
    @Deprecated
    private static final String FRAGMENT_SHADER = "precision mediump float;\nuniform vec4 uColor;\nvoid main() {\n    gl_FragColor = uColor;\n}\n";
    @Deprecated
    private static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nattribute vec4 aPosition;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n}\n";
    private float[] color;
    private final GlProgramLocation fragmentColorHandle;
    private final GlProgramLocation vertexMvpMatrixHandle;
    private final GlProgramLocation vertexPositionHandle;
    
    static {
        Companion = new GlNativeFlatProgram.GlNativeFlatProgram$Companion((DefaultConstructorMarker)null);
    }
    
    public GlNativeFlatProgram() {
        super("uniform mat4 uMVPMatrix;\nattribute vec4 aPosition;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n}\n", "precision mediump float;\nuniform vec4 uColor;\nvoid main() {\n    gl_FragColor = uColor;\n}\n");
        this.vertexPositionHandle = this.getAttribHandle("aPosition");
        this.vertexMvpMatrixHandle = this.getUniformHandle("uMVPMatrix");
        this.fragmentColorHandle = this.getUniformHandle("uColor");
        this.color = new float[] { 1.0f, 1.0f, 1.0f, 1.0f };
    }
    
    public final float[] getColor() {
        return this.color;
    }
    
    public void onPostDraw(final GlDrawable glDrawable) {
        Intrinsics.checkNotNullParameter((Object)glDrawable, "drawable");
        super.onPostDraw(glDrawable);
        GLES20.glDisableVertexAttribArray(this.vertexPositionHandle.getUvalue-pVg5ArA$library_release());
    }
    
    public void onPreDraw(final GlDrawable glDrawable, final float[] array) {
        Intrinsics.checkNotNullParameter((Object)glDrawable, "drawable");
        Intrinsics.checkNotNullParameter((Object)array, "modelViewProjectionMatrix");
        super.onPreDraw(glDrawable, array);
        GLES20.glUniformMatrix4fv(this.vertexMvpMatrixHandle.getValue(), 1, false, array, 0);
        Egloo.checkGlError("glUniformMatrix4fv");
        GLES20.glUniform4fv(this.fragmentColorHandle.getValue(), 1, this.color, 0);
        Egloo.checkGlError("glUniform4fv");
        GLES20.glEnableVertexAttribArray(this.vertexPositionHandle.getUvalue-pVg5ArA$library_release());
        Egloo.checkGlError("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.vertexPositionHandle.getUvalue-pVg5ArA$library_release(), glDrawable.getCoordsPerVertex(), GlKt.getGL_FLOAT(), false, glDrawable.getVertexStride(), (Buffer)glDrawable.getVertexArray());
        Egloo.checkGlError("glVertexAttribPointer");
    }
    
    public final void setColor(final float[] color) {
        Intrinsics.checkNotNullParameter((Object)color, "<set-?>");
        this.color = color;
    }
}
