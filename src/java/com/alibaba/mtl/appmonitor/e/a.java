package com.alibaba.mtl.appmonitor.e;

import com.alibaba.mtl.log.d.t;
import com.alibaba.mtl.log.model.LogField;
import com.alibaba.mtl.log.d.i;
import java.util.Map;

public class a
{
    private static final String TAG;
    private static a a;
    
    private a() {
    }
    
    public static a a() {
        synchronized (a.class) {
            if (com.alibaba.mtl.appmonitor.e.a.a == null) {
                com.alibaba.mtl.appmonitor.e.a.a = new a();
            }
            return com.alibaba.mtl.appmonitor.e.a.a;
        }
    }
    
    public void a(final Map<String, String> map) {
        if (map == null) {
            return;
        }
        i.a(com.alibaba.mtl.appmonitor.e.a.TAG, new Object[] { "[sendToUT]:", " args:", map });
        if (com.alibaba.mtl.log.a.r) {
            if (map != null) {
                com.alibaba.mtl.log.a.a((String)map.get((Object)LogField.PAGE.toString()), (String)map.get((Object)LogField.EVENTID.toString()), (String)map.get((Object)LogField.ARG1.toString()), (String)map.get((Object)LogField.ARG2.toString()), (String)map.get((Object)LogField.ARG3.toString()), map);
            }
        }
        else {
            map.put((Object)"_fuamf", (Object)"yes");
            t.send(map);
        }
    }
}
