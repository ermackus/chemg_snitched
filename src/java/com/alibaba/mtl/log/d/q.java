package com.alibaba.mtl.log.d;

import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class q
{
    public static Map<String, String> b(final Map<String, String> map) {
        if (map != null) {
            final HashMap hashMap = new HashMap();
            for (final String s : map.keySet()) {
                if (s instanceof String) {
                    final String s2 = (String)map.get((Object)s);
                    if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
                        continue;
                    }
                    try {
                        ((Map)hashMap).put((Object)URLEncoder.encode(s, "UTF-8"), (Object)URLEncoder.encode(s2, "UTF-8"));
                    }
                    catch (final UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            return (Map<String, String>)hashMap;
        }
        return map;
    }
    
    public static String convertObjectToString(final Object o) {
        if (o == null) {
            return "";
        }
        if (o instanceof String) {
            return ((String)o).toString();
        }
        if (o instanceof Integer) {
            final StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append((int)o);
            return sb.toString();
        }
        if (o instanceof Long) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append((long)o);
            return sb2.toString();
        }
        if (o instanceof Double) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append((double)o);
            return sb3.toString();
        }
        if (o instanceof Float) {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("");
            sb4.append((float)o);
            return sb4.toString();
        }
        if (o instanceof Short) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("");
            sb5.append((int)(short)o);
            return sb5.toString();
        }
        if (o instanceof Byte) {
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("");
            sb6.append((int)(byte)o);
            return sb6.toString();
        }
        if (o instanceof Boolean) {
            return ((Boolean)o).toString();
        }
        if (o instanceof Character) {
            return ((Character)o).toString();
        }
        return o.toString();
    }
    
    public static String d(final Map<String, String> map) {
        if (map != null) {
            int n = 1;
            final StringBuffer sb = new StringBuffer();
            for (final Object next : map.keySet()) {
                final String convertObjectToString = convertObjectToString(map.get(next));
                final String convertObjectToString2 = convertObjectToString(next);
                if (convertObjectToString != null && convertObjectToString2 != null) {
                    if (n != 0) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(convertObjectToString2);
                        sb2.append("=");
                        sb2.append(convertObjectToString);
                        sb.append(sb2.toString());
                        n = 0;
                    }
                    else {
                        sb.append(",");
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(convertObjectToString2);
                        sb3.append("=");
                        sb3.append(convertObjectToString);
                        sb.append(sb3.toString());
                    }
                }
            }
            return sb.toString();
        }
        return null;
    }
}
