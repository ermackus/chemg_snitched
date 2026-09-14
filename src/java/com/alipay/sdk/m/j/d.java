package com.alipay.sdk.m.j;

import java.util.Collections;
import android.content.Context;
import android.net.Uri;
import android.app.Activity;
import com.alipay.sdk.m.u.n;
import org.json.JSONObject;
import android.content.Intent;
import com.alipay.sdk.m.s.a;

public class d
{
    public static final int a = 1010;
    public static a b;
    
    public static boolean a(final com.alipay.sdk.m.s.a a, final int n, final int n2, final Intent intent) {
        if (n != 1010) {
            return false;
        }
        if (intent == null) {
            return false;
        }
        final a b = d.b;
        if (b == null) {
            return true;
        }
        d.b = null;
        if (n2 != -1) {
            String uri = "";
            if (n2 != 0) {
                final StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(n2);
                com.alipay.sdk.m.k.a.b(a, "biz", "TbUnknown", sb.toString());
            }
            else {
                if (intent != null) {
                    uri = intent.toUri(1);
                }
                com.alipay.sdk.m.k.a.a(a, "biz", "TbCancel", uri);
                b.a(false, null, "CANCELED");
            }
        }
        else {
            com.alipay.sdk.m.k.a.a(a, "biz", "TbOk", intent.toUri(1));
            b.a(true, n.a(intent), "OK");
        }
        return true;
    }
    
    public static boolean a(final com.alipay.sdk.m.s.a a, final Activity activity, final int n, final String s, final String s2, final a b) {
        try {
            com.alipay.sdk.m.k.a.a(a, "biz", "TbStart");
            activity.startActivityForResult(new Intent(s2, Uri.parse(s)), n);
            d.b = b;
            return true;
        }
        finally {
            b.a(false, null, "UNKNOWN_ERROR");
            final Throwable t;
            com.alipay.sdk.m.k.a.a(a, "biz", "TbActFail", t);
            return false;
        }
    }
    
    public static boolean a(final com.alipay.sdk.m.s.a a, final Context context) {
        return n.a(a, context, Collections.singletonList((Object)new com.alipay.sdk.m.m.a.b("com.taobao.taobao", 0, "")), false);
    }
    
    public interface a
    {
        void a(final boolean p0, final JSONObject p1, final String p2);
    }
}
