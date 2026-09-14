package com.kingagroot.kingdraw.utils;

public class GMath
{
    static {
        System.loadLibrary("gmath-lib");
    }
    
    public static native String key();
}
