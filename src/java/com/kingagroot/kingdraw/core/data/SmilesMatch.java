package com.kingagroot.kingdraw.core.data;

public class SmilesMatch
{
    static {
        System.loadLibrary("kingdrawCore-data");
    }
    
    public static native boolean matchSmiles(final String p0);
    
    public static native void setQuerySmiles(final String p0);
}
