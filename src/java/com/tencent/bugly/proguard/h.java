package com.tencent.bugly.proguard;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.List;
import java.util.Map;

public final class h
{
    private StringBuilder a;
    private int b;
    
    public h(final StringBuilder a, final int b) {
        this.b = 0;
        this.a = a;
        this.b = b;
    }
    
    private <T> h a(final T t, final String s) {
        if (t == null) {
            this.a.append("null\n");
        }
        else if (t instanceof Byte) {
            final byte byteValue = (byte)t;
            this.a(s);
            final StringBuilder a = this.a;
            a.append((int)byteValue);
            a.append('\n');
        }
        else if (t instanceof Boolean) {
            final boolean booleanValue = (boolean)t;
            this.a(s);
            final StringBuilder a2 = this.a;
            char c;
            if (booleanValue) {
                c = 'T';
            }
            else {
                c = 'F';
            }
            a2.append(c);
            a2.append('\n');
        }
        else if (t instanceof Short) {
            final short shortValue = (short)t;
            this.a(s);
            final StringBuilder a3 = this.a;
            a3.append((int)shortValue);
            a3.append('\n');
        }
        else if (t instanceof Integer) {
            final int intValue = (int)t;
            this.a(s);
            final StringBuilder a4 = this.a;
            a4.append(intValue);
            a4.append('\n');
        }
        else if (t instanceof Long) {
            final long longValue = (long)t;
            this.a(s);
            final StringBuilder a5 = this.a;
            a5.append(longValue);
            a5.append('\n');
        }
        else if (t instanceof Float) {
            final float floatValue = (float)t;
            this.a(s);
            final StringBuilder a6 = this.a;
            a6.append(floatValue);
            a6.append('\n');
        }
        else if (t instanceof Double) {
            final double doubleValue = (double)t;
            this.a(s);
            final StringBuilder a7 = this.a;
            a7.append(doubleValue);
            a7.append('\n');
        }
        else if (t instanceof String) {
            this.a((String)t, s);
        }
        else if (t instanceof Map) {
            this.a((java.util.Map<Object, Object>)t, s);
        }
        else if (t instanceof List) {
            final List list = (List)t;
            if (list == null) {
                this.a(s);
                this.a.append("null\t");
            }
            else {
                this.a(((Collection)list).toArray(), s);
            }
        }
        else if (t instanceof k) {
            this.a((k)t, s);
        }
        else if (t instanceof byte[]) {
            this.a((byte[])(Object)t, s);
        }
        else if (t instanceof boolean[]) {
            this.a((Object)t, s);
        }
        else {
            final boolean b = t instanceof short[];
            final int n = 0;
            final int n2 = 0;
            final int n3 = 0;
            final int n4 = 0;
            int i = 0;
            if (b) {
                final short[] array = (Object)t;
                this.a(s);
                if (array == null) {
                    this.a.append("null\n");
                }
                else if (array.length == 0) {
                    final StringBuilder a8 = this.a;
                    a8.append(array.length);
                    a8.append(", []\n");
                }
                else {
                    final StringBuilder a9 = this.a;
                    a9.append(array.length);
                    a9.append(", [\n");
                    final h h = new h(this.a, this.b + 1);
                    while (i < array.length) {
                        final short n5 = array[i];
                        h.a(null);
                        final StringBuilder a10 = h.a;
                        a10.append((int)n5);
                        a10.append('\n');
                        ++i;
                    }
                    this.a(null);
                    final StringBuilder a11 = this.a;
                    a11.append(']');
                    a11.append('\n');
                }
            }
            else if (t instanceof int[]) {
                final int[] array2 = (Object)t;
                this.a(s);
                if (array2 == null) {
                    this.a.append("null\n");
                }
                else if (array2.length == 0) {
                    final StringBuilder a12 = this.a;
                    a12.append(array2.length);
                    a12.append(", []\n");
                }
                else {
                    final StringBuilder a13 = this.a;
                    a13.append(array2.length);
                    a13.append(", [\n");
                    final h h2 = new h(this.a, this.b + 1);
                    for (int length = array2.length, j = n; j < length; ++j) {
                        final int n6 = array2[j];
                        h2.a(null);
                        final StringBuilder a14 = h2.a;
                        a14.append(n6);
                        a14.append('\n');
                    }
                    this.a(null);
                    final StringBuilder a15 = this.a;
                    a15.append(']');
                    a15.append('\n');
                }
            }
            else if (t instanceof long[]) {
                final long[] array3 = (Object)t;
                this.a(s);
                if (array3 == null) {
                    this.a.append("null\n");
                }
                else if (array3.length == 0) {
                    final StringBuilder a16 = this.a;
                    a16.append(array3.length);
                    a16.append(", []\n");
                }
                else {
                    final StringBuilder a17 = this.a;
                    a17.append(array3.length);
                    a17.append(", [\n");
                    final h h3 = new h(this.a, this.b + 1);
                    for (int length2 = array3.length, k = n2; k < length2; ++k) {
                        final long n7 = array3[k];
                        h3.a(null);
                        final StringBuilder a18 = h3.a;
                        a18.append(n7);
                        a18.append('\n');
                    }
                    this.a(null);
                    final StringBuilder a19 = this.a;
                    a19.append(']');
                    a19.append('\n');
                }
            }
            else if (t instanceof float[]) {
                final float[] array4 = (Object)t;
                this.a(s);
                if (array4 == null) {
                    this.a.append("null\n");
                }
                else if (array4.length == 0) {
                    final StringBuilder a20 = this.a;
                    a20.append(array4.length);
                    a20.append(", []\n");
                }
                else {
                    final StringBuilder a21 = this.a;
                    a21.append(array4.length);
                    a21.append(", [\n");
                    final h h4 = new h(this.a, this.b + 1);
                    for (int length3 = array4.length, l = n3; l < length3; ++l) {
                        final float n8 = array4[l];
                        h4.a(null);
                        final StringBuilder a22 = h4.a;
                        a22.append(n8);
                        a22.append('\n');
                    }
                    this.a(null);
                    final StringBuilder a23 = this.a;
                    a23.append(']');
                    a23.append('\n');
                }
            }
            else if (t instanceof double[]) {
                final double[] array5 = (Object)t;
                this.a(s);
                if (array5 == null) {
                    this.a.append("null\n");
                }
                else if (array5.length == 0) {
                    final StringBuilder a24 = this.a;
                    a24.append(array5.length);
                    a24.append(", []\n");
                }
                else {
                    final StringBuilder a25 = this.a;
                    a25.append(array5.length);
                    a25.append(", [\n");
                    final h h5 = new h(this.a, this.b + 1);
                    for (int length4 = array5.length, n9 = n4; n9 < length4; ++n9) {
                        final double n10 = array5[n9];
                        h5.a(null);
                        final StringBuilder a26 = h5.a;
                        a26.append(n10);
                        a26.append('\n');
                    }
                    this.a(null);
                    final StringBuilder a27 = this.a;
                    a27.append(']');
                    a27.append('\n');
                }
            }
            else {
                if (!t.getClass().isArray()) {
                    throw new b("write object error: unsupport type.");
                }
                this.a((Object[])(Object)t, s);
            }
        }
        return this;
    }
    
