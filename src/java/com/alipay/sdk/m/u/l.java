package com.alipay.sdk.m.u;

import com.alipay.sdk.m.s.a;
import java.util.HashMap;
import com.alipay.sdk.m.j.c;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class l
{
    public static final String a = "resultStatus";
    public static final String b = "memo";
    public static final String c = "result";
    
    public static String a(String group, final String s) {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("(^|;)");
            sb.append(s);
            sb.append("=\\{([^}]*?)\\}");
            final Matcher matcher = Pattern.compile(sb.toString()).matcher((CharSequence)group);
            if (matcher.find()) {
                group = matcher.group(2);
                return (String)group;
            }
        }
        finally {
            e.a((Throwable)group);
        }
        group = "?";
        return (String)group;
    }
    
    public static Map<String, String> a() {
        final c b = com.alipay.sdk.m.j.c.b(com.alipay.sdk.m.j.c.e.b());
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)"resultStatus", (Object)Integer.toString(b.b()));
        ((Map)hashMap).put((Object)"memo", (Object)b.a());
        ((Map)hashMap).put((Object)"result", (Object)"");
        return (Map<String, String>)hashMap;
    }
    
    public static Map<String, String> a(a a, final String s) {
        final Map<String, String> a2 = a();
        Map<String, String> map = null;
        try {
            a = (a)a(s);
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(a, "biz", "FormatResultEx", t);
            map = a2;
        }
        return map;
    }
    
    public static Map<String, String> a(final String s) {
        final String[] split = s.split(";");
        final HashMap hashMap = new HashMap();
        for (final String s2 : split) {
            final String substring = s2.substring(0, s2.indexOf("={"));
            ((Map)hashMap).put((Object)substring, (Object)b(s2, substring));
        }
        return (Map<String, String>)hashMap;
    }
    
    public static String b(final String s, String string) {
        final StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append("={");
        string = sb.toString();
        return s.substring(s.indexOf(string) + string.length(), s.lastIndexOf("}"));
    }
}
