package com.alibaba.mtl.appmonitor.a;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.mtl.appmonitor.c.b;

public class h implements b
{
    public int e;
    public Map<String, String> m;
    public String u;
    public String v;
    public String w;
    public String x;
    
    public void clean() {
        this.u = null;
        this.e = 0;
        this.v = null;
        this.w = null;
        this.x = null;
        final Map<String, String> m = this.m;
        if (m != null) {
            m.clear();
        }
    }
    
    public void fill(final Object... array) {
        if (this.m == null) {
            this.m = (Map<String, String>)new HashMap();
        }
    }
}
