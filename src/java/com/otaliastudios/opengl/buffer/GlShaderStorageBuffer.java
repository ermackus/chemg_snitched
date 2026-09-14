package com.otaliastudios.opengl.buffer;

import kotlin.jvm.internal.Intrinsics;
import android.opengl.GLES30;
import com.otaliastudios.opengl.core.GlBindableKt;
import com.otaliastudios.opengl.core.Egloo;
import java.nio.Buffer;
import android.opengl.GLES20;
import kotlin.UInt;
import kotlin.jvm.internal.Lambda;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import com.otaliastudios.opengl.core.GlBindable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.otaliastudios.opengl.internal.GlKt;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003J\u001c\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000f" }, d2 = { "Lcom/otaliastudios/opengl/buffer/GlShaderStorageBuffer;", "Lcom/otaliastudios/opengl/buffer/GlBuffer;", "size", "", "usage", "(II)V", "getSize", "()I", "getUsage", "bind", "", "index", "use", "block", "Lkotlin/Function0;", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class GlShaderStorageBuffer extends GlBuffer
{
    private final int size;
    private final int usage;
    
    public GlShaderStorageBuffer(final int size, final int usage) {
        super(GlKt.getGL_SHADER_STORAGE_BUFFER(), (Integer)null, 2, (DefaultConstructorMarker)null);
        this.size = size;
        this.usage = usage;
        GlBindableKt.use((GlBindable)this, (Function0)new Function0<Unit>(this) {
            final GlShaderStorageBuffer this$0;
            
            public final void invoke() {
                GLES20.glBufferData(UInt.constructor-impl(this.this$0.getTarget()), this.this$0.getSize(), (Buffer)null, UInt.constructor-impl(this.this$0.getUsage()));
                Egloo.checkGlError("glBufferData");
            }
        });
    }
    
    public final void bind(final int n) {
        GLES30.glBindBufferBase(UInt.constructor-impl(this.getTarget()), UInt.constructor-impl(n), UInt.constructor-impl(this.getId()));
        Egloo.checkGlError("glBindBufferBase");
    }
    
    public final int getSize() {
        return this.size;
    }
    
    public final int getUsage() {
        return this.usage;
    }
    
    public final void use(final int n, final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter((Object)function0, "block");
        this.bind(n);
        function0.invoke();
        this.unbind();
    }
}
