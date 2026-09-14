package org.json.alipay;

import java.util.Collection;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class a
{
    public ArrayList a;
    
    public a() {
        this.a = new ArrayList();
    }
    
    public a(final Object o) {
        this();
        if (o.getClass().isArray()) {
            for (int length = Array.getLength(o), i = 0; i < length; ++i) {
                this.a.add(Array.get(o, i));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }
    
    public a(final String s) {
        this(new c(s));
    }
    
    public a(final Collection collection) {
        ArrayList a;
        if (collection == null) {
            a = new ArrayList();
        }
        else {
            a = new ArrayList(collection);
        }
        this.a = a;
    }
    
    public a(final c c) {
        this();
        final char c2 = c.c();
        char c3;
        if (c2 == '[') {
            c3 = ']';
        }
        else {
            if (c2 != '(') {
                throw c.a("A JSONArray text must start with '['");
            }
            c3 = ')';
        }
        if (c.c() == ']') {
            return;
        }
        do {
            c.a();
            final char c4 = c.c();
            c.a();
            ArrayList list;
            Object d;
            if (c4 == ',') {
                list = this.a;
                d = null;
            }
            else {
                list = this.a;
                d = c.d();
            }
            list.add(d);
            final char c5 = c.c();
            if (c5 != ')') {
                if (c5 == ',' || c5 == ';') {
                    continue;
                }
                if (c5 != ']') {
                    throw c.a("Expected a ',' or ']'");
                }
            }
            if (c3 == c5) {
                return;
            }
            final StringBuilder sb = new StringBuilder("Expected a '");
            sb.append((Object)new Character(c3));
            sb.append("'");
            throw c.a(sb.toString());
        } while (c.c() != ']');
    }
    
    private String a(final String s) {
        final int size = this.a.size();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; ++i) {
            if (i > 0) {
                sb.append(s);
            }
            sb.append(b.a(this.a.get(i)));
        }
        return sb.toString();
    }
    
    public final int a() {
        return this.a.size();
    }
    
    public final Object a(final int n) {
        Object value;
        if (n >= 0 && n < this.a.size()) {
            value = this.a.get(n);
        }
        else {
            value = null;
        }
        if (value != null) {
            return value;
        }
        final StringBuilder sb = new StringBuilder("JSONArray[");
        sb.append(n);
        sb.append("] not found.");
        throw new JSONException(sb.toString());
    }
    
    @Override
    public String toString() {
        try {
            final StringBuilder sb = new StringBuilder("[");
            sb.append(this.a(","));
            sb.append(']');
            return sb.toString();
        }
        catch (final Exception ex) {
            return null;
        }
    }
}
