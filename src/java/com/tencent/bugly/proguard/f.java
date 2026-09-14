package com.tencent.bugly.proguard;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public final class f extends k
{
    private static byte[] k;
    private static Map<String, String> l;
    private static boolean m;
    public short a;
    public int b;
    public String c;
    public String d;
    public byte[] e;
    private byte f;
    private int g;
    private int h;
    private Map<String, String> i;
    private Map<String, String> j;
    
    static {
        f.m = (f.class.desiredAssertionStatus() ^ true);
        f.k = null;
        f.l = null;
    }
    
    public f() {
        this.a = 0;
        this.f = 0;
        this.g = 0;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.h = 0;
    }
    
    public final void a(final i i) {
        try {
            this.a = i.a(this.a, 1, true);
            this.f = i.a(this.f, 2, true);
            this.g = i.a(this.g, 3, true);
            this.b = i.a(this.b, 4, true);
            this.c = i.b(5, true);
            this.d = i.b(6, true);
            if (com.tencent.bugly.proguard.f.k == null) {
                com.tencent.bugly.proguard.f.k = new byte[] { 0 };
            }
            this.e = i.c(7, true);
            this.h = i.a(this.h, 8, true);
            if (com.tencent.bugly.proguard.f.l == null) {
                (com.tencent.bugly.proguard.f.l = (Map<String, String>)new HashMap()).put((Object)"", (Object)"");
            }
            this.i = (Map<String, String>)i.a((Object)com.tencent.bugly.proguard.f.l, 9, true);
            if (com.tencent.bugly.proguard.f.l == null) {
                (com.tencent.bugly.proguard.f.l = (Map<String, String>)new HashMap()).put((Object)"", (Object)"");
            }
            this.j = (Map<String, String>)i.a((Object)com.tencent.bugly.proguard.f.l, 10, true);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            final PrintStream out = System.out;
            final StringBuilder sb = new StringBuilder("RequestPacket decode error ");
            sb.append(com.tencent.bugly.proguard.e.a(this.e));
            out.println(sb.toString());
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    public final void a(final j j) {
        j.a(this.a, 1);
        j.a(this.f, 2);
        j.a(this.g, 3);
        j.a(this.b, 4);
        j.a(this.c, 5);
        j.a(this.d, 6);
        j.a(this.e, 7);
        j.a(this.h, 8);
        j.a((Map)this.i, 9);
        j.a((Map)this.j, 10);
    }
    
    public final void a(final StringBuilder sb, final int n) {
        final h h = new h(sb, n);
        h.a(this.a, "iVersion");
        h.a(this.f, "cPacketType");
        h.a(this.g, "iMessageType");
        h.a(this.b, "iRequestId");
        h.a(this.c, "sServantName");
        h.a(this.d, "sFuncName");
        h.a(this.e, "sBuffer");
        h.a(this.h, "iTimeout");
        h.a((Map)this.i, "context");
        h.a((Map)this.j, "status");
    }
    
    public final Object clone() {
        Object clone;
        try {
            clone = super.clone();
        }
        catch (final CloneNotSupportedException ex) {
            if (!com.tencent.bugly.proguard.f.m) {
                throw new AssertionError();
            }
            clone = null;
        }
        return clone;
    }
    
    public final boolean equals(final Object o) {
        final f f = (f)o;
        final short a = f.a;
        final Integer value = 1;
        return com.tencent.bugly.proguard.l.a(1, (int)a) && com.tencent.bugly.proguard.l.a(1, (int)f.f) && com.tencent.bugly.proguard.l.a(1, f.g) && com.tencent.bugly.proguard.l.a(1, f.b) && com.tencent.bugly.proguard.l.a((Object)value, (Object)f.c) && com.tencent.bugly.proguard.l.a((Object)value, (Object)f.d) && com.tencent.bugly.proguard.l.a((Object)value, (Object)f.e) && com.tencent.bugly.proguard.l.a(1, f.h) && com.tencent.bugly.proguard.l.a((Object)value, (Object)f.i) && com.tencent.bugly.proguard.l.a((Object)value, (Object)f.j);
    }
}
