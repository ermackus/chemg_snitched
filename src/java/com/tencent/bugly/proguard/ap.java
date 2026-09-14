package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class ap extends k implements Cloneable
{
    private static ao m;
    private static Map<String, String> n;
    private static boolean o;
    public boolean a;
    public boolean b;
    public boolean c;
    public String d;
    public String e;
    public ao f;
    public Map<String, String> g;
    public long h;
    public int i;
    private String j;
    private String k;
    private int l;
    
    static {
        ap.o = (ap.class.desiredAssertionStatus() ^ true);
        ap.m = new ao();
        (ap.n = (Map<String, String>)new HashMap()).put((Object)"", (Object)"");
    }
    
    public ap() {
        this.a = true;
        this.b = true;
        this.c = true;
        this.d = "";
        this.e = "";
        this.f = null;
        this.g = null;
        this.h = 0L;
        this.j = "";
        this.k = "";
        this.l = 0;
        this.i = 0;
    }
    
    public final void a(final i i) {
        this.a = i.a(0, true);
        this.b = i.a(1, true);
        this.c = i.a(2, true);
        this.d = i.b(3, false);
        this.e = i.b(4, false);
        this.f = (ao)i.a((k)ap.m, 5, false);
        this.g = (Map<String, String>)i.a((Object)ap.n, 6, false);
        this.h = i.a(this.h, 7, false);
        this.j = i.b(8, false);
        this.k = i.b(9, false);
        this.l = i.a(this.l, 10, false);
        this.i = i.a(this.i, 11, false);
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
        final ao f = this.f;
        if (f != null) {
            j.a((k)f, 5);
        }
        final Map<String, String> g = this.g;
        if (g != null) {
            j.a((Map)g, 6);
        }
        j.a(this.h, 7);
        final String i = this.j;
        if (i != null) {
            j.a(i, 8);
        }
        final String k = this.k;
        if (k != null) {
            j.a(k, 9);
        }
        j.a(this.l, 10);
        j.a(this.i, 11);
    }
    
    public final void a(final StringBuilder sb, final int n) {
        final h h = new h(sb, n);
        h.a(this.a, "enable");
        h.a(this.b, "enableUserInfo");
        h.a(this.c, "enableQuery");
        h.a(this.d, "url");
        h.a(this.e, "expUrl");
        h.a((k)this.f, "security");
        h.a((Map)this.g, "valueMap");
        h.a(this.h, "strategylastUpdateTime");
        h.a(this.j, "httpsUrl");
        h.a(this.k, "httpsExpUrl");
        h.a(this.l, "eventRecordCount");
        h.a(this.i, "eventTimeInterval");
    }
    
    public final Object clone() {
        Object clone;
        try {
            clone = super.clone();
        }
        catch (final CloneNotSupportedException ex) {
            if (!ap.o) {
                throw new AssertionError();
            }
            clone = null;
        }
        return clone;
    }
    
    public final boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        final ap ap = (ap)o;
        return com.tencent.bugly.proguard.l.a(this.a, ap.a) && com.tencent.bugly.proguard.l.a(this.b, ap.b) && com.tencent.bugly.proguard.l.a(this.c, ap.c) && com.tencent.bugly.proguard.l.a((Object)this.d, (Object)ap.d) && com.tencent.bugly.proguard.l.a((Object)this.e, (Object)ap.e) && com.tencent.bugly.proguard.l.a((Object)this.f, (Object)ap.f) && com.tencent.bugly.proguard.l.a((Object)this.g, (Object)ap.g) && com.tencent.bugly.proguard.l.a(this.h, ap.h) && com.tencent.bugly.proguard.l.a((Object)this.j, (Object)ap.j) && com.tencent.bugly.proguard.l.a((Object)this.k, (Object)ap.k) && com.tencent.bugly.proguard.l.a(this.l, ap.l) && com.tencent.bugly.proguard.l.a(this.i, ap.i);
    }
    
    public final int hashCode() {
        try {
            throw new Exception("Need define key first!");
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
