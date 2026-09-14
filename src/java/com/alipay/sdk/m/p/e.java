package com.alipay.sdk.m.p;

import java.util.Iterator;
import java.util.Map$Entry;
import com.alipay.sdk.m.u.n;
import android.os.Build;
import java.util.HashMap;
import com.alipay.sdk.m.o.a$a;
import com.alipay.sdk.m.u.m;
import android.content.Context;
import com.alipay.sdk.m.s.a;
import com.alipay.sdk.m.m.b;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;
import android.text.TextUtils;
import java.util.List;
import com.alipay.sdk.m.o.a$b;

public abstract class e
{
    public static final String c = "msp-gzip";
    public static final String d = "Msp-Param";
    public static final String e = "Operation-Type";
    public static final String f = "content-type";
    public static final String g = "Version";
    public static final String h = "AppId";
    public static final String i = "des-mode";
    public static final String j = "namespace";
    public static final String k = "api_name";
    public static final String l = "api_version";
    public static final String m = "data";
    public static final String n = "params";
    public static final String o = "public_key";
    public static final String p = "device";
    public static final String q = "action";
    public static final String r = "type";
    public static final String s = "method";
    public boolean a;
    public boolean b;
    
    public e() {
        this.a = true;
        this.b = true;
    }
    
    public static String a(final a$b a$b, final String s) {
        if (a$b == null || s == null) {
            return null;
        }
        final Map a = a$b.a;
        if (a == null) {
            return null;
        }
        final List list = (List)a.get((Object)s);
        if (list == null) {
            return null;
        }
        return TextUtils.join((CharSequence)",", (Iterable)list);
    }
    