    private <T> h a(final T[] array, final String s) {
        this.a(s);
        if (array == null) {
            this.a.append("null\n");
            return this;
        }
        if (array.length == 0) {
            final StringBuilder a = this.a;
            a.append(array.length);
            a.append(", []\n");
            return this;
        }
        final StringBuilder a2 = this.a;
        a2.append(array.length);
        a2.append(", [\n");
        final h h = new h(this.a, this.b + 1);
        for (int length = array.length, i = 0; i < length; ++i) {
            h.a(array[i], null);
        }
        this.a(null);
        final StringBuilder a3 = this.a;
        a3.append(']');
        a3.append('\n');
        return this;
    }
    
    private void a(final String s) {
        for (int i = 0; i < this.b; ++i) {
            this.a.append('\t');
        }
        if (s != null) {
            final StringBuilder a = this.a;
            a.append(s);
            a.append(": ");
        }
    }
    
    public final h a(final byte b, final String s) {
        this.a(s);
        final StringBuilder a = this.a;
        a.append((int)b);
        a.append('\n');
        return this;
    }
    
    public final h a(final int n, final String s) {
        this.a(s);
        final StringBuilder a = this.a;
        a.append(n);
        a.append('\n');
        return this;
    }
    
    public final h a(final long n, final String s) {
        this.a(s);
        final StringBuilder a = this.a;
        a.append(n);
        a.append('\n');
        return this;
    }
    
