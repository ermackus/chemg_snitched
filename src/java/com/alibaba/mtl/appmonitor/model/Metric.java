package com.alibaba.mtl.appmonitor.model;

import java.util.UUID;
import java.util.Iterator;
import android.text.TextUtils;
import java.util.List;
import com.alibaba.mtl.appmonitor.c.b;

public class Metric implements b
{
    private DimensionSet b;
    private MeasureSet b;
    private boolean g;
    private String o;
    private String p;
    private String r;
    private String s;
    private String z;
    
    @Deprecated
    public Metric() {
        this.z = null;
    }
    
    public Metric(final String o, final String p5, final MeasureSet b, final DimensionSet b2, final boolean g) {
        this.z = null;
        this.o = o;
        this.p = p5;
        this.b = b2;
        this.b = b;
        this.s = null;
        this.g = g;
    }
    
    private Measure a(final String s, final List<Measure> list) {
        if (list != null) {
            for (final Measure measure : list) {
                if (TextUtils.equals((CharSequence)s, (CharSequence)measure.name)) {
                    return measure;
                }
            }
        }
        return null;
    }
    
    public void clean() {
        this.o = null;
        this.p = null;
        this.s = null;
        this.g = false;
        this.b = null;
        this.b = null;
        this.r = null;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final Metric metric = (Metric)o;
        final String s = this.s;
        if (s == null) {
            if (metric.s != null) {
                return false;
            }
        }
        else if (!s.equals((Object)metric.s)) {
            return false;
        }
        final String o2 = this.o;
        if (o2 == null) {
            if (metric.o != null) {
                return false;
            }
        }
        else if (!o2.equals((Object)metric.o)) {
            return false;
        }
        final String p = this.p;
        if (p == null) {
            if (metric.p != null) {
                return false;
            }
        }
        else if (!p.equals((Object)metric.p)) {
            return false;
        }
        return true;
    }
    
    public void fill(final Object... array) {
        this.o = (String)array[0];
        this.p = (String)array[1];
        if (array.length > 2) {
            this.s = (String)array[2];
        }
    }
    
    public DimensionSet getDimensionSet() {
        return this.b;
    }
    
    public MeasureSet getMeasureSet() {
        return this.b;
    }
    
    public String getModule() {
        return this.o;
    }
    
    public String getMonitorPoint() {
        return this.p;
    }
    
    public String getTransactionId() {
        synchronized (this) {
            if (this.r == null) {
                final StringBuilder sb = new StringBuilder();
                sb.append(UUID.randomUUID().toString());
                sb.append("$");
                sb.append(this.o);
                sb.append("$");
                sb.append(this.p);
                this.r = sb.toString();
            }
            return this.r;
        }
    }
    
    @Override
    public int hashCode() {
        final String s = this.s;
        int hashCode = 0;
        int hashCode2;
        if (s == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = s.hashCode();
        }
        final String o = this.o;
        int hashCode3;
        if (o == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = o.hashCode();
        }
        final String p = this.p;
        if (p != null) {
            hashCode = p.hashCode();
        }
        return ((hashCode2 + 31) * 31 + hashCode3) * 31 + hashCode;
    }
    
    public boolean isCommitDetail() {
        synchronized (this) {
            return "1".equalsIgnoreCase(this.z) || (!"0".equalsIgnoreCase(this.z) && this.g);
        }
    }
    
    public void resetTransactionId() {
        this.r = null;
    }
    
    public void setCommitDetailFromConfig(final String z) {
        synchronized (this) {
            this.z = z;
        }
    }
    
    public boolean valid(final DimensionValueSet set, final MeasureValueSet set2) {
        final DimensionSet b = this.b;
        final boolean b2 = true;
        final boolean b3 = b == null || b.valid(set);
        final MetricRepo repo = MetricRepo.getRepo();
        final StringBuilder sb = new StringBuilder();
        sb.append("config_prefix");
        sb.append(this.o);
        final String string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("config_prefix");
        sb2.append(this.p);
        final Metric metric = repo.getMetric(string, sb2.toString());
        if (metric != null && metric.getMeasureSet() != null && set2 != null && set2.getMap() != null && this.b != null) {
            final List measures = metric.getMeasureSet().getMeasures();
            for (final String s : set2.getMap().keySet()) {
                Measure measure;
                if ((measure = this.a(s, (List<Measure>)measures)) == null) {
                    measure = this.a(s, (List<Measure>)this.b.getMeasures());
                }
                if (measure == null || !measure.valid(set2.getValue(s))) {
                    return false;
                }
            }
            return b3;
        }
        final MeasureSet b4 = this.b;
        boolean b5 = b3;
        if (b4 != null) {
            b5 = (b3 && b4.valid(set2) && b2);
        }
        return b5;
    }
}
