package com.otaliastudios.cameraview.internal;

import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Callable;
import android.os.Looper;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import android.os.HandlerThread;
import android.os.Handler;
import java.util.concurrent.Executor;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import com.otaliastudios.cameraview.CameraLogger;

public class WorkerHandler
{
    private static final String FALLBACK_NAME = "FallbackCameraThread";
    private static final CameraLogger LOG;
    private static final ConcurrentHashMap<String, WeakReference<WorkerHandler>> sCache;
    private static WorkerHandler sFallbackHandler;
    private Executor mExecutor;
    private Handler mHandler;
    private String mName;
    private HandlerThread mThread;
    
    static {
        LOG = CameraLogger.create(WorkerHandler.class.getSimpleName());
        sCache = new ConcurrentHashMap(4);
    }
    
    private WorkerHandler(final String mName) {
        this.mName = mName;
        (this.mThread = new HandlerThread(this, mName) {
            final WorkerHandler this$0;
            
            public String toString() {
                final StringBuilder sb = new StringBuilder();
                sb.append(super.toString());
                sb.append("[");
                sb.append(this.getThreadId());
                sb.append("]");
                return sb.toString();
            }
        }).setDaemon(true);
        this.mThread.start();
        this.mHandler = new Handler(this.mThread.getLooper());
        this.mExecutor = (Executor)new Executor(this) {
            final WorkerHandler this$0;
            
            public void execute(final Runnable runnable) {
                this.this$0.run(runnable);
            }
        };
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.post((Runnable)new Runnable(this, countDownLatch) {
            final WorkerHandler this$0;
            final CountDownLatch val$latch;
            
            public void run() {
                this.val$latch.countDown();
            }
        });
        try {
            countDownLatch.await();
        }
        catch (final InterruptedException ex) {}
    }
    
    public static void destroyAll() {
        final Iterator iterator = WorkerHandler.sCache.keySet().iterator();
        while (iterator.hasNext()) {
            final WeakReference weakReference = (WeakReference)WorkerHandler.sCache.get((Object)iterator.next());
            final WorkerHandler workerHandler = (WorkerHandler)weakReference.get();
            if (workerHandler != null) {
                workerHandler.destroy();
            }
            weakReference.clear();
        }
        WorkerHandler.sCache.clear();
    }
    
    public static void execute(final Runnable runnable) {
        get().post(runnable);
    }
    
    public static WorkerHandler get() {
        return WorkerHandler.sFallbackHandler = get("FallbackCameraThread");
    }
    
    public static WorkerHandler get(final String s) {
        if (WorkerHandler.sCache.containsKey((Object)s)) {
            final WorkerHandler workerHandler = (WorkerHandler)((WeakReference)WorkerHandler.sCache.get((Object)s)).get();
            if (workerHandler != null) {
                if (workerHandler.getThread().isAlive() && !workerHandler.getThread().isInterrupted()) {
                    WorkerHandler.LOG.w(new Object[] { "get:", "Reusing cached worker handler.", s });
                    return workerHandler;
                }
                workerHandler.destroy();
                WorkerHandler.LOG.w(new Object[] { "get:", "Thread reference found, but not alive or interrupted.", "Removing.", s });
                WorkerHandler.sCache.remove((Object)s);
            }
            else {
                WorkerHandler.LOG.w(new Object[] { "get:", "Thread reference died. Removing.", s });
                WorkerHandler.sCache.remove((Object)s);
            }
        }
        WorkerHandler.LOG.i(new Object[] { "get:", "Creating new handler.", s });
        final WorkerHandler workerHandler2 = new WorkerHandler(s);
        WorkerHandler.sCache.put((Object)s, (Object)new WeakReference((Object)workerHandler2));
        return workerHandler2;
    }
    
    public void destroy() {
        final HandlerThread thread = this.getThread();
        if (thread.isAlive()) {
            thread.interrupt();
            thread.quit();
        }
        WorkerHandler.sCache.remove((Object)this.mName);
    }
    
    public Executor getExecutor() {
        return this.mExecutor;
    }
    
    public Handler getHandler() {
        return this.mHandler;
    }
    
    public Looper getLooper() {
        return this.mThread.getLooper();
    }
    
    public HandlerThread getThread() {
        return this.mThread;
    }
    
    public <T> Task<T> post(final Callable<T> callable) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.post((Runnable)new Runnable(this, taskCompletionSource, callable) {
            final WorkerHandler this$0;
            final Callable val$callable;
            final TaskCompletionSource val$source;
            
            public void run() {
                try {
                    this.val$source.trySetResult(this.val$callable.call());
                }
                catch (final Exception ex) {
                    this.val$source.trySetException(ex);
                }
            }
        });
        return (Task<T>)taskCompletionSource.getTask();
    }
    
    public void post(final long n, final Runnable runnable) {
        this.mHandler.postDelayed(runnable, n);
    }
    
    public void post(final Runnable runnable) {
        this.mHandler.post(runnable);
    }
    
    public void remove(final Runnable runnable) {
        this.mHandler.removeCallbacks(runnable);
    }
    
    public <T> Task<T> run(final Callable<T> callable) {
        if (Thread.currentThread() == this.getThread()) {
            try {
                return (Task<T>)Tasks.forResult(callable.call());
            }
            catch (final Exception ex) {
                return (Task<T>)Tasks.forException(ex);
            }
        }
        return (Task<T>)this.post((java.util.concurrent.Callable<Object>)callable);
    }
    
    public void run(final Runnable runnable) {
        if (Thread.currentThread() == this.getThread()) {
            runnable.run();
        }
        else {
            this.post(runnable);
        }
    }
}
