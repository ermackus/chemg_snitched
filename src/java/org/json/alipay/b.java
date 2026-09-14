package org.json.alipay;

import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class b
{
    public static final Object a;
    public Map b;
    
    static {
        a = new a((byte)0);
    }
    
    public b() {
        this.b = (Map)new HashMap();
    }
    
    public b(final String s) {
        this(new c(s));
    }
    
    public b(final Map map) {
        Object b = map;
        if (map == null) {
            b = new HashMap();
        }
        this.b = (Map)b;
    }
    
    public b(final c c) {
        this();
        if (c.c() != '{') {
            throw c.a("A JSONObject text must begin with '{'");
        }
        while (true) {
            final char c2 = c.c();
            if (c2 == '\0') {
                throw c.a("A JSONObject text must end with '}'");
            }
            if (c2 == '}') {
                return;
            }
            c.a();
            final String string = c.d().toString();
            final char c3 = c.c();
            if (c3 == '=') {
                if (c.b() != '>') {
                    c.a();
                }
            }
            else if (c3 != ':') {
                throw c.a("Expected a ':' after a key");
            }
            final Object d = c.d();
            if (string == null) {
                throw new JSONException("Null key.");
            }
            if (d != null) {
                b(d);
                this.b.put((Object)string, d);
            }
            else {
                this.b.remove((Object)string);
            }
            final char c4 = c.c();
            if (c4 != ',' && c4 != ';') {
                if (c4 == '}') {
                    return;
                }
                throw c.a("Expected a ',' or '}'");
            }
            else {
                if (c.c() == '}') {
                    return;
                }
                c.a();
            }
        }
    }
    
    public static String a(final Object o) {
        if (o == null || o.equals(null)) {
            return "null";
        }
        if (o instanceof Number) {
            final Number n = (Number)o;
            if (n != null) {
                b(n);
                String s;
                String substring = s = n.toString();
                if (substring.indexOf(46) > 0) {
                    s = substring;
                    if (substring.indexOf(101) < 0) {
                        s = substring;
                        if (substring.indexOf(69) < 0) {
                            while (substring.endsWith("0")) {
                                substring = substring.substring(0, substring.length() - 1);
                            }
                            s = substring;
                            if (substring.endsWith(".")) {
                                s = substring.substring(0, substring.length() - 1);
                            }
                        }
                    }
                }
                return s;
            }
            throw new JSONException("Null pointer");
        }
        else {
            if (o instanceof Boolean || o instanceof b || o instanceof org.json.alipay.a) {
                return o.toString();
            }
            if (o instanceof Map) {
                return new b((Map)o).toString();
            }
            if (o instanceof Collection) {
                return new org.json.alipay.a((Collection)o).toString();
            }
            if (o.getClass().isArray()) {
                return new org.json.alipay.a(o).toString();
            }
            return c(o.toString());
        }
    }
    
    public static void b(final Object o) {
        if (o != null) {
            if (o instanceof Double) {
                final Double n = (Double)o;
                if (n.isInfinite() || n.isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            }
            else if (o instanceof Float) {
                final Float n2 = (Float)o;
                if (n2.isInfinite() || n2.isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            }
        }
    }
    
    public static String c(final String s) {
        if (s != null && s.length() != 0) {
            final int length = s.length();
            final StringBuffer sb = new StringBuffer(length + 4);
            sb.append('\"');
            int i = 0;
            int n = 0;
            while (i < length) {
                final char char1 = s.charAt(i);
                Label_0284: {
                    String string2 = null;
                    Label_0235: {
                        if (char1 != '\f') {
                            if (char1 != '\r') {
                                Label_0260: {
                                    if (char1 != '\"') {
                                        if (char1 != '/') {
                                            if (char1 != '\\') {
                                                switch (char1) {
                                                    default: {
                                                        if (char1 < ' ' || (char1 >= '\u0080' && char1 < ' ') || (char1 >= '\u2000' && char1 < '\u2100')) {
                                                            final StringBuilder sb2 = new StringBuilder("000");
                                                            sb2.append(Integer.toHexString((int)char1));
                                                            final String string = sb2.toString();
                                                            final StringBuilder sb3 = new StringBuilder("\\u");
                                                            sb3.append(string.substring(string.length() - 4));
                                                            string2 = sb3.toString();
                                                            break Label_0235;
                                                        }
                                                        break Label_0260;
                                                    }
                                                    case 10: {
                                                        string2 = "\\n";
                                                        break Label_0235;
                                                    }
                                                    case 9: {
                                                        string2 = "\\t";
                                                        break Label_0235;
                                                    }
                                                    case 8: {
                                                        string2 = "\\b";
                                                        break Label_0235;
                                                    }
                                                }
                                            }
                                        }
                                        else if (n != 60) {
                                            break Label_0260;
                                        }
                                    }
                                    sb.append('\\');
                                }
                                sb.append(char1);
                                break Label_0284;
                            }
                            string2 = "\\r";
                        }
                        else {
                            string2 = "\\f";
                        }
                    }
                    sb.append(string2);
                }
                ++i;
                n = char1;
            }
            sb.append('\"');
            return sb.toString();
        }
        return "\"\"";
    }
    
    public final Object a(final String s) {
        Object value;
        if (s == null) {
            value = null;
        }
        else {
            value = this.b.get((Object)s);
        }
        if (value != null) {
            return value;
        }
        final StringBuilder sb = new StringBuilder("JSONObject[");
        sb.append(c(s));
        sb.append("] not found.");
        throw new JSONException(sb.toString());
    }
    
    public final Iterator a() {
        return this.b.keySet().iterator();
    }
    
    public final boolean b(final String s) {
        return this.b.containsKey((Object)s);
    }
    
    @Override
    public String toString() {
        try {
            final Iterator a = this.a();
            final StringBuffer sb = new StringBuffer("{");
            while (a.hasNext()) {
                if (sb.length() > 1) {
                    sb.append(',');
                }
                final Object next = a.next();
                sb.append(c(next.toString()));
                sb.append(':');
                sb.append(a(this.b.get(next)));
            }
            sb.append('}');
            return sb.toString();
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    public static final class a
    {
        public final Object clone() {
            return this;
        }
        
        @Override
        public final boolean equals(final Object o) {
            return o == null || o == this;
        }
        
        @Override
        public final String toString() {
            return "null";
        }
    }
}
