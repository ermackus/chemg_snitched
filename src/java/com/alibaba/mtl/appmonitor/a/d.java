package com.alibaba.mtl.appmonitor.a;

import org.json.JSONException;
import com.alibaba.mtl.appmonitor.c.e;
import com.alibaba.mtl.appmonitor.c.a;
import org.json.JSONObject;
import com.alibaba.mtl.appmonitor.c.b;

public abstract class d implements b
{
    public int e;
    public String o;
    public String p;
    public String s;
    
    public JSONObject a() {
        final JSONObject jsonObject = (JSONObject)a.a().a((Class)e.class, new Object[0]);
        try {
            jsonObject.put("page", (Object)this.o);
            jsonObject.put("monitorPoint", (Object)this.p);
            if (this.s != null) {
                jsonObject.put("arg", (Object)this.s);
            }
            return jsonObject;
        }
        catch (final JSONException ex) {
            return jsonObject;
        }
    }
    
    public void clean() {
        this.e = 0;
        this.o = null;
        this.p = null;
        this.s = null;
    }
    
    public void fill(final Object... array) {
        this.e = (int)array[0];
        this.o = (String)array[1];
        this.p = (String)array[2];
        if (array.length > 3 && array[3] != null) {
            this.s = (String)array[3];
        }
    }
}
