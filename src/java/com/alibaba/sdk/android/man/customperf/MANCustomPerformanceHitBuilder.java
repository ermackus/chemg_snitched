package com.alibaba.sdk.android.man.customperf;

import com.alibaba.sdk.android.man.util.ToolKit;
import com.alibaba.sdk.android.man.util.MANLog;
import java.util.regex.Pattern;

public class MANCustomPerformanceHitBuilder
{
    private static final String TAG = "MAN_MANCustomPerformanceHitBuilder";
    static Pattern pattern;
    long beginTime;
    MANCustomPerformance performance;
    
    static {
        MANCustomPerformanceHitBuilder.pattern = Pattern.compile("[A-Za-z0-9_]*");
    }
    
    public MANCustomPerformanceHitBuilder(final String eventLabel) {
        this.beginTime = -1L;
        this.performance = new MANCustomPerformance();
        if (MANCustomPerformanceHitBuilder.pattern.matcher((CharSequence)eventLabel).matches()) {
            this.performance.setEventLabel(eventLabel);
        }
        else {
            final StringBuilder sb = new StringBuilder();
            sb.append("eventLabel illegal \uff1a");
            sb.append(eventLabel);
            MANLog.Loge("MAN_MANCustomPerformanceHitBuilder", sb.toString());
        }
    }
    
    public MANCustomPerformance build() {
        return this.performance;
    }
    
    public MANCustomPerformanceHitBuilder hitEnd() {
        if (this.beginTime != -1L) {
            this.performance.setDuration(System.currentTimeMillis() - this.beginTime);
            final StringBuilder sb = new StringBuilder();
            sb.append("performance.duration = ");
            sb.append(this.performance.getDuration());
            MANLog.Logd("MAN_MANCustomPerformanceHitBuilder", sb.toString());
        }
        else {
            MANLog.Loge("MAN_MANCustomPerformanceHitBuilder", "Without hitBegin");
        }
        return this;
    }
    
    public MANCustomPerformanceHitBuilder hitStart() {
        this.beginTime = System.currentTimeMillis();
        return this;
    }
    
    public MANCustomPerformanceHitBuilder setDuration(final long duration) {
        this.performance.setDuration(duration);
        return this;
    }
    
    public MANCustomPerformanceHitBuilder withExtraInfo(final String s, final String s2) {
        if (!ToolKit.isNullOrEmpty(s) && !ToolKit.isNullOrEmpty(s2)) {
            this.performance.getProperties().put((Object)s, (Object)s2);
        }
        return this;
    }
}
