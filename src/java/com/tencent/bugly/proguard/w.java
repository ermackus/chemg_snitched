package com.tencent.bugly.proguard;

import java.util.concurrent.TimeUnit;
import com.tencent.bugly.b;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public final class w
{
    private static final AtomicInteger a;
    private static w b;
    private ScheduledExecutorService c;
    
    static {
        a = new AtomicInteger(1);
    }
    
    protected w() {
        this.c = null;
        final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3, (ThreadFactory)new ThreadFactory(this) {
            public final Thread newThread(final Runnable runnable) {
                final Thread thread = new Thread(runnable);
                final StringBuilder sb = new StringBuilder("BuglyThread-");
                sb.append(w.a.getAndIncrement());
                thread.setName(sb.toString());
                return thread;
            }
        });
        this.c = scheduledThreadPool;
        if (scheduledThreadPool == null || scheduledThreadPool.isShutdown()) {
            x.d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }
    
    public static w a() {
        synchronized (w.class) {
            if (w.b == null) {
                w.b = new w();
            }
            return w.b;
        }
    }
    
    public final boolean a(final Runnable runnable) {
        synchronized (this) {
            if (!this.c()) {
                x.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
                return false;
            }
            if (runnable == null) {
                x.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
                return false;
            }
            x.c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
            try {
                this.c.execute(runnable);
                return true;
            }
            finally {
                if (com.tencent.bugly.b.c) {
                    final Throwable t;
                    t.printStackTrace();
                }
                return false;
            }
        }
    }
    
    public final boolean a(final Runnable runnable, long n) {
        synchronized (this) {
            if (!this.c()) {
                x.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
                return false;
            }
            if (runnable == null) {
                x.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
                return false;
            }
            if (n <= 0L) {
                n = 0L;
            }
            x.c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", n, runnable.getClass().getName());
            try {
                this.c.schedule(runnable, n, TimeUnit.MILLISECONDS);
                return true;
            }
            finally {
                if (com.tencent.bugly.b.c) {
                    final Throwable t;
                    t.printStackTrace();
                }
                return false;
            }
        }
    }
    
    public final void b() {
        synchronized (this) {
            if (this.c != null && !this.c.isShutdown()) {
                x.c("[AsyncTaskHandler] Close async handler.", new Object[0]);
                this.c.shutdownNow();
            }
        }
    }
    
    public final boolean c() {
        synchronized (this) {
            return this.c != null && !this.c.isShutdown();
        }
    }
}
