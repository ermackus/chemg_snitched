package com.tencent.bugly.proguard;

public class b extends RuntimeException
{
    public b(final Exception ex) {
        super((Throwable)ex);
    }
    
    public b(final String s) {
        super(s);
    }
}
