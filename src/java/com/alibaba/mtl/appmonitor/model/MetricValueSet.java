package com.alibaba.mtl.appmonitor.model;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.mtl.appmonitor.a.f;
import java.util.Iterator;
import com.alibaba.mtl.appmonitor.c.a;
import java.util.Collections;
import java.util.HashMap;
import com.alibaba.mtl.appmonitor.a.d;
import java.util.Map;
import com.alibaba.mtl.appmonitor.c.b;

public class MetricValueSet implements b
{
    private Map<Metric, d> n;
    
    public MetricValueSet() {
        this.n = (Map<Metric, d>)Collections.synchronizedMap((Map)new HashMap());
    }
    
    public void clean() {
        final Iterator iterator = this.n.values().iterator();
        while (iterator.hasNext()) {
            a.a().a((b)iterator.next());
        }
        this.n.clear();
    }
    
    public void fill(final Object... array) {
        if (this.n == null) {
            this.n = (Map<Metric, d>)Collections.synchronizedMap((Map)new HashMap());
        }
    }
    
    public d getEvent(final Integer n, final String s, final String s2, final String s3, final Class<? extends d> clazz) {
        final int intValue = n;
        final int a = f.d.a();
        final int n2 = 0;
        Metric metric;
        if (intValue == a) {
            metric = MetricRepo.getRepo().getMetric(s, s2);
            final int n3 = 0;
        }
        else {
            metric = (Metric)com.alibaba.mtl.appmonitor.c.a.a().a((Class)Metric.class, new Object[] { s, s2, s3 });
            final int n3 = 1;
        }
        d d = null;
        if (metric != null) {
            Label_0172: {
                if (this.n.containsKey((Object)metric)) {
                    final d d2 = (d)this.n.get((Object)metric);
                    break Label_0172;
                }
                synchronized (MetricValueSet.class) {
                    final d d2 = (d)com.alibaba.mtl.appmonitor.c.a.a().a((Class)clazz, new Object[] { n, s, s2, s3 });
                    this.n.put((Object)metric, (Object)d2);
                    monitorexit(MetricValueSet.class);
                    final int n3 = n2;
                    d = d2;
                    if (n3 != 0) {
                        com.alibaba.mtl.appmonitor.c.a.a().a((b)metric);
                        d = d2;
                    }
                }
            }
        }
        return d;
    }
    
    public List<d> getEvents() {
        return (List<d>)new ArrayList(this.n.values());
    }
}
