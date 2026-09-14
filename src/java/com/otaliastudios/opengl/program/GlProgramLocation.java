package com.otaliastudios.opengl.program;

import kotlin.jvm.internal.Intrinsics;
import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import kotlin.UInt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0002\u0012\u0013B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001f\u0010\u000b\u001a\u00020\fX\u0080\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0014" }, d2 = { "Lcom/otaliastudios/opengl/program/GlProgramLocation;", "", "program", "", "type", "Lcom/otaliastudios/opengl/program/GlProgramLocation$Type;", "name", "", "(ILcom/otaliastudios/opengl/program/GlProgramLocation$Type;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "uvalue", "Lkotlin/UInt;", "getUvalue-pVg5ArA$library_release", "()I", "I", "value", "getValue", "Companion", "Type", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class GlProgramLocation
{
    public static final Companion Companion;
    private final String name;
    private final int uvalue;
    private final int value;
    
    static {
        Companion = new Companion(null);
    }
    
    private GlProgramLocation(int value, final Type type, final String name) {
        this.name = name;
        final int n = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (n != 1) {
            if (n != 2) {
                throw new NoWhenBranchMatchedException();
            }
            value = GLES20.glGetUniformLocation(UInt.constructor-impl(value), this.name);
        }
        else {
            value = GLES20.glGetAttribLocation(UInt.constructor-impl(value), this.name);
        }
        Egloo.checkGlProgramLocation(this.value = value, this.name);
        this.uvalue = UInt.constructor-impl(this.value);
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final int getUvalue-pVg5ArA$library_release() {
        return this.uvalue;
    }
    
    public final int getValue() {
        return this.value;
    }
    
    @Metadata(d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\n" }, d2 = { "Lcom/otaliastudios/opengl/program/GlProgramLocation$Companion;", "", "()V", "getAttrib", "Lcom/otaliastudios/opengl/program/GlProgramLocation;", "program", "", "name", "", "getUniform", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
    public static final class Companion
    {
        private Companion() {
        }
        
        public final GlProgramLocation getAttrib(final int n, final String s) {
            Intrinsics.checkNotNullParameter((Object)s, "name");
            return new GlProgramLocation(n, Type.ATTRIB, s, null);
        }
        
        public final GlProgramLocation getUniform(final int n, final String s) {
            Intrinsics.checkNotNullParameter((Object)s, "name");
            return new GlProgramLocation(n, Type.UNIFORM, s, null);
        }
    }
    
    @Metadata(d1 = { "\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005" }, d2 = { "Lcom/otaliastudios/opengl/program/GlProgramLocation$Type;", "", "(Ljava/lang/String;I)V", "ATTRIB", "UNIFORM", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
    private enum Type
    {
        private static final Type[] $VALUES;
        
        ATTRIB, 
        UNIFORM;
        
        private static final /* synthetic */ Type[] $values() {
            return new Type[] { Type.ATTRIB, Type.UNIFORM };
        }
        
        static {
            $VALUES = $values();
        }
    }
}
