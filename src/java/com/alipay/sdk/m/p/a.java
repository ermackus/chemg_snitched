package com.alipay.sdk.m.p;

import android.text.TextUtils;

public class a
{
    public static String a(String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return "";
        }
        final String[] split = s.split("&");
        if (split.length == 0) {
            return "";
        }
        final int length = split.length;
        String s2 = null;
        Object o = null;
        Object o2 = s = null;
        Object e;
        Object d;
        Object f;
        String c;
        for (int i = 0; i < length; ++i, s2 = (String)e, o = d, o2 = f, s = c) {
            final String s3 = split[i];
            e = s2;
            if (TextUtils.isEmpty((CharSequence)s2)) {
                e = e(s3);
            }
            d = o;
            if (TextUtils.isEmpty((CharSequence)o)) {
                d = d(s3);
            }
            f = o2;
            if (TextUtils.isEmpty((CharSequence)o2)) {
                f = f(s3);
            }
            c = s;
            if (TextUtils.isEmpty((CharSequence)s)) {
                c = c(s3);
            }
        }
        final StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("biz_type=");
            sb2.append(s2);
            sb2.append(";");
            sb.append(sb2.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)o)) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("biz_no=");
            sb3.append((String)o);
            sb3.append(";");
            sb.append(sb3.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)o2)) {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("trade_no=");
            sb4.append((String)o2);
            sb4.append(";");
            sb.append(sb4.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("app_userid=");
            sb5.append(s);
            sb5.append(";");
            sb.append(sb5.toString());
        }
        final String s4 = s = sb.toString();
        if (s4.endsWith(";")) {
            s = s4.substring(0, s4.length() - 1);
        }
        return s;
    }
    
    public static String b(String replaceAll) {
        final String[] split = replaceAll.split("=");
        if (split.length > 1) {
            final String s = replaceAll = split[1];
            if (s.contains((CharSequence)"\"")) {
                replaceAll = s.replaceAll("\"", "");
            }
        }
        else {
            replaceAll = null;
        }
        return replaceAll;
    }
    
    public static String c(final String s) {
        if (!s.contains((CharSequence)"app_userid")) {
            return null;
        }
        return b(s);
    }
    
    public static String d(final String s) {
        if (!s.contains((CharSequence)"biz_no")) {
            return null;
        }
        return b(s);
    }
    
    public static String e(final String s) {
        if (!s.contains((CharSequence)"biz_type")) {
            return null;
        }
        return b(s);
    }
    
    public static String f(final String s) {
        if (s.contains((CharSequence)"trade_no") && !s.startsWith("out_trade_no")) {
            return b(s);
        }
        return null;
    }
}
