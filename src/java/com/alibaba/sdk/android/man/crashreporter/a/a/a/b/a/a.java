package com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a;

import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;

public class a<T> extends LinkedList<T>
{
    private final int max;
    
    public a(final int max) {
        this.max = max;
    }
    
    public void add(final int n, final T t) {
        throw new UnsupportedOperationException();
    }
    
    public boolean add(final T t) {
        if (this.size() == this.max) {
            this.removeFirst();
        }
        return super.add((Object)t);
    }
    
    public boolean addAll(final int n, final Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }
    
    public boolean addAll(final Collection<? extends T> collection) {
        final int n = this.size() + collection.size() - this.max;
        if (n > 0) {
            this.removeRange(0, n);
        }
        return super.addAll((Collection)collection);
    }
    
    public void addFirst(final T t) {
        throw new UnsupportedOperationException();
    }
    
    public void addLast(final T t) {
        throw new UnsupportedOperationException();
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toString());
        }
        return sb.toString();
    }
}
