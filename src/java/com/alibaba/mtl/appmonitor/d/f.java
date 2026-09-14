package com.alibaba.mtl.appmonitor.d;

import org.json.JSONArray;
import com.alibaba.mtl.appmonitor.f.b;
import com.alibaba.mtl.log.d.i;
import java.util.Map;
import org.json.JSONObject;

public class f extends g
{
    String TAG;
    private int o;
    private int p;
    
    public f(final com.alibaba.mtl.appmonitor.a.f f, final int n) {
        super(f, n);
        this.TAG = "AlarmSampling";
        this.o = 0;
        this.p = 0;
        this.o = n;
        this.p = n;
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
        }
        catch (final Exception ex) {}
    }
    
    public boolean a(final int n, final String s, final String s2, final Boolean b, final Map<String, String> map) {
        final String tag = this.TAG;
        final boolean b2 = false;
        boolean b3 = false;
        final int o = this.o;
        final StringBuilder sb = new StringBuilder();
        sb.append("failSampling:");
        sb.append(this.p);
        i.a(tag, new Object[] { "samplingSeed:", n, "isSuccess:", b, "successSampling:", o, sb.toString() });
        if (this.q != null) {
            final h h = (h)this.q.get((Object)s);
            if (h != null && h instanceof d) {
                return ((d)h).a(n, s2, b, map);
            }
        }
        if (b) {
            if (n < this.o) {
                b3 = true;
            }
            return b3;
        }
        boolean b4 = b2;
        if (n < this.p) {
            b4 = true;
        }
        return b4;
    }
    
    @Override
    public void b(final JSONObject jsonObject) {
        this.a(jsonObject);
        this.c(jsonObject);
        this.q.clear();
        try {
            final JSONArray jsonArray = jsonObject.getJSONArray("metrics");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); ++i) {
                    final JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    final String string = jsonObject2.getString("module");
                    if (b.c(string)) {
                        h h;
                        if ((h = (h)this.q.get((Object)string)) == null) {
                            h = new d(string, this.o, this.p);
                            this.q.put((Object)string, (Object)h);
                        }
                        h.b(jsonObject2);
                    }
                }
            }
        }
        catch (final Exception ex) {}
    }
    
    @Override
    public void setSampling(final int p) {
        super.setSampling(p);
        this.o = p;
        this.p = p;
    }
}
