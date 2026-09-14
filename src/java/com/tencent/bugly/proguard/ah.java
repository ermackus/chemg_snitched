package com.tencent.bugly.proguard;

public final class ah extends k implements Cloneable
{
    public String a;
    public String b;
    public String c;
    private String d;
    private String e;
    
    public ah() {
        this.a = "";
        this.d = "";
        this.b = "";
        this.e = "";
        this.c = "";
    }
    
    public final void a(final i i) {
        this.a = i.b(0, true);
        this.d = i.b(1, false);
        this.b = i.b(2, false);
        this.e = i.b(3, false);
        this.c = i.b(4, false);
    }
    
    public final void a(final j j) {
        j.a(this.a, 0);
        final String d = this.d;
        if (d != null) {
            j.a(d, 1);
        }
        final String b = this.b;
        if (b != null) {
            j.a(b, 2);
        }
        final String e = this.e;
        if (e != null) {
            j.a(e, 3);
        }
        final String c = this.c;
        if (c != null) {
            j.a(c, 4);
        }
    }
    
    public final void a(final StringBuilder sb, final int n) {
    }
}
