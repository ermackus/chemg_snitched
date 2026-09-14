package com.alipay.sdk.m.q;

import org.json.JSONObject;
import org.json.JSONException;
import java.util.Map;
import java.util.HashMap;
import com.alipay.sdk.m.s.a;
import com.alipay.sdk.m.p.e;

public class b extends e
{
    @Override
    public String a(final a a, final HashMap<String, String> hashMap, final HashMap<String, String> hashMap2) throws JSONException {
        HashMap hashMap3 = hashMap2;
        if (hashMap2 == null) {
            hashMap3 = new HashMap();
        }
        hashMap3.putAll((Map)com.alipay.sdk.m.u.a.a(a));
        final StringBuilder sb = new StringBuilder();
        sb.append("cf ");
        sb.append((Object)hashMap3);
        com.alipay.sdk.m.u.e.d("mspl", sb.toString());
        return super.a(a, hashMap, (HashMap<String, String>)hashMap3);
    }
    
    @Override
    public JSONObject a() throws JSONException {
        return com.alipay.sdk.m.p.e.a("sdkConfig", "obtain");
    }
    
    @Override
    public String b() {
        return "5.0.0";
    }
    
    @Override
    public boolean c() {
        return true;
    }
}
