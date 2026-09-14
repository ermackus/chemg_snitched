package com.kingagroot.kingdraw.thread;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor$AbortPolicy;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;

public class ThreadPoolManager
{
    private static ThreadPoolManager manager;
    int corePoolSize;
    RejectedExecutionHandler handler;
    long keepAliveTime;
    int maximumPoolSize;
    ExecutorService service;
    ThreadFactory threadFactory;
    TimeUnit unit;
    BlockingQueue<Runnable> workQueue;
    
    private ThreadPoolManager() {
        this.corePoolSize = 3;
        this.maximumPoolSize = 10;
        this.keepAliveTime = 0L;
        this.unit = TimeUnit.MICROSECONDS;
        this.workQueue = (BlockingQueue<Runnable>)new LinkedBlockingDeque(1024);
        this.handler = (RejectedExecutionHandler)new ThreadPoolExecutor$AbortPolicy();
        this.threadFactory = (ThreadFactory)new ThreadFactory(this) {
            final ThreadPoolManager this$0;
            
            public Thread newThread(final Runnable runnable) {
                return new MThread(runnable);
            }
        };
        this.service = (ExecutorService)new ThreadPoolExecutor(this.corePoolSize, this.maximumPoolSize, this.keepAliveTime, this.unit, (BlockingQueue)this.workQueue, this.threadFactory, this.handler);
    }
    
    public static ThreadPoolManager getInstance() {
        if (ThreadPoolManager.manager == null) {
            ThreadPoolManager.manager = new ThreadPoolManager();
        }
        return ThreadPoolManager.manager;
    }
    
    public ExecutorService getService() {
        return this.service;
    }
}
