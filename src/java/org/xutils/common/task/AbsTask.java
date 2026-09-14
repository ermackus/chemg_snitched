package org.xutils.common.task;

import java.util.concurrent.Executor;
import org.xutils.common.Callback;

public abstract class AbsTask<ResultType> implements Cancelable
{
    private final Cancelable cancelHandler;
    private volatile boolean isCancelled;
    private ResultType result;
    private volatile State state;
    private TaskProxy taskProxy;
    
    public AbsTask() {
        this(null);
    }
    
    public AbsTask(final Cancelable cancelHandler) {
        this.taskProxy = null;
        this.isCancelled = false;
        this.state = State.IDLE;
        this.cancelHandler = cancelHandler;
    }
    
    @Override
    public final void cancel() {
        synchronized (this) {
            if (!this.isCancelled) {
                this.isCancelled = true;
                this.cancelWorks();
                if (this.cancelHandler != null && !this.cancelHandler.isCancelled()) {
                    this.cancelHandler.cancel();
                }
                if (this.state == State.WAITING || (this.state == State.STARTED && this.isCancelFast())) {
                    if (this.taskProxy != null) {
                        this.taskProxy.onCancelled(new Callback.CancelledException("cancelled by user"));
                        this.taskProxy.onFinished();
                    }
                    else if (this instanceof TaskProxy) {
                        this.onCancelled(new Callback.CancelledException("cancelled by user"));
                        this.onFinished();
                    }
                }
            }
        }
    }
    
    protected void cancelWorks() {
    }
    
    protected abstract ResultType doBackground() throws Throwable;
    
    public Executor getExecutor() {
        return null;
    }
    
    public Priority getPriority() {
        return null;
    }
    
    public final ResultType getResult() {
        return this.result;
    }
    
    public final State getState() {
        return this.state;
    }
    
    protected boolean isCancelFast() {
        return false;
    }
    
    @Override
    public final boolean isCancelled() {
        if (!this.isCancelled && this.state != State.CANCELLED) {
            final Cancelable cancelHandler = this.cancelHandler;
            if (cancelHandler == null || !cancelHandler.isCancelled()) {
                return false;
            }
        }
        return true;
    }
    
    public final boolean isFinished() {
        return this.state.value() > State.STARTED.value();
    }
    
    protected void onCancelled(final CancelledException ex) {
    }
    
    protected abstract void onError(final Throwable p0, final boolean p1);
    
    protected void onFinished() {
    }
    
    protected void onStarted() {
    }
    
    protected abstract void onSuccess(final ResultType p0);
    
    protected void onUpdate(final int n, final Object... array) {
    }
    
    protected void onWaiting() {
    }
    
    final void setResult(final ResultType result) {
        this.result = result;
    }
    
    void setState(final State state) {
        this.state = state;
    }
    
    final void setTaskProxy(final TaskProxy taskProxy) {
        this.taskProxy = taskProxy;
    }
    
    protected final void update(final int n, final Object... array) {
        final TaskProxy taskProxy = this.taskProxy;
        if (taskProxy != null) {
            taskProxy.onUpdate(n, array);
        }
    }
    
    public enum State
    {
        private static final State[] $VALUES;
        
        CANCELLED(4), 
        ERROR(5), 
        IDLE(0), 
        STARTED(2), 
        SUCCESS(3), 
        WAITING(1);
        
        private final int value;
        
        private State(final int value) {
            this.value = value;
        }
        
        public int value() {
            return this.value;
        }
    }
}
