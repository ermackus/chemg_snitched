package com.tencent.bugly.proguard;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public final class ak extends k
{
    private static ArrayList<aj> A;
    private static Map<String, String> B;
    private static Map<String, String> C;
    private static Map<String, String> v;
    private static ai w;
    private static ah x;
    private static ArrayList<ah> y;
    private static ArrayList<ah> z;
    public String a;
    public long b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public Map<String, String> h;
    public String i;
    public ai j;
    public int k;
    public String l;
    public String m;
    public ah n;
    public ArrayList<ah> o;
    public ArrayList<ah> p;
    public ArrayList<aj> q;
    public Map<String, String> r;
    public Map<String, String> s;
    private String t;
    private boolean u;
    
    static {
        (ak.v = (Map<String, String>)new HashMap()).put((Object)"", (Object)"");
        ak.w = new ai();
        ak.x = new ah();
        (ak.y = (ArrayList<ah>)new ArrayList()).add((Object)new ah());
        (ak.z = (ArrayList<ah>)new ArrayList()).add((Object)new ah());
        (ak.A = (ArrayList<aj>)new ArrayList()).add((Object)new aj());
        (ak.B = (Map<String, String>)new HashMap()).put((Object)"", (Object)"");
        (ak.C = (Map<String, String>)new HashMap()).put((Object)"", (Object)"");
    }
    
    public ak() {
        this.a = "";
        this.b = 0L;
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = null;
        this.i = "";
        this.j = null;
        this.k = 0;
        this.l = "";
        this.m = "";
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = "";
        this.u = true;
    }
    
    public final void a(final i i) {
        this.a = i.b(0, true);
        this.b = i.a(this.b, 1, true);
        this.c = i.b(2, true);
        this.d = i.b(3, false);
        this.e = i.b(4, false);
        this.f = i.b(5, false);
        this.g = i.b(6, false);
        this.h = (Map<String, String>)i.a((Object)ak.v, 7, false);
        this.i = i.b(8, false);
        this.j = (ai)i.a((k)ak.w, 9, false);
        this.k = i.a(this.k, 10, false);
        this.l = i.b(11, false);
        this.m = i.b(12, false);
        this.n = (ah)i.a((k)ak.x, 13, false);
        this.o = (ArrayList<ah>)i.a((Object)ak.y, 14, false);
        this.p = (ArrayList<ah>)i.a((Object)ak.z, 15, false);
        this.q = (ArrayList<aj>)i.a((Object)ak.A, 16, false);
        this.r = (Map<String, String>)i.a((Object)ak.B, 17, false);
        this.s = (Map<String, String>)i.a((Object)ak.C, 18, false);
        this.t = i.b(19, false);
        this.u = i.a(20, false);
    }
    
    public final void a(final j j) {
        j.a(this.a, 0);
        j.a(this.b, 1);
        j.a(this.c, 2);
        final String d = this.d;
        if (d != null) {
            j.a(d, 3);
        }
        final String e = this.e;
        if (e != null) {
            j.a(e, 4);
        }
        final String f = this.f;
        if (f != null) {
            j.a(f, 5);
        }
        final String g = this.g;
        if (g != null) {
            j.a(g, 6);
        }
        final Map<String, String> h = this.h;
        if (h != null) {
            j.a((Map)h, 7);
        }
        final String i = this.i;
        if (i != null) {
            j.a(i, 8);
        }
        final ai k = this.j;
        if (k != null) {
            j.a((k)k, 9);
        }
        j.a(this.k, 10);
        final String l = this.l;
        if (l != null) {
            j.a(l, 11);
        }
        final String m = this.m;
        if (m != null) {
            j.a(m, 12);
        }
        final ah n = this.n;
        if (n != null) {
            j.a((k)n, 13);
        }
        final ArrayList<ah> o = this.o;
        if (o != null) {
            j.a((Collection)o, 14);
        }
        final ArrayList<ah> p = this.p;
        if (p != null) {
            j.a((Collection)p, 15);
        }
        final ArrayList<aj> q = this.q;
        if (q != null) {
            j.a((Collection)q, 16);
        }
        final Map<String, String> r = this.r;
        if (r != null) {
            j.a((Map)r, 17);
        }
        final Map<String, String> s = this.s;
        if (s != null) {
            j.a((Map)s, 18);
        }
        final String t = this.t;
        if (t != null) {
            j.a(t, 19);
        }
        j.a(this.u, 20);
    }
}
