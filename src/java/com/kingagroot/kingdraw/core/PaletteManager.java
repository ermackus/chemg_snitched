package com.kingagroot.kingdraw.core;

import com.kingagroot.kingdraw.core.model.PaletteConfigModel;
import com.kingagroot.kingdraw.core.graphics.CanvasController;

public class PaletteManager
{
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    public static native String createPalette(final CanvasController p0, final String p1, final PaletteConfigModel p2);
    
    public static native void deletePalette(final String p0);
    
    public static native boolean stickPalette(final String p0);
}
