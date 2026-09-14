package com.tencent.open.log;

public abstract class Tracer
{
    private volatile int a;
    private volatile boolean b;
    private g c;
    
    public Tracer() {
        this(com.tencent.open.log.c.a, true, g.a);
    }
    
    public Tracer(final int n, final boolean b, final g g) {
        this.a = com.tencent.open.log.c.a;
        this.b = true;
        this.c = g.a;
        this.a(n);
        this.a(b);
        this.a(g);
    }
    
    public void a(final int a) {
        this.a = a;
    }
    
    public void a(final int n, final Thread thread, final long n2, final String s, final String s2, final Throwable t) {
        if (this.d() && d.a.a(this.a, n)) {
            this.doTrace(n, thread, n2, s, s2, t);
        }
    }
    
    public void a(final g c) {
        this.c = c;
    }
    
    public void a(final boolean b) {
        this.b = b;
    }
    
    public boolean d() {
        return this.b;
    }
    
    protected abstract void doTrace(final int p0, final Thread p1, final long p2, final String p3, final String p4, final Throwable p5);
    
    public g e() {
        return this.c;
    }
}
