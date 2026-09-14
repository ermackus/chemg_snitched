package com.alibaba.sdk.android.man.customperf;

import java.util.HashMap;
import java.util.Map;

public class MANCustomPerformance
{
    public static final String TAG = "MAN_MANCustomPerformance";
    private long duration;
    private String eventLabel;
    private Map<String, String> properties;
    
    public MANCustomPerformance() {
        this.duration = -1L;
        this.eventLabel = null;
        this.properties = (Map<String, String>)new HashMap();
    }
    
    public long getDuration() {
        return this.duration;
    }
    
    public String getEventLabel() {
        return this.eventLabel;
    }
    
    public Map<String, String> getProperties() {
        return this.properties;
    }
    
    public void setDuration(final long duration) {
        this.duration = duration;
    }
    
    public void setEventLabel(final String eventLabel) {
        this.eventLabel = eventLabel;
    }
}
