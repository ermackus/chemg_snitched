package com.alibaba.mtl.appmonitor.b;

import java.util.List;
import com.alibaba.mtl.appmonitor.f.c;
import java.util.Map;
import com.alibaba.mtl.appmonitor.c.d;
import com.alibaba.mtl.appmonitor.SdkMeta;
import java.util.HashMap;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.a.h;
import java.util.ArrayList;
import com.alibaba.mtl.appmonitor.c.e;
import com.alibaba.mtl.appmonitor.c.a;
import org.json.JSONObject;
import android.content.Context;
import java.io.IOException;

public class b
{
    private static String a(final Throwable t) throws IOException {
        final StringBuilder sb = new StringBuilder();
        sb.append(t.getClass().getName());
        final StackTraceElement[] stackTrace = t.getStackTrace();
        if (stackTrace != null) {
            for (int length = stackTrace.length, i = 0; i < length; ++i) {
                sb.append(stackTrace[i].toString());
            }
        }
        String s;
        if (com.alibaba.mtl.appmonitor.f.b.d(s = sb.toString())) {
            s = t.toString();
        }
        return s;
    }
    
    private static JSONObject a(final Context context, final Throwable t) throws IOException {
        final JSONObject jsonObject = a.a().a((Class<JSONObject>)e.class, new Object[0]);
        Label_0031: {
            if (context == null) {
                break Label_0031;
            }
            try {
                jsonObject.put("pname", (Object)com.alibaba.mtl.log.d.b.a(context));
                jsonObject.put("page", (Object)"APPMONITOR");
                jsonObject.put("monitorPoint", (Object)"sdk-exception");
                jsonObject.put("arg", (Object)t.getClass().getSimpleName());
                jsonObject.put("successCount", 0);
                jsonObject.put("failCount", 1);
                final ArrayList list = new ArrayList();
                final String a = a(t);
                if (a != null) {
                    final JSONObject jsonObject2 = com.alibaba.mtl.appmonitor.c.a.a().a((Class<JSONObject>)e.class, new Object[0]);
                    jsonObject2.put("errorCode", (Object)a);
                    jsonObject2.put("errorCount", 1);
                    ((List)list).add((Object)jsonObject2);
                }
                jsonObject.put("errors", (Object)list);
                return jsonObject;
            }
            catch (final Exception ex) {
                return jsonObject;
            }
        }
    }
    
    public static void a(final Context context, final Throwable t) {
        if (t != null) {
            try {
                final h h = a.a().a(h.class, new Object[0]);
                h.e = f.a.a();
                final HashMap hashMap = new HashMap();
                ((Map)hashMap).put((Object)"meta", (Object)SdkMeta.getSDKMetaData());
                final d d = a.a().a(d.class, new Object[0]);
                d.put((Object)a(context, t));
                ((Map)hashMap).put((Object)"data", (Object)d);
                h.m.put((Object)f.a.a(), (Object)new JSONObject((Map)hashMap).toString());
                h.v = "APPMONITOR";
                h.w = "sdk-exception";
                c.a(h);
                a.a().a(d);
            }
            finally {
                final Throwable t2;
                t2.printStackTrace();
            }
        }
    }
    
    public static void a(final Throwable t) {
        a((Context)null, t);
    }
}
