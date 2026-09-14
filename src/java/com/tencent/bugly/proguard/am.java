package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class am extends k
{
    private static byte[] y;
    private static Map<String, String> z;
    public int a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g;
    public byte[] h;
    public String i;
    public String j;
    public Map<String, String> k;
    public String l;
    public long m;
    public String n;
    public String o;
    public String p;
    public long q;
    public String r;
    public String s;
    public String t;
    private String u;
    private String v;
    private String w;
    private String x;
    
    static {
        (am.y = new byte[1])[0] = 0;
        (am.z = (Map<String, String>)new HashMap()).put((Object)"", (Object)"");
    }
    
    public am() {
        this.a = 0;
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = 0;
        this.h = null;
        this.i = "";
        this.j = "";
        this.k = null;
        this.l = "";
        this.m = 0L;
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = 0L;
        this.u = "";
        this.r = "";
        this.v = "";
        this.w = "";
        this.s = "";
        this.t = "";
        this.x = "";
    }
    
    public final void a(final i i) {
        this.a = i.a(this.a, 0, true);
        this.b = i.b(1, true);
        this.c = i.b(2, true);
        this.d = i.b(3, true);
        this.e = i.b(4, false);
        this.f = i.b(5, true);
        this.g = i.a(this.g, 6, true);
        this.h = i.c(7, true);
        this.i = i.b(8, false);
        this.j = i.b(9, false);
        this.k = (Map<String, String>)i.a((Object)am.z, 10, false);
        this.l = i.b(11, false);
        this.m = i.a(this.m, 12, false);
        this.n = i.b(13, false);
        this.o = i.b(14, false);
        this.p = i.b(15, false);
        this.q = i.a(this.q, 16, false);
        this.u = i.b(17, false);
        this.r = i.b(18, false);
        this.v = i.b(19, false);
        this.w = i.b(20, false);
        this.s = i.b(21, false);
        this.t = i.b(22, false);
        this.x = i.b(23, false);
    }
    
    public final void a(final j j) {
        j.a(this.a, 0);
        j.a(this.b, 1);
        j.a(this.c, 2);
        j.a(this.d, 3);
        final String e = this.e;
        if (e != null) {
            j.a(e, 4);
        }
        j.a(this.f, 5);
        j.a(this.g, 6);
        j.a(this.h, 7);
        final String i = this.i;
        if (i != null) {
            j.a(i, 8);
        }
        final String k = this.j;
        if (k != null) {
            j.a(k, 9);
        }
        final Map<String, String> l = this.k;
        if (l != null) {
            j.a((Map)l, 10);
        }
        final String m = this.l;
        if (m != null) {
            j.a(m, 11);
        }
        j.a(this.m, 12);
        final String n = this.n;
        if (n != null) {
            j.a(n, 13);
        }
        final String o = this.o;
        if (o != null) {
            j.a(o, 14);
        }
        final String p = this.p;
        if (p != null) {
            j.a(p, 15);
        }
        j.a(this.q, 16);
        final String u = this.u;
        if (u != null) {
            j.a(u, 17);
        }
        final String r = this.r;
        if (r != null) {
            j.a(r, 18);
        }
        final String v = this.v;
        if (v != null) {
            j.a(v, 19);
        }
        final String w = this.w;
        if (w != null) {
            j.a(w, 20);
        }
        final String s = this.s;
        if (s != null) {
            j.a(s, 21);
        }
        final String t = this.t;
        if (t != null) {
            j.a(t, 22);
        }
        final String x = this.x;
        if (x != null) {
            j.a(x, 23);
        }
    }
}
