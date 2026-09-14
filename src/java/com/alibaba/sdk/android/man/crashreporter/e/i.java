package com.alibaba.sdk.android.man.crashreporter.e;

import java.util.Iterator;
import java.util.Map;

public class i
{
    public static int a(final String s) {
        final int length = s.length();
        int n = 0;
        int i = 0;
        if (length > 0) {
            final char[] charArray = s.toCharArray();
            n = 0;
            while (i < charArray.length) {
                n = n * 31 + charArray[i];
                ++i;
            }
        }
        return n;
    }
    
    public static String a(final Object o) {
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
    
    public static String a(final String s, final String s2) {
        if (a((CharSequence)s)) {
            return s2;
        }
        return s;
    }
    
    public static boolean a(final CharSequence charSequence) {
        if (charSequence != null) {
            final int length = charSequence.length();
            if (length != 0) {
                for (int i = 0; i < length; ++i) {
                    if (!Character.isWhitespace(charSequence.charAt(i))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static boolean b(final CharSequence charSequence) {
        return a(charSequence) ^ true;
    }
    
    public static boolean b(final String s) {
        return s == null || s.length() <= 0;
    }
    
    public static String c(final Map<String, String> map) {
        if (map != null) {
            int n = 1;
            final StringBuffer sb = new StringBuffer();
            for (final String s : map.keySet()) {
                final String s2 = (String)map.get((Object)s);
                if (s2 != null && s != null) {
                    if (n != 0) {
                        if (!"--invalid--".equals((Object)s2)) {
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(s);
                            sb2.append("=");
                            sb2.append(s2);
                            sb.append(sb2.toString());
                        }
                        else {
                            sb.append(s);
                        }
                        n = 0;
                    }
                    else if (!"--invalid--".equals((Object)s2)) {
                        sb.append(",");
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(s);
                        sb3.append("=");
                        sb3.append(s2);
                        sb.append(sb3.toString());
                    }
                    else {
                        sb.append(",");
                        sb.append(s);
                    }
                }
            }
            return sb.toString();
        }
        return null;
    }
}
