package com.alibaba.mtl.appmonitor.f;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.List;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.model.LogField;
import org.json.JSONObject;
import com.alibaba.mtl.appmonitor.SdkMeta;
import java.util.HashMap;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.a.d;
import com.alibaba.mtl.appmonitor.model.UTDimensionValueSet;
import java.util.Map;
import com.alibaba.mtl.log.a;
import com.alibaba.mtl.appmonitor.a.h;

public class c
{
    public static void a(final h h) {
        if (h == null) {
            return;
        }
        a.a(h.u, String.valueOf(h.e), h.v, h.w, h.x, (Map<String, String>)h.m);
        com.alibaba.mtl.appmonitor.c.a.a().a(h);
    }
    
    public static void a(final UTDimensionValueSet set, final d d) {
        final Integer eventId = set.getEventId();
        if (eventId != null) {
            final f a = f.a(eventId);
            final h h = com.alibaba.mtl.appmonitor.c.a.a().a(h.class, new Object[0]);
            h.e = 6699;
            if (set.getMap() != null) {
                h.m.putAll(set.getMap());
            }
            final HashMap hashMap = new HashMap();
            ((Map)hashMap).put((Object)"meta", (Object)SdkMeta.getSDKMetaData());
            ((Map)hashMap).put((Object)"_event_id", (Object)eventId);
            final com.alibaba.mtl.appmonitor.c.d d2 = com.alibaba.mtl.appmonitor.c.a.a().a(com.alibaba.mtl.appmonitor.c.d.class, new Object[0]);
            d2.put((Object)d.a());
            com.alibaba.mtl.appmonitor.c.a.a().a(d);
            ((Map)hashMap).put((Object)"data", (Object)d2);
            h.m.put((Object)a.a(), (Object)new JSONObject((Map)hashMap).toString());
            h.m.put((Object)LogField.EVENTID.toString(), (Object)String.valueOf(6699));
            b(h);
            com.alibaba.mtl.appmonitor.c.a.a().a(d2);
        }
    }
    
    public static void b(final h h) {
        i.a("UTUtil", new Object[] { "upload without flowback. args:", h.m });
        com.alibaba.mtl.appmonitor.e.a.a().a((Map<String, String>)h.m);
        com.alibaba.mtl.appmonitor.c.a.a().a(h);
    }
    
    public static void b(final Map<UTDimensionValueSet, List<d>> map) {
        for (final Map$Entry map$Entry : map.entrySet()) {
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            final UTDimensionValueSet set = (UTDimensionValueSet)map$Entry.getKey();
            final List list = (List)map$Entry.getValue();
            if (list.size() != 0) {
                final Integer eventId = set.getEventId();
                if (eventId != null) {
                    final f a = f.a(eventId);
                    final com.alibaba.mtl.appmonitor.c.a a2 = com.alibaba.mtl.appmonitor.c.a.a();
                    int n = 0;
                    final h h = a2.a(h.class, new Object[0]);
                    h.e = eventId;
                    if (set.getMap() != null) {
                        h.m.putAll(set.getMap());
                    }
                    final HashMap hashMap = new HashMap();
                    ((Map)hashMap).put((Object)"meta", (Object)SdkMeta.getSDKMetaData());
                    final com.alibaba.mtl.appmonitor.c.d d = com.alibaba.mtl.appmonitor.c.a.a().a(com.alibaba.mtl.appmonitor.c.d.class, new Object[0]);
                    for (final d d2 : list) {
                        d.put((Object)d2.a());
                        if (n == 0) {
                            sb.append(d2.o);
                            sb2.append(d2.p);
                        }
                        else {
                            sb.append(",");
                            sb.append(d2.o);
                            sb2.append(",");
                            sb2.append(d2.p);
                        }
                        ++n;
                        com.alibaba.mtl.appmonitor.c.a.a().a(d2);
                    }
                    ((Map)hashMap).put((Object)"data", (Object)d);
                    h.m.put((Object)a.a(), (Object)new JSONObject((Map)hashMap).toString());
                    final String string = sb.toString();
                    final String string2 = sb2.toString();
                    h.m.put((Object)LogField.ARG1.toString(), (Object)string);
                    h.m.put((Object)LogField.ARG2.toString(), (Object)string2);
                    h.v = string;
                    h.w = string2;
                    b(h);
                    com.alibaba.mtl.appmonitor.c.a.a().a(d);
                }
            }
            com.alibaba.mtl.appmonitor.c.a.a().a(set);
        }
    }
}
