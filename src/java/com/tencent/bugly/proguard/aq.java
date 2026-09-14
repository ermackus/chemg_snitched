package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class aq extends k
{
    private static Map<String, String> i;
    public long a;
    public byte b;
    public String c;
    public String d;
    public String e;
    public Map<String, String> f;
    public boolean g;
    private String h;
    
    static {
        (aq.i = (Map<String, String>)new HashMap()).put((Object)"", (Object)"");
    }
    
    public aq() {
        this.a = 0L;
        this.b = 0;
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = null;
        this.h = "";
        this.g = true;
    }
    
    public final void a(final i i) {
        this.a = i.a(this.a, 0, true);
        this.b = i.a(this.b, 1, true);
        this.c = i.b(2, false);
        this.d = i.b(3, false);
        this.e = i.b(4, false);
        this.f = (Map<String, String>)i.a((Object)aq.i, 5, false);
        this.h = i.b(6, false);
        this.g = i.a(7, false);
    }
    
    public final void a(final j j) {
        j.a(this.a, 0);
        j.a(this.b, 1);
        final String c = this.c;
        if (c != null) {
            j.a(c, 2);
        }
        final String d = this.d;
        if (d != null) {
            j.a(d, 3);
        }
        final String e = this.e;
        if (e != null) {
            j.a(e, 4);
        }
        final Map<String, String> f = this.f;
        if (f != null) {
            j.a((Map)f, 5);
        }
        final String h = this.h;
        if (h != null) {
            j.a(h, 6);
        }
        j.a(this.g, 7);
    }
}
