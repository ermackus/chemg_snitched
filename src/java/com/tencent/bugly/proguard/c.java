package com.tencent.bugly.proguard;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class c extends a
{
    protected HashMap<String, byte[]> d;
    private HashMap<String, Object> e;
    private i f;
    
    public c() {
        this.d = null;
        this.e = (HashMap<String, Object>)new HashMap();
        this.f = new i();
    }
    
    public <T> void a(final String s, final T t) {
        if (this.d == null) {
            super.a(s, (Object)t);
            return;
        }
        if (s == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (!(t instanceof Set)) {
            final j j = new j();
            j.a(this.b);
            j.a((Object)t, 0);
            this.d.put((Object)s, (Object)l.a(j.a()));
            return;
        }
        throw new IllegalArgumentException("can not support Set");
    }
    
    public void a(final byte[] array) {
        try {
            super.a(array);
        }
        catch (final Exception ex) {
            this.f.a(array);
            this.f.a(this.b);
            final HashMap hashMap = new HashMap(1);
            hashMap.put((Object)"", (Object)new byte[0]);
            this.d = (HashMap<String, byte[]>)this.f.a((Map)hashMap, 0, false);
        }
    }
    
    public byte[] a() {
        if (this.d != null) {
            final j j = new j(0);
            j.a(this.b);
            j.a((Map)this.d, 0);
            return l.a(j.a());
        }
        return super.a();
    }
    
    public final <T> T b(final String s, final T t) throws b {
        final HashMap<String, byte[]> d = this.d;
        if (d != null) {
            if (!d.containsKey((Object)s)) {
                return null;
            }
            if (this.e.containsKey((Object)s)) {
                return (T)this.e.get((Object)s);
            }
            final byte[] array = (byte[])this.d.get((Object)s);
            try {
                this.f.a(array);
                this.f.a(this.b);
                final Object a = this.f.a((Object)t, 0, true);
                if (a != null) {
                    this.e.put((Object)s, a);
                }
                return (T)a;
            }
            catch (final Exception ex) {
                throw new b(ex);
            }
        }
        if (!this.a.containsKey((Object)s)) {
            return null;
        }
        if (this.e.containsKey((Object)s)) {
            return (T)this.e.get((Object)s);
        }
        final HashMap hashMap = (HashMap)this.a.get((Object)s);
        byte[] array2 = new byte[0];
        final Iterator iterator = hashMap.entrySet().iterator();
        if (iterator.hasNext()) {
            final Map$Entry map$Entry = (Map$Entry)iterator.next();
            map$Entry.getKey();
            array2 = (byte[])map$Entry.getValue();
        }
        try {
            this.f.a(array2);
            this.f.a(this.b);
            final Object a2 = this.f.a((Object)t, 0, true);
            this.e.put((Object)s, a2);
            return (T)a2;
        }
        catch (final Exception ex2) {
            throw new b(ex2);
        }
    }
    
    public void c() {
        this.d = (HashMap<String, byte[]>)new HashMap();
    }
}
