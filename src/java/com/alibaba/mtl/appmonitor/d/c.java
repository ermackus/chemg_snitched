package com.alibaba.mtl.appmonitor.d;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class c extends a<JSONObject>
{
    private Map<String, b> p;
    
    public c(final int n) {
        super(n);
        this.p = (Map<String, b>)new HashMap();
    }
    
    public Boolean a(final int n, final Map<String, String> map) {
        if (map != null) {
            final Map<String, b> p2 = this.p;
            if (p2 != null) {
                for (final String s : p2.keySet()) {
                    if (!((b)this.p.get((Object)s)).b((String)map.get((Object)s))) {
                        return null;
                    }
                }
                return this.a(n);
            }
        }
        return null;
    }
    
    public void b(final JSONObject jsonObject) {
        this.a(jsonObject);
    }
}
