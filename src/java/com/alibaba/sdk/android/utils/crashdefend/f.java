package com.alibaba.sdk.android.utils.crashdefend;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

final class f
{
    private final ThreadFactory a;
    private ExecutorService c;
    
    f() {
        this.a = (ThreadFactory)new ThreadFactory() {
            final f a;
            
            public Thread newThread(final Runnable runnable) {
                final Thread thread = new Thread(runnable, "safe_thread");
                thread.setDaemon(false);
                return thread;
            }
        };
    }
    
    ExecutorService a() {
        synchronized (this) {
            if (this.c == null) {
                this.c = (ExecutorService)new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1L, TimeUnit.SECONDS, (BlockingQueue)new SynchronousQueue(), this.a);
            }
            return this.c;
        }
    }
}
