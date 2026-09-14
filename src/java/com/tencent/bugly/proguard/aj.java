package com.tencent.bugly.proguard;

public final class aj extends k implements Cloneable
{
    private static byte[] d;
    private byte a;
    private String b;
    private byte[] c;
    
    public aj() {
        this.a = 0;
        this.b = "";
        this.c = null;
    }
    
    public aj(final byte a, final String b, final byte[] c) {
        this.a = 0;
        this.b = "";
        this.c = null;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final void a(final i i) {
        this.a = i.a(this.a, 0, true);
        this.b = i.b(1, true);
        if (aj.d == null) {
            (aj.d = new byte[1])[0] = 0;
        }
        this.c = i.c(2, false);
    }
    
    public final void a(final j j) {
        j.a(this.a, 0);
        j.a(this.b, 1);
        final byte[] c = this.c;
        if (c != null) {
            j.a(c, 2);
        }
    }
    
    public final void a(final StringBuilder sb, final int n) {
    }
}
