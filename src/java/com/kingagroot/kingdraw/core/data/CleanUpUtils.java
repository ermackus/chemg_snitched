package com.kingagroot.kingdraw.core.data;

public class CleanUpUtils
{
    static {
        System.loadLibrary("kingdrawCore-data");
    }
    
    public static native String cleanUp(final String p0, final float p1, final float p2);
}
