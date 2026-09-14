package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.log.d.s;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.appmonitor.model.Metric;
import com.alibaba.mtl.appmonitor.model.MetricRepo;
import com.alibaba.mtl.log.model.LogField;
import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.appmonitor.f.b;
import java.util.concurrent.ConcurrentHashMap;
import com.alibaba.mtl.appmonitor.model.MetricValueSet;
import com.alibaba.mtl.appmonitor.model.UTDimensionValueSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class e
{
    private static e a;
    private AtomicInteger a;
    private AtomicInteger b;
    private AtomicInteger c;
    private Map<String, c> j;
    private Map<UTDimensionValueSet, MetricValueSet> k;
    
    private e() {
        this.a = new AtomicInteger(0);
        this.b = new AtomicInteger(0);
        this.c = new AtomicInteger(0);
        this.k = (Map<UTDimensionValueSet, MetricValueSet>)new ConcurrentHashMap();
        this.j = (Map<String, c>)new ConcurrentHashMap();
    }
    
    private d a(final UTDimensionValueSet set, final String s, final String s2, final String s3, final Class<? extends d> clazz) {
        if (com.alibaba.mtl.appmonitor.f.b.c(s) && com.alibaba.mtl.appmonitor.f.b.c(s2)) {
            final Integer eventId = set.getEventId();
            if (eventId != null) {
                final Map<UTDimensionValueSet, MetricValueSet> k = this.k;
                synchronized (k) {
                    MetricValueSet set2;
                    if ((set2 = (MetricValueSet)this.k.get((Object)set)) == null) {
                        set2 = com.alibaba.mtl.appmonitor.c.a.a().a(MetricValueSet.class, new Object[0]);
                        this.k.put((Object)set, (Object)set2);
                    }
                    monitorexit(k);
                    return set2.getEvent(eventId, s, s2, s3, (Class)clazz);
                }
            }
        }
        return null;
    }
    
    public static e a() {
        synchronized (e.class) {
            if (e.a == null) {
                e.a = new e();
            }
            return e.a;
        }
    }
    
    private UTDimensionValueSet a(final int n, final Map<String, String> map) {
        final UTDimensionValueSet set = com.alibaba.mtl.appmonitor.c.a.a().a(UTDimensionValueSet.class, new Object[0]);
        if (map != null) {
            set.setMap((Map)map);
        }
        set.setValue(LogField.ACCESS.toString(), com.alibaba.mtl.log.a.b());
        set.setValue(LogField.ACCESS_SUBTYPE.toString(), com.alibaba.mtl.log.a.c());
        set.setValue(LogField.USERID.toString(), com.alibaba.mtl.log.a.d());
        set.setValue(LogField.USERNICK.toString(), com.alibaba.mtl.log.a.e());
        set.setValue(LogField.EVENTID.toString(), String.valueOf(n));
        return set;
    }
    
    private String a(final String s, final String s2) {
        final Metric metric = MetricRepo.getRepo().getMetric(s, s2);
        if (metric != null) {
            return metric.getTransactionId();
        }
        return null;
    }
    
    private void a(final f f, final AtomicInteger atomicInteger) {
        final int incrementAndGet = atomicInteger.incrementAndGet();
        i.a("EventRepo", f.toString(), " EVENT size:", String.valueOf(incrementAndGet));
        if (incrementAndGet >= f.b()) {
            i.a("EventRepo", new Object[] { f.toString(), " event size exceed trigger count." });
            atomicInteger.set(0);
            this.a(f.a());
        }
    }
    
    private void b(final String s, final String s2) {
        final Metric metric = MetricRepo.getRepo().getMetric(s, s2);
        if (metric != null) {
            metric.resetTransactionId();
        }
    }
    
    public Map<UTDimensionValueSet, List<d>> a(final int n) {
        final HashMap hashMap = new HashMap();
        final Map<UTDimensionValueSet, MetricValueSet> k = this.k;
        synchronized (k) {
            final ArrayList list = new ArrayList((Collection)this.k.keySet());
            for (int size = ((List)list).size(), i = 0; i < size; ++i) {
                final UTDimensionValueSet set = (UTDimensionValueSet)((List)list).get(i);
                if (set != null && set.getEventId() == n) {
                    ((Map)hashMap).put((Object)set, (Object)((MetricValueSet)this.k.get((Object)set)).getEvents());
                    this.k.remove((Object)set);
                }
            }
            return (Map<UTDimensionValueSet, List<d>>)hashMap;
        }
    }
    
    public void a(final int n) {
        s.a().b((Runnable)new Runnable(this, this.a(n)) {
            final e b;
            final Map l;
            
            public void run() {
                com.alibaba.mtl.appmonitor.f.c.b((Map<UTDimensionValueSet, List<d>>)this.l);
            }
        });
    }
    
    public void a(final int n, final String s, final String s2, final MeasureValueSet constantValue, final DimensionValueSet constantValue2, final Map<String, String> map) {
        final Metric metric = MetricRepo.getRepo().getMetric(s, s2);
        if (metric != null) {
            if (metric.getDimensionSet() != null) {
                metric.getDimensionSet().setConstantValue(constantValue2);
            }
            if (metric.getMeasureSet() != null) {
                metric.getMeasureSet().setConstantValue(constantValue);
            }
            final UTDimensionValueSet a = this.a(n, map);
            ((g)this.a(a, s, s2, null, (Class<? extends d>)g.class)).a(constantValue2, constantValue);
            if (com.alibaba.mtl.log.a.a.e()) {
                final g g = com.alibaba.mtl.appmonitor.c.a.a().a(g.class, n, s, s2);
                g.a(constantValue2, constantValue);
                com.alibaba.mtl.appmonitor.f.c.a(a, (d)g);
            }
            this.a(f.a(n), this.c);
        }
        else {
            i.a("EventRepo", "metric is null");
        }
    }
    
    public void a(final int n, final String s, final String s2, final String s3, final double n2, final Map<String, String> map) {
        final UTDimensionValueSet a = this.a(n, map);
        ((com.alibaba.mtl.appmonitor.a.b)this.a(a, s, s2, s3, (Class<? extends d>)com.alibaba.mtl.appmonitor.a.b.class)).a(n2);
        if (com.alibaba.mtl.log.a.a.e()) {
            final com.alibaba.mtl.appmonitor.a.b b = com.alibaba.mtl.appmonitor.c.a.a().a(com.alibaba.mtl.appmonitor.a.b.class, n, s, s2, s3);
            b.a(n2);
            com.alibaba.mtl.appmonitor.f.c.a(a, (d)b);
        }
        this.a(f.a(n), this.b);
    }
    
    public void a(final int n, final String s, final String s2, final String s3, final String s4, final String s5, final Map<String, String> map) {
        final UTDimensionValueSet a = this.a(n, map);
        final com.alibaba.mtl.appmonitor.a.a a2 = (com.alibaba.mtl.appmonitor.a.a)this.a(a, s, s2, s3, (Class<? extends d>)com.alibaba.mtl.appmonitor.a.a.class);
        a2.f();
        a2.a(s4, s5);
        if (com.alibaba.mtl.log.a.a.e()) {
            final com.alibaba.mtl.appmonitor.a.a a3 = com.alibaba.mtl.appmonitor.c.a.a().a(com.alibaba.mtl.appmonitor.a.a.class, n, s, s2, s3);
            a3.f();
            a3.a(s4, s5);
            com.alibaba.mtl.appmonitor.f.c.a(a, (d)a3);
        }
        this.a(f.a(n), this.a);
    }
    
    public void a(final int n, final String s, final String s2, final String s3, final Map<String, String> map) {
        final UTDimensionValueSet a = this.a(n, map);
        ((com.alibaba.mtl.appmonitor.a.a)this.a(a, s, s2, s3, (Class<? extends d>)com.alibaba.mtl.appmonitor.a.a.class)).e();
        if (com.alibaba.mtl.log.a.a.e()) {
            final com.alibaba.mtl.appmonitor.a.a a2 = com.alibaba.mtl.appmonitor.c.a.a().a(com.alibaba.mtl.appmonitor.a.a.class, n, s, s2, s3);
            a2.e();
            com.alibaba.mtl.appmonitor.f.c.a(a, (d)a2);
        }
        this.a(f.a(n), this.a);
    }
    
    public void a(final Integer n, final String s, final String s2, final String s3) {
        final String a = this.a(s, s2);
        if (a != null) {
            this.a(a, n, s, s2, s3);
        }
    }
    
    public void a(final String s, final Integer n, final String s2, final String s3, final DimensionValueSet set) {
        synchronized (c.class) {
            c c;
            if ((c = (c)this.j.get((Object)s)) == null) {
                c = com.alibaba.mtl.appmonitor.c.a.a().a(c.class, n, s2, s3);
                this.j.put((Object)s, (Object)c);
            }
            monitorexit(c.class);
            c.a(set);
        }
    }
    
    public void a(final String s, final Integer n, final String s2, final String s3, final String s4) {
        final Metric metric = MetricRepo.getRepo().getMetric(s2, s3);
        if (metric != null && metric.getMeasureSet() != null && metric.getMeasureSet().getMeasure(s4) != null) {
            synchronized (c.class) {
                c c;
                if ((c = (c)this.j.get((Object)s)) == null) {
                    c = com.alibaba.mtl.appmonitor.c.a.a().a(c.class, n, s2, s3);
                    this.j.put((Object)s, (Object)c);
                }
                monitorexit(c.class);
                c.a(s4);
            }
        }
    }
    
    public void a(String a, final String s, final String s2) {
        a = this.a(a, s);
        if (a != null) {
            this.a(a, s2, true, null);
        }
    }
    
    public void a(final String s, final String s2, final boolean b, final Map<String, String> map) {
        final c c = (c)this.j.get((Object)s);
        if (c != null && c.a(s2)) {
            this.j.remove((Object)s);
            if (b) {
                this.b(c.o, c.p);
            }
            this.a(c.e, c.o, c.p, c.a(), c.a(), map);
            com.alibaba.mtl.appmonitor.c.a.a().a(c);
        }
    }
    
    public void g() {
        final ArrayList list = new ArrayList((Collection)this.j.keySet());
        for (int size = ((List)list).size(), i = 0; i < size; ++i) {
            final String s = (String)((List)list).get(i);
            final c c = (c)this.j.get((Object)s);
            if (c != null && c.c()) {
                this.j.remove((Object)s);
            }
        }
    }
}
