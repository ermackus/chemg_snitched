package com.alibaba.mtl.appmonitor.c;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class c<T extends b>
{
    private static AtomicLong c;
    private static AtomicLong d;
    private ConcurrentLinkedQueue<T> a;
    private AtomicLong a;
    private Integer b;
    private Set<Integer> b;
    private AtomicLong b;
    private final int m;
    
    static {
        com.alibaba.mtl.appmonitor.c.c.c = new AtomicLong(0L);
        com.alibaba.mtl.appmonitor.c.c.d = new AtomicLong(0L);
    }
    
    public c() {
        this.m = 20;
        this.b = null;
        this.a = new AtomicLong(0L);
        this.b = new AtomicLong(0L);
        this.a = (ConcurrentLinkedQueue<T>)new ConcurrentLinkedQueue();
        this.b = (Set<Integer>)new HashSet();
    }
    
    public T a() {
        com.alibaba.mtl.appmonitor.c.c.c.getAndIncrement();
        this.a.getAndIncrement();
        final b b = (b)this.a.poll();
        if (b != null) {
            this.b.remove((Object)System.identityHashCode((Object)b));
            this.b.getAndIncrement();
            com.alibaba.mtl.appmonitor.c.c.d.getAndIncrement();
        }
        return (T)b;
    }
    
    public void a(final T t) {
        t.clean();
        if (this.a.size() < 20) {
            final Set<Integer> b = this.b;
            synchronized (b) {
                final int identityHashCode = System.identityHashCode((Object)t);
                if (!this.b.contains((Object)identityHashCode)) {
                    this.b.add((Object)identityHashCode);
                    this.a.offer((Object)t);
                }
            }
        }
    }
}
