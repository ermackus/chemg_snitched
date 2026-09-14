package com.otaliastudios.opengl.texture;

import com.otaliastudios.opengl.core.GlBindableKt;
import kotlin.jvm.functions.Function0;
import com.otaliastudios.opengl.core.Egloo;
import kotlin.UInt;
import kotlin.Unit;
import android.opengl.GLES20;
import kotlin.UIntArray;
import com.otaliastudios.opengl.internal.GlKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Metadata;
import com.otaliastudios.opengl.core.GlBindable;

@Metadata(d1 = { "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B'\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006BE\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fBS\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0006\u0010\u001a\u001a\u00020\u0019J\b\u0010\u001b\u001a\u00020\u0019H\u0016R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0017\u0010\u000f¨\u0006\u001c" }, d2 = { "Lcom/otaliastudios/opengl/texture/GlTexture;", "Lcom/otaliastudios/opengl/core/GlBindable;", "unit", "", "target", "id", "(IILjava/lang/Integer;)V", "width", "height", "format", "internalFormat", "type", "(IIIIIII)V", "(IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getFormat", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHeight", "getId", "()I", "getTarget", "getType", "getUnit", "getWidth", "bind", "", "release", "unbind", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class GlTexture implements GlBindable
{
    private final Integer format;
    private final Integer height;
    private final int id;
    private final int target;
    private final Integer type;
    private final int unit;
    private final Integer width;
    
    public GlTexture() {
        this(0, 0, null, 7, null);
    }
    
    public GlTexture(final int n) {
        this(n, 0, null, 6, null);
    }
    
    public GlTexture(final int n, final int n2) {
        this(n, n2, null, 4, null);
    }
    
    public GlTexture(final int n, final int n2, final int n3, final int n4) {
        this(n, n2, n3, n4, 0, 0, 0, 112, null);
    }
    
    public GlTexture(final int n, final int n2, final int n3, final int n4, final int n5) {
        this(n, n2, n3, n4, n5, 0, 0, 96, null);
    }
    
    public GlTexture(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this(n, n2, n3, n4, n5, n6, 0, 64, null);
    }
    
    public GlTexture(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this(n, n2, null, n3, n4, n5, n6, n7);
    }
    
    public GlTexture(final int n, final int n2, final Integer n3) {
        this(n, n2, n3, null, null, null, null, null);
    }
    
    private GlTexture(int i, int size-impl, final Integer n, final Integer width, final Integer height, final Integer format, final Integer n2, final Integer type) {
        this.unit = i;
        this.target = size-impl;
        this.width = width;
        this.height = height;
        this.format = format;
        this.type = type;
        if (n == null) {
            final GlTexture glTexture = this;
            final int[] constructor-impl = UIntArray.constructor-impl(1);
            size-impl = UIntArray.getSize-impl(constructor-impl);
            final int[] array = new int[size-impl];
            for (i = 0; i < size-impl; ++i) {
                array[i] = UIntArray.get-pVg5ArA(constructor-impl, i);
            }
            GLES20.glGenTextures(1, array, 0);
            final Unit instance = Unit.INSTANCE;
            UIntArray.set-VXSXFK8(constructor-impl, 0, UInt.constructor-impl(array[0]));
            Egloo.checkGlError("glGenTextures");
            i = UIntArray.get-pVg5ArA(constructor-impl, 0);
        }
        else {
            i = n;
        }
        this.id = i;
        if (n == null) {
            GlBindableKt.use((GlBindable)this, (Function0)new GlTexture$1(this, n2));
        }
    }
    
    public void bind() {
        GLES20.glActiveTexture(UInt.constructor-impl(this.unit));
        GLES20.glBindTexture(UInt.constructor-impl(this.target), UInt.constructor-impl(this.id));
        Egloo.checkGlError("bind");
    }
    
    public final Integer getFormat() {
        return this.format;
    }
    
    public final Integer getHeight() {
        return this.height;
    }
    
    public final int getId() {
        return this.id;
    }
    
    public final int getTarget() {
        return this.target;
    }
    
    public final Integer getType() {
        return this.type;
    }
    
    public final int getUnit() {
        return this.unit;
    }
    
    public final Integer getWidth() {
        return this.width;
    }
    
    public final void release() {
        final int[] array = { UInt.constructor-impl(this.id) };
        final int size-impl = UIntArray.getSize-impl(array);
        final int[] array2 = new int[size-impl];
        for (int i = 0; i < size-impl; ++i) {
            array2[i] = UIntArray.get-pVg5ArA(array, i);
        }
        GLES20.glDeleteTextures(1, array2, 0);
        final Unit instance = Unit.INSTANCE;
        UIntArray.set-VXSXFK8(array, 0, UInt.constructor-impl(array2[0]));
    }
    
    public void unbind() {
        GLES20.glBindTexture(UInt.constructor-impl(this.target), UInt.constructor-impl(0));
        GLES20.glActiveTexture(GlKt.getGL_TEXTURE0());
        Egloo.checkGlError("unbind");
    }
}
