package org.xutils.common.task;

class PriorityRunnable implements Runnable
{
    long SEQ;
    public final Priority priority;
    private final Runnable runnable;
    
    public PriorityRunnable(final Priority priority, final Runnable runnable) {
        Priority default1 = priority;
        if (priority == null) {
            default1 = Priority.DEFAULT;
        }
        this.priority = default1;
        this.runnable = runnable;
    }
    
    public final void run() {
        this.runnable.run();
    }
}
