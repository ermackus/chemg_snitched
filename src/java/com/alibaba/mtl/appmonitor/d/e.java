package com.alibaba.mtl.appmonitor.d;

import org.json.JSONObject;
import java.util.Iterator;
import java.util.Map;

public class e extends i
{
    private int o;
    private int p;
    
    public e(final String s, final int o, final int p3) {
        super(s, 0);
        this.o = o;
        this.p = p3;
    }
    
    public boolean a(final int n, final Boolean b, final Map<String, String> map) {
        com.alibaba.mtl.log.d.i.a("AlarmMonitorPointSampling", new Object[] { "samplingSeed:", n, "isSuccess:", b, "successSampling:", this.o, "failSampling:", this.p });
        if (this.e != null && map != null) {
            final Iterator iterator = this.e.iterator();
            while (iterator.hasNext()) {
                final Boolean a = ((c)iterator.next()).a(n, map);
                if (a != null) {
                    return a;
                }
            }
        }
        return this.a(n, b);
    }
    
    protected boolean a(final int n, final boolean b) {
        final boolean b2 = true;
        final boolean b3 = true;
        if (b) {
            return n < this.o && b3;
        }
        return n < this.p && b2;
    }
    
    @Override
    public void b(final JSONObject jsonObject) {
        super.b(jsonObject);
        this.o = this.n;
        this.p = this.n;
        try {
            final Integer value = jsonObject.getInt("successSampling");
            if (value != null) {
                this.o = value;
            }
            final Integer value2 = jsonObject.getInt("failSampling");
            if (value2 != null) {
                this.p = value2;
            }
            com.alibaba.mtl.log.d.i.a("AlarmMonitorPointSampling", new Object[] { "[updateSelfSampling]", jsonObject, "successSampling:", value, "failSampling", value2 });
        }
        catch (final Exception ex) {}
    }
}
