package com.otaliastudios.opengl.program;

import android.graphics.Color;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007" }, d2 = { "Lcom/otaliastudios/opengl/program/GlFlatProgram;", "Lcom/otaliastudios/opengl/program/GlNativeFlatProgram;", "()V", "setColor", "", "color", "", "library_release" }, k = 1, mv = { 1, 5, 1 }, xi = 48)
public final class GlFlatProgram extends GlNativeFlatProgram
{
    public final void setColor(final int n) {
        this.setColor(new float[] { Color.red(n) / 255.0f, Color.green(n) / 255.0f, Color.blue(n) / 255.0f, Color.alpha(n) / 255.0f });
    }
}
