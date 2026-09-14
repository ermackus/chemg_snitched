package com.otaliastudios.opengl.program;

import com.otaliastudios.opengl.types.BuffersKt;
import java.nio.Buffer;
import com.otaliastudios.opengl.internal.GlKt;
import android.opengl.GLES20;
import com.otaliastudios.opengl.draw.GlDrawable;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import com.otaliastudios.opengl.internal.MiscKt;
import com.otaliastudios.opengl.core.Egloo;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.nio.FloatBuffer;
import com.otaliastudios.opengl.texture.GlTexture;
import android.graphics.RectF;
import com.otaliastudios.opengl.draw.Gl2dDrawable;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 82\u00020\u0001:\u00018BG\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tB;\b\u0017\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fB;\b\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000fJ8\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020+2\u0006\u00100\u001a\u00020+2\u0006\u00101\u001a\u00020\u000eH\u0014J\u0010\u00102\u001a\u0002032\u0006\u0010-\u001a\u000204H\u0016J\u0018\u00105\u001a\u0002032\u0006\u0010-\u001a\u0002042\u0006\u00106\u001a\u00020\"H\u0016J\b\u00107\u001a\u000203H\u0016R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00060\u0013j\u0002`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0012\u0010\u001c\u001a\u00060\u001dj\u0002`\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069" }, d2 = { "Lcom/otaliastudios/opengl/program/GlTextureProgram;", "Lcom/otaliastudios/opengl/program/GlProgram;", "vertexShader", "", "fragmentShader", "vertexPositionName", "vertexMvpMatrixName", "textureCoordsName", "textureTransformName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "handle", "", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "ownsHandle", "", "(IZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "lastDrawable", "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "lastDrawableBounds", "Landroid/graphics/RectF;", "Lcom/otaliastudios/opengl/geometry/RectF;", "lastDrawableVersion", "texture", "Lcom/otaliastudios/opengl/texture/GlTexture;", "getTexture", "()Lcom/otaliastudios/opengl/texture/GlTexture;", "setTexture", "(Lcom/otaliastudios/opengl/texture/GlTexture;)V", "textureCoordsBuffer", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "textureCoordsHandle", "Lcom/otaliastudios/opengl/program/GlProgramLocation;", "textureTransform", "", "getTextureTransform", "()[F", "setTextureTransform", "([F)V", "textureTransformHandle", "vertexMvpMatrixHandle", "vertexPositionHandle", "computeTextureCoordinate", "", "vertex", "drawable", "value", "min", "max", "horizontal", "onPostDraw", "", "Lcom/otaliastudios/opengl/draw/GlDrawable;", "onPreDraw", "modelViewProjectionMatrix", "release", "Companion", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class GlTextureProgram extends GlProgram
{
    public static final GlTextureProgram.GlTextureProgram$Companion Companion;
    public static final String SIMPLE_FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    public static final String SIMPLE_VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n";
    private Gl2dDrawable lastDrawable;
    private final RectF lastDrawableBounds;
    private int lastDrawableVersion;
    private GlTexture texture;
    private FloatBuffer textureCoordsBuffer;
    private final GlProgramLocation textureCoordsHandle;
    private float[] textureTransform;
    private final GlProgramLocation textureTransformHandle;
    private final GlProgramLocation vertexMvpMatrixHandle;
    private final GlProgramLocation vertexPositionHandle;
    
    static {
        Companion = new GlTextureProgram.GlTextureProgram$Companion((DefaultConstructorMarker)null);
    }
    
    public GlTextureProgram() {
        this(null, null, null, null, null, null, 63, null);
    }
    
    public GlTextureProgram(final int n) {
        this(n, null, null, null, null, 30, null);
    }
    
    public GlTextureProgram(final int n, final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexPositionName");
        this(n, s, null, null, null, 28, null);
    }
    
    public GlTextureProgram(final int n, final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexPositionName");
        Intrinsics.checkNotNullParameter((Object)s2, "vertexMvpMatrixName");
        this(n, s, s2, null, null, 24, null);
    }
    
    public GlTextureProgram(final int n, final String s, final String s2, final String s3) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexPositionName");
        Intrinsics.checkNotNullParameter((Object)s2, "vertexMvpMatrixName");
        this(n, s, s2, s3, null, 16, null);
    }
    
    public GlTextureProgram(final int n, final String s, final String s2, final String s3, final String s4) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexPositionName");
        Intrinsics.checkNotNullParameter((Object)s2, "vertexMvpMatrixName");
        this(n, false, s, s2, s3, s4);
    }
    
    protected GlTextureProgram(final int n, final boolean b, final String s, final String s2, final String s3, final String s4) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexPositionName");
        Intrinsics.checkNotNullParameter((Object)s2, "vertexMvpMatrixName");
        super(n, b, new GlShader[0]);
        this.textureTransform = MiscKt.matrixClone(Egloo.IDENTITY_MATRIX);
        final GlProgramLocation glProgramLocation = null;
        GlProgramLocation uniformHandle;
        if (s4 == null) {
            uniformHandle = null;
        }
        else {
            uniformHandle = this.getUniformHandle(s4);
        }
        this.textureTransformHandle = uniformHandle;
        this.textureCoordsBuffer = BuffersJvmKt.floatBuffer(8);
        GlProgramLocation attribHandle;
        if (s3 == null) {
            attribHandle = glProgramLocation;
        }
        else {
            attribHandle = this.getAttribHandle(s3);
        }
        this.textureCoordsHandle = attribHandle;
        this.vertexPositionHandle = this.getAttribHandle(s);
        this.vertexMvpMatrixHandle = this.getUniformHandle(s2);
        this.lastDrawableBounds = new RectF();
        this.lastDrawableVersion = -1;
    }
    
    public GlTextureProgram(final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexShader");
        this(s, null, null, null, null, null, 62, null);
    }
    
    public GlTextureProgram(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexShader");
        Intrinsics.checkNotNullParameter((Object)s2, "fragmentShader");
        this(s, s2, null, null, null, null, 60, null);
    }
    
    public GlTextureProgram(final String s, final String s2, final String s3) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexShader");
        Intrinsics.checkNotNullParameter((Object)s2, "fragmentShader");
        Intrinsics.checkNotNullParameter((Object)s3, "vertexPositionName");
        this(s, s2, s3, null, null, null, 56, null);
    }
    
    public GlTextureProgram(final String s, final String s2, final String s3, final String s4) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexShader");
        Intrinsics.checkNotNullParameter((Object)s2, "fragmentShader");
        Intrinsics.checkNotNullParameter((Object)s3, "vertexPositionName");
        Intrinsics.checkNotNullParameter((Object)s4, "vertexMvpMatrixName");
        this(s, s2, s3, s4, null, null, 48, null);
    }
    
    public GlTextureProgram(final String s, final String s2, final String s3, final String s4, final String s5) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexShader");
        Intrinsics.checkNotNullParameter((Object)s2, "fragmentShader");
        Intrinsics.checkNotNullParameter((Object)s3, "vertexPositionName");
        Intrinsics.checkNotNullParameter((Object)s4, "vertexMvpMatrixName");
        this(s, s2, s3, s4, s5, null, 32, null);
    }
    
    public GlTextureProgram(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Intrinsics.checkNotNullParameter((Object)s, "vertexShader");
        Intrinsics.checkNotNullParameter((Object)s2, "fragmentShader");
        Intrinsics.checkNotNullParameter((Object)s3, "vertexPositionName");
        Intrinsics.checkNotNullParameter((Object)s4, "vertexMvpMatrixName");
        this(GlProgram.Companion.create(s, s2), true, s3, s4, s5, s6);
    }
    
    protected float computeTextureCoordinate(final int n, final Gl2dDrawable gl2dDrawable, final float n2, final float n3, final float n4, final boolean b) {
        Intrinsics.checkNotNullParameter((Object)gl2dDrawable, "drawable");
        return (n2 - n3) / (n4 - n3) * 1.0f + 0.0f;
    }
    
    public final GlTexture getTexture() {
        return this.texture;
    }
    
    public final float[] getTextureTransform() {
        return this.textureTransform;
    }
    
    public void onPostDraw(final GlDrawable glDrawable) {
        Intrinsics.checkNotNullParameter((Object)glDrawable, "drawable");
        super.onPostDraw(glDrawable);
        GLES20.glDisableVertexAttribArray(this.vertexPositionHandle.getUvalue-pVg5ArA$library_release());
        final GlProgramLocation textureCoordsHandle = this.textureCoordsHandle;
        if (textureCoordsHandle != null) {
            GLES20.glDisableVertexAttribArray(textureCoordsHandle.getUvalue-pVg5ArA$library_release());
        }
        final GlTexture texture = this.texture;
        if (texture != null) {
            texture.unbind();
        }
        Egloo.checkGlError("onPostDraw end");
    }
    
    public void onPreDraw(final GlDrawable glDrawable, final float[] array) {
        Intrinsics.checkNotNullParameter((Object)glDrawable, "drawable");
        Intrinsics.checkNotNullParameter((Object)array, "modelViewProjectionMatrix");
        super.onPreDraw(glDrawable, array);
        if (glDrawable instanceof Gl2dDrawable) {
            final GlTexture texture = this.texture;
            if (texture != null) {
                texture.bind();
            }
            GLES20.glUniformMatrix4fv(this.vertexMvpMatrixHandle.getValue(), 1, false, array, 0);
            Egloo.checkGlError("glUniformMatrix4fv");
            final GlProgramLocation textureTransformHandle = this.textureTransformHandle;
            if (textureTransformHandle != null) {
                GLES20.glUniformMatrix4fv(textureTransformHandle.getValue(), 1, false, this.getTextureTransform(), 0);
                Egloo.checkGlError("glUniformMatrix4fv");
            }
            final GlProgramLocation vertexPositionHandle = this.vertexPositionHandle;
            GLES20.glEnableVertexAttribArray(vertexPositionHandle.getUvalue-pVg5ArA$library_release());
            Egloo.checkGlError("glEnableVertexAttribArray");
            GLES20.glVertexAttribPointer(vertexPositionHandle.getUvalue-pVg5ArA$library_release(), 2, GlKt.getGL_FLOAT(), false, glDrawable.getVertexStride(), (Buffer)glDrawable.getVertexArray());
            Egloo.checkGlError("glVertexAttribPointer");
            final GlProgramLocation textureCoordsHandle = this.textureCoordsHandle;
            if (textureCoordsHandle != null) {
                if (!Intrinsics.areEqual((Object)glDrawable, (Object)this.lastDrawable) || glDrawable.getVertexArrayVersion() != this.lastDrawableVersion) {
                    final Gl2dDrawable lastDrawable = (Gl2dDrawable)glDrawable;
                    this.lastDrawable = lastDrawable;
                    this.lastDrawableVersion = glDrawable.getVertexArrayVersion();
                    lastDrawable.getBounds(this.lastDrawableBounds);
                    final int n = glDrawable.getVertexCount() * 2;
                    if (this.textureCoordsBuffer.capacity() < n) {
                        BuffersKt.dispose((Buffer)this.textureCoordsBuffer);
                        this.textureCoordsBuffer = BuffersJvmKt.floatBuffer(n);
                    }
                    this.textureCoordsBuffer.clear();
                    this.textureCoordsBuffer.limit(n);
                    if (n > 0) {
                        int n2 = 0;
                        while (true) {
                            final int n3 = n2 + 1;
                            final boolean b = n2 % 2 == 0;
                            final float value = glDrawable.getVertexArray().get(n2);
                            final RectF lastDrawableBounds = this.lastDrawableBounds;
                            float n4;
                            if (b) {
                                n4 = lastDrawableBounds.left;
                            }
                            else {
                                n4 = lastDrawableBounds.bottom;
                            }
                            final RectF lastDrawableBounds2 = this.lastDrawableBounds;
                            float n5;
                            if (b) {
                                n5 = lastDrawableBounds2.right;
                            }
                            else {
                                n5 = lastDrawableBounds2.top;
                            }
                            this.textureCoordsBuffer.put(this.computeTextureCoordinate(n2 / 2, lastDrawable, value, n4, n5, b));
                            if (n3 >= n) {
                                break;
                            }
                            n2 = n3;
                        }
                    }
                }
                this.textureCoordsBuffer.rewind();
                GLES20.glEnableVertexAttribArray(textureCoordsHandle.getUvalue-pVg5ArA$library_release());
                Egloo.checkGlError("glEnableVertexAttribArray");
                GLES20.glVertexAttribPointer(textureCoordsHandle.getUvalue-pVg5ArA$library_release(), 2, GlKt.getGL_FLOAT(), false, glDrawable.getVertexStride(), (Buffer)this.textureCoordsBuffer);
                Egloo.checkGlError("glVertexAttribPointer");
            }
            return;
        }
        throw new RuntimeException("GlTextureProgram only supports 2D drawables.");
    }
    
    public void release() {
        super.release();
        BuffersKt.dispose((Buffer)this.textureCoordsBuffer);
        final GlTexture texture = this.texture;
        if (texture != null) {
            texture.release();
        }
        this.texture = null;
    }
    
    public final void setTexture(final GlTexture texture) {
        this.texture = texture;
    }
    
    public final void setTextureTransform(final float[] textureTransform) {
        Intrinsics.checkNotNullParameter((Object)textureTransform, "<set-?>");
        this.textureTransform = textureTransform;
    }
}
