package com.alipay.sdk.m.u;

import java.util.Map;
import org.json.JSONException;
import android.text.TextUtils;
import android.content.Context;
import org.json.JSONObject;
import java.util.HashMap;
import com.alipay.sdk.m.h.e;
import com.alipay.sdk.m.h.f;
import com.alipay.sdk.m.k.a$c;
import com.alipay.sdk.m.h.c;
import com.alipay.sdk.m.s.b;

public class a
{
    public static final String a = "ap_req";
    public static final String b = "ap_args";
    public static final String c = "ap_resp";
    
    public static com.alipay.sdk.m.g.a a() {
        try {
            return com.alipay.sdk.m.h.a.a("NP", System.currentTimeMillis(), new c(com.alipay.sdk.m.s.b.d().c()), (short)a$c.a(com.alipay.sdk.m.s.b.d().b()), new f());
        }
        catch (final Exception ex) {
            try {
                return com.alipay.sdk.m.h.a.c();
            }
            catch (final Exception ex2) {
                return null;
            }
        }
    }
    
    public static HashMap<String, String> a(final com.alipay.sdk.m.s.a a) {
        final HashMap hashMap = new HashMap();
        try {
            final com.alipay.sdk.m.g.a a2 = a();
            final JSONObject jsonObject = new JSONObject();
            Context a3;
            if (a != null) {
                a3 = a.a();
            }
            else {
                a3 = null;
            }
            Context applicationContext = a3;
            if (a3 == null) {
                applicationContext = com.alipay.sdk.m.s.b.d().b().getApplicationContext();
            }
            final String a4 = n.a(a, applicationContext);
            final String b = com.alipay.sdk.m.w.b.b(a, applicationContext);
            final String s = "";
            String a5;
            if (a2 != null) {
                a5 = a2.a();
            }
            else {
                a5 = "";
            }
            jsonObject.put("ap_q", (Object)a5);
            String d;
            if (a != null) {
                d = a.d;
            }
            else {
                d = "";
            }
            jsonObject.put("ap_link_token", (Object)d);
            jsonObject.put("u_pd", (Object)String.valueOf(n.g()));
            jsonObject.put("u_lk", (Object)String.valueOf(n.e(n.b())));
            String g;
            if (a != null) {
                g = a.g;
            }
            else {
                g = "_";
            }
            jsonObject.put("u_pi", (Object)String.valueOf((Object)g));
            jsonObject.put("u_fu", (Object)a4);
            jsonObject.put("u_oi", (Object)b);
            hashMap.put((Object)"ap_req", (Object)jsonObject.toString());
            final StringBuilder sb = new StringBuilder();
            String a6 = s;
            if (a2 != null) {
                a6 = a2.a();
            }
            sb.append(a6);
            sb.append("|");
            sb.append(a4);
            com.alipay.sdk.m.k.a.a(a, "biz", "ap_q", sb.toString());
        }
        catch (final Exception ex) {
            com.alipay.sdk.m.k.a.a(a, "biz", "APMEx1", (Throwable)ex);
        }
        return (HashMap<String, String>)hashMap;
    }
    
    public static JSONObject a(final com.alipay.sdk.m.s.a a, JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final String optString = jsonObject.optString("ap_resp");
        try {
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                jsonObject = new JSONObject(optString);
                return jsonObject;
            }
            return null;
        }
        catch (final JSONException ex) {
            com.alipay.sdk.m.k.a.a(a, "biz", "APMEx2", (Throwable)ex);
            return null;
        }
    }
    
    public static void a(final com.alipay.sdk.m.s.a a, final HashMap<String, String> hashMap) {
        final JSONObject a2 = com.alipay.sdk.m.m.a.z().a();
        if (hashMap != null) {
            if (a2 != null) {
                com.alipay.sdk.m.k.a.a(a, "biz", "ap_r", a2.optString("ap_r"));
                hashMap.putAll((Map)n.a(a2));
            }
        }
    }
    
    public static void a(final com.alipay.sdk.m.s.a a, final JSONObject jsonObject, final JSONObject jsonObject2) {
        if (jsonObject != null) {
            if (jsonObject2 != null) {
                try {
                    jsonObject.putOpt("ap_args", (Object)jsonObject2);
                }
                catch (final JSONException ex) {
                    com.alipay.sdk.m.k.a.a(a, "biz", "APMEx2", (Throwable)ex);
                }
            }
        }
    }
}
