package com.alibaba.mtl.log.a;

import org.json.JSONArray;
import java.util.Iterator;
import java.util.ArrayList;
import org.json.JSONObject;
import com.alibaba.mtl.log.d.i;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class d
{
    private static d a;
    private String W;
    private Map<String, c> u;
    
    static {
        d.a = new d();
    }
    
    public d() {
        this.u = (Map<String, c>)Collections.synchronizedMap((Map)new HashMap());
    }
    
    public static d a() {
        return d.a;
    }
    
    public Map<String, c> b() {
        return this.u;
    }
    
    public void b(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("host config:");
        sb.append(s);
        i.a("HostConfigMgr", new Object[] { sb.toString() });
        if (s == null) {
            return;
        }
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final JSONObject jsonObject2 = jsonObject.getJSONObject("content");
            if (jsonObject2 != null) {
                final JSONObject jsonObject3 = jsonObject2.getJSONObject("hosts");
                if (jsonObject3 != null) {
                    final Iterator keys = jsonObject3.keys();
                    while (keys.hasNext()) {
                        final String s2 = (String)keys.next();
                        if (s2 != null) {
                            final c c = new c();
                            final JSONObject jsonObject4 = jsonObject3.getJSONObject(s2);
                            if (jsonObject4 != null) {
                                c.V = s2.substring(1);
                                c.U = jsonObject4.getString("host");
                                final JSONArray jsonArray = jsonObject4.getJSONArray("eids");
                                if (jsonArray != null) {
                                    c.a = (ArrayList<String>)new ArrayList();
                                    for (int i = 0; i < jsonArray.length(); ++i) {
                                        c.a.add((Object)jsonArray.getString(i));
                                    }
                                }
                            }
                            final Map<String, c> u = this.u;
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(c.V);
                            sb2.append("");
                            u.put((Object)sb2.toString(), (Object)c);
                        }
                    }
                }
            }
            this.W = jsonObject.getString("timestamp");
        }
        finally {}
    }
}
