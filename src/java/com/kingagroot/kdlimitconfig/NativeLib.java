package com.kingagroot.kdlimitconfig;

public class NativeLib
{
    static {
        System.loadLibrary("kdlimitconfig");
    }
    
    public static native LimitModel decryptLimitData(final String p0, final long p1);
    
    public static native ResultModel encryptionLimit(final LimitModel p0);
    
    public native String stringFromJNI();
}
