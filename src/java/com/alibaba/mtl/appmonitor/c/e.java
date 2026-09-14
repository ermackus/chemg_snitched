package com.alibaba.mtl.appmonitor.c;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class e extends JSONObject implements b
{
    public void clean() {
        final Iterator keys = this.keys();
        if (keys != null) {
            while (keys.hasNext()) {
                final String s = (String)keys.next();
                try {
                    final Object value = this.get(s);
                    if (value == null || !(value instanceof b)) {
                        continue;
                    }
                    a.a().a((b)value);
                }
                catch (final JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void fill(final Object... array) {
    }
}
