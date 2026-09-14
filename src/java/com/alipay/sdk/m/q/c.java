package com.alipay.sdk.m.q;

import org.json.JSONException;
import org.json.JSONObject;
import com.alipay.sdk.m.p.e;

public class c extends e
{
    @Override
    public JSONObject a() throws JSONException {
        return com.alipay.sdk.m.p.e.a("cashier", "gentid");
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
