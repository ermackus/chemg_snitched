package com.kingagroot.kingdraw.core.graphics.svg.utils;

import android.graphics.Canvas;
import java.lang.reflect.Method;

public class CanvasLegacy
{
    public static final int MATRIX_SAVE_FLAG;
    private static final Method SAVE;
    
    static {
        try {
            MATRIX_SAVE_FLAG = (int)Canvas.class.getField("MATRIX_SAVE_FLAG").get((Object)null);
            SAVE = Canvas.class.getMethod("save", Integer.TYPE);
        }
        finally {
            final Throwable t;
            throw sneakyThrow(t);
        }
    }
    
    public static void save(final Canvas canvas, final int n) {
        try {
            CanvasLegacy.SAVE.invoke((Object)canvas, new Object[] { n });
        }
        finally {
            final Throwable t;
            throw sneakyThrow(t);
        }
    }
    
    private static RuntimeException sneakyThrow(final Throwable t) {
        if (t != null) {
            return sneakyThrow0(t);
        }
        throw new NullPointerException("t");
    }
    
    private static <T extends Throwable> T sneakyThrow0(final Throwable t) throws T, Throwable {
        throw t;
    }
}
