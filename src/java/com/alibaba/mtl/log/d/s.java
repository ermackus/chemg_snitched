package com.alibaba.mtl.log.d;

import com.alibaba.mtl.appmonitor.b.b;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor$DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadPoolExecutor;

public class s
{
    private static int G = 1;
    private static int H = 3;
    private static int I = 10;
    private static int J = 60;
    public static s a;
    private static ThreadPoolExecutor a;
    private static final AtomicInteger f;
    private HandlerThread b;
    private Handler mHandler;
    
    static {
        f = new AtomicInteger();
    }
    
    private s() {
        (this.b = new HandlerThread("AppMonitor")).start();
        this.mHandler = new Handler(this, this.b.getLooper()) {
            final s b;
            
            public void handleMessage(final Message message) {
                super.handleMessage(message);
                try {
                    if (message.obj != null && message.obj instanceof Runnable) {
                        a().submit((Runnable)message.obj);
                    }
                }
                finally {}
            }
        };
    }
    
    public static s a() {
        synchronized (s.class) {
            if (s.a == null) {
                s.a = new s();
            }
            return s.a;
        }
    }
    
    private static ThreadPoolExecutor a() {
        synchronized (s.class) {
            if (s.a == null) {
                s.a = a(s.G, s.H, s.I, s.J, 500);
            }
            return s.a;
        }
    }
    
    private static ThreadPoolExecutor a(final int n, final int n2, final int n3, final int n4, final int n5) {
        LinkedBlockingQueue linkedBlockingQueue;
        if (n5 > 0) {
            linkedBlockingQueue = new LinkedBlockingQueue(n5);
        }
        else {
            linkedBlockingQueue = new LinkedBlockingQueue();
        }
        return new ThreadPoolExecutor(n2, n3, (long)n4, TimeUnit.SECONDS, (BlockingQueue)linkedBlockingQueue, (ThreadFactory)new a(n), (RejectedExecutionHandler)new ThreadPoolExecutor$DiscardOldestPolicy());
    }
    
    public final void a(final int n, final Runnable obj, final long n2) {
        try {
            final Message obtain = Message.obtain(this.mHandler, n);
            obtain.obj = obj;
            this.mHandler.sendMessageDelayed(obtain, n2);
        }
        catch (final Exception ex) {
            com.alibaba.mtl.appmonitor.b.b.a((Throwable)ex);
        }
    }
    
    public void b(final Runnable runnable) {
        try {
            a().submit(runnable);
        }
        finally {}
    }
    
    public final boolean b(final int n) {
        return this.mHandler.hasMessages(n);
    }
    
    public final void f(final int n) {
        this.mHandler.removeMessages(n);
    }
    
    static class a implements ThreadFactory
    {
        private int priority;
        
        public a(final int priority) {
            this.priority = priority;
        }
        
        public Thread newThread(final Runnable runnable) {
            final int andIncrement = s.f.getAndIncrement();
            final StringBuilder sb = new StringBuilder();
            sb.append("AppMonitor:");
            sb.append(andIncrement);
            final Thread thread = new Thread(runnable, sb.toString());
            thread.setPriority(this.priority);
            return thread;
        }
    }
}
