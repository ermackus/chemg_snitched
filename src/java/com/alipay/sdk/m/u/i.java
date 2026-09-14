package com.alipay.sdk.m.u;

import android.text.TextUtils;
import android.content.Context;
import com.alipay.sdk.m.s.a;

public class i
{
    public static final String a = "pref_trade_token";
    public static final String b = ";";
    public static final String c = "result={";
    public static final String d = "}";
    public static final String e = "trade_token=\"";
    public static final String f = "\"";
    public static final String g = "trade_token=";
    
    public static String a(final a a, final Context context) {
        final String a2 = j.a(a, context, "pref_trade_token", "");
        final StringBuilder sb = new StringBuilder();
        sb.append("get trade token: ");
        sb.append(a2);
        com.alipay.sdk.m.u.e.b("mspl", sb.toString());
        return a2;
    }
    
    public static String a(String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        String s2 = null;
        if (empty) {
            return null;
        }
        final String[] split = s.split(";");
        for (int i = 0; i < split.length; ++i, s2 = s) {
            s = s2;
            if (split[i].startsWith("result={")) {
                s = s2;
                if (split[i].endsWith("}")) {
                    final String[] split2 = split[i].substring(8, split[i].length() - 1).split("&");
                    int n = 0;
                    while (true) {
                        s = s2;
                        if (n >= split2.length) {
                            break;
                        }
                        if (split2[n].startsWith("trade_token=\"") && split2[n].endsWith("\"")) {
                            s = split2[n].substring(13, split2[n].length() - 1);
                            break;
                        }
                        if (split2[n].startsWith("trade_token=")) {
                            s = split2[n].substring(12);
                            break;
                        }
                        ++n;
                    }
                }
            }
        }
        return s2;
    }
    
    public static void a(final a a, final Context context, final String s) {
        try {
            final String a2 = a(s);
            final StringBuilder sb = new StringBuilder();
            sb.append("trade token: ");
            sb.append(a2);
            com.alipay.sdk.m.u.e.b("mspl", sb.toString());
            if (!TextUtils.isEmpty((CharSequence)a2)) {
                j.b(a, context, "pref_trade_token", a2);
            }
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(a, "biz", "SaveTradeTokenError", t);
            com.alipay.sdk.m.u.e.a(t);
        }
    }
}
