package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.HashMap;

public final class d extends c
{
    private static HashMap<String, byte[]> f;
    private static HashMap<String, HashMap<String, byte[]>> g;
    private f e;
    
    public d() {
        final f e = new f();
        this.e = e;
        e.a = 2;
    }
    
    public final void a(final int n) {
        this.e.b = 1;
    }
    
    @Override
    public final <T> void a(final String s, final T t) {
        if (!s.startsWith(".")) {
            super.a(s, t);
            return;
        }
        final StringBuilder sb = new StringBuilder("put name can not startwith . , now is ");
        sb.append(s);
        throw new IllegalArgumentException(sb.toString());
    }
    
    @Override
    public final void a(final byte[] array) {
        if (array.length >= 4) {
            try {
                final i i = new i(array, 4);
                i.a(this.b);
                this.e.a(i);
                if (this.e.a == 3) {
                    final i j = new i(this.e.e);
                    j.a(this.b);
                    if (d.f == null) {
                        (d.f = (HashMap<String, byte[]>)new HashMap()).put((Object)"", (Object)new byte[0]);
                    }
                    this.d = (HashMap<String, byte[]>)j.a((Map)d.f, 0, false);
                    return;
                }
                final i k = new i(this.e.e);
                k.a(this.b);
                if (d.g == null) {
                    d.g = (HashMap<String, HashMap<String, byte[]>>)new HashMap();
                    final HashMap hashMap = new HashMap();
                    hashMap.put((Object)"", (Object)new byte[0]);
                    d.g.put((Object)"", (Object)hashMap);
                }
                this.a = k.a((Map)d.g, 0, false);
                new HashMap();
                return;
            }
            catch (final Exception ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        throw new IllegalArgumentException("decode package must include size head");
    }
    
    @Override
    public final byte[] a() {
        if (this.e.a == 2) {
            if (this.e.c.equals((Object)"")) {
                throw new IllegalArgumentException("servantName can not is null");
            }
            if (this.e.d.equals((Object)"")) {
                throw new IllegalArgumentException("funcName can not is null");
            }
        }
        else {
            if (this.e.c == null) {
                this.e.c = "";
            }
            if (this.e.d == null) {
                this.e.d = "";
            }
        }
        final j j = new j(0);
        j.a(this.b);
        if (this.e.a == 2) {
            j.a((Map)this.a, 0);
        }
        else {
            j.a((Map)this.d, 0);
        }
        this.e.e = l.a(j.a());
        final j i = new j(0);
        i.a(this.b);
        this.e.a(i);
        final byte[] a = l.a(i.a());
        final int n = a.length + 4;
        final ByteBuffer allocate = ByteBuffer.allocate(n);
        allocate.putInt(n).put(a).flip();
        return allocate.array();
    }
    
    public final void b(final String c) {
        this.e.c = c;
    }
    
    @Override
    public final void c() {
        super.c();
        this.e.a = 3;
    }
    
    public final void c(final String d) {
        this.e.d = d;
    }
}
