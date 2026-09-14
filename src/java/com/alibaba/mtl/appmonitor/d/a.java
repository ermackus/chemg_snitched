package com.alibaba.mtl.appmonitor.d;

import org.json.JSONObject;

public abstract class a<T extends JSONObject>
{
    protected int n;
    
    public a(final int n) {
        this.n = n;
    }
    
    protected void a(final T t) {
        try {
            final Integer value = t.getInt("sampling");
            if (value != null) {
                this.n = value;
            }
        }
        catch (final Exception ex) {}
    }
    
    protected boolean a(final int n) {
        return n < this.n;
    }
}
