package com.alibaba.mtl.log.a;

@Deprecated
public class b
{
    private static b a;
    
    static {
        b.a = new b();
    }
    
    public static b a() {
        return b.a;
    }
    
    public void q() {
    }
}
