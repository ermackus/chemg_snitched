package com.alibaba.sdk.android.man.crashreporter.a.b;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.HashMap;
import android.os.Looper;
import java.util.Map;

public class a extends Error
{
    private static final long serialVersionUID = 1L;
    private final Map<Thread, StackTraceElement[]> b;
    
    private a(final a a, final Map<Thread, StackTraceElement[]> b) {
        super("Application Not Responding", (Throwable)a);
        this.b = b;
    }
    
    public static a a() {
        final Thread thread = Looper.getMainLooper().getThread();
        final StackTraceElement[] stackTrace = thread.getStackTrace();
        final HashMap hashMap = new HashMap(1);
        hashMap.put((Object)thread, (Object)stackTrace);
        final a a = new a(thread.getName(), stackTrace);
        a.getClass();
        return new a(new a((a)null), (Map<Thread, StackTraceElement[]>)hashMap);
    }
    
    public static a a(final String s, final boolean b) {
        final Thread thread = Looper.getMainLooper().getThread();
        final TreeMap treeMap = new TreeMap((Comparator)new Comparator<Thread>(thread) {
            final Thread a;
            
            public int a(final Thread thread, final Thread thread2) {
                if (thread == thread2) {
                    return 0;
                }
                final Thread a = this.a;
                if (thread == a) {
                    return 1;
                }
                if (thread2 == a) {
                    return -1;
                }
                return thread2.getName().compareTo(thread.getName());
            }
        });
        for (final Map$Entry map$Entry : Thread.getAllStackTraces().entrySet()) {
            if (map$Entry.getKey() == thread || (((Thread)map$Entry.getKey()).getName().startsWith(s) && (b || ((StackTraceElement[])map$Entry.getValue()).length > 0))) {
                ((Map)treeMap).put(map$Entry.getKey(), map$Entry.getValue());
            }
        }
        final Iterator iterator2 = ((Map)treeMap).entrySet().iterator();
        a a = null;
        while (iterator2.hasNext()) {
            final Map$Entry map$Entry2 = (Map$Entry)iterator2.next();
            final a a2 = new a(((Thread)map$Entry2.getKey()).getName(), (StackTraceElement[])map$Entry2.getValue());
            a2.getClass();
            a = new a(a);
        }
        return new a(a, (Map<Thread, StackTraceElement[]>)treeMap);
    }
    
    public static Map<Thread, StackTraceElement[]> d() {
        final Thread currentThread = Thread.currentThread();
        if (currentThread == null) {
            return null;
        }
        final HashMap hashMap = new HashMap(1);
        final StackTraceElement[] stackTrace = currentThread.getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        hashMap.put((Object)currentThread, (Object)stackTrace);
        return (Map<Thread, StackTraceElement[]>)hashMap;
    }
    
    public static Map<Thread, StackTraceElement[]> e() {
        final Thread thread = Looper.getMainLooper().getThread();
        if (thread == null) {
            return null;
        }
        final HashMap hashMap = new HashMap(1);
        final StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        hashMap.put((Object)thread, (Object)stackTrace);
        return (Map<Thread, StackTraceElement[]>)hashMap;
    }
    
    public Map<Thread, StackTraceElement[]> c() {
        return this.b;
    }
    
    public Throwable fillInStackTrace() {
        this.setStackTrace(new StackTraceElement[0]);
        return (Throwable)this;
    }
    
    private static class a
    {
        private final StackTraceElement[] a;
        private final String r;
        
        private a(final String r, final StackTraceElement[] a) {
            this.r = r;
            this.a = a;
        }
        
        private class a extends Throwable
        {
            final com.alibaba.sdk.android.man.crashreporter.a.b.a.a a;
            
            private a(final com.alibaba.sdk.android.man.crashreporter.a.b.a.a a, final a a2) {
                this.a = a;
                super(a.r, (Throwable)a2);
            }
            
            public Throwable fillInStackTrace() {
                this.setStackTrace(this.a.a);
                return this;
            }
        }
    }
}
