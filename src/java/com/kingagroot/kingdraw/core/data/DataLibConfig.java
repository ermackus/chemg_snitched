package com.kingagroot.kingdraw.core.data;

public class DataLibConfig
{
    static {
        System.loadLibrary("kingdrawCore-data");
    }
    
    public static native void InitConfig(final String p0, final String p1);
    
    public static native void cleanCachePath();
}
