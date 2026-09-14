package com.alibaba.mtl.appmonitor.c;

import org.json.JSONArray;

public class d extends JSONArray implements b
{
    public void clean() {
        for (int i = 0; i < this.length(); ++i) {
            final Object opt = this.opt(i);
            if (opt != null && opt instanceof b) {
                a.a().a((b)opt);
            }
        }
    }
    
    public void fill(final Object... array) {
    }
}
