package com.luck.picture.lib.thread;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import android.util.Log;
import java.util.Map$Entry;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.Map;
import android.os.Handler;

public final class PictureThreadUtils
{
    private static final int CPU_COUNT;
    private static final Handler HANDLER;
    private static final Map<Task, ExecutorService> TASK_POOL_MAP;
    private static final Timer TIMER;
    private static final byte TYPE_CACHED = -2;
    private static final byte TYPE_CPU = -8;
    private static final byte TYPE_IO = -4;
    private static final Map<Integer, Map<Integer, ExecutorService>> TYPE_PRIORITY_POOLS;
    private static final byte TYPE_SINGLE = -1;
    private static Executor sDeliver;
    
    static {
        HANDLER = new Handler(Looper.getMainLooper());
        TYPE_PRIORITY_POOLS = (Map)new HashMap();
        TASK_POOL_MAP = (Map)new ConcurrentHashMap();
        CPU_COUNT = Runtime.getRuntime().availableProcessors();
        TIMER = new Timer();
    }
    
    public static void cancel(final Task task) {
        if (task == null) {
            return;
        }
        task.cancel();
    }
    
    public static void cancel(final List<Task> list) {
        if (list != null) {
            if (list.size() != 0) {
                for (final Task task : list) {
                    if (task == null) {
                        continue;
                    }
                    task.cancel();
                }
            }
        }
    }
    
