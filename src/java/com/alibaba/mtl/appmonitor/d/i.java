package com.alibaba.mtl.appmonitor.d;

import org.json.JSONArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import org.json.JSONObject;

class i extends a<JSONObject>
{
    protected List<c> e;
    private String p;
    
    public i(final String p2, final int n) {
        super(n);
        this.p = p2;
    }
    
    public boolean a(final int n, final Map<String, String> map) {
        final List<c> e = this.e;
        if (e != null && map != null) {
            final Iterator iterator = e.iterator();
            while (iterator.hasNext()) {
                final Boolean a = ((c)iterator.next()).a(n, map);
                if (a != null) {
                    return a;
                }
            }
        }
        return this.a(n);
    }
    
    public void b(final JSONObject jsonObject) {
        this.a(jsonObject);
        try {
            final JSONArray optJSONArray = jsonObject.optJSONArray("extra");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    final JSONObject jsonObject2 = optJSONArray.getJSONObject(i);
                    final c c = new c(this.n);
                    if (this.e == null) {
                        this.e = (List<c>)new ArrayList();
                    }
                    this.e.add((Object)c);
                    c.b(jsonObject2);
                }
            }
        }
        catch (final Exception ex) {}
    }
}
