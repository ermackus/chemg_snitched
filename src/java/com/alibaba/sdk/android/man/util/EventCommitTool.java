package com.alibaba.sdk.android.man.util;

import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import com.alibaba.sdk.android.man.MANTracker;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.sdk.android.man.customperf.MANCustomPerformance;

public class EventCommitTool
{
    private static final String TAG = "MAN_EventCommitTool";
    
    public static void commitCustomPerformanceEvent(final MANCustomPerformance manCustomPerformance) {
        if (Aggregation.getInstance().addCustomPerfToAggregation(manCustomPerformance)) {
            if (MANLog.isPrintLog()) {
                final StringBuilder sb = new StringBuilder();
                sb.append("ToAggregation : 66602, duration=");
                sb.append(manCustomPerformance.getDuration());
                sb.append(", label=");
                sb.append(manCustomPerformance.getEventLabel());
                MANLog.Logi("MAN_EventCommitTool", sb.toString());
            }
        }
        else {
            commitEventToUT("UT", 66602, manCustomPerformance.getEventLabel(), "", String.valueOf(manCustomPerformance.getDuration()), manCustomPerformance.getProperties());
        }
    }
    
    public static void commitEvent(final int n, final String s, final Map<String, String> map) {
        if (s != null && map != null) {
            if (Aggregation.getInstance().addToNetPerfAggregation(map)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("ToAggregation : ");
                sb.append(n);
                sb.append(", ");
                sb.append(map.toString());
                MANLog.Logi("MAN_EventCommitTool", sb.toString());
                return;
            }
            commitEventDirectly(n, s, map);
        }
        else {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("[commitEvent] - eventLabel: ");
            sb2.append(s);
            sb2.append(", property : ");
            sb2.append((Object)map);
            MANLog.Logw("MAN_EventCommitTool", sb2.toString());
        }
    }
    
    static void commitEventDirectly(final int n, final String s, final Map<String, String> map) {
        if (s != null && map != null) {
            commitEventToUT("UT", n, s, "", "", map);
        }
        else {
            final StringBuilder sb = new StringBuilder();
            sb.append("[commitEvent] - eventLabel: ");
            sb.append(s);
            sb.append(", property : ");
            sb.append((Object)map);
            MANLog.Logf("MAN_EventCommitTool", sb.toString());
        }
    }
    
    static void commitEventToUT(final String s, final int n, final String s2, final String s3, final String s4, final Map<String, String> map) {
        final Class<EventCommitTool> clazz;
        monitorenter(clazz = EventCommitTool.class);
        Object o = map;
        Label_0025: {
            if (map != null) {
                break Label_0025;
            }
            try {
                o = new HashMap();
                ((Map)o).put((Object)"MAS_VER", (Object)"MBAAS_MAS_ANDROID_1.2.4");
                if (MANLog.isPrintLog()) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("commitEventFinally : eventId=");
                    sb.append(n);
                    sb.append(", arg1=");
                    sb.append(s2);
                    sb.append(", arg2=");
                    sb.append(s3);
                    sb.append(", arg3=");
                    sb.append(s4);
                    sb.append(", ");
                    sb.append(o.toString());
                    MANLog.Logd("MAN_EventCommitTool", sb.toString());
                }
                MANTracker.getInstance().send((Map<String, String>)new UTOriginalCustomHitBuilder(s, n, s2, s3, s4, (Map)o).build());
            }
            finally {
                monitorexit(clazz);
            }
        }
    }
}
