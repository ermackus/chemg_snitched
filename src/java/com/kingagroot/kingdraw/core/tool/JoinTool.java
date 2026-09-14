package com.kingagroot.kingdraw.core.tool;

public class JoinTool
{
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    private static native boolean join(final String p0);
    
    private static native boolean joinAvailability(final String p0);
}
