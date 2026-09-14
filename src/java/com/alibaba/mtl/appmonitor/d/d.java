package com.alibaba.mtl.appmonitor.d;

import org.json.JSONArray;
import com.alibaba.mtl.appmonitor.f.b;
import java.util.Map;
import com.alibaba.mtl.log.d.i;
import org.json.JSONObject;

public class d extends h
{
    private int o;
    private int p;
    
    public d(final String s, final int n, final int n2) {
        super(s, 0);
        this.o = this.n;
        this.p = this.n;
    }
    
    protected void a(final JSONObject jsonObject) {
        super.a(jsonObject);
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
            i.a("AlarmModuleSampling", new Object[] { "[updateSelfSampling]", jsonObject, "successSampling:", value, "failSampling" });
        }
        catch (final Exception ex) {}
    }
    
    public boolean a(final int n, final String s, final Boolean b, final Map<String, String> map) {
        i.a("AlarmModuleSampling", new Object[] { "samplingSeed:", n, "isSuccess:", b, "successSampling:", this.o, "failSampling:", this.p });
        if (this.r != null) {
            final com.alibaba.mtl.appmonitor.d.i i = (com.alibaba.mtl.appmonitor.d.i)this.r.get((Object)s);
            if (i != null && i instanceof e) {
                return ((e)i).a(n, b, map);
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
        this.a(jsonObject);
        try {
            final JSONArray jsonArray = jsonObject.getJSONArray("monitorPoints");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); ++i) {
                    final JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    final String string = jsonObject2.getString("monitorPoint");
                    if (b.c(string)) {
                        com.alibaba.mtl.appmonitor.d.i j;
                        if ((j = (com.alibaba.mtl.appmonitor.d.i)this.r.get((Object)string)) == null) {
                            j = new e(string, this.o, this.p);
                            this.r.put((Object)string, (Object)j);
                        }
                        j.b(jsonObject2);
                    }
                }
            }
        }
        catch (final Exception ex) {}
    }
}
