package com.kingagroot.kingdraw.core.tool;

import java.util.HashMap;
import android.os.CountDownTimer;
import com.kingagroot.kingdraw.core.model.ThreadTimerModel;
import java.util.Map;

public class KDThreadTool
{
    private static KDThreadTool instance;
    private Map<ThreadTimerModel, CountDownTimer> threadCache;
    
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    private KDThreadTool() {
        this.threadCache = (Map<ThreadTimerModel, CountDownTimer>)new HashMap();
    }
    
    public static KDThreadTool getInstance() {
        if (KDThreadTool.instance == null) {
            KDThreadTool.instance = new KDThreadTool();
        }
        return KDThreadTool.instance;
    }
    
    private static native void nativeThreadEnd(final ThreadTimerModel p0);
    
    private void threadEnd(final ThreadTimerModel threadTimerModel) {
        if (this.threadCache.containsKey((Object)threadTimerModel)) {
            this.threadCache.remove((Object)threadTimerModel);
        }
        nativeThreadEnd(threadTimerModel);
    }
    
    public void threadCancel(final ThreadTimerModel threadTimerModel) {
        if (this.threadCache.containsKey((Object)threadTimerModel)) {
            ((CountDownTimer)this.threadCache.get((Object)threadTimerModel)).cancel();
            this.threadCache.remove((Object)threadTimerModel);
        }
    }
    
    public void threadStart(final ThreadTimerModel threadTimerModel) {
        if (this.threadCache.containsKey((Object)threadTimerModel)) {
            ((CountDownTimer)this.threadCache.get((Object)threadTimerModel)).cancel();
            this.threadCache.remove((Object)threadTimerModel);
        }
        final long n = (long)(threadTimerModel.timerSecond * 1000.0f);
        final KDCountDownTimer kdCountDownTimer = new KDCountDownTimer(n, n, threadTimerModel);
        kdCountDownTimer.start();
        this.threadCache.put((Object)threadTimerModel, (Object)kdCountDownTimer);
    }
    
    private class KDCountDownTimer extends CountDownTimer
    {
        final KDThreadTool this$0;
        private ThreadTimerModel timerModel;
        
        public KDCountDownTimer(final KDThreadTool this$0, final long n, final long n2, final ThreadTimerModel timerModel) {
            this.this$0 = this$0;
            super(n, n2);
            this.timerModel = timerModel;
        }
        
        public void onFinish() {
            this.this$0.threadEnd(this.timerModel);
        }
        
        public void onTick(final long n) {
        }
    }
}
