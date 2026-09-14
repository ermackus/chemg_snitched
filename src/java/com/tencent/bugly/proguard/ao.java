package com.tencent.bugly.proguard;

public final class ao extends k implements Cloneable
{
    public String a;
    private String b;
    
    public ao() {
        this.a = "";
        this.b = "";
    }
    
    public final void a(final i i) {
        this.a = i.b(0, true);
        this.b = i.b(1, true);
    }
    
    public final void a(final j j) {
        j.a(this.a, 0);
        j.a(this.b, 1);
    }
    
    public final void a(final StringBuilder sb, final int n) {
    }
}
