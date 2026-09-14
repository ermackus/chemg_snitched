package com.alipay.sdk.m.p;

import com.alipay.sdk.m.u.e;
import android.text.TextUtils;
import org.json.JSONObject;

public final class b
{
    public final String a;
    public final String b;
    
    public b(final String a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public String a() {
        return this.b;
    }
    
    public String b() {
        return this.a;
    }
    
    public JSONObject c() {
        final boolean empty = TextUtils.isEmpty((CharSequence)this.b);
        JSONObject jsonObject = null;
        if (empty) {
            return null;
        }
        try {
            jsonObject = new JSONObject(this.b);
        }
        catch (final Exception ex) {
            e.a((Throwable)ex);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return String.format("<Letter envelop=%s body=%s>", new Object[] { this.a, this.b });
    }
}
