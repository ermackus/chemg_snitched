package com.alibaba.mtl.log.d;

import com.alibaba.mtl.log.a;
import java.util.Iterator;
import com.alibaba.mtl.log.model.LogField;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class h
{
    public static String a(final String s, final String s2, final String s3, final String s4, final String s5, final Map<String, String> map, final String s6, final String s7) {
        final HashMap hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll((Map)map);
        }
        if (!TextUtils.isEmpty((CharSequence)s)) {
            hashMap.put((Object)LogField.PAGE.toString(), (Object)s);
        }
        hashMap.put((Object)LogField.EVENTID.toString(), (Object)s2);
        if (!TextUtils.isEmpty((CharSequence)s3)) {
            hashMap.put((Object)LogField.ARG1.toString(), (Object)s3);
        }
        if (!TextUtils.isEmpty((CharSequence)s4)) {
            hashMap.put((Object)LogField.ARG2.toString(), (Object)s4);
        }
        if (!TextUtils.isEmpty((CharSequence)s5)) {
            hashMap.put((Object)LogField.ARG3.toString(), (Object)s5);
        }
        if (!TextUtils.isEmpty((CharSequence)s7)) {
            hashMap.put((Object)LogField.RECORD_TIMESTAMP.toString(), (Object)s7);
        }
        if (!TextUtils.isEmpty((CharSequence)s6)) {
            hashMap.put((Object)LogField.RESERVE3.toString(), (Object)s6);
        }
        return b((Map<String, String>)hashMap);
    }
    
    public static String a(final Map<String, String> map) {
        final StringBuilder sb = new StringBuilder();
        final LogField[] values = LogField.values();
        final int length = values.length;
        int n = 0;
        while (true) {
            String string = null;
            if (n >= length) {
                break;
            }
            final LogField logField = values[n];
            if (logField == LogField.ARGS) {
                break;
            }
            if (map.containsKey((Object)logField.toString())) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append((String)map.get((Object)logField.toString()));
                sb2.append("");
                string = sb2.toString();
                map.remove((Object)logField.toString());
            }
            sb.append(b(string));
            sb.append("||");
            ++n;
        }
        int n2;
        if (map.containsKey((Object)LogField.ARGS.toString())) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append((String)map.get((Object)LogField.ARGS.toString()));
            sb3.append("");
            sb.append(b(sb3.toString()));
            map.remove((Object)LogField.ARGS.toString());
            n2 = 0;
        }
        else {
            n2 = 1;
        }
        for (final String s : map.keySet()) {
            String string2;
            if (map.containsKey((Object)s)) {
                final StringBuilder sb4 = new StringBuilder();
                sb4.append((String)map.get((Object)s));
                sb4.append("");
                string2 = sb4.toString();
            }
            else {
                string2 = null;
            }
            if (n2 != 0) {
                if ("StackTrace".equals((Object)s)) {
                    sb.append("StackTrace=====>");
                    sb.append(string2);
                }
                else {
                    sb.append(b(s));
                    sb.append("=");
                    sb.append(string2);
                }
                n2 = 0;
            }
            else if ("StackTrace".equals((Object)s)) {
                sb.append(",");
                sb.append("StackTrace=====>");
                sb.append(string2);
            }
            else {
                sb.append(",");
                sb.append(b(s));
                sb.append("=");
                sb.append(string2);
            }
        }
        String s3;
        final String s2 = s3 = sb.toString();
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            s3 = s2;
            if (s2.endsWith("||")) {
                final StringBuilder sb5 = new StringBuilder();
                sb5.append(s2);
                sb5.append("-");
                s3 = sb5.toString();
            }
        }
        return s3;
    }
    
    public static Map<String, String> a(final Map<String, String> map) {
        Object o = map;
        if (map == null) {
            o = new HashMap();
        }
        try {
            final String n = b.n();
            if (!TextUtils.isEmpty((CharSequence)n) && !((Map)o).containsKey((Object)LogField.USERNICK.toString())) {
                ((Map)o).put((Object)LogField.USERNICK.toString(), (Object)n);
            }
            final String k = b.k();
            if (!TextUtils.isEmpty((CharSequence)k) && !((Map)o).containsKey((Object)LogField.LL_USERNICK.toString())) {
                ((Map)o).put((Object)LogField.LL_USERNICK.toString(), (Object)k);
            }
            final String o2 = b.o();
            if (!TextUtils.isEmpty((CharSequence)o2) && !((Map)o).containsKey((Object)LogField.USERID.toString())) {
                ((Map)o).put((Object)LogField.USERID.toString(), (Object)o2);
            }
            final String l = b.l();
            if (!TextUtils.isEmpty((CharSequence)l) && !((Map)o).containsKey((Object)LogField.LL_USERID.toString())) {
                ((Map)o).put((Object)LogField.LL_USERID.toString(), (Object)l);
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (!((Map)o).containsKey((Object)LogField.RECORD_TIMESTAMP.toString())) {
                ((Map)o).put((Object)LogField.RECORD_TIMESTAMP.toString(), (Object)String.valueOf(currentTimeMillis));
            }
            if (!((Map)o).containsKey((Object)LogField.START_SESSION_TIMESTAMP.toString())) {
                ((Map)o).put((Object)LogField.START_SESSION_TIMESTAMP.toString(), (Object)String.valueOf((Object)a.B));
            }
            final Map<String, String> a = d.a(com.alibaba.mtl.log.a.getContext());
            if (a != null) {
                for (final String s : a.keySet()) {
                    final String s2 = (String)a.get((Object)s);
                    if (!TextUtils.isEmpty((CharSequence)s2) && !((Map)o).containsKey((Object)s) && !((Map)o).containsKey((Object)s)) {
                        ((Map)o).put((Object)s, (Object)s2);
                    }
                }
            }
            final String c = c((Map<String, String>)o);
            if (!TextUtils.isEmpty((CharSequence)c) && !((Map)o).containsKey((Object)LogField.RESERVES.toString())) {
                ((Map)o).put((Object)LogField.RESERVES.toString(), (Object)c);
            }
            return (Map<String, String>)o;
        }
        finally {
            return (Map<String, String>)o;
        }
    }
    
    private static String b(final String s) {
        String s2 = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            s2 = "-";
        }
        return s2;
    }
    
    public static String b(final Map<String, String> map) {
        if (map != null && map.size() > 0) {
            a(map);
            return a(map);
        }
        return null;
    }
    
    private static String c(final Map<String, String> map) {
        final boolean equalsIgnoreCase = "y".equalsIgnoreCase((String)map.get((Object)LogField.OS.toString()));
        String string;
        final String s = string = "_ap=1";
        if (equalsIgnoreCase) {
            final String r = d.r();
            string = s;
            if (!TextUtils.isEmpty((CharSequence)r)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("_ap=1");
                sb.append(",_did=");
                sb.append(r);
                string = sb.toString();
            }
        }
        final String s2 = (String)map.get((Object)LogField.APPKEY.toString());
        String string2 = string;
        if (!TextUtils.isEmpty((CharSequence)b.getAppkey())) {
            string2 = string;
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                string2 = string;
                if (!b.getAppkey().equalsIgnoreCase(s2)) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(string);
                    sb2.append(",_mak=");
                    sb2.append(b.getAppkey());
                    string2 = sb2.toString();
                }
            }
        }
        return string2;
    }
}
