package com.kingagroot.kingdraw.core.model;

import java.util.Objects;

public class ThreadTimerModel
{
    public String param;
    public String pid;
    public String threadId;
    public float timerSecond;
    
    public ThreadTimerModel() {
        this.threadId = "";
        this.timerSecond = 0.0f;
        this.pid = "";
        this.param = "";
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this == o) {
            return true;
        }
        if (o != null && this.getClass() == o.getClass()) {
            final ThreadTimerModel threadTimerModel = (ThreadTimerModel)o;
            if (Float.compare(threadTimerModel.timerSecond, this.timerSecond) != 0 || !Objects.equals((Object)this.threadId, (Object)threadTimerModel.threadId) || !Objects.equals((Object)this.pid, (Object)threadTimerModel.pid) || !Objects.equals((Object)this.param, (Object)threadTimerModel.param)) {
                b = false;
            }
            return b;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(new Object[] { this.threadId, this.timerSecond, this.pid, this.param });
    }
}
