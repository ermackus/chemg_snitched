package com.alibaba.mtl.log.d;

import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.net.URLEncoder;
import java.util.Map;
import com.alibaba.mtl.log.sign.IRequestAuth;
import android.content.Context;
import android.text.TextUtils;
import com.alibaba.mtl.log.sign.BaseRequestAuth;
import com.alibaba.mtl.log.sign.SecurityRequestAuth;
import com.alibaba.mtl.log.model.LogField;
import com.alibaba.mtl.log.a;

public class u
{
    private static final String TAG;
    
    static {
        TAG = u.class.getSimpleName();
    }
    
    private static String a(final String s, String string, String sign, String s2) throws Exception {
        final Context context = a.getContext();
        final String appkey = b.getAppkey();
        String m;
        if ((m = b.m()) == null) {
            m = "";
        }
        final String s3 = (String)d.a(context).get((Object)LogField.APPVERSION.toString());
        final String s4 = (String)d.a(context).get((Object)LogField.OS.toString());
        final String s5 = (String)d.a(context).get((Object)LogField.UTDID.toString());
        final String value = String.valueOf(System.currentTimeMillis());
        final IRequestAuth a = com.alibaba.mtl.log.a.a();
        final boolean b = a instanceof SecurityRequestAuth;
        String s6 = "1";
        String s7 = "0";
        if (!b) {
            if (a instanceof BaseRequestAuth) {
                if (!((BaseRequestAuth)a).isEncode()) {
                    s6 = "0";
                }
                s7 = s6;
                s6 = "0";
            }
            else {
                s6 = "0";
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(appkey);
        sb.append(m);
        sb.append(s3);
        sb.append(s4);
        sb.append("2.6.4.10_for_bc");
        sb.append(s5);
        sb.append(value);
        sb.append("3.0");
        sb.append(s6);
        if (sign == null) {
            sign = "";
        }
        sb.append(sign);
        if (s2 == null) {
            s2 = "";
        }
        sb.append(s2);
        sign = a.getSign(j.b(sb.toString().getBytes()));
        if (!TextUtils.isEmpty((CharSequence)string)) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            sb2.append("&");
            string = sb2.toString();
        }
        else {
            string = "";
        }
        return String.format("%s?%sak=%s&av=%s&c=%s&v=%s&s=%s&d=%s&sv=%s&p=%s&t=%s&u=%s&is=%s&k=%s", new Object[] { s, string, c(appkey), c(s3), c(m), c("3.0"), c(sign), c(s5), "2.6.4.10_for_bc", s4, value, "", s6, s7 });
    }
    
    public static String a(String s, final Map<String, Object> map, final Map<String, Object> map2) throws Exception {
        String s2;
        String string = s2 = "";
        if (map2 != null) {
            s2 = string;
            if (map2.size() > 0) {
                final Set keySet = map2.keySet();
                final String[] array = new String[keySet.size()];
                keySet.toArray((Object[])array);
                final String[] a = g.a().a(array, true);
                final int length = a.length;
                int n = 0;
                while (true) {
                    s2 = string;
                    if (n >= length) {
                        break;
                    }
                    final String s3 = a[n];
                    final byte[] array2 = (byte[])map2.get((Object)s3);
                    final StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(s3);
                    sb.append(j.b(array2));
                    string = sb.toString();
                    ++n;
                }
            }
        }
        try {
            s = a(s, null, null, s2);
        }
        finally {
            s = a(com.alibaba.mtl.log.a.a.g(), null, null, s2);
        }
        final String s4 = com.alibaba.mtl.log.a.a.S;
        String string2 = s;
        if (!TextUtils.isEmpty((CharSequence)s4)) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(s);
            sb2.append("&dk=");
            sb2.append(URLEncoder.encode(s4, "UTF-8"));
            string2 = sb2.toString();
        }
        return string2;
    }
    
    private static String c(final String s) {
        if (s == null) {
            return "";
        }
        try {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (final UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return s;
        }
    }
}
