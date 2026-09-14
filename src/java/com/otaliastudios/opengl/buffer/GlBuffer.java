package com.otaliastudios.opengl.buffer;

import kotlin.jvm.internal.DefaultConstructorMarker;
import com.otaliastudios.opengl.core.Egloo;
import kotlin.UInt;
import kotlin.Unit;
import android.opengl.GLES20;
import kotlin.UIntArray;
import kotlin.Metadata;
import com.otaliastudios.opengl.core.GlBindable;

@Metadata(d1 = { "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u0006\u0010\u000b\u001a\u00020\nJ\b\u0010\f\u001a\u00020\nH\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\r" }, d2 = { "Lcom/otaliastudios/opengl/buffer/GlBuffer;", "Lcom/otaliastudios/opengl/core/GlBindable;", "target", "", "id", "(ILjava/lang/Integer;)V", "getId", "()I", "getTarget", "bind", "", "release", "unbind", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public class GlBuffer implements GlBindable
{
    private final int id;
    private final int target;
    
    public GlBuffer(int i, final Integer n) {
        this.target = i;
        if (n == null) {
            final GlBuffer glBuffer = this;
            final int[] constructor-impl = UIntArray.constructor-impl(1);
            final int size-impl = UIntArray.getSize-impl(constructor-impl);
            final int[] array = new int[size-impl];
            for (i = 0; i < size-impl; ++i) {
                array[i] = UIntArray.get-pVg5ArA(constructor-impl, i);
            }
            GLES20.glGenBuffers(1, array, 0);
            final Unit instance = Unit.INSTANCE;
            UIntArray.set-VXSXFK8(constructor-impl, 0, UInt.constructor-impl(array[0]));
            Egloo.checkGlError("glGenBuffers");
            i = UIntArray.get-pVg5ArA(constructor-impl, 0);
        }
        else {
            i = n;
        }
        this.id = i;
    }
    
    public void bind() {
        GLES20.glBindBuffer(UInt.constructor-impl(this.target), UInt.constructor-impl(this.id));
    }
    
    public final int getId() {
        return this.id;
    }
    
    public final int getTarget() {
        return this.target;
    }
    
    public final void release() {
        final int[] array = { UInt.constructor-impl(this.id) };
        final int size-impl = UIntArray.getSize-impl(array);
        final int[] array2 = new int[size-impl];
        for (int i = 0; i < size-impl; ++i) {
            array2[i] = UIntArray.get-pVg5ArA(array, i);
        }
        GLES20.glDeleteBuffers(1, array2, 0);
        final Unit instance = Unit.INSTANCE;
        UIntArray.set-VXSXFK8(array, 0, UInt.constructor-impl(array2[0]));
    }
    
    public void unbind() {
        GLES20.glBindBuffer(UInt.constructor-impl(this.target), 0);
    }
}
