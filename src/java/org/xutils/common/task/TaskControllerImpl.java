package org.xutils.common.task;

import java.util.concurrent.atomic.AtomicInteger;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import android.os.Looper;
import org.xutils.x;
import org.xutils.common.TaskController;

public final class TaskControllerImpl implements TaskController
{
    private static volatile TaskController instance;
    
    private TaskControllerImpl() {
    }
    
    public static void registerInstance() {
        if (TaskControllerImpl.instance == null) {
            synchronized (TaskController.class) {
                if (TaskControllerImpl.instance == null) {
                    TaskControllerImpl.instance = new TaskControllerImpl();
                }
            }
        }
        x.Ext.setTaskController(TaskControllerImpl.instance);
    }
    
    @Override
    public void autoPost(final Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        }
        else {
            TaskProxy.sHandler.post(runnable);
        }
    }
    
    @Override
    public void post(final Runnable runnable) {
        if (runnable == null) {
            return;
        }
        TaskProxy.sHandler.post(runnable);
    }
    
    @Override
    public void postDelayed(final Runnable runnable, final long n) {
        if (runnable == null) {
            return;
        }
        TaskProxy.sHandler.postDelayed(runnable, n);
    }
    
    @Override
    public void removeCallbacks(final Runnable runnable) {
        TaskProxy.sHandler.removeCallbacks(runnable);
    }
    
    @Override
    public void run(final Runnable runnable) {
        if (!TaskProxy.sDefaultExecutor.isBusy()) {
            TaskProxy.sDefaultExecutor.execute(runnable);
        }
        else {
            new Thread(runnable).start();
        }
    }
    
    @Override
    public <T> AbsTask<T> start(AbsTask<T> taskProxy) {
        if (taskProxy instanceof TaskProxy) {
            taskProxy = taskProxy;
        }
        else {
            taskProxy = new TaskProxy((AbsTask)taskProxy);
        }
        try {
            taskProxy.doBackground();
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
        return (AbsTask<T>)taskProxy;
    }
    
    @Override
    public <T> T startSync(final AbsTask<T> absTask) throws Throwable {
        Throwable doBackground;
        final Throwable t = doBackground = null;
        try {
            absTask.onWaiting();
            doBackground = t;
            absTask.onStarted();
            doBackground = t;
            final Throwable t2 = doBackground = absTask.doBackground();
            absTask.onSuccess((T)t2);
            doBackground = t2;
            return (T)doBackground;
        }
        catch (final Callback.CancelledException ex) {
            absTask.onCancelled(ex);
        }
        finally {
            final AbsTask absTask2 = absTask;
            final Throwable t3 = doBackground;
            final boolean b = false;
            absTask2.onError(t3, b);
        }
        try {
            final AbsTask absTask2 = absTask;
            final Throwable t3 = doBackground;
            final boolean b = false;
            absTask2.onError(t3, b);
            return (T)doBackground;
        }
        finally {
            absTask.onFinished();
        }
    }
    
    @Override
    public <T extends AbsTask<?>> Callback.Cancelable startTasks(final Callback.GroupCallback<T> groupCallback, final T... array) {
        if (array != null) {
            final Runnable runnable = (Runnable)new Runnable(this, array, groupCallback) {
                private final AtomicInteger count = new AtomicInteger(0);
                final TaskControllerImpl this$0;
                private final int total = this.val$tasks.length;
                final Callback.GroupCallback val$groupCallback;
                final AbsTask[] val$tasks;
                
                public void run() {
                    if (this.count.incrementAndGet() == this.total) {
                        final Callback.GroupCallback val$groupCallback = this.val$groupCallback;
                        if (val$groupCallback != null) {
                            val$groupCallback.onAllFinished();
                        }
                    }
                }
            };
            for (final AbsTask<?> absTask : array) {
                this.start((AbsTask<Object>)new TaskControllerImpl$2(this, (AbsTask)absTask, (Callback.GroupCallback)groupCallback, (AbsTask)absTask, (Runnable)runnable));
            }
            return new Callback.Cancelable(this, array) {
                final TaskControllerImpl this$0;
                final AbsTask[] val$tasks;
                
                @Override
                public void cancel() {
                    final AbsTask[] val$tasks = this.val$tasks;
                    for (int length = val$tasks.length, i = 0; i < length; ++i) {
                        val$tasks[i].cancel();
                    }
                }
                
                @Override
                public boolean isCancelled() {
                    final AbsTask[] val$tasks = this.val$tasks;
                    final int length = val$tasks.length;
                    boolean b = true;
                    for (int i = 0; i < length; ++i) {
                        if (!val$tasks[i].isCancelled()) {
                            b = false;
                        }
                    }
                    return b;
                }
            };
        }
        throw new IllegalArgumentException("task must not be null");
    }
}
