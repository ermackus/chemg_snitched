package com.alibaba.mtl.appmonitor.d;

import com.alibaba.mtl.log.d.i;
import org.json.JSONArray;
import com.alibaba.mtl.appmonitor.f.b;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.mtl.appmonitor.a.f;
import org.json.JSONObject;

class g extends a<JSONObject>
{
    private f e;
    protected int q;
    protected Map<String, h> q;
    
    public g(final f e, final int n) {
        super(n);
        this.q = -1;
        this.e = e;
        this.q = (Map<String, h>)Collections.synchronizedMap((Map)new HashMap());
    }
    
    public boolean a(final int n, final String s, final String s2, final Map<String, String> map) {
        final Map<String, h> q = this.q;
        if (q != null) {
            final h h = (h)q.get((Object)s);
            if (h != null) {
                return h.a(n, s2, map);
            }
        }
        return n < this.n;
    }
    
    public void b(final JSONObject jsonObject) {
        this.a(jsonObject);
        this.c(jsonObject);
        this.q.clear();
        try {
            final JSONArray optJSONArray = jsonObject.optJSONArray("metrics");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    final JSONObject jsonObject2 = optJSONArray.getJSONObject(i);
                    final String optString = jsonObject2.optString("module");
                    if (b.c(optString)) {
                        h h;
                        if ((h = (h)this.q.get((Object)optString)) == null) {
                            h = new h(optString, this.n);
                            this.q.put((Object)optString, (Object)h);
                        }
                        h.b(jsonObject2);
                    }
                }
            }
        }
        catch (final Exception ex) {}
    }
    
    protected void c(final JSONObject jsonObject) {
        i.a("EventTypeSampling", new Object[] { "[updateEventTypeTriggerCount]", this, jsonObject });
        if (jsonObject == null) {
            return;
        }
        try {
            final int optInt = jsonObject.optInt("cacheCount");
            if (optInt > 0 && this.e != null) {
                this.e.b(optInt);
            }
        }
        finally {
            final Throwable t;
            i.a("EventTypeSampling", (Object)"updateTriggerCount", t);
        }
    }
    
    public void setSampling(final int n) {
        this.n = n;
    }
}
