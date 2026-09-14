package com.alipay.sdk.m.h;

public final class c extends b
{
    public final String f;
    
    public c(final String f) {
        this.f = f;
    }
    
    public void a() throws Exception {
        super.a = 1;
        final byte[] bytes = this.f.getBytes("UTF-8");
        super.c = bytes;
        super.b = (byte)bytes.length;
    }
}
