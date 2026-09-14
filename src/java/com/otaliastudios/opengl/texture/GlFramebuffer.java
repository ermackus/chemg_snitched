package com.otaliastudios.opengl.texture;

import com.otaliastudios.opengl.core.GlBindableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.core.Egloo;
import kotlin.UInt;
import kotlin.Unit;
import android.opengl.GLES20;
import kotlin.UIntArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Metadata;
import com.otaliastudios.opengl.core.GlBindable;

@Metadata(d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0003H\u0007J\b\u0010\f\u001a\u00020\bH\u0016J\u0006\u0010\r\u001a\u00020\bJ\b\u0010\u000e\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f" }, d2 = { "Lcom/otaliastudios/opengl/texture/GlFramebuffer;", "Lcom/otaliastudios/opengl/core/GlBindable;", "id", "", "(Ljava/lang/Integer;)V", "getId", "()I", "attach", "", "texture", "Lcom/otaliastudios/opengl/texture/GlTexture;", "attachment", "bind", "release", "unbind", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class GlFramebuffer implements GlBindable
{
    private final int id;
    
    public GlFramebuffer() {
        this(null, 1, null);
    }
    
    public GlFramebuffer(final Integer n) {
        int id;
        if (n == null) {
            final GlFramebuffer glFramebuffer = this;
            final int[] constructor-impl = UIntArray.constructor-impl(1);
            final int size-impl = UIntArray.getSize-impl(constructor-impl);
            final int[] array = new int[size-impl];
            for (int i = 0; i < size-impl; ++i) {
                array[i] = UIntArray.get-pVg5ArA(constructor-impl, i);
            }
            GLES20.glGenFramebuffers(1, array, 0);
            final Unit instance = Unit.INSTANCE;
            UIntArray.set-VXSXFK8(constructor-impl, 0, UInt.constructor-impl(array[0]));
            Egloo.checkGlError("glGenFramebuffers");
            id = UIntArray.get-pVg5ArA(constructor-impl, 0);
        }
        else {
            id = n;
        }
        this.id = id;
    }
    
    public static /* synthetic */ void attach$default(final GlFramebuffer glFramebuffer, final GlTexture glTexture, int gl_COLOR_ATTACHMENT0, final int n, final Object o) {
        if ((n & 0x2) != 0x0) {
            gl_COLOR_ATTACHMENT0 = GlKt.getGL_COLOR_ATTACHMENT0();
        }
        glFramebuffer.attach(glTexture, gl_COLOR_ATTACHMENT0);
    }
    
    public final void attach(final GlTexture glTexture) {
        Intrinsics.checkNotNullParameter((Object)glTexture, "texture");
        attach$default(this, glTexture, 0, 2, null);
    }
    
    public final void attach(final GlTexture glTexture, final int n) {
        Intrinsics.checkNotNullParameter((Object)glTexture, "texture");
        GlBindableKt.use((GlBindable)this, (Function0)new GlFramebuffer$attach$1(n, glTexture));
    }
    
    public void bind() {
        GLES20.glBindFramebuffer(GlKt.getGL_FRAMEBUFFER(), UInt.constructor-impl(this.id));
    }
    
    public final int getId() {
        return this.id;
    }
    
    public final void release() {
        final int[] array = { UInt.constructor-impl(this.id) };
        final int size-impl = UIntArray.getSize-impl(array);
        final int[] array2 = new int[size-impl];
        for (int i = 0; i < size-impl; ++i) {
            array2[i] = UIntArray.get-pVg5ArA(array, i);
        }
        GLES20.glDeleteFramebuffers(1, array2, 0);
        final Unit instance = Unit.INSTANCE;
        UIntArray.set-VXSXFK8(array, 0, UInt.constructor-impl(array2[0]));
    }
    
    public void unbind() {
        GLES20.glBindFramebuffer(GlKt.getGL_FRAMEBUFFER(), 0);
    }
}