    public static void cancel(final ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor4Util) {
            for (final Map$Entry map$Entry : PictureThreadUtils.TASK_POOL_MAP.entrySet()) {
                if (map$Entry.getValue() == executorService) {
                    cancel((Task)map$Entry.getKey());
                }
            }
        }
        else {
            Log.e("ThreadUtils", "The executorService is not ThreadUtils's pool.");
        }
    }
    
    public static void cancel(final Task... array) {
        if (array != null) {
            if (array.length != 0) {
                for (final Task task : array) {
                    if (task != null) {
                        task.cancel();
                    }
                }
            }
        }
    }
    
    private static <T> void execute(final ExecutorService executorService, final Task<T> task) {
        execute(executorService, task, 0L, 0L, null);
    }
    
    private static <T> void execute(final ExecutorService executorService, final Task<T> task, final long n, final long n2, final TimeUnit timeUnit) {
        final Map<Task, ExecutorService> task_POOL_MAP = PictureThreadUtils.TASK_POOL_MAP;
        synchronized (task_POOL_MAP) {
            if (PictureThreadUtils.TASK_POOL_MAP.get((Object)task) != null) {
                Log.e("ThreadUtils", "Task can only be executed once.");
                return;
            }
            PictureThreadUtils.TASK_POOL_MAP.put((Object)task, (Object)executorService);
            monitorexit(task_POOL_MAP);
            if (n2 == 0L) {
                if (n == 0L) {
                    executorService.execute((Runnable)task);
                }
                else {
                    PictureThreadUtils.TIMER.schedule((TimerTask)new TimerTask(executorService, task) {
                        final ExecutorService val$pool;
                        final Task val$task;
                        
                        public void run() {
                            this.val$pool.execute((Runnable)this.val$task);
                        }
                    }, timeUnit.toMillis(n));
                }
            }
            else {
                ((Task<Object>)task).setSchedule(true);
                PictureThreadUtils.TIMER.scheduleAtFixedRate((TimerTask)new TimerTask(executorService, task) {
                    final ExecutorService val$pool;
                    final Task val$task;
                    
                    public void run() {
                        this.val$pool.execute((Runnable)this.val$task);
                    }
                }, timeUnit.toMillis(n), timeUnit.toMillis(n2));
            }
        }
    }
    
    private static <T> void executeAtFixedRate(final ExecutorService executorService, final Task<T> task, final long n, final long n2, final TimeUnit timeUnit) {
        execute(executorService, (Task<Object>)task, n, n2, timeUnit);
    }
    
    public static <T> void executeByCached(final Task<T> task) {
        execute(getPoolByTypeAndPriority(-2), task);
    }
    
    public static <T> void executeByCached(final Task<T> task, final int n) {
        execute(getPoolByTypeAndPriority(-2, n), task);
    }
    
    public static <T> void executeByCachedAtFixRate(final Task<T> task, final long n, final long n2, final TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2), task, n, n2, timeUnit);
    }
    
    public static <T> void executeByCachedAtFixRate(final Task<T> task, final long n, final long n2, final TimeUnit timeUnit, final int n3) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2, n3), task, n, n2, timeUnit);
    }
    
    public static <T> void executeByCachedAtFixRate(final Task<T> task, final long n, final TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2), task, 0L, n, timeUnit);
    }
    
    public static <T> void executeByCachedAtFixRate(final Task<T> task, final long n, final TimeUnit timeUnit, final int n2) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2, n2), task, 0L, n, timeUnit);
    }
    
    public static <T> void executeByCachedWithDelay(final Task<T> task, final long n, final TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-2), task, n, timeUnit);
    }
    
    public static <T> void executeByCachedWithDelay(final Task<T> task, final long n, final TimeUnit timeUnit, final int n2) {
        executeWithDelay(getPoolByTypeAndPriority(-2, n2), task, n, timeUnit);
    }
    
    public static <T> void executeByCpu(final Task<T> task) {
        execute(getPoolByTypeAndPriority(-8), task);
    }
    
    public static <T> void executeByCpu(final Task<T> task, final int n) {
        execute(getPoolByTypeAndPriority(-8, n), task);
    }
    
    public static <T> void executeByCpuAtFixRate(final Task<T> task, final long n, final long n2, final TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8), task, n, n2, timeUnit);
    }
    
    public static <T> void executeByCpuAtFixRate(final Task<T> task, final long n, final long n2, final TimeUnit timeUnit, final int n3) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8, n3), task, n, n2, timeUnit);
    }
    
    public static <T> void executeByCpuAtFixRate(final Task<T> task, final long n, final TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8), task, 0L, n, timeUnit);
    }
    
    public static <T> void executeByCpuAtFixRate(final Task<T> task, final long n, final TimeUnit timeUnit, final int n2) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8, n2), task, 0L, n, timeUnit);
    }
    
    public static <T> void executeByCpuWithDelay(final Task<T> task, final long n, final TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-8), task, n, timeUnit);
    }
    
    public static <T> void executeByCpuWithDelay(final Task<T> task, final long n, final TimeUnit timeUnit, final int n2) {
        executeWithDelay(getPoolByTypeAndPriority(-8, n2), task, n, timeUnit);
    }
    
    public static <T> void executeByCustom(final ExecutorService executorService, final Task<T> task) {
        execute(executorService, (Task<Object>)task);
    }
    
    public static <T> void executeByCustomAtFixRate(final ExecutorService executorService, final Task<T> task, final long n, final long n2, final TimeUnit timeUnit) {
        executeAtFixedRate(executorService, (Task<Object>)task, n, n2, timeUnit);
    }
    
    public static <T> void executeByCustomAtFixRate(final ExecutorService executorService, final Task<T> task, final long n, final TimeUnit timeUnit) {
        executeAtFixedRate(executorService, task, 0L, n, timeUnit);
    }
    
    public static <T> void executeByCustomWithDelay(final ExecutorService executorService, final Task<T> task, final long n, final TimeUnit timeUnit) {
        executeWithDelay(executorService, (Task<Object>)task, n, timeUnit);
    }
    
    public static <T> void executeByFixed(final int n, final Task<T> task) {
        execute(getPoolByTypeAndPriority(n), task);
    }
    
    public static <T> void executeByFixed(final int n, final Task<T> task, final int n2) {
        execute(getPoolByTypeAndPriority(n, n2), task);
    }
    
    public static <T> void executeByFixedAtFixRate(final int n, final Task<T> task, final long n2, final long n3, final TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(n), task, n2, n3, timeUnit);
    }
    
    public static <T> void executeByFixedAtFixRate(final int n, final Task<T> task, final long n2, final long n3, final TimeUnit timeUnit, final int n4) {
        executeAtFixedRate(getPoolByTypeAndPriority(n, n4), task, n2, n3, timeUnit);
    }
    
    public static <T> void executeByFixedAtFixRate(final int n, final Task<T> task, final long n2, final TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(n), task, 0L, n2, timeUnit);
    }
    
    public static <T> void executeByFixedAtFixRate(final int n, final Task<T> task, final long n2, final TimeUnit timeUnit, final int n3) {
        executeAtFixedRate(getPoolByTypeAndPriority(n, n3), task, 0L, n2, timeUnit);
    }
    
    public static <T> void executeByFixedWithDelay(final int n, final Task<T> task, final long n2, final TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(n), task, n2, timeUnit);
    }
    
    public static <T> void executeByFixedWithDelay(final int n, final Task<T> task, final long n2, final TimeUnit timeUnit, final int n3) {
        executeWithDelay(getPoolByTypeAndPriority(n, n3), task, n2, timeUnit);
    }
    
    public static <T> void executeByIo(final Task<T> task) {
        execute(getPoolByTypeAndPriority(-4), task);
    }
    
    public static <T> void executeByIo(final Task<T> task, final int n) {
        execute(getPoolByTypeAndPriority(-4, n), task);
    }
    
    public static <T> void executeByIoAtFixRate(final Task<T> task, final long n, final long n2, final TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4), task, n, n2, timeUnit);
    }
    
    public static <T> void executeByIoAtFixRate(final Task<T> task, final long n, final long n2, final TimeUnit timeUnit, final int n3) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4, n3), task, n, n2, timeUnit);
    }
    
    public static <T> void executeByIoAtFixRate(final Task<T> task, final long n, final TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4), task, 0L, n, timeUnit);
    }
    
    public static <T> void executeByIoAtFixRate(final Task<T> task, final long n, final TimeUnit timeUnit, final int n2) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4, n2), task, 0L, n, timeUnit);
    }
    
    public static <T> void executeByIoWithDelay(final Task<T> task, final long n, final TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-4), task, n, timeUnit);
    }
    
    public static <T> void executeByIoWithDelay(final Task<T> task, final long n, final TimeUnit timeUnit, final int n2) {
        executeWithDelay(getPoolByTypeAndPriority(-4, n2), task, n, timeUnit);
    }
    
    public static <T> void executeBySingle(final Task<T> task) {
        execute(getPoolByTypeAndPriority(-1), task);
    }
    
    public static <T> void executeBySingle(final Task<T> task, final int n) {
        execute(getPoolByTypeAndPriority(-1, n), task);
    }
    
    public static <T> void executeBySingleAtFixRate(final Task<T> task, final long n, final long n2, final TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1), task, n, n2, timeUnit);
    }
    
    public static <T> void executeBySingleAtFixRate(final Task<T> task, final long n, final long n2, final TimeUnit timeUnit, final int n3) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1, n3), task, n, n2, timeUnit);
    }
    
    public static <T> void executeBySingleAtFixRate(final Task<T> task, final long n, final TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1), task, 0L, n, timeUnit);
    }
    
    public static <T> void executeBySingleAtFixRate(final Task<T> task, final long n, final TimeUnit timeUnit, final int n2) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1, n2), task, 0L, n, timeUnit);
    }
    
    public static <T> void executeBySingleWithDelay(final Task<T> task, final long n, final TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-1), task, n, timeUnit);
    }
    
    public static <T> void executeBySingleWithDelay(final Task<T> task, final long n, final TimeUnit timeUnit, final int n2) {
        executeWithDelay(getPoolByTypeAndPriority(-1, n2), task, n, timeUnit);
    }
    
    private static <T> void executeWithDelay(final ExecutorService executorService, final Task<T> task, final long n, final TimeUnit timeUnit) {
        execute(executorService, task, n, 0L, timeUnit);
    }
    
    public static ExecutorService getCachedPool() {
        return getPoolByTypeAndPriority(-2);
    }
    
    public static ExecutorService getCachedPool(final int n) {
        return getPoolByTypeAndPriority(-2, n);
    }
    
    public static ExecutorService getCpuPool() {
        return getPoolByTypeAndPriority(-8);
    }
    
    public static ExecutorService getCpuPool(final int n) {
        return getPoolByTypeAndPriority(-8, n);
    }
    
    public static ExecutorService getFixedPool(final int n) {
        return getPoolByTypeAndPriority(n);
    }
    
    public static ExecutorService getFixedPool(final int n, final int n2) {
        return getPoolByTypeAndPriority(n, n2);
    }
    
    private static Executor getGlobalDeliver() {
        if (PictureThreadUtils.sDeliver == null) {
            PictureThreadUtils.sDeliver = (Executor)new Executor() {
                public void execute(final Runnable runnable) {
                    PictureThreadUtils.runOnUiThread(runnable);
                }
            };
        }
        return PictureThreadUtils.sDeliver;
    }
    
    public static ExecutorService getIoPool() {
        return getPoolByTypeAndPriority(-4);
    }
    
    public static ExecutorService getIoPool(final int n) {
        return getPoolByTypeAndPriority(-4, n);
    }
    
    public static Handler getMainHandler() {
        return PictureThreadUtils.HANDLER;
    }
    
    private static ExecutorService getPoolByTypeAndPriority(final int n) {
        return getPoolByTypeAndPriority(n, 5);
    }
    
    private static ExecutorService getPoolByTypeAndPriority(final int n, final int n2) {
        final Map<Integer, Map<Integer, ExecutorService>> type_PRIORITY_POOLS = PictureThreadUtils.TYPE_PRIORITY_POOLS;
        synchronized (type_PRIORITY_POOLS) {
            final Map map = (Map)PictureThreadUtils.TYPE_PRIORITY_POOLS.get((Object)n);
            ExecutorService executorService;
            if (map == null) {
                final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                executorService = createPool(n, n2);
                ((Map)concurrentHashMap).put((Object)n2, (Object)executorService);
                PictureThreadUtils.TYPE_PRIORITY_POOLS.put((Object)n, (Object)concurrentHashMap);
            }
            else if ((executorService = (ExecutorService)map.get((Object)n2)) == null) {
                executorService = createPool(n, n2);
                map.put((Object)n2, (Object)executorService);
            }
            return executorService;
        }
    }
    
    public static ExecutorService getSinglePool() {
        return getPoolByTypeAndPriority(-1);
    }
    
    public static ExecutorService getSinglePool(final int n) {
        return getPoolByTypeAndPriority(-1, n);
    }
    
    public static boolean isInUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
    
    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        }
        else {
            PictureThreadUtils.HANDLER.post(runnable);
        }
    }
    
    public static void runOnUiThreadDelayed(final Runnable runnable, final long n) {
        PictureThreadUtils.HANDLER.postDelayed(runnable, n);
    }
    
    public static void setDeliver(final Executor sDeliver) {
        PictureThreadUtils.sDeliver = sDeliver;
    }
    
    private static final class LinkedBlockingQueue4Util extends LinkedBlockingQueue<Runnable>
    {
        private int mCapacity;
        private volatile ThreadPoolExecutor4Util mPool;
        
        LinkedBlockingQueue4Util() {
            this.mCapacity = Integer.MAX_VALUE;
        }
        
        LinkedBlockingQueue4Util(final int mCapacity) {
            this.mCapacity = Integer.MAX_VALUE;
            this.mCapacity = mCapacity;
        }
        
        LinkedBlockingQueue4Util(final boolean b) {
            this.mCapacity = Integer.MAX_VALUE;
            if (b) {
                this.mCapacity = 0;
            }
        }
        
        public boolean offer(final Runnable runnable) {
            return (this.mCapacity > this.size() || this.mPool == null || this.mPool.getPoolSize() >= this.mPool.getMaximumPoolSize()) && super.offer((Object)runnable);
        }
    }
    
    public static class SyncValue<T>
    {
        private AtomicBoolean mFlag;
        private CountDownLatch mLatch;
        private T mValue;
        
        public SyncValue() {
            this.mLatch = new CountDownLatch(1);
            this.mFlag = new AtomicBoolean();
        }
        
        public T getValue() {
            if (!this.mFlag.get()) {
                try {
                    this.mLatch.await();
                }
                catch (final InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            return this.mValue;
        }
        
        public T getValue(final long n, final TimeUnit timeUnit, final T t) {
            if (!this.mFlag.get()) {
                try {
                    this.mLatch.await(n, timeUnit);
                }
                catch (final InterruptedException ex) {
                    ex.printStackTrace();
                    return t;
                }
            }
            return this.mValue;
        }
        
        public void setValue(final T mValue) {
            if (this.mFlag.compareAndSet(false, true)) {
                this.mValue = mValue;
                this.mLatch.countDown();
            }
        }
    }
    
    public abstract static class Task<T> implements Runnable
    {
        private static final int CANCELLED = 4;
        private static final int COMPLETING = 3;
        private static final int EXCEPTIONAL = 2;
        private static final int INTERRUPTED = 5;
        private static final int NEW = 0;
        private static final int RUNNING = 1;
        private static final int TIMEOUT = 6;
        private Executor deliver;
        private volatile boolean isSchedule;
        private OnTimeoutListener mTimeoutListener;
        private long mTimeoutMillis;
        private Timer mTimer;
        private volatile Thread runner;
        private final AtomicInteger state;
        
        public Task() {
            this.state = new AtomicInteger(0);
        }
        
        private Executor getDeliver() {
            Executor executor;
            if ((executor = this.deliver) == null) {
                executor = getGlobalDeliver();
            }
            return executor;
        }
        
        private void setSchedule(final boolean isSchedule) {
            this.isSchedule = isSchedule;
        }
        
        private void timeout() {
            final AtomicInteger state = this.state;
            synchronized (state) {
                if (this.state.get() > 1) {
                    return;
                }
                this.state.set(6);
                monitorexit(state);
                if (this.runner != null) {
                    this.runner.interrupt();
                }
            }
        }
        
        public void cancel() {
            this.cancel(true);
        }
        
        public void cancel(final boolean b) {
            final AtomicInteger state = this.state;
            synchronized (state) {
                if (this.state.get() > 1) {
                    return;
                }
                this.state.set(4);
                monitorexit(state);
                if (b && this.runner != null) {
                    this.runner.interrupt();
                }
                this.getDeliver().execute((Runnable)new Runnable(this) {
                    final Task this$0;
                    
                    public void run() {
                        this.this$0.onCancel();
                        this.this$0.onDone();
                    }
                });
            }
        }
        
        public abstract T doInBackground() throws Throwable;
        
        public boolean isCanceled() {
            return this.state.get() >= 4;
        }
        
        public boolean isDone() {
            final int value = this.state.get();
            boolean b = true;
            if (value <= 1) {
                b = false;
            }
            return b;
        }
        
        public abstract void onCancel();
        
        protected void onDone() {
            PictureThreadUtils.TASK_POOL_MAP.remove((Object)this);
            final Timer mTimer = this.mTimer;
            if (mTimer != null) {
                mTimer.cancel();
                this.mTimer = null;
                this.mTimeoutListener = null;
            }
        }
        
        public abstract void onFail(final Throwable p0);
        
        public abstract void onSuccess(final T p0);
        
        public void run() {
            if (this.isSchedule) {
                if (this.runner == null) {
                    if (!this.state.compareAndSet(0, 1)) {
                        return;
                    }
                    this.runner = Thread.currentThread();
                    if (this.mTimeoutListener != null) {
                        Log.w("ThreadUtils", "Scheduled task doesn't support timeout.");
                    }
                }
                else if (this.state.get() != 1) {
                    return;
                }
            }
            else {
                if (!this.state.compareAndSet(0, 1)) {
                    return;
                }
                this.runner = Thread.currentThread();
                if (this.mTimeoutListener != null) {
                    (this.mTimer = new Timer()).schedule((TimerTask)new TimerTask(this) {
                        final Task this$0;
                        
                        public void run() {
                            if (!this.this$0.isDone() && this.this$0.mTimeoutListener != null) {
                                this.this$0.timeout();
                                this.this$0.mTimeoutListener.onTimeout();
                                this.this$0.onDone();
                            }
                        }
                    }, this.mTimeoutMillis);
                }
            }
            try {
                final Object doInBackground = this.doInBackground();
                if (this.isSchedule) {
                    if (this.state.get() != 1) {
                        return;
                    }
                    this.getDeliver().execute((Runnable)new Runnable(this, doInBackground) {
                        final Task this$0;
                        final Object val$result;
                        
                        public void run() {
                            this.this$0.onSuccess(this.val$result);
                        }
                    });
                }
                else {
                    if (!this.state.compareAndSet(1, 3)) {
                        return;
                    }
                    this.getDeliver().execute((Runnable)new Runnable(this, doInBackground) {
                        final Task this$0;
                        final Object val$result;
                        
                        public void run() {
                            this.this$0.onSuccess(this.val$result);
                            this.this$0.onDone();
                        }
                    });
                }
            }
            catch (final InterruptedException ex) {
                this.state.compareAndSet(4, 5);
            }
            finally {
                if (!this.state.compareAndSet(1, 2)) {
                    return;
                }
                final Throwable t;
                this.getDeliver().execute((Runnable)new Runnable(this, t) {
                    final Task this$0;
                    final Throwable val$throwable;
                    
                    public void run() {
                        this.this$0.onFail(this.val$throwable);
                        this.this$0.onDone();
                    }
                });
            }
        }
        
        public Task<T> setDeliver(final Executor deliver) {
            this.deliver = deliver;
            return this;
        }
        
        public Task<T> setTimeout(final long mTimeoutMillis, final OnTimeoutListener mTimeoutListener) {
            this.mTimeoutMillis = mTimeoutMillis;
            this.mTimeoutListener = mTimeoutListener;
            return this;
        }
        
        public interface OnTimeoutListener
        {
            void onTimeout();
        }
    }
    
    static final class ThreadPoolExecutor4Util extends ThreadPoolExecutor
    {
        private final AtomicInteger mSubmittedCount;
        private LinkedBlockingQueue4Util mWorkQueue;
        
        ThreadPoolExecutor4Util(final int n, final int n2, final long n3, final TimeUnit timeUnit, final LinkedBlockingQueue4Util mWorkQueue, final ThreadFactory threadFactory) {
            super(n, n2, n3, timeUnit, (BlockingQueue)mWorkQueue, threadFactory);
            this.mSubmittedCount = new AtomicInteger();
            mWorkQueue.mPool = this;
            this.mWorkQueue = mWorkQueue;
        }
        
        private static ExecutorService createPool(final int n, final int n2) {
            if (n == -8) {
                return (ExecutorService)new ThreadPoolExecutor4Util(PictureThreadUtils.CPU_COUNT + 1, PictureThreadUtils.CPU_COUNT * 2 + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(true), (ThreadFactory)new UtilsThreadFactory("cpu", n2));
            }
            if (n == -4) {
                return (ExecutorService)new ThreadPoolExecutor4Util(PictureThreadUtils.CPU_COUNT * 2 + 1, PictureThreadUtils.CPU_COUNT * 2 + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(), (ThreadFactory)new UtilsThreadFactory("io", n2));
            }
            if (n == -2) {
                return (ExecutorService)new ThreadPoolExecutor4Util(0, 128, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(true), (ThreadFactory)new UtilsThreadFactory("cached", n2));
            }
            if (n != -1) {
                final TimeUnit milliseconds = TimeUnit.MILLISECONDS;
                final LinkedBlockingQueue4Util linkedBlockingQueue4Util = new LinkedBlockingQueue4Util();
                final StringBuilder sb = new StringBuilder();
                sb.append("fixed(");
                sb.append(n);
                sb.append(")");
                return (ExecutorService)new ThreadPoolExecutor4Util(n, n, 0L, milliseconds, linkedBlockingQueue4Util, (ThreadFactory)new UtilsThreadFactory(sb.toString(), n2));
            }
            return (ExecutorService)new ThreadPoolExecutor4Util(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue4Util(), (ThreadFactory)new UtilsThreadFactory("single", n2));
        }
        
        private int getSubmittedCount() {
            return this.mSubmittedCount.get();
        }
        
        protected void afterExecute(final Runnable runnable, final Throwable t) {
            this.mSubmittedCount.decrementAndGet();
            super.afterExecute(runnable, t);
        }
        
        public void execute(final Runnable runnable) {
            if (this.isShutdown()) {
                return;
            }
            this.mSubmittedCount.incrementAndGet();
            try {
                super.execute(runnable);
            }
            catch (final RejectedExecutionException ex) {
                Log.e("ThreadUtils", "This will not happen!");
                this.mWorkQueue.offer(runnable);
            }
            finally {
                this.mSubmittedCount.decrementAndGet();
            }
        }
    }
    
    static final class UtilsThreadFactory extends AtomicLong implements ThreadFactory
    {
        private static final AtomicInteger POOL_NUMBER;
        private static final long serialVersionUID = -9209200509960368598L;
        private final boolean isDaemon;
        private final String namePrefix;
        private final int priority;
        
        static {
            POOL_NUMBER = new AtomicInteger(1);
        }
        
        UtilsThreadFactory(final String s, final int n) {
            this(s, n, false);
        }
        
        UtilsThreadFactory(final String s, final int priority, final boolean isDaemon) {
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("-pool-");
            sb.append(UtilsThreadFactory.POOL_NUMBER.getAndIncrement());
            sb.append("-thread-");
            this.namePrefix = sb.toString();
            this.priority = priority;
            this.isDaemon = isDaemon;
        }
        
        public Thread newThread(final Runnable runnable) {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.namePrefix);
            sb.append(this.getAndIncrement());
            final Thread thread = new Thread(this, runnable, sb.toString()) {
                final UtilsThreadFactory this$0;
                
                public void run() {
                    try {
                        super.run();
                    }
                    finally {
                        final Throwable t;
                        Log.e("ThreadUtils", "Request threw uncaught throwable", t);
                    }
                }
            };
            thread.setDaemon(this.isDaemon);
            thread.setUncaughtExceptionHandler((Thread$UncaughtExceptionHandler)new Thread$UncaughtExceptionHandler(this) {
                final UtilsThreadFactory this$0;
                
                public void uncaughtException(final Thread thread, final Throwable t) {
                    System.out.println((Object)t);
                }
            });
            thread.setPriority(this.priority);
            return thread;
        }
    }
}
