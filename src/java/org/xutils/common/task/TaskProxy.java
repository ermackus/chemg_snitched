package org.xutils.common.task;

import org.xutils.common.Callback$CancelledException;
import org.xutils.common.Callback$Cancelable;
import java.util.concurrent.Executor;

class TaskProxy<ResultType> extends AbsTask<ResultType>
{
    private static final int MSG_WHAT_BASE = 1000000000;
    private static final int MSG_WHAT_ON_CANCEL = 1000000006;
    private static final int MSG_WHAT_ON_ERROR = 1000000004;
    private static final int MSG_WHAT_ON_FINISHED = 1000000007;
    private static final int MSG_WHAT_ON_START = 1000000002;
    private static final int MSG_WHAT_ON_SUCCESS = 1000000003;
    private static final int MSG_WHAT_ON_UPDATE = 1000000005;
    private static final int MSG_WHAT_ON_WAITING = 1000000001;
    static final PriorityExecutor sDefaultExecutor;
    static final TaskProxy.TaskProxy$InternalHandler sHandler;
    private volatile boolean callOnCanceled;
    private volatile boolean callOnFinished;
    private final Executor executor;
    private final AbsTask<ResultType> task;
    
    static {
        sHandler = new TaskProxy.TaskProxy$InternalHandler((TaskProxy$1)null);
        sDefaultExecutor = new PriorityExecutor(true);
    }
    
    TaskProxy(final AbsTask<ResultType> task) {
        super((Callback$Cancelable)task);
        this.callOnCanceled = false;
        this.callOnFinished = false;
        (this.task = task).setTaskProxy(this);
        this.setTaskProxy((TaskProxy)null);
        Object executor;
        if ((executor = task.getExecutor()) == null) {
            executor = TaskProxy.sDefaultExecutor;
        }
        this.executor = (Executor)executor;
    }
    
    protected final ResultType doBackground() throws Throwable {
        this.onWaiting();
        this.executor.execute((Runnable)new PriorityRunnable(this.task.getPriority(), (Runnable)new TaskProxy$1(this)));
        return null;
    }
    
    public final Executor getExecutor() {
        return this.executor;
    }
    
    public final Priority getPriority() {
        return this.task.getPriority();
    }
    
    protected void onCancelled(final Callback$CancelledException ex) {
        this.setState(AbsTask$State.CANCELLED);
        TaskProxy.sHandler.obtainMessage(1000000006, (Object)new TaskProxy.TaskProxy$ArgsObj(this, new Object[] { ex })).sendToTarget();
    }
    
    protected void onError(final Throwable t, final boolean b) {
        this.setState(AbsTask$State.ERROR);
        TaskProxy.sHandler.obtainMessage(1000000004, (Object)new TaskProxy.TaskProxy$ArgsObj(this, new Object[] { t })).sendToTarget();
    }
    
    protected void onFinished() {
        TaskProxy.sHandler.obtainMessage(1000000007, (Object)this).sendToTarget();
    }
    
    protected void onStarted() {
        this.setState(AbsTask$State.STARTED);
        TaskProxy.sHandler.obtainMessage(1000000002, (Object)this).sendToTarget();
    }
    
    protected void onSuccess(final ResultType resultType) {
        this.setState(AbsTask$State.SUCCESS);
        TaskProxy.sHandler.obtainMessage(1000000003, (Object)this).sendToTarget();
    }
    
    protected void onUpdate(final int n, final Object... array) {
        TaskProxy.sHandler.obtainMessage(1000000005, n, n, (Object)new TaskProxy.TaskProxy$ArgsObj(this, array)).sendToTarget();
    }
    
    protected void onWaiting() {
        this.setState(AbsTask$State.WAITING);
        TaskProxy.sHandler.obtainMessage(1000000001, (Object)this).sendToTarget();
    }
    
    final void setState(final AbsTask$State absTask$State) {
        super.setState(absTask$State);
        this.task.setState(absTask$State);
    }
}
