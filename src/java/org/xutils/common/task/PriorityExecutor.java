package org.xutils.common.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Comparator;
import java.util.concurrent.Executor;

public class PriorityExecutor implements Executor
{
    private static final int CORE_POOL_SIZE = 5;
    private static final Comparator<Runnable> FIFO_CMP;
    private static final Comparator<Runnable> FILO_CMP;
    private static final int KEEP_ALIVE = 1;
    private static final int MAXIMUM_POOL_SIZE = 256;
    private static final AtomicLong SEQ_SEED;
    private static final ThreadFactory sThreadFactory;
    private final ThreadPoolExecutor mThreadPoolExecutor;
    
    static {
        SEQ_SEED = new AtomicLong(0L);
        sThreadFactory = (ThreadFactory)new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);
            
            public Thread newThread(final Runnable runnable) {
                final StringBuilder sb = new StringBuilder();
                sb.append("xTID#");
                sb.append(this.mCount.getAndIncrement());
                return new Thread(runnable, sb.toString());
            }
        };
        FIFO_CMP = (Comparator)new Comparator<Runnable>() {
            public int compare(final Runnable runnable, final Runnable runnable2) {
                if (runnable instanceof PriorityRunnable && runnable2 instanceof PriorityRunnable) {
                    final PriorityRunnable priorityRunnable = (PriorityRunnable)runnable;
                    final PriorityRunnable priorityRunnable2 = (PriorityRunnable)runnable2;
                    int n;
                    if ((n = priorityRunnable.priority.ordinal() - priorityRunnable2.priority.ordinal()) == 0) {
                        n = (int)(priorityRunnable.SEQ - priorityRunnable2.SEQ);
                    }
                    return n;
                }
                return 0;
            }
        };
        FILO_CMP = (Comparator)new Comparator<Runnable>() {
            public int compare(final Runnable runnable, final Runnable runnable2) {
                if (runnable instanceof PriorityRunnable && runnable2 instanceof PriorityRunnable) {
                    final PriorityRunnable priorityRunnable = (PriorityRunnable)runnable;
                    final PriorityRunnable priorityRunnable2 = (PriorityRunnable)runnable2;
                    int n;
                    if ((n = priorityRunnable.priority.ordinal() - priorityRunnable2.priority.ordinal()) == 0) {
                        n = (int)(priorityRunnable2.SEQ - priorityRunnable.SEQ);
                    }
                    return n;
                }
                return 0;
            }
        };
    }
    
    public PriorityExecutor(final int n, final boolean b) {
        Comparator<Runnable> comparator;
        if (b) {
            comparator = PriorityExecutor.FIFO_CMP;
        }
        else {
            comparator = PriorityExecutor.FILO_CMP;
        }
        this.mThreadPoolExecutor = new ThreadPoolExecutor(n, 256, 1L, TimeUnit.SECONDS, (BlockingQueue)new PriorityBlockingQueue(256, (Comparator)comparator), PriorityExecutor.sThreadFactory);
    }
    
    public PriorityExecutor(final boolean b) {
        this(5, b);
    }
    
    public void execute(final Runnable runnable) {
        if (runnable instanceof PriorityRunnable) {
            ((PriorityRunnable)runnable).SEQ = PriorityExecutor.SEQ_SEED.getAndIncrement();
        }
        this.mThreadPoolExecutor.execute(runnable);
    }
    
    public int getPoolSize() {
        return this.mThreadPoolExecutor.getCorePoolSize();
    }
    
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return this.mThreadPoolExecutor;
    }
    
    public boolean isBusy() {
        return this.mThreadPoolExecutor.getActiveCount() >= this.mThreadPoolExecutor.getCorePoolSize();
    }
    
    public void setPoolSize(final int corePoolSize) {
        if (corePoolSize > 0) {
            this.mThreadPoolExecutor.setCorePoolSize(corePoolSize);
        }
    }
}
