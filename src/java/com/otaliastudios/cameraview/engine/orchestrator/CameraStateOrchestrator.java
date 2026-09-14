package com.otaliastudios.cameraview.engine.orchestrator;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Callable;
import java.util.Iterator;

public class CameraStateOrchestrator extends CameraOrchestrator
{
    private CameraState mCurrentState;
    private int mStateChangeCount;
    private CameraState mTargetState;
    
    public CameraStateOrchestrator(final CameraOrchestrator$Callback cameraOrchestrator$Callback) {
        super(cameraOrchestrator$Callback);
        this.mCurrentState = CameraState.OFF;
        this.mTargetState = CameraState.OFF;
        this.mStateChangeCount = 0;
    }
    
    public CameraState getCurrentState() {
        return this.mCurrentState;
    }
    
    public CameraState getTargetState() {
        return this.mTargetState;
    }
    
    public boolean hasPendingStateChange() {
        final Object mJobsLock = this.mJobsLock;
        synchronized (mJobsLock) {
            for (final CameraOrchestrator$Job cameraOrchestrator$Job : this.mJobs) {
                if ((cameraOrchestrator$Job.name.contains((CharSequence)" >> ") || cameraOrchestrator$Job.name.contains((CharSequence)" << ")) && !cameraOrchestrator$Job.source.getTask().isComplete()) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public <T> Task<T> scheduleStateChange(final CameraState cameraState, final CameraState mTargetState, final boolean b, final Callable<Task<T>> callable) {
        final int mStateChangeCount = this.mStateChangeCount + 1;
        this.mStateChangeCount = mStateChangeCount;
        this.mTargetState = mTargetState;
        final boolean b2 = mTargetState.isAtLeast(cameraState) ^ true;
        String s;
        if (b2) {
            final StringBuilder sb = new StringBuilder();
            sb.append(cameraState.name());
            sb.append(" << ");
            sb.append(mTargetState.name());
            s = sb.toString();
        }
        else {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(cameraState.name());
            sb2.append(" >> ");
            sb2.append(mTargetState.name());
            s = sb2.toString();
        }
        return (Task<T>)this.schedule(s, b, (Callable)new CameraStateOrchestrator$2(this, cameraState, s, mTargetState, (Callable)callable, b2)).addOnCompleteListener((OnCompleteListener)new OnCompleteListener<T>(this, mStateChangeCount) {
            final CameraStateOrchestrator this$0;
            final int val$changeCount;
            
            public void onComplete(final Task<T> task) {
                if (this.val$changeCount == this.this$0.mStateChangeCount) {
                    final CameraStateOrchestrator this$0 = this.this$0;
                    this$0.mTargetState = this$0.mCurrentState;
                }
            }
        });
    }
    
    public Task<Void> scheduleStateful(final String s, final CameraState cameraState, final Runnable runnable) {
        return (Task<Void>)this.schedule(s, true, (Runnable)new CameraStateOrchestrator$3(this, cameraState, runnable));
    }
    
    public void scheduleStatefulDelayed(final String s, final CameraState cameraState, final long n, final Runnable runnable) {
        this.scheduleDelayed(s, true, n, (Runnable)new CameraStateOrchestrator$4(this, cameraState, runnable));
    }
}
