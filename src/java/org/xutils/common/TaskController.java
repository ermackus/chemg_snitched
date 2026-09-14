package org.xutils.common;

import org.xutils.common.task.AbsTask;

public interface TaskController
{
    void autoPost(final Runnable p0);
    
    void post(final Runnable p0);
    
    void postDelayed(final Runnable p0, final long p1);
    
    void removeCallbacks(final Runnable p0);
    
    void run(final Runnable p0);
    
     <T> AbsTask<T> start(final AbsTask<T> p0);
    
     <T> T startSync(final AbsTask<T> p0) throws Throwable;
    
     <T extends AbsTask<?>> Callback.Cancelable startTasks(final Callback.GroupCallback<T> p0, final T... p1);
}
