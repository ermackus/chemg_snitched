package com.otaliastudios.opengl.core;

import kotlin.jvm.internal.Intrinsics;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\u0010\u0007\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¨\u0006\b" }, d2 = { "use", "", "bindables", "", "Lcom/otaliastudios/opengl/core/GlBindable;", "block", "Lkotlin/Function0;", "([Lcom/otaliastudios/opengl/core/GlBindable;Lkotlin/jvm/functions/Function0;)V", "library_release" }, k = 2, mv = { 1, 5, 1 }, xi = 48)
public final class GlBindableKt
{
    public static final void use(final GlBindable glBindable, final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter((Object)glBindable, "<this>");
        Intrinsics.checkNotNullParameter((Object)function0, "block");
        glBindable.bind();
        function0.invoke();
        glBindable.unbind();
    }
    
    public static final void use(final GlBindable[] array, final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter((Object)array, "bindables");
        Intrinsics.checkNotNullParameter((Object)function0, "block");
        final int length = array.length;
        final int n = 0;
        for (int i = 0; i < length; ++i) {
            array[i].bind();
        }
        function0.invoke();
        for (int length2 = array.length, j = n; j < length2; ++j) {
            array[j].unbind();
        }
    }
}
