package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.model.MetricRepo;
import java.util.HashMap;
import java.util.Iterator;
import com.alibaba.mtl.appmonitor.c.b;
import java.util.List;
import com.alibaba.mtl.appmonitor.model.Measure;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.appmonitor.model.MeasureValue;
import java.util.Map;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.Metric;

public class c extends d
{
    private static final Long a;
    private Metric a;
    private DimensionValueSet b;
    private MeasureValueSet b;
    private Long b;
    private Map<String, MeasureValue> i;
    
    static {
        a = 300000L;
    }
    
    public DimensionValueSet a() {
        return this.b;
    }
    
    public MeasureValueSet a() {
        return this.b;
    }
    
    public void a(final DimensionValueSet b) {
        final DimensionValueSet b2 = this.b;
        if (b2 == null) {
            this.b = b;
        }
        else {
            b2.addValues(b);
        }
    }
    
    public void a(final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.i.isEmpty()) {
            this.b = currentTimeMillis;
        }
        this.i.put((Object)s, (Object)com.alibaba.mtl.appmonitor.c.a.a().a((Class)MeasureValue.class, new Object[] { Double.valueOf(currentTimeMillis), Double.valueOf(currentTimeMillis - this.b) }));
    }
    
    public boolean a(final String s) {
        final MeasureValue measureValue = (MeasureValue)this.i.get((Object)s);
        if (measureValue != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            final String o = this.o;
            final String p = this.p;
            final double n = (double)currentTimeMillis;
            com.alibaba.mtl.log.d.i.a("DurationEvent", new Object[] { "statEvent consumeTime. module:", o, " monitorPoint:", p, " measureName:", s, " time:", n - measureValue.getValue() });
            measureValue.setValue(n - measureValue.getValue());
            measureValue.setFinish(true);
            this.b.setValue(s, measureValue);
            if (this.a.getMeasureSet().valid(this.b)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean c() {
        final long currentTimeMillis = System.currentTimeMillis();
        final List measures = this.a.getMeasureSet().getMeasures();
        if (measures != null) {
            for (int size = measures.size(), i = 0; i < size; ++i) {
                final Measure measure = (Measure)measures.get(i);
                if (measure != null) {
                    double doubleValue;
                    if (measure.getMax() != null) {
                        doubleValue = measure.getMax();
                    }
                    else {
                        doubleValue = c.a;
                    }
                    final MeasureValue measureValue = (MeasureValue)this.i.get((Object)measure.getName());
                    if (measureValue != null && !measureValue.isFinish() && currentTimeMillis - measureValue.getValue() > doubleValue) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public void clean() {
        super.clean();
        this.a = null;
        this.b = null;
        final Iterator iterator = this.i.values().iterator();
        while (iterator.hasNext()) {
            com.alibaba.mtl.appmonitor.c.a.a().a((b)iterator.next());
        }
        this.i.clear();
        if (this.b != null) {
            com.alibaba.mtl.appmonitor.c.a.a().a((b)this.b);
            this.b = null;
        }
        if (this.b != null) {
            com.alibaba.mtl.appmonitor.c.a.a().a((b)this.b);
            this.b = null;
        }
    }
    
    @Override
    public void fill(final Object... array) {
        super.fill(array);
        if (this.i == null) {
            this.i = (Map<String, MeasureValue>)new HashMap();
        }
        final Metric metric = MetricRepo.getRepo().getMetric(this.o, this.p);
        this.a = metric;
        if (metric.getDimensionSet() != null) {
            this.b = (DimensionValueSet)com.alibaba.mtl.appmonitor.c.a.a().a((Class)DimensionValueSet.class, new Object[0]);
            this.a.getDimensionSet().setConstantValue(this.b);
        }
        this.b = (MeasureValueSet)com.alibaba.mtl.appmonitor.c.a.a().a((Class)MeasureValueSet.class, new Object[0]);
    }
}
