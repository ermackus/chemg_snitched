package com.tencent.bugly.proguard;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public final class ar extends k implements Cloneable
{
    private static ArrayList<aq> f;
    private static Map<String, String> g;
    public byte a;
    public String b;
    public String c;
    public ArrayList<aq> d;
    public Map<String, String> e;
    
    public ar() {
        this.a = 0;
        this.b = "";
        this.c = "";
        this.d = null;
        this.e = null;
    }
    
    public final void a(final i i) {
        this.a = i.a(this.a, 0, true);
        this.b = i.b(1, false);
        this.c = i.b(2, false);
        if (ar.f == null) {
            (ar.f = (ArrayList<aq>)new ArrayList()).add((Object)new aq());
        }
        this.d = (ArrayList<aq>)i.a((Object)ar.f, 3, false);
        if (ar.g == null) {
            (ar.g = (Map<String, String>)new HashMap()).put((Object)"", (Object)"");
        }
        this.e = (Map<String, String>)i.a((Object)ar.g, 4, false);
    }
    
    public final void a(final j j) {
        j.a(this.a, 0);
        final String b = this.b;
        if (b != null) {
            j.a(b, 1);
        }
        final String c = this.c;
        if (c != null) {
            j.a(c, 2);
        }
        final ArrayList<aq> d = this.d;
        if (d != null) {
            j.a((Collection)d, 3);
        }
        final Map<String, String> e = this.e;
        if (e != null) {
            j.a((Map)e, 4);
        }
    }
    
    public final void a(final StringBuilder sb, final int n) {
    }
}
