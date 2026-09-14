package com.alibaba.mtl.appmonitor;

import java.util.List;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.a.e;
import java.util.HashMap;
import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.model.Measure;
import java.util.Map;
import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.appmonitor.a.f;
import android.content.Context;
import com.alibaba.mtl.log.sign.IRequestAuth;
import com.alibaba.mtl.log.sign.BaseRequestAuth;
import com.alibaba.mtl.log.sign.SecurityRequestAuth;
import com.alibaba.mtl.appmonitor.model.Metric;
import com.alibaba.mtl.appmonitor.model.MetricRepo;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.log.a;
import com.alibaba.mtl.log.d.l;
import com.alibaba.mtl.log.d.i;
import android.app.Application;

public final class AppMonitorDelegate
{
    public static final String DEFAULT_VALUE = "defaultValue";
    public static boolean IS_DEBUG = false;
    public static final String MAX_VALUE = "maxValue";
    public static final String MIN_VALUE = "minValue";
    public static final String TAG = "AppMonitorDelegate";
    private static Application b;
    static volatile boolean i;
    
    public static void destroy() {
        final Class<AppMonitorDelegate> clazz;
        monitorenter(clazz = AppMonitorDelegate.class);
        final Throwable t2;
        try {
            com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "start destory" });
            if (AppMonitorDelegate.i) {
                c.d();
                c.destroy();
                com.alibaba.mtl.appmonitor.b.destroy();
                if (AppMonitorDelegate.b != null) {
                    l.c(AppMonitorDelegate.b.getApplicationContext());
                }
                AppMonitorDelegate.i = false;
            }
            return;
        }
        finally {
            final Throwable t = t2;
            com.alibaba.mtl.appmonitor.b.b.a(t);
        }
        try {
            final Throwable t = t2;
            com.alibaba.mtl.appmonitor.b.b.a(t);
        }
        finally {
            monitorexit(clazz);
        }
    }
    
    public static void enableLog(final boolean b) {
        com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "[enableLog]" });
        com.alibaba.mtl.log.d.i.d(b);
    }
    
    public static void init(final Application b) {
        synchronized (AppMonitorDelegate.class) {
            com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "start init" });
            try {
                if (!AppMonitorDelegate.i) {
                    AppMonitorDelegate.b = b;
                    a.a(b.getApplicationContext());
                    b.init();
                    c.init();
                    com.alibaba.mtl.appmonitor.a.init(b);
                    l.b(b.getApplicationContext());
                    AppMonitorDelegate.i = true;
                }
            }
            finally {
                destroy();
            }
        }
    }
    
    public static void register(final String s, final String s2, final MeasureSet set) {
        register(s, s2, set, null);
    }
    
    public static void register(final String s, final String s2, final MeasureSet set, final DimensionSet set2) {
        register(s, s2, set, set2, false);
    }
    
    public static void register(final String s, final String s2, final MeasureSet set, final DimensionSet set2, final boolean b) {
        try {
            if (AppMonitorDelegate.i) {
                if (!com.alibaba.mtl.appmonitor.f.b.d(s) && !com.alibaba.mtl.appmonitor.f.b.d(s2)) {
                    MetricRepo.getRepo().add(new Metric(s, s2, set, set2, b));
                }
                else {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "register stat event. module: ", s, " monitorPoint: ", s2 });
                    if (!AppMonitorDelegate.IS_DEBUG) {
                        return;
                    }
                    throw new com.alibaba.mtl.appmonitor.b.a("register error. module and monitorPoint can't be null");
                }
            }
        }
        finally {
            final Throwable t;
            com.alibaba.mtl.appmonitor.b.b.a(t);
        }
    }
    
    public static void register(final String s, final String s2, final MeasureSet set, final boolean b) {
        register(s, s2, set, null, b);
    }
    
    public static void setChannel(final String channel) {
        a.setChannel(channel);
    }
    
    public static void setRequestAuthInfo(final boolean b, final String s, final String s2, final String s3) {
        Object o;
        if (b) {
            o = new SecurityRequestAuth(s, s3);
        }
        else {
            o = new BaseRequestAuth(s, s2, "1".equalsIgnoreCase(s3));
        }
        a.a((IRequestAuth)o);
        com.alibaba.mtl.log.a.a.a((Context)AppMonitorDelegate.b);
    }
    
    public static void setSampling(final int n) {
        int i = 0;
        com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "[setSampling]" });
        for (f[] values = f.values(); i < values.length; ++i) {
            final f f = values[i];
            f.c(n);
            j.a().a(f, n);
        }
    }
    
    public static void setStatisticsInterval(final int statisticsInterval) {
        for (final f f : f.values()) {
            f.setStatisticsInterval(statisticsInterval);
            setStatisticsInterval(f, statisticsInterval);
        }
    }
    
    static void setStatisticsInterval(final f f, final int n) {
        try {
            if (AppMonitorDelegate.i && f != null) {
                c.a(f.a(), n);
                if (n > 0) {
                    f.b(true);
                }
                else {
                    f.b(false);
                }
            }
        }
        finally {
            final Throwable t;
            com.alibaba.mtl.appmonitor.b.b.a(t);
        }
    }
    
    public static void triggerUpload() {
        final Class<AppMonitorDelegate> clazz;
        monitorenter(clazz = AppMonitorDelegate.class);
        final Throwable t2;
        try {
            com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "triggerUpload" });
            if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.f()) {
                c.d();
            }
            return;
        }
        finally {
            final Throwable t = t2;
            com.alibaba.mtl.appmonitor.b.b.a(t);
        }
        try {
            final Throwable t = t2;
            com.alibaba.mtl.appmonitor.b.b.a(t);
        }
        finally {
            monitorexit(clazz);
        }
    }
    
    public static void turnOffRealTimeDebug() {
        com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "[turnOffRealTimeDebug]" });
    }
    
    public static void turnOnRealTimeDebug(final Map<String, String> map) {
        com.alibaba.mtl.log.a.a.turnOnRealTimeDebug(map);
    }
    
    public static void updateMeasure(final String s, final String s2, final String s3, final double n, final double n2, final double n3) {
        com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "[updateMeasure]" });
        try {
            if (AppMonitorDelegate.i && !com.alibaba.mtl.appmonitor.f.b.d(s)) {
                if (!com.alibaba.mtl.appmonitor.f.b.d(s2)) {
                    final Metric metric = MetricRepo.getRepo().getMetric(s, s2);
                    if (metric != null && metric.getMeasureSet() != null) {
                        metric.getMeasureSet().upateMeasure(new Measure(s3, n3, n, n2));
                    }
                }
            }
        }
        catch (final Exception ex) {}
    }
    
    public static class Alarm
    {
        @Deprecated
        public static boolean checkSampled(final String s, final String s2) {
            return j.a(f.a, s, s2);
        }
        
        public static void commitFail(final String s, final String s2, final String s3, final String s4, final String s5, final Map<String, String> map) {
            try {
                if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", "module & monitorPoint must not null");
                    return;
                }
                com.alibaba.mtl.log.b.a.A();
                final HashMap hashMap = new HashMap();
                ((Map)hashMap).put((Object)"_status", (Object)"0");
                if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.f() && f.a.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(s, s2, false, (Map<String, String>)hashMap))) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "commitFail module:", s, " monitorPoint:", s2, " errorCode:", s4, "errorMsg:", s5 });
                    com.alibaba.mtl.log.b.a.B();
                    e.a().a(f.a.a(), s, s2, s3, s4, s5, map);
                }
                else {
                    com.alibaba.mtl.log.d.i.a("log discard !", "");
                }
            }
            finally {
                final Throwable t;
                com.alibaba.mtl.appmonitor.b.b.a(t);
            }
        }
        
        public static void commitFail(final String s, final String s2, final String s3, final String s4, final Map<String, String> map) {
            commitFail(s, s2, null, s3, s4, map);
        }
        
        public static void commitSuccess(final String s, final String s2, final String s3, final Map<String, String> map) {
            try {
                if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", "module & monitorPoint must not null");
                    return;
                }
                com.alibaba.mtl.log.b.a.A();
                if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.f() && f.a.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(s, s2, true, null))) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "commitSuccess module:", s, " monitorPoint:", s2 });
                    com.alibaba.mtl.log.b.a.B();
                    e.a().a(f.a.a(), s, s2, s3, map);
                }
                else {
                    com.alibaba.mtl.log.d.i.a("log discard !", "");
                }
            }
            finally {
                final Throwable t;
                com.alibaba.mtl.appmonitor.b.b.a(t);
            }
        }
        
        public static void commitSuccess(final String s, final String s2, final Map<String, String> map) {
            commitSuccess(s, s2, null, map);
        }
        
        public static void setSampling(final int n) {
            j.a().a(f.a, n);
        }
        
        public static void setStatisticsInterval(final int statisticsInterval) {
            f.a.setStatisticsInterval(statisticsInterval);
            AppMonitorDelegate.setStatisticsInterval(f.a, statisticsInterval);
        }
    }
    
    public static class Counter
    {
        @Deprecated
        public static boolean checkSampled(final String s, final String s2) {
            return j.a(f.b, s, s2);
        }
        
        public static void commit(final String s, final String s2, final double n, final Map<String, String> map) {
            commit(s, s2, null, n, map);
        }
        
        public static void commit(final String s, final String s2, final String s3, final double n, final Map<String, String> map) {
            try {
                if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", "module & monitorPoint must not null");
                    return;
                }
                com.alibaba.mtl.log.b.a.y();
                if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.f() && f.b.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(f.b, s, s2))) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "commitCount module: ", s, " monitorPoint: ", s2, " value: ", n });
                    com.alibaba.mtl.log.b.a.z();
                    e.a().a(f.b.a(), s, s2, s3, n, map);
                }
            }
            finally {
                final Throwable t;
                com.alibaba.mtl.appmonitor.b.b.a(t);
            }
        }
        
        public static void setSampling(final int n) {
            j.a().a(f.b, n);
        }
        
        public static void setStatisticsInterval(final int statisticsInterval) {
            f.b.setStatisticsInterval(statisticsInterval);
            AppMonitorDelegate.setStatisticsInterval(f.b, statisticsInterval);
        }
    }
    
    public static class OffLineCounter
    {
        @Deprecated
        public static boolean checkSampled(final String s, final String s2) {
            return j.a(f.c, s, s2);
        }
        
        public static void commit(final String s, final String s2, final double n) {
            try {
                if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", "module & monitorPoint must not null");
                    return;
                }
                com.alibaba.mtl.log.b.a.w();
                if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.f() && f.c.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(f.c, s, s2))) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "commitOffLineCount module: ", s, " monitorPoint: ", s2, " value: ", n });
                    com.alibaba.mtl.log.b.a.x();
                    e.a().a(f.c.a(), s, s2, null, n, null);
                }
            }
            finally {
                final Throwable t;
                com.alibaba.mtl.appmonitor.b.b.a(t);
            }
        }
        
        public static void setSampling(final int n) {
            j.a().a(f.c, n);
        }
        
        public static void setStatisticsInterval(final int statisticsInterval) {
            f.c.setStatisticsInterval(statisticsInterval);
            AppMonitorDelegate.setStatisticsInterval(f.c, statisticsInterval);
        }
    }
    
    public static class Stat
    {
        public static void begin(final String s, final String s2, final String s3) {
            try {
                if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.f() && f.d.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(f.d, s, s2))) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "statEvent begin. module: ", s, " monitorPoint: ", s2, " measureName: ", s3 });
                    e.a().a(f.d.a(), s, s2, s3);
                }
            }
            finally {
                final Throwable t;
                com.alibaba.mtl.appmonitor.b.b.a(t);
            }
        }
        
        @Deprecated
        public static boolean checkSampled(final String s, final String s2) {
            return j.a(f.d, s, s2);
        }
        
        public static void commit(final String s, final String s2, final double n, final Map<String, String> map) {
            commit(s, s2, null, n, map);
        }
        
        public static void commit(final String s, final String s2, final DimensionValueSet set, final double n, final Map<String, String> map) {
            try {
                if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", "module & monitorPoint must not null");
                    return;
                }
                com.alibaba.mtl.log.b.a.u();
                if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.f() && f.d.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(f.d, s, s2))) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "statEvent commit. module: ", s, " monitorPoint: ", s2 });
                    final Metric metric = MetricRepo.getRepo().getMetric(s, s2);
                    com.alibaba.mtl.log.b.a.v();
                    if (metric != null) {
                        final List<Measure> measures = metric.getMeasureSet().getMeasures();
                        if (measures.size() == 1) {
                            commit(s, s2, set, com.alibaba.mtl.appmonitor.c.a.a().a(MeasureValueSet.class, new Object[0]).setValue(((Measure)measures.get(0)).getName(), n), map);
                        }
                    }
                }
            }
            finally {
                final Throwable t;
                com.alibaba.mtl.appmonitor.b.b.a(t);
            }
        }
        
        public static void commit(final String s, final String s2, final DimensionValueSet set, final MeasureValueSet set2, final Map<String, String> map) {
            try {
                if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", "module & monitorPoint must not null");
                    return;
                }
                com.alibaba.mtl.log.b.a.u();
                Label_0135: {
                    if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.f() && f.d.isOpen()) {
                        if (!AppMonitorDelegate.IS_DEBUG) {
                            final f d = f.d;
                            Map map2;
                            if (set != null) {
                                map2 = set.getMap();
                            }
                            else {
                                map2 = null;
                            }
                            if (!j.a(d, s, s2, (Map<String, String>)map2)) {
                                break Label_0135;
                            }
                        }
                        com.alibaba.mtl.log.d.i.a("statEvent commit success", new Object[] { "statEvent commit. module: ", s, " monitorPoint: ", s2 });
                        com.alibaba.mtl.log.b.a.v();
                        e.a().a(f.d.a(), s, s2, set2, set, map);
                        return;
                    }
                }
                com.alibaba.mtl.log.d.i.a("statEvent commit failed,log discard", new Object[] { " ,. module: ", s, " monitorPoint: ", s2 });
            }
            finally {
                final Throwable t;
                com.alibaba.mtl.appmonitor.b.b.a(t);
            }
        }
        
        public static Transaction createTransaction(final String s, final String s2) {
            return createTransaction(s, s2, null);
        }
        
        public static Transaction createTransaction(final String s, final String s2, final DimensionValueSet set) {
            return new Transaction(f.d.a(), s, s2, set);
        }
        
        public static void end(final String s, final String s2, final String s3) {
            try {
                if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.f() && f.d.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(f.d, s, s2))) {
                    com.alibaba.mtl.log.d.i.a("AppMonitorDelegate", new Object[] { "statEvent end. module: ", s, " monitorPoint: ", s2, " measureName: ", s3 });
                    e.a().a(s, s2, s3);
                }
            }
            finally {
                final Throwable t;
                com.alibaba.mtl.appmonitor.b.b.a(t);
            }
        }
        
        public static void setSampling(final int n) {
            j.a().a(f.d, n);
        }
        
        public static void setStatisticsInterval(final int statisticsInterval) {
            f.d.setStatisticsInterval(statisticsInterval);
            AppMonitorDelegate.setStatisticsInterval(f.d, statisticsInterval);
        }
    }
}
