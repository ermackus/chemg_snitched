package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class an extends k
{
    private static byte[] i;
    private static Map<String, String> j;
    public byte a;
    public int b;
    public byte[] c;
    public long d;
    public String e;
    private String f;
    private String g;
    private Map<String, String> h;
    
    static {
        (an.i = new byte[1])[0] = 0;
        (an.j = (Map<String, String>)new HashMap()).put((Object)"", (Object)"");
    }
    
    public an() {
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.f = "";
        this.d = 0L;
        this.g = "";
        this.e = "";
        this.h = null;
    }
    
    public final void a(final i i) {
        this.a = i.a(this.a, 0, true);
        this.b = i.a(this.b, 1, true);
        this.c = i.c(2, false);
        this.f = i.b(3, false);
        this.d = i.a(this.d, 4, false);
        this.g = i.b(5, false);
        this.e = i.b(6, false);
        this.h = (Map<String, String>)i.a((Object)an.j, 7, false);
    }
    
    public final void a(final j j) {
        j.a(this.a, 0);
        j.a(this.b, 1);
        final byte[] c = this.c;
        if (c != null) {
            j.a(c, 2);
        }
        final String f = this.f;
        if (f != null) {
            j.a(f, 3);
        }
        j.a(this.d, 4);
        final String g = this.g;
        if (g != null) {
            j.a(g, 5);
        }
        final String e = this.e;
        if (e != null) {
            j.a(e, 6);
        }
        final Map<String, String> h = this.h;
        if (h != null) {
            j.a((Map)h, 7);
        }
    }
}
