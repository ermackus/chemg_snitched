package com.alipay.android.phone.mrpc.core.a;

import com.alipay.android.phone.mrpc.core.RpcException;
import com.alipay.sdk.m.e.e;
import org.json.JSONObject;
import java.lang.reflect.Type;

public final class d extends a
{
    public d(final Type type, final byte[] array) {
        super(type, array);
    }
    
    public final Object a() {
        try {
            final String s = new String(super.b);
            final StringBuilder sb = new StringBuilder("threadid = ");
            sb.append(Thread.currentThread().getId());
            sb.append("; rpc response:  ");
            sb.append(s);
            final JSONObject jsonObject = new JSONObject(s);
            final int int1 = jsonObject.getInt("resultStatus");
            if (int1 == 1000) {
                Object o;
                if (super.a == String.class) {
                    o = jsonObject.optString("result");
                }
                else {
                    o = e.a(jsonObject.optString("result"), super.a);
                }
                return o;
            }
            throw new RpcException(Integer.valueOf(int1), jsonObject.optString("tips"));
        }
        catch (final Exception ex) {
            final StringBuilder sb2 = new StringBuilder("response  =");
            sb2.append(new String(super.b));
            sb2.append(":");
            sb2.append((Object)ex);
            String message;
            if (sb2.toString() == null) {
                message = "";
            }
            else {
                message = ex.getMessage();
            }
            throw new RpcException(Integer.valueOf(10), message);
        }
    }
}
