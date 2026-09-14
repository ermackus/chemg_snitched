package com.alibaba.sdk.android.man.util;

import com.alibaba.sdk.android.man.customperf.MANCustomPerformance;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.Map$Entry;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.ArrayList;
import java.util.Map;

public class Aggregation
{
    private static String tag = "MAN_Aggregation";
    private final String AGGREGATION_CUSTOM_PERFORMANCE_LABLE;
    private final String AGGREGATION_NETWORK_PERFORMANCE_LABLE;
    private final Map<String, AggregationSend> hashMap;
    private final ArrayList<String> networkDefineKey;
    private Timer timer;
    private AggregationTimerTask timerTask;
    private long totalNum;
    
    private Aggregation() {
        this.AGGREGATION_NETWORK_PERFORMANCE_LABLE = "AGGREGATION_3002";
        this.AGGREGATION_CUSTOM_PERFORMANCE_LABLE = "AGGREGATION_66602";
        this.totalNum = 0L;
        this.hashMap = (Map<String, AggregationSend>)new LinkedHashMap<String, AggregationSend>() {
            private static final long serialVersionUID = 201503121136L;
            final Aggregation this$0;
            
            protected boolean removeEldestEntry(final Map$Entry<String, AggregationSend> map$Entry) {
                return this.size() > 200;
            }
        };
        (this.networkDefineKey = (ArrayList<String>)new ArrayList()).add((Object)"singleConnectTime");
        this.networkDefineKey.add((Object)"firstPacketRT");
        this.networkDefineKey.add((Object)"singleRequestRT");
        this.networkDefineKey.add((Object)"singleRequestBytes");
        this.networkDefineKey.add((Object)"Host");
        this.networkDefineKey.add((Object)"Method");
        this.timerTask = new AggregationTimerTask();
        (this.timer = new Timer()).schedule((TimerTask)this.timerTask, 30000L, 30000L);
    }
    
    private long convertTimeStr2Long(final String s) {
        try {
            return Long.valueOf(s);
        }
        catch (final NumberFormatException ex) {
            return -1L;
        }
    }
    
    public static Aggregation getInstance() {
        return Singleton.instance;
    }
    
    private boolean isOnlyContainsDefineKey(final Map<String, String> map) {
        if (map == null) {
            return false;
        }
        final Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            if (!this.networkDefineKey.contains((Object)iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    private void submitAggregation() {
        final Map<String, AggregationSend> hashMap = this.hashMap;
        synchronized (hashMap) {
            this.totalNum = 0L;
            final Iterator iterator = this.hashMap.keySet().iterator();
            while (iterator.hasNext()) {
                final String s = (String)iterator.next();
                if (this.hashMap.get((Object)s) != null) {
                    ((AggregationSend)this.hashMap.get((Object)s)).send();
                }
                iterator.remove();
            }
        }
    }
    
    public boolean addCustomPerfToAggregation(final MANCustomPerformance manCustomPerformance) {
        if (manCustomPerformance.getProperties() != null && manCustomPerformance.getProperties().size() != 0) {
            return false;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("AGGREGATION_66602");
        sb.append(manCustomPerformance.getEventLabel());
        final String string = sb.toString();
        final Map<String, AggregationSend> hashMap = this.hashMap;
        synchronized (hashMap) {
            Aggregation.Aggregation$AggregationCustomPerf aggregation$AggregationCustomPerf;
            if ((aggregation$AggregationCustomPerf = (Aggregation.Aggregation$AggregationCustomPerf)this.hashMap.get((Object)string)) == null) {
                aggregation$AggregationCustomPerf = new Aggregation.Aggregation$AggregationCustomPerf(this, manCustomPerformance.getEventLabel());
                this.hashMap.put((Object)string, (Object)aggregation$AggregationCustomPerf);
            }
            aggregation$AggregationCustomPerf.addCustomPerf(manCustomPerformance.getDuration());
            final long totalNum = this.totalNum + 1L;
            this.totalNum = totalNum;
            if (totalNum >= 100L) {
                this.submitAggregation();
            }
            return true;
        }
    }
    
    public boolean addToNetPerfAggregation(final Map<String, String> map) {
        if (!this.isOnlyContainsDefineKey(map)) {
            return false;
        }
        final long convertTimeStr2Long = this.convertTimeStr2Long((String)map.get((Object)"singleConnectTime"));
        final long convertTimeStr2Long2 = this.convertTimeStr2Long((String)map.get((Object)"firstPacketRT"));
        final long convertTimeStr2Long3 = this.convertTimeStr2Long((String)map.get((Object)"singleRequestRT"));
        long longValue;
        try {
            longValue = Long.valueOf((String)map.get((Object)"singleRequestBytes"));
        }
        catch (final NumberFormatException ex) {
            longValue = 0L;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("AGGREGATION_3002");
        sb.append((String)map.get((Object)"Host"));
        sb.append((String)map.get((Object)"Method"));
        final String string = sb.toString();
        final Map<String, AggregationSend> hashMap = this.hashMap;
        synchronized (hashMap) {
            final Aggregation.Aggregation$AggregationNetworkPerformance aggregation$AggregationNetworkPerformance = (Aggregation.Aggregation$AggregationNetworkPerformance)this.hashMap.get((Object)string);
            if (aggregation$AggregationNetworkPerformance != null) {
                aggregation$AggregationNetworkPerformance.addNetworkPerformance(convertTimeStr2Long3, convertTimeStr2Long, convertTimeStr2Long2, longValue, (String)map.get((Object)"Method"), (String)map.get((Object)"Host"));
            }
            else {
                final Aggregation.Aggregation$AggregationNetworkPerformance aggregation$AggregationNetworkPerformance2 = new Aggregation.Aggregation$AggregationNetworkPerformance(this, (Aggregation$1)null);
                aggregation$AggregationNetworkPerformance2.addNetworkPerformance(convertTimeStr2Long3, convertTimeStr2Long, convertTimeStr2Long2, longValue, (String)map.get((Object)"Method"), (String)map.get((Object)"Host"));
                this.hashMap.put((Object)string, (Object)aggregation$AggregationNetworkPerformance2);
            }
            final long totalNum = this.totalNum + 1L;
            this.totalNum = totalNum;
            if (totalNum >= 100L) {
                this.submitAggregation();
            }
            return true;
        }
    }
    
    interface AggregationSend
    {
        void send();
    }
    
    private class AggregationTimerTask extends TimerTask
    {
        final Aggregation this$0;
        
        private AggregationTimerTask(final Aggregation this$0) {
            this.this$0 = this$0;
        }
        
        public void run() {
            MANLog.Logi(Aggregation.tag, "timer alive.");
            Aggregation.getInstance().submitAggregation();
        }
    }
    
    private static class Singleton
    {
        static Aggregation instance;
        
        static {
            Singleton.instance = new Aggregation(null);
        }
    }
}
