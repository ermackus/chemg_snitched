package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.appmonitor.model.DimensionSet;

public class AppMonitorStatTable
{
    private String o;
    private String p;
    
    public AppMonitorStatTable(final String o, final String p2) {
        this.o = o;
        this.p = p2;
    }
    
    public AppMonitorStatTable registerRowAndColumn(final DimensionSet set, final MeasureSet set2, final boolean b) {
        AppMonitor.register(this.o, this.p, set2, set, b);
        return this;
    }
    
    public AppMonitorStatTable update(final DimensionValueSet set, final MeasureValueSet set2) {
        AppMonitor.Stat.commit(this.o, this.p, set, set2);
        return this;
    }
}