    public static JSONObject a(final String s, final String s2) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("type", (Object)s);
        jsonObject2.put("method", (Object)s2);
        jsonObject.put("action", (Object)jsonObject2);
        return jsonObject;
    }
    
    public static boolean a(final a$b a$b) {
        return Boolean.valueOf(a(a$b, "msp-gzip"));
    }
    
    public static boolean a(String optString) {
        final boolean empty = TextUtils.isEmpty((CharSequence)optString);
        final boolean b = false;
        if (empty) {
            return false;
        }
        boolean b2;
        try {
            final JSONObject jsonObject = new JSONObject(optString).getJSONObject("data");
            if (!jsonObject.has("params")) {
                return false;
            }
            optString = jsonObject.getJSONObject("params").optString("public_key", (String)null);
            b2 = b;
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                com.alipay.sdk.m.m.b.a(optString);
                b2 = true;
            }
        }
        catch (final JSONException ex) {
            com.alipay.sdk.m.u.e.a((Throwable)ex);
            b2 = b;
        }
        return b2;
    }
    
    public com.alipay.sdk.m.p.b a(final a a, final Context context) throws Throwable {
        return this.a(a, context, "");
    }
    
    public com.alipay.sdk.m.p.b a(final a a, final Context context, final String s) throws Throwable {
        return this.a(a, context, s, com.alipay.sdk.m.u.m.b(context));
    }
    
    public com.alipay.sdk.m.p.b a(final a a, final Context context, final String s, final String s2) throws Throwable {
        return this.a(a, context, s, s2, true);
    }
    
    public com.alipay.sdk.m.p.b a(final a a, final Context context, final String s, final String s2, final boolean b) throws Throwable {
        final StringBuilder sb = new StringBuilder();
        sb.append("Packet: ");
        sb.append(s2);
        com.alipay.sdk.m.u.e.b("mspl", sb.toString());
        final c c = new c(this.b);
        final com.alipay.sdk.m.p.b b2 = new com.alipay.sdk.m.p.b(this.a(a), this.a(a, s, this.a()));
        final Map<String, String> a2 = this.a(false, s);
        final d a3 = c.a(b2, this.a, (String)a2.get((Object)"iSr"));
        final a$b a4 = com.alipay.sdk.m.o.a.a(context, new a$a(s2, (Map)this.a(a3.b(), s), a3.a()));
        if (a4 != null) {
            final com.alipay.sdk.m.p.b a5 = c.a(new d(a(a4), a4.c), (String)a2.get((Object)"iSr"));
            com.alipay.sdk.m.p.b a6;
            if ((a6 = a5) != null) {
                a6 = a5;
                if (a(a5.b())) {
                    a6 = a5;
                    if (b) {
                        a6 = this.a(a, context, s, s2, false);
                    }
                }
            }
            return a6;
        }
        throw new RuntimeException("Response is null.");
    }
    
    public String a(final a a) throws JSONException {
        final HashMap hashMap = new HashMap();
        hashMap.put((Object)"device", (Object)Build.MODEL);
        hashMap.put((Object)"namespace", (Object)"com.alipay.mobilecashier");
        hashMap.put((Object)"api_name", (Object)"com.alipay.mcpay");
        hashMap.put((Object)"api_version", (Object)this.b());
        return this.a(a, (HashMap<String, String>)hashMap, (HashMap<String, String>)new HashMap());
    }
    
    public String a(final a a, final String s, JSONObject a2) {
        final com.alipay.sdk.m.s.b d = com.alipay.sdk.m.s.b.d();
        final com.alipay.sdk.m.t.a a3 = com.alipay.sdk.m.t.a.a(d.b());
        a2 = com.alipay.sdk.m.u.d.a(new JSONObject(), a2);
        try {
            a2.put("external_info", (Object)s);
            a2.put("tid", (Object)a3.d());
            a2.put("user_agent", (Object)d.a().a(a, a3, this.c()));
            a2.put("has_alipay", com.alipay.sdk.m.u.n.a(a, d.b(), (List<com.alipay.sdk.m.m.a$b>)com.alipay.sdk.m.j.a.d, false));
            a2.put("has_msp_app", com.alipay.sdk.m.u.n.h(d.b()));
            a2.put("app_key", (Object)"2014052600006128");
            a2.put("utdid", (Object)d.c());
            a2.put("new_client_key", (Object)a3.c());
            a2.put("pa", (Object)com.alipay.sdk.m.m.b.b(d.b()));
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(a, "biz", "BodyErr", t);
            com.alipay.sdk.m.u.e.a(t);
        }
        return a2.toString();
    }
    
    public String a(final a a, final HashMap<String, String> hashMap, final HashMap<String, String> hashMap2) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        if (hashMap != null) {
            for (final Map$Entry map$Entry : hashMap.entrySet()) {
                jsonObject2.put((String)map$Entry.getKey(), map$Entry.getValue());
            }
        }
        if (hashMap2 != null) {
            final JSONObject jsonObject3 = new JSONObject();
            for (final Map$Entry map$Entry2 : hashMap2.entrySet()) {
                jsonObject3.put((String)map$Entry2.getKey(), map$Entry2.getValue());
            }
            jsonObject2.put("params", (Object)jsonObject3);
        }
        jsonObject.put("data", (Object)jsonObject2);
        return jsonObject.toString();
    }
    
    public Map<String, String> a(final boolean b, final String s) {
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)"msp-gzip", (Object)String.valueOf(b));
        ((Map)hashMap).put((Object)"Operation-Type", (Object)"alipay.msp.cashier.dispatch.bytes");
        ((Map)hashMap).put((Object)"content-type", (Object)"application/octet-stream");
        ((Map)hashMap).put((Object)"Version", (Object)"2.0");
        ((Map)hashMap).put((Object)"AppId", (Object)"TAOBAO");
        ((Map)hashMap).put((Object)"Msp-Param", (Object)com.alipay.sdk.m.p.a.a(s));
        ((Map)hashMap).put((Object)"des-mode", (Object)"CBC");
        return (Map<String, String>)hashMap;
    }
    
    public abstract JSONObject a() throws JSONException;
    
    public String b() {
        return "4.9.0";
    }
    
    public abstract boolean c();
}
