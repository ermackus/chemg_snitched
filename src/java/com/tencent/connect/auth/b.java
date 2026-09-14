package com.tencent.connect.auth;

import com.tencent.tauth.IUiListener;
import java.util.HashMap;

public class b
{
    public static b a;
    static final boolean d;
    private static int e;
    public HashMap<String, a> b;
    public final String c;
    
    static {
        d = (b.class.desiredAssertionStatus() ^ true);
        b.e = 0;
    }
    
    public b() {
        this.b = (HashMap<String, a>)new HashMap();
        this.c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    }
    
    public static b a() {
        if (b.a == null) {
            b.a = new b();
        }
        return b.a;
    }
    
    public static int b() {
        return ++b.e;
    }
    
    public String a(final a a) {
        final int b = b();
        try {
            final HashMap<String, a> b2 = this.b;
            final StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(b);
            b2.put((Object)sb.toString(), (Object)a);
        }
        finally {
            final Throwable t;
            t.printStackTrace();
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        sb2.append(b);
        return sb2.toString();
    }
    
    public String c() {
        final int n = (int)Math.ceil(Math.random() * 20.0 + 3.0);
        final char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        final int length = charArray.length;
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(charArray[(int)(Math.random() * length)]);
        }
        return sb.toString();
    }
    
    public static class a
    {
        public IUiListener a;
        public com.tencent.connect.auth.a b;
        public String c;
    }
}
