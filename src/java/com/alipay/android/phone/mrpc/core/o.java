package com.alipay.android.phone.mrpc.core;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import java.util.ArrayList;

public final class o extends t
{
    public String b;
    public byte[] c;
    public String d;
    public ArrayList<Header> e;
    public Map<String, String> f;
    public boolean g;
    
    public o(final String b) {
        this.b = b;
        this.e = (ArrayList<Header>)new ArrayList();
        this.f = (Map<String, String>)new HashMap();
        this.d = "application/x-www-form-urlencoded";
    }
    
    public final String a() {
        return this.b;
    }
    
    public final void a(final String d) {
        this.d = d;
    }
    
    public final void a(final String s, final String s2) {
        if (this.f == null) {
            this.f = (Map<String, String>)new HashMap();
        }
        this.f.put((Object)s, (Object)s2);
    }
    
    public final void a(final Header header) {
        this.e.add((Object)header);
    }
    
    public final void a(final boolean g) {
        this.g = g;
    }
    
    public final void a(final byte[] c) {
        this.c = c;
    }
    
    public final String b(final String s) {
        final Map<String, String> f = this.f;
        if (f == null) {
            return null;
        }
        return (String)f.get((Object)s);
    }
    
    public final byte[] b() {
        return this.c;
    }
    
    public final String c() {
        return this.d;
    }
    
    public final ArrayList<Header> d() {
        return this.e;
    }
    
    public final boolean e() {
        return this.g;
    }
    
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.class != o.getClass()) {
            return false;
        }
        final o o2 = (o)o;
        final byte[] c = this.c;
        if (c == null) {
            if (o2.c != null) {
                return false;
            }
        }
        else if (!c.equals(o2.c)) {
            return false;
        }
        final String b = this.b;
        final String b2 = o2.b;
        if (b == null) {
            if (b2 != null) {
                return false;
            }
        }
        else if (!b.equals((Object)b2)) {
            return false;
        }
        return true;
    }
    
    public final int hashCode() {
        final Map<String, String> f = this.f;
        int n;
        if (f != null && f.containsKey((Object)"id")) {
            n = ((String)this.f.get((Object)"id")).hashCode() + 31;
        }
        else {
            n = 1;
        }
        final String b = this.b;
        int hashCode;
        if (b == null) {
            hashCode = 0;
        }
        else {
            hashCode = b.hashCode();
        }
        return n * 31 + hashCode;
    }
    
    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", new Object[] { this.b, this.e });
    }
}
