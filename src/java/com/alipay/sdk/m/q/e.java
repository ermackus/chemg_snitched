package com.alipay.sdk.m.q;

import org.json.JSONObject;
import com.alipay.sdk.m.o.a$b;
import java.util.Map;
import com.alipay.sdk.m.o.a$a;
import java.util.HashMap;
import java.nio.charset.Charset;
import com.alipay.sdk.m.p.b;
import android.content.Context;
import com.alipay.sdk.m.s.a;

public class e extends com.alipay.sdk.m.p.e
{
    @Override
    public b a(final a a, final Context context, final String s) throws Throwable {
        com.alipay.sdk.m.u.e.d("mspl", "mdap post");
        final byte[] a2 = com.alipay.sdk.m.n.b.a(s.getBytes(Charset.forName("UTF-8")));
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)"utdId", (Object)com.alipay.sdk.m.s.b.d().c());
        ((Map)hashMap).put((Object)"logHeader", (Object)"RAW");
        ((Map)hashMap).put((Object)"bizCode", (Object)"alipaysdk");
        ((Map)hashMap).put((Object)"productId", (Object)"alipaysdk_android");
        ((Map)hashMap).put((Object)"Content-Encoding", (Object)"Gzip");
        ((Map)hashMap).put((Object)"productVersion", (Object)"15.8.17");
        final a$b a3 = com.alipay.sdk.m.o.a.a(context, new a$a("https://loggw-exsdk.alipay.com/loggw/logUpload.do", (Map)hashMap, a2));
        final StringBuilder sb = new StringBuilder();
        sb.append("mdap got ");
        sb.append((Object)a3);
        com.alipay.sdk.m.u.e.d("mspl", sb.toString());
        if (a3 != null) {
            final boolean a4 = com.alipay.sdk.m.p.e.a(a3);
            try {
                byte[] array = a3.c;
                if (a4) {
                    array = com.alipay.sdk.m.n.b.b(array);
                }
                return new b("", new String(array, Charset.forName("UTF-8")));
            }
            catch (final Exception ex) {
                com.alipay.sdk.m.u.e.a((Throwable)ex);
                return null;
            }
        }
        throw new RuntimeException("Response is null");
    }
    
    @Override
    public String a(final a a, final String s, final JSONObject jsonObject) {
        return s;
    }
    
    @Override
    public Map<String, String> a(final boolean b, final String s) {
        return (Map<String, String>)new HashMap();
    }
    
    @Override
    public JSONObject a() {
        return null;
    }
    
    @Override
    public boolean c() {
        return false;
    }
}
