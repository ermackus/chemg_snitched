package com.alipay.apmobilesecuritysdk.d;

import com.alipay.sdk.m.y.b;
import java.util.TreeMap;
import android.content.Context;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;

public final class e
{
    public static Map<String, String> a;
    public static final String[] b;
    
    static {
        b = new String[] { "AD1", "AD2", "AD3", "AD8", "AD9", "AD10", "AD11", "AD12", "AD14", "AD15", "AD16", "AD18", "AD20", "AD21", "AD23", "AD24", "AD26", "AD27", "AD28", "AD29", "AD30", "AD31", "AD34", "AA1", "AA2", "AA3", "AA4", "AC4", "AC10", "AE1", "AE2", "AE3", "AE4", "AE5", "AE6", "AE7", "AE8", "AE9", "AE10", "AE11", "AE12", "AE13", "AE14", "AE15" };
    }
    
    public static String a(final Map<String, String> map) {
        final StringBuffer sb = new StringBuffer();
        final ArrayList list = new ArrayList((Collection)map.keySet());
        Collections.sort((List)list);
        for (int i = 0; i < ((List)list).size(); ++i) {
            final String s = (String)((List)list).get(i);
            final String s2 = (String)map.get((Object)s);
            String s3 = "";
            String s4;
            if ((s4 = s2) == null) {
                s4 = "";
            }
            final StringBuilder sb2 = new StringBuilder();
            if (i != 0) {
                s3 = "&";
            }
            sb2.append(s3);
            sb2.append(s);
            sb2.append("=");
            sb2.append(s4);
            sb.append(sb2.toString());
        }
        return sb.toString();
    }
    
    public static Map<String, String> a(final Context context, final Map<String, String> map) {
        synchronized (e.class) {
            if (e.a == null) {
                c(context, map);
            }
            e.a.putAll((Map)d.a());
            return e.a;
        }
    }
    
    public static void a() {
        synchronized (e.class) {
            e.a = null;
        }
    }
    
    public static String b(final Context context, final Map<String, String> map) {
        synchronized (e.class) {
            a(context, map);
            final TreeMap treeMap = new TreeMap();
            for (final String s : e.b) {
                if (e.a.containsKey((Object)s)) {
                    ((Map)treeMap).put((Object)s, e.a.get((Object)s));
                }
            }
            return com.alipay.sdk.m.y.b.a(a((Map<String, String>)treeMap));
        }
    }
    
    public static void c(final Context context, final Map<String, String> map) {
        synchronized (e.class) {
            (e.a = (Map<String, String>)new TreeMap()).putAll((Map)com.alipay.apmobilesecuritysdk.d.b.a(context, map));
            e.a.putAll((Map)d.a(context));
            e.a.putAll((Map)c.a(context));
            e.a.putAll((Map)com.alipay.apmobilesecuritysdk.d.a.a(context, map));
        }
    }
}
