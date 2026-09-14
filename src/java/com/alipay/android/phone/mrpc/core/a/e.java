package com.alipay.android.phone.mrpc.core.a;

import com.alipay.android.phone.mrpc.core.RpcException;
import java.util.List;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import com.alipay.sdk.m.e.f;
import java.util.ArrayList;

public final class e extends b
{
    public int c;
    public Object d;
    
    public e(final int c, final String s, final Object o) {
        super(s, o);
        this.c = c;
    }
    
    public final void a(final Object d) {
        this.d = d;
    }
    
    public final byte[] a() {
        try {
            final ArrayList list = new ArrayList();
            if (this.d != null) {
                list.add((Object)new BasicNameValuePair("extParam", f.a(this.d)));
            }
            list.add((Object)new BasicNameValuePair("operationType", super.a));
            final StringBuilder sb = new StringBuilder();
            sb.append(this.c);
            list.add((Object)new BasicNameValuePair("id", sb.toString()));
            new StringBuilder("mParams is:").append(super.b);
            String a;
            if (super.b == null) {
                a = "[]";
            }
            else {
                a = f.a(super.b);
            }
            list.add((Object)new BasicNameValuePair("requestData", a));
            return URLEncodedUtils.format((List)list, "utf-8").getBytes();
        }
        catch (final Exception ex) {
            final StringBuilder sb2 = new StringBuilder("request  =");
            sb2.append(super.b);
            sb2.append(":");
            sb2.append((Object)ex);
            String message;
            if (sb2.toString() == null) {
                message = "";
            }
            else {
                message = ex.getMessage();
            }
            throw new RpcException(Integer.valueOf(9), message, (Throwable)ex);
        }
    }
}
