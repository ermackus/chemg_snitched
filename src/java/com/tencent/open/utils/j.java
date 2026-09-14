package com.tencent.open.utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.RejectedExecutionException;
import android.os.HandlerThread;
import android.os.Handler;
import java.util.concurrent.Executor;

public final class j
{
    public static final Executor a;
    private static Object b;
    private static Handler c;
    private static HandlerThread d;
    
    static {
        j.b = new Object();
        a = c();
    }
    
    public static Handler a() {
        if (j.c == null) {
            synchronized (j.class) {
                (j.d = new HandlerThread("SDK_SUB")).start();
                j.c = new Handler(j.d.getLooper());
            }
        }
        return j.c;
    }
    
    public static void a(final Runnable runnable) {
        try {
            j.a.execute(runnable);
        }
        catch (final RejectedExecutionException ex) {}
    }
    
    public static Executor b() {
        return (Executor)new a();
    }
    
    public static void b(final Runnable runnable) {
        a().post(runnable);
    }
    
    private static Executor c() {
        return (Executor)new ThreadPoolExecutor(0, 3, 10L, TimeUnit.SECONDS, (BlockingQueue)new LinkedBlockingQueue());
    }
    
    private static class a implements Executor
    {
        final Queue<Runnable> a;
        Runnable b;
        
        private a() {
            this.a = (Queue<Runnable>)new LinkedList();
        }
        
        protected void a() {
            synchronized (this) {
                final Runnable b = (Runnable)this.a.poll();
                this.b = b;
                if (b != null) {
                    j.a.execute(this.b);
                }
            }
        }
        
        public void execute(final Runnable runnable) {
            synchronized (this) {
                this.a.offer((Object)new Runnable(this, runnable) {
                    final Runnable a;
                    final a b;
                    
                    public void run() {
                        try {
                            this.a.run();
                        }
                        finally {
                            this.b.a();
                        }
                    }
                });
                if (this.b == null) {
                    this.a();
                }
            }
        }
    }
}
