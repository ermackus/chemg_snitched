package com.otaliastudios.cameraview.engine.orchestrator;

import java.util.Set;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import com.google.android.gms.tasks.Tasks;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Callable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.otaliastudios.cameraview.internal.WorkerHandler;
import com.google.android.gms.tasks.Task;
import java.util.ArrayDeque;
import com.otaliastudios.cameraview.CameraLogger;

public class CameraOrchestrator
{
    protected static final CameraLogger LOG;
    protected static final String TAG;
    protected final Callback mCallback;
    protected boolean mJobRunning;
    protected final ArrayDeque<Job<?>> mJobs;
    protected final Object mJobsLock;
    
    static {
        LOG = CameraLogger.create(TAG = CameraOrchestrator.class.getSimpleName());
    }
    
    public CameraOrchestrator(final Callback mCallback) {
        this.mJobs = (ArrayDeque<Job<?>>)new ArrayDeque();
        this.mJobRunning = false;
        this.mJobsLock = new Object();
        this.mCallback = mCallback;
    }
    
    private <T> void execute(final Job<T> job) {
        final WorkerHandler jobWorker = this.mCallback.getJobWorker(job.name);
        jobWorker.run((Runnable)new Runnable(this, job, jobWorker) {
            final CameraOrchestrator this$0;
            final Job val$job;
            final WorkerHandler val$worker;
            
            public void run() {
                try {
                    CameraOrchestrator.LOG.i(new Object[] { this.val$job.name.toUpperCase(), "- Executing." });
                    onComplete((com.google.android.gms.tasks.Task<Object>)this.val$job.scheduler.call(), this.val$worker, (com.google.android.gms.tasks.OnCompleteListener<Object>)new CameraOrchestrator$3$1(this));
                }
                catch (final Exception ex) {
                    CameraOrchestrator.LOG.i(new Object[] { this.val$job.name.toUpperCase(), "- Finished with ERROR.", ex });
                    if (this.val$job.dispatchExceptions) {
                        this.this$0.mCallback.handleJobException(this.val$job.name, ex);
                    }
                    this.val$job.source.trySetException(ex);
                    final Object mJobsLock = this.this$0.mJobsLock;
                    synchronized (mJobsLock) {
                        this.this$0.executed((Job<Object>)this.val$job);
                    }
                }
            }
        });
    }
    