    public final h a(final k k, final String s) {
        this.a(s);
        final StringBuilder a = this.a;
        a.append('{');
        a.append('\n');
        if (k == null) {
            final StringBuilder a2 = this.a;
            a2.append('\t');
            a2.append("null");
        }
        else {
            k.a(this.a, this.b + 1);
        }
        this.a(null);
        final StringBuilder a3 = this.a;
        a3.append('}');
        a3.append('\n');
        return this;
    }
    
    public final h a(final String s, final String s2) {
        this.a(s2);
        if (s == null) {
            this.a.append("null\n");
        }
        else {
            final StringBuilder a = this.a;
            a.append(s);
            a.append('\n');
        }
        return this;
    }
    
    public final <K, V> h a(final Map<K, V> map, final String s) {
        this.a(s);
        if (map == null) {
            this.a.append("null\n");
            return this;
        }
        if (map.isEmpty()) {
            final StringBuilder a = this.a;
            a.append(map.size());
            a.append(", {}\n");
            return this;
        }
        final StringBuilder a2 = this.a;
        a2.append(map.size());
        a2.append(", {\n");
        final h h = new h(this.a, this.b + 1);
        final h h2 = new h(this.a, this.b + 2);
        for (final Map$Entry map$Entry : map.entrySet()) {
            h.a(null);
            final StringBuilder a3 = h.a;
            a3.append('(');
            a3.append('\n');
            h2.a(map$Entry.getKey(), null);
            h2.a(map$Entry.getValue(), null);
            h.a(null);
            final StringBuilder a4 = h.a;
            a4.append(')');
            a4.append('\n');
        }
        this.a(null);
        final StringBuilder a5 = this.a;
        a5.append('}');
        a5.append('\n');
        return this;
    }
    
    public final h a(final short n, final String s) {
        this.a(s);
        final StringBuilder a = this.a;
        a.append((int)n);
        a.append('\n');
        return this;
    }
    
    public final h a(final boolean b, final String s) {
        this.a(s);
        final StringBuilder a = this.a;
        char c;
        if (b) {
            c = 'T';
        }
        else {
            c = 'F';
        }
        a.append(c);
        a.append('\n');
        return this;
    }
    
    public final h a(final byte[] array, final String s) {
        this.a(s);
        if (array == null) {
            this.a.append("null\n");
            return this;
        }
        if (array.length == 0) {
            final StringBuilder a = this.a;
            a.append(array.length);
            a.append(", []\n");
            return this;
        }
        final StringBuilder a2 = this.a;
        a2.append(array.length);
        a2.append(", [\n");
        final h h = new h(this.a, this.b + 1);
        for (final byte b : array) {
            h.a(null);
            final StringBuilder a3 = h.a;
            a3.append((int)b);
            a3.append('\n');
        }
        this.a(null);
        final StringBuilder a4 = this.a;
        a4.append(']');
        a4.append('\n');
        return this;
    }
}
