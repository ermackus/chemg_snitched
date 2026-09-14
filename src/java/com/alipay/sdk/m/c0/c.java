package com.alipay.sdk.m.c0;

public final class c implements Runnable
{
    public final b a;
    
    public c(final b a) {
        this.a = a;
    }
    
    public final void run() {
        try {
            b.a(this.a);
        }
        catch (final Exception ex) {
            d.a((Throwable)ex);
        }
    }
}
