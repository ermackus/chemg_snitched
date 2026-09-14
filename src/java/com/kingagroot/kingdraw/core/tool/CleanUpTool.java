package com.kingagroot.kingdraw.core.tool;

import android.text.TextUtils;

public class CleanUpTool
{
    private String paletteId;
    
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    public CleanUpTool(final String paletteId) {
        this.paletteId = paletteId;
    }
    
    private static native boolean cleanUp(final String p0);
    
    public boolean cleanUp() {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && cleanUp(this.paletteId);
    }
}