    private <T> void executed(final Job<T> job) {
        if (this.mJobRunning) {
            this.mJobRunning = false;
            this.mJobs.remove((Object)job);
            this.sync(0L);
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("mJobRunning was not true after completing job=");
        sb.append(job.name);
        throw new IllegalStateException(sb.toString());
    }
    
    private static <T> void onComplete(final Task<T> task, final WorkerHandler workerHandler, final OnCompleteListener<T> onCompleteListener) {
        if (task.isComplete()) {
            workerHandler.run((Runnable)new Runnable(onCompleteListener, task) {
                final OnCompleteListener val$listener;
                final Task val$task;
                
                public void run() {
                    this.val$listener.onComplete(this.val$task);
                }
            });
        }
        else {
            task.addOnCompleteListener(workerHandler.getExecutor(), (OnCompleteListener)onCompleteListener);
        }
    }
    
    private <T> Task<T> scheduleInternal(final String s, final boolean b, final long n, final Callable<Task<T>> callable) {
        CameraOrchestrator.LOG.i(new Object[] { s.toUpperCase(), "- Scheduling." });
        final Job job = new Job(s, (Callable)callable, b, System.currentTimeMillis() + n);
        final Object mJobsLock = this.mJobsLock;
        synchronized (mJobsLock) {
            this.mJobs.addLast((Object)job);
            this.sync(n);
            monitorexit(mJobsLock);
            return (Task<T>)job.source.getTask();
        }
    }
    
    private void sync(final long n) {
        this.mCallback.getJobWorker("_sync").post(n, (Runnable)new Runnable(this) {
            final CameraOrchestrator this$0;
            
            public void run() {
                final Object mJobsLock = this.this$0.mJobsLock;
                synchronized (mJobsLock) {
                    final boolean mJobRunning = this.this$0.mJobRunning;
                    final Job job = null;
                    final Job job2 = null;
                    Job job3;
                    if (mJobRunning) {
                        job3 = job;
                    }
                    else {
                        final long currentTimeMillis = System.currentTimeMillis();
                        final Iterator iterator = this.this$0.mJobs.iterator();
                        Object o;
                        do {
                            o = job2;
                            if (!iterator.hasNext()) {
                                break;
                            }
                            o = iterator.next();
                        } while (((Job)o).startTime > currentTimeMillis);
                        if ((job3 = (Job)o) != null) {
                            this.this$0.mJobRunning = true;
                            job3 = (Job)o;
                        }
                    }
                    monitorexit(mJobsLock);
                    if (job3 != null) {
                        this.this$0.execute((Job<Object>)job3);
                    }
                }
            }
        });
    }
    
    public void remove(final String s) {
        this.trim(s, 0);
    }
    
    public void reset() {
        final Object mJobsLock = this.mJobsLock;
        synchronized (mJobsLock) {
            final HashSet set = new HashSet();
            final Iterator iterator = this.mJobs.iterator();
            while (iterator.hasNext()) {
                ((Set)set).add((Object)((Job)iterator.next()).name);
            }
            final Iterator iterator2 = ((Set)set).iterator();
            while (iterator2.hasNext()) {
                this.remove((String)iterator2.next());
            }
        }
    }
    
    public Task<Void> schedule(final String s, final boolean b, final Runnable runnable) {
        return this.scheduleDelayed(s, b, 0L, runnable);
    }
    
    public <T> Task<T> schedule(final String s, final boolean b, final Callable<Task<T>> callable) {
        return this.scheduleInternal(s, b, 0L, callable);
    }
    
    public Task<Void> scheduleDelayed(final String s, final boolean b, final long n, final Runnable runnable) {
        return this.scheduleInternal(s, b, n, (java.util.concurrent.Callable<com.google.android.gms.tasks.Task<Void>>)new Callable<Task<Void>>(this, runnable) {
            final CameraOrchestrator this$0;
            final Runnable val$job;
            
            public Task<Void> call() {
                this.val$job.run();
                return (Task<Void>)Tasks.forResult((Object)null);
            }
        });
    }
    
    public void trim(final String s, int max) {
        final Object mJobsLock = this.mJobsLock;
        synchronized (mJobsLock) {
            final ArrayList list = new ArrayList();
            for (final Job job : this.mJobs) {
                if (job.name.equals((Object)s)) {
                    ((List)list).add((Object)job);
                }
            }
            CameraOrchestrator.LOG.v(new Object[] { "trim: name=", s, "scheduled=", ((List)list).size(), "allowed=", max });
            max = Math.max(((List)list).size() - max, 0);
            if (max > 0) {
                Collections.reverse((List)list);
                final Iterator iterator2 = ((List)list).subList(0, max).iterator();
                while (iterator2.hasNext()) {
                    this.mJobs.remove((Object)iterator2.next());
                }
            }
        }
    }
    
    public interface Callback
    {
        WorkerHandler getJobWorker(final String p0);
        
        void handleJobException(final String p0, final Exception p1);
    }
    
    protected static class Job<T>
    {
        public final boolean dispatchExceptions;
        public final String name;
        public final Callable<Task<T>> scheduler;
        public final TaskCompletionSource<T> source;
        public final long startTime;
        
        private Job(final String name, final Callable<Task<T>> scheduler, final boolean dispatchExceptions, final long startTime) {
            this.source = (TaskCompletionSource<T>)new TaskCompletionSource();
            this.name = name;
            this.scheduler = scheduler;
            this.dispatchExceptions = dispatchExceptions;
            this.startTime = startTime;
        }
    }
}
