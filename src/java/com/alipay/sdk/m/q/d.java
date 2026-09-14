package com.alipay.sdk.m.q;

import java.util.Map;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.HashMap;
import com.alipay.sdk.m.p.b;
import android.content.Context;
import com.alipay.sdk.m.s.a;
import com.alipay.sdk.m.p.e;

public class d extends e
{
    public static final String t = "log_v";
    
    @Override
    public b a(final a a, final Context context, final String s) throws Throwable {
        return this.a(a, context, s, "https://mcgw.alipay.com/sdklog.do", true);
    }
    
    @Override
    public String a(final a a) throws JSONException {
        final HashMap hashMap = new HashMap();
        hashMap.put((Object)"api_name", (Object)"/sdk/log");
        hashMap.put((Object)"api_version", (Object)"1.0.0");
        final HashMap hashMap2 = new HashMap();
        hashMap2.put((Object)"log_v", (Object)"1.0");
        return this.a(a, (HashMap<String, String>)hashMap, (HashMap<String, String>)hashMap2);
    }
    
    @Override
    public String a(final a a, final String s, final JSONObject jsonObject) {
        return s;
    }
    
    @Override
    public Map<String, String> a(final boolean b, final String s) {
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)"msp-gzip", (Object)String.valueOf(b));
        ((Map)hashMap).put((Object)"content-type", (Object)"application/octet-stream");
        ((Map)hashMap).put((Object)"des-mode", (Object)"CBC");
        return (Map<String, String>)hashMap;
    }
    
    @Override
    public JSONObject a() throws JSONException {
        return null;
    }
    
    @Override
    public boolean c() {
        return false;
    }
}
