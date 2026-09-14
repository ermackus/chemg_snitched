package com.alibaba.mtl.appmonitor.model;

import java.util.ArrayList;
import java.util.List;

public class MetricRepo
{
    private static MetricRepo a;
    public List<Metric> metrics;
    
    private MetricRepo(final int n) {
        this.metrics = (List<Metric>)new ArrayList(n);
    }
    
    public static MetricRepo getRepo() {
        if (MetricRepo.a == null) {
            MetricRepo.a = new MetricRepo(3);
        }
        return MetricRepo.a;
    }
    
    public static MetricRepo getRepo(final int n) {
        return new MetricRepo(n);
    }
    
    public void add(final Metric metric) {
        if (!this.metrics.contains((Object)metric)) {
            this.metrics.add((Object)metric);
        }
    }
    
    public Metric getMetric(final String s, final String s2) {
        if (s != null && s2 != null) {
            final List<Metric> metrics = this.metrics;
            if (metrics != null) {
                for (int size = metrics.size(), i = 0; i < size; ++i) {
                    final Metric metric = (Metric)this.metrics.get(i);
                    if (metric != null && metric.getModule().equals((Object)s) && metric.getMonitorPoint().equals((Object)s2)) {
                        return metric;
                    }
                }
            }
        }
        return null;
    }
    
    public boolean remove(final Metric metric) {
        return !this.metrics.contains((Object)metric) || this.metrics.remove((Object)metric);
    }
}
