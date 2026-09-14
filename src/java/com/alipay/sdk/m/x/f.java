package com.alipay.sdk.m.x;

import java.util.Iterator;
import java.util.Stack;

public class f
{
    public Stack<e> a;
    
    public f() {
        this.a = (Stack<e>)new Stack();
    }
    
    public void a() {
        if (this.b()) {
            return;
        }
        final Iterator iterator = this.a.iterator();
        while (iterator.hasNext()) {
            ((e)iterator.next()).a();
        }
        this.a.clear();
    }
    
    public void a(final e e) {
        this.a.push((Object)e);
    }
    
    public boolean b() {
        return this.a.isEmpty();
    }
    
    public e c() {
        return (e)this.a.pop();
    }
}
